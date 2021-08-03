package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String context_path = ((HttpServletRequest)request).getContextPath();
        String servlet_path = ((HttpServletRequest)request).getServletPath();

        if(!servlet_path.matches("/css.*")) {       // CSSフォルダ内は認証処理から除外する
            HttpSession session = ((HttpServletRequest)request).getSession();

            // セッションスコープに保存されたユーザー（ログインユーザー）情報を取得
            User e = (User)session.getAttribute("login_user");
            // ログインしていない状態で"/login"と"/user/new"と"/index.html"と"/users/create"以外にアクセスした場合、トップページへ戻す
            if (e == null && !servlet_path.equals("/login") && !servlet_path.equals("/users/new") && !servlet_path.equals("/index.html") && !servlet_path.equals("/users/create")) {
                ((HttpServletResponse) response).sendRedirect(context_path + "/index.html");
                return;
            }
            // ログインしてる状態で"/login"にアクセスした場合、トップページへ戻す
            if (e != null && servlet_path.equals("/login")) {
                ((HttpServletResponse) response).sendRedirect(context_path + "/index.html");
                return;
            }
        }
        chain.doFilter(request, response);

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

}