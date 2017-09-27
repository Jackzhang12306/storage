package cn.com.ecict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value = "/")
public class RootController {

    /**
     * 首页
     * http://localhost:8080/index -> web.xml -> /index（本方法） -> 返回/WEB-INF/view/index.jsp
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    /**
     * 链接必须是*.do
     * http://localhost:8080/test.do
     * @return
     */
    @RequestMapping("test.do")
    public String test() {
        System.out.println("RootController.test");
        return "test";
    }

    @RequestMapping("hello.do")
    public String testA() {
        System.out.println("RootController.hello");

        return "test";
    }

}

