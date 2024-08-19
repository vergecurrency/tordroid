package com.vergepay.core.coins.families;

import com.vergepay.core.messages.MessageFactory;
import com.vergepay.core.wallet.families.vpncoin.VpncoinTxMessage;

import javax.annotation.Nullable;

/**
 * @author John L. Jegutanis
 * <p>
 * This family contains Vpncoin
 */
public abstract class VpncoinFamily extends BitFamily {
    {
        family = Families.VPNCOIN;
    }

    @Override
    @Nullable
    public MessageFactory getMessagesFactory() {
        return VpncoinTxMessage.getFactory();
    }
}