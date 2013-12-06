package com.mobatia.mibblio.adapter;

import java.util.ArrayList;

import com.mobatia.mibblio.R;
import com.mobatia.mibblio.model.GistsResponseModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class ThumpnailAdapter.
 */
public class ThumpnailAdapter extends BaseAdapter {

	/** The context. */
	private Context context;

	/** The mobile values. */
	ArrayList<GistsResponseModel> mobileValues;

	/**
	 * Instantiates a new thumpnail adapter. A view that shows items in a
	 * vertically scrolling list. The items come from the ListAdapter associated
	 * with this view.
	 * 
	 * @param context
	 *            the context
	 * @param mobileValues
	 *            the mobile values
	 */
	public ThumpnailAdapter(Context context,
			ArrayList<GistsResponseModel> mobileValues) {
		this.context = context;
		this.mobileValues = mobileValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mobileValues.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mobileValues.get(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			gridView = inflater.inflate(R.layout.inflategridview, null);

			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);

			TextView textViewlanguage = (TextView) gridView
					.findViewById(R.id.language);

			TextView textSize = (TextView) gridView.findViewById(R.id.size);

			String strtextSize[] = mobileValues.get(position).getFileSize();
			String name[] = mobileValues.get(position).getFilename();

			textView.setText(name[position]);
			float fsize = Float.parseFloat(strtextSize[position]);
			if (fsize <= 0) {
				try {
					textSize.setText("Size :" + fsize + " Kb");
				} catch (Exception e) {

				}
			} else {
				textSize.setText("Size :"
						+ (((fsize) / 1024) + "").substring(0, 3) + " Kb");
			}

			String language[] = mobileValues.get(position).getLanguage();

			if (language[position].length() < 1
					|| language[position].equals("null")) {
				textViewlanguage.setText("Language unavailable");
			} else
				textViewlanguage.setText(language[position]);

			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);

			imageView.setImageResource(R.drawable.doc);
		

		} else {
			gridView = (View) convertView;
		}
		return gridView;
	}

}
