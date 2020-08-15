package com.vergeandroid.wallet.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.vergeandroid.core.coins.CoinType;
import com.vergeandroid.core.wallet.Wallet;
import com.vergeandroid.core.wallet.WalletAccount;
import com.vergeandroid.wallet.Configuration;
import com.vergeandroid.wallet.R;
import com.vergeandroid.wallet.WalletApplication;
import com.vergeandroid.wallet.ui.dialogs.FingerprintScanDialog;

import java.util.List;

import javax.annotation.Nullable;

import static com.vergeandroid.wallet.util.Crypto.hashMD5;

/**
 * @author John L. Jegutanis
 */
abstract public class BaseWalletActivity extends AppCompatActivity {

    public WalletApplication getWalletApplication() {
        return (WalletApplication) getApplication();
    }

    @Nullable
    public WalletAccount getAccount(String accountId) {
        return getWalletApplication().getAccount(accountId);
    }

    public List<WalletAccount> getAllAccounts() {
        return getWalletApplication().getAllAccounts();
    }

    public List<WalletAccount> getAccounts(CoinType type) {
        return getWalletApplication().getAccounts(type);
    }

    public List<WalletAccount> getAccounts(List<CoinType> types) {
        return getWalletApplication().getAccounts(types);
    }

    public boolean isAccountExists(String accountId) {
        return getWalletApplication().isAccountExists(accountId);
    }

    public Configuration getConfiguration() {
        return getWalletApplication().getConfiguration();
    }

    public FragmentManager getFM() {
        return getSupportFragmentManager();
    }

    public void replaceFragment(Fragment fragment, int container) {
        replaceFragment(fragment, container, null);
    }

    public void replaceFragment(Fragment fragment, int container, @Nullable String tag) {
        FragmentTransaction transaction = getFM().beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(container, fragment, tag);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Nullable
    public Wallet getWallet() {
        return getWalletApplication().getWallet();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWalletApplication().touchLastResume();
        lockCheck(false);
    }

    private void lockCheck(boolean ignoreFragmentCheck) {
        if (getWalletApplication().isLocked() && (ignoreFragmentCheck || getSupportFragmentManager().findFragmentByTag("AuthCheck") == null)) {
            if (getWalletApplication().getConfiguration().isFingerprintAuthEnabled()) {
                final FingerprintScanDialog fingerprintDialog = FingerprintScanDialog.newInstance(FingerprintScanDialog.Mode.CHECK);
                fingerprintDialog.setCallback(new FingerprintScanDialog.Callback() {
                    @Override
                    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);

                        getWalletApplication().unlockApp();
                    }

                    @Override
                    public void onAuthenticationError(int errMsgId, CharSequence errString) {
                        super.onAuthenticationError(errMsgId, errString);

                        Toast.makeText(getApplicationContext(), errString, Toast.LENGTH_SHORT).show();
                        showPincodeDialog();
                    }

                    @Override
                    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                        super.onAuthenticationHelp(helpMsgId, helpString);

                        Toast.makeText(getApplicationContext(), helpString, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(getApplicationContext(), R.string.finger_not_recognized, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPincodeClicked() {
                        fingerprintDialog.dismiss();
                        showPincodeDialog();
                    }
                });
                fingerprintDialog.show(getSupportFragmentManager(), "AuthCheck");
            } else {
                showPincodeDialog();
            }
        }
    }

    private void showPincodeDialog() {
        final EnterPincodeFragment pincodeFragment = new EnterPincodeFragment();
        pincodeFragment.setCancelable(false);
        pincodeFragment.setResultCallback(new EnterPincodeFragment.Callback() {
            @Override
            public void onResult(String pincode) {
                if (getWalletApplication().getConfiguration().isPincodeHashValid(hashMD5(pincode))) {
                    getWalletApplication().unlockApp();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.wrong_pincode, Toast.LENGTH_SHORT).show();
                    lockCheck(true);
                }
            }
        });
        pincodeFragment.setCancelable(false);
        pincodeFragment.show(getSupportFragmentManager(), "AuthCheck");
    }

    @Override
    protected void onStop() {
        super.onStop();
        getWalletApplication().touchLastStop();
    }
}