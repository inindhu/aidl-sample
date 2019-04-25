package com.sample.aidl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class sample2 extends Activity {
    /** Called when the activity is first created. */
	
	IService remoteService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 startService(new Intent(sample2.this,MsgService.class));
			        
			     Log.d("Sample Activity 2", "After start Service");
			}
		});
        
    }
    
  
	
	
}