package com.users.webapi.controllers.mvc;

import com.users.usersdata.model.Person;
import com.users.usersdata.repositories.IRepositoriesCatalog;
import com.users.webapi.services.security.RandomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final IRepositoriesCatalog db;

    /**
     * Możesz użyć adnotacji @AuthenticationPrincipal do bezpośredniego wstrzyknięcia szczegółów użytkownika.
     * Wymaga to, aby Twój UserDetailsService zwracał obiekt UserDetails
     *
     * inne sposoby: Authentication, Principal, SecurityContextHolder
     * --> mogą zawierać więcej informacji o użytkowniku, takich jak role i uprawnienia.
     */
    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal RandomUserDetails user){

        var person = db.getPeople().findByUserId(user.getId()).orElse(new Person());
        model.addAttribute("person", person);
        return "profile";
    }

//    @GetMapping("profile")
//    public String getProfile(Model model, HttpServletRequest request, Authentication auth, SecurityContextHolder holder, Principal principal){
//        var session = request.getSession();
//        String sessionId = session.getId();
//        model.addAttribute("sessionId", sessionId);
//        model.addAttribute("person", new Person());
//        return "profile";
//    }

}
