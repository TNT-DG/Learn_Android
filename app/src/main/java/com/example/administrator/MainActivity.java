package com.example.administrator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String Tag ="MainActivity";
    //声明一个button和一个textview
    Button mButton;
    TextView mText;
    ImageView mImg;
    Animation mAnim;
    SoundPool mSoundPool;
    int click;
    Animation mFadein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(Tag,"MainActivity onCreate");
        Log.d(Tag,"MainActivity onCreate");
        Log.i(Tag,"MainActivity onCreate");
        Log.w(Tag,"MainActivity onCreate");
        Log.e(Tag,"MainActivity onCreate");

        //给button和textview赋值
        mButton=(Button) findViewById(R.id.button1);
        mText=(TextView) findViewById(R.id.textView1);
        mImg=(ImageView) findViewById(R.id.imageView);
        mAnim= AnimationUtils.loadAnimation(this,R.anim.zoomin);
        mAnim.setDuration(1000);
        mSoundPool=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        click=mSoundPool.load(this,R.raw.click,1);
        mFadein=AnimationUtils.loadAnimation(this,R.anim.fadein);
        mFadein.setDuration(2000);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText.setText("Yes");
                mText.setAnimation(mFadein);

                mImg.startAnimation(mAnim);
                playSound();
//                toast("这是一个toast提示");

                Log.e(Tag,"MainActivity onCreate");
//                startAnotherActivity();
                toast("跳转成功");


            }
        });

    }


    //跳转到第二个界面
    private void startAnotherActivity(){
        Intent intent =new Intent(this,SecondAcitivity.class);
        startActivity(intent);
    }


//播放声音
    private void playSound(){

        mSoundPool.play(click,1,1,0,0,1f);

    }
//toast提醒
    private  void toast(String content){

         Toast mToast =  Toast.makeText(this,content,Toast.LENGTH_LONG);
         mToast.setGravity(Gravity.CENTER,0,0);
         mToast.show();
    }
//showDialog提醒
    private void showDialog(){
        AlertDialog.Builder mBuilder= new AlertDialog.Builder(this);
        mBuilder.setTitle("温馨提示");
        mBuilder.setMessage("确定退出？");
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MainActivity.this.finish();
            }
        });
        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.create().show();
    }

    //重写退出按钮
    @Override
    public void onBackPressed(){
        showDialog();
    }
}
