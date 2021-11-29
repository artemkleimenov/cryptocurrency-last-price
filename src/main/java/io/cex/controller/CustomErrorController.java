package io.cex.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController{

    @GetMapping("/error")
    public String error() {
        return "Page not found. Please try again!";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
