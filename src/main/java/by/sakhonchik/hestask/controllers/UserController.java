package by.sakhonchik.hestask.controllers;

import by.sakhonchik.hestask.entities.UserAccount;
import by.sakhonchik.hestask.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        return "user";
    }

    @GetMapping("/view/{id}")
    public String viewPage(Model model, @PathVariable("id") Long id) {
        UserAccount selectedAccount = userAccountService.getUserAccountById(id);
        model.addAttribute("selectedAccount", selectedAccount);
        return "view";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUserAccountById(@PathVariable("id") Long id) {
        userAccountService.deleteUserAccountById(id);
        return "redirect:/user";
    }

}
