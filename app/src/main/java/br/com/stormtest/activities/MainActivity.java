package br.com.stormtest.activities;

;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;


import java.util.List;

import br.com.stormtest.R;
import br.com.stormtest.adapters.ContentFragmentAdapter;
import br.com.stormtest.models.Content;
import br.com.stormtest.presenters.ContentPresenter;


public class MainActivity extends AppCompatActivity implements ContentPresenter.ContentInterface {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ContentFragmentAdapter cfa;
    private ContentPresenter contentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        contentPresenter = new ContentPresenter(this);
        contentPresenter.getContent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
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

    @Override
    public void getContentReturn(List<Content> content) {

        cfa = new ContentFragmentAdapter(getSupportFragmentManager(), this, content);

        viewPager.setAdapter(cfa);

        tabLayout.setupWithViewPager(viewPager);
    }
}
