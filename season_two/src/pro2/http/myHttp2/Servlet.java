package pro2.http.myHttp2;

import java.io.IOException;

/**
 * Created by Near on 2016/1/26.
 */
public abstract class Servlet {

    public void service(Request request, Response response) throws IOException{
       this.doGet(request, response);
       this.doPost(request, response);
    }

    public abstract void doGet(Request request, Response response) throws IOException;

    public abstract void doPost(Request request, Response response) throws IOException;

}
