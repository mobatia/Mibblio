package com.mobatia.mibblio.userinterface;

import java.util.ArrayList;

import com.mobatia.mibblio.R;
import com.mobatia.mibblio.adapter.ListViewAdapter;
import com.mobatia.mibblio.adapter.ThumpnailAdapter;
import com.mobatia.mibblio.constants.Constants;
import com.mobatia.mibblio.manager.ActionBarColorManager;
import com.mobatia.mibblio.model.GistsResponseModel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class FileViewerActivity.
 */
public class FileViewerActivity extends Activity implements Constants {

	/** The array gists response model. */
	ArrayList<GistsResponseModel> arrayGistsResponseModel;

	/** The Gists response model. */
	GistsResponseModel gistsResponseModel;

	/** The grid view. */
	GridView gridView;

	/** The position. */
	int position;

	/** The activity. */
	Activity activity;

	/*
	 * (non-Javadoc) onCreate(Bundle) is where you initialize your activity.
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fileviewer);
		activity = this;
		new ActionBarColorManager(activity).setActionBarColor(ACTION_BAR_COLOR);
		Bundle extras = getIntent().getExtras();
		gistsResponseModel = (GistsResponseModel) extras
				.getSerializable("model");
		position = extras.getInt("position");
		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setGravity(GridView.AUTO_FIT);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				String url[] = gistsResponseModel.getRaw_url();
				Intent i = new Intent(FileViewerActivity.this,
						LoadFileActivity.class);
				i.putExtra("url", url[position]);
				startActivity(i);

			}

		});

		LoadThumpnail load = new LoadThumpnail();
		load.execute();

	}

	/**
	 * The Class LoadThumpnail.
	 */
	private class LoadThumpnail extends AsyncTask<Void, Void, Void> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			arrayGistsResponseModel = new ArrayList<GistsResponseModel>();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected Void doInBackground(Void... params) {

			GistsResponseModel model;
			for (int i = 0; i < gistsResponseModel.getFilename().length; i++) {

				model = new GistsResponseModel();
				model.setFilename(gistsResponseModel.getFilename());
				model.setLanguage(gistsResponseModel.getLanguage());
				model.setRaw_url(gistsResponseModel.getRaw_url());
				model.setType(gistsResponseModel.getType());
				model.setFileSize(gistsResponseModel.getFileSize());
				arrayGistsResponseModel.add(model);

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
			gridView.setAdapter(new ThumpnailAdapter(FileViewerActivity.this,
					arrayGistsResponseModel));
		}

	}

}
