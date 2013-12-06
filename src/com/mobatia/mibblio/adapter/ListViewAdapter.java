package com.mobatia.mibblio.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mobatia.mibblio.R;
import com.mobatia.mibblio.manager.Utils;
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
 * The Class ListViewAdapter.
 */
public class ListViewAdapter extends BaseAdapter {

	/** The context. */
	private Context context;
	
	/** The mobile values. */
	ArrayList<GistsResponseModel> mobileValues;

	/**
	 * Instantiates a new list view adapter.
	 * A view that shows items in a vertically scrolling list. 
	 * The items come from the ListAdapter associated with this view.
	 *
	 * @param context the context
	 * @param mobileValues the mobile values
	 */
	public ListViewAdapter(Context context,
			ArrayList<GistsResponseModel> mobileValues) {
		this.context = context;
		this.mobileValues = mobileValues;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mobileValues.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mobileValues.get(arg0);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			gridView = inflater.inflate(R.layout.inflatelistview, null);

			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			TextView language = (TextView) gridView
					.findViewById(R.id.language);
			TextView date = (TextView) gridView
					.findViewById(R.id.tdate);
			
			date.setText( Utils.getDateFormat(mobileValues.get(position).getDate().substring(0,10)) );

			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);

			String mobile = mobileValues.get(position).getDescription();
			textView.setText(mobile);
			String lanString[]= mobileValues.get(position).getLanguage();
			if(lanString[0].length()<1||lanString[0].equals("null"))
			{
				language.setText("Language unavailable");
			}else
				language.setText(lanString[0]);
			imageView.setImageResource(R.drawable.gist);

		} else {
			gridView = (View) convertView;
		}
		return gridView;
	}

}
