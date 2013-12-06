package com.mobatia.mibblio.userinterface;

import com.mobatia.mibblio.R;
import com.mobatia.mibblio.constants.Constants;
import com.mobatia.mibblio.manager.ActionBarColorManager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class LoadFileActivity.
 */
public class LoadFileActivity extends Activity implements Constants {

	/** The web view. */
	WebView webView;

	/** The activity. */
	Activity activity;

	/** The pro web view. */
	ProgressBar proWebView;

	/*
	 * (non-Javadoc) onCreate(Bundle) is where you initialize your activity.
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_load_file);
		activity = this;
		new ActionBarColorManager(activity).setActionBarColor(ACTION_BAR_COLOR);
		Bundle extra = getIntent().getExtras();

		webView = (WebView) findViewById(R.id.webView);

		String url = extra.getString("url");
		proWebView = (ProgressBar) findViewById(R.id.proWebView);

		webView.loadUrl(url);
		webView.setWebViewClient(new HelloWebViewClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setPluginState(PluginState.ON);
		webView.getSettings().setBuiltInZoomControls(false);
		webView.getSettings().setDisplayZoomControls(true);

	}

	/**
	 * The Class HelloWebViewClient.
	 * 
	 * HelloWebViewClient class extend from WebViewClient
	 * 
	 * 
	 * 
	 */
	private class HelloWebViewClient extends WebViewClient {

		/*
		 * (non-Javadoc) host application of a resource request and allow the
		 * application to return the data. If the return value is null, the
		 * WebView will continue to load the resource as usual. Otherwise, the
		 * return response and data will be used
		 * 
		 * @see
		 * android.webkit.WebViewClient#shouldOverrideUrlLoading(android.webkit
		 * .WebView, java.lang.String)
		 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.webkit.WebViewClient#onPageFinished(android.webkit.WebView,
		 * java.lang.String)
		 * 
		 * Notify the host application that a page has finished loading. This
		 * method is called only for main frame
		 */
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);

			proWebView.setVisibility(View.GONE);
		}

	}

}
