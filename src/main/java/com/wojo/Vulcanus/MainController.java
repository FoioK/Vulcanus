package com.wojo.Vulcanus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = "/convert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<String> convertAllToBase64(@RequestBody List<String> urls) {
        return base64Service.getBase64(urls);
    }
}
