package com.wojo.Vulcanus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/base64")
@RestController
public class MainController {

    private final Base64Service base64Service;

    @Autowired
    public MainController(Base64Service Base64Service) {
        this.base64Service = Base64Service;
    }

    @GetMapping("/get/")
    public String getBase64(@RequestParam String url) {
        return base64Service.getBase64(url);
    }
}
