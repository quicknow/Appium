package com.appium_multi_dev_test;

import java.util.*;

//根据连接上adb的设备数量自动生成端口和判断端口是否被占用的类
public class Port{

	private DosCmd execDos;
	
	//初始化时执行cmd命令：adb devices 查看设备数量
	public Port(DosCmd execDos){
		this.execDos=execDos;
	}

	//端口是否被占用
	public Boolean isPortUsed(int portNum){

		List<String> portRes = new ArrayList<String>();
		boolean flag=true;
		try{
			portRes = execDos.execCmdConsole("netstat -ano|findstr "+ portNum); //注意+前面有个空格
			//System.out.println(portRes.size());  调试查看是否有占用端口
			if(portRes.size() > 0){
				flag=true;
			}else{
				
				flag=false;
			}
		}catch(Exception e){
			
		}
		return flag;
	}


	//根据设备数 生成可用端口
	public List<Integer> GeneratPortList(int portStart, int deviceTotal){
		List<Integer> portList = new ArrayList<Integer>();
		while(portList.size() != deviceTotal) {
			if(portStart >= 0 && portStart <=65535) {
				if(!isPortUsed(portStart)){
					portList.add(portStart);
				}
				portStart++;
			}			
		}
		return portList;
	}
	
	
	/*
	//调试测试
	public  static void main(String args[]){
		
		Port ports= new Port(new DosCmd());
		
		
		try {
			//cmd执行adb devices 的运行结果以便得到设备数 注意最后一行有个空行，设备数为（lists.size()-2）
			List<String> lists=ports.execDos.execCmdConsole("adb devices");
			//cmd命令执行结果展示
			for(String str:lists){
				System.out.println(str);
			}			
			System.out.println(lists.size());
			
			
			for(int i=1; i<lists.size() -1; i++) {
				String deviceInfo[] = lists.get(i).split("\t");
				
				for(int j=0;j<deviceInfo.length;j++){
					System.out.println(deviceInfo[j]);
				}
				
				if(deviceInfo[1].trim().equals("device")){				
					
					System.out.println(deviceInfo[0].trim());
				}
			}	
			
			//设备总数=lists.size()-2
			List<Integer> devportnum=ports.GeneratPortList(4493, lists.size()-2);
			
			for(Integer ins:devportnum){
				System.out.println(ins);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

}
