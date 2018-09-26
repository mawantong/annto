package cn.annto;

import android.widget.TextView;

@LayoutBind(R.layout.activity_main)
public class MainActivity extends ActivityUntil {

    @ViewBind(R.id.one)
    public TextView oneText;
    @ViewBind(R.id.two)
    public TextView twoText;


    @Override
    protected void doWrok() {
        oneText.setText("注解测试");
        twoText.setText("注解通过了");
    }
}
