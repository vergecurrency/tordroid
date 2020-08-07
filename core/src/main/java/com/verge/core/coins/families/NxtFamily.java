package com.verge.core.coins.families;

import com.verge.core.coins.CoinType;
import com.verge.core.exceptions.AddressMalformedException;
import com.verge.core.messages.MessageFactory;
import com.verge.core.wallet.AbstractAddress;
import com.verge.core.wallet.families.nxt.NxtAddress;
import com.verge.core.wallet.families.nxt.NxtTxMessage;

import javax.annotation.Nullable;


/**
 * @author John L. Jegutanis
 *
 * Coins that belong to this family are: NXT, Burst, etc
 */
public abstract class NxtFamily extends CoinType {
    public static final short DEFAULT_DEADLINE = 1440;

    {
        family = Families.NXT;
    }

    @Override
    public AbstractAddress newAddress(String addressStr) throws AddressMalformedException {
        return NxtAddress.fromString(this, addressStr);
    }

    @Override
    public boolean canHandleMessages() {
        return true;
    }

    @Override
    @Nullable
    public MessageFactory getMessagesFactory() {
        return NxtTxMessage.getFactory();
    }
}