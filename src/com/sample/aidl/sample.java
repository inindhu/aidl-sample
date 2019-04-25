package com.sample.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class sample extends Activity {
	/** Called when the activity is first created. */

	IService remoteService;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final MyConnection mConnection = new MyConnection();

		findViewById(R.id.button).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean ret = getApplicationContext().bindService(new Intent(sample.this,MsgService.class),
						mConnection, Context.BIND_AUTO_CREATE);

				Log.d("Sample Activity", "After bind call " + ret);
			}
		});
	}

	public void someMethodInActivity(){
		Log.d("Sample Activity", "someMethodInActivity");
		
	}
	//   private ServiceConnection mConnection = new ServiceConnection() {
	class MyConnection implements ServiceConnection {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.d("Sample Activity", "Inside OnServiceConnected callback");
			remoteService = IService.Stub.asInterface(service);
			try {
				remoteService.registerCallBack(mCallback);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Log.d("Sample Activity", "Before calling fromActivity in Activity");
				remoteService.fromActivity();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//	};


	private IServiceCallBack mCallback = new IServiceCallBack.Stub() {
		
		@Override
		public void fromService() throws RemoteException {
			// TODO Auto-generated method stub
			Log.d("Sample Activity", "Callback from Service");
			someMethodInActivity();
		}
	};
		
}