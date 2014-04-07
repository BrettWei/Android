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
	
    static int screen_width;        //设备屏幕的宽     
    static int screen_height;       //设备屏幕的高
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screen_width = metric.widthPixels;  // 屏幕宽度（像素）
        screen_height = metric.heightPixels;  // 屏幕高度（像素）
        
        HandlerThread thread = new HandlerThread("MyHandlerThread"); 
        thread.start();//创建一个HandlerThread并启动它 
        mHandler = new Handler(thread.getLooper());//使用HandlerThread的looper对象创建Handler，如果使用默认的构造方法，很有可能阻塞UI线程 
        mHandler.post(mBackgroundRunnable);//将线程post到Handler中 
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

	  //实现耗时操作的线程 
	  Runnable mBackgroundRunnable = new Runnable() { 
	
		  @Override 
		  public void run() { 
		  //----------模拟耗时的操作，开始--------------- 
			  try { 
				  Thread.sleep(3000);   
				  Button btnStart = (Button)findViewById(R.id.btnStart);
			      btnStart.setEnabled(true);
 
			  } catch (InterruptedException e) { 
			  e.printStackTrace(); 
			  } 
		  //----------模拟耗时的操作，结束--------------- 
		  }
	  };
  
	  @Override 
	  protected void onDestroy() { 
		  super.onDestroy(); 
	  //销毁线程 
	  	mHandler.removeCallbacks(mBackgroundRunnable); 
	  } 
} 