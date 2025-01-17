package com.example.SpringSecu;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public ResponseEntity<?> sayHello(HttpServletRequest request) {
        return ResponseEntity.ok("Hello World!" + request.getSession().getId());
    }
}
