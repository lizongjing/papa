package papa;
import java.util.ArrayList;
import java.util.List;

import de.ifdag.log.Log;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 
 * @author ReverieNight@Foxmail.com
 *
 */
public class DoubanBook implements PageProcessor{
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(2000).setTimeOut(6000);
	
	
//	public static final String URL_LIST = "http://www\\.36dm\\.com/sort-4-\\d+\\.html";
  //  public static final String URL_POST = "http://www\\.36dm\\.com/show-\\w+\\.html";
	
	public static final String URL_MAIN =  "https://book.douban.com/tag/?view=type";
    //列表页的正则表达式 
    public static final String URL_LIST = "https://book\\.douban\\.com/tag/.+";
  //详情页的正则表达式
    public static final String URL_POST =  "https://book\\.douban\\.com/subject/.+";
    
    
  
   
    
	
	public Site getSite() {
		return site;
	}
	
	public void process(Page page) {
		if(page.getUrl().get().equals(URL_MAIN)){
			List<String> l_url = page.getHtml().xpath("//table[@class='tagCol']").links().regex(URL_LIST).all();	//所有的列表
			List<String> temp = new ArrayList<String>();
			temp.add(l_url.get(0));
		    page.addTargetRequests(temp);
		}
		
//		//列表页
		else if (page.getUrl().regex(URL_LIST).match()) {
        	List<String> l_url = page.getHtml().xpath("//a[@class=\"nbg\"]").links().regex(URL_POST).all(); //目标详情
        	List<String> list_url =page.getHtml().xpath("//span[@class=\"next\"]").links().regex(URL_LIST).all();;
             
        	page.addTargetRequests(list_url);
            page.addTargetRequests(l_url);
        //详情页
         } 
		else if (page.getUrl().regex(URL_POST).match()) {
			String bookName =page.getHtml().xpath("//span[@property=\"v:itemreviewed\"]/text()").get();
			String temp =page.getHtml().xpath("//div[@id=\"info\"]").get();
			System.out.println(temp);
			String hrefArg[] =temp.split("</a>");
			String [] zuozhe=hrefArg[0].split(">");
		    String writerInfo = zuozhe[zuozhe.length-1];
		    
		    zuozhe=hrefArg[1].split(">");
		    String translator = zuozhe[zuozhe.length-1];
		    
		    String otherInfo[]= temp.split("<br");
		    int i=0;
		    for(String o : otherInfo){
		    	System.out.println(i+":"+o);
		    	i++;
		    	
		    }
		   System.out.println(otherInfo[1].split("span>")[1].replaceAll("\n", ""));
		   System.out.println("--------------");
		    
			
//			String title = page.getHtml().xpath("//div[@class='location']").regex("\\[[\\S|\\s]+\\<").toString();	//匹配标题
//            page.putField("title", title.substring(0, title.length() - 1).trim());
//            page.putField("torrent", page.getHtml().xpath("//p[@class='original download']").links().toString().trim());	//匹配种子
//            System.out.println();
		}
		else {
			Log.error("error url "+page.getUrl().toString());
        
        }	
	}
	
	public static void main(String[] args) {
		Spider.create(new DoubanBook())
			.addUrl("https://book.douban.com/tag/?view=type")	//开始地址	
			.thread(1)	
			.run();
	}
	
}