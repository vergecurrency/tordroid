package com.verge.core.network.interfaces;

import com.verge.core.coins.CoinType;

/**
 * @author John L. Jegutanis
 */
public interface ConnectionEventListener {
    void onConnection(BlockchainConnection blockchainConnection);
    void onDisconnect();
}
