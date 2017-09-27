package cn.com.ecict.controller;

import cn.com.ecict.bean.User;
import cn.com.ecict.dao.IUserDao;
import cn.com.ecict.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by root on 17-9-26.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/checkLogin.do")
    public String checkLogin(HttpSession httpSession, String username, String password) {
        System.out.println("checkLogin.do");

        User user=userService.login(username,password);
        if(user!=null){//登录成功
            httpSession.setAttribute("username",user.getUsername());
            System.out.println("登录成功！");
            /**
             * sendRedirect对浏览器做出的响应是重新发出对另外一个URL的访问请求，
             * forward在服务器端内部将请求转发给另外一个资源，浏览器只知道发出了请求并得到了响应结果
             *
             * forward的调用者与被调用者之间共享相同的request对象和response对象，他们属于同一个访问请求和响应过程；
             * sendRedirect的调用者与被调用者使用各自的request和response对象，属于两个独立的访问请求和响应过程
             */
            return "redirect:/index";
        }else{
            //登录失败
            System.out.println("登录失败！");
            httpSession.setAttribute("tip","登录失败！");
            return "forward:/index.jsp";
        }
    }
}
