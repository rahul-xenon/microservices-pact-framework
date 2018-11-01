package se.ff.demo;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
public class TodoClient {
    int port=443;
    String host="jsonplaceholder.typicode.com";

    TodoClient() {
        // Will use default port.
        System.out.println("Default port "+port);
    }

    TodoClient(String host, int port) {
        this.host=host;
        this.port=port;
        System.out.println("Custom host: "+host);
        System.out.println("Custom port: "+port);
    }

    public static void main( String[] args ) {
        Todo t = new TodoClient().call(1);
        System.out.println("\n----------TOTO-----------\n"+
                t
                +"\n------------------------");
    }

    Todo call(int id) {
        try {
            String url=String.format("Http://%s:%d/todos/%d", host, port, id);
            System.out.println("using url: "+url);
            HttpResponse r = Request.Get(url).execute().returnResponse();
            String json = EntityUtils.toString(r.getEntity());
            System.out.println("json="+json);
            Todo t = new Gson().fromJson(json, Todo.class);

            return t;

        }
        catch (Exception e) {
            System.out.println("Unable to get eta, e="+e);
            return null;
        }

    }
}

