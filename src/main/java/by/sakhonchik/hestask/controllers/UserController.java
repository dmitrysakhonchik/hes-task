package by.sakhonchik.hestask.controllers;

import by.sakhonchik.hestask.entities.UserAccount;
import by.sakhonchik.hestask.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private UserAccountService userAccountService;

    @Autowired
    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/user")
    public String userPage(Model model) {
        List<UserAccount> allUserAccounts = userAccountService.getAllUserAccounts();
        model.addAttribute("allUserAccounts", allUserAccounts);
 //       model.addAttribute("user", new UserAccount());
        return "user";
    }

    @GetMapping("/user/{id}")
    public String viewPage(Model model, @PathVariable("id") Long id) {
        UserAccount selectedAccount = userAccountService.getUserAccountById(id);
        model.addAttribute("selectedAccount", selectedAccount);
        return "view";
    }

    @GetMapping("/user/new")
    public String addUserForm(Model model) {
        model.addAttribute("user", new UserAccount());
        return "new";
    }

    @PostMapping("/user/new/add")
    public String addUser(UserAccount userAccount) {
        userAccountService.addUserAccount(userAccount);
        return "redirect:/user";
    }

    @GetMapping("/user/{id}/edit")
    public String showUpdateForm(Model model, @PathVariable("id") Long id) {
        UserAccount userAccount = userAccountService.getUserAccountById(id);
        model.addAttribute("userAccount", userAccount);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateUserAccount(UserAccount userAccount) {
        userAccountService.addUserAccount(userAccount);
        return "redirect:/user";
    }

    @GetMapping("/user/delete/{id}") //GET METHOD?
    public String deleteUserAccountById(@PathVariable("id") Long id) {
        userAccountService.deleteUserAccountById(id);
        return "redirect:/user";
    }


}
