package com.example.huyongqiang.alarmchoosetime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//此处activity会对timepicker样式产生影响，如果是CompeActivity主题就只能是新的样式，timepicker的样式也是新的
//如果只是activity就可以使用老的timepicker的样式
public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showButton = (Button) findViewById(R.id.set_accept_time);
        showButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //按钮的点击事件就是，显示一个timepicker的弹窗
        new TimeIntervalDialog(this, new TimeIntervalDialog.TimeIntervalInterface() {
            @Override
            public void apply(int startHour, int startMin, int endHour, int endMin) {
                //选择完后的时间就会通过接口传递过来可以给你的参数的值
                //MiPushClient.setAcceptTime(MainActivity.this, startHour, startMin, endHour, endMin, null);
            }

            @Override
            public void cancel() {
                //ignore
            }
        }).show();
    }
}
