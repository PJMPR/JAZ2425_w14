package com.users.webapi.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("chat")
    public String chat(Model model){
        return "chat";
    }
    @GetMapping("home")
    public String home(Model model){
        return "home";
    }
}
