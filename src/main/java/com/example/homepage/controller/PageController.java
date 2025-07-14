package com.example.homepage.controller;


import com.example.homepage.form.ContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@ModelAttribute ContactForm contactForm, Model model) {

        model.addAttribute("name",contactForm.getName());
        model.addAttribute("email",contactForm.getEmail());
        model.addAttribute("phone",contactForm.getPhone());
        model.addAttribute("message",contactForm.getMessage());
        return "contact_result";

    }
}
