package com.conger.test;

/**
 * Defines constants representing stream types.
 * @author Ang.Ji
 *
 */
public final class StreamType {
	/**
	 * LIVE
	 */
	public final static short LIVE = 0;
	
	/**
	 * VOD
	 */
	public final static short VOD = 1;
	
	/**
	 * DVR
	 */
	public final static short DVR = 2;
	
	public final static String[] streamTypeStrings = {"LIVE", "VOD", "DVR"};
	
	public  static String getTypeString(short shortType){
		if(shortType < streamTypeStrings.length){
			return streamTypeStrings[shortType];
		}else{
			return "UNKNOWN";
		}
	}
}
