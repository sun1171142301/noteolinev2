package com.noteoline.v2.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class zhengZe {
    public String zhengCeTuPian(String string){

        List<Integer> start=new ArrayList();//字符串分割点
        String strTemp= new String();
        start.add(0);

        Matcher m= Pattern.compile("\\<img([^\\<\\>]*)\\/>").matcher(string);

        while (m.find()) {
            start.add(m.start());
            start.add(m.end());
        }//找到所有字符串分割点

        for (int i = 0; i < (start.size())-1; i=i+2) {
            strTemp+=string.substring(start.get(i),start.get(i+1));
        }//按规律连接字符串

        return strTemp;
    }
}
