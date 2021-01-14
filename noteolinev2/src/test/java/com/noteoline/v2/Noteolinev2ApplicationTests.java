package com.noteoline.v2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.java2d.pipe.AAShapePipe;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class Noteolinev2ApplicationTests {

	@Test
	void contextLoads() {

		String str="ab1ab2ab3ab4ab5";


		String str1="<><>4t45tergt456yrthgtr<><><img src='123434545454545' /><><><><<img gfegdfg />ghgfhghgfhdhfghf<img fgfg fgfdgsdg/>";
//
//		System.out.println(str.getBytes(StandardCharsets.UTF_8));
//		System.out.println(str.getBytes());
//
//		for (int i = 0; i < str.length(); i++) {
//			System.out.println(str.substring(i));
//		}

//		System.out.println(str.toUpperCase(Locale.ROOT));

//		for (int i = 0; i <str.length(); i++) {
//			for (int j = 0; j < str.length()-i+1; j++) {
//				System.out.println("i=" + i);
//				int new_j=j+i;
//				System.out.println("NEW j=" + new_j);
//				System.out.println(str.substring(i, j+i));
//			}
//		}

//		System.out.println(str.indexOf("d"));
//		System.out.println(str.indexOf(3));//查找ascII码值
//		System.out.println(str.indexOf("a", 5));//从fromIndex开始查找
//		System.out.println(str.indexOf(2, 2));//从fromIndex开始查找ascII码值

		/*System.out.println(str.replace("ab", "cc"));

		System.out.println(str.replace('c', '/'));//替换所有字符*/
		/*String str2=str;
		while (str2.indexOf("c")!=-1)
		{
			str2=str2.replace('c','/');
		}
		System.out.println(str2);*/
		Matcher m= Pattern.compile("\\<img([^\\<\\>]*)\\/>").matcher(str1);
		List<Integer> start=new ArrayList();
		start.add(0);
		ArrayList end=new ArrayList();
		while (m.find()) {
			/*String strstart=str1.substring(0,m.start());
			System.out.println(strstart);

			String strend=str1.substring(m.end());

			System.out.println(strend);
			str1= strstart.concat(strend);
			System.out.println(str1);*/
			start.add(m.start());
			start.add(m.end());
			System.out.println("start index"+m.start());
			System.out.println("end index"+m.end());

		}

		/*System.out.println(str1 = str1.substring(0, start.get(0)));*/
		List<String> strbulid=new ArrayList<String>();
		String str2="";
		for (int i = 0; i < (start.size())-1; i=i+2) {
			strbulid.add(str1.substring(start.get(i),start.get(i+1)));
			str2+=str1.substring(start.get(i),start.get(i+1));
			System.out.println(str1.substring(start.get(i),start.get(i+1)));
		}
		System.out.println(str2);
//		System.out.println(str1.indexOf("<img*/>"));
//		System.out.println(str1.indexOf("<img />"));
//		System.out.println(str1.lastIndexOf("<img*/>"));
	}

}
