package cn.com.ecict.controller;

import cn.com.ecict.bean.UserBean;
import cn.com.ecict.dao.IUserDao;
import cn.com.ecict.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 17-9-26.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource(name="userService")
    private IUserService userService;


    @RequestMapping("/checkLogin.do")
    public String checkLogin(HttpSession session, String username, String password) {
        System.out.println("checkLogin.do");

        UserBean user=userService.login(username,password);
        if(user!=null){//登录成功

            //session.getServletContext()得到时application对象
            ServletContext application=session.getServletContext();
            Map<String, String> loginMap = (Map<String, String>)application.getAttribute("loginMap");
            if(loginMap==null){
                loginMap = new HashMap<>();
            }
            for(String key:loginMap.keySet()) {
                if (user.getUsername().equals(key)) {
                    if(session.getId().equals(loginMap.get(key))) {
                        System.out.println(username+"在同一地点多次登录！");
                    }else{
                        System.out.println(username+"异地登录被拒绝！");
                        session.setAttribute("tip", "该用户已经异地登录！");
                        return "forward:/index.jsp";
                    }
                }
            }
            loginMap.put(user.getUsername(),session.getId());
            application.setAttribute("loginMap", loginMap);
            session.setAttribute("username",user.getUsername());
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
            session.setAttribute("tip","登录失败！");
            return "forward:/index.jsp";
        }
    }

    @RequestMapping("/getUserList.do")
    @ResponseBody
    public List<UserBean> getUserList(){
        List<UserBean> list=userService.getUserList();
        for(UserBean u:list){
            System.out.println(u.getUid()+","+u.getUsername()+","+u.getEmail());
        }
        return list;
    }

    @RequestMapping("/checkUsername.do")
    @ResponseBody
    public String checkUsername(String username){
        boolean flag=userService.checkUsername(username);
        if(flag){
            return "用户名已经存在！";
        }else{
            return "该用户名可以使用！";
        }
    }


}
