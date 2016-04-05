package br.com.stormtest.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import br.com.stormtest.R;
import br.com.stormtest.fragments.ContentFragment;
import br.com.stormtest.models.Content;

/**
 * Created by root on 03/04/16.
 */
public class ContentFragmentAdapter extends FragmentPagerAdapter {

    private int NUMBER_OF_PAGES = 3;
    private Context context;
    private List<Content> contentList, videoList, articleList, favoriteList;

    public ContentFragmentAdapter(FragmentManager fm, Context context, List<Content> contentList) {
        super(fm);
        this.contentList = contentList;
        this.context = context;

        for (Content content : contentList) {

            if (TextUtils.equals(content.getType(), "Video")) {

                if (videoList == null) videoList = new ArrayList<>();

                videoList.add(content);
            } else {

                if (articleList == null) articleList = new ArrayList<>();

                articleList.add(content);
            }
        }

        //Lógica para favoritos...
    }

    @Override
    public Fragment getItem(int position) {

        ContentFragment contentFragment = ContentFragment.newInstance();

        if (position == 0) {
            contentFragment.setContent(videoList);
        } else if (position == 1) {
            contentFragment.setContent(articleList);
        } else if (position == 2) {
            contentFragment.setContent(contentList);
        }


        return contentFragment;

    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return context.getResources().getString(R.string.video_tab_title);
        } else if(position == 1) {
            return context.getResources().getString(R.string.articles_tab_title);
        } else if (position == 2) {
            return context.getResources().getString(R.string.favorites_tab_title);
        } else {
            return context.getResources().getString(R.string.fail_tab_title);
        }


    }
}
