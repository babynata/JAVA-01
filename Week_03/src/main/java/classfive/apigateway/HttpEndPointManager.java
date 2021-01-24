package classfive.apigateway;

import java.util.ArrayList;
import java.util.List;

public class HttpEndPointManager {

    private static List<String> urls = new ArrayList<>();

    static {
        urls.add("http://localhost:8801");
        urls.add("http://localhost:8802");
        urls.add("http://localhost:8803");
    }

    public static List<String> getUrls() {
        return urls;
    }
}
