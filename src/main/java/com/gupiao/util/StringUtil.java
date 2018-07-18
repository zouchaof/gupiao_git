package com.gupiao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class StringUtil {
	
	/**
	 * 字符串左补0
	 * */
	public static String stringFillLeftZero(String str, int len) {
		if (str.length() < len) {
			StringBuffer sb = new StringBuffer(len);
			for (int i = 0; i < len - str.length(); i++)
				sb.append('0');
			sb.append(str);
			return new String(sb);
		} else
			return str;
	}
	
	/**
	   * 功能：不定长参数,其中一个参数为null或空则返回true,负责返回false
	   * 
	   * @param str
	   * @return boolean
	   */
	  public static boolean isEmpty(String... str) {
	    for (String s : str) {
	      if (StringUtils.isEmpty(s)) {
	        return true;
	      }
	    }
	    return false;
	  }

	  /**
	   * 功能：不定长参数,其中一个参数为null或空或为空格字符串则返回true,负责返回false
	   * 
	   * @param str
	   * @return boolean
	   */
	  public static boolean isBlank(String... str) {
	    for (String s : str) {
	      if (StringUtils.isBlank(s))
	        return true;
	    }
	    return false;
	  }

	  /**
	   * 功能：判断字符串是否是数值. 默认允许有正负号,默认允许有小数点
	   * 
	   * @param str
	   * @return
	   */
	  public static boolean isNumeric(String str) {
	    boolean sign = true;
	    int point_bef = Integer.MAX_VALUE;// 小数点前有几位
	    int point_aft = Integer.MAX_VALUE;// 小数点后有几位
	    return isNumeric(str, sign, point_bef, point_aft);
	  }

	  /**
	   * 功能：判断字符串是否是数值
	   * 
	   * @param str
	   * @param sign
	   *          是否允许有正负号
	   * @param point
	   *          是否允许有小数点
	   * @return
	   */
	  public static boolean isNumeric(String str, boolean sign, boolean point) {
	    int point_bef = Integer.MAX_VALUE;// 小数点前有几位
	    int point_aft = Integer.MAX_VALUE;// 小数点后有几位
	    if (!point)
	      point_aft = 0;

	    return isNumeric(str, sign, point_bef, point_aft);
	  }

	  /**
	   * 功能：判断字符串是否是数值
	   * 
	   * @param str
	   * @param sign
	   *          是否允许有正负号
	   * @param point_bef
	   *          精度,小数点前有几位
	   * @param point_aft
	   *          精度,小数点后有几位,如果为0,则为整数
	   * 
	   * @return
	   */
	  public static boolean isNumeric(String str, boolean sign, int point_bef,
	      int point_aft) {
	    if (StringUtil.isBlank(str)) {
	      return false;
	    }
	    boolean point = true;// 是否允许小数点
	    if (point_aft == 0) {
	      point = false;// 不允许有小数点
	    } else {
	      point = true;
	    }
	    StringBuffer pat = new StringBuffer();
	    if (sign) {
	      pat.append("[+|-]?");
	    }
	    if (point_bef == 0) {
	      pat.append("[0]");
	    } else {
	      pat.append("[0-9]{1,");
	      pat.append(point_bef);
	      pat.append("}");
	    }
	    if (point && str.indexOf(".") != -1) {// 允许小数点,并且有小数点
	      pat.append("[.]");
	      pat.append("[0-9]{1,");// 小数点后必须有一位
	      pat.append(point_aft);
	      pat.append("}");
	    }
	    Pattern pattern = Pattern.compile(pat.toString());
	    if (!pattern.matcher(str).matches()) {
	      return false;
	    } else {// 排除如00.1,返回false
	      if (str.indexOf(".") != -1
	          && str.substring(0, str.indexOf(".")).length() > 1
	          && Integer.valueOf(str.substring(0, str.indexOf("."))) == 0) {
	        return false;
	      } else {
	        return true;
	      }
	    }
	  }

	  /**
	   * 功能：查看字符串是否有这个子字符串
	   * 
	   * @param str
	   *          主字符串
	   * @param substr
	   *          字字符串
	   * @return
	   */
	  public static boolean hasSubstring(String str, String substr) {
	    if (str == null || substr == null)
	      return false;
	    int strLen = str.length();
	    int substrLen = substr.length();
	    for (int i = 0; (i + substrLen) <= strLen; i++) {
	      if (str.substring(i, i + substrLen).equalsIgnoreCase(substr)) {
	        return true;
	      }
	    }
	    return false;
	  }

	  /**
	   * 功能：验证是否是正确的手机号
	   * 
	   * @param mobile
	   * @return
	   */
	  public static boolean isMobile(String mobile) {
	    if (StringUtil.isBlank(mobile))
	      return false;
	    return Pattern.matches("^(1[3|5|8])\\d{9}$", mobile);
	  }

	  /**
	   * 功能：字符串不以"/"结尾，则在串尾加"/"
	   * 
	   * @param s
	   * @return
	   */
	  public static String addSlashInEnd(String s) {
	    if (s != null) {
	      s = s.trim();
	      if (!s.endsWith("/")) {
	        s = s + "/";
	      }
	    } else {
	      s = "";
	    }
	    return s;
	  }

	  /**
	   * 功能：字符串不以"/"结尾，则在串尾加"/";字符串如果以/开头,则去掉第一个/
	   * 
	   * @return
	   */
	  public static String dealSlash(String s) {
	    if (s != null) {
	      s = s.trim();
	      if (!s.endsWith("/")) {
	        s = s + "/";
	      }
	      if (s.startsWith("/")) {
	        s = s.substring(1, s.length());
	      }
	    } else {
	      s = "";
	    }
	    return s;

	  }

	  /**
	   * 功能：传入一个数字类型的参数，返回一个小数点后两位的小数
	   * 
	   * @param parm
	   */
	  public static String ConverDouble(String parm) {
	    if (isNumeric(parm, false, true)) {
	      if (parm.indexOf(".") >= 0) {
	        String value = parm.substring(parm.indexOf(".") + 1);
	        if (value.length() == 1) {
	          return parm + "0";
	        } else if (value.length() > 2) {
	          return parm.substring(0, parm.indexOf(".") + 1)
	              + value.substring(0, 2);
	        } else {
	          return parm;
	        }
	      } else {
	        return parm + ".00";
	      }
	    }
	    return null;
	  }
	  
	  
	  /**
	   * 
	   * @param obj
	   * @return String
	   * @obj==null,或obj是空字符串，就返回参数ifEmptyThen，否则返回obj.toString。
	   */

	  public static String ifEmptyThen(Object obj,String ifEmptyThen) {
      String ret="";
      if(obj==null||String.valueOf(obj).equals("")){
        ret=ifEmptyThen;
      }else{
        ret=obj.toString();
      }
      return ret;
    }
	
	public static String filterNull(Object s) {
		if (s == null) {
			return "";
		} else {
			return s.toString();
		}
	}
	

	/**
	 * 	
	 * @param str 字符串 如："'1','2','3'"
	 * @param type String   or  BigDecimal
	 * @return
	 */
	public static Object[] getInVal(String str,String type) {
		String strs [] = str.split(",");
		String t = "";
		List<Object> list = new ArrayList<Object>();
		for (String string : strs) {
			t+="?,";
			if (type.equals("String")) {
				list.add(string.replace("'", ""));
			}else if (type.equals("BigDecimal")) {
				list.add(new BigDecimal(string.replace("'", "")));
			}
		}
		if (t.length() > 0) {
			t = t.substring(0,t.length()-1);
		}
		Object[] obj = new Object[2];
		obj[0] = t;
		obj[1] = list;
		return obj;
	}

	
    public static String ClobToString(Clob clob){
    	String reString = ""; 

    	try {
        	if(clob == null){
        		return reString;
        	}
        	Reader is = clob.getCharacterStream();// 得到流 
        	BufferedReader br = new BufferedReader(is); 
        	String s = br.readLine(); 
        	StringBuffer sb = new StringBuffer(); 
        	while (s != null) {// 执行循环将字符串全部取出付值给 StringBuffer由StringBuffer转成STRING 
        	sb.append(s);
        	s = br.readLine();
        	} 
        	reString = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	return reString; 
    }
    
    public static boolean isHoliday(Clob dayTime, String date){
    	String dayString = ClobToString(dayTime);    	
    	if(dayString.indexOf(date) > -1){
    		return true;
    	}
    	return false;    	
    }

	

	public static String getInString(String stringWithComma){
		String[] array = stringWithComma.split(",");
		String returnString = "";
		if(stringWithComma.indexOf("-1")>-1){
			return "-1";
		}
		for(String temp : array){
			if(returnString.length() > 0){
				returnString += ",";
			}
			returnString += ("'"+temp+"'");
		}
		return returnString;
	}
	
	

	public static void main(String args[]){
             for(int i=0;i<100;i++){
            	 System.out.println(random6Int());
             }
	}
	
	public static String random6Str(){
		return GetRandomString(6);
	}

	public static String GetRandomString(int Len) {
		
		String[] baseString={"0","1","2","3",
				"4","5","6","7","8","9",
				"a","b","c","d","e",
				"f","g","h","i","j",
				"k","l","m","n","o",
				"p","q","r","s","t",
				"u","v","w","x","y",
				"z","A","B","C","D",
				"E","F","G","H","I",
				"J","K","L","M","N",
				"O","P","Q","R","S",
				"T","U","V","W","X","Y","Z"};
		Random random = new Random();
	    int length=baseString.length;
        String randomString="";
        for(int i=0;i<length;i++){
        	randomString+=baseString[random.nextInt(length)];
        }
		random = new Random(System.currentTimeMillis());
		String resultStr="";
		for (int i = 0; i < Len; i++) {
			resultStr += randomString.charAt(random.nextInt(randomString.length()-1));
		}
		return resultStr;
	}
	

	public static String random6Int(){
		String temp="";
		for(int i=0;i<6;i++){
			Random ran=new Random();
			int random=0+(int)((ran.nextDouble())*(9));
			temp+=""+random;
		}
		return temp;
	}


	/**
	 * 手机号掩码
	 * @param phone
	 * @return
	 */
	public static String maskPhone(String phone){
		if(phone == null || phone.length() !=11){
			return "****";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.left(phone, 3));
		sb.append("****");
		sb.append(StringUtils.right(phone, 4));
		return sb.toString();
	}

	
	
	public static String formatXML(String inputXML) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(inputXML));
		String requestXML = null;
		XMLWriter writer = null;
		if (document != null) {
			try {
				StringWriter stringWriter = new StringWriter();
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setExpandEmptyElements(true);
				writer = new XMLWriter(stringWriter, format);
				writer.write(document);
				writer.flush();
				requestXML = stringWriter.getBuffer().toString();
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return requestXML;
	}
	
	/**
	 * 判断map中的value是否为空
	 * @param map map
	 * @param columns 要判断的key数组
	 * @return 返回value为空的对应的key
	 */
	public static String containsValueInMap(Map<String, String> map, String[] columns)
	{
		for (String key : columns)
		{
			if (StringUtils.isBlank(map.get(key)))
			{
				return key;
			}
		}
		return "";
	}
	
	/**
	 * 过滤多余的参数
	 * @param map 
	 * @param columns key值数组
	 */
	public static void filterExtraValueInMap(Map<String, String> map, String[] columns)
	{
		List<String> keyList = Arrays.asList(columns);
		Iterator<String> iterator = map.keySet().iterator();
		//通过迭代器的remove删除元素
		while (iterator.hasNext())
		{
			String next = iterator.next();
			if (!keyList.contains(next))
			{
				iterator.remove();
			}
		}
	}
}
