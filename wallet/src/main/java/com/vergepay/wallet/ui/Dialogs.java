package com.vergepay.wallet.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.vergepay.wallet.R;

import javax.annotation.Nullable;

/**
 * @author John L. Jegutanis
 */
public class Dialogs {
    public static final String MESSAGE = "message";

    public static DialogFragment setMessage(DialogFragment newDialog, String message) {
        Bundle args = newDialog.getArguments();
        if (args == null) {
            newDialog.setArguments(new Bundle());
        }
        newDialog.getArguments().putString(MESSAGE, message);
        return newDialog;
    }

    /**
     * Shows a dialog fragment
     */
    public static void showDialog(FragmentManager fm, DialogFragment dialog, String string, String tag) {
        setMessage(dialog, string).show(fm, tag);
    }

    /**
     * Dismiss a dialog fragment
     * @return true if fragment is detached, useful for halting async task's onPostExecute.
     */
    public static boolean dismissAllowingStateLoss(@Nullable FragmentManager fm, String tag) {
        if (fm == null) {
            return true;
        }
        DialogFragment dialog = (DialogFragment) fm.findFragmentByTag(tag);
        if (dialog != null) {
            dialog.dismissAllowingStateLoss();
        }
        return false;
    }

    public static class ProgressDialogFragment extends DialogFragment {
        public static void show(FragmentManager fm, String string, String tag) {
            showDialog(fm, new ProgressDialogFragment(), string, tag);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final View view = LayoutInflater.from(requireContext())
                    .inflate(R.layout.dialog_progress_retro, null);

            final TextView messageView = view.findViewById(R.id.progress_message);
            messageView.setText(getArguments().getString(MESSAGE));

            final AlertDialog dialog = new AlertDialog.Builder(requireContext(),
                    R.style.AppAlertDialogTheme)
                    .setView(view)
                    .create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }
    }
}
