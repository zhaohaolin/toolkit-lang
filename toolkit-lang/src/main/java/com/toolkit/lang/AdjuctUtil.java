package com.toolkit.lang;

/**
 * TODO
 * 
 * @author qiaofeng
 * @version $Id: AdjuctUtil, v 0.1 2013-3-30 下午08:44:28 Exp $
 */
public abstract class AdjuctUtil {
	
	/**
	 * 
	 */
	public AdjuctUtil() {
		//
	}
	
	/**
	 * 2013-3-30 joe.zhao
	 * 
	 * @param string
	 * @param width
	 * @return
	 */
	public static String getAdjuct(String string, int width) {
		try {
			if (string == null) {
				string = "null";
			} else {
				int length = string.length();
				
				if (length > width) {
					string = string.substring(0, width - 1) + "...";
				}
			}
		} catch (Exception e) {
			//
		}
		
		return string;
	}
	
}
