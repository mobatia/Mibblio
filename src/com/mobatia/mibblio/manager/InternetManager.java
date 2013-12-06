package com.mobatia.mibblio.manager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class InternetManager.,handling GET ,POST method api,image upload api and image download api 
 */
public class InternetManager {

	/** The httpclient. */
	HttpClient httpclient;

	/** The httpget. */
	HttpGet httpget;

	/** The httpresponse. */
	HttpResponse httpresponse;

	/** The httpentity. */
	HttpEntity httpentity;

	/** The url. */
	String url;

	/** The response. */
	String response = null;

	/** The data. */
	byte[] data = null;

	/** The Is server conn. */
	public boolean IsServerConn = true;

	/**
	 * Instantiates a new internet manager.
	 * 
	 * @param url
	 *            the url
	 */
	public InternetManager(String url) {
		this.url = url;
	}

	/**
	 *Function for calling get apis 
	 * 
	 * @return the string
	 */
	public String URLRequest() {
		httpclient = new DefaultHttpClient();
		try {
			httpget = new HttpGet(url);
			httpresponse = httpclient.execute(httpget);
			httpentity = httpresponse.getEntity();
			response = EntityUtils.toString(httpentity);
			response = response.trim();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			IsServerConn = false;
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			IsServerConn = false;
		}
		return response;

	}

	/**
	 * File request.
	 * 
	 * @return the byte[]
	 */
	public byte[] fileRequest() {
		httpclient = new DefaultHttpClient();

		httpget = new HttpGet(url);
		try {
			httpresponse = httpclient.execute(httpget);
			httpentity = httpresponse.getEntity();
			data = EntityUtils.toByteArray(httpentity);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.v("", "File downloaded URL: " + url);
		return data;
	}

	/**
	 * Check internet connection, for checking the internet connection in the device .
	 * 
	 * @param context
	 *            the context
	 * @return the boolean
	 */
	public static Boolean checkInternetConnection(Context context) {
		ConnectivityManager connec = (ConnectivityManager) context
				.getSystemService(context.CONNECTIVITY_SERVICE);
		if (connec.getNetworkInfo(0).isConnectedOrConnecting()
				|| connec.getNetworkInfo(1).isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}
	}



	/**
	 * Common PostAPI Call For 
	 * 
	 * @param url
	 *            the url
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */

	public String commonPostData(String url, String[] name, String[] value)
			throws Exception {
		/** Create a new HttpClient and Post Header */
		String text = "";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		/** Add your data */
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (int i = 0; i < value.length; i++) {
			System.out.println("values  " + value[i]);
			System.out.println("name  " + name[i]);
			nameValuePairs.add(new BasicNameValuePair(name[i], value[i]));
		}
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		/** Execute HTTP Post Request */
		HttpResponse response = httpclient.execute(httppost);
		InputStream is = response.getEntity().getContent();
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayBuffer baf = new ByteArrayBuffer(20);
		int current = 0;
		while ((current = bis.read()) != -1) {
			baf.append((byte) current);
		}
		/** Convert the Bytes read to a String. */
		text = new String(baf.toByteArray());
		return text;
	}

	/**
	 * Image upload1.
	 * 
	 * @param fileTitle
	 *            the file title
	 * @param fileName
	 *            the file name
	 * @param fileTitle1
	 *            the file title1
	 * @param fileName1
	 *            the file name1
	 * @param URL
	 *            the url
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String imageUpload1(String fileTitle, String fileName,
			String fileTitle1, String fileName1, String URL, String name[],
			String value[]) throws Exception {

		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		DataInputStream inStream = null;
		String line;
		String response = null;
		System.out.println("Image Upload 1");

		

		System.out.println("Image Upload 5");
		String exsistingFileName = fileName;
		String exsistingFileName1 = fileName1;
		// Is this the place are you doing something wrong.

		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "-----------------------*****";

		int bytesRead, bytesAvailable, bufferSize;

		byte[] buffer;

		int maxBufferSize = 1 * 1024 * 1024;

		String responseFromServer = "";

		/*
		 * String urlString = URL_CREATE_POST + "?user_id=" + user_id +
		 * "&survey_id=" + survey_id + "&question_id" + question_id;
		 */

		String urlString = URL;
		// String urlString = URL_CREATE_POST;
		// upload

		try {
			// ------------------ CLIENT REQUEST

			Log.e("fileupload", "Inside second Method");
			// FileInputStream fileInputStream = null ;

			// open a URL connection to the Servlet

			URL url = new URL(urlString);

			// Open a HTTP connection to the URL

			conn = (HttpURLConnection) url.openConnection();

			// Allow Inputs
			conn.setDoInput(true);

			// Allow Outputs
			conn.setDoOutput(true);

			// Don't use a cached copy.
			conn.setUseCaches(false);

			// Use a post method.
			conn.setRequestMethod("POST");

			conn.setRequestProperty("Connection", "Keep-Alive");

			conn.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			// conn.setRequestProperty( "title", "kk");

			dos = new DataOutputStream(conn.getOutputStream());

			// start

			if ((!fileName.equals("")) && (!fileName.contains("http"))) {
				FileInputStream fileInputStream = new FileInputStream(new File(
						exsistingFileName));
				dos.writeBytes(twoHyphens + boundary + lineEnd);
				dos.writeBytes("Content-Disposition: form-data; name=\""
						+ fileTitle + "\";filename=\".jpg\"" + lineEnd);
				dos.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
				// dos.writeBytes(lineEnd);

				Log.e("MediaPlayer", "Headers are written");

				// create a buffer of maximum size

				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				buffer = new byte[bufferSize];

				// read file and write it into form...

				bytesRead = fileInputStream.read(buffer, 0, bufferSize);

				while (bytesRead > 0) {
					dos.write(buffer, 0, bufferSize);
					bytesAvailable = fileInputStream.available();
					bufferSize = Math.min(bytesAvailable, maxBufferSize);
					bytesRead = fileInputStream.read(buffer, 0, bufferSize);
				}

				// send multipart form data necesssary after file data...

				dos.writeBytes(lineEnd);

				fileInputStream.close();
			}

			if ((!fileName1.equals("")) && (!fileName1.contains("http"))) {
				FileInputStream fileInputStream = new FileInputStream(new File(
						exsistingFileName1));
				dos.writeBytes(twoHyphens + boundary + lineEnd);
				dos.writeBytes("Content-Disposition: form-data; name=\""
						+ fileTitle1 + "\";filename=\".jpg\"" + lineEnd);
				dos.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
				// dos.writeBytes(lineEnd);

				Log.e("MediaPlayer", "Headers are written");

				// create a buffer of maximum size

				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				buffer = new byte[bufferSize];

				// read file and write it into form...

				bytesRead = fileInputStream.read(buffer, 0, bufferSize);

				while (bytesRead > 0) {
					dos.write(buffer, 0, bufferSize);
					bytesAvailable = fileInputStream.available();
					bufferSize = Math.min(bytesAvailable, maxBufferSize);
					bytesRead = fileInputStream.read(buffer, 0, bufferSize);
				}

				// send multipart form data necesssary after file data...

				dos.writeBytes(lineEnd);

				fileInputStream.close();
			}
			// end..

			for (int i = 0; i < value.length; i++) {

				System.out.println("NAME" + name[i] + "  VAlue==" + value[i]);

				dos.writeBytes(twoHyphens + boundary + lineEnd);
				dos.writeBytes("Content-Disposition: form-data; name=\""
						+ name[i] + "\"" + lineEnd + lineEnd);
				dos.writeBytes(value[i]);
				dos.writeBytes(lineEnd);
				// System.out.println("USER ID="+GlobalConstants.USERID);
			}
			dos.writeBytes(twoHyphens + boundary + lineEnd);

			System.out.println("DATA==" + dos.toString());

			// close streams
			Log.e("fileupload", "File is written");

			dos.flush();
			dos.close();

		} catch (MalformedURLException ex) {
			Log.e("fileupload", "error1: " + ex.getMessage(), ex);
			return "FAil";
		}

		catch (IOException ioe) {
			Log.e("MediaPlayer", "error2: " + ioe.getMessage(), ioe);
			return "" + ioe.getMessage();
		}

		// ------------------ read the SERVER RESPONSE

		try {

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			while ((line = rd.readLine()) != null) {
				// showAlert("Dialoge Box", "Message: " + line,"OK", false);
				System.out.println("Dialog Box" + line);
				response = line;
			}
			rd.close();
		}

		catch (IOException ioex) {
			Log.e("MediaPlayer", "error3: " + ioex.getMessage(), ioex);

			return ioex.getMessage();
		}

		return response;
	}

}
