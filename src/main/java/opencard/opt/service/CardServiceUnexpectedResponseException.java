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

package opencard.opt.service;


import opencard.core.service.CardServiceImplementationException;


/**
 * Exception indicating that a smartcard's response cannot be interpreted.
 * This may be the result of a bug in the card service, or of a mismatch
 * between the smartcard and the service that was used to access it. It may
 * also be the result of a limited implementation, for example one that
 * does not bother to check for the different reasons of a failure.
 * <br>
 * If the response from the smartcard does not indicate success,
 * but is an error response recognized by the service, a
 * <tt>CardServiceOperationFailedException</tt> should be thrown instead.
 *
 * @author  Thomas Schaeck (schaeck@de.ibm.com)
 *
 * @version $Id: CardServiceUnexpectedResponseException.java,v 1.1.1.1 1999/10/05 15:08:48 damke Exp $
 *
 * @see opencard.core.service.CardServiceOperationFailedException
 */
public class CardServiceUnexpectedResponseException
    extends CardServiceImplementationException
{
  /**
   * Creates a new exception without detail message.
   */
  public CardServiceUnexpectedResponseException()
  {
    super();
  }

  /**
   * Creates a new exception with the given detail message.
   *
   * @param message   a string indicating why this exception is thrown
   */
  public CardServiceUnexpectedResponseException(String message)
  {
    super(message);
  }

} // class CardServiceUnexpectedResponseException
