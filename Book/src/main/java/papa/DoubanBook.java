package papa;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.ifdag.log.Log;
import papa.bean.BookInfoBean;
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
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(2000).setTimeOut(7000);
	
	
//	public static final String URL_LIST = "http://www\\.36dm\\.com/sort-4-\\d+\\.html";
  //  public static final String URL_POST = "http://www\\.36dm\\.com/show-\\w+\\.html";
	
	SimpleDateFormat sdf   =    new   SimpleDateFormat( "yyyy-MM-dd" ); 
	
	public static final String URL_MAIN =  "https://book.douban.com/tag/?view=type";
    //列表页的正则表达式 
    public static final String URL_LIST = "https://book\\.douban\\.com/tag/.+";
  //详情页的正则表达式
    public static final String URL_POST =  "https://book\\.douban\\.com/subject/.+";
    
    
    public static final String []PAINFO={"作者","出版社","副标题:","原作名","译者","出版年","页数","定价","丛书","装帧","ISBN"};
   
    
	
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
			String lineInfo[] =temp.split("<br>");
			String line;
			BookInfoBean bib=new BookInfoBean();
			String resultInfo;
			for(int i=0; i<lineInfo.length;i++){
				
				line =lineInfo[i].replaceAll("\n", "");
				for(int j=0; j<PAINFO.length;j++){
					if(line.contains(PAINFO[j])){
						resultInfo= getPaInfo(line);
						switch(j) {
						//{"作者","出版社","副标题:","原作名","译者","出版年","页数","定价","丛书","装帧","ISBN"};
						case  0:bib.setZuozhe(resultInfo); break;
						case  1:bib.setChubanshe(resultInfo); break;
						case  2:bib.setFubiaoti(resultInfo); break;
						case  3:bib.setYuanzhuoming(resultInfo); break;
						case  4:bib.setYizhe(resultInfo); break;
						case  5:
							try{
							  Date d= new Date(sdf.parse(resultInfo).getTime());
							  bib.setChubannian(d);
							  }
							catch(Exception e){
								
							}
							
						break;
						case  6:
							try{
									bib.setPageCount(Integer.parseInt(resultInfo)); 
								  }
								catch(Exception e){
									
								}
							break;
						case  7:
							try{
								bib.setPrice(Double.parseDouble(resultInfo));
							  }
							catch(Exception e){
								
							}
						break;
						case  8:bib.setCongshu(resultInfo); break;
						case  9:bib.setZhuangzhen(resultInfo); break;
						case  10:bib.setIsbn(resultInfo); break;
						default: break;
					}
					}
				}
			    
				
			}
			System.out.println(bib.toString());
			
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
	public static String  getPaInfo(String str){
		
		String temp=str.split("</span>")[1];
		String args[] = temp.split("</a>");
		if(args.length>1){
			return args[0].split(">")[1];
			
		}
		else{
			return temp.trim();
		}
		
	}
	
}