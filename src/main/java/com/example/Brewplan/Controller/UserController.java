package com.example.Brewplan.Controller;

import com.example.Brewplan.Model.User;
import com.example.Brewplan.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/User/login"; // Redirect to login if user is not logged in
        }
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        return "User/profile";
    }

    @GetMapping("/list")
    public String listAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "User/list"; // Ensure your profile page is set to display the user list as well
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "User/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user, Model model) {
        if (userService.userExists(user.getUsername())) {
            model.addAttribute("userExists", true);
            return "User/signup";
        }
        userService.saveUser(user);
        return "redirect:/User/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "User/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        User user = userService.authenticate(email, password);
        if (user == null) {
            model.addAttribute("loginError", true);
            return "User/login";
        }
        session.setAttribute("loggedInUser", user);
        if ("ADMIN".equals(user.getRole())) {
            return "redirect:/User/profile"; // Admins go to profile page to manage users
        }
        return "redirect:/dashboard"; // Non-admin users go to dashboard or another appropriate page
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/User/login";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "User/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        user.setId(id);
        userService.saveUser(user);
        return "redirect:/User/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/User/list";
    }

    @GetMapping("/assign-role/{id}")
    public String showAssignRoleForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "User/assign-role";
    }

    @PostMapping("/assign-role/{id}")
    public String assignRole(@PathVariable("id") Long id, @RequestParam("role") String role) {
        userService.updateUserRole(id, role);
        return "redirect:/User/list";
    }
}
