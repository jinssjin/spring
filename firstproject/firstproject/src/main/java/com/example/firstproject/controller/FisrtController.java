package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FisrtController {

    @GetMapping("/hi")  // 요청URL, request url
    public String niceToMeetYou(Model model){
        model.addAttribute("username","jungwon");
        // 보여줄 페이지 return
        return "greetings";  // D:\spring\firstproject\firstproject\src\main\resources\templates
    }

    @GetMapping("/bye")  // 요청URL, request url
    public String seeYouNext(Model model){
        model.addAttribute("nickname","홍길동");
        // 보여줄 페이지 return
        return "goodbye";  // D:\spring\firstproject\firstproject\src\main\resources\templates

    }
}
