package com.vergepay.core.util;

import static com.vergepay.core.Preconditions.checkArgument;

import com.vergepay.core.exceptions.AddressMalformedException;
import com.vergepay.core.wallet.AbstractAddress;
import com.vergepay.core.wallet.families.bitcoin.BitAddress;

import org.bitcoinj.script.Script;

/**
 * @author John L. Jegutanis
 */
public class BitAddressUtils {
    public static boolean isP2SHAddress(AbstractAddress address) {
        checkArgument(address instanceof BitAddress, "This address cannot be a P2SH address");
        return ((BitAddress) address).isP2SHAddress();
    }

    public static byte[] getHash160(AbstractAddress address) {
        checkArgument(address instanceof BitAddress, "Cannot get hash160 from this address");
        return ((BitAddress) address).getHash160();
    }

    public static boolean producesAddress(Script script, AbstractAddress address) {
        try {
            return BitAddress.from(address.getType(), script).equals(address);
        } catch (AddressMalformedException e) {
            return false;
        }
    }
}
