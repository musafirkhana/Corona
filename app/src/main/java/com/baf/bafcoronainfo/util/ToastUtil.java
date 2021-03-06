package com.baf.bafcoronainfo.util;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baf.bafcoronainfo.R;


public class ToastUtil {
	private Activity activity;
	public static Toast mToast;

	public ToastUtil(Activity _activity) {

		this.activity = _activity;
		// other initializations...

	}

	/**
	 * Application Error Toast Message
	 * @param context
	 * @param message
	 */
	public void appErrorMsg(Context context, String message) {
		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_LONG);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.toast_message,
				(ViewGroup) this.activity.findViewById(R.id.custom_toast_layout));

		TextView toast_text = (TextView) layout.findViewById(R.id.txtvw);
		toast_text.setText(message);
		toast.setView(layout);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();

	}
/**
 *  Application Success Toast Message
 * @param context
 * @param message
 */
	public void appSuccessMsg(Context context, String message) {
		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setGravity(Gravity.BOTTOM, 0, 100);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.toast_message,
				(ViewGroup) this.activity.findViewById(R.id.custom_toast_layout));
		TextView toast_text = (TextView) layout.findViewById(R.id.txtvw);
		toast_text.setText(message);
		toast.setView(layout);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();

	}

	@SuppressLint("ShowToast")
	private static void initToast() {
		if (mToast != null) {
			return;
		}
		Context context = VaiuuTools.getApplicationContext();
		if (context == null) {
			return;
		}
		mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
	}

	public static void showToast(int resId) {
		initToast();
		if (resId < 0 || mToast == null) {
			return;
		}

		mToast.setText(resId);
		mToast.show();
	}

	public static void showToast(String message) {
		initToast();
		if (TextUtils.isEmpty(message) || mToast == null) {
			return;
		}

		mToast.setText(message);
		mToast.show();
	}

	public static void dismissToast() {
		if (mToast == null) {
			return;
		}
		mToast.cancel();
	}
}
