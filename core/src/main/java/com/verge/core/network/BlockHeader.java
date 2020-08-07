package com.verge.core.network;

import com.verge.core.coins.CoinType;

import java.util.Date;

/**
 * @author John L. Jegutanis
 */
public class BlockHeader {
    final CoinType type;
    final long timestamp;
    final int blockHeight;

    /**
     * timestamp in seconds (unix epoch)
     */
    public BlockHeader(CoinType type, long timestamp, int blockHeight) {
        this.type = type;
        this.timestamp = timestamp;
        this.blockHeight = blockHeight;
    }

    public CoinType getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public boolean equals(BlockHeader blockHeader) {
        return (this.getBlockHeight() == blockHeader.getBlockHeight() &&
                 this.getTimestamp() == this.getTimestamp());
    }
}
