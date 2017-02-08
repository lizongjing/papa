package pagame.savedb;

import java.sql.SQLException;
import java.util.List;

public class SaveDb {

	public static boolean savedb(String sql,List<Object> params){
		JdbcUtils jdbcUtils = JdbcUtils.getInstance();
		jdbcUtils.getConnection();
		
		
	      try { 
	          boolean flag = JdbcUtils.getInstance().updateByPreparedStatement(sql, params); 
//	          System.out.println(flag); 
	          
	      } catch (SQLException e) { 
	          // TODO Auto-generated catch block 
	          e.printStackTrace(); 
	          return false;//表示插入失败
	      }
      
      return true;//表示插入成功
	}
	
}
