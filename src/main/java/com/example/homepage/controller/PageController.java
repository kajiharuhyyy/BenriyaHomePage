package com.example.homepage.controller;


import com.example.homepage.entity.Request;
import com.example.homepage.form.ContactForm;
import com.example.homepage.form.RequestForm;
import com.example.homepage.repository.RequestRepository;
import jakarta.validation.Valid;
import com.example.homepage.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class PageController {

    private final MailService mailService;

    @Autowired
    private RequestRepository requestRepository;

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

    @GetMapping("/price")
    public String price() {
        return "price";
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
                + "郵便番号: " + contactForm.getPostcode() + "\n"
                + "メッセージ:\n" + contactForm.getMessage();

        mailService.sendContactMail("info@tenohira-benri.com", subject, body);

        model.addAttribute("name",contactForm.getName());
        model.addAttribute("email",contactForm.getEmail());
        model.addAttribute("phone",contactForm.getPhone());
        model.addAttribute("postcode",contactForm.getPostcode());
        model.addAttribute("message",contactForm.getMessage());
        return "contact_result";
    }

    @GetMapping("/request")
    public String requestForm(Model model) {
        model.addAttribute("requestForm", new RequestForm());
        return "request";
    }

    @PostMapping("/request")
    public String submitRequest(
            @ModelAttribute("requestForm") @Valid RequestForm form,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "request";
        }

        Request req = new Request();
        req.setName(form.getName());
        req.setEmail(form.getEmail());
        requestRepository.save(req);

        model.addAttribute("name",form.getName());
        return "request_result";
    }

    @GetMapping("/faq")
    public String showFaqPage() {
        return "faq";
    }
}
