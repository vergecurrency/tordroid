package com.vergepay.wallet.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.vergepay.wallet.ui.BalanceFragment;
import com.vergepay.wallet.ui.WalletActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrbotStatusReceiver extends BroadcastReceiver {

    private static final Logger log = LoggerFactory.getLogger(OrbotStatusReceiver.class);

    private final BalanceFragment.Listener listener;

    public OrbotStatusReceiver(BalanceFragment.Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String status = intent.getStringExtra("org.torproject.android.intent.extra.STATUS");
        log.info("Received new intent from Orbot. Status: {}" + status);

        if (status.equals("ON")) {
            if (listener != null) {
                listener.onRefresh();
            }
        }
    }
}
