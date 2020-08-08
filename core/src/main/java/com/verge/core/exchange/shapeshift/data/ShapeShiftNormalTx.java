package com.vergeandroid.core.exchange.shapeshift.data;

import com.vergeandroid.core.coins.CoinID;
import com.vergeandroid.core.exchange.shapeshift.ShapeShift;
import com.vergeandroid.core.wallet.AbstractAddress;

import org.json.JSONObject;

/**
 * @author John L. Jegutanis
 */
public class ShapeShiftNormalTx extends ShapeShiftBase {
    public final String pair;
    public final AbstractAddress deposit;
    public final AbstractAddress withdrawal;

    public ShapeShiftNormalTx(JSONObject data) throws ShapeShiftException {
        super(data);
        if (!isError) {
            try {
                deposit = CoinID.typeFromSymbol(data.getString("depositType")).
                        newAddress(data.getString("deposit"));
                withdrawal = CoinID.typeFromSymbol(data.getString("withdrawalType")).
                        newAddress(data.getString("withdrawal"));
                pair = ShapeShift.getPair(deposit.getType(), withdrawal.getType());
            } catch (Exception e) {
                throw new ShapeShiftException("Could not parse object", e);
            }
        } else {
            deposit = null;
            withdrawal = null;
            pair = null;
        }
    }
}
