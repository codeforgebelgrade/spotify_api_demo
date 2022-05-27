package com.codeforge.demo.filter;
import org.slf4j.MDC;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationFilter implements Filter {

    public static final String REQUEST_ID_HEADER = "X-Correlation-Id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        if ( request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            doHttpFilter((HttpServletRequest)request, (HttpServletResponse)response, filterChain);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        /*CustomRequestWrapper servletRequestWrapper = new CustomRequestWrapper(request);
        String requestId = request.getHeader(REQUEST_ID_HEADER);
        // verifies the correlation id was set
        if (requestId == null || requestId.isEmpty()) {
            UUID correlationUuid = UUID.randomUUID();
            requestId = correlationUuid.toString();
            servletRequestWrapper.addHeader(REQUEST_ID_HEADER, requestId);
        }

        MDC.put("cid", requestId);
        //response.addHeader(REQUEST_ID_HEADER, requestId);*/
        chain.doFilter(request, response);
    }
}
