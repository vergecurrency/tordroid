package com.vergeandroid.core.wallet.families.bitcoin;

import com.vergeandroid.core.network.AddressStatus;
import com.vergeandroid.core.network.interfaces.BlockchainConnection;

/**
 * @author John L. Jegutanis
 */
public interface BitBlockchainConnection extends BlockchainConnection<BitTransaction> {
    void getUnspentTx(AddressStatus status, BitTransactionEventListener listener);
}
