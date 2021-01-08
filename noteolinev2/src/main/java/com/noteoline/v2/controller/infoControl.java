package com.noteoline.v2.controller;

import com.noteoline.v2.entity.Infotable;
import com.noteoline.v2.repositroy.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class infoControl {
    @Autowired
    InfoRepository infoRepository;

    @GetMapping("/in/a")   //查询信息
    @ResponseBody
    public List<Infotable> getinfoallv1() {
        List<Infotable> infoList = infoRepository.findAll();//做分页
        return infoList;
    }

    @GetMapping("/in/web/a")   //查询信息
    @ResponseBody
    public List<Infotable> getInfoByAllweb()
    {
        List<Infotable> userList = infoRepository.findAllByType(1);
        return userList;
    }

    @GetMapping("/in/wechat/a")   //查询信息
    @ResponseBody
    public List<Infotable> getInfoByAllwechat()
    {
        List<Infotable> userList = infoRepository.findAllByType(2);
        return userList;
    }

    @GetMapping("/in/sbytitleweb/{title}")   //查询信息
    @ResponseBody
    public List<Infotable> getInfoByTitleweb(
            @PathVariable("title") String title)
    {
        List<Infotable> userList = infoRepository.findAllByTitleType(title,1);
        return userList;
    }

    @GetMapping("/in/sbytitlewechat/{title}")   //查询信息
    @ResponseBody
    public List<Infotable> getInfoByTitlewechat(
            @PathVariable("title") String title)
    {
        List<Infotable> userList = infoRepository.findAllByInfoType(title,2);
        return userList;
    }

    @GetMapping("/in/sbyinfo/{info}")   //查询信息
    @ResponseBody
    public List<Infotable> getInfoallbyInfo(
            @PathVariable("info") String info)
    {
        List<Infotable> userList = infoRepository.findAllByInfo(info);
        return userList;
    }

    @GetMapping("/in/sbyinid/{id}")   //查询信息
    @ResponseBody
    public Infotable getInfobyId(
            @PathVariable("id") int id)
    {
         Infotable infotable=new Infotable();
        infotable = infoRepository.findById(id).get();
        return infotable;
    }

    @GetMapping("/in/sa")   //insert
    @ResponseBody
    public String insertInfoRequest(
            @RequestParam("title") String title ,
            @RequestParam("info") String info ,
            @RequestParam("cid") int cid ,
            @RequestParam("name") String name
    )
    {
        Infotable infotable = new Infotable();
        infotable.setTitle(title);
        infotable.setInfo(info);
        infotable.setCid(cid);
        infotable.setName(name);
        Timestamp time=new Timestamp(System.currentTimeMillis());//时间无时区调整
        infotable.setUpdatetime(time);

        infotable = infoRepository.save(infotable);
        return "插入成功!";
    }

    @GetMapping("/in/up")   //update更改数据ID存在自增问题
    @ResponseBody
    public String updateInfoV3(
            @RequestParam("infoid") int infoid ,
            @RequestParam("title") String title ,
            @RequestParam("info") String info ,
            @RequestParam("cid") int cid ,
            @RequestParam("name") String  name
    )
    {
        Infotable infotable = infoRepository.findById(infoid).get();
        infotable.setTitle(title);
        infotable.setInfo(info);
        infotable.setCid(cid);
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        infotable.setUpdatetime(timestamp);
        infotable.setName(name);
        infotable = infoRepository.save(infotable);
        return "更改成功!";
    }


    @GetMapping("/in/del/{infoid}")   //deleteById
    @ResponseBody
    public String deletetInfo(@PathVariable("infoid") Integer infoid) {
        Infotable infotable = infoRepository.findById(infoid).get();
        if( infotable!= null ) {
            infoRepository.deleteById(infoid);
            return "删除成功！";
        }else {
            return "删除失败！";
        }
    }
}
