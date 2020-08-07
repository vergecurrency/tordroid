package com.verge.core.wallet.families.bitcoin;

import com.verge.core.network.AddressStatus;
import com.verge.core.network.ServerClient.UnspentTx;
import com.verge.core.network.interfaces.TransactionEventListener;

import java.util.List;

/**
 * @author John L. Jegutanis
 */
public interface BitTransactionEventListener extends TransactionEventListener<BitTransaction> {
    void onUnspentTransactionUpdate(AddressStatus status, List<UnspentTx> UnspentTxes);
}
