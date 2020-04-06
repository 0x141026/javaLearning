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
		 
	        /* �ҳ�ָ����2���ַ��� ���ַ�������� λ�� */
	        int strStartIndex = str.indexOf(strStart);
	        int strEndIndex = str.indexOf(strEnd);
	 
	        /* index Ϊ���� ����ʾ���ַ����� û�и��ַ� */
	        if (strStartIndex < 0) {
	            return "�ַ��� :---->" + str + "<---- �в����� " + strStart + ", �޷���ȡĿ���ַ���";
	        }
	        if (strEndIndex < 0) {
	            return "�ַ��� :---->" + str + "<---- �в����� " + strEnd + ", �޷���ȡĿ���ַ���";
	        }
	        /* ��ʼ��ȡ */
	        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
	        return result;
	    }
	 /** 
     *  Webservice����
     */
	 public String getWebservice(String content){
		 try{
			//�����ַ
		 		//String requestUrl = "http://nkstar.jios.org:9080/webservice/fcjyWebService?wsdl";   
			 
		 		//String requestXml = getxml.readFileToString("D:/input_mz.xml");
		 		
		 		Object[] object = new Object[] { content };//�������
		 		Service service = new Service();
		 		Call call = (Call) service.createCall();
		 		call.setTargetEndpointAddress(wsdlurl);// Զ�̵���·��
		 		// ���õ������ռ�ͷ�����
		 		//call.setOperationName(new QName("http://www.nankaistar.com", "sendBaseXMLFcjyWebService"));
		 		call.setOperationName(new QName(namespace, method));
		 		call.setUseSOAPAction(true); 
		 		//������wsdl���Ҹ������ַ
		 		//call.setSOAPActionURI("http://www.nankaistar.com/sendBaseXMLFcjyWebService"); 
		 		call.setSOAPActionURI(soapactionname);
		 		
		 		//�����ռ�Ͳ����������������������д,������������wsdl�ļ����ҵ�
		 		//call.addParameter(new QName("http://www.nankaistar.com","pr"),
		 				//org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
		 		call.addParameter(new QName(namespace,parameters),
 				org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);

		 		//call.addParameter("string",XMLType.XSD_INT,javax.xml.rpc.ParameterMode.IN);
		 		
		 	    call.setReturnType(XMLType.XSD_STRING);// ����ֵ���ͣ�String
		 		call.setTimeout(30000);//��ʱ
		 		String result = (String) call.invoke(object);// Զ�̵���
		 		//System.out.println(result);
		 		return result;
		 }catch(Exception e){
			 e.printStackTrace();
			 return "";
		 }
		 
	 }
	 /** 
     * ���ֽ���дת����˼����д��������Ȼ������ʰ�滻���� Ҫ�õ�������ʽ 
     */ 
    public static String digitUppercase(double n) {  
        String fraction[] = { "��", "��"};  
        String digit[] = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��"};  
        String unit[][] = { { "Ԫ", "��", "��"}, { "", "ʰ", "��", "Ǫ"}};  
           
        String head = n < 0 ? "��" : "";  
        n = Math.abs(n);  
           
        String s = "";  
        for (int i = 0; i < fraction.length; i++) {  
            s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(��.)+", "");  
        }  
        if (s.length() < 1) {  
            s = "��";  
        }  
        int integerPart = (int) Math.floor(n);  
           
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {  
            String p = "";  
            for (int j = 0; j < unit[1].length && n > 0; j++) {  
                p = digit[integerPart % 10] + unit[1][j] + p;  
                integerPart = integerPart / 10;  
            }  
            s = p.replaceAll("(��.)*��$", "").replaceAll("^$", "��") + unit[0][i] + s;  
        }  
        return head + s.replaceAll("(��.)*��Ԫ", "Ԫ").replaceFirst("(��.)+", "").replaceAll("(��.)+", "��").replaceAll("^��$", "��Ԫ��");  
    }  
}
