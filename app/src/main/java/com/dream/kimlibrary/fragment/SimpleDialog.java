package com.dream.kimlibrary.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.text.HtmlCompat;

import com.dream.kimlibrary.R;

/**
 * 요즘은 Fragment로 Dialog를 표현하는구나?
 */
public class SimpleDialog extends AppCompatDialogFragment {

    Context context;
    String title;
    String message;

    public SimpleDialog(Context context, String title, String message) {
        this.context = context;
        this.title = title;
        this.message = message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new AlertDialog.Builder( context )
                .setTitle( title)
                .setMessage(HtmlCompat.fromHtml(getString(R.string.tnc_notice_details), HtmlCompat.FROM_HTML_MODE_LEGACY))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.dismiss();
                    }
                })
                .create();
    }
}
