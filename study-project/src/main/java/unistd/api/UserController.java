package unistd.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import unistd.domain.service.model.User;
import unistd.domain.service.user.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/")
    public String top(Model model) {

        List<User> users = service.getUsers();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/user")
    public String newUserForm(Model model) {

        // 入力フォームのために空のオブジェクトを渡してあげる
        model.addAttribute("newUser", new NewUser());
        return "newUser";
    }

    @PostMapping("/user")
    public String addUser(@ModelAttribute("newUser") NewUser newUser, Model model) {

        service.addUser(newUser.getName(), newUser.getMailAddress());

        List<User> users = service.getUsers();

        model.addAttribute("users", users);

        return "users";
    }
}
