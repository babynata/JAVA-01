package classfive.apigateway.router;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter{
    @Override
    public String route(List<String> endPoints) {
        int size = endPoints.size();
        Random random = new Random(System.currentTimeMillis());
        return endPoints.get(random.nextInt(size));
    }
}
