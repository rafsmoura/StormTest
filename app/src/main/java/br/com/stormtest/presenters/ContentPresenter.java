package br.com.stormtest.presenters;

import android.util.Log;


import java.util.List;

import br.com.stormtest.models.Content;
import br.com.stormtest.service.GetContent;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 04/04/16.
 */
public class ContentPresenter {

    private static final String LOG_TAG = "ContentPresenter";
    GetContent getContent;
    ContentInterface contentInterface;

    public ContentPresenter(ContentInterface contentInterface) {

        this.contentInterface = contentInterface;
        this.getContent = new GetContent();
    }

    public void getContent() {
        getContent.getApi()
                .getContent()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Content>>() {
                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                               }

                               @Override
                               public void onNext(List<Content> content) {
                                   if (contentInterface != null) {
                                       contentInterface.getContentReturn(content);
                                   } else {
                                       Log.d(LOG_TAG, "getContent - tenho resultados mas ninguém registrado para recebê-los.");
                                   }
                               }
                           }
                );
    }

    public void subscribe(ContentInterface contentInterface) {
        this.contentInterface = contentInterface;
    }

    public void unsubscribe() {
        this.contentInterface = null;
    }

    public interface ContentInterface {

        void getContentReturn(List<Content> content);

    }
}
