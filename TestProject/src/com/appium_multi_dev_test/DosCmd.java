package com.appium_multi_dev_test;

import java.util.*;
import java.io.*;

//调用dos运行的类
public class DosCmd{

	public boolean execCmd(String cmdString){
		Runtime p = Runtime.getRuntime();
		try{
			//开始执行
			System.out.println("开始执行 cmd /c "+cmdString);
			Process process = p.exec("cmd /c "+cmdString);
			
			process.waitFor();			
			process.destroy();
			System.out.println(cmdString+"执行结束了");
			return true;
		}catch(Exception e){
			
			return false;
		}
	}



		//把dos命令执行的结果放入list集合中并返回集合
	public List<String> execCmdConsole(String cmdString) throws InterruptedException{
		List<String> dosRes = new ArrayList<String>();
		try{
			Process process = Runtime.getRuntime().exec("cmd /c "+ cmdString);
			InputStream in = process.getInputStream();
			BufferedReader inr = new BufferedReader(new InputStreamReader(in,"GBK"));
			String line = null;
			while((line = inr.readLine())!=null){
				dosRes.add(line);
			}
			process.waitFor();
			process.destroy();
			
		}catch(IOException e){
			
		}
	
		return dosRes;
	
	}
	
	//根据进程号杀死进程
	public boolean killServer(int pid){
		if(execCmd("taskkill -f -PID"+String.valueOf(pid))){
		
			return true;
		}else{
			
			return false;
		}
	}

	//根据进程名杀死进程
	public boolean killServer(String serverName){
		if(execCmd("taskkill -f -im "+serverName)){
			
			return true;
		}else{
			
			return false;
		}
	}
	
}