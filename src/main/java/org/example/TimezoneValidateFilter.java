package org.example;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.zone.ZoneRulesException;

@WebFilter(value = "/time")
public class TimezoneValidateFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String timezone = httpRequest.getParameter("timezone");

        if (timezone != null && !timezone.isEmpty()){
            try{
                Timezone.validateTimezone(timezone);
                chain.doFilter(request, response);

            }catch (IllegalArgumentException | ZoneRulesException e){
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpResponse.setContentType("text/html");
                httpResponse.getWriter()
                             .write("<html><body><h2>Invalid timezone</h2></body></html>");
            }
        }else{
            chain.doFilter(request, response);
        }

    }
    public void init(FilterConfig config) throws ServletException {}

    public void destroy() {}

}