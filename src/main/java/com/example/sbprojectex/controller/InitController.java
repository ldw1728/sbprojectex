package com.example.sbprojectex.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@Slf4j
@Controller
public class InitController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index.html";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String requestData(HttpServletRequest request, Model model) throws IOException{
        model.addAttribute("data", "wooklee");
        return "data.html";
    }

}
