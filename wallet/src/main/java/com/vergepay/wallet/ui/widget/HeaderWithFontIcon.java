package com.vergepay.wallet.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vergepay.wallet.R;
import com.vergepay.wallet.util.Fonts;

/**
 * @author John L. Jegutanis
 */
public class HeaderWithFontIcon extends LinearLayout {
    private final TextView fontIconView;
    private final TextView messageView;

    public HeaderWithFontIcon(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.header_with_font_icon, this, true);

        messageView = findViewById(R.id.message);
        fontIconView = findViewById(R.id.font_icon);
        Fonts.setTypeface(fontIconView, Fonts.Font.COINOMI_FONT_ICONS);
    }

    public void setFontIcon(int resid) {
        fontIconView.setText(resid);
    }

    public void setMessage(int resid) {
        messageView.setText(resid);
    }
}
