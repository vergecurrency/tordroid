package com.vergeandroid.core.exceptions;

import com.vergeandroid.core.coins.CoinType;

/**
 * @author John L. Jegutanis
 */
public class UnsupportedCoinTypeException extends RuntimeException {
    public UnsupportedCoinTypeException(CoinType type) {
        super("Unsupported coin type: " + type);
    }

    public UnsupportedCoinTypeException(String message) {
        super(message);
    }
}
