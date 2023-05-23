package com.example.sbprojectex.Filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sbprojectex.Filter.httpCache.CachedHttpServletRequest;
import com.example.sbprojectex.Filter.httpCache.CachedHttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class RequestCachingFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            
                //  HttpServlet class wrapping
            CachedHttpServletRequest cachedRequest = new CachedHttpServletRequest(request);
            CachedHttpServletResponse cachedResponse = new CachedHttpServletResponse(response);

            String queryStr     = cachedRequest.getQueryString();
            String reqUri       = queryStr == null ? cachedRequest.getRequestURI() : cachedRequest.getRequestURI() + "?" + queryStr;
            String reqAddr      = cachedRequest.getRemoteAddr();
            String httpMtd      = cachedRequest.getMethod();
            String contentType  = cachedRequest.getContentType();
            String params       = getReqPrmStr(cachedRequest);
            String reqDataStr   = cachedRequest.toDataString();
            String resDataStr   = "";

            log.debug("[{}]      method : [{}],   URI : [{}],   contentType : [{}],   params : [{}]",
                                reqAddr,  httpMtd,         reqUri,       contentType,          params );
            
            // request body data
            reqDataStr = reqDataStr.contains("multipart/form-data") ? "" : reqDataStr;
            log.debug("REQUEST DATA: " + reqDataStr);
            
            // do Filter
            filterChain.doFilter(cachedRequest, cachedResponse);

            // response body data
            resDataStr = cachedResponse.toDateString();
            log.debug("RESPONSE DATA: " + resDataStr);

        }

        protected String getReqPrmStr(HttpServletRequest request){
            String result = "";
            for(Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()){
                String  entryStr = entry.getKey();
                        entryStr += "=";
                        entryStr += Arrays.toString(entry.getValue());
                        entryStr += ", ";
    
                result += entryStr;
            }
            return result;
        }
    
}
