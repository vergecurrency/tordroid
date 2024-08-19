package com.vergepay.core.coins.families;

import com.vergepay.core.messages.MessageFactory;
import com.vergepay.core.wallet.families.clams.ClamsTxMessage;

import javax.annotation.Nullable;

/**
 * @author John L. Jegutanis
 * <p>
 * This family contains Clams
 */
public class ClamsFamily extends BitFamily {
    {
        family = Families.CLAMS;
    }

    @Override
    @Nullable
    public MessageFactory getMessagesFactory() {
        return ClamsTxMessage.getFactory();
    }
}
