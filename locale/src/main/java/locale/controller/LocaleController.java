package locale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class LocaleController {
    private final LocaleResolver localeResolver;

    public LocaleController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @RequestMapping
    public String index(HttpServletRequest request) {
        String lang = localeResolver.resolveLocale(request).getLanguage();
        System.out.println("Lang Code: " + lang);
        return "index";
    }
}
