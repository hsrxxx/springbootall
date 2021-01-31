package com.huang.springbootall.tinify;

import com.tinify.Source;
import com.tinify.Tinify;

import java.io.File;
import java.io.IOException;

/**
 * @program: springbootall
 * @description: 压缩图片
 * @author: hsrxxx
 * @create: 2021-01-06 10:18
 **/
public class TinifyTest {
    public static void main(String[] args) {
//        Tinify.setKey("YJzp7F3BbwlZP1nYfk8qZxLYKzBmx3QH");
        Tinify.setKey("XhGQZWHx7hD9NkwyQxGZbQL1DNhdfDZb");


        File lf[] = getFileList("D:\\huang\\wallpapers\\beautiful");

        Source source = null;
        for (File f : lf) {
            try {
                // 您可以选择一个本地文件作为源并将其写入另一个文件。
                source = Tinify.fromFile(f.getPath());
                source.toFile(f.getPath());
                System.out.println(f.getPath());

                // 您还可以从缓冲区（带二进制的字符串）上载图像并获取压缩的图像数据。

//               byte[] sourceData = Files.readAllBytes(Paths.get("unoptimized.jpg"));
//               byte[] resultData = Tinify.fromBuffer(sourceData).toBuffer();
                // 您可以提供图片的URL，而不必上传。

//               Source source = Tinify.fromUrl("https://tinypng.com/images/panda-happy.png");
//               source.toFile("optimized.jpg");

            } catch (IOException e) {
                e.printStackTrace();
            }
            int compressionsThisMonth = Tinify.compressionCount();
            System.out.println(compressionsThisMonth);
        }
    }

    static public File[] getFileList(String path){
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return null;
        }

        File lf[] = f.listFiles();
        return lf;
    }
}
