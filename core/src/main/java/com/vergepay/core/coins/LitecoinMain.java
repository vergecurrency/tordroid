package com.vergepay.core.coins;

import com.vergepay.core.coins.families.BitFamily;

/**
 * @author John L. Jegutanis
 */
public class LitecoinMain extends BitFamily {
    private static final LitecoinMain instance = new LitecoinMain();

    private LitecoinMain() {
        id = "litecoin.main";

        addressHeader = 48;
        p2shHeader = 5;
        acceptableAddressCodes = new int[]{addressHeader, p2shHeader};
        spendableCoinbaseDepth = 100;
        dumpedPrivateKeyHeader = 176;

        name = "Litecoin";
        symbol = "LTC";
        uriScheme = "litecoin";
        bip44Index = 2;
        unitExponent = 8;
        feeValue = value(100000);
        minNonDust = value(1000); // 0.00001 LTC mininput
        softDustLimit = value(100000); // 0.001 LTC
        softDustPolicy = SoftDustPolicy.BASE_FEE_FOR_EACH_SOFT_DUST_TXO;
        signedMessageHeader = toBytes("Litecoin Signed Message:\n");
    }

    public static synchronized CoinType get() {
        return instance;
    }
}
