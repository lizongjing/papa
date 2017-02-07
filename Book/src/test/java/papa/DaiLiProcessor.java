package papa;
import java.util.List;

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
	
	private Site site = Site.me().setSleepTime(1000).setRetryTimes(3);
	
	//列表页的正则表达式
	public static final String URL_LIST = "http://www\\.kuaidaili\\.com/free/inha/\\d+\\";
	//详情页的正则表达式
	
	public Site getSite() {
		return site;
	}
	
	public void process(Page page) {
		
		System.out.println(page.getUrl());
		//列表页
//        	List<String> l_url = page.getHtml().xpath("//div[@id=\"listnav\"]").links().regex(URL_LIST).all(); 
//        	
//        	for(String s:l_url ){
//        		System.out.println(s);
//        	}
//        	
//            page.addTargetRequests(l_url);
        //详情页
	}
	
	public static void main(String[] args) {
		Spider.create(new DaiLiProcessor())
			.addUrl("http://www.xicidaili.com/")	//开始地址	
			.thread(1)	//开启5线程
			.run();
	}
	
}