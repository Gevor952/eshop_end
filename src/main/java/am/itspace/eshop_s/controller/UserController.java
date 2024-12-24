package am.itspace.eshop_s.controller;

import am.itspace.eshop_s.entity.User;
import am.itspace.eshop_s.entity.UserType;
import am.itspace.eshop_s.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping(value = "/register")
    public String addUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserType(UserType.USER);
        userService.save(user);
        return "redirect:/";
    }
}
