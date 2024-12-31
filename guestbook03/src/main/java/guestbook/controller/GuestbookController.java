package guestbook.controller;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import guestbook.repository.GuestbookRepository;
import guestbook.vo.GuestbookVo;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GuestbookController {
    @Autowired
    private GuestbookRepository guestbookRepository;

    @RequestMapping("/")
    public String index(HttpServletRequest req, Model model) {
        ServletContext servletContext = req.getServletContext();
        Enumeration<String> enumeration = servletContext.getAttributeNames();

        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            System.out.println(name);
        }

        ApplicationContext applicationContext1
            = (ApplicationContext)servletContext.getAttribute(
            "org.springframework.web.context.WebApplicationContext.ROOT");
        ApplicationContext applicationContext2
            = (ApplicationContext)servletContext.getAttribute(
            "org.springframework.web.servlet.FrameworkServlet.CONTEXT.spring");

        GuestbookRepository repository = applicationContext1.getBean(GuestbookRepository.class);
        System.out.println(repository);

        GuestbookController controller = applicationContext2.getBean(GuestbookController.class);
        System.out.println(controller);

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
