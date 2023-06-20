package gov.iti.career.hub.authroizationserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/authorized")
@RestController
public class AuthorizedController {
    @GetMapping
    public String authorized(){
        return "authorized";
    }
}
