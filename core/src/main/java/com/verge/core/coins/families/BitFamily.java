package com.vergepay.core.coins.families;

import com.vergepay.core.coins.CoinType;
import com.vergepay.core.wallet.AbstractAddress;
import com.vergepay.core.exceptions.AddressMalformedException;
import com.vergepay.core.wallet.families.bitcoin.BitAddress;

import org.bitcoinj.core.AddressFormatException;

/**
 * @author John L. Jegutanis
 *
 * This is the classical Bitcoin family that includes Litecoin, Dogecoin, Dash, etc
 */
public abstract class BitFamily extends CoinType {
    {
        family = Families.BITCOIN;
    }

    @Override
    public BitAddress newAddress(String addressStr) throws AddressMalformedException {
        return BitAddress.from(this, addressStr);
    }
}
