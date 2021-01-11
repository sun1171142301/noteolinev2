package com.noteoline.v2.controller;

import com.noteoline.v2.entity.Picture;
import com.noteoline.v2.entity.PictureIndex;
import com.noteoline.v2.repositroy.PictureIndexRepository;
import com.noteoline.v2.repositroy.PictureTepository;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Controller

public class pictureIndexControl {

    @Autowired
    PictureIndexRepository pictureIndexRepository;

    @GetMapping("/pi/a")
    @ResponseBody
    public List<PictureIndex> getPicIndall() {
        List<PictureIndex> pictureIndices=pictureIndexRepository.findAll();



        return pictureIndices;
    }//selectById



    @GetMapping("/pi/sa")   //insert
    @ResponseBody
    public String insertPicIndRequest(
            @RequestParam("position") int position ,
            @RequestParam("file") MultipartFile file
            )
    {
        //判断blob不为空

        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String fileName = file.getOriginalFilename();



        PictureIndex picture = new PictureIndex();
        picture.setPosition(position);
        /*picture.setPictureindex(pictureIndex);*/
        picture=pictureIndexRepository.save(picture);
        return "数据存储成功！";

    }

    @GetMapping("/pi/del/{id}")   //deleteById
    @ResponseBody
    public String deletetPicIndById(@PathVariable("id") Integer id) {
        PictureIndex picture = pictureIndexRepository.findById(id).get();
        if( picture!= null ) {
            pictureIndexRepository.deleteById(id);
            return "删除成功！";
        }else {
            return "删除失败！";
        }
    }


    @ResponseBody

    @RequestMapping("/KindEditorUpmethod")
    public JSONObject uploadMethod(@RequestParam String callBackPath, @RequestParam(value = "imgFile", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException {

        /*String scheme = request.getScheme();//http
        String serverName = request.getServerName();//localhost
        //String url = scheme + "://" + serverName + ":" + serverPort + contextPath;//http://127.0.0.1:8080/test
        String url = scheme + "://" + serverName + ":8080";//http://127.0.0.1:8080*/

        String url = "http://127.0.0.1:8080";

        String referer = request.getHeader("referer");

        Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+/[^/?#]*/)?)", Pattern.CASE_INSENSITIVE);

        Matcher mathcer = p.matcher(referer);

        /*配置JSON返回值*/
        JSONObject msgMap = new JSONObject();
        /*----------*/


        if (mathcer.find()) {

            String htmlheader = mathcer.group();// 请求来源

            response.setContentType("text/html;charset=UTF-8");

            String savePath = " D:/java project/noteolinev2/src/main/resources/static/";
            String saveUrl = request.getContextPath();

            /*定义允许上传的文件扩展名*/
            HashMap<String, String> extMap = new HashMap<String, String>();
            extMap.put("image", "gif,jpg,jpeg,png,bmp");
            extMap.put("flash", "swf,flv");
            extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
            extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
            /*---------------*/

            /*最大文件大小*/
            long maxSize = 100000000;
            /*------------*/

            /*response.setContentType("text/html; charset=UTF-8");*/

            if (!ServletFileUpload.isMultipartContent(request)) {
                System.out.println("请选择文件。");
                //response.sendRedirect(getError(htmlheader, "请选择文件.", callBackPath));
                return null;
            }

            File uploadDir = new File(savePath);

            /*检查上传目录是否存在*/
            if (!uploadDir.isDirectory()) {
                System.out.println("上传目录不存在。");
                //response.sendRedirect(getError(htmlheader, "上传目录不存在。", callBackPath));
                return null;
            }
            /*------------------------*/

            /*//检查目录写权限*/
            if (!uploadDir.canWrite()) {
                System.out.println("上传目录没有写权限。");
                //response.sendRedirect(getError(htmlheader, "上传目录没有写权限。", callBackPath));
                return null;
            }
            /*----------------*/

            String dirName = request.getParameter("dir");

            /*如果目录名为空，则命名为image*/
            if (dirName == null) {
                dirName = "image";
            }
            /*-----------------------*/

            /*检查文件后缀名*/
            if (!extMap.containsKey(dirName)) {
                System.out.println("目录名不正确。");
                //response.sendRedirect(getError(htmlheader, "目录名不正确。", callBackPath));
                return null;
            }
            /*----------------------*/

            //创建文件夹
            savePath += dirName + "/";
            saveUrl += dirName + "/";

            File saveDirFile = new File(savePath);


            /*如果保存目录不存在，则创建文件夹*/
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
            /*-------------*/

            /*得到特定格式的当前时间*/
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String ymd = sdf.format(new Date());
            /*----------*/

            savePath += ymd + "/";
            saveUrl += ymd + "/";

            File dirFile = new File(savePath);


            /*如果保存目录文件不存在，则创建文件*/
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            /*-----------------------*/


            String fileName = file.getOriginalFilename();
            long fileSize = file.getSize();
//            if (!item.isFormField()) {


            /*检查文件大小*/
            if (file.getSize() > maxSize) {
                System.out.println("上传文件大小超过限制。");
                //response.sendRedirect(getError(htmlheader, "上传文件大小超过限制。", callBackPath));
                return null;
            }
            /*------------*/


            //检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

            if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                System.out.println("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
                //response.sendRedirect(getError(htmlheader, "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。", callBackPath));
                return null;
            }

            /*图片文件名=时间戳+短杠+随机数+点+拓展名*/
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            /*----------*/

            try {
                File uploadedFile = new File(savePath, newFileName);
                OutputStream os = new FileOutputStream(uploadedFile);
                InputStream inputStream = file.getInputStream();
                byte[] buf = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(buf)) != -1) {
                    os.write(buf, 0, length);
                }
                inputStream.close();
                os.close();
            } catch (Exception e) {
                System.out.println("上传文件失败。");
                //response.sendRedirect(getError(htmlheader, "上传文件失败。", callBackPath));
                return null;
            }

            //Map<String, Object> msgMap = new HashMap<String, Object>();
            //遍历选择的图片
            msgMap.put("error", 0);
            // msgMap.put("url", "");
            msgMap.put("message", "");
            String urlString = "";
            //根据自己实际情况做修改
            //urlString = "http://localhost:63342/Beginner_admin/" + callBackPath + "?error=0&url=" + "http://192.168.2.158:8080/file/" + saveUrl + newFileName;
            //urlString = htmlheader + callBackPath + "?error=0&url=" + url+"/file/" + saveUrl + newFileName;
            urlString = callBackPath + "?error=0&url=" + url + "/" + saveUrl + newFileName;
            System.out.println(urlString);
            // response.sendRedirect(urlString);
            msgMap.put("url", url + "/" + saveUrl + newFileName);


            if (request.getParameter("single") != null && request.getParameter("single") != "" && "y".equals(request.getParameter("single"))) {
                //request.getParameter("single")   single ：y 是在KindEditor 源码中个给单个图片上传添加的标志， 批量上传没有添加。
                //单个图片上传，需要跳转网址，用来支持跨域
                response.sendRedirect(urlString);
            } else {
                //多个图片上传，没有跳转网址，直接返回JSONObject数据，就可完成上传
                return msgMap;
            }
        }

        return null;
    }




}
