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


package opencard.core.terminal;

/** A <tt>CardTerminalFactory</tt> produces <tt>CardTerminal</tt> objects
 * of a certain <tt>type</tt> attached at an <tt>address</tt>.<p>
 *
 * As with the <tt>opencard.core.service.CardServiceFactory</tt> the idea here is that a
 * card terminal manufacturer shall provide his own version of a
 * <tt>CardTerminalFactory</tt> that can produce appropriate <tt>CardTerminal</tt>
 * objects.<p>
 *
 * @author  Dirk Husemann       (hud@zurich.ibm.com)
 * @author  Stephan Breideneich (sbreiden@de.ibm.com)
 * @version $Id: CardTerminalFactory.java,v 1.1.1.1 1999/10/05 15:34:31 damke Exp $
 *
 * @see opencard.core.terminal.CardTerminal
 * @see opencard.core.terminal.CardTerminalRegistry
 */
public interface CardTerminalFactory {

  /** first element in terminal configuration array */
  public final int TERMINAL_NAME_ENTRY      = 0;

  /** second element in terminal configuration array */
  public final int TERMINAL_TYPE_ENTRY      = 1;

  /** third element in terminal configuration array */
  public final int TERMINAL_ADDRESS_ENTRY   = 2;

  /** create a specific <tt>CardTerminal</tt> object that knows how to handle
   *  a specific card terminal and register it to the CardTerminalRegistry.
   *  @param     ctr
   *		         the CardTerminalRegistry for registration-process
   *  @param     terminalInfo
   *		         the parameter array for the terminal.
   *             {TerminalName, TerminalType, factory-specific count of parameters....}.
   *  @exception OpenCardInitializationException
   *		         thrown when initialization error occured
   *  @exception CardTerminalException
   *		         thrown when CardTerminal error occured
   *  @exception TerminalInitException
   *             thrown when terminalInfo is incorrect or factory not able to
   *             support requested terminal type.
   */
  public void createCardTerminals(CardTerminalRegistry ctr, String[] terminalInfo)
    throws CardTerminalException,
           TerminalInitException;

  /** initialize the CardTerminalFactory
   *  @exception CardTerminalException
   *             thrown when error occurred while <tt>open</tt> initializes the factory.
   */
  public void open()
    throws CardTerminalException;

  /** deinitialize the CardTerminalFactory
   *  @exception CardTerminalException
   *             thrown when error occurred while close deinitializes the factory.
   */
  public void close()
    throws CardTerminalException;
}
