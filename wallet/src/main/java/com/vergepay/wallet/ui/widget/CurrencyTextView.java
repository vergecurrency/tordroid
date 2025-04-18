package com.vergepay.wallet.ui.widget;

/*
 * Copyright 2013-2014 the original author or authors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


import android.content.Context;
import android.graphics.Paint;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import com.vergepay.core.coins.CoinType;
import com.vergepay.core.coins.Value;
import com.vergepay.core.coins.ValueType;
import com.vergepay.core.util.MonetaryFormat;
import com.vergepay.wallet.Constants;
import com.vergepay.wallet.R;
import com.vergepay.wallet.util.MonetarySpannable;

import org.bitcoinj.core.Monetary;

import javax.annotation.Nonnull;

/**
 * @author Andreas Schildbach
 * @author John L. Jegutanis
 */
public final class CurrencyTextView extends android.support.v7.widget.AppCompatTextView {
    private ValueType type = null;
    private Value amount = null;
    private MonetaryFormat format = null;
    private boolean alwaysSigned = false;

    public CurrencyTextView(final Context context) {
        super(context);
    }

    public CurrencyTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAmount(@Nonnull final Value amount) {
        this.amount = amount;
        this.type = amount.type;
        if (format == null) format = type.getMonetaryFormat().noCode();
        updateView();
    }

    public void setFormat(@Nonnull final MonetaryFormat format) {
        this.format = format.codeSeparator(Constants.CHAR_HAIR_SPACE);
        updateView();
    }

    public void setAlwaysSigned(final boolean alwaysSigned) {
        this.alwaysSigned = alwaysSigned;
        updateView();
    }

    public void setStrikeThru(final boolean strikeThru) {
        if (strikeThru)
            setPaintFlags(getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        else
            setPaintFlags(getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
    }

    public void setInsignificantRelativeSize(final float insignificantRelativeSize) {
        RelativeSizeSpan prefixRelativeSizeSpan = null;
        RelativeSizeSpan insignificantRelativeSizeSpan = null;
        if (insignificantRelativeSize != 1) {
            prefixRelativeSizeSpan = new RelativeSizeSpan(insignificantRelativeSize);
            insignificantRelativeSizeSpan = new RelativeSizeSpan(insignificantRelativeSize);
        } else {
            prefixRelativeSizeSpan = null;
            insignificantRelativeSizeSpan = null;
        }
    }

    public void setPrefixColor(final int prefixColor) {
        ForegroundColorSpan prefixColorSpan = new ForegroundColorSpan(prefixColor);
        updateView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setPrefixColor(getResources().getColor(R.color.fg_less_significant));
        setInsignificantRelativeSize(0.85f);
        setSingleLine();
    }

    private void updateView() {
        final MonetarySpannable text;

        if (amount != null) {
//            text = new MonetarySpannable(format, alwaysSigned, amount, type)
//                    .applyMarkup(prefixRelativeSizeSpan, prefixColorSpan, insignificantRelativeSizeSpan);
            text = new MonetarySpannable(format, alwaysSigned, amount);
        } else {
            text = null;
        }

        setText(text);
    }
}