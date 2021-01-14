package com.noteoline.v2.controller;

import com.noteoline.v2.entity.Infotable;
import com.noteoline.v2.repositroy.InfoRepository;
import com.noteoline.v2.service.zhengZe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.Context;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class infoControl {
    @Autowired
    InfoRepository infoRepository;

    /*@Autowired
    zhengZe zhengZe;*/

//    @GetMapping("/in/a")   //查询信息所有
//    @ResponseBody
//    public List<Infotable> getinfoallv1() {
//        List<Infotable> infoList = infoRepository.findAll();//做分页
//        return infoList;
//    }

    @GetMapping("/in/a")   //查询信息所有
    @PreAuthorize("hasAuthority('admins')")
    @ResponseBody
    public List<Infotable> getinfoallv1() {
        return  infoRepository.findAll();//做分页
    }

    @GetMapping("/in/aVC")   //查询信息所有
    @PreAuthorize("hasAnyAuthority('admins','common')")
    @ResponseBody
    public List<Infotable> getinfoallv1VC() {
        List<Infotable> list = infoRepository.findAll();
        for (int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getInfo().contains("<img ")){
                String imginfo= list.get(i).getInfo();
                imginfo.substring(imginfo.indexOf("<img*/>"));

            }

        }
        return  list;//做分页
    }

    /*被替换-start*/
    @GetMapping("/in/web/a")   //查询web所有信息
    @ResponseBody
    public List<Infotable> getInfoByAllweb()
    {
//        List<Infotable> userList = infoRepository.findAllByType(1);
//        return userList;
        return infoRepository.findAllByType(1);
    }

    @GetMapping("/in/wechat/a")   //查询wechat所有信息
    @ResponseBody
    public List<Infotable> getInfoByAllwechat()
    {
//        List<Infotable> userList = infoRepository.findAllByType(2);
//        return userList;
        return infoRepository.findAllByType(2);
    }
    /*被替换-end*/

    /*Cid查询-start*/
    @GetMapping("/in/{cid}/a")   //根据Cid查询所有信息
    @ResponseBody
    public List<Infotable> getInfoByAllCid(
            @PathVariable("cid") int cid
    )
    {
//        List<Infotable> userList = infoRepository.findAllByType(cid);
//        return userList;
        return infoRepository.findAllByType(cid);
    }
    /*Cid查询-end*/

    /*版本控制 Cid查询-start*/
    @GetMapping("/in/{cid}/aVC")   //根据Cid查询所有信息
    @ResponseBody
    public List<Infotable> getInfoByAllCidVersionControl(
            @PathVariable("cid") int cid
    )
    {
        List<Infotable> userList = infoRepository.findAllByTypeVersionControl(cid);

        return userList;

    }





    /*版本控制 Cid查询-endt*/

    /*版本控制 Cid查询-start 正则*/
    @GetMapping("/in/{cid}/aVC/ZZ")   //根据Cid查询所有信息
    @PreAuthorize("hasAuthority('common')")
    @ResponseBody
    public List<Infotable> getInfoByAllCidVersionControlzhengze(
            @PathVariable("cid") int cid
    )
    {

        List<Infotable> userList = infoRepository.findAllByTypeVersionControl(cid);
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).setInfo(zhengZe.zhengCeTuPian(userList.get(i).getInfo())) ;
        }

        return userList;

    }
    /*版本控制 Cid查询-endt*/





    /*被替换Cid title查询-start*/
    @GetMapping("/in/sbytitleweb/{title}")   //查询信息
    @ResponseBody
    public List<Infotable> getInfoByTitleweb(
            @PathVariable("title") String title)
    {
//        List<Infotable> userList = infoRepository.findAllByTitleType(title,1);
//        return userList;
        return infoRepository.findAllByTitleType(title,1);
    }

    @GetMapping("/in/sbytitlewechat/{title}")   //查询信息
    @ResponseBody
    public List<Infotable> getInfoByTitlewechat(
            @PathVariable("title") String title)
    {
//        List<Infotable> userList = infoRepository.findAllByInfoType(title,2);
//        return userList;
        return infoRepository.findAllByInfoType(title,2);
    }
    /*被替换Cid title查询-end*/

    /*Cid title查询-start*/
    @GetMapping("/in/sbytitle/{title}/{cid}")   //查询信息
    @ResponseBody
    public List<Infotable> getInfoByTitleAndCid(
            @PathVariable("title") String title,
            @PathVariable("cid")int cid)
    {
//        List<Infotable> userList = infoRepository.findAllByInfoType(title,cid);
//        return userList;
        return  infoRepository.findAllByInfoType(title,cid);
    }
    /*Cid title查询-end*/


    @GetMapping("/in/sbyinfo/{info}")   //查询信息
    @ResponseBody
    public List<Infotable> getInfoallbyInfo(
            @PathVariable("info") String info)
    {
//        List<Infotable> userList = infoRepository.findAllByInfo(info);
//        return userList;
        return infoRepository.findAllByInfo(info);
    }


    @GetMapping("/in/sbyinid/{id}")   //查询信息
    @ResponseBody
    public Infotable getInfobyId(
            @PathVariable("id") int id)
    {
  //       Infotable infotable=infoRepository.findById(id).get();//验证get().isPresent()
   //     return infotable;
        return infoRepository.findById(id).get();
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
        infoRepository.save(infotable);
        //infotable = infoRepository.save(infotable);
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
        //infotable = infoRepository.save(infotable);
        infoRepository.save(infotable);
        return "更改成功!";
    }


    @GetMapping("/in/del/{infoid}")   //deleteById
    @ResponseBody
    public String deletetInfo(@PathVariable("infoid") Integer infoid) {
        //Infotable infotable = infoRepository.findById(infoid).get();

        if( /*infotable!= null*/ infoRepository.findById(infoid).isPresent()) {
            infoRepository.deleteById(infoid);
            return "删除成功！";
        }else {
            return "删除失败！";
        }
    }
}
