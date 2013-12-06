package com.mobatia.mibblio.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mobatia.mibblio.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils {

	/**
	 * Gets the date format.
	 * 
	 * @param date
	 *            the date
	 * @return the date format
	 */
	public static String getDateFormat(String date) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
		Date datet = null;
		try {
			datet = format1.parse(date);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return format2.format(datet);
	}

	/**
	 * Show toast.
	 * 
	 * @param msg
	 *            the msg
	 * @param context
	 *            the context
	 */
	public static void showToast(String msg, Context context) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	/**
	 * Check internet.
	 * 
	 * @param context
	 *            the context
	 * @return true, if successful
	 */
	public static boolean checkInternet(Context context) {
		ConnectivityManager connec = (ConnectivityManager) context
				.getSystemService(context.CONNECTIVITY_SERVICE);

		NetworkInfo netInfo = connec.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check internet and finish.
	 * 
	 * @param activity
	 *            the activity
	 */
	public static void checkInternetAndFinish(Activity activity) {
		if (!checkInternet(activity)) {

			activity.finish();
		}
	}

	/**
	 * Gets the device width.
	 * 
	 * @param context
	 *            the context
	 * @return the device width
	 */
	@SuppressLint("NewApi")
	public static int getDeviceWidth(Context context) {
		int measuredWidth = 0;
		int measuredHeight = 0;
		Point size = new Point();
		WindowManager w = ((Activity) context).getWindowManager();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			w.getDefaultDisplay().getSize(size);
			measuredWidth = size.x;
			measuredHeight = size.y;
		} else {
			Display d = w.getDefaultDisplay();
			measuredWidth = d.getWidth();
			measuredHeight = d.getHeight();
		}
		return measuredWidth;
	}

	/**
	 * Gets the device height.
	 * 
	 * @param context
	 *            the context
	 * @return the device height
	 */
	@SuppressLint("NewApi")
	public static int getDeviceHeight(Context context) {
		int measuredWidth = 0;
		int measuredHeight = 0;
		Point size = new Point();
		WindowManager w = ((Activity) context).getWindowManager();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			w.getDefaultDisplay().getSize(size);
			measuredWidth = size.x;
			measuredHeight = size.y;
		} else {
			Display d = w.getDefaultDisplay();
			measuredWidth = d.getWidth();
			measuredHeight = d.getHeight();
		}
		return measuredHeight;
	}

	/**
	 * Showtoast.
	 * 
	 * @param context
	 *            the context
	 * @param text
	 *            the text
	 */
	public static void showtoast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Sets the error for text view.
	 * 
	 * @param edt
	 *            the edt
	 * @param msg
	 *            the msg
	 */
	public static void setErrorForTextView(TextView edt, String msg) {
		edt.setError(msg);
	}

	/**
	 * commonAlert
	 * 
	 * @param listView
	 *            the list view
	 * @param ctx
	 *            the ctx
	 */
	public static void commonAlert(final Activity context, String msg) {

		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setIcon(R.drawable.ic_launcher);
		alert.setTitle(context.getResources().getString(R.string.app_name));
		alert.setMessage(msg);
		alert.setCancelable(false);
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				context.finish();
			}
		});

		alert.show();
	}

	/**
	 * Gets the device id.
	 * 
	 * @param context
	 *            the context
	 * @return the device id
	 */
	public static String getDeviceID(Context context) {

		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (telephonyManager != null) {

			return telephonyManager.getDeviceId();
		} else {
			return Secure.getString(context.getContentResolver(),
					Secure.ANDROID_ID);
		}

	}

}
