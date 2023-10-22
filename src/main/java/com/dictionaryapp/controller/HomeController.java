package com.dictionaryapp.controller;

import com.dictionaryapp.pseudo.security.AppUser;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AppUser appUser;
    private final WordService wordService;

    public HomeController(AppUser appUser, WordService wordService) {

        this.appUser = appUser;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String index() {

        if (appUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (!appUser.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("allWords", wordService.getAllGroupedByLanguage());

        return "home";
    }
}
