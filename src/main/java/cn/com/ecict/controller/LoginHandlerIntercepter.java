package cn.com.ecict.controller;

import cn.com.ecict.util.Authority;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by root on 17-9-26.
 * 登录拦截器：登录，则不拦截，没登录，则转到登录界面
 */
public class LoginHandlerIntercepter implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {

    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse  response, Object handler) throws Exception {
        System.out.println("拦截："+handler.getClass().getSimpleName());
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            Authority auth = ((HandlerMethod) handler).getMethodAnnotation(Authority.class);
            //该类未加Authority注解，或者该类加了Authority注解但是validate为false
            if(auth == null || auth.validate() == false) {
                System.out.println("无需拦截");
                return true;
            }else{//拦截代码，通过验证则返回true，否则返回false
                //获取Session
                HttpSession session = request.getSession();
                String username = (String)session.getAttribute("username");
                if(username != null){
                    System.out.println("通过拦截");
                    return true;
                }else{
                    System.out.println("拦截不通过");
                    //不符合条件的，跳转到登录界面
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    return false;
                }
            }
        }
        else {
            return true;
        }
    }
}
