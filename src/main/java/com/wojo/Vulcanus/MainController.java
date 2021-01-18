package com.wojo.Vulcanus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/base64/convert", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class MainController {

    private final Base64Service base64Service;

    @Autowired
    public MainController(Base64Service Base64Service) {
        this.base64Service = Base64Service;
    }

    @GetMapping()
    public String getBase64(@RequestParam String url) {
        return base64Service.getBase64(url);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<String> convertAllToBase64(@RequestBody List<String> urls) {
        return urls.size() > Request.REQUEST_SIZE ?
                base64Service.getBase64(urls) : base64Service.getBase64SingleRequest(urls);
    }
}
