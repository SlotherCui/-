package library.wtz.util;
//������������
public class ToolString {
	/**
	 * �ṩһ��������������ת��gb2312��gbk,utf-8
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
