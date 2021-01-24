package classfour.socket.client;

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

public class HttpAsyncClientDemo {

    public static void main(String[] args){
        /*
        * ConnectTimeout : 连接超时,连接建立时间,三次握手完成时间。
        * SocketTimeout : 请求超时,数据传输过程中数据包之间间隔的最大时间。
        * ConnectionRequestTimeout : 使用连接池来管理连接,从连接池获取连接的超时时间。
        * */
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
            //连接池中最大连接数
            connectionManager.setMaxTotal(100);
            //分配给同一个route(路由)最大的并发连接数
            connectionManager.setDefaultMaxPerRoute(100);

            final CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
            HttpGet httpGet = new HttpGet("http://localhost:8801");
            httpClient.start();
            httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse response) {
                    try {
                        String content = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine();
                        System.out.println("content:" + content);
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
