package unistd.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.RequiredArgsConstructor;
import unistd.domain.service.model.User;
import unistd.domain.service.user.UserService;

/**
 * ユーザー関連機能のControllerです。
 */
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

    @GetMapping("/users/new-user-form")
    public String newUserForm(Model model) {

        // 入力フォームのために空のオブジェクトを渡してあげる
        model.addAttribute("newUser", new NewUser());
        return "newUser";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("newUser") NewUser newUser) {

        service.addUser(newUser.getName(), newUser.getMailAddress());

        return "redirect:/";
    }

    @GetMapping("/users/{userId}/edit-user-form")
    public String editUserForm(@PathVariable("userId") int userId, Model model) {

        User user = service.getUser(userId);

        model.addAttribute("user", user);
        return "editUser";
    }

    @PutMapping("/users/{userId}")
    public String updateUser(@PathVariable("userId") int userId, @ModelAttribute("user") User user) {

        user.setUserId(userId);
        service.updateUser(user);

        return "redirect:/";
    }

    @GetMapping("/users/{userId}/delete-user-form")
    public String deleteUserForm(@PathVariable("userId") int userId, Model model) {

        User user = service.getUser(userId);

        model.addAttribute("user", user);
        return "deleteUser";
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {

        service.deleteUser(userId);

        return "redirect:/";
    }
}
