package papa.Thread;
import papa.Queue.DBQueue;
import papa.bean.BookInfoBean;
import papa.dao.ConnectionSource;
import papa.dao.SqlDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 超时线程
 * @author xj
 */


public class DBInsertThread  extends Thread  {
   
	private final int insertBufferTime =40;
	
     public DBInsertThread() {
	}

    @Override
	public void run() {
    	Connection conn=null;
     	 try {
			conn = ConnectionSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     	SqlDao dao =new SqlDao(conn);
	   List<BookInfoBean> bib= new ArrayList<BookInfoBean>();
	   BookInfoBean bi;
	   int cc=0;
    	 while(true){
    		 bi= DBQueue.get();
    		 if(bi!=null){
    			 bib.add(bi);
    		 }
    		 
    		 if(cc>insertBufferTime){
    			 if(bib.size()>0){
    			
    				 dao.insertBookInfo(bib);
    				 bib= new ArrayList<BookInfoBean>();
    			 }
    			 
    		 }
    		 
            try {
            	cc++;
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
    
  

  

}
    

    

}
