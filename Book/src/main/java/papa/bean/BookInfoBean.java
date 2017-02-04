package papa.bean;

import java.sql.Date;

public class BookInfoBean {
	
	private String zuozhe;
	private String chubanshe;
	private String fubiaoti;
	private String yuanzhuoming;
	private String yizhe;
	private Date chubannian;
	private int pageCount;
	private double price;
	private String zhuangzhen;
	private String congshu;
	private String tags;
	private String doubanScore;
	private String doubanScorePersonCount;
	private String isbn;
	private String synopsis;
	
	
	public String getZuozhe() {
		return zuozhe;
	}
	public void setZuozhe(String zuozhe) {
		this.zuozhe = zuozhe;
	}
	public String getChubanshe() {
		return chubanshe;
	}
	public void setChubanshe(String chubanshe) {
		this.chubanshe = chubanshe;
	}
	public String getFubiaoti() {
		return fubiaoti;
	}
	public void setFubiaoti(String fubiaoti) {
		this.fubiaoti = fubiaoti;
	}
	public String getYuanzhuoming() {
		return yuanzhuoming;
	}
	public void setYuanzhuoming(String yuanzhuoming) {
		this.yuanzhuoming = yuanzhuoming;
	}
	public String getYizhe() {
		return yizhe;
	}
	public void setYizhe(String yizhe) {
		this.yizhe = yizhe;
	}

	
	public Date getChubannian() {
		return chubannian;
	}
	public void setChubannian(Date chubannian) {
		this.chubannian = chubannian;
	}

	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getZhuangzhen() {
		return zhuangzhen;
	}
	public void setZhuangzhen(String zhuangzhen) {
		this.zhuangzhen = zhuangzhen;
	}
	public String getCongshu() {
		return congshu;
	}
	public void setCongshu(String congshu) {
		this.congshu = congshu;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDoubanScore() {
		return doubanScore;
	}
	public void setDoubanScore(String doubanScore) {
		this.doubanScore = doubanScore;
	}
	public String getDoubanScorePersonCount() {
		return doubanScorePersonCount;
	}
	public void setDoubanScorePersonCount(String doubanScorePersonCount) {
		this.doubanScorePersonCount = doubanScorePersonCount;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String toString(){
		StringBuffer sb =new StringBuffer();
		sb.append("zuozhe: "+zuozhe);
		sb.append("\n");
		sb.append("chubanshe: "+chubanshe);
		sb.append("\n");
		sb.append("fubiaoti: "+fubiaoti);
		sb.append("\n");
		sb.append("yuanzhuoming: "+yuanzhuoming);
		sb.append("\n");
		sb.append("yizhe: "+yizhe);
		sb.append("\n");
		sb.append("chubannian: "+chubannian);
		sb.append("\n");
		sb.append("pageCount: "+pageCount);
		sb.append("\n");
		sb.append("price: "+price);
		sb.append("\n");
		sb.append("zhuangzhen: "+zhuangzhen);
		sb.append("\n");
		sb.append("congshu: "+congshu);
		sb.append("\n");
		sb.append("tags: "+tags);
		sb.append("\n");
		sb.append("doubanScore: "+doubanScore);
		sb.append("\n");
		sb.append("doubanScorePersonCount: "+doubanScorePersonCount);
		sb.append("\n");
		sb.append("isbn: "+isbn);
		sb.append("\n");
		sb.append("synopsis: "+synopsis);
		
		
		return sb.toString();
		
		
		
	}
	

}
