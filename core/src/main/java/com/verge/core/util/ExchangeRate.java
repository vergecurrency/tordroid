package com.verge.core.util;

import com.verge.core.coins.CoinType;
import com.verge.core.coins.Value;
import com.verge.core.coins.ValueType;

import org.bitcoinj.core.Coin;

import java.io.Serializable;

/**
 * @author John L. Jegutanis
 */
public interface ExchangeRate extends Serializable {
    @Deprecated
    Value convert(CoinType type, Coin coin);

    /**
     * Convert from one value to another
     */
    Value convert(Value convertingValue);

    ValueType getOtherType(ValueType type);

    ValueType getSourceType();
    ValueType getDestinationType();

    boolean canConvert(ValueType type1, ValueType type2);
}
