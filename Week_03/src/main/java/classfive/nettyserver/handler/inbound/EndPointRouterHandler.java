package classfive.nettyserver.handler.inbound;

import classfive.nettyserver.HttpEndPointManager;
import classfive.nettyserver.NamedThreadFactory;
import classfive.nettyserver.filter.HeaderHttpResponseFilter;
import classfive.nettyserver.filter.HttpResponseFilter;
import classfive.nettyserver.httpClient.HttpAsyncClientHandler;
import classfive.nettyserver.router.HttpEndpointRouter;
import classfive.nettyserver.router.RandomHttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EndPointRouterHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

    private static final int CORE = Runtime.getRuntime().availableProcessors();

    private static final int KEEP_ALIVE_TIME = 1000;

    private static final int QUEUE_SIZE = 2048;

    private List<String> urls = HttpEndPointManager.getUrls();

    private HttpEndpointRouter httpEndpointRouter = new RandomHttpEndpointRouter();

    private HttpAsyncClientHandler httpAsyncClientHandler = new HttpAsyncClientHandler();

    private HttpResponseFilter httpResponseFilter = new HeaderHttpResponseFilter();

    private ExecutorService proxyService = new ThreadPoolExecutor(CORE, CORE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(QUEUE_SIZE), new NamedThreadFactory("proxyService"), new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        final String url = httpEndpointRouter.route(urls);
//        proxyService.submit(() -> httpAsyncClientHandler.get(url, msg, ctx, httpResponseFilter));
        httpAsyncClientHandler.get(url, msg, ctx, httpResponseFilter);
    }
}
