package br.com.stormtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.stormtest.R;
import br.com.stormtest.adapters.RecyclerViewAdapter;
import br.com.stormtest.cache.CacheManager;
import br.com.stormtest.models.Content;

public class ContentFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Content> contents;
    private boolean isFavoriteFragment = false;


    public ContentFragment() {
    }



    public static ContentFragment newInstance() {
        ContentFragment fragment = new ContentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_content, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.contentList);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), contents);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (recyclerView != null && isFavoriteFragment) {
            recyclerViewAdapter.setContents(CacheManager.getInstance().getFavorites(getContext()));
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public List<Content> getContent() {
        return contents;
    }

    public void setContent(List<Content> contents) {
        this.contents = contents;
        if (recyclerViewAdapter != null && recyclerView != null) {
            recyclerViewAdapter.setContents(contents);
            recyclerView.getAdapter().notifyDataSetChanged();
        }

    }

    public boolean isFavoriteFragment() {
        return isFavoriteFragment;
    }

    public void setFavoriteFragment(boolean isFavoriteFragment) {
        this.isFavoriteFragment = isFavoriteFragment;
    }


}
