/*
 * Copyright © 1997 - 1999 IBM Corporation.
 * 
 * Redistribution and use in source (source code) and binary (object code)
 * forms, with or without modification, are permitted provided that the
 * following conditions are met:
 * 1. Redistributed source code must retain the above copyright notice, this
 * list of conditions and the disclaimer below.
 * 2. Redistributed object code must reproduce the above copyright notice,
 * this list of conditions and the disclaimer below in the documentation
 * and/or other materials provided with the distribution.
 * 3. The name of IBM may not be used to endorse or promote products derived
 * from this software or in any other form without specific prior written
 * permission from IBM.
 * 4. Redistribution of any modified code must be labeled "Code derived from
 * the original OpenCard Framework".
 * 
 * THIS SOFTWARE IS PROVIDED BY IBM "AS IS" FREE OF CHARGE. IBM SHALL NOT BE
 * LIABLE FOR INFRINGEMENTS OF THIRD PARTIES RIGHTS BASED ON THIS SOFTWARE.  ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IBM DOES NOT WARRANT THAT THE FUNCTIONS CONTAINED IN THIS
 * SOFTWARE WILL MEET THE USER'S REQUIREMENTS OR THAT THE OPERATION OF IT WILL
 * BE UNINTERRUPTED OR ERROR-FREE.  IN NO EVENT, UNLESS REQUIRED BY APPLICABLE
 * LAW, SHALL IBM BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  ALSO, IBM IS UNDER NO OBLIGATION
 * TO MAINTAIN, CORRECT, UPDATE, CHANGE, MODIFY, OR OTHERWISE SUPPORT THIS
 * SOFTWARE.
 */
package opencard.core.util;

import java.util.Properties;

/**
 * Singleton that provides access to protected system resources 
 * like file I/O, properties, native code.
 * Thread safe: each thread can only access its own SystemAccess class.
 * <br>
 * This is the default SystemAccess class used by OpenCard.
 * To run OpenCard under a browser install the browser specific
 * SystemAccess class in the applets init() method as follows:
 * <br>
 * opencard.core.util.SystemAccess sys = 
 *    opencard.opt.vendorX.VendorXSystemAccess();
 * opencard.core.util.SystemAccess.setSystemAccess(sys);
 *
 * @author Peter Bendel (peter_bendel@de.bm.com)
 *
 * @version $Id: SystemAccess.java,v 1.1.1.1 1999/10/05 15:34:32 damke Exp $
 *
 * @see opencard.opt.ms.MicrosoftSystemAccess
 * @see opencard.opt.netscape.NetscapeSystemAccess
 */
public class SystemAccess {
  private static SystemAccess _theSystem = new SystemAccess();
  private static java.util.Hashtable<Thread, SystemAccess> _registeredSystems = new java.util.Hashtable<Thread, SystemAccess>();

  /**
   * Access system properties
   */
  public boolean getBoolean(String key) {
    //System.out.println("using SystemAccess.getBoolean()");
    return Boolean.getBoolean(key);
  }

  /**
   * Access system properties
   */
  public Properties getProperties() {
    //System.out.println("using SystemAccess.getProperties()");
    return System.getProperties();
  }

  /**
   * Access system properties
   */
  public String getProperty(String key) {
    //System.out.println("using SystemAccess.getProperty()");
    return System.getProperty(key);
  }

  /**
   * Access system properties
   */
  public String getProperty(String key, String def) {
    //System.out.println("using SystemAccess.getProperty()");
    return System.getProperty(key,def);
  }

  /**
   * Return the instance of SystemAccess associated with the current thread.
   * If the current thread has not set its own SystemAccess instance return the default system access instance.
   */
  public static SystemAccess getSystemAccess() {
    SystemAccess sys = (SystemAccess)_registeredSystems.get(Thread.currentThread());
    if (sys==null)
      return _theSystem;
    else return sys;
  }

  /**
   * Link to a native DLL.
   */
  public void loadLibrary(String libName) {
    //System.out.println("using SystemAccess.loadLibrary()");
    String arch = System.getProperty("os.arch");
    System.loadLibrary(libName + "-" + arch);
  }

  /**
   * Access system properties
   */
  public Properties loadProperties(String filename) throws java.io.FileNotFoundException, java.io.IOException{
    //System.out.println("using SystemAccess.loadProperties()");
    if(filename == null)
      filename = getClass().getResource("/opencard.properties").getPath();
    Properties props = new Properties ();
    if(getClass().getResourceAsStream(filename) != null)
      props.load ( getClass().getResourceAsStream(filename) );
    else
      props.load(new java.io.FileInputStream(new java.io.File(filename)));
    return props;
  }

  /**
   * Access system properties
   */
  public Properties loadResourceProperties(String filename) throws java.io.FileNotFoundException, java.io.IOException{
    //System.out.println("using SystemAccess.loadProperties()");
    Properties props = new Properties ();
    return props;
  }

  /**
   * Set the SystemAccess instance for the current thread.
   * This allows a thread to set a browser specific object that requests privileges from the browser VM
   * by using proprietary mechanisms (e.g. netscape.security.PrivilegeManager.enablePrivilege() )
   */
  public static void setSystemAccess(SystemAccess newSystemAccess) {
    _registeredSystems.put(Thread.currentThread(), newSystemAccess);
  }
}