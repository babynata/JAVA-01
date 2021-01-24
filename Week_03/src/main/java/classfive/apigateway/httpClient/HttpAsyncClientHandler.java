package classfive.apigateway.httpClient;

import classfive.apigateway.filter.HttpResponseFilter;
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
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class HttpAsyncClientHandler {

    public void get(String url, FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, HttpResponseFilter httpResponseFilter) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(500000)
                .setSocketTimeout(500000)
                .setConnectionRequestTimeout(1000)
                .build();
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(true)
                .build();
        ConnectingIOReactor connectingIOReactor = null;
        try {
            connectingIOReactor = new DefaultConnectingIOReactor(ioReactorConfig);
            PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(connectingIOReactor);
            connectionManager.setMaxTotal(100);
            connectionManager.setDefaultMaxPerRoute(100);

            final CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
            HttpGet httpGet = new HttpGet(url);
            httpClient.start();
            httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse response) {
                    try {
                        String content = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine();
                        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(fullHttpRequest.protocolVersion(), HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes(Charset.forName("utf-8"))));
                        fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");

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

        } catch (IOReactorException e) {
            e.printStackTrace();
        }
    }
}
