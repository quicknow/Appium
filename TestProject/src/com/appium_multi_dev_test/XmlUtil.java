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
	
	}*/
	
	/*
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
		List<String> s=readXML(); //ȥ�������ļ�
		//�����߳��� ��̬����test
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
	
	//��ȡxml�ļ��ĺ���
	public static void readXML(){
		SAXReader reader = new SAXReader(); // User.hbm.xml��ʾ��Ҫ������xml�ĵ�  
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("testng.xml");  
        try {  
            Document doc = reader.read(in);  
            Element root = doc.getRootElement(); // ��ȡ���ڵ�  
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
                                System.out.println(item.getName() + "Ϊ"  
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
