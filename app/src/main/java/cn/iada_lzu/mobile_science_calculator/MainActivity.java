package cn.iada_lzu.mobile_science_calculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    ClientSocket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt1).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt1:
                socket = new ClientSocket();
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                socket.requestResult(((EditText)findViewById(R.id.et1)).getText().toString(), new ClientSocket.CallOver() {
                    @Override
                    public void onError(final String emsg) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,emsg,Toast.LENGTH_SHORT).show();
                                findViewById(R.id.progressBar).setVisibility(View.GONE);
                            }
                        });
                    }

                    @Override
                    public void onFinished(final String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((TextView)findViewById(R.id.tv1)).setText(result);
                                findViewById(R.id.progressBar).setVisibility(View.GONE);
                            }
                        });
                    }
                });
                break;

        }
    }
}
