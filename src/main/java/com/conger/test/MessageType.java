package com.conger.test;

/**
 * Defines constants representing QoS message type.
 * @author Ang.Ji
 *
 */
public class MessageType {
	/**
	 * HEARTBEAT
	 */
	public final static short HEARTBEAT = 0;
	
	/**
	 * STOP
	 */
	public final static short STOP = 1;
	
	/**
	 * ERROR
	 */
	public final static short ERROR = 2;
	
	/**
	 * TIME_PULS
	 */
	public final static short TIME_PULSE = 3;
	
	/**
	 * DEBUG_DUMP_CONTEXT
	 */
	public final static short DUMP_CONTEXT = 4;
	
	/**
	 * CHECK_CONTEXT_DATA
	 */
	public final static short CEHCK_CONTEXT_DATA = 5; 
	
	/**
	 * END_USER_TRACK_COMMAND
	 */
	public final static short END_USER_TRACK = 6;
}
