package com.verge.core.exchange.shapeshift.data;

import com.verge.core.coins.CoinType;
import com.verge.core.coins.Value;
import com.verge.core.exchange.shapeshift.ShapeShift;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author John L. Jegutanis
 */
abstract public class ShapeShiftBase {
    final public String errorMessage;
    final public boolean isError;

    protected ShapeShiftBase(JSONObject data) {
        this(data.optString("error", null));
    }

    protected ShapeShiftBase(String errorMessage) {
        this.errorMessage = errorMessage;
        isError = errorMessage != null;
    }

    protected static Value parseValueRound(CoinType type, String string) {
        return parseValue(type, string, RoundingMode.HALF_UP);
    }

    protected static Value parseValue(CoinType type, String string, RoundingMode roundingMode) {
        BigDecimal value = new BigDecimal(string).setScale(type.getUnitExponent(), roundingMode);
        return Value.parse(type, value);
    }
}
