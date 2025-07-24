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

import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Consumer.DeadLetterPolicy;
import org.springframework.stereotype.Service;
@Service
public class fraction { //处理牌型打分

	private static final Object Character = null;
	private static final boolean Integer = false;
	List<String> handlist ;  //自己的7 张牌
	List<String> opponentList; //对手的 7 张牌
	int hand ; 
	int opponent;
	int pair = 0;
	int three = 0;
	Map<Character, Integer> color1;
	Map<Character, Integer> color2;
	Map<String, Integer> number1;
	Map<String, Integer> number2;
	boolean flash = false;
	int four= 0;
	public void myhand(List<String> list,int i ) {
	
		if( i == 1) {
			handlist = new ArrayList<String>(list);
			 color1 =  dealcolor(list);	 //传参数得到 花色
			 number1 =dealnumber(list); //传参数 得到数字
			System.out.println(number1);
		  ifdeal(color1, 1);
		  ifdeal(number1,1);
		  System.out.print(hand);
		}
		if(i ==2) {
			opponentList = new ArrayList<String>(list);
		color2 =dealcolor(opponentList); //得到花色数量
		number2 =dealnumber (opponentList);//得到 数字的数量
			System.out.print (number2);
			ifdeal(color2, 2);
			ifdeal(number2, 2);
			System.out.println(opponent);
		}
}
	public int ifdeal( Map<?, Integer> list , int i ) { //
	
		for(Map.Entry<?,Integer > entry: list.entrySet()) {
			if(entry.getValue() == 4) { //四条
				four =1;
			}
			if(entry.getValue() == 5) { //判断 是否出现五张花色
				flash =true;
			}else if(entry.getValue() == 3) { //三条
			 three++ ;
			 }else if(entry.getValue() == 2) { //两对
			      pair =2;
			      }
			else if(entry.getValue() ==1) { // 一对
				 pair =1;
			}
		}
		if (pair == 2&& three ==1) { //葫芦
			 if( i == 1) {
				 hand = 7;
				 return hand;
			 }  else if( i == 2) {
				 opponent = 7;
				 return opponent;
			 }
		}
		
		if ( three ==1) { //三条
			 if( i == 1) {
				 hand = 7;
				 return hand;
			 }  else if( i == 2) {
				 opponent = 7;
				 return opponent;
			 }
		}
		if(pair == 2) { //两对
			 if( i == 1) {
				 hand = 4;
				 return hand;
			 }  else if( i == 2) {
				 opponent = 4;
				 return opponent;
			 }
		}
		return 0 ;
		}

	
	public void text () { 
		System.out.println( "自己的牌组为"+handlist);
		System.out.println("对手的牌组"+opponentList);
		System.out.println(hand);
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
	public  Map<String, Integer> dealnumber (List<String> list ) { //处理花色  用不用map 都可以
		
		Map<String ,Integer> numbermap =new HashMap<String, Integer>();
		
		 for(String play1 :list ) {
			String number = play1.substring( 1); //获取数字
			 int count =numbermap.getOrDefault(number, 0);
			numbermap.put(number,count+1);
		 }
		  return numbermap;
		  
	}
}
