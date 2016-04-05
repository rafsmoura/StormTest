package br.com.stormtest.service;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

/**
 * Created by root on 04/04/16.
 */
public final class CustomHttpClient extends UrlConnectionClient {

    @Override
    protected HttpURLConnection openConnection(Request request) throws IOException {
        HttpURLConnection connection = super.openConnection(request);
        connection.setConnectTimeout(60 * 1000);
        connection.setReadTimeout(60 * 1000);
        return connection;
    }
}
