package cn.iada_lzu.mobile_science_calculator;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hopeful on 16-4-20.
 */
public class PolyFun {

    static MainActivity activity;

    /* Solving Equation */
    public static void fun1() {
        final EditText in = (EditText) activity.findViewById(R.id.et_poly_in);
        final TextView out = (TextView) activity.findViewById(R.id.tv_poly_out);
        final String sin = in.getText().toString().trim();
        String msg = ++activity.requetsCount + "|10|" + sin.trim() +"|1";
        //out.append("你输入了方程："+sin+"\n");
        activity.socket.requestResult(msg, new ClientSocket.CallOver() {
            @Override
            public void onError(final String emsg) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        out.append("运行错误!错误信息："+emsg+"\n\n");
                    }
                });
            }
            @Override
            public void onFinished(String result) {
                final int code = Integer.valueOf(result.substring(0,result.indexOf('|')));
                final String msg = result.substring(result.indexOf('|')+1);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(code == 0)
                            out.append("运行错误!错误信息："+msg.substring(msg.indexOf("|")+1)+"\n\n");
                        else
                            out.append("方程"+sin+"的解为："+msg+"\n");
                    }
                });
            }
        });
    }

    /* Regression Analysis */
    public static void fun2() {
        final EditText in = (EditText) activity.findViewById(R.id.et_poly_in);
        final TextView out = (TextView) activity.findViewById(R.id.tv_poly_out);
        String sin = in.getText().toString();

        String xs = "", ys = "";
        int size = 0;
        String msg = ++activity.requetsCount + "|11|";
        while(true) {
            int linesize = sin.indexOf('\n');
            if(linesize == -1)
            {
                if(sin.length()<=0)
                    break;
                int i = sin.indexOf(' ');
                xs += sin.substring(0,i+1);
                sin = sin.substring(i+1);
                ys += sin;
                size ++;
                break;
            }
            int i = sin.indexOf(' ');
            xs += sin.substring(0,i+1);
            ys += sin.substring(i+1,linesize)+" ";
            size ++;
            sin = sin.substring(linesize+1);
        }
        msg += size + " " + xs + ys;
        activity.socket.requestResult(msg, new ClientSocket.CallOver() {
            @Override
            public void onError(final String emsg) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        out.append("运行错误!错误信息："+emsg+"\n\n");
                    }
                });
            }

            @Override
            public void onFinished(String result) {
                final int code = Integer.valueOf(result.substring(0,result.indexOf('|')));
                final String msg = result.substring(result.indexOf('|')+1);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(code == 0)
                            out.append("运行错误!错误信息："+msg+"\n\n");
                        else
                            out.append("回归分析结果："+msg+"\n");
                    }
                });
            }
        });
    }
}
