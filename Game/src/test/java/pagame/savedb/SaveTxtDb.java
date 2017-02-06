package pagame.savedb;

public class SaveTxtDb {
	public static boolean createflag = false;//默認記錄為撞見文件狀態
	public static String path = "D:/webmagic/game/";

	public static void savetxtdb(String context,String filename)
	{
	    if(!createflag){
	    	createflag = true;
	    	FileOperation.createDir(path);
	    }
	    FileOperation.contentToTxt(path+filename,context);
	}
}
