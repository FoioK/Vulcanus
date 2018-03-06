package com.wojo.Vulcanus.Controller;

import com.wojo.Vulcanus.Service.ImgToBase64Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/base64")
@RestController
public class Base64Controller {

    private final ImgToBase64Service imgToBase64Service;

    @Autowired
    public Base64Controller(ImgToBase64Service imgToBase64Service) {
        this.imgToBase64Service = imgToBase64Service;
    }

    @GetMapping("/get/")
    public String getBase64(@RequestParam String url) {
        return imgToBase64Service.getBase64FromImage(url);
    }

    @PostMapping("/create")
    public String createBase64(@RequestBody String url) {
        return imgToBase64Service.getBase64FromImage(url);
    }
}
