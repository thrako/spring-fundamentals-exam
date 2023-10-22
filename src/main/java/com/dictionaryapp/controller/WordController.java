package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.word.WordAddBindingModel;
import com.dictionaryapp.model.enums.LanguageEnum;
import com.dictionaryapp.pseudo.security.AppUser;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.dictionaryapp.constant.Paths.BINDING_RESULT_PATH__DOT;

@Controller
@RequestMapping("/words")
public class WordController {

    private final WordService wordService;
    private final AppUser appUser;

    public WordController(WordService wordService, AppUser appUser) {

        this.wordService = wordService;
        this.appUser = appUser;
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (!appUser.isLogged()) {
            return "redirect:/auth/login";
        }

        final String attributeName = "wordAddBindingModel";
        if (!model.containsAttribute(attributeName)) {
            model.addAttribute(attributeName, new WordAddBindingModel());
        }

        model.addAttribute("languages", LanguageEnum.values());

        return "word-add";
    }

    @PostMapping("/add")
    public String add(@Valid WordAddBindingModel wordAddBindingModel,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {

        if (!appUser.isLogged()) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            final String attributeName = "wordAddBindingModel";
            redirectAttributes.addFlashAttribute(attributeName, wordAddBindingModel);
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PATH__DOT + attributeName, bindingResult);

            return "redirect:/words/add";
        }

        this.wordService.addWord(wordAddBindingModel, appUser.getUsername());

        return "redirect:/home";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long wordId) {

        if (!appUser.isLogged()) {
            return "redirect:/auth/login";
        }

        this.wordService.removeById(wordId);

        return "redirect:/home";
    }

    @DeleteMapping
    public String removeAll() {

        if (!appUser.isLogged()) {
            return "redirect:/auth/login";
        }

        this.wordService.removeAll();

        return "redirect:/home";
    }
}
