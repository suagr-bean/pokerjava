package com.example.demo.service;

import java.awt.DisplayMode;
import java.security.KeyStore.Entry;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntToDoubleFunction;

import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Consumer.DeadLetterPolicy;
import org.springframework.stereotype.Service;
@Service
public class fraction { //处理牌型打分

	private static final Object Character = null;

	List<String> handlist ;  //自己的7 张牌
	List<String> opponentList; //对手的 7 张牌
	List<String> sort1 ;
	List<String> sort2; //排序过的数组 
	int play1 ; 
	int play2;

	Map<Character, Integer> color1;
	Map<Character, Integer> color2;
	Map<String, Integer> number1;
	Map<String, Integer> number2;
	
	private static final Map<String, Integer> cardmap =new HashMap<String, Integer>();
	static {  //映射
		cardmap.put("2", 2);
		cardmap.put("3", 3);
		cardmap.put("4", 4);
		cardmap.put("5", 5);
		cardmap.put("6", 6);
		cardmap.put("7", 7);
		cardmap.put("8", 8);
		cardmap.put("9", 9);
		cardmap.put("10", 10);
		cardmap.put("J", 11);
		cardmap.put("Q", 12);
		cardmap.put("K", 13);
		cardmap.put("A", 14);
	} 

  
	public void myhand(List<String> list,int i ) { //传参数
	           
		if( i == 1) {
			handlist = new ArrayList<String>(list);
			sort1 =cardsort(list);
			 color1 =  dealcolor(list);	 //传参数得到 花色
			 number1 =dealnumber(list); //传参数 得到数字
		
	
		 play1  =ifdeal(color1, number1);

		}
		if(i ==2) {
			opponentList = new ArrayList<String>(list);
			sort2  =cardsort(list);
		color2 =dealcolor(opponentList); //得到花色数量
		number2 =dealnumber (opponentList);//得到 数字的数量
			play2 =ifdeal(color2,number2);
		}
}
	 public List<String> cardsort(List<String> list) { //排序
		list.sort((card1,card2) ->{
			 String num1 = card1.substring(1);
			 String num2 =card2.substring(1); //提取数字
			 int rank1 =cardmap.getOrDefault(num1, 0);
			 int rank2 =cardmap.getOrDefault(num2, 0) ; 
		     return Integer.compare(rank1, rank2); //返回比较的大小
		 });
		 return list;
	 }
	public int ifdeal( Map<Character, Integer> colormap ,Map<String, Integer> numbermap ) { // 判断除掉顺子的牌 返回大小
		int four =0 ;
		int pair = 0;
		int three = 0;
		boolean flush = false; //同花
	
		 for(Map.Entry<Character, Integer> entry: colormap.entrySet()) {
			 if (entry.getValue() == 5) {
				flush = true;
				break;
			 }
		 }
		for(Map.Entry<String,Integer > entry: numbermap.entrySet()) {
			if(entry.getValue() == 4) { //四条
				four =1;
			}else if(entry.getValue() == 3) { //三条
			 three=1 ;
			 }else if(entry.getValue() == 2) { //两对 
			      pair ++;
			      }
			
		}
		 if (four == 1) return 8;         // 四条
		    if (three == 1 && pair  >= 1) return 7;  // 葫芦
		    if (flush == true) return 6;            // 同花
		    if (three == 1) return 4;       // 三条
		    if (pair >= 2) return 3;        // 两对
		    if (pair == 1) return 2;        // 一对
		    return 1;      
		}

	
	public void text () {   //测试打印 不需要的功能
	/*	System.out.println( "自己的牌组为"+handlist);
		System.out.println("对手的牌组"+opponentList);
		System.out.println( play1);
		System.err.println(play2);*/
		System.out.println(sort1);
		System.out.println(sort2);
	} 
	public  Map<Character, Integer> dealcolor (List<String> list ) { //处理花色  用不用map 都可以
		
		Map<Character, Integer> suitMap =new HashMap<Character, Integer>();
		
		 for(String play :list ) {
			 char color = play.charAt( 0); //获取花色
			 int count =suitMap.getOrDefault(color, 0);
			 suitMap.put(color,count+1);
		 }
		  return suitMap;
		  
	}
	public  Map<String, Integer> dealnumber (List<String> list ) { //处理数字  用不用map 都可以
		
		Map<String ,Integer> numbermap =new HashMap<String, Integer>();
		
		 for(String play1 :list ) {
			String number = play1.substring( 1); //获取数字
			 int count =numbermap.getOrDefault(number, 0);
			numbermap.put(number,count+1);
		 }
		  return numbermap;
		  
	}
}
