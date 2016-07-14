package ro.teamnet.zth.web;


import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import java.io.IOException;

public class HeadersLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LogFileWriter.logHeader("user", servletRequest.getParameter("user"));
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
