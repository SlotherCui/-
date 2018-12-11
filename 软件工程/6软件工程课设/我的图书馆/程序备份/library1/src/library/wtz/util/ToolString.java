package library.wtz.util;
//处理乱码问题
public class ToolString {
	/**
	 * 提供一个方法，将乱码转成gb2312，gbk,utf-8
	 */
	public static String getNewString(String input){
		String result="";
		try{
			result=new String(input.getBytes("iso-8859-1"),"gb2312");
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
