package com.appium_multi_dev_test;
import java.io.FileOutputStream;
import java.io.Writer;
import java.sql.SQLException;
import java.util.*;

import javax.sql.RowSetInternal;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.spi.XmlWriter;

import org.dom4j.io.*;
import org.dom4j.*;
import org.dom4j.io.XMLWriter;
import java.io.*;



public class XmlUtil{
	/*
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
	
	}*/
	
	/*
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
		List<String> s=readXML(); //去读配置文件
		//根据线程数 动态生成test
		for(int j=0;j<threadCount;j++){
			Element test =root.addElement("test");
			test.addAttribute("name",deviceList.get(j));
			Element paramPort = test.addElement("parameter");
			paramPort.addAttribute("name","port");
			paramPort.addAttribute("value",s.get(j+1));
			Element paramUuid=test.addElement("parameter");
			paramUuid.addAttribute("name","uuid");
			paramUuid.addAttribute("value",s.get(j));
			Element classes = test.addElement("classes");	
		
			Element classNode=classes.addElement("class");
			classNode.addAttribute("name", classname);
			
		}
		
		OutputFormat format  = new OutputFormat("    ", true);
		XMLWriter xmlWriter2;
		
		try {			
			xmlWriter2 = new XMLWriter(new FileOutputStream("testng.xml"),format);			
			xmlWriter2.write(document);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
	}*/
	
	//读取xml文件的函数
	public static void readXML(){
		SAXReader reader = new SAXReader(); // User.hbm.xml表示你要解析的xml文档  
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("testng.xml");  
        try {  
            Document doc = reader.read(in);  
            Element root = doc.getRootElement(); // 获取根节点  
            List<Element> list = new ArrayList<Element>();  
            list.add(root);  
            while (list != null) {  
                Element element = null;  
                Element ele = null;  
  
                Iterator ite = list.iterator();  
                if (ite.hasNext()) {  
                    ele = (Element) ite.next();  
                    ite.remove();  
                }  
                if (ele != null) {  
                    for (Iterator i = ele.elementIterator(); (i != null)  
                            && (i.hasNext());) {  
                        element = (Element) i.next();  
                        list.add(element);  
                        if (element != null) {  
                            System.out.println(element.getName() + " : "  
                                    + element.getPath() + " --"  
                                    + element.getText());  
                            for (Iterator iter = element.attributeIterator(); iter  
                                    .hasNext();) {  
                                Attribute item = (Attribute) iter.next();  
                                System.out.println(item.getName() + "为"  
                                        + item.getValue());  
                            }  
                        }  
                    }  
                }  
            }  
        } catch (DocumentException e) {  
  
            e.printStackTrace();  
        } 
		
	}
	
	public static void main(String[] args) throws Exception{
		//createTestngXml(2,"cn.gloryroad.gloryroadAppium.WeiXinTest");
		readXML();
	}

}
