package de.ifdag.log;


import org.apache.log4j.Logger;


public class Log {

	
	public static Logger logger =Logger.getLogger(Log.class);
	


	public static <T> void error(Class<T> class1, String string) {
		// TODO Auto-generated method stub
		logger.error(string);
	}

	public static void exception(String msg, Exception ex) {
		// TODO Auto-generated method stub
		logger.error(msg,ex);
	}

	public static <T> void docu(Class<T> class1, String string) {
		// TODO Auto-generated method stub
		logger.info(string);
	}

	public static <T> void test(Class<T> class1, String string) {
		// TODO Auto-generated method stub
		logger.info(string);
	}

	public static void error(Object ccSocket, String string) {
		// TODO Auto-generated method stub
		logger.error(string);
	}

	public static void docu(Object gy_SORT_Fetch, String string) {
		// TODO Auto-generated method stub
		logger.info(string);
	}

	public static void test(Object iss_DATA_Fetch, String string) {
		// TODO Auto-generated method stub
		
		logger.info(string);
	}

	
	

}
