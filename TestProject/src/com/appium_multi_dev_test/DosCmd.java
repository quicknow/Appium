package com.appium_multi_dev_test;

import java.util.*;
import java.io.*;

//调用dos运行的类
public class DosCmd{

	public boolean execCmd(String cmdString){
		Runtime p = Runtime.getRuntime();
		try{
			Process process = p.exec("cmd /c"+cmdString);
			process.waitFor();			
			process.destroy();
			return true;
		}catch(Exception e){
			
			return false;
		}
	}



		//把dos命令执行的结果返回回来
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
	
	
	public boolean killServer(int pid){
		if(execCmd("taskkill -f -PID"+String.valueOf(pid))){
		
			return true;
		}else{
			
			return false;
		}
	}


	public boolean killServer(String serverName){
		if(execCmd("taskkill -f -im "+serverName)){
			
			return true;
		}else{
			
			return false;
		}
	}
	
}