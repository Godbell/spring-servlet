package guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import guestbook.repository.GuestbookRepository;
import guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
    @Autowired
    private GuestbookRepository guestbookRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("list", guestbookRepository.findAll());
        return "index";
    }

    @RequestMapping("/add")
    public String add(GuestbookVo vo) {
        guestbookRepository.add(vo);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "delete";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(
        @PathVariable("id") Long id,
        @RequestParam("password") String password
    ) {
        if (password == null || password.isEmpty()) {
            System.out.println("no password given");
            return "redirect:/";
        }

        guestbookRepository.deleteByIdAndPassword(id, password);
        return "redirect:/";
    }
}
