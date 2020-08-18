package com.xwzx.equipmanager.mvp.models.submit;

import java.util.List;

public class SubmitApprovalModel {


    /**
     * caseId : 12
     * taskId : 8
     * context :
     * equipments : [{"name":"定位设备","img":"group1/M00/00/34/DA0pOl7x_aCAT9_cAAALI36jaqU695.png","id":"11","type":"LOCAL"}]
     * fetchTime : 2020-07-08T03:18:27.281Z
     * borrowTime : 2020-07-10T03:18:27.281Z
     * caseName : 测试案件
     * taskName : 任务1
     */

    private int caseId;
    private int taskId;
    private String context;
    private String fetchTime;
    private String borrowTime;
    private String caseName;
    private String taskName;
    private List<EquipmentsBean> equipments;

    public SubmitApprovalModel() {
    }

    public SubmitApprovalModel(int caseId, int taskId, String context, String fetchTime, String borrowTime, String caseName, String taskName, List<EquipmentsBean> equipments) {
        this.caseId = caseId;
        this.taskId = taskId;
        this.context = context;
        this.fetchTime = fetchTime;
        this.borrowTime = borrowTime;
        this.caseName = caseName;
        this.taskName = taskName;
        this.equipments = equipments;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(String fetchTime) {
        this.fetchTime = fetchTime;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<EquipmentsBean> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<EquipmentsBean> equipments) {
        this.equipments = equipments;
    }

    public static class EquipmentsBean {
        /**
         * name : 定位设备
         * img : group1/M00/00/34/DA0pOl7x_aCAT9_cAAALI36jaqU695.png
         * id : 11
         * type : LOCAL
         */

        private String name;
        private String img;
        private String id;
        private String type;

        public EquipmentsBean() {
        }

        public EquipmentsBean(String name, String img, String id, String type) {
            this.name = name;
            this.img = img;
            this.id = id;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
