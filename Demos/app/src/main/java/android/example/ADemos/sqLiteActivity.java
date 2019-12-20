package android.example.ADemos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class sqLiteActivity extends AppCompatActivity {
    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_lite);
        dbHelper = new DbHelper(this);
    }

    //    插入的函数
    public void Insert_hao(View view) {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", "星马豪");
        values.put("gender", "男");
        values.put("birthday", "8月1日");
        values.put("Animation", "四驱兄弟");
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert("AnimeCharacter", null, values);
        if (newRowId == -1) {
            Toast toast = Toast.makeText(this, "插入失败。", Toast.LENGTH_LONG);
            toast.show();
        } else {
            String MSG = "插入成功，插入在第 " + newRowId + " 行";
            Toast toast = Toast.makeText(this, MSG, Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void Insert_Nami(View view) {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", "娜美");
        values.put("gender", "女");
        values.put("birthday", "7月3日");
        values.put("Animation", "ONE PIECE");
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert("AnimeCharacter", null, values);//null是在你没有给的values列上面插入一个sql可以接受的null
        if (newRowId == -1) {
            Log.println(Log.INFO, "meow", "插入失败~");
        } else {
            String MSG = "插入成功，插入在第 " + newRowId + " 行";
            Log.println(Log.INFO, "meow", MSG);
        }
    }

    //    读取的函数
    public void Read(View view) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "ID",
                "name",
                "gender",
                "birthday",
                "Animation"
        };

        Cursor cursor = db.query(
                "AnimeCharacter",   // table name
                projection,         // 要查的列名字符串数组
                null,               // The columns for the WHERE clause
                null,               // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                null                // The sort order
        );

        Log.println(Log.INFO, "meow", "获取内容了：");

        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow("ID"));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow("name")
            );
            String gengder = cursor.getString(
                    cursor.getColumnIndexOrThrow("gender")
            );
            Log.println(Log.INFO, "meow", "获取id： " + itemId + " name: " + name + " gender:" + gengder);
        }
        cursor.close();
    }

    //    删除的函数
// 相当于：DELETE FROM AnimeCharacter WHERE gender like '男'
    public void Delete_male(View view) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = "gender" + " LIKE ?";  //“？” 部分会被替换为下面的 selectionArgs
        String[] selectionArgs = {"男"}; //可以多选
        int deletedRows = db.delete("AnimeCharacter", selection, selectionArgs);
    }
    // 更新函数
    public void Update_maleToUnknow(View view){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("gender", "unknow"); //values.put("列名", "新值");

        String selection = "gender" + " LIKE ?";//“？” 部分会被替换为下面的 selectionArgs
        String[] selectionArgs = { "男" };//可以多选
        int count = db.update(
                "AnimeCharacter",   //表名
                values,             //插入值类 ContentValues
                selection,          //选行参数部分1
                selectionArgs);     //选行参数部分2
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
