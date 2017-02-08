package papa.dao;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import de.ifdag.log.Log;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Properties;
/**
 * @author xj
 */

public class ConnectionSource {
    private static BasicDataSource dataSource = null;
    public ConnectionSource() {
    }
    /**
     */
    public static void init() {
       if (dataSource != null) {
                try {
                    dataSource.close();
                } catch (Exception e) {
                    Log.exception(ConnectionSource.class,e);
                }
                dataSource = null;
            }
        try {
        
  			String serverName="192.168.100.149";
  			serverName="127.0.0.1";
  		
  			//serverName="172.10.162.30";
            Properties p = new Properties();
            p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
            p.setProperty("url", "jdbc:mysql://"+serverName+":3306/book?useUnicode=true&characterEncoding=utf-8");
            p.setProperty("password", "etrans");
            p.setProperty("username", "root");
            p.setProperty("maxActive", "20");
            p.setProperty("maxIdle", "12");
            p.setProperty("maxWait", "1000");
            p.setProperty("testOnBorrow", "true");
            p.setProperty("testWhileIdle", "true");
            p.setProperty("validationQuery", "select 1 from dual");
            p.setProperty("removeAbandoned", "true");
            p.setProperty("removeAbandonedTimeout", "120");
            p.setProperty("logAbandoned", "true");
            
            dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
        	Log.exception(ConnectionSource.class,e);
      }

    }

     /**
     */

    public static synchronized Connection getConnection() throws  SQLException {
        if (dataSource == null) {
            init();
        }
        Connection conn = null;
        if (dataSource != null) {
            conn = dataSource.getConnection();
        }
        return conn;


    }


}
