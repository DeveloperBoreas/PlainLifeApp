package com.xwzx.equipmanager.mvp.models.precenter;

import com.xwzx.equipmanager.mvp.ModelInterface;

import java.util.List;

public class KeeperUsedDevicesModel implements ModelInterface {

    /**
     * data : {"totalNum":4,"detail":{"LOCAL":{"count":3,"proportion":75},"COUNTER":{"count":1,"proportion":25}},"currWeekNum":1,"currMonthNum":1,"recentDevices":[{"id":2,"userId":1,"fetchTime":"2020-06-22T05:14:17Z","equipmentId":"1593346241897","endTime":"2020-06-24T08:02:12Z","caseId":1,"equipmentName":"外线设备","borrowTime":"2020-06-25T05:14:23Z","equipmentImg":"wwwwwww","startTime":"2020-06-23T08:02:22Z","caseName":"案件一","taskId":2,"taskName":"任务二","status":0}]}
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
         * totalNum : 4
         * detail : {"LOCAL":{"count":3,"proportion":75},"COUNTER":{"count":1,"proportion":25}}
         * currWeekNum : 1
         * currMonthNum : 1
         * recentDevices : [{"id":2,"userId":1,"fetchTime":"2020-06-22T05:14:17Z","equipmentId":"1593346241897","endTime":"2020-06-24T08:02:12Z","caseId":1,"equipmentName":"外线设备","borrowTime":"2020-06-25T05:14:23Z","equipmentImg":"wwwwwww","startTime":"2020-06-23T08:02:22Z","caseName":"案件一","taskId":2,"taskName":"任务二","status":0}]
         */

        private int totalNum;
        private List<DetailBean> details;
        private int currWeekNum;
        private int currMonthNum;
        private List<RecentDevicesBean> recentDevices;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public List<DetailBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailBean> details) {
            this.details = details;
        }

        public int getCurrWeekNum() {
            return currWeekNum;
        }

        public void setCurrWeekNum(int currWeekNum) {
            this.currWeekNum = currWeekNum;
        }

        public int getCurrMonthNum() {
            return currMonthNum;
        }

        public void setCurrMonthNum(int currMonthNum) {
            this.currMonthNum = currMonthNum;
        }

        public List<RecentDevicesBean> getRecentDevices() {
            return recentDevices;
        }

        public void setRecentDevices(List<RecentDevicesBean> recentDevices) {
            this.recentDevices = recentDevices;
        }

        public static class DetailBean {

            /**
             * name : 定位
             * count : 3
             * proportion : 100
             */

            private String name;
            private int count;
            private int proportion;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getProportion() {
                return proportion;
            }

            public void setProportion(int proportion) {
                this.proportion = proportion;
            }
        }

        public static class RecentDevicesBean {
            /**
             * id : 2
             * userId : 1
             * fetchTime : 2020-06-22T05:14:17Z
             * equipmentId : 1593346241897
             * endTime : 2020-06-24T08:02:12Z
             * caseId : 1
             * equipmentName : 外线设备
             * borrowTime : 2020-06-25T05:14:23Z
             * equipmentImg : wwwwwww
             * startTime : 2020-06-23T08:02:22Z
             * caseName : 案件一
             * taskId : 2
             * taskName : 任务二
             * status : 0
             */

            private int id;
            private int userId;
            private String fetchTime;
            private String equipmentId;
            private String endTime;
            private int caseId;
            private String equipmentName;
            private String borrowTime;
            private String equipmentImg;
            private String startTime;
            private String caseName;
            private int taskId;
            private String taskName;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getFetchTime() {
                return fetchTime;
            }

            public void setFetchTime(String fetchTime) {
                this.fetchTime = fetchTime;
            }

            public String getEquipmentId() {
                return equipmentId;
            }

            public void setEquipmentId(String equipmentId) {
                this.equipmentId = equipmentId;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getCaseId() {
                return caseId;
            }

            public void setCaseId(int caseId) {
                this.caseId = caseId;
            }

            public String getEquipmentName() {
                return equipmentName;
            }

            public void setEquipmentName(String equipmentName) {
                this.equipmentName = equipmentName;
            }

            public String getBorrowTime() {
                return borrowTime;
            }

            public void setBorrowTime(String borrowTime) {
                this.borrowTime = borrowTime;
            }

            public String getEquipmentImg() {
                return equipmentImg;
            }

            public void setEquipmentImg(String equipmentImg) {
                this.equipmentImg = equipmentImg;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getCaseName() {
                return caseName;
            }

            public void setCaseName(String caseName) {
                this.caseName = caseName;
            }

            public int getTaskId() {
                return taskId;
            }

            public void setTaskId(int taskId) {
                this.taskId = taskId;
            }

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
