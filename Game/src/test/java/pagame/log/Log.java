package pagame.log;


import org.apache.log4j.Logger;


public class Log {

	
	public static Logger logger =Logger.getLogger(Log.class);
	


	public static <T> void error(String string) {
		// TODO Auto-generated method stub
		logger.error(string);
	}

	public static void exception(String msg, Exception ex) {
		// TODO Auto-generated method stub
		logger.error(msg,ex);
	}
	
	public static void exception(Object n, Exception ex) {
		// TODO Auto-generated method stub
		logger.error(ex);
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
	public static void info(String string) {
		// TODO Auto-generated method stub
		logger.info(string);
	}


	
	

}
