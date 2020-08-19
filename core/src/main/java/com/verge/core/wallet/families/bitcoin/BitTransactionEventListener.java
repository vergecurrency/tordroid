package com.vergepay.core.wallet.families.bitcoin;

import com.vergepay.core.network.AddressStatus;
import com.vergepay.core.network.ServerClient.UnspentTx;
import com.vergepay.core.network.interfaces.TransactionEventListener;

import java.util.List;

/**
 * @author John L. Jegutanis
 */
public interface BitTransactionEventListener extends TransactionEventListener<BitTransaction> {
    void onUnspentTransactionUpdate(AddressStatus status, List<UnspentTx> UnspentTxes);
}
