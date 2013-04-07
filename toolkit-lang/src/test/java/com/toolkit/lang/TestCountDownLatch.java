/*
 * CopyRight (c) 2005-2012 GLOBE Co, Ltd. All rights reserved.
 * Filename:    TestCountDownLatch.java
 * Creator:     qiaofeng
 * Create-Date: 下午4:00:01
 */
package com.toolkit.lang;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 * 
 * @author qiaofeng
 * @version $Id: TestCountDownLatch, v 0.1 2013-4-1 下午4:00:01 Exp $
 */
public class TestCountDownLatch {
	
	public static void main(String[] args) throws InterruptedException {
		run(30);
	}
	
	public static void run(int len) throws InterruptedException {
		// 开始的倒数锁
		final CountDownLatch begin = new CountDownLatch(1);
		// 结束的倒数锁
		final CountDownLatch end = new CountDownLatch(len);
		// 十名选手
		final ExecutorService exec = Executors.newFixedThreadPool(8);
		
		for (int i = 0; i < len; i++) {
			final int NO = i + 1;
			
			exec.submit(new Runnable() {
				
				// 多线程部分
				public void run() {
					try {
						begin.await();
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println(DateUtil.formatDate(new Date(),
								"HH:mm:ss") + " No." + NO + " arrived");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						end.countDown();
					}
				}
				
			});
			
		}
		
		System.out.println("Game Start");
		// TODO 一定要在所有线程执行前执行的任务
		begin.countDown();
		end.await();
		System.out.println("Game Over");
		// TODO 一定要在所有线程执行后执行的任务
		exec.shutdown();
	}
}
