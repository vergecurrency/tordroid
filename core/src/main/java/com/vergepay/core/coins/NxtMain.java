package com.vergepay.core.coins;

import com.vergepay.core.coins.families.NxtFamily;

/**
 * @author John L. Jegutanis
 */
public class NxtMain extends NxtFamily {

    private static final NxtMain instance = new NxtMain();

    private NxtMain() {
        id = "nxt.main";

        name = "NXT";
        symbol = "NXT";
        uriScheme = "nxt";
        bip44Index = 29;
        unitExponent = 8;
        addressPrefix = "NXT-";
        feeValue = oneCoin();
        minNonDust = value(1);
        feePolicy = FeePolicy.FLAT_FEE;
    }

    public static synchronized CoinType get() {
        return instance;
    }
}
