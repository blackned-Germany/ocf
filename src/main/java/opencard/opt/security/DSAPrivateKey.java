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

package opencard.opt.security;

import java.math.BigInteger;

/** Contains a public DSA key.
  * Implements the java.security.interfaces.DSAPrivateKey interface
  * CardServices using PKA algorithms like SignatureCardService require
  * keys implementing the PrivateKey/PublicKey interface.
  * In this package OpenCard provides key classes for common algorithms
  * like RSA, DSA (or DES) that each concrete card service implementing
  * a card service interface should support instead of defining its own
  * key classes.
  * Only for new PKA algorithms that OpenCard does not yet support a
  * card service may define its own key classes.
  *
  * @author  Peter Bendel (peter_bendel@de.ibm.com)
  * @version $Id: DSAPrivateKey.java,v 1.1.1.1 1999/10/05 15:08:48 damke Exp $
  *
  * @see java.security.interfaces.DSAPrivateKey
  */
public class DSAPrivateKey implements java.security.interfaces.DSAPrivateKey {
 java.security.interfaces.DSAParams param = null;
 /** private key */
 BigInteger x=null;

  /** Produce an <tt>DSAPrivateKey</tt> from the given BigIntegers.<p>
   *
   * @param    q subprime
   * @param    p prime
   * @param    g base
   * @param    x private key
   */
  public DSAPrivateKey(BigInteger p, BigInteger q, BigInteger g, BigInteger x) {
    this.param=new DSAParams(p, q, g);
    this.x=x;
  }

  /** Conformance to the java.security interface
   * @see java.security.PublicKey
   */
  public String getAlgorithm() {
    return ("DSA");
  }

  /** Conformance to the java.security interface
   * @see java.security.PublicKey
   */
  public byte[] getEncoded() {
    return null;
  }

  /** Conformance to the java.security interface
   * @see java.security.PublicKey
   */
  public String getFormat() {
    return null;
  }

  /**
   * Returns the DSA-specific key parameters. These parameters are
   * never secret.
   * @return the DSA-specific key parameters.
   * @see DSAParams
   */
  public java.security.interfaces.DSAParams getParams(){
    return param;
  }

  /**
   * Returns the value of the private key, <code>x</code>.
   *
   * @return the value of the private key, <code>x</code>.
   */
  public BigInteger getX(){
    return x;
  }
}