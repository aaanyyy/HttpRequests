package Util;

import Models.MyHttpClient;
import Models.Response;

import java.util.function.Consumer;

public abstract class AsyncRequest extends Thread{
    public AsyncRequest() {

    }

    abstract public Response doAsync();
    Consumer <Response> onResult;

    public AsyncRequest(MyHttpClient myHttpClient,Consumer<Response> onResult) {
        this.onResult = onResult;
    }

    public void run()
    {
        Response response=doAsync();
        onResult.accept(response);
    }
}
