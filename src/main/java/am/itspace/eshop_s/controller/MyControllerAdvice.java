package am.itspace.eshop_s.controller;

import am.itspace.eshop_s.entity.User;
import am.itspace.eshop_s.service.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyControllerAdvice {

    @ModelAttribute("createUser")
    public User createUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null) {
            return null;
        }
        return currentUser.getUser();
    }
}
