package validation;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class MvcController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/ex01")
    public String ex01() {
        return "form/ex01";
    }

    @GetMapping("/result")
    public String result() {
        return "result";
    }

    @PostMapping("/ex01")
    public String ex01(
        @ModelAttribute("user") @Valid User user,
        BindingResult result, Model model
    ) {
        if (result.hasErrors()) {
            //result.getAllErrors().forEach(System.out::println);
            Map<String, Object> map = result.getModel();
            model.addAllAttributes(map);

            return "form/ex01";
        }

        return "redirect:/result";
    }

    @GetMapping("/ex02")
    public String ex02() {
        return "form/ex02";
    }

    @PostMapping("/ex02")
    public String ex02(
        @ModelAttribute("user") @Valid User user,
        BindingResult result, Model model
    ) {
        if (result.hasErrors()) {
            //result.getAllErrors().forEach(System.out::println);
            Map<String, Object> map = result.getModel();
            model.addAllAttributes(map);

            return "form/ex02";
        }

        return "redirect:/result";
    }

    @GetMapping("/ex03")
    public String ex03(@ModelAttribute User user) {
        return "form/ex03";
    }

    @PostMapping("/ex03")
    public String ex03(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAllAttributes(result.getModel());
            return "form/ex03";
        }

        return "redirect:/result";
    }
}
