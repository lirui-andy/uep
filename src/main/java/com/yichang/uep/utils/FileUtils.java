package com.yichang.uep.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.util.StreamUtils;

public class FileUtils {
	
	public synchronized static String genId(){
        return UUID.randomUUID().toString().replace("-", "");  
	}
	
	public static void saveFile(InputStream in, String destName) throws Exception{
		File dir = new File("c:/uep/upload");
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
}
