package classfive.nettyserver.router;

import java.util.List;

public interface HttpEndpointRouter {

    String route(List<String> endPoints);
}
