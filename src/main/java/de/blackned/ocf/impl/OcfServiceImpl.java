package de.blackned.ocf.impl;

import de.blackned.ocf.OcfService;
import de.cardcontact.opencard.service.smartcardhsm.SmartCardHSMCardService;
import opencard.core.service.CardRequest;
import opencard.core.service.CardServiceException;
import opencard.core.service.SmartCard;
import opencard.core.terminal.CardTerminal;
import opencard.core.terminal.CardTerminalException;
import opencard.opt.iso.fs.FileAccessCardService;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Security;

@Component
@Service(OcfService.class)
public class OcfServiceImpl implements OcfService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OcfService.class);

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Activate
    protected void activate() {
        try {
            SmartCard.start();
        } catch (Exception e) {
            LOGGER.error("Smart card could not be started.", e);
        }
    }

    @Deactivate
    protected void deactivate() {
        try {
            SmartCard.shutdown();
        } catch (Exception e) {
            LOGGER.error("Smart card could not be stopped.", e);
        }
    }

    public SmartCardHSMCardService getSmartCardHSMCardService() {
        try {
            CardRequest cardRequest = new CardRequest(CardRequest.ANYCARD, null, FileAccessCardService.class);
            SmartCard sc = SmartCard.waitForCard(cardRequest);
            Object service = sc.getCardService(SmartCardHSMCardService.class, true);
            if (service instanceof SmartCardHSMCardService) {
                return (SmartCardHSMCardService)service;
            }
        } catch (CardTerminalException | ClassNotFoundException | CardServiceException e) {
            LOGGER.error("Smart Card could not be read.", e);
        }
        return null;
    }
}
