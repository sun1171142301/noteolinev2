package com.noteoline.v2.controller;

import com.noteoline.v2.entity.Picture;
import com.noteoline.v2.repositroy.PictureTepository;
import com.sun.scenario.effect.ImageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;

@Controller
public class controller {
@Autowired
    PictureTepository pictureTepository;

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        /*File file=new File("C:\\Users\\11711\\Desktop\\pictureTest\\example.png");
        try {
            FileInputStream fileInputStream=new FileInputStream(file);
            BufferedInputStream in=new BufferedInputStream(fileInputStream).;
            Blob blob=new SerialBlob(in.);
        }
        catch (Exception exception)
        {
            System.out.println("未在指定目录找到文件");
        }*/


        /*Image image=null;
        try {
            File file=new File("C:\\Users\\11711\\Desktop\\pictureTest\\example.png");
            *//*image= ImageIO.read(file);*//*
            Blob blob=new SerialBlob(ImageData);
            Picture picture=new Picture();
            picture.setPosition(2);
            picture.setPicturedata(blob);
            picture=pictureTepository.save(picture);
        }
        catch (Exception exception){}*/

        return "test";
    }

    @GetMapping("/login")
    public String getloginpage(){
        return "/login";
    }

    @PostMapping("/login")
    public String postloginPage(

    ){

        return "redirect:/index.html";//重定向至main页面
    }

    @GetMapping("/index")
    public String mainPage(){
        return "index";}

        @GetMapping("/login?error")
    public ModelAndView loginError(HttpServletRequest request, Model model){
        model.addAttribute("longinError",true);
        model.addAttribute("errorMsg","登陆失败，账号密码错误");
        return new ModelAndView("login","userModel",model);
        }

   /* @GetMapping("/logout")
    public String logout(){

        return "login";
    }*/
}
