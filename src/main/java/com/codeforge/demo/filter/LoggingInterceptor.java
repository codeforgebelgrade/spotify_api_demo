package com.codeforge.demo.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static java.rmi.server.LogStream.log;


@Component
public class LoggingInterceptor implements Filter {

    private final static String CORRELATION_ID = "X-Correlation-Id";
    private final static Logger log = LogManager.getLogger(LoggingInterceptor.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {

            var req = (HttpServletRequest) servletRequest;
            CustomRequestWrapper servletRequestWrapper = new CustomRequestWrapper(req);
            var resp = (HttpServletResponse) servletResponse;
            var correlationId = req.getHeader(CORRELATION_ID);
            if (correlationId == null || correlationId.equals("")) {
                var uuid = UUID.randomUUID().toString();
                correlationId = uuid.toString();
                servletRequestWrapper.addHeader(CORRELATION_ID, correlationId);
            }

            MDC.put("cid", correlationId);
            log.info("Request: {} {} {} Headers: {}", correlationId, req.getMethod(), req.getRequestURL(),
                    getRequestHeaderContent(servletRequestWrapper));
            resp.addHeader(CORRELATION_ID, correlationId);
            filterChain.doFilter(req, resp);
            log.info("Response: {}", correlationId, resp.getStatus());
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private String getRequestHeaderContent(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        List<String> list = Collections.list(headerNames);
        Map<String, String> requestHeaders = new HashMap<>();
        if (!list.isEmpty()) {
            for(String headerName : list) {
                requestHeaders.put(headerName, request.getHeader(headerName));
            }
        }

        StringBuilder mapAsString = new StringBuilder("{");
        for (String key : requestHeaders.keySet()) {
            mapAsString.append(key + ":" + "\"" + requestHeaders.get(key) + "\"" + ", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
        return mapAsString.toString();
    }
}
