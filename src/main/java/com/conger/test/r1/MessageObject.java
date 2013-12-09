package com.conger.test.r1;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * A POJO class used to wrap QoS message data that be send from data receiver to 
 * statistic server.
 * @author Ang.Ji
 *
 */

public final class MessageObject implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String	siteID;
	private int		siteIDHashCode;	
	private int 	viewIDHashCode = 0;	
	private int 	clientIDHashCode = 0;
	private String	viewID = MessageObject.emptyString; 
    private int 	msgID = -1;
	private short 	msgType = MessageType.HEARTBEAT;
	private int 	errorCode = -1;
	private String 	ipAddress = MessageObject.emptyString;	
	private String 	userID = MessageObject.emptyString;
	
	private long 	playheadTime = 0;
	private short 	streamType = StreamType.LIVE;
	private String 	streamURL = MessageObject.emptyString;
	private String 	streamDescritpion = MessageObject.emptyString;
	private int 	streamID = 0;
	private long	streamLength = 0;
	private boolean hasProductID = false;
	private String	productID = MessageObject.emptyString;
	private boolean hasProgramID = false;
	private long    programID = 0;
	private boolean hasProgramType = false;
	private String  programType = MessageObject.emptyString;
	private boolean hasGameID = false;
	private long    gameID = 0;
	private String  homeTeam  = MessageObject.emptyString;
	private String  awayTeam  = MessageObject.emptyString;
	private int     gameDate  = 0;
	
	private int 	startupTime = 0;
	private int 	updateInterval = 0;
	private int 	bitrate = 0;
	private short 	bitrateSwitchMethod = BitrateSwitchMethod.ADAPTIVE;
	private int 	bandwidth = 0;
	private int 	dropFrames = 0;
	private int 	bufferLength;
	private int 	numBuffer = 0;
	private int[]	bufferTimes = MessageObject.emptyIntArray;
	
	private String[] cdnNames = MessageObject.emptyStringArray;
	private long[] 	cdnBytesLoaded = MessageObject.emptyLongArray;
	private int[]	cdnBytesDelta = MessageObject.emptyIntArray;

	private int 	cityID = 0;
	private int		regionID = 0;
	private int		countryID = 0;
	private String	cityName = MessageObject.emptyString;
	private String 	regionName = MessageObject.emptyString;
	private String 	countryName = MessageObject.emptyString;
	
	private String 	windowMode = MessageObject.defaultWindowMode;
	private String 	os = MessageObject.emptyString;
	private String 	playerVersion = MessageObject.emptyString;
	private String	browserVersion = MessageObject.emptyString;
	private String 	deviceType = MessageObject.pcDeviceType;
	private String	networkType = MessageObject.emptyString;
	
	private int     appTypeID = 1;
	private String  appType = MessageObject.defaultAppType;
	
	private String[] tagNames = MessageObject.emptyStringArray;
	private String[] tagValues = MessageObject.emptyStringArray;
	
	/**
	 * Private constructor to prevent create MessageObject instance directly.
	 * To get a MessageObject instance, use MessageObject.getMessageObject().
	 * @see MessageObject.getMessageObject()
	 */
	public MessageObject(){
		
	}
	
	/**
	 * @return Site ID.
	 */
	public String getSiteID(){
		return this.siteID;
	}
	
	/**
	 * 
	 * @param siteID QoS message siteID parameter.
	 */
	public void setSiteID(String siteID){
		this.siteID = siteID;
	}
	
	/**
	 * 
	 * @return Hash code of site ID.
	 */
	public int getSiteIDHashCode(){
		return this.siteIDHashCode;
	}
	
	/**
	 * 
	 * @param siteID Value of QoS message siteID parameter.
	 */
	public void setSiteIDHashCode(int siteIDHash){
			this.siteIDHashCode = siteIDHash;
	}
	
	/**
	 * This value is used for debug and trouble shooting.
	 * @return First 8 characters of input siteID.
	 */
	public String getViewID(){
		return this.viewID;
	}
	
	/**
	 * 
	 * @param viewID Value of QoS message viewID parameter.
	 */
	public void setViewID(String viewID){
		if(viewID != null){
			this.viewID = viewID.substring(0, Math.min(8, viewID.length()));			
		}
	}
	
	/**
	 * 
	 * @param viewIDHash Hash code of viewID.
	 */
	public void setViewIDHashCode(int viewIDHash){
		this.viewIDHashCode = viewIDHash;
	}
	
	/**
	 * 
	 * @return Hash code of assigned viewID. 
	 */
	public int getViewIDHashCode(){
		return this.viewIDHashCode;
	}
	
	/**
	 * 
	 * @param clientID Hash code of QoS message cientID parameter.
	 */
	public void setClientIDHashCode(int clientIDHashCode){
			this.clientIDHashCode = clientIDHashCode;
	}
	
	/**
	 * 
	 * @return Hash code of clientID.
	 */
	public int getClientIDHashCode(){
		return this.clientIDHashCode;
	}
	
	/**
	 * 
	 * @param msgID Value of QoS message msgID parameter. 
	 */
	public void setMessageID(int msgID){
		this.msgID = msgID;
	}
	
	/**
	 * 
	 * @return QoS message msgID parameter.
	 */
	public int getMessageID(){
		return this.msgID;
	}
	
	/**
	 * 
	 * @param msgType One of the values defined in com.neulion.qos.common.message.MessageType, corresponding to QoS message eventType parameter.
	 * @see com.conger.test.r1.neulion.qos.common.message.MessageType
	 */
	public void setMessageType(short msgType){
		this.msgType = msgType;
	}
	
	/**
	 * 
	 * @return Message type value defined in com.neulion.qos.common.message.MessageType.
	 */
	public short getMessageType(){
		return this.msgType;
	}
	
	/**
	 * 
	 * @param errorCode When messageType is ERROR, set the code parameter.
	 */
	public void setErrorCode(int errorCode){
		this.errorCode = errorCode;
	}
	
	/**
	 * 
	 * @return Error code of an ERROR message.
	 */
	public int getErrorCode(){
		return this.errorCode;
	}
	
	/**
	 * 
	 * @param ipAddress Remote IP address of client send the QoS message.
	 */
	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}
	
	/**
	 * 
	 * @return Remote IP address of QoS message client.
	 */
	public String getIpAddress(){
		return this.ipAddress;
	}
	
	/**
	 * 
	 * @param userID Value of userID parameter in QoS message.
	 */
	public void setUserID(String userID){
		this.userID = userID;
	}
	
	/**
	 * 
	 * @return QoS message userID value.
	 */
	public String getUserID(){
		return this.userID;
	}
	
	/**
	 * 
	 * @param playheadTime QoS message playTime parameter value.
	 */
	public void setPlayheadTime(long playheadTime){
		this.playheadTime = playheadTime;
	}
	
	public long getPlayheadTime(){
		return this.playheadTime;
	}
	
	/**
	 * 
	 * @param streamType One of values defined in com.neulion.qos.common.message.StreamType, corresponding to streamType parameter of QoS message.
	 * @see com.conger.test.r1.neulion.qos.common.message.StreamType
	 */
	public void setStreamType(short streamType){
		this.streamType = streamType;
	}
	
	public short getStreamType(){
		return this.streamType;
	}
	
	/**
	 * 
	 * @param streamURL QoS message streamURL parameter.
	 */
	public void setStreamURL(String streamURL){
		this.streamURL = streamURL;
	}
	
	public String getStreamURL(){
		return this.streamURL;
	}
	
	/**
	 * 
	 * @param streamName QoS message streamDescription parameter.
	 */
	public void setStreamName(String streamName){
		this.streamDescritpion = streamName;
	}
	
	public String getStreamName(){
		return this.streamDescritpion;
	}
	
	/**
	 * 
	 * @param streamLength QoS message streamLength parameter.
	 */
	public void setStreamLength(long streamLength){
		this.streamLength = streamLength;
	}
	
	public long getStreamLength(){
		return this.streamLength;
	}
	
	public boolean getHasProductID(){
		return this.hasProductID;
	}
	
	/**
	 * 
	 * @param productID QoS message productID parameter.
	 */
	public void setProductID(String productID){
		this.productID = productID;
		this.hasProductID = true;
	}
	
	public String getProductID(){
		return this.productID;
	}
	
	/**
	 * 
	 * @return A stream ID representing a unique stream.
	 */
	public int getStreamID(){
		return this.streamID;
	}
	

	
	public boolean getHasProgramID(){
		return this.hasProgramID;
	}
	
	public void setProgramID(long programID){
		this.programID = programID;
		this.hasProgramID = true;
	}
	
	public long getProgramID(){
		return this.programID;
	}
	
	public boolean getHasProgramType(){
		return this.hasProgramType;
	}
	
	public void setProgramType(String programType){
		this.programType = programType;
		this.hasProgramType = true;
	}
	
	public String getProgramType(){
		return this.programType;
	}
	
	public boolean getHasGameID(){
		return this.hasGameID;
	}
	public void setGameID(long gameID){
		this.gameID = gameID;
		this.hasGameID = true;
	}
	
	public long getGameID(){
		return this.gameID;
	}
	
	public void setHomeTeam(String homeTeam){
		this.homeTeam = homeTeam;
	}
	
	public String getHomeTeam(){
		return this.homeTeam;
	}
	
	public void setAwayTeam(String awayTeam){
		this.awayTeam = awayTeam;
	}
	
	public String getAwayTeam(){
		return this.awayTeam;
	}
	
	public void setGameDate(int gameDate){
		this.gameDate = gameDate;
	}
	
	public int getGameDate(){
		return this.gameDate;
	}
	
	/**
	 * 
	 * @param startupTime QoS message startupTime parameter.
	 */
	public void setStartupTime(int startupTime){
		this.startupTime = startupTime;
	}
	
	public int getStartupTime(){
		return this.startupTime;
	}
	
	/**
	 * 
	 * @param updateInterval QoS message updateInterval parameter.
	 */
	public void setUpdateInterval(int updateInterval){
		this.updateInterval = updateInterval;
	}
	
	public int getUpdateInterval(){
		return this.updateInterval;
	}
	
	/**
	 * 
	 * @param bitrate QoS message bitrate parameter.
	 */
	public void setBitrate(int bitrate){
		this.bitrate = bitrate;
	}
	
	public int getBitrate(){
		return this.bitrate;
	}
	
	/**
	 * 
	 * @param switchMethod One of values defined in com.neulion.qos.common.message.BitrateSwitchMethod, corresponding to QoS switchMethod parameter.
	 */
	public void setBitrateSwitchMethod(short switchMethod){
		this.bitrateSwitchMethod = switchMethod;
	}
	
	public short getBitrateSwitchMethod(){
		return this.bitrateSwitchMethod;
	}
	
	/**
	 * 
	 * @param bandwidth QoS message bandwidth parameter.
	 */
	public void setBandwidth(int bandwidth){
		this.bandwidth = bandwidth;
	}
	
	public int getBandwidth(){
		return this.bandwidth;
	}
	
	/**
	 * 
	 * @param dropFrames QoS message dropFrameCount parameter.
	 */
	public void setDropFrameCount(int dropFrames){
		this.dropFrames = dropFrames;
	}
	
	public int getDropFrameCount(){
		return this.dropFrames;
	}
	
	/**
	 * 
	 * @param bufferLength QoS message bufferLength parameter.
	 */
	public void setBufferLength(int bufferLength){
		this.bufferLength = bufferLength;
	}
	
	public int getBufferLength(){
		return this.bufferLength;
	}
	
	/**
	 * 
	 * @param bufferTimes QoS message bufferTime parameter array.
	 */
	public void setBufferTimes(int[] bufferTimes){
		this.bufferTimes = bufferTimes;
	}
	
	public int[] getBufferTimes(){
		return this.bufferTimes;
	}
	
	/**
	 * 
	 * @param cdnNames QoS message cdnName parameter array.
	 */
	public void setCDNNames(String[] cdnNames){
		this.cdnNames = cdnNames;
	}
	
	public String[] getCDNNames(){
		return this.cdnNames;
	}
	
	/**
	 * 
	 * @param bytesLoaded QoS message bytesLoaded parameter array.
	 */
	public void setCDNBytesLoaded(long[] bytesLoaded){
		this.cdnBytesLoaded = bytesLoaded;
	}
	
	public long[] getCDNBytesLoaded(){
		return this.cdnBytesLoaded;
	}
	
	/**
	 * 
	 * @param bytesDelta QoS message bytesDelta parameter array.
	 */
	public void setCDNBytesDelta(int[] bytesDelta){
		this.cdnBytesDelta = bytesDelta;
	}
	
	public int[] getCDNBytesDelta(){
		return this.cdnBytesDelta;
	}
	
	/**
	 * 
	 * @param cityName City name of IP.
	 */
	public void setCityName(String cityName){
		this.cityName = cityName;
	}
	
	public String getCityName(){
		return this.cityName;
	}
	
	/**
	 * 
	 * @param cityID Hash code of city name.
	 */
	public void setCityID(int cityID){
		this.cityID = cityID;
	}
	
	public int getCityID(){
		return this.cityID;
	}
	
	/**
	 * 
	 * @param regionName Region name of IP.
	 */
	public void setRegionName(String regionName){
		this.regionName = regionName;
	}
	
	public String getRegionName(){
		return this.regionName;
	}
	
	/**
	 * 
	 * @param regionID Hash code of region name.
	 */
	public void setRegionID(int regionID){
		this.regionID = regionID;
	}
	
	public int getRegionID(){
		return this.regionID;
	}
	
	/**
	 * 
	 * @param countryName Country of IP.
	 */
	public void setCountryName(String countryName){
		this.countryName = countryName;
	}
	
	public String getCountryName(){
		return this.countryName;
	}
	
	/**
	 * 
	 * @param countryID Hash code of country name.
	 */
	public void setCountryID(int countryID){
		this.countryID = countryID;
	}
	
	public int getCountryID(){
		return this.countryID;
	}
	
	/**
	 * 
	 * @param windowMode QoS message window mode parameter.
	 */
	public void setWindowMode(String windowMode){
		this.windowMode = windowMode;
	}
	
	public String getWindowMode(){
		return this.windowMode;
	}
	
	/**
	 * 
	 * @param os QoS message os parameter.
	 */
	public void setOS(String os){
		this.os = os;
	}
	
	public String getOS(){
		return this.os;
	}
	
	/**
	 * 
	 * @param playerVersion QoS message player parameter.
	 */
	public void setPlayerVersion(String playerVersion){
		this.playerVersion = playerVersion;
	}
	
	public String getPlayerVersion(){
		return this.playerVersion;
	}
	
	/**
	 * 
	 * @param browserVersion QoS message browserVersion parameter.
	 */
	public void setBrowserVersion(String browserVersion){
		this.browserVersion = browserVersion;
	}
	
	public String getBrowserVersion(){
		return this.browserVersion;
	}
	
	/**
	 * 
	 * @param deviceType QoS message deviceType parameter.
	 */
	public void setDeviceType(String deviceType){
		this.deviceType = deviceType;
	}
	
	public String getDeviceType(){
		return this.deviceType;
	}
	
	/**
	 * 
	 * @param networkType QoS message networkType parameter.
	 */
	public void setNetworkType(String networkType){
		this.networkType = networkType;
	}
	
	public String getNetworkType(){
		return this.networkType;
	}
	
	/**
	 * @param appType QoS message appType paramter.
	 */
	public void setAppType(String appType){
		this.appType = appType;
	}
	
	public String getAppType(){
		return this.appType;
	}
	
	public void setAppTypeID(int appTypeID){
		this.appTypeID = appTypeID;
	}
	
	public int getAppTypeID(){
		return this.appTypeID;
	}
	
	/**
	 * 
	 * @param tagNames QoS message customized tag names.
	 */
	public void setTagNames(String[] tagNames){
		this.tagNames = tagNames;
	}
	
	public String[] getTagNames(){
		return this.tagNames;
	}
	
	public void setTagValues(String[] tagValues){
		this.tagValues = tagValues;
	}
	
	public String[] getTagValues(){
		return this.tagValues;
	}
	
	public void setYear(int year){
		this.bandwidth = year;
	}
	
	public int getYear(){
		return this.bandwidth;
	}
	
	public void setMonth(int month){
		this.bitrate = month;
	}
	
	public int getMonth(){
		return this.bitrate;
	}
	
	public void setDay(int day){
		this.bufferLength = day;
	}
	
	public int getDay(){
		return this.bufferLength;
	}
	
	public void setHour(int hour){
		this.countryID = hour;
	}
	
	public int getHour(){
		return this.countryID;
	}
	
	public void setMinute(int minute){
		this.cityID = minute;
	}
	
	public int getMinute(){
		return this.cityID;
	}
	
	public void setSecond(int second){
		this.clientIDHashCode = second;
	}
	
	public int getSecond(){
		return this.clientIDHashCode;
	}
	
	public void setUTC(long utc){
		this.playheadTime = utc;
	}
	
	public long getUTC(){
		return this.playheadTime;
	}

	public void setSecondID(long val){
		this.streamLength = val;
	}
	
	public long getSecondID(){
		return this.streamLength;
	}
	
	/**
	 * Override function to read a MessageObject from ObjectInput.
	 */
	public void readExternal(ObjectInput arg0) throws IOException,
			ClassNotFoundException {
		int i=0;
		
		this.siteIDHashCode = arg0.readInt();
		this.viewIDHashCode = arg0.readInt();
		this.clientIDHashCode = arg0.readInt();
		this.viewID = arg0.readUTF();
		this.msgID = arg0.readInt();
		this.msgType = arg0.readShort();
		if(this.msgType == MessageType.ERROR){
			this.errorCode = arg0.readInt();
		}
		this.ipAddress = arg0.readUTF();
		this.userID = arg0.readUTF();

		this.playheadTime = arg0.readLong();
		this.streamType = arg0.readShort();
		this.streamURL = arg0.readUTF();
		this.streamDescritpion = arg0.readUTF();
		this.streamDescritpion = new String(this.streamDescritpion.getBytes("ISO-8859-1"),"UTF-8");
		this.streamID = arg0.readInt();
		this.streamLength = arg0.readLong();
		this.hasProductID = arg0.readBoolean();
		if(this.hasProductID){
			this.productID = arg0.readUTF();
		}
		this.hasProgramID = arg0.readBoolean();
		if(this.hasProgramID){
			this.programID = arg0.readLong();
		}
		this.hasProgramType = arg0.readBoolean();
		if(this.hasProgramType){
			this.programType = arg0.readUTF();
		}
		this.hasGameID = arg0.readBoolean();
		if(this.hasGameID){
			this.gameID = arg0.readLong();
		}
		this.homeTeam = arg0.readUTF();
		this.homeTeam = new String(this.homeTeam.getBytes("ISO-8859-1"),"UTF-8");
		this.awayTeam = arg0.readUTF();
		this.awayTeam = new String(this.awayTeam.getBytes("ISO-8859-1"),"UTF-8");
		this.gameDate = arg0.readInt();
		
		this.startupTime = arg0.readInt();
		this.updateInterval = arg0.readInt();
		this.bitrate = arg0.readInt();
		this.bitrateSwitchMethod = arg0.readShort();
		this.bandwidth = arg0.readInt();
		this.dropFrames = arg0.readInt();
		this.bufferLength = arg0.readInt();
		this.numBuffer = arg0.readInt();
		if(numBuffer > 0){
			this.bufferTimes = new int[numBuffer];
			for(i=0; i<numBuffer; ++i){
				this.bufferTimes[i] = arg0.readInt();
			}
		}	
		
		int numCDN = arg0.readInt();
		if(numCDN > 0){
			this.cdnNames = new String[numCDN];
			this.cdnBytesLoaded = new long[numCDN];
			this.cdnBytesDelta = new int[numCDN];
			
			for(i=0; i<numCDN; ++i){
				cdnNames[i] = arg0.readUTF();
				cdnBytesLoaded[i] = arg0.readLong();
				cdnBytesDelta[i] = arg0.readInt();
			}
		}
		
		this.cityID = arg0.readInt();
		this.regionID = arg0.readInt();
		this.countryID = arg0.readInt();
		this.cityName = arg0.readUTF();
		this.regionName = arg0.readUTF();
		this.countryName = arg0.readUTF();

		this.windowMode = arg0.readUTF();
		this.os = arg0.readUTF();
		this.playerVersion = arg0.readUTF();
		this.browserVersion = arg0.readUTF();
		this.deviceType = arg0.readUTF();
		this.networkType = arg0.readUTF();
			
		this.appType = arg0.readUTF();
		this.appTypeID = arg0.readInt();
		
		int numTags = arg0.readInt();
		if(numTags > 0){
			this.tagNames = new String[numTags];
			this.tagValues = new String[numTags];
			
			for(i=0; i<numTags; ++i){
				tagNames[i] = arg0.readUTF();
				tagValues[i] = arg0.readUTF();
			}
		}
	}

	/**
	 * Override function to write a MessageObject to ObjectOutput.
	 */
	public void writeExternal(ObjectOutput arg0) throws IOException {
		int i=0;		
			
		arg0.writeInt(this.siteIDHashCode);
		arg0.writeInt(this.viewIDHashCode);
		arg0.writeInt(this.clientIDHashCode);
		arg0.writeUTF(this.viewID);
		arg0.writeInt(msgID);
		arg0.writeShort(msgType);
		if(this.msgType == MessageType.ERROR){
			arg0.writeInt(errorCode);
		}
		arg0.writeUTF(ipAddress);
		arg0.writeUTF(userID);

		arg0.writeLong(playheadTime);
		arg0.writeShort(streamType);
		arg0.writeUTF(streamURL);
		
		arg0.writeUTF(streamDescritpion);

		int streamID = (this.streamType+this.streamDescritpion+this.streamURL+this.productID).hashCode();
		arg0.writeInt(streamID);
		arg0.writeLong(streamLength);
		arg0.writeBoolean(this.hasProductID);
		if(this.hasProductID == true){
			arg0.writeUTF(productID);
		}
		arg0.writeBoolean(this.hasProgramID);
		if(this.hasProgramID == true){
			arg0.writeLong(this.programID);
		}
		arg0.writeBoolean(this.hasProgramType);
		if(this.hasProgramType == true){
			arg0.writeUTF(this.programType);
		}
		arg0.writeBoolean(this.hasGameID);
		if(this.hasGameID == true){
			arg0.writeLong(this.gameID);
		}
		arg0.writeUTF(this.homeTeam);
		arg0.writeUTF(this.awayTeam);
		arg0.writeInt(this.gameDate);	
		
		arg0.writeInt(startupTime);
		arg0.writeInt(updateInterval);
		arg0.writeInt(bitrate);
		arg0.writeShort(bitrateSwitchMethod);
		arg0.writeInt(bandwidth);
		arg0.writeInt(dropFrames);
		arg0.writeInt(bufferLength);
		arg0.writeInt(bufferTimes.length);
		if(bufferTimes.length > 0){
			for(i=0; i<bufferTimes.length; ++i){
				arg0.writeInt(bufferTimes[i]);
			}			
		}	
		
		arg0.writeInt(cdnNames.length);
		if(cdnNames.length > 0){
			for(i=0; i<cdnNames.length; ++i){
				arg0.writeUTF(cdnNames[i]);
				arg0.writeLong(cdnBytesLoaded[i]);
				arg0.writeInt(cdnBytesDelta[i]);
			}			
		}	
		
		arg0.writeInt(cityID);
		arg0.writeInt(regionID);
		arg0.writeInt(countryID);
		arg0.writeUTF(cityName);
		arg0.writeUTF(regionName);
		arg0.writeUTF(countryName);		

		arg0.writeUTF(windowMode);
		arg0.writeUTF(os);
		arg0.writeUTF(playerVersion);
		arg0.writeUTF(browserVersion);
		arg0.writeUTF(deviceType);
		arg0.writeUTF(networkType);
		
		arg0.writeUTF(this.appType);
		arg0.writeInt(this.appTypeID);		
		
		arg0.writeInt(tagNames.length);
		if(tagNames.length > 0){
			for(i=0; i<tagNames.length; ++i){
				arg0.writeUTF(tagNames[i]);
				arg0.writeUTF(tagValues[i]);
			}			
		}
	}
	
	public void reset(){
	    siteID = MessageObject.emptyString;
	    siteIDHashCode = emptyStringHash;
		msgID = -1;
		viewID = MessageObject.emptyString;
		viewIDHashCode = emptyStringHash;
		clientIDHashCode = emptyStringHash;
		errorCode = -1;
		ipAddress = MessageObject.emptyString;	
		userID = MessageObject.emptyString;
		playheadTime = 0;		
		
		streamURL = MessageObject.emptyString;
		streamDescritpion = MessageObject.emptyString;
		streamID = 0;
		streamLength = 0;
		hasProductID = false;
		productID = MessageObject.emptyString;
		hasProgramID = false;
		programID = 0;
		hasProgramType = false;
		programType = MessageObject.emptyString;
		hasGameID = false;
		gameID = 0;
		homeTeam = MessageObject.emptyString;
		awayTeam = MessageObject.emptyString;
		gameDate = 0;
		
		startupTime = 0;
		updateInterval = 0;
		bitrate = 0;
		bitrateSwitchMethod = BitrateSwitchMethod.ADAPTIVE;
		bandwidth = 0;
		dropFrames = 0;
		bufferLength = 0;
		numBuffer = 0;
		bufferTimes = MessageObject.emptyIntArray;
		
		cdnNames = MessageObject.emptyStringArray;
		cdnBytesLoaded = MessageObject.emptyLongArray;
		cdnBytesDelta = MessageObject.emptyIntArray;

		cityID = 0;
		regionID = 0;
		countryID = 0;
		cityName = MessageObject.emptyString;
		regionName = MessageObject.emptyString;
		countryName = MessageObject.emptyString;
		
		os = emptyString;
		playerVersion = MessageObject.emptyString;
		browserVersion = MessageObject.emptyString;
		deviceType = MessageObject.pcDeviceType;
		networkType = MessageObject.emptyString;
		
		tagNames = MessageObject.emptyStringArray;
		tagValues = MessageObject.emptyStringArray;	
	}
	
	private static final String emptyString = "";
	private static final int emptyStringHash = emptyString.hashCode();
	private static final String defaultWindowMode = "default";
	private static final String pcDeviceType = "pc";
	private static final String defaultAppType = "unidentified";
	@SuppressWarnings("unused")
    private static final String defaultNetworkType = "default";
	private static final String[] emptyStringArray = {};
	private static final int[] emptyIntArray = {};
	private static final long[] emptyLongArray = {};

	//------------Object Pool-----------------------------------
	private static boolean OBJECT_POOL_ENABLED = true;
	private static int INIT_POOL_SIZE = 500;
	private static int MAX_POOL_SIZE  = 5000;
	
	private static int min_pool_size = Integer.MAX_VALUE;
	private static long request_message_count = 0;
	private static long reclaim_message_count = 0;
	private static long reclaim_call_count = 0;
	private static long pool_hit_count = 0;
	private static long pool_fail_count = 0;
	private static long pool_drop_count = 0;
	
	private static LinkedList<MessageObject> pool = new LinkedList<MessageObject>();
	
	static{
		if(OBJECT_POOL_ENABLED){
			initPool();
		}
	}
	
	/**
	 *
	 * @return A not-null MessageObject instance.
	 */
	public static synchronized MessageObject getMessageObject(){
		
		MessageObject msg = null;
		if(OBJECT_POOL_ENABLED && !pool.isEmpty()){
			if(pool.size() < min_pool_size){
				min_pool_size = pool.size();
			}

			msg = pool.pollFirst();
			msg.reset();
		}else{
			
			msg = new MessageObject();
		}
		
		return msg;
	}
	
	/**
	 * Reclaim a MessageObject instance.
	 * @param msg
	 */
	public static synchronized void reclaimMessageObject(MessageObject msg){
//		if(DEBUG_SWITCH.IS_DEBUG){
//			System.out.println("MessageObject.reclaimMessageObject:" + msg.getViewID());
	}
	
	/**
	 * Set whether or not enable MessageObject instance pool.
	 * @param enable
	 */
	public static void setObjectPoolEnabled(boolean enable){
		if(enable != OBJECT_POOL_ENABLED){
			if(enable){
				initPool();
			}else{
				clearPool();
			}
			
			OBJECT_POOL_ENABLED = enable;
		}
	}
	
	/**
	 * 
	 * @return Current object pool size.
	 */
	public static int getObjectPoolSize(){
		return pool.size();
	}
	
	public static int getObjectPoolMinSize(){
		return min_pool_size;
	}
	
	private synchronized static void initPool(){
		for(int i=0; i<INIT_POOL_SIZE; ++i){
			pool.add(new MessageObject());
		}		
	}
	
	private synchronized static void clearPool(){
		pool.clear();
	}	
	
	public static long getRequestObjectCount(){
		return request_message_count;
	}
	
	public static long getReclaimObjectCount(){
		return reclaim_message_count;
	}
	
	public static long getPoolHitCount(){
		return pool_hit_count;
	}
	
	public static long getPoolFailCount(){
		return pool_fail_count;
	}
	
	public static long getPoolDropCount(){
		return pool_drop_count;
	}
	
	public static long getReclaimCallCount(){
		return reclaim_call_count;
	}
}
