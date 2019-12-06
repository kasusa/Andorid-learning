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

    public void Insert_hao(View view) {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name","星马豪");
        values.put("gender","男");
        values.put("birthday","8月1日");
        values.put("Animation","四驱兄弟");
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert("AnimeCharacter", null, values);
        if(newRowId == -1){
            Toast toast = Toast.makeText(this,"插入失败。", Toast.LENGTH_LONG);
            toast.show();
        }else {
            String MSG = "插入成功，插入在第 "+newRowId+" 行";
            Toast toast = Toast.makeText(this,MSG, Toast.LENGTH_LONG);
            toast.show();
        }

    }
    public void Read(View view){
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

        Log.println(Log.INFO,"meow","获取内容了：");

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow("ID"));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow("name")
            );
            Log.println(Log.INFO,"meow","获取id： "+itemId+"name: "+name);
        }
        cursor.close();
    }

}
