/*
 * CopyRight (c) 2005-2012 GLOBE Co, Ltd. All rights reserved.
 * Filename:    TestCyclicBarrier.java
 * Creator:     qiaofeng
 * Create-Date: 下午3:48:45
 */
package com.toolkit.lang;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 * 
 * @author qiaofeng
 * @version $Id: TestCyclicBarrier, v 0.1 2013-4-1 下午3:48:45 Exp $
 */
class Config {
	// 徒步需要的时间: Shenzhen, Guangzhou, Shaoguan, Changsha, Wuhan
	public static int[]	timeWalk	= { 5, 8, 15, 15, 10 };
	// 自驾游
	public static int[]	timeSelf	= { 1, 3, 4, 4, 5 };
	// 旅游大巴
	public static int[]	timeBus		= { 2, 4, 6, 6, 7 };
	
	public static String now() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date()) + ":=> ";
	}
}

public class TestCyclicBarrier implements Runnable {
	private int[]			times;
	private CyclicBarrier	barrier;
	private String			tourName;
	
	public TestCyclicBarrier(CyclicBarrier barrier, String tourName, int[] times) {
		this.times = times;
		this.tourName = tourName;
		this.barrier = barrier;
	}
	
	public void run() {
		try {
			Thread.sleep(times[0] * 1000);
			System.out.println(Config.now() + tourName + " 到达 深圳");
			barrier.await();
			Thread.sleep(times[1] * 1000);
			System.out.println(Config.now() + tourName + " 到达广州");
			barrier.await();
			Thread.sleep(times[2] * 1000);
			System.out.println(Config.now() + tourName + " 到达韶关");
			barrier.await();
			Thread.sleep(times[3] * 1000);
			System.out.println(Config.now() + tourName + " 到达长沙");
			barrier.await();
			Thread.sleep(times[4] * 1000);
			System.out.println(Config.now() + tourName + " 到达武汉");
			barrier.await();
		} catch (InterruptedException e) {
		} catch (BrokenBarrierException e) {
		}
	}
	
	public static void main(String[] args) { // 三个旅行团
		CyclicBarrier barrier = new CyclicBarrier(3);
		ExecutorService exec = Executors.newFixedThreadPool(3);
		exec.submit(new TestCyclicBarrier(barrier, "徒步行团", Config.timeWalk));
		exec.submit(new TestCyclicBarrier(barrier, "自驾车团", Config.timeSelf));
		exec.submit(new TestCyclicBarrier(barrier, "观光车团", Config.timeBus));
		exec.shutdown();
	}
}
