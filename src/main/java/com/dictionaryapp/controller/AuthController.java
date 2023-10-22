package com.dictionaryapp.controller;

import com.dictionaryapp.exception.LoginException;
import com.dictionaryapp.exception.UserNotFoundException;
import com.dictionaryapp.model.dto.user.UserLoginBindingModel;
import com.dictionaryapp.model.dto.user.UserRegisterBindingModel;
import com.dictionaryapp.pseudo.security.AppUser;
import com.dictionaryapp.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.dictionaryapp.constant.Paths.BINDING_RESULT_PATH__DOT;

@Controller
@RequestMapping("/auth")
public class AuthController {


    private final AppUser appUser;
    private final AuthService authService;

    public AuthController(AppUser appUser, AuthService authService) {

        this.appUser = appUser;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login(Model model) {

        if (appUser.isLogged()) {
            return "redirect:/home";
        }

        final String attributeName = "userLoginBindingModel";
        if (!model.containsAttribute(attributeName)) {
            model.addAttribute(attributeName, new UserLoginBindingModel());
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginBindingModel userLoginBindingModel,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (appUser.isLogged()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            final String attributeName = "userLoginBindingModel";
            redirectAttributes.addFlashAttribute(attributeName, userLoginBindingModel);
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PATH__DOT + attributeName, bindingResult);

            return "redirect:/auth/login";
        }

        authService.verifyCredentials(userLoginBindingModel);
        appUser.login(userLoginBindingModel.getUsername());
        return "redirect:/home";
    }

    @ExceptionHandler({UserNotFoundException.class, LoginException.class})
    public String handleLoginCredentialsError(RuntimeException e,
                                              RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("badCredentials", true);
        System.out.println(e.getMessage());
        return "redirect:/auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (appUser.isLogged()) {
            return "redirect:/home";
        }

        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterBindingModel userRegisterBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (appUser.isLogged()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            final String attributeName = "userRegisterBindingModel";
            redirectAttributes.addFlashAttribute(attributeName, userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PATH__DOT + attributeName, bindingResult);

            return "redirect:/auth/register";
        }

        this.authService.register(userRegisterBindingModel);

        return "redirect:/auth/login";
    }

    @PostMapping("/logout")
    public String logout() {

        appUser.logout();
        return "redirect:/auth/login";
    }

}
