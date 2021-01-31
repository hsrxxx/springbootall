package com.huang.springbootall.test;

import com.huang.springbootall.tinify.TinifyTest;

import java.io.File;

/**
 * @program: springbootall
 * @description: 修改文件名称
 * @author: hsrxxx
 * @create: 2021-01-09 16:49
 **/
public class UpdateFileName {
    public static void main(String[] args) {
        File[] imgs = TinifyTest.getFileList("D:\\huang\\test");

        for (int i = 0; i < imgs.length; i++) {
            File file = new File(imgs[i].getPath());
            String name = i + 20 + "";
            if (name.length() == 1) {
                name = "00" + name;
            }else{
                name = "0" + name;
            }
            String newPath = imgs[i].getPath().replace(file.getName().substring(0,file.getName().lastIndexOf(".")), name);
            System.out.println(newPath);
            file.renameTo(new File(newPath)); //改名
        }
    }
}
