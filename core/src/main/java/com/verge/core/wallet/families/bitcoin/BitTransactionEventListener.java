package com.vergeandroid.core.wallet.families.bitcoin;

import com.vergeandroid.core.network.AddressStatus;
import com.vergeandroid.core.network.ServerClient.UnspentTx;
import com.vergeandroid.core.network.interfaces.TransactionEventListener;

import java.util.List;

/**
 * @author John L. Jegutanis
 */
public interface BitTransactionEventListener extends TransactionEventListener<BitTransaction> {
    void onUnspentTransactionUpdate(AddressStatus status, List<UnspentTx> UnspentTxes);
}
