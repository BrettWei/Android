package com.example.mydice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Handler mHandler; 
	
    static int screen_width;        //�豸��Ļ�Ŀ�     
    static int screen_height;       //�豸��Ļ�ĸ�
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screen_width = metric.widthPixels;  // ��Ļ��ȣ����أ�
        screen_height = metric.heightPixels;  // ��Ļ�߶ȣ����أ�
        
        HandlerThread thread = new HandlerThread("MyHandlerThread"); 
        thread.start();//����һ��HandlerThread�������� 
        mHandler = new Handler(thread.getLooper());//ʹ��HandlerThread��looper���󴴽�Handler�����ʹ��Ĭ�ϵĹ��췽�������п�������UI�߳� 
        mHandler.post(mBackgroundRunnable);//���߳�post��Handler�� 
/*
        LayoutParams lp;
        //lp = new AbsoluteLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0);

        Button btnStart = (Button)findViewById(R.id.btnStart);

        lp = (LayoutParams) btnStart.getLayoutParams();
        
        lp.topMargin = 50;//(screen_width - btnStart.getWidth()) / 2;
        lp.leftMargin = 50;
        lp.rightMargin = 50;
        lp.bottomMargin = 50;
        //lp.y = (screen_height - btnStart.getHeight()) / 2;
        
        btnStart.setLayoutParams(lp);
        */
        //btnStart.setPadding((screen_width - btnStart.getWidth()) / 2, , 100, 0); //;
        //btnStart.setPadding(50, 50, 50, 50); //;
        //btnStart.setPivotX(screen_height);
        
    }

    public void btnStart(View view){
        Button btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setEnabled(false);

    	//ImageView img1 = (ImageView)findViewById(R.id.img1);
    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	  //ʵ�ֺ�ʱ�������߳� 
	  Runnable mBackgroundRunnable = new Runnable() { 
	
		  @Override 
		  public void run() { 
		  //----------ģ���ʱ�Ĳ�������ʼ--------------- 
			  try { 
				  Thread.sleep(3000);   
				  Button btnStart = (Button)findViewById(R.id.btnStart);
			      btnStart.setEnabled(true);
 
			  } catch (InterruptedException e) { 
			  e.printStackTrace(); 
			  } 
		  //----------ģ���ʱ�Ĳ���������--------------- 
		  }
	  };
  
	  @Override 
	  protected void onDestroy() { 
		  super.onDestroy(); 
	  //�����߳� 
	  	mHandler.removeCallbacks(mBackgroundRunnable); 
	  } 
} 