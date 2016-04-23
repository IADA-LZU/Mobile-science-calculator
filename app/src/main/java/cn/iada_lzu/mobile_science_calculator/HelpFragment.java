package cn.iada_lzu.mobile_science_calculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by hopeful on 16-4-20.
 */
public class HelpFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.help, container, false);
        ((WebView)v.findViewById(R.id.wv_help)).loadUrl("file:///android_asset/help.html");
        return v;
    }
}
