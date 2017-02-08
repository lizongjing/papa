package pagame.sample;

import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pagame.SqlConfigure;
import pagame.savedb.DelDb;
import pagame.savedb.JdbcUtils;
import pagame.savedb.SaveDb;

public class TestMySql {


    /** 
     * @param args 
     */  
    public static void main(String[] args) throws SQLException {  
        // TODO Auto-generated method stub  
        JdbcUtils jdbcUtils = JdbcUtils.getInstance();
        jdbcUtils.getConnection();
  
        /*******************增*********************/  
        /*      String sql = "insert into userinfo (username, pswd) values (?, ?), (?, ?), (?, ?)"; 
        List<Object> params = new ArrayList<Object>(); 
        params.add("小明"); 
        params.add("123xiaoming"); 
        params.add("张三"); 
        params.add("zhangsan"); 
        params.add("李四"); 
        params.add("lisi000"); 
        try { 
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params); 
            System.out.println(flag); 
        } catch (SQLException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        }*/  
        

        
//        List<Object> params = new ArrayList<Object>(); 
//        params.add("重装机兵"); 
//        params.add("rpg"); 
//        params.add("小霸王"); 
//        params.add("90"); 
//        params.add("www.baidu.com"); 
//      SaveDb.savedb(SqlConfigure.sql,params);
//        
//        try { 
//            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params); 
//            System.out.println(flag); 
//            flag = jdbcUtils.updateByPreparedStatement(sql, params); 
//            System.out.println(flag); 
//            flag = jdbcUtils.updateByPreparedStatement(sql, params); 
//            System.out.println(flag); 
//            flag = jdbcUtils.updateByPreparedStatement(sql, params); 
//            System.out.println(flag); 
//            flag = jdbcUtils.updateByPreparedStatement(sql, params); 
//            System.out.println(flag); 
//        } catch (SQLException e) { 
//            // TODO Auto-generated catch block 
//            e.printStackTrace(); 
//        }
  
//        jdbcUtils.releaseConn();
        /*******************删*********************/  
        //删除名字为张三的记录  
        /*      String sql = "delete from userinfo where username = ?"; 
        List<Object> params = new ArrayList<Object>(); 
        params.add("小明"); 
        boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);*/  
        
//        String sql = "delete from gameinfo where gamename = ?"; 
//        List<Object> params = new ArrayList<Object>(); 
//        params.add("重装机兵"); 
//        boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
        
        
  
        /*******************改*********************/  
        //将名字为李四的密码改了  
        /*      String sql = "update userinfo set pswd = ? where username = ? "; 
        List<Object> params = new ArrayList<Object>(); 
        params.add("lisi88888"); 
        params.add("李四"); 
        boolean flag = jdbcUtils.updateByPreparedStatement(sql, params); 
        System.out.println(flag);*/  
        
//        String sql1 = "update gameinfo set gamefromurl = ? where gamename = ? "; 
//        List<Object> params1 = new ArrayList<Object>(); 
//        params1.add("www.dict.cn"); 
//        params1.add("重装机兵"); 
//        boolean flag = jdbcUtils.updateByPreparedStatement(sql1, params1); 
//        System.out.println(flag);
  
        /*******************查*********************/  
        //不利用反射查询多个记录  
        /*      String sql2 = "select * from userinfo "; 
        List<Map<String, Object>> list = jdbcUtils.findModeResult(sql2, null); 
        System.out.println(list);*/  
        
        
//        String sql2 = "select * from gameinfo "; 
//        List<Map<String, Object>> list = jdbcUtils.findModeResult(sql2, null);
//        //这个需要考虑怎么转化为list对象
//        System.out.println(list);
  
        //利用反射查询 单条记录  
//        String sql = "select * from userinfo where username = ? ";  
//        List<Object> params = new ArrayList<Object>();  
//        params.add("李四");  
//        UserInfo userInfo;  
//        try {  
//            userInfo = jdbcUtils.findSimpleRefResult(sql, params, UserInfo.class);  
//            System.out.print(userInfo);  
//        } catch (Exception e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
        
        
        
  
  
    }  
}
