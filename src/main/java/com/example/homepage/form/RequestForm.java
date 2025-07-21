package com.example.homepage.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RequestForm {

    @NotBlank(message = "お名前を入力してください!")
    private String  name;

    @NotBlank(message = "メールアドレスを入力してください!")
    @Email(message = "メールアドレスの形式が正しくありません!")
    private  String  email;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
