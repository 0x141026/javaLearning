package com.just.estate.util;
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
 
 

 
public class GetXml {
	
	private String wsdlurl=GetProperties.getConfigProp("ds.data.wsdlurl");
	private String namespace=GetProperties.getConfigProp("ds.data.namespace");
	private String method=GetProperties.getConfigProp("ds.data.method");
	private String soapactionname=GetProperties.getConfigProp("ds.data.soapactionname");
	private String parameters=GetProperties.getConfigProp("ds.data.parameters");
	
	
	public String readFileToString(String filepath) throws FileNotFoundException, IOException {
		  StringBuilder sb = new StringBuilder();
		  String s ="";
		  InputStreamReader isr = new InputStreamReader(new FileInputStream(filepath), "UTF-8");
		  BufferedReader br = new BufferedReader(isr);
		  //BufferedReader br = new BufferedReader(new FileReader(filepath));

		  while( (s = br.readLine()) != null) {
		  sb.append(s + "\n");
		  }

		  br.close();
		  String str = sb.toString();
		  
		  
		  return str;
		 }
	
	 public  String subString(String str, String strStart, String strEnd) {
		 
	        /* 找出指定的2个字符在 该字符串里面的 位置 */
	        int strStartIndex = str.indexOf(strStart);
	        int strEndIndex = str.indexOf(strEnd);
	 
	        /* index 为负数 即表示该字符串中 没有该字符 */
	        if (strStartIndex < 0) {
	            return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
	        }
	        if (strEndIndex < 0) {
	            return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
	        }
	        /* 开始截取 */
	        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
	        return result;
	    }
	 /** 
     *  Webservice调用
     */
	 public String getWebservice(String content){
		 try{
			//服务地址
		 		//String requestUrl = "http://nkstar.jios.org:9080/webservice/fcjyWebService?wsdl";   
			 
		 		//String requestXml = getxml.readFileToString("D:/input_mz.xml");
		 		
		 		Object[] object = new Object[] { content };//请求参数
		 		Service service = new Service();
		 		Call call = (Call) service.createCall();
		 		call.setTargetEndpointAddress(wsdlurl);// 远程调用路径
		 		// 调用的命名空间和方法名
		 		//call.setOperationName(new QName("http://www.nankaistar.com", "sendBaseXMLFcjyWebService"));
		 		call.setOperationName(new QName(namespace, method));
		 		call.setUseSOAPAction(true); 
		 		//可以在wsdl中找个这个地址
		 		//call.setSOAPActionURI("http://www.nankaistar.com/sendBaseXMLFcjyWebService"); 
		 		call.setSOAPActionURI(soapactionname);
		 		
		 		//命名空间和参数名，参数名不可以随便写,参数名可以在wsdl文件中找到
		 		//call.addParameter(new QName("http://www.nankaistar.com","pr"),
		 				//org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
		 		call.addParameter(new QName(namespace,parameters),
 				org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);

		 		//call.addParameter("string",XMLType.XSD_INT,javax.xml.rpc.ParameterMode.IN);
		 		
		 	    call.setReturnType(XMLType.XSD_STRING);// 返回值类型：String
		 		call.setTimeout(30000);//超时
		 		String result = (String) call.invoke(object);// 远程调用
		 		//System.out.println(result);
		 		return result;
		 }catch(Exception e){
			 e.printStackTrace();
			 return "";
		 }
		 
	 }
	 /** 
     * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零 要用到正则表达式 
     */ 
    public static String digitUppercase(double n) {  
        String fraction[] = { "角", "分"};  
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};  
        String unit[][] = { { "元", "万", "亿"}, { "", "拾", "佰", "仟"}};  
           
        String head = n < 0 ? "负" : "";  
        n = Math.abs(n);  
           
        String s = "";  
        for (int i = 0; i < fraction.length; i++) {  
            s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");  
        }  
        if (s.length() < 1) {  
            s = "整";  
        }  
        int integerPart = (int) Math.floor(n);  
           
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {  
            String p = "";  
            for (int j = 0; j < unit[1].length && n > 0; j++) {  
                p = digit[integerPart % 10] + unit[1][j] + p;  
                integerPart = integerPart / 10;  
            }  
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;  
        }  
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");  
    }  
}
