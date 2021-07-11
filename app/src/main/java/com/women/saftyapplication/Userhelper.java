package com.women.saftyapplication;

public class Userhelper {

    String username,phone,email,password,confirmpassword;
    public Userhelper(){}
    public Userhelper(String username, String phone, String email, String password, String confirmpassword) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}
