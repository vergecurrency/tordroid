package com.vergeandroid.core.coins.families;

import com.vergeandroid.core.coins.CoinType;
import com.vergeandroid.core.wallet.AbstractAddress;
import com.vergeandroid.core.exceptions.AddressMalformedException;
import com.vergeandroid.core.wallet.families.bitcoin.BitAddress;

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
