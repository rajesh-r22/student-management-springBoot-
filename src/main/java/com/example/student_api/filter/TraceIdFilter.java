package com.example.student_api.filter;

import jakarta.servlet.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Component
public class TraceIdFilter implements Filter {

    private static final String TRACE_ID_KEY = "traceId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String traceId = UUID.randomUUID().toString().substring(0, 8); // short, readable id
            MDC.put(TRACE_ID_KEY, traceId); // attaches to EVERY log line on this thread, for this request

            chain.doFilter(request, response); // request proceeds through Controller → Service → Repository
        } finally {
            MDC.clear(); // CRITICAL: prevents trace IDs leaking across requests on reused threads (thread pool!)
        }
    }
}