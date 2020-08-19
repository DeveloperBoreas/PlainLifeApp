package com.boreas.plainlife.mvp.views.activitys;

import android.content.Intent;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.boreas.plainlife.App;
import com.boreas.plainlife.R;
import com.boreas.plainlife.adapter.ApplyCaseListAdapter;
import com.boreas.plainlife.adapter.ApplyTaskListAdapter;
import com.boreas.plainlife.adapter.SubmitApplyAdapter;
import com.boreas.plainlife.base.BaseActivity;
import com.boreas.plainlife.base.adapter.BaseRecyclerAdapter;
import com.boreas.plainlife.databinding.ActivitySubmitApplyOperationBinding;
import com.boreas.plainlife.events.UpDataMainEquip;
import com.boreas.plainlife.framwork.ClickProxy;
import com.boreas.plainlife.internal.components.DaggerSubmitApplyOperationActivityComponent;
import com.boreas.plainlife.internal.modules.SubmitApplyOperationActivityModule;
import com.boreas.plainlife.mvp.models.ShoppingModel;
import com.boreas.plainlife.mvp.models.submit.FindCaseByUserModel;
import com.boreas.plainlife.mvp.models.submit.SubmitApprovalModel;
import com.boreas.plainlife.mvp.presenters.presenterimpl.SubmitApplyOperationPresenter;
import com.boreas.plainlife.mvp.views.viewinterfaces.SubmitApplyOperationActivityInterface;
import com.boreas.plainlife.utils.TimeUtils;
import com.boreas.plainlife.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class SubmitApplyOperationActivity extends BaseActivity<ActivitySubmitApplyOperationBinding> implements SubmitApplyOperationActivityInterface {
    private ArrayList<ShoppingModel> shoppingModels;
    private boolean isSubmitSuccess = false;
    private static final int CODE_RESULT_SUBMIT = 1002;

    @Inject
    SubmitApplyOperationPresenter submitApplyOperationPresenter;
    private ArrayList<FindCaseByUserModel.CaseItemModel> caseList;
    private ArrayList<FindCaseByUserModel.TaskItemModel> taskList;
    private String mStartDate;
    private String mEndDate;

    @Override
    public int setContentView() {
        return R.layout.activity_submit_apply_operation;
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.binding.headLayout.headTitle.setText(getResources().getString(R.string.submitapply));
        this.binding.headLayout.rightTitle.setVisibility(View.GONE);
        this.binding.shoppingList.setHasFixedSize(false);
        this.binding.shoppingList.setLayoutManager(new LinearLayoutManager(this));
        this.binding.shoppingList.setAdapter(new SubmitApplyAdapter(this, this.shoppingModels, R.layout.activity_shopping_item_parent));

    }

    @Override
    public void handlerJumpData(Intent intent) {
        super.handlerJumpData(intent);
        this.shoppingModels = (ArrayList<ShoppingModel>) intent.getSerializableExtra("applyList");
    }

    private boolean backIndex() {
        //返回首页
        if (isSubmitSuccess) {
            //返回首页
            EventBus.getDefault().post(new UpDataMainEquip(true));
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }

    @Override
    protected void initListener() {
        this.binding.headLayout.back.setOnClickListener(new ClickProxy(v -> {
            if (!this.backIndex()) {
                if(isSubmitSuccess){
                    setResult(CODE_RESULT_SUBMIT);
                }
                finish();
            }
        }));

        this.binding.backIndex.setOnClickListener(new ClickProxy(v -> {
            if (!this.backIndex()) {
                finish();
            }

        }));
        this.binding.lookRecord.setOnClickListener(new ClickProxy(v -> {
            if (!this.backIndex()) {
                finish();
            }
        }));

        this.binding.rlSelectCase.setOnClickListener(new ClickProxy(view -> {
            submitApplyOperationPresenter.findCaseAndTaskByUser();
        }));

        this.binding.rlSelectTask.setOnClickListener(new ClickProxy(view -> {
            String caseName = this.binding.tvCase.getText().toString();
            if ("".equals(caseName) || caseName == null) {
                ToastUtil.show(this, "请选择案件");
                return;
            }
            for (FindCaseByUserModel.CaseItemModel item : caseList) {
                if (item.getName().equals(caseName)) {
                    List<FindCaseByUserModel.TaskItemModel> tasks = item.getTasks();
                    for (FindCaseByUserModel.TaskItemModel taskItemModel:taskList) {
                        if(taskItemModel.isCheck()){
                            for (FindCaseByUserModel.TaskItemModel taskModel:tasks) {
                                if(taskModel.getId()==taskItemModel.getId()){
                                    taskModel.setCheck(true);
                                    break;
                                }
                            }
                            break;
                        }

                    }
                    taskList.clear();
                    taskList.addAll(tasks);
                    showTaskBottomDialog();
                }
            }


        }));

        this.binding.rlStartDate.setOnClickListener(new ClickProxy(view -> {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            endDate.add(Calendar.MONTH,1);
            TimePickerView timePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    setStartDateForView(date);
                    int dayCount = TimeUtils.getDayCount(mStartDate, mEndDate);
                    SubmitApplyOperationActivity.this.binding.tvDays.setText("共"+dayCount+"天");
                }
            })
                    .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                    .setRangDate(startDate,endDate)//起始终止年月日设定
                    .setDecorView(this.binding.content)
                    .build();

            timePickerView.show();
        }));

        this.binding.rlEndDate.setOnClickListener(new ClickProxy(view -> {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            endDate.add(Calendar.MONTH,1);
            TimePickerView timePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    setEndDateForView(date);
                    int dayCount = TimeUtils.getDayCount(mStartDate, mEndDate);
                    SubmitApplyOperationActivity.this.binding.tvDays.setText("共"+dayCount+"天");
                }
            })
                    .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                    .setRangDate(startDate,endDate)//起始终止年月日设定
                    .setDecorView(this.binding.content)
                    .build();

            timePickerView.show();
        }));

        this.binding.submit.setOnClickListener(new ClickProxy(view -> {
            if(this.verParams()){
                String caseName = this.binding.tvCase.getText().toString().trim();
                String taskName = this.binding.tvTask.getText().toString().trim();
                int caseId = (int) this.binding.tvCase.getTag();
                int taskId = (int) this.binding.tvTask.getTag();
                ArrayList<SubmitApprovalModel.EquipmentsBean> equipmentsBeans = new ArrayList<>();
                for (ShoppingModel shoppingModel:shoppingModels) {
                    ArrayList<ShoppingModel.ShoppingChildModel> childModels = shoppingModel.getChildModels();
                    if(childModels.size()>0){
                        for (ShoppingModel.ShoppingChildModel shoppingChildModel:childModels) {
                            SubmitApprovalModel.EquipmentsBean bean =new SubmitApprovalModel.EquipmentsBean(shoppingChildModel.getTitle()
                                    ,shoppingChildModel.getImgUrl(),shoppingChildModel.getId(),shoppingChildModel.getType_en());
                            equipmentsBeans.add(bean);
                        }
                    }
                }
                SubmitApprovalModel submitApprovalModel = new SubmitApprovalModel();
                submitApprovalModel.setCaseName(caseName);
                submitApprovalModel.setCaseId(caseId);
                submitApprovalModel.setTaskName(taskName);
                submitApprovalModel.setTaskId(taskId);
                submitApprovalModel.setFetchTime(mStartDate);
                submitApprovalModel.setBorrowTime(mEndDate);
                submitApprovalModel.setEquipments(equipmentsBeans);
                submitApplyOperationPresenter.submitApply(submitApprovalModel);
            }

        }));


    }

    private boolean verParams(){
        if(TextUtils.isEmpty(this.binding.tvCase.getText().toString().trim())){
            ToastUtil.show(this,"请选择案件");
            return false;
        }

        if(TextUtils.isEmpty(this.binding.tvTask.getText().toString().trim())){
            ToastUtil.show(this,"请选择任务");
            return false;
        }

        return true;
    }

    public void setStartDateForView(Date date){
        String month = String.valueOf(DateFormat.format("MM月dd日",date));
        String week = String.valueOf(DateFormat.format("EEEE",date));
        String hour = String.valueOf(DateFormat.format("HH:mm",date));
        mStartDate = TimeUtils.formatStr("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",date);;
        SubmitApplyOperationActivity.this.binding.tvStartWeek.setText(week+" "+hour);
        SubmitApplyOperationActivity.this.binding.tvStartDate.setText(month);
    }

    public void setEndDateForView(Date date){
        String month = String.valueOf(DateFormat.format("MM月dd日",date));
        String week = String.valueOf(DateFormat.format("EEEE",date));
        String hour = String.valueOf(DateFormat.format("HH:mm",date));
//        endDate = String.valueOf(DateFormat.format("yyyy-MM-dd'T'HH:mm:ss.000Z", date.getTime()));
        mEndDate = TimeUtils.formatStr("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",date);
        SubmitApplyOperationActivity.this.binding.tvEndWeek.setText(week+" "+hour);
        SubmitApplyOperationActivity.this.binding.tvEndDate.setText(month);
    }

    private void showCompleteView() {
        this.binding.submit.setVisibility(View.GONE);
        this.binding.contentView.setVisibility(View.GONE);
        this.binding.headLayout.headTitle.setVisibility(View.INVISIBLE);
        this.binding.headLayout.rightTitle.setVisibility(View.INVISIBLE);
        this.binding.headLayout.rightBtn.setVisibility(View.INVISIBLE);
        this.binding.complete.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initComponent() {
        DaggerSubmitApplyOperationActivityComponent.builder()
                .appComponent(App.getInstance().getmAppComponent())
                .netComponent(App.getInstance().getmNetComponent())
                .submitApplyOperationActivityModule(new SubmitApplyOperationActivityModule(this))
                .build().inject(this);
    }

    @Override
    protected void initData() {
        caseList = new ArrayList<>();
        taskList = new ArrayList<>();

        Date toDay = new Date();
        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(toDay);
        setStartDateForView(toDay);

        todayCal.add(Calendar.DAY_OF_MONTH,3);
        Date endDay = todayCal.getTime();
        todayCal.setTime(endDay);
        setEndDateForView(endDay);

        int dayCount = TimeUtils.getDayCount(mStartDate, mEndDate);
        this.binding.tvDays.setText("共"+dayCount+"天");
    }



    private void showCaseBottomDialog() {
        View viewLayout = View.inflate(this, R.layout.apply_tasklist_dialog, null);
        RecyclerView recyclerView = viewLayout.findViewById(R.id.recycle_task_list);
        TextView title = viewLayout.findViewById(R.id.title);
        title.setText("请选择案件");
        TextView cancle = viewLayout.findViewById(R.id.cancle);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApplyCaseListAdapter adapter = new ApplyCaseListAdapter(this, caseList, R.layout.recycle_item_apply_task_list);
        recyclerView.setAdapter(adapter);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomFullDialog);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.setContentView(viewLayout);

        cancle.setOnClickListener(new ClickProxy(view -> {
            if (bottomSheetDialog != null) {
                bottomSheetDialog.dismiss();
            }
        }));
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                FindCaseByUserModel.CaseItemModel caseItemModel = caseList.get(position);
                if(!caseItemModel.isCheck()){
                    setAllCaseModelsUnCheck();
                    SubmitApplyOperationActivity.this.binding.tvCase.setText(caseItemModel.getName());
                    SubmitApplyOperationActivity.this.binding.tvCase.setTag(caseItemModel.getId());
                    SubmitApplyOperationActivity.this.binding.tvTask.setText("");
                    SubmitApplyOperationActivity.this.binding.tvTask.setTag(-1);
                    caseItemModel.setCheck(true);
                    adapter.notifyDataSetChanged();
                }
                if (bottomSheetDialog != null) {
                    bottomSheetDialog.dismiss();
                }

            }
        });

        bottomSheetDialog.show();

    }

    private void showTaskBottomDialog() {
        View viewLayout = View.inflate(this, R.layout.apply_tasklist_dialog, null);
        RecyclerView recyclerView = viewLayout.findViewById(R.id.recycle_task_list);
        TextView title = viewLayout.findViewById(R.id.title);
        title.setText("请选择任务");
        TextView cancle = viewLayout.findViewById(R.id.cancle);

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApplyTaskListAdapter adapter = new ApplyTaskListAdapter(this, taskList, R.layout.recycle_item_apply_task_list);
        recyclerView.setAdapter(adapter);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomFullDialog);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.setContentView(viewLayout);
        cancle.setOnClickListener(new ClickProxy(view -> {
            if (bottomSheetDialog != null) {
                bottomSheetDialog.dismiss();
            }
        }));

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                FindCaseByUserModel.TaskItemModel taskItemModel = taskList.get(position);
                if(!taskItemModel.isCheck()){
                    setAllTaskModelsUnCheck();
                    taskItemModel.setCheck(true);
                    SubmitApplyOperationActivity.this.binding.tvTask.setText(taskItemModel.getName());
                    SubmitApplyOperationActivity.this.binding.tvTask.setTag(taskItemModel.getId());
                    adapter.notifyDataSetChanged();
                }

                if (bottomSheetDialog != null) {
                    bottomSheetDialog.dismiss();
                }
            }
        });

        bottomSheetDialog.show();

    }

    public void setAllCaseModelsUnCheck() {
        for (FindCaseByUserModel.CaseItemModel caseItemModel : caseList) {
            caseItemModel.setCheck(false);
        }
    }

    public void setAllTaskModelsUnCheck() {
        for (FindCaseByUserModel.TaskItemModel taskItemModel : taskList) {
            taskItemModel.setCheck(false);
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onGetCaseAndTask(List<FindCaseByUserModel.CaseItemModel> data) {
        ArrayList<FindCaseByUserModel.CaseItemModel> caseListData = new ArrayList<>();
        caseListData.addAll(data);
        for (FindCaseByUserModel.CaseItemModel caseItemModel : caseList) {
            if (caseItemModel.isCheck()) {
                for (FindCaseByUserModel.CaseItemModel model : caseListData) {
                    if (caseItemModel.getId() == model.getId()) {
                        model.setCheck(true);
                        break;
                    }
                }
                break;
            }

        }
        caseList.clear();
        caseList.addAll(caseListData);
        showCaseBottomDialog();


    }
    //提交申请回调
    @Override
    public void onSubmitApply(String msg) {
        ToastUtil.show(this, msg);
        showCompleteView();
        isSubmitSuccess = true;
    }

    @Override
    public void onFailed(String msg) {
        ToastUtil.show(this, msg);
    }

    @Override
    public void noNetWork() {

    }

    @Override
    public void noData() {

    }

    @Override
    public void onDisLoadingDialog() {
        this.dismissLoadingDialog();
    }

    @Override
    public void onShowLoadingDialog() {
        this.showLoadingDialog();
    }


}
