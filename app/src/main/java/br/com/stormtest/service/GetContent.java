package br.com.stormtest.service;


import java.util.List;

import br.com.stormtest.models.Content;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by root on 04/04/16.
 */
public class GetContent {

    private GetContentApi  clientApi;

    private final String SERVICE_ENDPOINT = "http://private-38dd0-stormtest.apiary-mock.com";

    public GetContent() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(SERVICE_ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new CustomHttpClient())
                .build();

        clientApi = restAdapter.create(GetContentApi.class);
    }

    public GetContentApi getApi() {
        return clientApi;
    }

    public interface GetContentApi {

        @GET("/content")
        Observable<List<Content>> getContent();
    }
}
