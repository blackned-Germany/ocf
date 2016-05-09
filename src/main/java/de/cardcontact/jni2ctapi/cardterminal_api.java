/*
 *  ---------
 * |.**> <**.|  CardContact
 * |*       *|  Software & System Consulting
 * |*       *|  Minden, Germany
 * |'**> <**'|  Copyright (c) 2000-2014. All rights reserved
 *  --------- 
 *
 * See file LICENSE for details on licensing
 *
 * Abstract :       Implementation of a CTAPI Interface for Java.
 *
 * Author :         Frank Thater (FTH), Andreas Schwier (ASC)
 *
 * Last modified:   10/04/2014
 *
 *****************************************************************************/

package de.cardcontact.jni2ctapi;



/**
 * Native class that allows access to CT-API card terminals.
 * 
 * Development of the 64-Bit version for Windows was kindly sponsored by KV-IT (www.kv-it.de).
 */
public class cardterminal_api
{
	public final static int OK			= 0;
	public final static int ERR_INVALID	= -1;
	public final static int ERR_CT		= -8;
	public final static int ERR_TRANS	= -10;
	public final static int ERR_MEMORY	= -11;
	public final static int ERR_HOST	= -127;

	public final static int ICC1		= 0x00;
	public final static int CT			= 0x01;
	public final static int HOST		= 0x02;
	public final static int ICC2		= 0x02;
	public final static int REMOTE_HOST = 0x05;
	
	private long ctInitPointer;
	private long ctClosePointer;
	private long ctDataPointer;

	/**
	 * Initialize Host to Card Terminal connection.
	 * 
	 * @param ctn the logical card terminal number assigned by the caller and used in subsequent CT_Data and CT_Close calls
	 * @param pn the port number representing the physical port
	 * @return the return code
	 */
	public native int CT_Init(char ctn, char pn);

	/**
	 * Close Host to Card Terminal connection
	 * 
	 * @param ctn the logical card terminal number
	 * @return the return code
	 */
	public native int CT_Close(char ctn);

	/**
	 * Exchange an Application Protocol Data Unit (APDU) with the card terminal.
	 * 
	 * The API works like the native CT_Data API, with exception of the lenr parameter which in inbound only.
	 * The value for lenr returned by the CT-API device is passed as result of the method instead
	 * 
	 * @param ctn the logical card terminal number
	 * @param dad the destination address (ICC1, CT, ICC2...)
	 * @param sad the source address (usually HOST)
	 * @param lenc the number of bytes to be send from command. Must be less or equal command.length()
	 * @param command the outgoing command bytes
	 * @param lenr the number of bytes reserved in response. Must be less or equal response.length()
	 * @param response the buffer allocated to receive the response
	 * @return the number of bytes placed in response or one of the negative error codes
	 */
	public native int CT_Data(char ctn, byte dad, byte sad, int lenc, byte[] command, char lenr, byte[] response);

	// sets the name of the shared lib which holds the CTAPI references for a specific card terminal
	private native void setReader(String readername) throws UnsatisfiedLinkError;


	// get the native library
	static 	{
		String arch = System.getProperty("os.arch");
		System.loadLibrary("jni2ctapi-" + arch);
	}

	/**
	 * Create a CT-API access object for a given shared object / DLL
	 * 
	 * @param readername the shared object or DLL name
	 */
	public cardterminal_api(String drivername) {
		super();

		ctInitPointer = 0;
		ctClosePointer = 0;
		ctDataPointer = 0;

		setReader(System.mapLibraryName(drivername));
	}
}
