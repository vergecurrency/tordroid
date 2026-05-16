package com.vergepay.wallet.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.vergepay.wallet.R;

final class RetroStatusToast {
    private RetroStatusToast() { }

    static void showInfo(@Nullable final Context context, final CharSequence message) {
        show(context, context != null ? context.getString(R.string.status_sending_title) : null,
                message, Toast.LENGTH_SHORT);
    }

    static void showSuccess(@Nullable final Context context, final CharSequence message) {
        show(context, context != null ? context.getString(R.string.status_sent_title) : null,
                message, Toast.LENGTH_LONG);
    }

    static void showWarning(@Nullable final Context context, final CharSequence message) {
        show(context, context != null ? context.getString(R.string.status_attention_title) : null,
                message, Toast.LENGTH_LONG);
    }

    static void showError(@Nullable final Context context, final CharSequence message) {
        show(context, context != null ? context.getString(R.string.status_error_title) : null,
                message, Toast.LENGTH_LONG);
    }

    private static void show(@Nullable final Context context, @Nullable final CharSequence title,
                             final CharSequence message, final int duration) {
        if (context == null) {
            return;
        }

        final View view = LayoutInflater.from(context).inflate(R.layout.toast_retro_status, null);
        final TextView titleView = view.findViewById(R.id.toast_title);
        final TextView messageView = view.findViewById(R.id.toast_message);

        if (title == null || title.length() == 0) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setText(title);
            titleView.setVisibility(View.VISIBLE);
        }
        messageView.setText(message);

        final Toast toast = new Toast(context.getApplicationContext());
        toast.setDuration(duration);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, dpToPx(context, 96));
        toast.setView(view);
        toast.show();
    }

    private static int dpToPx(final Context context, final int dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }
}
