package hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/guestbook/*")
@Controller
public class GuestbookController {
    @ResponseBody
    @RequestMapping
    public String list() {
        return "GuestbookController:list()";
    }
}