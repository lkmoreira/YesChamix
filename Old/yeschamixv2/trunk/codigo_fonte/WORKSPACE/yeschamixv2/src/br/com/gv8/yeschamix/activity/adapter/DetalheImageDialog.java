package br.com.gv8.yeschamix.activity.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;

public class DetalheImageDialog extends Dialog {

    public float currentXPosition;
    public float currentYPosition;

    public DetalheImageDialog(Context context) {
	super(context);
    }

    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
	dismiss();
	return true;
    }
}