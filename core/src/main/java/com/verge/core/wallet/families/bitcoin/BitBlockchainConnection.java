package com.vergepay.core.wallet.families.bitcoin;

import com.vergepay.core.network.AddressStatus;
import com.vergepay.core.network.interfaces.BlockchainConnection;

/**
 * @author John L. Jegutanis
 */
public interface BitBlockchainConnection extends BlockchainConnection<BitTransaction> {
    void getUnspentTx(AddressStatus status, BitTransactionEventListener listener);
}
