package com.xwzx.equipmanager.mvp.models.precenter;

import com.xwzx.equipmanager.mvp.ModelInterface;

import java.util.List;

public class PersonalBaseMsgModel implements ModelInterface {

    /**
     * data : {"success":true,"result":{"userId":1,"userName":"admin","typeId":1,"mobile":"13520164579","role":"操作员","currDeviceNum":3,"toDoTaskNum":1,"applicationNum":1,"historyNum":2,"alarmNum":3}}
     * msg : OK
     * status : 200
     */

    private DataBean data;
    private String msg;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * success : true
         * result : {"userId":1,"userName":"admin","typeId":1,"mobile":"13520164579","role":"操作员","currDeviceNum":3,"toDoTaskNum":1,"applicationNum":1,"historyNum":2,"alarmNum":3}
         */

        private boolean success;
        private ResultBean result;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * userId : 1
             * userName : admin
             * typeId : 1
             * mobile : 13520164579
             * role : 操作员
             * currDeviceNum : 3
             * toDoTaskNum : 1
             * applicationNum : 1
             * historyNum : 2
             * alarmNum : 3
             */

            private int userId;
            private String userName;
            private int typeId;
            private String mobile;
            private List<String> role;
            private int currDeviceNum;
            private int toDoTaskNum;
            private int applicationNum;
            private int historyNum;
            private int alarmNum;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public List<String> getRole() {
                return role;
            }

            public void setRole(List<String> role) {
                this.role = role;
            }

            public int getCurrDeviceNum() {
                return currDeviceNum;
            }

            public void setCurrDeviceNum(int currDeviceNum) {
                this.currDeviceNum = currDeviceNum;
            }

            public int getToDoTaskNum() {
                return toDoTaskNum;
            }

            public void setToDoTaskNum(int toDoTaskNum) {
                this.toDoTaskNum = toDoTaskNum;
            }

            public int getApplicationNum() {
                return applicationNum;
            }

            public void setApplicationNum(int applicationNum) {
                this.applicationNum = applicationNum;
            }

            public int getHistoryNum() {
                return historyNum;
            }

            public void setHistoryNum(int historyNum) {
                this.historyNum = historyNum;
            }

            public int getAlarmNum() {
                return alarmNum;
            }

            public void setAlarmNum(int alarmNum) {
                this.alarmNum = alarmNum;
            }
        }
    }
}
