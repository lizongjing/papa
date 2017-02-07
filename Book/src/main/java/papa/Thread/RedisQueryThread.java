package papa.Thread;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


import de.ifdag.log.Log;
/**
 * 超时线程
 * @author xj
 */


public class RedisQueryThread  extends Thread  {
   
    private  JedisPool pool;
    private  static final String  key="queue_book.douban.com";
    private  static final String  u="https://book.douban.com/subject/";
	 public RedisQueryThread(String host) {
	        this(new JedisPool(new JedisPoolConfig(), host));
	    }

	    public RedisQueryThread(JedisPool pool) {
	        this.pool = pool;
	    }
	    
	    
    @Override
	public void run() {
    	 Jedis jedis;
    	 
    	 
    	 while(true){
    		 jedis = pool.getResource();
    		  Long size = jedis.llen(key);
    		  String url;
    		  String urlAgr[];
    		  int inDouBanID=0;
    		  if(size<500){
    			  url = jedis.lrange(key, 0,0).get(0);
    			  url.split("");
    				urlAgr= url.split("/");
    				 url = urlAgr[urlAgr.length - 1];
    				 inDouBanID=Integer.parseInt(url)+1;
    				 for(int i= inDouBanID; i<inDouBanID+5000; i++ ){
    					 jedis.rpushx(key, u+i+"/");
    				 }
    		  }
    		 try {
				sleep(60*1000*2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 }
	    
    
  

  

}
    

    

}
