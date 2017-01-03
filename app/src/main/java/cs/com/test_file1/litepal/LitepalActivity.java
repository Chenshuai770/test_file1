package cs.com.test_file1.litepal;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import cs.com.test_file1.R;

/**
 * Created by chenshuai on 2017/1/3.
 */

public class LitepalActivity extends Activity implements View.OnClickListener {
    private Button mBtnLp1;
    private Button mBtnLp2;
    private Button mBtnLp3;
    private Button mBtnLp4;
    private Button mBtnLp5;
    private Book book;
    private Button mBtnLp6;
    private Button mBtnLp7;
    private Button mBtnLp8;
    private Button mBtnLp9;
    private Button mBtnLp10;
    private Button mBtnLp11;
    private Button mBtnLp12;
    private Button mBtnLp13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_literpal);
        initView();
    }

    private void initView() {
        mBtnLp1 = (Button) findViewById(R.id.btn_lp1);
        mBtnLp2 = (Button) findViewById(R.id.btn_lp2);
        mBtnLp3 = (Button) findViewById(R.id.btn_lp3);
        mBtnLp4 = (Button) findViewById(R.id.btn_lp4);
        mBtnLp5 = (Button) findViewById(R.id.btn_lp5);

        mBtnLp1.setOnClickListener(this);
        mBtnLp2.setOnClickListener(this);
        mBtnLp3.setOnClickListener(this);
        mBtnLp4.setOnClickListener(this);
        mBtnLp5.setOnClickListener(this);
        mBtnLp6 = (Button) findViewById(R.id.btn_lp6);
        mBtnLp6.setOnClickListener(this);
        mBtnLp7 = (Button) findViewById(R.id.btn_lp7);
        mBtnLp7.setOnClickListener(this);
        mBtnLp8 = (Button) findViewById(R.id.btn_lp8);
        mBtnLp8.setOnClickListener(this);
        mBtnLp9 = (Button) findViewById(R.id.btn_lp9);
        mBtnLp9.setOnClickListener(this);
        mBtnLp10 = (Button) findViewById(R.id.btn_lp10);
        mBtnLp10.setOnClickListener(this);
        mBtnLp11 = (Button) findViewById(R.id.btn_lp11);
        mBtnLp11.setOnClickListener(this);
        mBtnLp12 = (Button) findViewById(R.id.btn_lp12);
        mBtnLp12.setOnClickListener(this);
        mBtnLp13 = (Button) findViewById(R.id.btn_lp13);
        mBtnLp13.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_lp1:
                // 创建数据库的信息,对数据库进行一些列的操作 比原始数据库操作慢了些,但是挺快的
                Connector.getDatabase();
                break;
            case R.id.btn_lp2:
                book = new Book();
                book.setName("第一行代码");
                book.setAuthor("大帅");
                book.setPages(454);
                book.setPrice(50.44);
                book.save();
                book.clearSavedState();//这行代码如果不设置,只会添加下面的代码,不会更新其他数据

                book.setName("第二行代码");
                book.setAuthor("小帅");
                book.setPages(400);
                book.setPrice(30.99);
                book.save();
                book.clearSavedState();

                break;
            case R.id.btn_lp3:
                book = new Book();

                book.setPages(100);
                book.setPrice(17.44);
                book.updateAll("name=? and author=?", "第一行代码", "大帅");//对哪里进行限定
              /*  book.update(4);//具体查询哪一行数据
                book.clearSavedState();
                //默认的价格更新为0,数据的库的操作很死
                book = new Book();
                book.setToDefault("pages");
                book.update(4);*/

                break;
            case R.id.btn_lp4:
               // DataSupport.deleteAll(Book.class, "price<?", "26");
                DataSupport.deleteAll(Book.class);
                break;
            case R.id.btn_lp5:
                List<Book> all = DataSupport.findAll(Book.class);
                //遍历所有的数据
                for (Book book : all) {
                    Log.d("TGG", "onClick: " + book.getId()+ "\t"+ book.getName() + "\t" + book.getAuthor() + "\t" + book.getPrice() + "\t" + book.getPages());
                }

                break;
            case R.id.btn_lp6:
                //查询第一条数据
                Book book0 = DataSupport.findFirst(Book.class);
                Log.d("TGG", "onClick: " + book0.getId()+ "\t"+ book0.getName() + "\t" + book0.getAuthor() + "\t" + book0.getPrice() + "\t" + book0.getPages());

                break;
            case R.id.btn_lp7:
                //遍历最后的一条数据
                Book book1 = DataSupport.findLast(Book.class);
                Log.d("TGG", "onClick: " + book1.getId()+ "\t"+ book1.getName() + "\t" + book1.getAuthor() + "\t" + book1.getPrice() + "\t" + book1.getPages());

                break;
            case R.id.btn_lp8:
                //指定列的参数查询数据的操作
                List<Book> books = DataSupport.select("price", "author").find(Book.class);
                for (Book book2 : books) {
                    Log.d("TGG", "onClick: " + book2.getId()+ "\t"+ book2.getName() + "\t" + book2.getAuthor() + "\t" + book2.getPrice() + "\t" + book2.getPages());
                }
                break;
            case R.id.btn_lp9:
                List<Book> books3 = DataSupport.where("price>?", "30").find(Book.class);
                for (Book book3 : books3) {
                    Log.d("TGG", "onClick: "+ book3.getId()+ "\t" + book3.getName() + "\t" + book3.getAuthor() + "\t" + book3.getPrice() + "\t" + book3.getPages());
                }

                break;
            case R.id.btn_lp10:
                //desc 表示降,ascb表示升序
                List<Book> order = DataSupport.order("price desc").find(Book.class);
                for (Book book4 : order) {
                    Log.d("TGG", "onClick: " + book4.getId()+ "\t"+ book4.getName() + "\t" + book4.getAuthor() + "\t" + book4.getPrice() + "\t" + book4.getPages());
                }
                break;

            case R.id.btn_lp11:
                //表示limit查询前三条数据
                List<Book> books5 = DataSupport.limit(3).find(Book.class);
                for (Book book5 : books5) {
                    Log.d("TGG", "onClick: " + book5.getId()+ "\t"+ book5.getName() + "\t" + book5.getAuthor() + "\t" + book5.getPrice() + "\t" + book5.getPages());
                }
                break;
            case R.id.btn_lp12:
                //表示limit offset限定查询前三条数据
                List<Book> books6 = DataSupport.limit(3).offset(2).find(Book.class);
                for (Book book6 : books6) {

                    Log.d("TGG", "onClick: " + book6.getId()+ "\t"+ book6.getName() + "\t" + book6.getAuthor() + "\t" + book6.getPrice() + "\t" + book6.getPages());
                }

                break;
            case R.id.btn_lp13:
                List<Book> books7 = DataSupport.select("name", "author", "pages")
                        .where("price>?", "18")
                        .order("pages asc")
                        .limit(5)
                        .offset(2)
                        .find(Book.class);

                for (Book book6 : books7) {

                    Log.d("TGG", "onClick: " + book6.getId()+ "\t"+ book6.getName() + "\t" + book6.getAuthor() + "\t" + book6.getPrice() + "\t" + book6.getPages());
                }


                break;
        }
    }
}
