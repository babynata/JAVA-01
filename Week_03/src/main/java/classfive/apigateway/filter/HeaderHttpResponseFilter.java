package classfive.apigateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse fullHttpResponse, ChannelHandlerContext ctx) {
        fullHttpResponse.headers().set("mao", "maomaomao");
    }
}
