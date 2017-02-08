package papa.Thread;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.sql.Connection;
import java.sql.SQLException;

import de.ifdag.log.Log;
import papa.dao.ConnectionSource;
import papa.dao.SqlDao;
/**
 * 超时线程
 * @author xj
 */


public class RedisQueryThread  extends Thread  {
   
    private  JedisPool pool;
    private  Spider s;
    private   RedisScheduler rs;
    private  static final String  key="queue_book.douban.com";
    private  static final String  u="https://book.douban.com/subject/";
	 public RedisQueryThread(String host,RedisScheduler r ,Spider ss) {
	        this(new JedisPool(new JedisPoolConfig(), host));
	        s=ss;
	        rs=r;
	    }

	    public RedisQueryThread(JedisPool pool) {
	        this.pool = pool;
	    }
	    
	    
    @Override
	public void run() {
    	 Jedis jedis;
    	 
    	Connection conn=null;
    	SqlDao dao ;
  	     jedis = pool.getResource();
    	 while(true){
    	
    		  Log.info("start QUEUE "+ key);
    		  Long size = jedis.llen(key);
    		  Log.info("QUEUE Size: "+ size);
    		  Log.info("end QUEUE "+ key);
    		  String url;
    		  String urlAgr[];
    		  int inDouBanID=0;
    		  
    		  if(size<100){
    			 
    				try {
    					conn = ConnectionSource.getConnection();
    					
    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				dao=new SqlDao(conn);
    				String did=dao.getLastDouBanID();
    				if(did==null){
    					
    					
    				}
    				else{
    					inDouBanID=Integer.parseInt(did)+1;
	    				 for(int i= inDouBanID; i<inDouBanID+300; i++ ){
	     					 rs.push(new Request(u+i+"/"), s);
	     				 }
    				 }
    		  }
    		  
    		  else if(size<200){
    			  url = jedis.rpop(key);
    				urlAgr= url.split("/");
    				 url = urlAgr[urlAgr.length - 1];
    				 inDouBanID=Integer.parseInt(url);
    				 for(int i= inDouBanID; i<inDouBanID+5000; i++ ){
    					// jedis.rpushx(key, u+i+"/");
    					 rs.push(new Request(u+i+"/"), s);
    				 }
    		  }
    		 try {
				sleep(30*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 }
	    
    
  

  

}
    

    

}
