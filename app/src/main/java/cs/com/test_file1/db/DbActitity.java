package cs.com.test_file1.db;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cs.com.test_file1.R;

/**
 * Created by chenshuai on 2017/1/2.
 * 数据库的操作无法4种 CRUD
 * C >>>>> Creat 添加    对应的方法是insert
 * R >>>>>Retrieve 查询  对应的方法是select
 * U >>>>>Update 更新    对应的方法是update
 * D >>>>>Delete 删除    对应的方法是delete
 */

public class DbActitity extends Activity implements View.OnClickListener {
    private Button mBtnDb1;
    private MyDatabaseHelper myDatabaseHelper;
    private Button mBtnDb2;
    private Button mBtnDb3;
    private Button mBtnDb4;
    private Button mBtnDb5;
    private SQLiteDatabase database;
    private ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        initView();
    }

    private void initView() {
        myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);
        mBtnDb1 = (Button) findViewById(R.id.btn_db1);

        mBtnDb1.setOnClickListener(this);
        mBtnDb2 = (Button) findViewById(R.id.btn_db2);
        mBtnDb2.setOnClickListener(this);
        mBtnDb3 = (Button) findViewById(R.id.btn_db3);
        mBtnDb3.setOnClickListener(this);
        mBtnDb4 = (Button) findViewById(R.id.btn_db4);
        mBtnDb4.setOnClickListener(this);
        mBtnDb5 = (Button) findViewById(R.id.btn_db5);
        mBtnDb5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_db1:
                //在文件中创建数据库,一共有两个 一个是BookStore.db,另外一个是BookStore.db-journal,后者是数据库的日志文件
                //创建数据库的信息,对数据库进行一些列的操作
                database = myDatabaseHelper.getWritableDatabase();
                //myDatabaseHelper.getReadableDatabase();//这里两个方法一般情况下可以混用,极端情况才分区分,比如内存不足等
                break;
            case R.id.btn_db2:
                contentValues = new ContentValues();
                //开始组装第一组数据
                contentValues.put("作者","大帅");
                contentValues.put("价格",200);
                contentValues.put("页数",300);
                contentValues.put("名字","我是高手");
                //第二个参数用于在未指定添加数据的情况下可以将某些可谓空的值自动赋值为null
                database.insert("BOOK",null, contentValues);

                //contentValues 必须清空下才能执行第二个操作
                contentValues.clear();
                //开始组装第一组数据
                contentValues.put("作者","小帅");
                contentValues.put("价格",20.22);
                contentValues.put("页数",500);
                contentValues.put("名字","我是低手");
                //第二个参数用于在未指定添加数据的情况下可以将某些可谓空的值自动赋值为null
                database.insert("BOOK",null, contentValues);
                contentValues.clear();

                break;
            case R.id.btn_db3:


                contentValues.put("价格",10.99);
                database.update("BOOK",contentValues,"名字=?",new String[]{
                        "我是高手"
                });//用后面连个参数来修改价格,同时如果不加  contentValues.clear();名字在这行的代码,后面的参数名会是之前默认,加了这个就是清晰了

                break;
            case R.id.btn_db4:
                ///注意这里删除数据的时候不需要用到contentValues,这必须注意呢
                //database.delete("BOOK",null,null); 不指定参数就表示删除了里面的数据
                database.delete("BOOK","价格>?",new String []{
                        "100"
                });
                break;
            case R.id.btn_db5:
                Cursor cursor = database.query("BOOK", null, null, null, null, null, null);
                if (cursor.moveToNext()) {
                    do {
                        String authou = cursor.getString(cursor.getColumnIndex("作者"));
                        Double price = cursor.getDouble(cursor.getColumnIndex("价格"));
                        int page = cursor.getInt(cursor.getColumnIndex("页数"));
                        String name = cursor.getString(cursor.getColumnIndex("名字"));

                        Log.d("TTT",authou);
                        Log.d("TTT",price+"");
                        Log.d("TTT",page+"");
                        Log.d("TTT",name);

                    }while (cursor.moveToNext());
                    //最后关闭查询操作
                    cursor.close();
                }

                break;
        }
    }
}
