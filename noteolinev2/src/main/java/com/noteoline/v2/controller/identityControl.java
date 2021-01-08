package com.noteoline.v2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class identityControl {

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }//避免重复提交表单
}
