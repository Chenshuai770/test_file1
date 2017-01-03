package cs.com.test_file1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by chenshuai on 2017/1/2.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    /**
     *目的 我们希望创建一个BookStore.db的数据库,里面记录了id,作者,价格,页数和数名等列;
     *
     * 数据库文件放在 data/data/包名/databases/目录下
     * @param context 上下文
     * @param name 表的名字 单位是db
     * @param factory 查询数据是返回的一个cursou值,一般为null
     * @param version 数据库版本号 对应的是onUpgrade方法,而且我们无法手动修改数据的版本号,除非卸载了
     *
     *
     * integer 表示整型
     * text 表示文本型
     * real 表示浮点型
     * blob表示二进制
     *                autoincrement 表示自己增长 ++i
     *                primare key 表示将id 设置为主键
     *                注意最后的参数都是要以",隔开"
     */

    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "作者 text, "
            + "价格 real, "
            + "页数 integer, "
            + "名字 text)";
//创建第二张表格
    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";
    private Context context;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }


    @Override//创建数据库
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
        Toast.makeText(context, "表单创建成功", Toast.LENGTH_SHORT).show();

    }

    @Override//更新数据库 如果数据库已经存在,则先删除,然后在执行操作,对应的是version的参数
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Book");
        sqLiteDatabase.execSQL("drop table if exists Category");
        onCreate(sqLiteDatabase);

    }
}
