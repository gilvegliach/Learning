package com.example.remoteservicetest;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener
{
	EditText mEditText;
	TextView mText;
	Button mButton;

	IRemoteService mService;
	ServiceConnection mServiceConnection = new ServiceConnection()
	{
		@Override
		public void onServiceConnected(ComponentName name, IBinder service)
		{
			mService = IRemoteService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			mService = null;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mEditText = (EditText) findViewById(R.id.edit_text);
		mText = (TextView) findViewById(R.id.text);
		mButton = (Button) findViewById(R.id.button);
		mButton.setOnClickListener(this);
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		Intent intent = new Intent(this, RemoteService.class);
		bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		unbindService(mServiceConnection);
	}

	@Override
	public void onClick(View v)
	{
		if (mService == null)
			return;

		try
		{
			int aInt = (int) (System.currentTimeMillis() / 1000);
			String aString = mEditText.getText().toString();
			ParcelableData input = new ParcelableData(aInt, aString);
			ParcelableData output = mService.process(input);
			mText.setText(output.toString());
		}
		catch (RemoteException e)
		{
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			mText.setText("Error:\n" + sw.toString());
		}
	}
}
