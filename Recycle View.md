# 目的
左边的listitem是自动动态的生成的,点击左边项目进入右边的项目详情.
![image.png](http://ww1.sinaimg.cn/large/0083vuQJly1ge2h1ygtvdj30oi0jddgy.jpg)

## 数据结构
* `item` 类
* 用于生成item列表的 `LinkedList<item>`
* 链接数据库请自行想办法 把 数据库内容添加在 `LinkedList` 中

## 创建布局文件
![image.png](http://ww1.sinaimg.cn/large/0083vuQJly1ge2h8z7yqtj30qk0e1wfa.jpg)

* activity showlist (创建空`activity`自动生成`java`)
  * 放上一个`recycleview`
* activity detail   (创建空`activity`自动生成`java`)
  * 放一些`textview`,用来显示传递过来的信息
* item xml  ( `res > layout > New > Layout resource file` )
  * 放上一个/几个`textview`,用来显示简略信息

## 创建一个adapter
* 基本全粘贴就可以了,这里需要改几个东西:
* 使用 <kbd>ctrl</kbd>+<kbd>shift</kbd>+<kbd>L</kbd> 全局搜索并更改掉
  * `xiaoquAdapter`
  * `xiaoquViewHolder`
* 剩余根据TODO做即可

```java
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.datacls.xiaoqu;
import java.util.LinkedList;


/**
 * 定义的item的数据存放结构 mWordList
 */
  //TODO 自行搜索相关内容更改名字就好
public class xiaoquAdapter extends RecyclerView.Adapter<xiaoquAdapter.xiaoquViewHolder>   {
    private final LinkedList<xiaoqu> mWordList;
    private LayoutInflater mInflater;

    public xiaoquAdapter(Context context, LinkedList<xiaoqu> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }
    @NonNull
    @Override
    public xiaoquAdapter.xiaoquViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_xiaoqu, parent, false);
        //TODO 上面的R.layout.wordlist_item 更换为自己写的item xml文件
        return new xiaoquViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull xiaoquAdapter.xiaoquViewHolder holder, int position) {
      //TODO mCurrent是现在的链表指针位置,根据你的链表类型,更改mCurrent类型
        xiaoqu mCurrent = mWordList.get(position); 
      // TODO 这里是初始化item的时候用的settext函数,更改为自己需要的
        holder.xiaoquname.setText(mCurrent.name);
        holder.prov.setText(mCurrent.province);
        holder.city.setText(mCurrent.city);
        holder.area.setText(mCurrent.area);

    }

    @Override
    public int getItemCount() {
        return  mWordList.size();// 返回链表数目
    }


    /**
     * holder可以把数据和view的id绑定在一起。
     */
    class xiaoquViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //TODO 这里声明item的用到的textview等显示信息的控件
        public final TextView xiaoquname;
        public final TextView city;
        public final TextView prov;
        public final TextView area;
        final xiaoquAdapter xiaoquAdapter;
        public xiaoquViewHolder(View itemView, xiaoquAdapter adapter) {
            super(itemView);
            //TODO 这里绑定textview等显示信息的控件的 R id
            xiaoquname = itemView.findViewById(R.id.textView31);
            prov = itemView.findViewById(R.id.textView32);
            city = itemView.findViewById(R.id.textView33);
            area = itemView.findViewById(R.id.textView34);
            this.xiaoquAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        /**
         * 这个是让你的 item 被点击后的执行的函数。
         */
        @Override
        public void onClick(View v) {
          //TODO 设定点击一个item要做的事情
            //获取你的正在点击的项目,并且保存 (我这里保存到的Dataclass是一个全局的Data取用类)
            int mPosition = getLayoutPosition();
            Dataclass.thexiaoqu = mWordList.get(mPosition);
//            声明一个intent,进入下一个界面.
            Intent intent = new Intent(v.getContext(), MakeSureXiaoquActivity.class);
            v.getContext().startActivity(intent);
            xiaoquAdapter.notifyDataSetChanged();
        }
    }

}
```

## show list activity
放在class顶部
* recycle view
* 上一步骤写的Adapter

```java
private RecyclerView mRecyclerView; 
private XXXXAdapter mAdapter;
LinkedList<XXXyour_data_structXXX> mWordList;
```

放在 `Oncreate`
  * 赋值
  * 首次填充列表
  * 在这之前要自己先给Linklist数据
```java
mRecyclerView = findViewById(R.id.recycleeView);

mAdapter = new WordListAdapter(this, mWordList);
mRecyclerView.setAdapter(mAdapter);
mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
```

列表更新函数
* 改变LinkList内容
```java
int wordListSize = mWordList.size();
mWordList.addLast("+ Word " + wordListSize);
```
* 提示adapter更新视图
* 滚动到最后下方
```java
mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
mRecyclerView.smoothScrollToPosition(wordListSize);
```