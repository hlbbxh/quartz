package com.bao.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Gelil
 * 第一步 创建一个job
 * 实现job这个接口
 */
public class HelloJob implements Job{
	//实现execute方法
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//编写具体的业务逻辑
		System.out.println("Hello World");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");
		System.out.println("HelloJob" + sdf.format(new Date()));//输出当时间
	}
}
