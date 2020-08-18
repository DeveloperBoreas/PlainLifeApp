package com.xwzx.equipmanager.mvp.views.activitys;


import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.xwzx.equipmanager.R;
import com.xwzx.equipmanager.adapter.MessageAdapter;
import com.xwzx.equipmanager.base.BaseActivity;
import com.xwzx.equipmanager.databinding.ActivityMessageBinding;
import com.xwzx.equipmanager.framwork.ClickProxy;
import com.xwzx.equipmanager.mvp.ModelInterface;

import java.util.ArrayList;


public class MessageActivity extends BaseActivity<ActivityMessageBinding> {

    private ArrayList<MessageModel> messageModels;
    private MessageAdapter messageAdapter;

    @Override
    public int setContentView() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        this.testData();
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.message));
        this.binding.headLayout.rightTitle.setText(getResources().getString(R.string.read_all_message));
        this.binding.headLayout.rightTitle.setTextColor(getResources().getColor(R.color.light23_blue));
        this.binding.list.setHasFixedSize(false);
        this.binding.list.setLayoutManager(new LinearLayoutManager(this));
        this.messageAdapter = new MessageAdapter(this, this.messageModels, R.layout.message_item);
        this.binding.list.setAdapter(messageAdapter);
    }

    private void testData() {
        this.messageModels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MessageModel model = new MessageModel(i,i<2?true:false,"系统通知","1号仓库在12日下午13：00-14：00之间盘库，暂停出入装备，忘知晓！！！！！！！！！！！！！！！","昨天 12：16");
            this.messageModels.add(model);
        }
    }
    private void readMessage(){
        for (MessageModel model:this.messageModels) {
            model.setNew(false);
        }
        this.messageAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        this.binding.headLayout.back.setOnClickListener(new ClickProxy(v -> finish()));
        this.binding.headLayout.rightBtn.setOnClickListener(new ClickProxy(v -> {
            this.readMessage();
        }));

    }
    public class MessageModel implements ModelInterface{
        public int id;
        public boolean isNew;
        public String title;
        public String message;
        public String date;

        public MessageModel(int id, boolean isNew, String title, String message, String date) {
            this.id = id;
            this.isNew = isNew;
            this.title = title;
            this.message = message;
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isNew() {
            return isNew;
        }

        public void setNew(boolean aNew) {
            isNew = aNew;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "MessageModel{" +
                    "id=" + id +
                    ", isNew=" + isNew +
                    ", title='" + title + '\'' +
                    ", message='" + message + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }
    }
    @Override
    protected void initComponent() {

    }

    @Override
    protected void initData() {

    }
}
