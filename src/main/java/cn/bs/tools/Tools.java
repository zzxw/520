package cn.bs.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class Tools {
	public static boolean isEmpty(String string) {
		return (string == null || "".equals(string.trim()));
	}
	public static boolean writeTxtFile(String content,File fileName)throws Exception{
        RandomAccessFile mm=null;
        boolean flag=false;
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(content.getBytes("gbk"));
            fileOutputStream.close();
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
