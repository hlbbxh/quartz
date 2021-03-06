package com.bao.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author Gelil
 * 第二步骤
 */
public class HelloScheduler {
	public static void main(String[] args) {
		//创建一个jobdetail实例 讲该实例 与  hellojob  class绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myjob","group1")
				.usingJobData("message","hello myjob1")
				.usingJobData("floatjobValue",3014F)
				.build();
		
		//要点一 
		System.out.println("jobDetail name"+ jobDetail.getKey().getName());
		System.out.println("jobDetail group"+ jobDetail.getKey().getGroup());
		System.out.println("jobDetail jobclass"+ jobDetail.getJobClass().getName());		
		
		//要点二
		//jobDetail  中传入 .usingJobData("message","hello myjob1")
		//trigger    中传入  .usingJobData("message","hello myTrigger1")
		//一个key相同  一个不同  类型也不同  在HelloJob JobExecutionContext 获取
		
		//创建一个trigger实例  触发器 定义该job立即执行 并且每两秒钟执行一次
		Trigger trigger = TriggerBuilder.newTrigger()  //工厂模式创建的 new一个
				.withIdentity("mytrigger","group1") //定义标识符  和组
				.usingJobData("message","hello myTrigger1")
				.usingJobData("DoubleTriggerValue",2.0D)
				.startNow()   //现在执行
				.withSchedule(SimpleScheduleBuilder
						.simpleSchedule() //执行频度两秒钟一次
						.withIntervalInSeconds(2)
						.repeatForever()).build();
		//创建 scheduler 实例
		SchedulerFactory sfy = new StdSchedulerFactory();
		try {
			Scheduler scheduler = sfy.getScheduler();
			scheduler.start(); //开始执行
			
			//start 之后输出时间  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");
			System.out.println("HelloScheduler" + sdf.format(new Date()));//输出当时间
			
			scheduler.scheduleJob(jobDetail,trigger);//这个方法 jobDetail trigger 两个参数
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
}
