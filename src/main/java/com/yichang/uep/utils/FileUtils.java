package com.yichang.uep.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.FilterType;
import org.springframework.util.StreamUtils;

public class FileUtils {
	
	public final static File home = new ApplicationHome(FileUtils.class).getDir();
	
	final static String[] images = "PNG|JPG|JPEG|GIF|BMP".split("\\|");
//	static File dir = new File("c:/uep/upload");
	static File dir = new File(home, "upload");
	public synchronized static String genId(){
        return UUID.randomUUID().toString().replace("-", "");  
	}
	
	public static void saveFile(InputStream in, String destName) throws Exception{
		if(!dir.exists()){
			dir.mkdirs();
		}
		File dest = new File(dir, destName);
		if(dest.exists()) dest.delete();
		
		try( OutputStream out = new FileOutputStream(dest) ){
			StreamUtils.copy(in, out);
		}finally{
			in.close();
		}
	}

	public static InputStream readFile(String uuid) throws FileNotFoundException {
		File dest = new File(dir, uuid);
		return new FileInputStream(dest);
	}

	public static String guessFileType(String originalFilename) {
		if(originalFilename == null || originalFilename.length() == 0)
		return null;
		int pos = originalFilename.lastIndexOf(".");
		if(pos > -1 && pos+1 < originalFilename.length() ){
			return originalFilename.substring(pos+1);
		}
		return null;
	}
	
	public static boolean isImage(String fileType){
		fileType = (fileType == null ? "" : fileType.toUpperCase());
		for(String t : images){
			if(fileType.endsWith(t))
				return true;
		}
		return false;
	}
}
