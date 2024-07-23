package com.vergepay.core.util;

import com.vergepay.core.coins.BitcoinMain;
import com.vergepay.core.coins.CoinType;
import com.vergepay.core.coins.LitecoinMain;

import com.vergepay.core.exceptions.AddressMalformedException;
import com.vergepay.core.wallet.AbstractAddress;

import org.bitcoinj.core.Coin;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author John L. Jegutanis
 */
public class GenericUtilsTests {

    @Test
    public void getPossibleTypes() throws AddressMalformedException {
        List<CoinType> types = GenericUtils.getPossibleTypes("BPa5FmbZRGpmNfy4qaUzarXwSSFbJKFRMQ");
        assertTrue(GenericUtils.hasMultipleTypes("BPa5FmbZRGpmNfy4qaUzarXwSSFbJKFRMQ"));

        // Many coins share Bitcoin's multisig addresses...
        types = GenericUtils.getPossibleTypes("3Lp1ZbdoDfZF21BLMBpctM6CrM6j4t2JyU");
        assertTrue(types.contains(BitcoinMain.get()));
        assertTrue(types.contains(LitecoinMain.get()));
        assertTrue(GenericUtils.hasMultipleTypes("3Lp1ZbdoDfZF21BLMBpctM6CrM6j4t2JyU"));

        // Classic Bitcoin addresses should have only one type
        types = GenericUtils.getPossibleTypes("1AjnxP4frz7Nb4v2soLnhN2uV9UocqvaGH");
        assertTrue(types.contains(BitcoinMain.get()));
        assertEquals(1, types.size());
        assertFalse(GenericUtils.hasMultipleTypes("1AjnxP4frz7Nb4v2soLnhN2uV9UocqvaGH"));
    }

    @Test(expected = AddressMalformedException.class)
    public void getPossibleTypesInvalid() throws AddressMalformedException {
        GenericUtils.getPossibleTypes("");
    }

    @Test(expected = AddressMalformedException.class)
    public void getPossibleTypesUnsupported() throws AddressMalformedException {
        GenericUtils.getPossibleTypes("2mwJoik9pimQHUN2zU56J7h8tCTWYoUhpCM"); // version byte 0xFF
    }

    @Test
    public void formatValue() {
        // Bitcoin family
        assertEquals("1.3370", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(133700000), 6, 0));
        assertEquals("0.001337", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(133700), 6, 0));
        assertEquals("1.3370", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(133700000), 4, 0));
        assertEquals("0.0013", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(133700), 4, 0));
        assertEquals("1.34", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(133700000), 2, 0));
        assertEquals("1.34", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(133700000), 2, 0));
        assertEquals("0.0013", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(133700), 2, 0));

        assertEquals("1.00", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(100000001), 6, 0));
        assertEquals("1.00000001", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(100000001), 8, 0));
        assertEquals("1.00", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(100000010), 6, 0));
        assertEquals("1.00000010", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(100000010), 8, 0));
        assertEquals("1.000001", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(100000100), 6, 0));
        assertEquals("1.000001", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(100000100), 8, 0));
        assertEquals("1.000010", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(100001000), 6, 0));
        assertEquals("1.000010", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(100001000), 8, 0));
        assertEquals("1.0010", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(100100001), 6, 0));
        assertEquals("1.00100001", GenericUtils.formatCoinValue(BitcoinMain.get(), Coin.valueOf(100100001), 8, 0));
    }


}
