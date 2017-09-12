package com.example.remoteservicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class RemoteService extends Service
{
	public final static String TAG = "RemoteService";

	private final IRemoteService.Stub mBinder = new IRemoteService.Stub()
	{

		@Override
		public ParcelableData process(ParcelableData data) throws RemoteException
		{
			Log.i(TAG, "Before processing, data: " + data);
			data.process();
			ParcelableData result = new ParcelableData(data);
			Log.i(TAG, "After processing, result: " + result);
			return result;
		}
	};

	@Override
	public IBinder onBind(Intent intent)
	{
		return mBinder;
	}
}
