package cn.iada_lzu.mobile_science_calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by hopeful on 16-4-2.
 */
public class ClientSocket {


    private static final String addr = "192.168.1.100";//"219.246.65.134";
    private static final int port = 6666;

    private Thread mainThread;
    private Thread countThread;

    private long timeout;

    private Socket client;

    private String input;
    private String output;

    public ClientSocket() {
        this(0);//default never timeout
    }

    public ClientSocket(long timeout) {
        this.timeout = timeout;
    }

    public void requestResult(String input, final CallOver over) {
        this.input = input;
        mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client = new Socket(addr,port);
                    if(ClientSocket.this.input == null || ClientSocket.this.input.length() == 0) {
                        output = "输入不存在！";
                        over.onError(output);
                        return;
                    }
                    PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
                    writer.println(ClientSocket.this.input);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    output = reader.readLine();
                    writer.close();
                    reader.close();
                    client.close();
                    over.onFinished(output);
                } catch (Exception e) {
                    //call when connected failed
                    output = e.toString();
                    over.onError(output);
                }
                countThread.interrupt();
            }
        });
        countThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(timeout <= 0)
                        return;
                    Thread.sleep(timeout);
                    mainThread.interrupt();
                    over.onError("请求超时！");
                } catch (Exception e) {

                }
            }
        });
        mainThread.start();
        countThread.start();
    }

    public interface CallOver {
        void onError(String emsg);
        void onFinished(String result);
    }
}
