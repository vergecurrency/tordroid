package com.vergepay.core.wallet.families.bitcoin;

import com.vergepay.core.coins.VergeMain;

import org.bitcoinj.core.Transaction;
import org.junit.Test;
import org.spongycastle.util.encoders.Hex;

import static org.junit.Assert.assertEquals;

/**
 * @author John L. Jegutanis
 */
public class TransactionTest {
    String vergeHash = "f1311469970c2a7bd1a0f69affe627222e7bae446dbafdbe90479706b75263ca";
    Transaction vergeTx = new Transaction(VergeMain.get(), Hex.decode("01000000896dc3550448d7832861e532b3fbd225017cb7ca9a20caf79b6f802255520b37415959570d030000006c493046022100871f5b7a75cc9732d4c1410787a7e07131c74410420efaf7e5f438f86a3933fa022100dff501634f5d3fd64075f685173830c5023824cbfc46ea013d29da78c7eda9d3012103bcce424f94449613ad54050739ed5a5f79bc97cb526c5e850f19e6fcf030f5d2ffffffff8b015753b65cf8a5fa8af15aa955d2925d9138dde80ac07f94056a56331a7407010000006b483045022054030eb85636ddb9362505af46d334cd16c5df09245f9ad44d7d065ec7a4de420221009368aa9b145ec952dd5c2edd65a32210049328b8d57681e367da72bfc63e9029012103bcce424f94449613ad54050739ed5a5f79bc97cb526c5e850f19e6fcf030f5d2ffffffffb7eb79dd9205847bb19571b544e96d3f479f1aebc7818b185c31b150b2157453010000006b48304502205c7b91f7057563c992ce2d1a56b7c7b61394f327ccd5e0d6ceafb6c42d70e330022100ba76acfb2b5e1592b03e9f32c4c0756d56e73f99a78766d12915effd0648f635012103bcce424f94449613ad54050739ed5a5f79bc97cb526c5e850f19e6fcf030f5d2ffffffff33c208bc6fe2c2c12c218617325849536c9adc24e673d25c24758d3dd96cbdef540000006c493046022100a03ff05c33b2ac34aff5a8dfb4b545d14cf53887d70ec48a9ae203038b9f228f022100f287e59fdd132b11c095ec105b9b7d4f9cd18cb2de26f9de32912d626dc870d50121038a64028ae8fe1801158a8f72f6f9e8757fe46c06fed4705424aa7a0cb50ce371ffffffff0162b31701000000001976a914181805d5c08a3f5cc0c0c1e6fd40304259dbaddc88ac00000000"));

    @Test
    public void vergeTxTest() {
        assertEquals(vergeHash, vergeTx.getHashAsString());
    }
}