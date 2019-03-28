package com.bao.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * @author Gelil
 * 第一步 创建一个job
 * 实现job这个接口
 */
public class HelloJob implements Job{
	//实现execute方法
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//编写具体的业务逻辑
		System.out.println("Hello World");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");
		System.out.println("HelloJob" + sdf.format(new Date()));//输出当时间
		
		//打印获取传入参数的值
		//job的
		JobKey key = context.getJobDetail().getKey();
		System.out.println("job:"+key.getGroup()+":"+key.getName());
		//参数
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String msg = jobDataMap.getString("message");
		float floatjobValue = jobDataMap.getFloat("floatjobValue");
		System.out.println("job参数:"+ msg +":"+floatjobValue);
		
		//trigger的
		TriggerKey key2 = context.getTrigger().getKey();
		System.out.println("trigger:"+key2.getGroup()+":"+key2.getName());
		
		//参数
		JobDataMap jobDataMap2 = context.getTrigger().getJobDataMap();
		String message = jobDataMap2.getString("message");
		double DoubleTriggerValue = jobDataMap2.getDouble("DoubleTriggerValue");
		System.out.println("trigger参数:"+ message +":"+DoubleTriggerValue);
		 
		
	}
}
