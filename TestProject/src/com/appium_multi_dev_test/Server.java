package com.appium_multi_dev_test;
import java.util.*;

//获取当前连接的设备以及启动appium
public class Server{
	private List<Integer> appiumPortList;
	private List<Integer> bootstrapPortList;
	private List<String> deviceList;
	private Port port;
	private DosCmd dos;	
	
	public Server(Port port, DosCmd dos){
		this.port=port;
		this.dos=dos;
	}
	
			
	//获取当前所有连接的设备名称的集合 	dos.exeCmdConsole("adb devices");
	public List<String> getDevices() throws Exception{
		List<String> devList = dos.execCmdConsole("adb devices");
		List<String> deviceRes = new ArrayList<String>();

		if(devList.size()>2) {
			for(int i=1; i<devList.size() -1; i++) {
				String deviceInfo[] = devList.get(i).split("\t");
				if(deviceInfo[1].trim().equals("device")){						
					deviceRes.add(deviceInfo[0].trim());
				}
			}	
		} else{
			System.out.println("当前没有设备或设备连接状态不正确");
		}
		
		return deviceRes;
	
	}
	
	//根据设备数量生成可用端口列表
	private List<Integer> getPortList(int start) throws Exception{
		List<String> deviceList=getDevices();  //接入的所有设备的集合
		List<Integer> portList=port.GeneratPortList(start,deviceList.size()); //生成可用端口的集合
		return portList;
	}
	
	//生成启动服务端的命令，存入一个list ArraryList<String>();
	public List<String> GeneratServerCommand() throws Exception{
		appiumPortList=getPortList(4490);  //appium生成端口 从4490开始起生成
		bootstrapPortList=getPortList(2233);  //bootstrapPort生成端口 从2233开始生成起
		deviceList = getDevices();
		List<String> commandList = new ArrayList<String>();		
		for(int i=0;i<deviceList.size();i++){
			String command="appium -p " +appiumPortList.get(i)+" -bp "+bootstrapPortList.get(i)+" -U "+deviceList.get(i)+" >logs\\"+deviceList.get(i)+".txt";
			
			System.out.println(command);
			commandList.add(command);
		}
		XmlUtil.createDeviceXml(deviceList,appiumPortList); //创建device.xml文件
		XmlUtil.createTestngXml(2, "com.appium_multi_dev_test.MultDemo");   //创建Testng.xml文件
		return commandList;
	}
	
	//启动服务端
	public boolean startServers() throws Exception{
		List<String> startCommand=GeneratServerCommand();
		boolean flag=false; int i=0; 
		if(startCommand.size()>0){
			for(String s:startCommand){
				//dos.execCmd(s);
				final String str=s;	 //注意这里必须使用final否则线程中的参数值会发生变化
				
				  new Thread() {					  
				      public void  run() { 
				    	  
				    	  dos.execCmd(str);
				      }			          
				   }.start();
								    				
			}
			flag=true;
		}else{
			flag=false;
		}
		return flag;
	}
	
	
	public static void main(String[] args) throws Exception{
		Server servers = new Server(new Port(new DosCmd()),new DosCmd());
		//servers.GeneratServerCommand();
		servers.startServers();
		
	}

	
	
}
