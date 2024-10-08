package com.vergepay.core.wallet.families.bitcoin;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.vergepay.core.Preconditions.checkState;

import com.vergepay.core.coins.CoinType;
import com.vergepay.core.coins.Value;
import com.vergepay.core.coins.families.BitFamily;
import com.vergepay.core.util.TypeUtils;
import com.vergepay.core.wallet.SendRequest;

import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Transaction;

/**
 * @author John L. Jegutanis
 */
public class BitSendRequest extends SendRequest<BitTransaction> {
    public BitSendRequest(CoinType type) {
        super(type);
    }

    /**
     * <p>Creates a new SendRequest to the given address for the given value.</p>
     *
     * <p>Be very careful when value is smaller than {@link Transaction#MIN_NONDUST_OUTPUT} as the transaction will
     * likely be rejected by the network in this case.</p>
     */

    public static BitSendRequest to(BitAddress destination, Value amount) {
        checkNotNull(destination.getType(), "Address is for an unknown network");
        checkState(TypeUtils.is(destination.getType(), amount.type), "Incompatible sending amount type");
        checkTypeCompatibility(destination.getType());

        BitSendRequest req = new BitSendRequest(destination.getType());

        Transaction tx = new Transaction(req.type);
        tx.addOutput(amount.toCoin(), destination);
        req.tx = new BitTransaction(tx);

        return req;
    }

    public static BitSendRequest emptyWallet(BitAddress destination) {
        checkNotNull(destination.getType(), "Address is for an unknown network");
        checkTypeCompatibility(destination.getType());

        BitSendRequest req = new BitSendRequest(destination.getType());

        Transaction tx = new Transaction(req.type);
        tx.addOutput(Coin.ZERO, destination);
        req.tx = new BitTransaction(tx);
        req.emptyWallet = true;

        return req;
    }

    private static void checkTypeCompatibility(CoinType type) {
        // Only Bitcoin family coins are supported
        if (!(type instanceof BitFamily)) {
            throw new RuntimeException("Unsupported type: " + type);
        }
    }
}
