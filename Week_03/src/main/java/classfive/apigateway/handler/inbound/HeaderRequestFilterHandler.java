package classfive.apigateway.handler.inbound;

import classfive.apigateway.filter.HeaderHttpRequestFilter;
import classfive.apigateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderRequestFilterHandler extends ChannelInboundHandlerAdapter {

    private HttpRequestFilter httpRequestFilter = new HeaderHttpRequestFilter();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        httpRequestFilter.filter(fullHttpRequest, ctx);
        ctx.fireChannelRead(fullHttpRequest);
    }

}
