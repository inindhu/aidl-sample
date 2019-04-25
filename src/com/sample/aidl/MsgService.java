package com.sample.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class MsgService extends Service {

	final RemoteCallbackList<IServiceCallBack> mCallbacks = new RemoteCallbackList<IServiceCallBack>();

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("AIDL service", "Oncreate");
		startActivity();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	public void startActivity(){
		Log.d("AIDL service", "startActivity");
		Intent phoneintent = new Intent();
		phoneintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		phoneintent.setClassName("com.sample.aidl", "com.sample.aidl.sample");
		startActivity(phoneintent);
	}

	public void fromActivity1(){
		Log.d("AIDL service", "fromActivity1");
		Log.d("AIDL service", "Now to call someMethodInActivity");
		try {
			int N = mCallbacks.beginBroadcast();
			Log.d("AIDL service", "mCallBacks N value = " + N);
			Log.d("AIDL service", "Called someMethodInActivity");
			mCallbacks.getBroadcastItem(0).fromService();
			mCallbacks.finishBroadcast();

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private final IService.Stub mBinder = new IService.Stub() {

		@Override
		public void fromActivity() throws RemoteException {
			// TODO Auto-generated method stub
			Log.d("AIDL service", "fromActivity method called from Activity");
			fromActivity1();
		}

		@Override
		public int getPid() throws RemoteException {
			// TODO Auto-generated method stub
			Log.d("AIDL service", "getPid method called from Activity");
			return 0;
		}

		@Override
		public void registerCallBack(IServiceCallBack cb)
				throws RemoteException {
			// TODO Auto-generated method stub
			if(cb!=null){
				Log.d("AIDL service", "registerCallBack registering");
				mCallbacks.register(cb);
			}
		}
	};


}
