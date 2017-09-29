package ro.mmitran.auth.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by student on 29.09.2017.
 */
@Controller
public class Mappings {
    @RequestMapping("/")
    public String hello() {
        return "/home";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

}
