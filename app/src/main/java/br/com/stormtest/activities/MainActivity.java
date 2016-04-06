package br.com.stormtest.activities;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import br.com.stormtest.R;
import br.com.stormtest.adapters.ContentFragmentAdapter;
import br.com.stormtest.cache.CacheManager;
import br.com.stormtest.fragments.ContentFragment;
import br.com.stormtest.models.Content;
import br.com.stormtest.presenters.ContentPresenter;


public class MainActivity extends AppCompatActivity implements ContentPresenter.ContentInterface {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ContentFragmentAdapter cfa;
    private ContentPresenter contentPresenter;
    private ContentFragment searchFragment;
    private SearchView searchView;
    private FrameLayout searchResult;
    private List<Content> contents;

    private final String LOG_TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        searchResult = (FrameLayout) findViewById(R.id.searchResult);


        contentPresenter = new ContentPresenter(this);


        if (CacheManager.getInstance().savedRequestIsValid(getApplicationContext())) {

            createScreen(CacheManager.getInstance().getContents(getApplicationContext()));

            Log.d(LOG_TAG, "Caiu no cache");

        } else {

            contentPresenter.getContent();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        manageSearch(searchView);

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                if (item.getItemId() == R.id.action_search) {
                    item.setActionView(searchView);
                    searchFragment = ContentFragment.newInstance();
                    searchFragment.setContent(new ArrayList<Content>());
                    setContentVisibility(View.GONE);

                }

                return true;

            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                if (item.getItemId() == R.id.action_search) {
                    setContentVisibility(View.VISIBLE);
                    searchView.setIconified(true);

                }

                return true;
            }
        });
        MenuItemCompat.setActionView(menuItem, searchView);
        return true;
    }

    private void manageSearch(SearchView searchView) {

        if (searchView != null) {

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    List<Content> found =
                            Stream.of(contents)
                                    .filter((c) -> !TextUtils.isEmpty(c.getContentTitle()) &&
                                            c.getContentTitle().toLowerCase().contains(query.toLowerCase())
                                            || c.getDescription().toLowerCase().contains(query.toLowerCase()))
                                    .collect(Collectors.toList());

                    searchFragment.setContent(found);

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    List<Content> found =
                            Stream.of(contents)
                                    .filter((c) -> !TextUtils.isEmpty(c.getContentTitle()) &&
                                            c.getContentTitle().toLowerCase().contains(newText.toLowerCase())
                                            || c.getDescription().toLowerCase().contains(newText.toLowerCase()))
                                    .collect(Collectors.toList());

                    searchFragment.setContent(found);
                    return false;
                }
            });
        }
    }


    private void setContentVisibility(int visibility) {
        viewPager.setVisibility(visibility);
        tabLayout.setVisibility(visibility);

        if (visibility == View.GONE) {
            searchResult.setVisibility(View.VISIBLE);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.searchResult, searchFragment);
            ft.commit();
        } else {
            searchResult.setVisibility(View.GONE);
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        contentPresenter.subscribe(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        contentPresenter.unsubscribe();
    }

    private void createScreen(List<Content> content) {
        this.contents = content;

        CacheManager.getInstance().saveRequest(getApplicationContext(), content);

        cfa = new ContentFragmentAdapter(getSupportFragmentManager(), this, content);

        viewPager.setAdapter(cfa);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void getContentReturn(List<Content> content) {

        Log.d(LOG_TAG, "Fez request..");

        CacheManager.getInstance().saveRequest(getApplicationContext(), content);

        createScreen(content);

    }
}
