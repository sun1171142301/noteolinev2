package com.noteoline.v2.controller;


import com.noteoline.v2.entity.SecurityTable;
import com.noteoline.v2.repositroy.SecurityTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class securityControl {
    @Autowired
    SecurityTableRepository securityTableRepository;

    @GetMapping("/s/a")   //查询信息
    @ResponseBody
    public List<SecurityTable> getUserallv1() {
        List<SecurityTable> userList = securityTableRepository.findAll();
        return userList;
    }

    @GetMapping("/s/sbyname/{username}")   //查询信息
    @ResponseBody
    public List<SecurityTable> getUserallbyname(
            @PathVariable("username") String username)
    {
        List<SecurityTable> userList = securityTableRepository.findAllByNameUserList(username);
        return userList;
    }

    @GetMapping("/s/sbyrole/{role}")   //查询信息
    @ResponseBody
    public List<SecurityTable> getUserallbyrole(
            @PathVariable("role") String role)
    {
        List<SecurityTable> userList = securityTableRepository.findAllByRoleList(role);
        return userList;
    }

    @GetMapping("/s/sa/{username}/{password}/{role}")   //insert
    @ResponseBody
    public String insertUser(
            @PathVariable("username") String username ,
            @PathVariable("password") String password ,
            @PathVariable("role") String role )
    {
        SecurityTable securityTable = new SecurityTable();
        securityTable.setUsername(username);
        securityTable.setPassword(password);
        securityTable.setRole(role);
        securityTable = securityTableRepository.save(securityTable);
        return "插入成功!";
    }

    @GetMapping("/s/up/{id}/{username}/{password}/{role}")   //update更改数据ID存在自增问题
    @ResponseBody
    public String updateUser(
            @PathVariable("id") int id ,
            @PathVariable("username") String username ,
            @PathVariable("password") String password ,
            @PathVariable("role") String role )
    {
        SecurityTable securityTable = new SecurityTable();
        securityTable.setId(id);
        securityTable.setUsername(username);
        securityTable.setPassword(password);
        securityTable.setRole(role);
        securityTable = securityTableRepository.save(securityTable);
        return "更改成功!";
    }

    @GetMapping("/s/del/{id}")   //deleteById
    @ResponseBody
    public String deletetUserById(@PathVariable("id") Integer id) {
        SecurityTable securityTable = securityTableRepository.findById(id).get();
        if( securityTable!= null ) {
            securityTableRepository.deleteById(id);
            return "删除成功！";
        }else {
            return "删除失败！";
        }
    }
}
