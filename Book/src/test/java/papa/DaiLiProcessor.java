package papa;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import papa.dao.ConnectionSource;
import papa.dao.SqlDao;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

/**
 * 
 * @author ReverieNight@Foxmail.com
 *
 */
public class DaiLiProcessor implements PageProcessor{
	
	private Site site =Site.me().setRetryTimes(3).setSleepTime(2000).setTimeOut(7000).setCycleRetryTimes(3).addHeader("Accept-Encoding", "/").setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36");
	
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yy-MM-dd HH:mm");
	private static long maxTime;
    private static int pageCount=1;
    private static final String url="http://www.xicidaili.com/nn/";
	static Connection conn=null;
	public Site getSite() {
		return site;
	}
	
	public void process(Page page) {
		   

		
	
        	List<String > ipList =page.getHtml().xpath("//table[@id=\"ip_list\"]//td[2]/text()").all();
        	List<String > portList=	page.getHtml().xpath("//table[@id=\"ip_list\"]//td[3]/text()").all();
        	List<String > dateList=	page.getHtml().xpath("//table[@id=\"ip_list\"]//td[10]/text()").all();
        
        	   List<String[]>  result= new ArrayList<String[]>();
        	   Date Da=null;
        	for(int i=0; i <dateList.size();i++){
        		
        		
        			try {
        				Da=sdf2.parse(dateList.get(i));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			
        	    if(Da.getTime()>maxTime){
        	       result.add(new String [] {ipList.get(i),portList.get(i),String.valueOf(Da.getTime())});
        		}
        		else{
        			
        		}
        		
        	}
        	if(result.size()>0){
        		
        		SqlDao dao =new SqlDao(conn);
        		dao.insertIP(result);
        	
        	  if(result.size()==dateList.size()){
        		  pageCount++;
        		  page.addTargetRequest(url+pageCount);
        	  }
        	  else{
        		  
        		  System.out.println("OUT");
        	  }
        	}
            
        	
        	
        	
        	
        	
        	
      
       
	}
	
	public static void main(String[] args) {
	
		try {
			conn = ConnectionSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		SqlDao dao =new SqlDao(conn);
		long t=dao.getMaxIppTime();
		if(t==0){
         t= new Date().getTime()-(3600*60);
			
		}
	
		maxTime=t;
		
		Spider.create(new DaiLiProcessor())
			.addUrl(url+pageCount)	//开始地址	
			
			.thread(1)	//开启5线程
			.run();
	}
	
}