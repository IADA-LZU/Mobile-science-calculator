package cn.iada_lzu.mobile_science_calculator;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements OnClickListener {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private LinearLayout mTabMatrix;
    private LinearLayout mTabPoly;

    private TextView MatrixText;
    private TextView PolyText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();

        setSelect(0);
    }

    private void initEvent() {
        mTabMatrix.setOnClickListener(this);
        mTabPoly.setOnClickListener(this);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabMatrix = (LinearLayout) findViewById(R.id.id_tab_matrix);
        mTabPoly = (LinearLayout) findViewById(R.id.id_tab_polynomial);

        MatrixText = (TextView) findViewById(R.id.matrix_text);
        PolyText = (TextView) findViewById(R.id.polynomial_text);

        mFragments = new ArrayList<Fragment>();
        Fragment mTab01 = new MatrixFragment();
        Fragment mTab02 = new PolyFragment();

        mFragments.add(mTab01);
        mFragments.add(mTab02);


        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_matrix:
                setSelect(0);
                break;
            case R.id.id_tab_polynomial:
                setSelect(1);
                break;

            default:
                break;
        }
    }

    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    private void setTab(int i)
    {
        resetImgs();
        // 设置图片为亮色
        // 切换内容区域
        switch (i)
        {
            case 0:
                MatrixText.setTextColor(this.getResources().getColor(R.color.blue));
                break;
            case 1:
                PolyText.setTextColor(this.getResources().getColor(R.color.blue));
                break;

        }
    }

    /**
     * 切换图片至暗色
     */
    private void resetImgs() {
        MatrixText.setTextColor(this.getResources().getColor(R.color.half_transparent));
        PolyText.setTextColor(this.getResources().getColor(R.color.half_transparent));
    }

}
