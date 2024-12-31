package hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserController {
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join() {
        return "/WEB-INF/views/join.jsp";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(UserVo vo) {
        System.out.println(vo);
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@RequestParam("n") String no) {
        // http 400 when n null
        return "UserController:update(" + no + ")";
    }

    @ResponseBody
    @RequestMapping("/update2")
    public String update2(@RequestParam(value = "n", required = false) Long no) {
        // http 400 when n null
        return "UserController:update(" + no + ")";
    }

    @ResponseBody
    @RequestMapping("/list")
    public String list(@RequestParam(value = "p", required = true, defaultValue = "1") Integer pageNo) {
        return "list:pageNo(" + pageNo + ")";
    }
}
