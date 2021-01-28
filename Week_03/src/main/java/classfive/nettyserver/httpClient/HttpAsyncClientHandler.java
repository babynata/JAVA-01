package classfive.nettyserver.httpClient;

import classfive.nettyserver.filter.HttpResponseFilter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class HttpAsyncClientHandler {

    private final CloseableHttpAsyncClient httpClient;

    public HttpAsyncClientHandler() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(500000)
                .setSocketTimeout(500000)
                .setConnectionRequestTimeout(1000)
                .build();
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(true)
                .build();
        this.httpClient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioReactorConfig)
                .setKeepAliveStrategy(((response, context) -> 6000))
                .setDefaultRequestConfig(requestConfig).build();
        this.httpClient.start();
    }

    public void get(String url, FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, HttpResponseFilter httpResponseFilter) {

        HttpGet httpGet = new HttpGet(url);
        httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse response) {
                try {
                    String content = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine();
                    FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes(Charset.forName("utf-8"))));
                    fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");
                    fullHttpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, content.getBytes().length);

                    httpResponseFilter.filter(fullHttpResponse, ctx);

                    boolean isKeepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
                    if (isKeepAlive) {
                        fullHttpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                        ctx.writeAndFlush(fullHttpResponse);
                    } else {
                        ctx.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void failed(Exception ex) {
                httpGet.abort();
                ex.printStackTrace();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }
}
