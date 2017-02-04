package pagame;


import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class GetGameInfo implements PageProcessor {
	
	private Site site = Site.me().setSleepTime(1000).setRetryTimes(3);
	
	//列表页的正则表达式
	public static final String URL_LIST = "http://0day.ali213.net/all/1-act-0-2017-01-0-td-\\d+.html";
	//详情页的正则表达式
    public static final String URL_POST = "http://0day.ali213.net/html/2017/\\d+.html";
	
	public Site getSite() {
		return site;
	}
	
	public void process(Page page) {
		//列表页
        if (page.getUrl().regex(URL_LIST).match()) {
        	List<String> l_post = page.getHtml().xpath("//div[@class=\"ol_one_c\"]").links().regex(URL_POST).all(); //目标详情
        	List<String> l_url = page.getHtml().links().regex(URL_LIST).all();	//所有的列表
//        	for(int i = 0 ; i < l_post.size();i++){
//        		System.out.println("l_post==="+l_post.get(i));
//        	}
//        	for(int j = 0 ; j < l_url.size();j++){
//        		System.out.println("l_url==="+l_url.get(j));
//        	}
            page.addTargetRequests(l_post);
            page.addTargetRequests(l_url);
        //详情页
        } else {
        	System.out.println("1111111");
        	String title = page.getHtml().xpath("//div[@class=\"oinfo_tit_con_tit\"]/h1/text()").toString();	//匹配标题
            page.putField("title", title.substring(0, title.length() - 1).trim());
//            System.out.println("title=="+title.substring(0, title.length() - 1).trim());
//            page.putField("torrent", page.getHtml().xpath("//p[@class='original download']").links().toString().trim());	//匹配种子
//            System.out.println("page=="+page.getHtml().xpath("//p[@class='original download']").links().toString().trim());
            System.out.println();
        }
	}
	
	public static void main(String[] args) {
		Spider.create(new GetGameInfo())
			.addUrl("http://0day.ali213.net/all/1-act-0-2017-01-0-td-1.html")	//开始地址	
			.addPipeline(new ConsolePipeline())	//打印到控制台
			.addPipeline(new FilePipeline("D:\\webmagic\\Game1"))	//保存到文件夹
			.thread(1)	//开启5线程
			.run();
	}
	
}
