package classfour.netty.httpserver;

import classfour.socket.client.HttpClientDemo;
import classfour.socket.client.OkHttpDemo;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/*
* 简单的http转发,请求8801
* */
public class HttpSimpleHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine();
                HttpResponse httpResponse = new DefaultFullHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes(Charset.forName("utf-8"))));
                httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");

                boolean isKeepAlive = HttpUtil.isKeepAlive(msg);
                if (isKeepAlive) {
                    httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                    ctx.writeAndFlush(httpResponse);
                } else {
                    ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
