package com.xwzx.equipmanager.mvp.models.precenter;

import com.xwzx.equipmanager.mvp.ModelInterface;

import java.util.ArrayList;
import java.util.List;

public class OperatorAllRecordModel implements ModelInterface {

    /**
     * data : {"take":[{"caseName":"测试案件","taskName":"任务10000","caseId":12,"taskId":11,"data":[{"id":32,"equipmentId":"1594266792162","equipmentName":"定位测试003","equipmentImg":"group1/M00/00/00/wKgLuV8GlRmAZPXnAAKO5BNUSpk414.jpg","time":"2020-07-17T03:43:40Z","overdue":false}]}],"back":[{"caseName":"让金玉专用案件","taskName":"小玉任务02","caseId":35,"taskId":18,"data":[{"id":29,"equipmentId":"1594266792162","equipmentName":"定位测试003","equipmentImg":"group1/M00/00/00/wKgLuV8GlRmAZPXnAAKO5BNUSpk414.jpg","time":"2020-07-16T08:04:53Z","overdue":false}]},{"caseName":"让金玉专用案件","taskName":"小玉任务01","caseId":35,"taskId":17,"data":[{"id":30,"equipmentId":"1594278066574","equipmentName":"定位测试004","equipmentImg":"group1/M00/00/00/wKgLuV8GwYmAR7JVAALQASwd6-s75.jpeg","time":"2020-07-13T08:04:53Z","overdue":true}]}],"returned":[{"caseName":"让金玉专用案件","taskName":"小玉任务01","caseId":35,"taskId":17,"data":[{"id":27,"equipmentId":"1594265468437","equipmentName":"反制测试001","equipmentImg":"group1/M00/00/34/DA0pOl7x_aCAT9_cAAALI36jaqU695.png","time":"2020-07-16T07:52:18Z","overdue":false},{"id":31,"equipmentId":"1594278307683","equipmentName":"定位测试005","equipmentImg":"group1/M00/00/00/wKgLuV8Gwh6AMVnrAAAAgtj0ipc43.jpeg","time":"2020-07-16T08:04:53Z","overdue":false}]},{"caseName":"让金玉专用案件","taskName":"小玉任务02","caseId":35,"taskId":18,"data":[{"id":28,"equipmentId":"1594266578825","equipmentName":"定位测试002","equipmentImg":"group1/M00/00/00/wKgLuV8GlH2AWHrCAAYvzyC8Dkc759.jpg","time":"2020-07-16T08:04:53Z","overdue":false}]}]}
     * msg : OK
     * status : 200
     */

    private DataBeanXXX data;
    private String msg;
    private int status;

    public DataBeanXXX getData() {
        return data;
    }

    public void setData(DataBeanXXX data) {
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

    public static class DataBeanXXX {
        private List<InnerBean> take = new ArrayList<>();
        private List<InnerBean> back = new ArrayList<>();
        private List<InnerBean> returned = new ArrayList<>();
        private List<InnerBean> overdue = new ArrayList<>();

        public List<InnerBean> getTake() {
            return take;
        }

        public void setTake(List<InnerBean> take) {
            this.take = take;
        }

        public List<InnerBean> getBack() {
            return back;
        }

        public void setBack(List<InnerBean> back) {
            this.back = back;
        }

        public List<InnerBean> getReturned() {
            return returned;
        }

        public void setReturned(List<InnerBean> returned) {
            this.returned = returned;
        }

        public List<InnerBean> getOverdue() {
            return overdue;
        }

        public void setOverdue(List<InnerBean> overdue) {
            this.overdue = overdue;
        }

        public static class InnerBean {
            /**
             * caseName : 测试案件
             * taskName : 任务10000
             * caseId : 12
             * taskId : 11
             * data : [{"id":32,"equipmentId":"1594266792162","equipmentName":"定位测试003","equipmentImg":"group1/M00/00/00/wKgLuV8GlRmAZPXnAAKO5BNUSpk414.jpg","time":"2020-07-17T03:43:40Z","overdue":false}]
             * type:0
             */

            private String caseName;
            private String taskName;
            private int caseId;
            private int taskId;
            private List<DataBean> data;
            private int type;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * id : 32
                 * equipmentId : 1594266792162
                 * equipmentName : 定位测试003
                 * equipmentImg : group1/M00/00/00/wKgLuV8GlRmAZPXnAAKO5BNUSpk414.jpg
                 * time : 2020-07-17T03:43:40Z
                 * overdue : false
                 */

                private int id;
                private String equipmentId;
                private String equipmentName;
                private String equipmentImg;
                private String time;
                private boolean overdue;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getEquipmentId() {
                    return equipmentId;
                }

                public void setEquipmentId(String equipmentId) {
                    this.equipmentId = equipmentId;
                }

                public String getEquipmentName() {
                    return equipmentName;
                }

                public void setEquipmentName(String equipmentName) {
                    this.equipmentName = equipmentName;
                }

                public String getEquipmentImg() {
                    return equipmentImg;
                }

                public void setEquipmentImg(String equipmentImg) {
                    this.equipmentImg = equipmentImg;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public boolean isOverdue() {
                    return overdue;
                }

                public void setOverdue(boolean overdue) {
                    this.overdue = overdue;
                }
            }
        }

    }
}
