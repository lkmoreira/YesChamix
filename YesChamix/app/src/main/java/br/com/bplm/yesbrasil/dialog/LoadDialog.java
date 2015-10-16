package br.com.bplm.yesbrasil.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import br.com.bplm.yesbrasil.R;

@EFragment(R.layout.dialog_load)
public class LoadDialog extends DialogFragment {
    protected BaseActivity activity;

    public LoadDialog() {
        setCancelable(true);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Dialog);
    }

    @AfterViews
    protected void afterViews() {
        activity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
            dialog.setCanceledOnTouchOutside(false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismissAllowingStateLoss();
        activity.finish();
    }

}