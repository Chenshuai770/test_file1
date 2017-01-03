package cs.com.test_file1.file;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import cs.com.test_file1.R;

public class FileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtMain;
    private Button mBtnMain1;
    private Button mBtnMain2;
    private TextView mTvMian;
    private Button mBtn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        mEtMain = (EditText) findViewById(R.id.et_main);
        mBtnMain1 = (Button) findViewById(R.id.btn_main1);
        mBtnMain2 = (Button) findViewById(R.id.btn_main2);
        mTvMian = (TextView) findViewById(R.id.tv_mian);

        mBtnMain1.setOnClickListener(this);
        mBtnMain2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main1:
                String string = mEtMain.getText().toString();
                save(string);
                break;
            case R.id.btn_main2:

                String read = read();
                if (read == null) {
                    Toast.makeText(this, "没有数据,请写入数据", Toast.LENGTH_SHORT).show();
                }else {
                    mTvMian.setText(read);
                }

                break;
            case R.id.btn3:


                File data1 = new File(getApplicationContext().getFilesDir(), "data1");
                DeleteFile(data1);
                //同时手动清空文件
                mTvMian.setText("");
                break;
        }
    }

    private String read() {
        FileInputStream dada1 = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = null;
        try {
            dada1 = openFileInput("data1");//写出文件的操作,和openfileout是对应的操作
            InputStreamReader inputStreamReader = new InputStreamReader(dada1);
            stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder!=null?stringBuilder.toString():null;
    }

    //保存数据的操作,文件保存在data.data.包名.data1文件里面
    public void save(String data) {

        FileOutputStream out = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            out = openFileOutput("data1", Context.MODE_APPEND);//借助文件输出操作打开流
            outputStreamWriter = new OutputStreamWriter(out);//包装流
            bufferedWriter = new BufferedWriter(outputStreamWriter);//写出数据
            bufferedWriter.write(data);//调用这个方法写出数据

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();//数据全写在buf里面了
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //调用ui线程更新吐司
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(FileActivity.this, "数据已经保存在+data.data.cs.com.test_file1.data1文件里面", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void DeleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
           /* for (File f : childFile) {
                DeleteFile(f);
            }
            file.delete();*/
            for (int i = 0; i < childFile.length; i++) {
                //file下面的子文件删除
                DeleteFile(childFile[i]);
            }
            //删除父文件夹
            file.delete();
        }
    }


}
