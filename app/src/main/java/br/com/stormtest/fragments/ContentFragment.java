package br.com.stormtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.stormtest.R;
import br.com.stormtest.adapters.RecyclerViewAdapter;
import br.com.stormtest.models.Content;
import br.com.stormtest.presenters.ContentPresenter;

public class ContentFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Content> contents;


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

    public List<Content> getContent() {
        return contents;
    }

    public void setContent(List<Content> contents) {
        this.contents = contents;
    }
}
