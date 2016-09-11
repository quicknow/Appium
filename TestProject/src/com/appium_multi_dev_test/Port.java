package com.appium_multi_dev_test;

import java.util.*;

//����������adb���豸�����Զ����ɶ˿ں��ж϶˿��Ƿ�ռ�õ���
public class Port{

	private DosCmd execDos;	
	public Port(DosCmd execDos){
		this.execDos=execDos;
	}

	//�˿��Ƿ�ռ��
	public Boolean isPortUsed(int portNum){

		List<String> portRes = new ArrayList<String>();
		boolean flag=true;
		try{
			portRes = execDos.execCmdConsole("netstat -ano|findstr"+ portNum);
			if(portRes.size() > 0){
				
			}else{
				
				flag=false;
			}
		}catch(Exception e){
			
		}
		return flag;
	}


	//���ɶ˿�
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

}
