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



}
