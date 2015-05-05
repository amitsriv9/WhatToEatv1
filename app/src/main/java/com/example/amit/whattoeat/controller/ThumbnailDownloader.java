package com.example.amit.whattoeat.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.example.amit.whattoeat.controller.WebFetcher;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ThumbnailDownloader<Token> extends HandlerThread {
	public static final String TAG = "ThumbnailDownloader";
	private static final int MESSAGE_DOWNLOAD = 0;
	private static final int MESSAGE_PRELOAD = 1;
	private static final int CACHE_SIZE = 400;
	
	Handler mHandler;
	Map<Token, String> requestMap =
			Collections.synchronizedMap(new HashMap<Token, String>());
	Handler mResponseHandler;
	Listener<Token> mListener;
	LruCache<String, Bitmap> mCache;
	
	public interface Listener<Token> {
		void onThumbnailDownloaded(Token token, Bitmap thumbnail);
	}

    //JUN listener would be passed in to handle the response (in main thread) when downloading is done
	public void setListener(Listener<Token> listener) {
		mListener = listener;
	}
	
	public ThumbnailDownloader(Handler responseHandler) {
		super (TAG);
		mResponseHandler = responseHandler;
		mCache = new LruCache<String, Bitmap>(CACHE_SIZE);
	}
	
	@Override
	protected void onLooperPrepared() {
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == MESSAGE_DOWNLOAD) {
					@SuppressWarnings("unchecked")
					Token token = (Token)msg.obj;
					Log.i(TAG, "Got a request for url: " + requestMap.get(token));
					handleRequest(token);
				} else if(msg.what == MESSAGE_PRELOAD) {
					String url = (String)msg.obj;
					preload(url);
				}
			}
		};
	}

    //todo JUN called by GalleryItemAdapter in its getView() -- p424
	public void queueThumbnail(Token token, String url) {
		Log.i(TAG, "Got an URL: " + url);
		requestMap.put(token, url);
		
		mHandler
			.obtainMessage(MESSAGE_DOWNLOAD, token)
			.sendToTarget();
	}
	
	public void queuePreload(String url) {
		if (mCache.get(url) != null)return;
		
		mHandler
			.obtainMessage(MESSAGE_PRELOAD, url)
			.sendToTarget();
	}
	
	public Bitmap checkCache(String url) {
		return mCache.get(url);
	}

    //todo loading the pics here JUN
	private Bitmap getBitmap(String url) {

		try {
			byte[] bitmapBytes = new WebFetcher().getUrlBytes(url);
			Bitmap bitmapDecode = BitmapFactory
					.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
			Log.i(TAG, "bitmap created");
			return bitmapDecode;
		} catch (IOException ioe) {
			Log.e(TAG, "Error downloading image", ioe);
		}
		return null;
	}
	
	private void preload(final Token token) {
		String url = requestMap.get(token);
		preload(url);
	}
	
	private void preload(String url) {
		if(url == null)
			return;
		if (mCache.get(url) != null)
			return;
		Bitmap bitmap = getBitmap(url);
		if (bitmap != null)
			mCache.put(url, bitmap);
	}
	
	private void handleRequest(final Token token) {
            //JUN get the bitmap IDed by the token
			final String url = requestMap.get(token);
			if(url == null)
				return;
			if (mCache.get(url) == null)
				preload(token);	
			final Bitmap bitmap = mCache.get(url);

        //JUN instead of run the runable of the target handler, run our customized one (in main thread) to update UI
			mResponseHandler.post(new Runnable() {
				public void run() {
					if (requestMap.get(token) != url)
						return;
					
					requestMap.remove(token);
					mListener.onThumbnailDownloaded(token, bitmap);
				}
			});
	}

    //clear queue in case users rotate screen -- original gridviews no longer exist.
	public void clearQueue() {
		mHandler.removeMessages(MESSAGE_DOWNLOAD);
		requestMap.clear();
	}
	
}
