package com.massclouds.ns.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.NumberFormat;

/**
 * String工具类
 * 
 */
public class StringUtil {

	/**
	 * 空判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullorBlank(String str) {
		if (str == null || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 转码
	 * 
	 * @param str
	 * @throws UnsupportedEncodingException
	 */
	public static String enCode(String str) throws UnsupportedEncodingException {
		if (str == null || "".equals(str))
			return "";
		String s = new String(str.getBytes("ISO-8859-1"), "utf-8");
		return s;
	}

	/**
	 * 格式化字符串 例：4→0004
	 * 
	 * @param str
	 *            字符串
	 * @param digit
	 *            补零的位数
	 * @return 格式化后字符串
	 */
	public static String formatStr(int str, int digit) {
		// 得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(digit);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(digit);
		// 输出测试语句
		return nf.format(str);

	}

	/**
	 * 给字符串拼接单引号 例：004，005→‘004’，‘005’
	 * 
	 * @param str
	 *            字符串
	 * @return 拼接后字符串
	 */
	public static String joinStr(String str) {
		String[] array = str.split(",");
		StringBuilder strb = new StringBuilder();
		for (String Str : array) {
			strb.append("'").append(Str).append("',");
		}
		return strb.delete(strb.length() - 1, strb.length()).toString();
	}

	/**
	 * 字符串补齐
	 * 
	 * @param source
	 *            源字符串
	 * @param fillLength
	 *            补齐长度
	 * @param fillChar
	 *            补齐的字符
	 * @param isLeftFill
	 *            true为左补齐，false为右补齐
	 * @return
	 */
	public static String stringFill(String source, int fillLength,
			char fillChar, boolean isLeftFill) {
		if (source == null || source.length() >= fillLength)
			return source;

		StringBuilder result = new StringBuilder(fillLength);
		int len = fillLength - source.length();
		if (isLeftFill) {
			for (; len > 0; len--) {
				result.append(fillChar);
			}
			result.append(source);
		} else {
			result.append(source);
			for (; len > 0; len--) {
				result.append(fillChar);
			}
		}
		return result.toString();
	}
	
	/**
	 * md5加密
	 */
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	//es编码
	 public static String escape(String src) {  
	        int i;  
	        char j;  
	        StringBuffer tmp = new StringBuffer();  
	        tmp.ensureCapacity(src.length() * 6);  
	        for (i = 0; i < src.length(); i++) {  
	            j = src.charAt(i);  
	            if (Character.isDigit(j) || Character.isLowerCase(j)  
	                    || Character.isUpperCase(j))  
	                tmp.append(j);  
	            else if (j < 256) {  
	                tmp.append("%");  
	                if (j < 16)  
	                    tmp.append("0");  
	                tmp.append(Integer.toString(j, 16));  
	            } else {  
	                tmp.append("%u");  
	                tmp.append(Integer.toString(j, 16));  
	            }  
	        }  
	        return tmp.toString();  
	    }  
	 
	 	//es解码
	    public static String unescape(String src) {  
	        StringBuffer tmp = new StringBuffer();  
	        tmp.ensureCapacity(src.length());  
	        int lastPos = 0, pos = 0;  
	        char ch;  
	        while (lastPos < src.length()) {  
	            pos = src.indexOf("%", lastPos);  
	            if (pos == lastPos) {  
	                if (src.charAt(pos + 1) == 'u') {  
	                    ch = (char) Integer.parseInt(src  
	                            .substring(pos + 2, pos + 6), 16);  
	                    tmp.append(ch);  
	                    lastPos = pos + 6;  
	                } else {  
	                    ch = (char) Integer.parseInt(src  
	                            .substring(pos + 1, pos + 3), 16);  
	                    tmp.append(ch);  
	                    lastPos = pos + 3;  
	                }  
	            } else {  
	                if (pos == -1) {  
	                    tmp.append(src.substring(lastPos));  
	                    lastPos = src.length();  
	                } else {  
	                    tmp.append(src.substring(lastPos, pos));  
	                    lastPos = pos;  
	                }  
	            }  
	        }  
	        return tmp.toString();  
	    } 
	
	
	

}
