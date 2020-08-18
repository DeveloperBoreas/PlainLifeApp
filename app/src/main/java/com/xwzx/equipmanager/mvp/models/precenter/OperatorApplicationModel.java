package com.xwzx.equipmanager.mvp.models.precenter;

import com.xwzx.equipmanager.mvp.ModelInterface;

import java.util.List;

/**
 * 我的申请
 */
public class OperatorApplicationModel implements ModelInterface {

    /**
     * data : {"agree":[{"caseId":12,"caseName":"装备演示案件","caseCreateTime":"2019-12-20T06:45:35Z","casePrincipalName":"鲁达","casePrincipalId":34,"equipment":[{"equipmentName":"定位测试005","equipmentId":"1594278307683","equipmentImg":"group1/M00/00/00/wKgLuV8Gwh6AMVnrAAAAgtj0ipc43.jpeg","position":null},{"equipmentName":"定位测试004","equipmentId":"1594278066574","equipmentImg":"group1/M00/00/00/wKgLuV8GwYmAR7JVAALQASwd6-s75.jpeg","position":null}]}],"apply":[{"caseId":12,"caseName":"装备演示案件","caseCreateTime":"2019-12-20T06:45:35Z","casePrincipalName":"鲁达","casePrincipalId":34,"equipment":[{"equipmentName":"定位测试005","equipmentId":"1594278307683","equipmentImg":"group1/M00/00/00/wKgLuV8Gwh6AMVnrAAAAgtj0ipc43.jpeg","position":null},{"equipmentName":"定位测试004","equipmentId":"1594278066574","equipmentImg":"group1/M00/00/00/wKgLuV8GwYmAR7JVAALQASwd6-s75.jpeg","position":null}]}],"rehecrt":[{"caseId":12,"caseName":"装备演示案件","caseCreateTime":"2019-12-20T06:45:35Z","casePrincipalName":"鲁达","casePrincipalId":34,"equipment":[{"equipmentName":"定位测试005","equipmentId":"1594278307683","equipmentImg":"group1/M00/00/00/wKgLuV8Gwh6AMVnrAAAAgtj0ipc43.jpeg","position":null},{"equipmentName":"定位测试004","equipmentId":"1594278066574","equipmentImg":"group1/M00/00/00/wKgLuV8GwYmAR7JVAALQASwd6-s75.jpeg","position":null}]}]}
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
        private List<AgreeBean> agree;
        private List<ApplyBean> apply;
        private List<RehecrtBean> rehecrt;

        public List<AgreeBean> getAgree() {
            return agree;
        }

        public void setAgree(List<AgreeBean> agree) {
            this.agree = agree;
        }

        public List<ApplyBean> getApply() {
            return apply;
        }

        public void setApply(List<ApplyBean> apply) {
            this.apply = apply;
        }

        public List<RehecrtBean> getRehecrt() {
            return rehecrt;
        }

        public void setRehecrt(List<RehecrtBean> rehecrt) {
            this.rehecrt = rehecrt;
        }

        public static class AgreeBean {
            /**
             * caseId : 12
             * caseName : 装备演示案件
             * caseCreateTime : 2019-12-20T06:45:35Z
             * casePrincipalName : 鲁达
             * casePrincipalId : 34
             * equipment : [{"equipmentName":"定位测试005","equipmentId":"1594278307683","equipmentImg":"group1/M00/00/00/wKgLuV8Gwh6AMVnrAAAAgtj0ipc43.jpeg","position":null},{"equipmentName":"定位测试004","equipmentId":"1594278066574","equipmentImg":"group1/M00/00/00/wKgLuV8GwYmAR7JVAALQASwd6-s75.jpeg","position":null}]
             */

            private int caseId;
            private String caseName;
            private String caseCreateTime;
            private String casePrincipalName;
            private int casePrincipalId;
            private List<EquipmentBean> equipment;

            public int getCaseId() {
                return caseId;
            }

            public void setCaseId(int caseId) {
                this.caseId = caseId;
            }

            public String getCaseName() {
                return caseName;
            }

            public void setCaseName(String caseName) {
                this.caseName = caseName;
            }

            public String getCaseCreateTime() {
                return caseCreateTime;
            }

            public void setCaseCreateTime(String caseCreateTime) {
                this.caseCreateTime = caseCreateTime;
            }

            public String getCasePrincipalName() {
                return casePrincipalName;
            }

            public void setCasePrincipalName(String casePrincipalName) {
                this.casePrincipalName = casePrincipalName;
            }

            public int getCasePrincipalId() {
                return casePrincipalId;
            }

            public void setCasePrincipalId(int casePrincipalId) {
                this.casePrincipalId = casePrincipalId;
            }

            public List<EquipmentBean> getEquipment() {
                return equipment;
            }

            public void setEquipment(List<EquipmentBean> equipment) {
                this.equipment = equipment;
            }

            public static class EquipmentBean {
                /**
                 * equipmentName : 定位测试005
                 * equipmentId : 1594278307683
                 * equipmentImg : group1/M00/00/00/wKgLuV8Gwh6AMVnrAAAAgtj0ipc43.jpeg
                 * position : null
                 */

                private String equipmentName;
                private String equipmentId;
                private String equipmentImg;
                private Object position;

                public String getEquipmentName() {
                    return equipmentName;
                }

                public void setEquipmentName(String equipmentName) {
                    this.equipmentName = equipmentName;
                }

                public String getEquipmentId() {
                    return equipmentId;
                }

                public void setEquipmentId(String equipmentId) {
                    this.equipmentId = equipmentId;
                }

                public String getEquipmentImg() {
                    return equipmentImg;
                }

                public void setEquipmentImg(String equipmentImg) {
                    this.equipmentImg = equipmentImg;
                }

                public Object getPosition() {
                    return position;
                }

                public void setPosition(Object position) {
                    this.position = position;
                }
            }
        }

        public static class ApplyBean {
            /**
             * caseId : 12
             * caseName : 装备演示案件
             * caseCreateTime : 2019-12-20T06:45:35Z
             * casePrincipalName : 鲁达
             * casePrincipalId : 34
             * equipment : [{"equipmentName":"定位测试005","equipmentId":"1594278307683","equipmentImg":"group1/M00/00/00/wKgLuV8Gwh6AMVnrAAAAgtj0ipc43.jpeg","position":null},{"equipmentName":"定位测试004","equipmentId":"1594278066574","equipmentImg":"group1/M00/00/00/wKgLuV8GwYmAR7JVAALQASwd6-s75.jpeg","position":null}]
             */

            private int caseId;
            private String caseName;
            private String caseCreateTime;
            private String casePrincipalName;
            private int casePrincipalId;
            private List<EquipmentBeanX> equipment;

            public int getCaseId() {
                return caseId;
            }

            public void setCaseId(int caseId) {
                this.caseId = caseId;
            }

            public String getCaseName() {
                return caseName;
            }

            public void setCaseName(String caseName) {
                this.caseName = caseName;
            }

            public String getCaseCreateTime() {
                return caseCreateTime;
            }

            public void setCaseCreateTime(String caseCreateTime) {
                this.caseCreateTime = caseCreateTime;
            }

            public String getCasePrincipalName() {
                return casePrincipalName;
            }

            public void setCasePrincipalName(String casePrincipalName) {
                this.casePrincipalName = casePrincipalName;
            }

            public int getCasePrincipalId() {
                return casePrincipalId;
            }

            public void setCasePrincipalId(int casePrincipalId) {
                this.casePrincipalId = casePrincipalId;
            }

            public List<EquipmentBeanX> getEquipment() {
                return equipment;
            }

            public void setEquipment(List<EquipmentBeanX> equipment) {
                this.equipment = equipment;
            }

            public static class EquipmentBeanX {
                /**
                 * equipmentName : 定位测试005
                 * equipmentId : 1594278307683
                 * equipmentImg : group1/M00/00/00/wKgLuV8Gwh6AMVnrAAAAgtj0ipc43.jpeg
                 * position : null
                 */

                private String equipmentName;
                private String equipmentId;
                private String equipmentImg;
                private Object position;

                public String getEquipmentName() {
                    return equipmentName;
                }

                public void setEquipmentName(String equipmentName) {
                    this.equipmentName = equipmentName;
                }

                public String getEquipmentId() {
                    return equipmentId;
                }

                public void setEquipmentId(String equipmentId) {
                    this.equipmentId = equipmentId;
                }

                public String getEquipmentImg() {
                    return equipmentImg;
                }

                public void setEquipmentImg(String equipmentImg) {
                    this.equipmentImg = equipmentImg;
                }

                public Object getPosition() {
                    return position;
                }

                public void setPosition(Object position) {
                    this.position = position;
                }
            }
        }

        public static class RehecrtBean {
            /**
             * caseId : 12
             * caseName : 装备演示案件
             * caseCreateTime : 2019-12-20T06:45:35Z
             * casePrincipalName : 鲁达
             * casePrincipalId : 34
             * equipment : [{"equipmentName":"定位测试005","equipmentId":"1594278307683","equipmentImg":"group1/M00/00/00/wKgLuV8Gwh6AMVnrAAAAgtj0ipc43.jpeg","position":null},{"equipmentName":"定位测试004","equipmentId":"1594278066574","equipmentImg":"group1/M00/00/00/wKgLuV8GwYmAR7JVAALQASwd6-s75.jpeg","position":null}]
             */

            private int caseId;
            private String caseName;
            private String caseCreateTime;
            private String casePrincipalName;
            private int casePrincipalId;
            private List<EquipmentBeanXX> equipment;

            public int getCaseId() {
                return caseId;
            }

            public void setCaseId(int caseId) {
                this.caseId = caseId;
            }

            public String getCaseName() {
                return caseName;
            }

            public void setCaseName(String caseName) {
                this.caseName = caseName;
            }

            public String getCaseCreateTime() {
                return caseCreateTime;
            }

            public void setCaseCreateTime(String caseCreateTime) {
                this.caseCreateTime = caseCreateTime;
            }

            public String getCasePrincipalName() {
                return casePrincipalName;
            }

            public void setCasePrincipalName(String casePrincipalName) {
                this.casePrincipalName = casePrincipalName;
            }

            public int getCasePrincipalId() {
                return casePrincipalId;
            }

            public void setCasePrincipalId(int casePrincipalId) {
                this.casePrincipalId = casePrincipalId;
            }

            public List<EquipmentBeanXX> getEquipment() {
                return equipment;
            }

            public void setEquipment(List<EquipmentBeanXX> equipment) {
                this.equipment = equipment;
            }

            public static class EquipmentBeanXX {
                /**
                 * equipmentName : 定位测试005
                 * equipmentId : 1594278307683
                 * equipmentImg : group1/M00/00/00/wKgLuV8Gwh6AMVnrAAAAgtj0ipc43.jpeg
                 * position : null
                 */

                private String equipmentName;
                private String equipmentId;
                private String equipmentImg;
                private Object position;

                public String getEquipmentName() {
                    return equipmentName;
                }

                public void setEquipmentName(String equipmentName) {
                    this.equipmentName = equipmentName;
                }

                public String getEquipmentId() {
                    return equipmentId;
                }

                public void setEquipmentId(String equipmentId) {
                    this.equipmentId = equipmentId;
                }

                public String getEquipmentImg() {
                    return equipmentImg;
                }

                public void setEquipmentImg(String equipmentImg) {
                    this.equipmentImg = equipmentImg;
                }

                public Object getPosition() {
                    return position;
                }

                public void setPosition(Object position) {
                    this.position = position;
                }
            }
        }
    }
}
