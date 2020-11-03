package by.sakhonchik.hestask.controllers;

import by.sakhonchik.hestask.dto.UserAccountDto;
import by.sakhonchik.hestask.entities.UserAccount;
import by.sakhonchik.hestask.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private UserAccountService userAccountService;

    @Autowired
    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('users:read')")
    public String userPage(Model model) {
        List<UserAccount> allUserAccounts = userAccountService.getAllUserAccounts();
        model.addAttribute("allUserAccounts", allUserAccounts);
        return "user";
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public String viewPage(Model model, @PathVariable("id") Long id) {
        UserAccountDto selectedAccount = userAccountService.getUserAccountById(id);
        model.addAttribute("selectedAccount", selectedAccount);
        return "view";
    }

    @GetMapping("/user/new")
    @PreAuthorize("hasAuthority('users:write')")
    public String addUserForm(Model model) {
        UserAccountDto userAccountDto = new UserAccountDto();
        model.addAttribute("user", userAccountDto);
        return "new";
    }

    @PostMapping("/user/new/add")
    @PreAuthorize("hasAuthority('users:write')")
    public String addUser(@Valid @ModelAttribute("user") UserAccountDto userAccountDto,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "new";
        } else {
            userAccountService.addUserAccount(userAccountDto);
            return "redirect:/user";
        }
    }

    @GetMapping("/user/{id}/edit")
    @PreAuthorize("hasAuthority('users:write')")
    public String showUpdateForm(Model model, @PathVariable("id") Long id) {
        UserAccountDto userAccountDto = userAccountService.getUserAccountById(id);
        model.addAttribute("userAccount", userAccountDto);
        return "edit";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String updateUserAccount(UserAccountDto userAccountDto) {
        userAccountService.updateUserAccount(userAccountDto);
        return "redirect:/user";
    }


    @GetMapping("/user/delete/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public String deleteUserAccountById(@PathVariable("id") Long id) {
        userAccountService.deleteUserAccountById(id);
        return "redirect:/user";
    }


}
