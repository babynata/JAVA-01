package classfour.netty.httpserver;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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
public class HttpSimpleHandler extends ChannelInboundHandlerAdapter {

    public HttpSimpleHandler() {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8803");
        try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine();
                HttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes(Charset.forName("utf-8"))));
                httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
                httpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, content.getBytes().length);

                boolean isKeepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
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
