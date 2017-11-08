package Servlet.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dream Sky on 2017/3/10.
 * 该过滤器主要用于拦截未登陆用户的浏览操作
 * ###弃用###
 */
/*@WebFilter(filterName = "LoginFilter", urlPatterns = {"/index.html", "/UserServlet", "/AlbumServlet",
        "/AnnounceServlet", "/DynamicServlet", "/MessageServlet", "/PhotoServlet"})*/
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        final String login_page = "/index.html";
        /*转换为HttpServle对象t*/
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        /*获取请求URI*/
        String reqURI = request.getRequestURI();
        System.out.println("reqURI:" + reqURI);
        /**/
        String ctxPath = request.getContextPath();
        System.out.println("ctxPath:" + ctxPath);
        //除掉项目名称时访问页面当前路径
        String targetURL = reqURI.substring(ctxPath.length());
        System.out.println("targetURL:" + targetURL);
        String action = request.getParameter("action");
        /*如果请求的动作为登陆,则通过过滤器*/
        if (action != null && action.equals("UserLogin")) {
            chain.doFilter(req, resp);
            return;
        }
        /*获取session*/
        HttpSession session = request.getSession();
        /*判断当前是否为登陆页面*/
        if (!login_page.equals(targetURL) && !targetURL.equals("/")) {
            System.out.println("非登陆页面");
            if (session.getAttribute("UserSession") == null) {
                /*不是登陆界面且没有登录，则跳转回登录界面*/
                //response.sendRedirect("index.html");
                response.getWriter().print("<script>window.location.href='index.html'</script>");
                System.out.println("未登陆");
            }
        } else {
            System.out.println("登录页面");
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
