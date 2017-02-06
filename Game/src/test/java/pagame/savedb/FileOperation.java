package pagame.savedb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;  

public class FileOperation {
	  
	   
	 /** 
	  * 创建文件 
	  * @param fileName 
	  * @return 
	  */  
	 public static boolean createFile(File fileName)throws Exception{  
	  boolean flag=false;  
	  try{  
	   if(!fileName.exists()){  
	    fileName.createNewFile();  
	    flag=true;  
	   }  
	  }catch(Exception e){  
	   e.printStackTrace();  
	  }  
	  return true;  
	 }   
	   
	 /** 
	  * 读TXT文件内容 
	  * @param fileName 
	  * @return 
	  */  
	 public static String readTxtFile(File fileName)throws Exception{  
	  String result=null;  
	  FileReader fileReader=null;  
	  BufferedReader bufferedReader=null;  
	  try{  
	   fileReader=new FileReader(fileName);  
	   bufferedReader=new BufferedReader(fileReader);  
	   try{  
	    String read=null;  
	    while((read=bufferedReader.readLine())!=null){  
	     result=result+read+"\r\n";  
	    }  
	   }catch(Exception e){  
	    e.printStackTrace();  
	   }  
	  }catch(Exception e){  
	   e.printStackTrace();  
	  }finally{  
	   if(bufferedReader!=null){  
	    bufferedReader.close();  
	   }  
	   if(fileReader!=null){  
	    fileReader.close();  
	   }  
	  }  
	  System.out.println("读取出来的文件内容是："+"\r\n"+result);  
	  return result;  
	 }  
	   
	   
	 public static boolean writeTxtFile(String content,File  fileName)throws Exception{  
	  RandomAccessFile mm=null;  
	  boolean flag=false;  
	  FileOutputStream o=null;  
	  try {  
	   o = new FileOutputStream(fileName);  
	      o.write(content.getBytes("UTF-8"));  
	      o.close();  
	//   mm=new RandomAccessFile(fileName,"rw");  
	//   mm.writeBytes(content);  
	   flag=true;  
	  } catch (Exception e) {  
	   // TODO: handle exception  
	   e.printStackTrace();  
	  }finally{  
	   if(mm!=null){  
	    mm.close();  
	   }  
	  }  
	  return flag;  
	 }  
	  
	  
	  
	public static void contentToTxt(String filePath, String content) {  
	        String str = new String(); //原有txt内容  
	        String s1 = new String();//内容更新  
	        try {  
	            File f = new File(filePath);  
	            if (f.exists()) {  
	                System.out.print("文件存在");  
	            } else {  
	                System.out.print("文件不存在");  
	                f.createNewFile();// 不存在则创建  
	            }  
	            BufferedReader input = new BufferedReader(new FileReader(f));  
	  
	            while ((str = input.readLine()) != null) {  
	                s1 += str + "\r\n";  
	            }  
	            System.out.println(s1);  
	            input.close();  
	            s1 += content;  
	  
	            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
	            output.write(s1);  
	            output.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	  
	        }  
	    }
	
	// 创建目录
		public static boolean createDir(String destDirName) {
			File dir = new File(destDirName);
			if (dir.exists()) {// 判断目录是否存在
				System.out.println("创建目录失败，目标目录已存在！");
				return false;
			}
			if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
				destDirName = destDirName + File.separator;
			}
			if (dir.mkdirs()) {// 创建目标目录
				System.out.println("创建目录成功！" + destDirName);
				return true;
			} else {
				System.out.println("创建目录失败！");
				return false;
			}
		}
	  
}
