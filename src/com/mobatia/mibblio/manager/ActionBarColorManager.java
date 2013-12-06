package com.mobatia.mibblio.manager;

import com.mobatia.mibblio.constants.Constants;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

// TODO: Auto-generated Javadoc
/**
 * The Class ActionBarColorManager.
 */
public class ActionBarColorManager implements Constants{
	
	/** The activity. */
	Activity activity;
	
	/**
	 * Instantiates a new action bar color manager.
	 *To change the action bar background, create a custom theme for your activity
	 * @param activity the activity
	 */
	public ActionBarColorManager(Activity activity) {
		
		this.activity= activity;
	}
	
	/**
	 * Sets the action bar color.
	 * 
	 * @param color the new action bar color
	 */
	public void setActionBarColor(String color)
	{
		ActionBar actionBar = activity.getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(ACTION_BAR_COLOR)));
	}

}
