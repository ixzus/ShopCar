package com.liuzhuangzhuang.shopcar;

import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by liuzhuang on 15/11/9.
 */
public class MainActivity extends AppCompatActivity {

    private TopView topView;
    private Button button;
    private FloatingActionButton fab;
    private TextView numTv;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topView = TopView.attach2Window(this);
        button = (Button) findViewById(R.id.button);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        numTv = (TextView) findViewById(R.id.num_tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] start = new int[]{0, 0};
                button.getLocationInWindow(start);
                int[] end = new int[]{0, 0};
                fab.getLocationInWindow(end);

                PointF p0 = new PointF(start[0], start[1]); // 起点
                PointF p1 = new PointF(end[0], start[1]); // 第二点
                PointF p2 = new PointF(end[0], end[1]); // 终点

                count++;
                TopView.AnimationInfo animationInfo = new TopView.AnimationInfo.Builder().callback(
                        new TopView.AnimationCallback() {
                            @Override
                            public void animationEnd() {
                                // 更新显示数字
                                numTv.setText("购物车数量 " + count);
                            }
                        })
                        .resId(R.drawable.ic_launcher)
                        .p0(p0)
                        .p1(p1)
                        .p2(p2)
                        .create();
                topView.add(animationInfo);
            }
        });

        // test
        this.findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button.performClick();
                        handler.postDelayed(this, 100);
                    }
                }, 100);
            }
        });
    }
}
