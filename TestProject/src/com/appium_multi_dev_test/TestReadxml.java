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

public class TestReadxml {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readxml();
	}
	
	public static void readxml() {  
        SAXReader reader = new SAXReader(); // User.hbm.xml表示你要解析的xml文档  
        InputStream in = Thread.currentThread().getContextClassLoader()  
                .getResourceAsStream("testng.xml");  
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

}
