package com.appium_multi_dev_test;

import java.util.*;

//����������adb���豸�����Զ����ɶ˿ں��ж϶˿��Ƿ�ռ�õ���
public class Port{

	private DosCmd execDos;
	
	//��ʼ��ʱִ��cmd���adb devices �鿴�豸����
	public Port(DosCmd execDos){
		this.execDos=execDos;
	}

	//�˿��Ƿ�ռ��
	public Boolean isPortUsed(int portNum){

		List<String> portRes = new ArrayList<String>();
		boolean flag=true;
		try{
			portRes = execDos.execCmdConsole("netstat -ano|findstr "+ portNum); //ע��+ǰ���и��ո�
			//System.out.println(portRes.size());  ���Բ鿴�Ƿ���ռ�ö˿�
			if(portRes.size() > 0){
				flag=true;
			}else{
				
				flag=false;
			}
		}catch(Exception e){
			
		}
		return flag;
	}


	//�����豸�� ���ɿ��ö˿�
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
	//���Բ���
	public  static void main(String args[]){
		
		Port ports= new Port(new DosCmd());
		
		
		try {
			//cmdִ��adb devices �����н���Ա�õ��豸�� ע�����һ���и����У��豸��Ϊ��lists.size()-2��
			List<String> lists=ports.execDos.execCmdConsole("adb devices");
			//cmd����ִ�н��չʾ
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
			
			//�豸����=lists.size()-2
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
