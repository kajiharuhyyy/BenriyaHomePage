package com.example.homepage.controller;


import com.example.homepage.form.ContactForm;
import jakarta.validation.Valid;
import com.example.homepage.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class PageController {

    private final MailService mailService;

    public PageController(MailService mailService) {
        this.mailService = mailService;
    }

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
    public String submitContact(
            @ModelAttribute("contactForm") @Valid ContactForm contactForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "contact";
        }

        String subject = "【お問い合わせ】" + contactForm.getName() + "様より";
        String body = "名前: " + contactForm.getName() + "\n"
                + "メール: " + contactForm.getEmail() + "\n"
                + "電話番号: " + contactForm.getPhone() + "\n"
                + "メッセージ:\n" + contactForm.getMessage();

        mailService.sendContactMail("kajiharu0921@gmail.com", subject, body);

        model.addAttribute("name",contactForm.getName());
        model.addAttribute("email",contactForm.getEmail());
        model.addAttribute("phone",contactForm.getPhone());
        model.addAttribute("message",contactForm.getMessage());
        return "contact_result";
    }
}
