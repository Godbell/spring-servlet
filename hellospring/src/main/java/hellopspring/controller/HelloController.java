package hellopspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "/WEB-INF/views/hello.jsp";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello2() {
        return "/WEB-INF/views/hello.jsp";
    }
}