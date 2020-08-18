package com.xwzx.equipmanager.mvp.models.submit;

import android.widget.CheckBox;

import com.xwzx.equipmanager.mvp.models.indexoperator.TaskModel;

import java.util.List;

public class FindCaseByUserModel {

    /**
     * data : [{"id":12,"name":"测试案件","tasks":[]}]
     * msg : OK
     * status : 200
     */

    private String msg;
    private int status;
    private List<CaseItemModel> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CaseItemModel> getData() {
        return data;
    }

    public void setData(List<CaseItemModel> data) {
        this.data = data;
    }

    public static class CaseItemModel {
        /**
         * id : 12
         * name : 测试案件
         * tasks : []
         */

        private int id;
        private String name;
        private boolean isCheck;
        private List<TaskItemModel> tasks;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public List<TaskItemModel> getTasks() {
            return tasks;
        }

        public void setTasks(List<TaskItemModel> tasks) {
            this.tasks = tasks;
        }
    }

    public static class TaskItemModel{
        private int id;
        private String name;
        private boolean isCheck;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }
}
