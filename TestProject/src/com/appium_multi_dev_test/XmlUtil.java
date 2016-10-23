package com.appium_multi_dev_test;
import java.io.FileOutputStream;
import java.io.Writer;
import java.sql.SQLException;
import java.util.*;

import javax.sql.RowSetInternal;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.spi.XmlWriter;

import org.apache.bcel.classfile.InnerClass;
import org.apache.bcel.generic.ARRAYLENGTH;
import org.dom4j.io.*;
import org.dom4j.*;
import org.dom4j.io.XMLWriter;

import java.io.*;



public class XmlUtil{
	
	//��̬����Device xml�ļ��ķ���   ��Ҫ dom4j jar��֧��  DocumentHelper.createDocument();
	public static void createDeviceXml(List<String> deviceList,List<Integer> appiumPortList) throws Exception{
		Document document = DocumentHelper.createDocument();   //����һ��document����ͨ�������½�һ��xml�ĵ�
		Element root = DocumentHelper.createElement("Device"); //����һ��element���󣬼�����һ��Device��ǩ		
		document.setRootElement(root);		
		root.addAttribute("name","appiumstartlist");  //��ǩ���һ������appiumstarlist
		
		if(deviceList.size()>0){
			for(int j=0;j<deviceList.size();j++){
				Element deviceId = root.addElement("deviceId"); //Device��ǩ������ΪdeviceId���ӱ�ǩ				
				deviceId.addAttribute("id",String.valueOf(j)); //deviceId�ӱ�ǩ����������Ϊid
				
				Element deviceName = deviceId.addElement("deviceName"); //deviceid��ǩ������ΪdeviceName�ӱ�ǩ
				Element appiumPort = deviceId.addElement("appiumPort"); //deviceid��ǩ������Ϊ��appiumPort�ӱ�ǩ ����deviceName�ӱ�ǩͬ����
				
				deviceName.setText(deviceList.get(j));  //����devceName��ǩ�м��ֵ
				appiumPort.setText(String.valueOf(appiumPortList.get(j))); //����appiumPortList��ǩ�м��ֵ
			
			}
		
		}
		
		OutputFormat format  = new OutputFormat("    ", true);
		XMLWriter xmlWriter2;
		
		try {			
			xmlWriter2 = new XMLWriter(new FileOutputStream("device.xml"),format);			
			xmlWriter2.write(document);
			xmlWriter2.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	
	}
	
	
	//��̬����testngxml�����ļ�
	public static void createTestngXml(int threadCount, String classname) throws Exception{
		Server server = new Server(new Port(new DosCmd()),new DosCmd());
		List<String> deviceList = server.getDevices();
		Document document = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("suite");
		document.setRootElement(root);
		root.addAttribute("name","Suite");
		root.addAttribute("parallel","tests");
		root.addAttribute("thread-count",String.valueOf(threadCount));
		List<String[]> s=readXML(); //ȥ�������ļ�
		//List<String> s=new ArrayList<String>();
		//�����߳��� ��̬����test
		for(int j=0;j<threadCount;j++){
			Element test =root.addElement("test");
			test.addAttribute("name",deviceList.get(j));
			Element paramPort = test.addElement("parameter");
			paramPort.addAttribute("name","port");
			paramPort.addAttribute("value",s.get(j)[1]);
			Element paramUuid=test.addElement("parameter");
			paramUuid.addAttribute("name","devicename");
			paramUuid.addAttribute("value",s.get(j)[0]);
			Element classes = test.addElement("classes");	
		
			Element classNode=classes.addElement("class");
			classNode.addAttribute("name", classname);
			
		}
		
		OutputFormat format  = new OutputFormat("    ", true);
		XMLWriter xmlWriter2;
		
		try {			
			xmlWriter2 = new XMLWriter(new FileOutputStream("testng2.xml"),format);			
			xmlWriter2.write(document);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
	}
	
	//��ȡxml�ļ��ĺ���
	public static List<String[]> readXML(){
		File file = new File("device.xml");
		SAXReader reader = new SAXReader(); // User.hbm.xml��ʾ��Ҫ������xml�ĵ�  
        List<String[]> s=new ArrayList<String[]>();		
        try {  
            Document doc = reader.read(file);  
            Element root = doc.getRootElement(); // ��ȡ���ڵ�  
            Element element;   
            for (Iterator i = root.elementIterator("deviceId"); i.hasNext();) {   
            	  element = (Element) i.next(); 
            	  /*
	              System.out.print("name:" + element.elementText("deviceName")+" ");   
	              System.out.println("port:" + element.elementText("appiumPort")); */
            	  String str1=element.elementText("deviceName");
            	  String str2=element.elementText("appiumPort");
            	  String[] str=new String[2];
            	  str[0]=str1;
            	  str[1]=str2;
            	  s.add(str);
             }             
           
            
        } catch (DocumentException e) {  
  
            e.printStackTrace();  
        } 
        
        return s;
	}
	//����
	public static void main(String[] args) throws Exception{
		//createTestngXml(2,"cn.gloryroad.gloryroadAppium.WeiXinTest");
		//readXML();
		//��������device.xml�Ĵ����
		String device="192.168.245.101:5555";
		String device2="192.168.245.102:5555";
		List<String> deviceList=new ArrayList<String>();
		deviceList.add(device);
		deviceList.add(device2);
		List<Integer> appiumPortList=new ArrayList<Integer>();		
		Integer intr= new Integer(4493);
		Integer intr2= new Integer(4494);
		appiumPortList.add(intr);
		appiumPortList.add(intr2);
		//XmlUtil.createDeviceXml(deviceList, appiumPortList);
		XmlUtil xx= new XmlUtil();
		xx.createDeviceXml(deviceList, appiumPortList);
		
		
		
	}

}
