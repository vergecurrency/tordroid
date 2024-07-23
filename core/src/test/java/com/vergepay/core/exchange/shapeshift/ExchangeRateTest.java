package com.vergepay.core.exchange.shapeshift;

import com.vergepay.core.coins.BitcoinMain;
import com.vergepay.core.coins.CoinType;
import com.vergepay.core.coins.FiatValue;
import com.vergepay.core.coins.VergeMain;
import com.vergepay.core.exchange.shapeshift.data.ShapeShiftExchangeRate;
import com.vergepay.core.util.ExchangeRateBase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author John L. Jegutanis
 */
public class ExchangeRateTest {
    final CoinType BTC = BitcoinMain.get();
    final CoinType XVG = VergeMain.get();


    @Test
    public void baseFee() {
        ShapeShiftExchangeRate rate = new ShapeShiftExchangeRate(BTC, XVG, "100", "0.01");

        assertEquals(BTC.value("1"), rate.value1);
        assertEquals(XVG.value("100"), rate.value2);
        assertEquals(XVG.value("0.01"), rate.minerFee);

        assertEquals(XVG.value("99.99"), rate.convert(BTC.oneCoin()));
        assertEquals(BTC.value("1"), rate.convert(XVG.value("99.99")));

        rate = new ShapeShiftExchangeRate(BTC.oneCoin(),
                XVG.value("1911057.69230769"), XVG.value("1"));
        assertEquals(BTC.value("1"), rate.value1);
        assertEquals(XVG.value("1911057.69230769"), rate.value2);
        assertEquals(XVG.value("1"), rate.minerFee);
        assertEquals(BTC.value("0.00052379"), rate.convert(XVG.value("1000")));

        rate = new ShapeShiftExchangeRate(BTC.oneCoin(),
                XVG.value("1878207.54716981"), XVG.value("1"));
        assertEquals(BTC.value("1"), rate.value1);
        assertEquals(XVG.value("1878207.54716981"), rate.value2);
        assertEquals(XVG.value("1"), rate.minerFee);
        assertEquals(BTC.value("0.00532476"), rate.convert(XVG.value("10000")));
    }

    @Test
    public void zeroValues() {
        ShapeShiftExchangeRate rate = new ShapeShiftExchangeRate(BTC, XVG, "100", "0.01");
        assertEquals(BTC.value("0"), rate.convert(XVG.value("0")));
        assertEquals(XVG.value("0"), rate.convert(BTC.value("0")));
    }

    @Test
    public void smallValues() {
        ShapeShiftExchangeRate rate = new ShapeShiftExchangeRate(XVG, BTC, "5.1e-7", "0.0001");
        assertEquals(BTC.value("0"), rate.convert(XVG.value("1")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroExchangeRate() throws Exception {
        new ShapeShiftExchangeRate(BTC, XVG, "0", "0.01");
    }
}