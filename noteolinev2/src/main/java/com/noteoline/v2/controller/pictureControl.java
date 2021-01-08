package com.noteoline.v2.controller;

import com.noteoline.v2.entity.Infotable;
import com.noteoline.v2.entity.Picture;
import com.noteoline.v2.repositroy.PictureTepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.rowset.serial.SerialBlob;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.util.List;

@Controller
public class pictureControl {

    @Autowired
    PictureTepository pictureTepository;

    @GetMapping("/p/a")
    @ResponseBody
    public List<Picture> getPictall() {
        List<Picture> pictures = pictureTepository.findAll();
        return pictures;
    }//selectById

    @GetMapping("/p/sa")   //insert
    @ResponseBody
    public String insertPictRequest(
            @RequestParam("position") int position ,
            @RequestParam("picturedata") String stringdata
    )
    {
        Picture picture = new Picture();
        picture.setPosition(position);
        try {
            Blob blobdata= new SerialBlob(stringdata.getBytes(StandardCharsets.UTF_8));
            picture.setPicturedata(blobdata);
            picture = pictureTepository.save(picture);
            return "插入成功!";
        }
        catch (Exception exception){
            System.out.println("BLOB数据解析失败！");
        }
        return "BLOB数据解析成功！";

    }

    @GetMapping("/p/del/{id}")   //deleteById
    @ResponseBody
    public String deletetPictById(@PathVariable("id") Integer id) {
        Picture picture = pictureTepository.findById(id).get();
        if( picture!= null ) {
            pictureTepository.deleteById(id);
            return "删除成功！";
        }else {
            return "删除失败！";
        }
    }
}
