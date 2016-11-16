package com.massclouds.ns.common.util;

import java.util.*;

public class RandomUtil  {  
	private static String str = "0,1,2,3,4,5,6,7,8,9"; 
    public static String getVerificationCode () {  
        String str2[] = str.split(",");//将字符串以,分割  
        Random rand = new Random(); 
        int index = 0;  
        //创建内容为空字符串对象randStr
        String randStr = "";
        for (int i=0; i<4; ++i) {  
        	//在0到str2.length-1生成一个伪随机数赋值给index  
            index = rand.nextInt(str2.length-1);
            //将对应索引的数组与randStr的变量值相连接
            randStr += str2[index];  
        }  
        return randStr;
    }  
    /**
     * 生成随即密码
     * @param pwd_len 生成的密码的总长度
     * @return  密码的字符串
     */
    public static String genRandomPassWord(int pwd_len){
     //35是因为数组是从0开始的，26个字母+10个数字
     final int  maxNum = 36;
     int i;  //生成的随机数
     int count = 0; //生成的密码的长度
     char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
       'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
       'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
     
     StringBuffer pwd = new StringBuffer("");
     Random r = new Random();
     while(count < pwd_len){
      //生成随机数，取绝对值，防止生成负数，
      i = Math.abs(r.nextInt(maxNum));  //生成的数最大为36-1
      if (i >= 0 && i < str.length) {
       pwd.append(str[i]);
       count ++;
      }
     }
     
     return pwd.toString();
    }
   
    public static void main(String[] args) {
		System.out.println(RandomUtil.genRandomPassWord(6));
	}
}  