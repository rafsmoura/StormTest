package br.com.stormtest.cache;

/**
 * Created by root on 06/04/16.
 */
public class ReturnWrapper {

    private String requestReturn;
    private long time;

    public ReturnWrapper(String requestReturn, long time) {
        this.requestReturn = requestReturn;
        this.time = time;
    }

    public String getRequestReturn() {
        return requestReturn;
    }

    public void setRequestReturn(String requestReturn) {
        this.requestReturn = requestReturn;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
