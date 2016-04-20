package cn.iada_lzu.mobile_science_calculator;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hopeful on 16-4-20.
 */
public class PolyFun {

    static MainActivity activity;

    /* Solving Equations */
    public static void fun1() {
        EditText in = (EditText) activity.findViewById(R.id.et_poly_in);
        final TextView out = (TextView) activity.findViewById(R.id.tv_poly_out);
        final String sin = in.getText().toString();
        String msg = ++activity.requetsCount + "|0|" + sin;
        out.append("你输入了方程："+sin+"\n");
        activity.socket.requestResult(msg, new ClientSocket.CallOver() {
            @Override
            public void onError(final String emsg) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        out.append("运行错误："+emsg+"\n");
                    }
                });
            }

            @Override
            public void onFinished(String result) {
                final String msg = result;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        out.append("方程"+sin+"的解为："+msg+"\n");
                    }
                });
            }
        });
    }
}
