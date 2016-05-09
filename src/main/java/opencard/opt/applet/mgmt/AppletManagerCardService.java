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



package opencard.opt.applet.mgmt;

import opencard.core.service.CardServiceException;
import opencard.core.terminal.CardTerminalException;
import opencard.opt.applet.AppletID;
import opencard.opt.applet.AppletInfo;
import opencard.opt.security.SecureService;

/**
 * The <tt>AppletManagerCardService</tt> interface defines a set
 * of calls for the maintenance of multiple applets on a card.
 * This includes creating, registering and deleting applets on a
 * multi-applicative smartcard.<p>
 * <br>
 * This interface is typically implemented for card technology handling
 * multi-applet management different (e.g. EMV compliant cards,
 * JavaCards, ...)<p>
 * <p>
 *
 * @author   Reto Hermann (rhe@zurich.ibm.com)
 * @author   Thomas Stober (tms@de.ibm.com)
 * @author   Christophe.Muller@research.gemplus.com
 * @version  $Id: AppletManagerCardService.java,v 1.1 1999/11/02 18:36:56 damke Exp $
 * @since    OCF1.2
 *
 * @see opencard.opt.applet.AppletInfo
 * @see opencard.opt.applet.AppletID
 * @see opencard.opt.applet.mgmt.AppletCode
 */

public interface AppletManagerCardService
    extends AppletAccessCardService, SecureService {

    /**
     *Install an applet on the smart card.<p>
     *
     * @param    appletCode
     *           The <tt>AppletCode</tt> representing the applet
     *           to be installed.
     */
    public AppletInfo installApplet(AppletCode appletCode)
	throws CardServiceException, CardTerminalException;

    /**
     * Register an applet on the smart card.<p>
     *
     * @param    appletID
     *           An <tt>AppletID</tt> representing the applet to be registered.
     * @exception opencard.core.service.CardServiceException
     *            Thrown when error occurs during execution of the operation.
     */
    public AppletInfo registerApplet(AppletID appletID)
	throws CardServiceException, CardTerminalException;

    /**
     * Remove an applet from the smart card.<p>
     *
     * @param    appletID
     *           The <tt>AppletID</tt> object referring to the applet
     *           to be removed.
     * @exception opencard.core.service.CardServiceException
     *            Thrown when error occurs during execution of the operation.
     */
    public AppletInfo removeApplet(AppletID appletID)
	throws CardServiceException, CardTerminalException;

}
