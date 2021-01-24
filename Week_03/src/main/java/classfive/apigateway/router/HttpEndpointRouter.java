package classfive.apigateway.router;

import java.util.List;

public interface HttpEndpointRouter {

    String route(List<String> endPoints);
}
