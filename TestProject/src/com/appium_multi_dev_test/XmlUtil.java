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
	
	//动态生成Device xml文件的方法   需要 dom4j jar包支持  DocumentHelper.createDocument();
	public static void createDeviceXml(List<String> deviceList,List<Integer> appiumPortList) throws Exception{
		Document document = DocumentHelper.createDocument();   //创建一个document对象，通常用于新建一个xml文档
		Element root = DocumentHelper.createElement("Device"); //创建一个element对象，即创建一个Device标签		
		document.setRootElement(root);		
		root.addAttribute("name","appiumstartlist");  //标签添加一个属性appiumstarlist
		
		if(deviceList.size()>0){
			for(int j=0;j<deviceList.size();j++){
				Element deviceId = root.addElement("deviceId"); //Device标签增加名为deviceId的子标签				
				deviceId.addAttribute("id",String.valueOf(j)); //deviceId子标签的属性设置为id
				
				Element deviceName = deviceId.addElement("deviceName"); //deviceid标签增加名为deviceName子标签
				Element appiumPort = deviceId.addElement("appiumPort"); //deviceid标签增加名为的appiumPort子标签 （与deviceName子标签同级）
				
				deviceName.setText(deviceList.get(j));  //设置devceName标签中间的值
				appiumPort.setText(String.valueOf(appiumPortList.get(j))); //设置appiumPortList标签中间的值
			
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
	
	
	//动态生成testngxml配置文件
	public static void createTestngXml(int threadCount, String classname) throws Exception{
		Server server = new Server(new Port(new DosCmd()),new DosCmd());
		List<String> deviceList = server.getDevices();
		Document document = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("suite");
		document.setRootElement(root);
		root.addAttribute("name","Suite");
		root.addAttribute("parallel","tests");
		root.addAttribute("thread-count",String.valueOf(threadCount));
		List<String[]> s=readXML(); //去读配置文件
		//List<String> s=new ArrayList<String>();
		//根据线程数 动态生成test
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
	
	//读取xml文件的函数
	public static List<String[]> readXML(){
		File file = new File("device.xml");
		SAXReader reader = new SAXReader(); // User.hbm.xml表示你要解析的xml文档  
        List<String[]> s=new ArrayList<String[]>();		
        try {  
            Document doc = reader.read(file);  
            Element root = doc.getRootElement(); // 获取根节点  
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
	//调试
	public static void main(String[] args) throws Exception{
		//createTestngXml(2,"cn.gloryroad.gloryroadAppium.WeiXinTest");
		//readXML();
		//调试生成device.xml的代码块
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
