package com.verge.wallet.ui;

import android.support.v4.app.FragmentActivity;

import com.verge.wallet.WalletApplication;

/**
 * @author John L. Jegutanis
 */
abstract public class AbstractWalletFragmentActivity extends FragmentActivity {

    protected WalletApplication getWalletApplication() {
        return (WalletApplication) getApplication();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWalletApplication().touchLastResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getWalletApplication().touchLastStop();
    }
}
