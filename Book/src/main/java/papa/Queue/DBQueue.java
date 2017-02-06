/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package papa.Queue;

import java.util.concurrent.ConcurrentLinkedQueue;

import papa.bean.BookInfoBean;

/**
 * @author xj
 */
public class DBQueue {

  	     public static ConcurrentLinkedQueue<BookInfoBean> bib =new  ConcurrentLinkedQueue<BookInfoBean> (); //报文队列
         /**
          * 添加数据
          * @param b 报文
          */
         public static void put(BookInfoBean b){
        	 bib.add(b);
         }
         public  synchronized static BookInfoBean get(){
        	 return bib.poll();

         }
 
         public static  boolean isEmpty(){ 
        	 return bib.isEmpty();
         }
         public static  int  getSize(){

        	 return bib.size();
         }
}
