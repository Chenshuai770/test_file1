package cs.com.test_file1.share;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cs.com.test_file1.R;

/**
 * Created by chenshuai on 2017/1/2.
 */

public class ShareActitity extends Activity implements View.OnClickListener {
    private EditText mEtShare;
    private Button mBtnShare1;
    private Button mBtnShare2;
    private Button mBtnShare3;
    private TextView mTvShare;
    private EditText mEtShare1;
    private EditText mEtShare2;
    private EditText mEtShare3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        initView();
    }

    private void initView() {

        mBtnShare1 = (Button) findViewById(R.id.btn_share1);
        mBtnShare2 = (Button) findViewById(R.id.btn_share2);
        mBtnShare3 = (Button) findViewById(R.id.btn_share3);
        mTvShare = (TextView) findViewById(R.id.tv_share);

        mBtnShare1.setOnClickListener(this);
        mBtnShare2.setOnClickListener(this);
        mBtnShare3.setOnClickListener(this);
        mEtShare1 = (EditText) findViewById(R.id.et_share1);
        mEtShare1.setOnClickListener(this);
        mEtShare2 = (EditText) findViewById(R.id.et_share2);
        mEtShare2.setOnClickListener(this);
        mEtShare3 = (EditText) findViewById(R.id.et_share3);
        mEtShare3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share1:
                String mEtName = mEtShare1.getText().toString();
                int mEtAge = Integer.parseInt(mEtShare2.getText().toString());
                //edit是编辑的意思
                SharedPreferences share1 = getSharedPreferences("data2", MODE_PRIVATE);
                SharedPreferences.Editor edit1 = share1.edit();
                edit1.putString("name", mEtName);
                edit1.putInt("age", mEtAge);
                edit1.putBoolean("marreid", false);
                edit1.apply();

                break;
            case R.id.btn_share2:

                SharedPreferences share2 = getSharedPreferences("data2", 0);
                String name = share2.getString("name","");//这里第二个参数是默认值
                int age = share2.getInt("age", 0);//int 默认值参数是0
                boolean marreid = share2.getBoolean("marreid", false);
                mTvShare.setText(name+">>>"+age+">>>"+marreid);

                break;
            case R.id.btn_share3:

                break;
        }
    }
}



