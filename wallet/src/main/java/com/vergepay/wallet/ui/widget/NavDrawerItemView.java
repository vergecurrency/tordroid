package com.vergepay.wallet.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vergepay.core.wallet.WalletAccount;
import com.vergepay.wallet.Constants;
import com.vergepay.wallet.R;
import com.vergepay.wallet.util.WalletUtils;

/**
 * @author John L. Jegutanis
 */
public class NavDrawerItemView extends LinearLayout implements Checkable {
    private final TextView title;
    private final ImageView icon;
    private final View view;

    private boolean isChecked = false;

    public NavDrawerItemView(Context context) {
        super(context);

        view = LayoutInflater.from(context).inflate(R.layout.nav_drawer_item, this, true);
        title = findViewById(R.id.item_text);
        icon = findViewById(R.id.item_icon);
    }

    public void setData(String titleStr, int iconRes) {
        title.setText(titleStr);
        icon.setImageResource(iconRes);
    }

    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;

        if (isChecked) {
            view.setBackgroundResource(R.color.primary_500);
        } else {
            view.setBackgroundResource(0);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}
