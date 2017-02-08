package papa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import de.ifdag.log.Log;
import papa.DoubanBookByNo;
import papa.DoubanBookGetGoodPro;
import papa.bean.BookInfoBean;

/**
 * 
 * @author xj
 */



public class SqlDao  extends SqldbBase {
	 private  Connection con;

    public SqlDao(Connection con) {
		super();
		this.con = con;
	}
    
    public boolean insertBookInfo(List<BookInfoBean>list)  {
        PreparedStatement pstmt = null;
        boolean result =false;
        try {
            String sql =  "INSERT INTO book_info (bookName,zuozhe,zuozheCountry,chubanshe,fubiaoti,yuanzhuoming,yizhe,chubannian,pageCount,price"
            		+ ",zhuangzhen,congshu,tags,doubanScore,doubanScorePersonCount,isbn,synopsis,doubanID,updateTime) " + //8
            		     "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            Timestamp d=	 new Timestamp(System.currentTimeMillis());
             for(BookInfoBean b :list){
                    pstmt.setString(1,b.getBookName());
                    pstmt.setString(2,b.getZuozhe());
                    pstmt.setString(3,b.getZuozheCountry());
                    pstmt.setString(4,b.getChubanshe());
                    pstmt.setString(5,b.getFubiaoti());
                    pstmt.setString(6,b.getYuanzhuoming());
                    pstmt.setString(7,b.getYizhe());
                    pstmt.setDate(8,b.getChubannian());
                    pstmt.setInt(9,b.getPageCount());
                    pstmt.setDouble(10,b.getPrice());
                    pstmt.setString(11,b.getZhuangzhen());
                    pstmt.setString(12,b.getCongshu());
                    pstmt.setString(13,b.getTags());
                    pstmt.setDouble(14,b.getDoubanScore());
                    pstmt.setInt(15,b.getDoubanScorePersonCount());
                    pstmt.setString(16,b.getIsbn());
                    pstmt.setString(17,b.getSynopsis());
                    pstmt.setString(18,b.getDoubanID());
                    pstmt.setTimestamp(19, d);
                     pstmt.addBatch();
            }

            pstmt.executeBatch();

            result=true;
        }
        catch(Exception e){
        	 Log.error("-------------------------ERROR DATE----------------------------");
        	   for(BookInfoBean b :list){
        		   
        		   Log.error(b.toString());
        	   }
        	
            	 Log.error("-----------------------------------------------------------");
	    	  Log.exception(this,e);
	      }
        finally {
            close(pstmt);
        }
        return result;
    }

    
    public long  getMaxIppTime(){
    	
    	 PreparedStatement pstmt = null;
    	 ResultSet  rs=null;
    	 long time=0l;
         boolean result =false;
         try {
             String sql =  "select max(updateTime)  from ip_proxy  ";
             pstmt = con.prepareStatement(sql);
             
             rs= pstmt.executeQuery();
             if(rs.next()){
            	 time =rs.getTimestamp(1).getTime();
             }
             }
         
         catch(Exception e){
             e.printStackTrace();
	      }
       finally {
    	   close(rs);
           close(pstmt);
       }
         return time;
    }
    
    public boolean insertIP(List<String[]>list)  {
        PreparedStatement pstmt = null;
        boolean result =false;
        try {
            String sql =  "INSERT INTO ip_proxy (ip,port,status,updateTime) " + //8
            		     "  VALUES (?,?,0,?)";
            pstmt = con.prepareStatement(sql);
             for(String [] b :list){
                    pstmt.setString(1,b[0]);
                    pstmt.setString(2,b[1]);
                    pstmt.setTimestamp(3,  new Timestamp(Long.parseLong(b[2])));
                     pstmt.addBatch();
            }

            pstmt.executeBatch();

            result=true;
        }
        catch(Exception e){
              e.printStackTrace();
	      }
        finally {
            close(pstmt);
        }
        return result;
    }
    
    
    
    public List<String[]>  getporxyList(int status){
    	
   	 PreparedStatement pstmt = null;
   	 ResultSet  rs=null;
   	   List<String[]> list =new ArrayList<String[]>();
        try {
            String sql =  "select ip,port from ip_proxy  where status =?   ";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, status);
            rs= pstmt.executeQuery();
            while(rs.next()){
            	list.add(new String[] {rs.getString(1),rs.getString(2)});
            }
            
            }
        
        catch(Exception e){
            e.printStackTrace();
	      }
      finally {
   	   close(rs);
          close(pstmt);
      }
        return list;
   }
   
    
    
    
    public boolean  updateProxyIP (int dest, int orign)  {
        PreparedStatement pstmt = null;
        boolean result =false;
        try {
            String sql =  "update  ip_proxy set status= ? where status =? limit "+DoubanBookGetGoodPro.MaxProxy ;
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, dest);
            pstmt.setInt(2, orign);
            pstmt.executeUpdate();

            result=true;
        }
        catch(Exception e){
              e.printStackTrace();
	      }
        finally {
            close(pstmt);
        }
        return result;
    }
    
    public boolean  deleteProxyIP (String ip, String port)  {
        PreparedStatement pstmt = null;
        boolean result =false;
        try {
            String sql =  "delete  from ip_proxy where ip= ? and  port =? " ;
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ip);
            pstmt.setString(2, port);
            pstmt.execute();
            result=true;
        }
        catch(Exception e){
              e.printStackTrace();
	      }
        finally {
            close(pstmt);
        }
        return result;
    }
    
    
    
    
    public String  getLastDouBanID(){
    	
   	 PreparedStatement pstmt = null;
   	 ResultSet  rs=null;
   	 String id=null;
        try {
            String sql =  "SELECT doubanID FROM book_info  order by updatetime desc LIMIT 1  ";
            pstmt = con.prepareStatement(sql);
            
            rs= pstmt.executeQuery();
            if(rs.next()){
            	id =rs.getString(1);
            }
            }
        
        catch(Exception e){
            e.printStackTrace();
	      }
      finally {
   	   close(rs);
          close(pstmt);
      }
        return id;
   }


}
