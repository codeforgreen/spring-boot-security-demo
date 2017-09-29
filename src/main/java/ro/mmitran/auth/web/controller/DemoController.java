package ro.mmitran.auth.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by student on 29.09.2017.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
    @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        return "home";
    }
}

