package cn.iada_lzu.mobile_science_calculator;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hopeful on 16-4-20.
 */
public class MatrixFun {

    static MainActivity activity;

    /* Transposition */
    public static void fun1() {
        final EditText in = (EditText) activity.findViewById(R.id.et_matrix_in);
        final TextView out = (TextView) activity.findViewById(R.id.tv_matrix_out);

        final String imsg = ++activity.requetsCount + "|00|" + getMatrix(in.getText().toString());

        activity.socket.requestResult(imsg, new ClientSocket.CallOver() {
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
                            out.append("结果："+msg+"\n");
                    }
                });
            }
        });
    }

    public static void fun2() {
        final EditText in = (EditText) activity.findViewById(R.id.et_matrix_in);
        final TextView out = (TextView) activity.findViewById(R.id.tv_matrix_out);

        final String imsg = ++activity.requetsCount + "|01|" + getMatrix(in.getText().toString());

        activity.socket.requestResult(imsg, new ClientSocket.CallOver() {
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
                            out.append("结果："+msg+"\n");
                    }
                });
            }
        });
    }

    public static void fun3() {
        final EditText in = (EditText) activity.findViewById(R.id.et_matrix_in);
        final TextView out = (TextView) activity.findViewById(R.id.tv_matrix_out);

        final String imsg = ++activity.requetsCount + "|02|" + getMatrix(in.getText().toString());

        activity.socket.requestResult(imsg, new ClientSocket.CallOver() {
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
                            out.append("结果："+msg+"\n");
                    }
                });
            }
        });
    }

    public static void fun4() {
        final EditText in = (EditText) activity.findViewById(R.id.et_matrix_in);
        final TextView out = (TextView) activity.findViewById(R.id.tv_matrix_out);

        final String imsg = ++activity.requetsCount + "|03|" + getMatrix(in.getText().toString());

        activity.socket.requestResult(imsg, new ClientSocket.CallOver() {
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
                            out.append("结果："+msg+"\n");
                    }
                });
            }
        });
    }

    public static void fun5() {
        final EditText in = (EditText) activity.findViewById(R.id.et_matrix_in);
        final TextView out = (TextView) activity.findViewById(R.id.tv_matrix_out);

        final String imsg = ++activity.requetsCount + "|04|" + getEquations(in.getText().toString());

        activity.socket.requestResult(imsg, new ClientSocket.CallOver() {
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
                            out.append("结果："+msg+"\n");
                    }
                });
            }
        });
    }


    private static String getMatrix(String in) {
        String out = "";
        out += "[";
        int xsize = 0, ysize = 0;
        int i = 0;
        while(in.charAt(i) != '\n') {
            if(in.charAt(i) == ' ') xsize++;
            i++;
        }
        xsize ++;
        while(true) {
            int linesize = in.indexOf('\n');
            if(linesize == -1)
                break;
            out += "[";

            out += in.substring(0,linesize).replaceAll(" ",",");
            out += "]";
            ysize ++;
            in = in.substring(linesize+1);
        }
        out += "["+in.replaceAll(" ",",")+"]]";
        ysize ++;
        out = "("+xsize+","+ysize+")"+out;
        return out;
    }

    private static String getEquations(String in) {
        String out = "";
        out += "[";
        int xsize = 0, ysize = 0;
        int i = 0;
        while(in.charAt(i) != '\n') {
            if(in.charAt(i) == ' ') xsize++;
            i++;
        }
        String vf="";
        while(true) {
            int linesize = in.indexOf('\n');
            if(linesize == -1)
                break;
            String temp = in.substring(0,linesize);
            out += "[";
            out += temp.substring(0,temp.lastIndexOf(' ')).replaceAll(" ",",");
            vf += temp.substring(temp.lastIndexOf(' ')+1)+",";
            out += "]";
            ysize ++;
            in = in.substring(linesize+1);
        }
        out += "["+in.substring(0,in.lastIndexOf(' ')).replaceAll(" ",",")+"]]";
        vf+=in.substring(in.lastIndexOf(' ')+1);
        ysize ++;
        out = "("+xsize+","+ysize+")"+out+"|"+vf;
        return out;
    }
}
