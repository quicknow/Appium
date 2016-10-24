package com.appium_multi_dev_test;
import java.util.*;

//��ȡ��ǰ���ӵ��豸�Լ�����appium
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
	
			
	//��ȡ��ǰ�������ӵ��豸���Ƶļ��� 	dos.exeCmdConsole("adb devices");
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
			System.out.println("��ǰû���豸���豸����״̬����ȷ");
		}
		
		return deviceRes;
	
	}
	
	//�����豸�������ɿ��ö˿��б�
	private List<Integer> getPortList(int start) throws Exception{
		List<String> deviceList=getDevices();  //����������豸�ļ���
		List<Integer> portList=port.GeneratPortList(start,deviceList.size()); //���ɿ��ö˿ڵļ���
		return portList;
	}
	
	//������������˵��������һ��list ArraryList<String>();
	public List<String> GeneratServerCommand() throws Exception{
		appiumPortList=getPortList(4490);  //appium���ɶ˿� ��4490��ʼ������
		bootstrapPortList=getPortList(2233);  //bootstrapPort���ɶ˿� ��2233��ʼ������
		deviceList = getDevices();
		List<String> commandList = new ArrayList<String>();		
		for(int i=0;i<deviceList.size();i++){
			String command="appium -p " +appiumPortList.get(i)+" -bp "+bootstrapPortList.get(i)+" -U "+deviceList.get(i)+" >logs\\"+deviceList.get(i)+".txt";
			
			System.out.println(command);
			commandList.add(command);
		}
		XmlUtil.createDeviceXml(deviceList,appiumPortList); //����device.xml�ļ�
		XmlUtil.createTestngXml(2, "com.appium_multi_dev_test.MultDemo");   //����Testng.xml�ļ�
		return commandList;
	}
	
	//���������
	public boolean startServers() throws Exception{
		List<String> startCommand=GeneratServerCommand();
		boolean flag=false; int i=0; 
		if(startCommand.size()>0){
			for(String s:startCommand){
				//dos.execCmd(s);
				final String str=s;	 //ע���������ʹ��final�����߳��еĲ���ֵ�ᷢ���仯
				
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
