package com.mobatia.mibblio.userinterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobatia.mibblio.R;
import com.mobatia.mibblio.R.layout;
import com.mobatia.mibblio.adapter.ListViewAdapter;
import com.mobatia.mibblio.constants.Constants;
import com.mobatia.mibblio.manager.ActionBarColorManager;
import com.mobatia.mibblio.manager.InternetManager;
import com.mobatia.mibblio.manager.Utils;
import com.mobatia.mibblio.model.GistsResponseModel;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.JetPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeActivity.
 */
public class HomeActivity extends Activity implements Constants {

	/** The array gists response model. */
	ArrayList<GistsResponseModel> arrayGistsResponseModel;

	/** The manager. */
	InternetManager manager;

	/** The response. */
	String response;

	/** The activity. */
	Activity activity;

	/** The progress bar. */
	ProgressBar progressBar;

	/** The grid view. */
	GridView gridView;

	/**
	 * This boolean flag for handling api timeout error or api json response
	 * error
	 */
	private boolean apiServiceException = false;

	/*
	 * (non-Javadoc) onCreate(Bundle) is where you initialize your activity.
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);
		activity = this;

		new ActionBarColorManager(activity).setActionBarColor(ACTION_BAR_COLOR);

		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		gridView = (GridView) findViewById(R.id.gridView1);

		gridView.setGravity(GridView.AUTO_FIT);

		/*
		 * grid view click listner for handling selection of a file
		 */
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				if ((arrayGistsResponseModel.get(position).getFilename()).length == 1) {

					String url[] = arrayGistsResponseModel.get(position)
							.getRaw_url();

					Intent i = new Intent(HomeActivity.this,
							LoadFileActivity.class);
					i.putExtra("url", url[0]);
					startActivity(i);

				} else {
					Intent i = new Intent(getApplicationContext(),
							FileViewerActivity.class);

					i.putExtra("model", arrayGistsResponseModel.get(position));
					i.putExtra("position", position);

					startActivity(i);
				}

			}

		});
		/*
		 * Handling intenert connection alert
		 */
		if (Utils.checkInternet(activity)) {

			try {

				/*
				 * Create a Thread to get JSON Array
				 * 
				 * BASE_URL Api url ,get resent Gits
				 * 
				 * Activity is the second parameter
				 */
				ApiCall apicall = new ApiCall(BASE_URL, activity);
				apicall.execute();
			} catch (Exception e) {

				e.printStackTrace();
			}

		} else {

			Utils.commonAlert(activity, getApplicationContext().getResources()
					.getString(R.string.Internet_check));
		}

	}

	/**
	 * The Class ApiCall.
	 * 
	 * This class for get JSON array from Gist API
	 * 
	 * @params BASE_URL Api url ,get resent Gits, activity
	 * 
	 * 
	 */
	private class ApiCall extends AsyncTask<Void, Integer, Void> {

		/** The url. */
		String url;

		/** The context. */
		Context context;

		/**
		 * Instantiates a new api call.
		 * 
		 * @param url
		 *            the url
		 * @param context
		 *            the context
		 */
		public ApiCall(String url, Context context) {

			this.url = url;
			this.context = context;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(Params[]) This background
		 * async task is calling the api and handling the response
		 */
		@Override
		protected Void doInBackground(Void... params) {

			if (Utils.checkInternet(context)) {

				manager = new InternetManager(url);
				try {

					response = manager.URLRequest();
					apiJsonResponseParse(response);

				} catch (Exception e) {
					apiServiceException = true;
					e.printStackTrace();
				}

			} else {

				return null;
			}

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if (!apiServiceException) {
				gridView.setAdapter(new ListViewAdapter(HomeActivity.this,
						arrayGistsResponseModel));

				progressBar.setVisibility(View.GONE);
			} else {
				Utils.commonAlert(activity, getApplicationContext()
						.getResources()
						.getString(R.string.common_error_message));
			}
		}

	}

	/**
	 * Api json response parse.
	 * 
	 * function for parse JSON Array to strings
	 * 
	 * The git id. GIT_ID =
	 * 
	 * /** The git type description. GIT_TYPE_DESCRIPTION
	 * 
	 * /** The file. FILE
	 * 
	 * /** The git date. GIT_DATE
	 * 
	 * /** The git file name. GIT_FILE_NAME
	 * 
	 * /** The git language. GIT_LANGUAGE
	 * 
	 * /** The git raw url. GIT_RAW_URL
	 * 
	 * /** The git type. GIT_TYPE
	 * 
	 * @param response
	 *            the response
	 */
	public void apiJsonResponseParse(String response) {

		arrayGistsResponseModel = new ArrayList<GistsResponseModel>();

		if (response != null) {
			try {
				JSONArray jsonArray = new JSONArray(response);
				int jsonleng = jsonArray.length();

				if (jsonleng > COUNT) {
					jsonleng = COUNT;
				}

				for (int i = 0; i < jsonleng; i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					String gitId = jsonObject.optString(GIT_ID);
					String description = jsonObject
							.optString(GIT_TYPE_DESCRIPTION);
					String date = jsonObject.optString(GIT_DATE);

					JSONObject jsonFile = jsonObject.getJSONObject(FILE);

					JSONArray nameInJsonArray = jsonFile.toJSONArray(jsonFile
							.names());
					GistsResponseModel model = new GistsResponseModel();

					String fileName[] = new String[nameInJsonArray.length()];
					String fileType[] = new String[nameInJsonArray.length()];
					String fileLanguage[] = new String[nameInJsonArray.length()];
					String fileRawUrl[] = new String[nameInJsonArray.length()];
					String fileSize[] = new String[nameInJsonArray.length()];

					for (int j = 0; j < nameInJsonArray.length(); j++) {
						JSONObject name = null;
						try {
							name = nameInJsonArray.getJSONObject(j);

						} catch (Exception e) {
							apiServiceException = true;

						}

						fileName[j] = name.optString(GIT_FILE_NAME);
						fileType[j] = name.optString(GIT_TYPE);
						fileLanguage[j] = name.optString(GIT_LANGUAGE);
						fileRawUrl[j] = name.optString(GIT_RAW_URL);
						fileSize[j] = name.optString(GIT_FILE_SIZE);
					}

					model.setDate(date);
					model.setGitId(gitId);
					if (description.length() < 1 || description.equals("null")) {
						model.setDescription("No description available.");
					} else
						model.setDescription(description);

					model.setFilename(fileName);
					model.setLanguage(fileLanguage);
					model.setRaw_url(fileRawUrl);
					model.setType(fileType);
					model.setFileSize(fileSize);
					arrayGistsResponseModel.add(model);

				}

			} catch (JSONException e) {
				apiServiceException = true;
				e.printStackTrace();
			}
		}

	}

}
