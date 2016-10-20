package com.example.huyongqiang.alarmchoosetime;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

/**
 * Created by huyongqiang on 2016/10/20.
 */

public class TimeIntervalDialog extends Dialog implements OnTimeChangedListener {

    private TimeIntervalInterface mTimeIntervalInterface;
    private Context mContext;
    private TimePicker mStartTimePicker, mEndTimePicker;
    private int mStartHour, mStartMinute, mEndHour, mEndMinute;

    /**
     * 点击事件
     */
    private Button.OnClickListener clickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.apply:
                    dismiss();
                    //参数传递给接口的方法
                    mTimeIntervalInterface.apply(mStartHour, mStartMinute, mEndHour, mEndMinute);
                    break;
                case R.id.cancel:
                    dismiss();
                    mTimeIntervalInterface.cancel();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 构造方法一：可自行改动，开始时间，结束时间
     *
     * @param context
     * @param timeIntervalInterface
     * @param startHour
     * @param startMinute
     * @param endHour
     * @param endMinute
     */
    public TimeIntervalDialog(Context context, TimeIntervalInterface timeIntervalInterface,
                              int startHour, int startMinute, int endHour, int endMinute) {
        super(context);
        mContext = context;
        this.mTimeIntervalInterface = timeIntervalInterface;
        this.mStartHour = startHour;
        this.mStartMinute = startMinute;
        this.mEndHour = endHour;
        this.mEndMinute = endMinute;
    }

    /**
     * 构造方法二：固定开始时间和结束时间
     *
     * @param context
     * @param timeIntervalInterface
     */
    public TimeIntervalDialog(Context context, TimeIntervalInterface timeIntervalInterface) {
        this(context, timeIntervalInterface, 0, 0, 23, 59);
    }

    /**
     * 控件的初始化布局文件
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_time_dialog);

        //继承了dialog才有这样的设置取消的属性
        setCancelable(true);

        /*控件初始化*/
        //dialog自己有初始化这个title
        setTitle(mContext.getString(R.string.set_accept_time));
        //初始化开始时间的timepicker
        mStartTimePicker = (TimePicker) findViewById(R.id.startTimePicker);
        mStartTimePicker.setIs24HourView(true);
        mStartTimePicker.setCurrentHour(mStartHour);
        mStartTimePicker.setCurrentMinute(mStartMinute);
        mStartTimePicker.setOnTimeChangedListener(this);
        //初始化结束时间的timepicker
        mEndTimePicker = (TimePicker) findViewById(R.id.endTimePicker);
        mEndTimePicker.setIs24HourView(true);
        mEndTimePicker.setCurrentHour(mEndHour);
        mEndTimePicker.setCurrentMinute(mEndMinute);
        mEndTimePicker.setOnTimeChangedListener(this);
        //确定按钮
        Button applyBtn = (Button) findViewById(R.id.apply);
        applyBtn.setOnClickListener(clickListener);
        //取消按钮
        Button cancelBtn = (Button) findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(clickListener);
    }

    /**
     * 时间选择控件timepicker的改变监听事件
     *
     * @param view
     * @param hourOfDay
     * @param minute
     */
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        //如果是：开始时间的timepicker
        if (view == mStartTimePicker) {
            //将时间的参数传递出来
            mStartHour = hourOfDay;
            mStartMinute = minute;
        } else if (view == mEndTimePicker) {
            mEndHour = hourOfDay;
            mEndMinute = minute;
        }
    }

    /**
     * 接口
     */
    interface TimeIntervalInterface {
        void apply(int startHour, int startMin, int endHour, int endMin);

        void cancel();
    }
}

