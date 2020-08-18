package com.xwzx.equipmanager.mvp.models.login;

public class AccessModel {

    /**
     * data : {"level1":{"个人中心":true,"库房装备":true,"执法监督":false,"指挥调度":true,"操作终端":true,"日志管理":true,"权限管理":true,"案件管理":true,"消息中心":true,"移动办案":true,"绩效考核":false,"装备采购":false},"level2":{"创建案件":true,"定位指挥":true,"审批管理":false,"库房状态":true,"库管人员":true,"操作人员":true,"操作日志":true,"案例分析":true,"用户管理":true,"终端认证":true,"装备培训":true,"装备管理":true,"角色管理":true,"部门管理":true}}
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
         * level1 : {"个人中心":true,"库房装备":true,"执法监督":false,"指挥调度":true,"操作终端":true,"日志管理":true,"权限管理":true,"案件管理":true,"消息中心":true,"移动办案":true,"绩效考核":false,"装备采购":false}
         * level2 : {"创建案件":true,"定位指挥":true,"审批管理":false,"库房状态":true,"库管人员":true,"操作人员":true,"操作日志":true,"案例分析":true,"用户管理":true,"终端认证":true,"装备培训":true,"装备管理":true,"角色管理":true,"部门管理":true}
         */

        private Level1Bean level1;
        private Level2Bean level2;

        public Level1Bean getLevel1() {
            return level1;
        }

        public void setLevel1(Level1Bean level1) {
            this.level1 = level1;
        }

        public Level2Bean getLevel2() {
            return level2;
        }

        public void setLevel2(Level2Bean level2) {
            this.level2 = level2;
        }

        public static class Level1Bean {
            /**
             * 个人中心 : true
             * 库房装备 : true
             * 执法监督 : false
             * 指挥调度 : true
             * 操作终端 : true
             * 日志管理 : true
             * 权限管理 : true
             * 案件管理 : true
             * 消息中心 : true
             * 移动办案 : true
             * 绩效考核 : false
             * 装备采购 : false
             */

            private boolean 个人中心;
            private boolean 库房装备;
            private boolean 执法监督;
            private boolean 指挥调度;
            private boolean 操作终端;
            private boolean 日志管理;
            private boolean 权限管理;
            private boolean 案件管理;
            private boolean 消息中心;
            private boolean 移动办案;
            private boolean 绩效考核;
            private boolean 装备采购;

            public boolean is个人中心() {
                return 个人中心;
            }

            public void set个人中心(boolean 个人中心) {
                this.个人中心 = 个人中心;
            }

            public boolean is库房装备() {
                return 库房装备;
            }

            public void set库房装备(boolean 库房装备) {
                this.库房装备 = 库房装备;
            }

            public boolean is执法监督() {
                return 执法监督;
            }

            public void set执法监督(boolean 执法监督) {
                this.执法监督 = 执法监督;
            }

            public boolean is指挥调度() {
                return 指挥调度;
            }

            public void set指挥调度(boolean 指挥调度) {
                this.指挥调度 = 指挥调度;
            }

            public boolean is操作终端() {
                return 操作终端;
            }

            public void set操作终端(boolean 操作终端) {
                this.操作终端 = 操作终端;
            }

            public boolean is日志管理() {
                return 日志管理;
            }

            public void set日志管理(boolean 日志管理) {
                this.日志管理 = 日志管理;
            }

            public boolean is权限管理() {
                return 权限管理;
            }

            public void set权限管理(boolean 权限管理) {
                this.权限管理 = 权限管理;
            }

            public boolean is案件管理() {
                return 案件管理;
            }

            public void set案件管理(boolean 案件管理) {
                this.案件管理 = 案件管理;
            }

            public boolean is消息中心() {
                return 消息中心;
            }

            public void set消息中心(boolean 消息中心) {
                this.消息中心 = 消息中心;
            }

            public boolean is移动办案() {
                return 移动办案;
            }

            public void set移动办案(boolean 移动办案) {
                this.移动办案 = 移动办案;
            }

            public boolean is绩效考核() {
                return 绩效考核;
            }

            public void set绩效考核(boolean 绩效考核) {
                this.绩效考核 = 绩效考核;
            }

            public boolean is装备采购() {
                return 装备采购;
            }

            public void set装备采购(boolean 装备采购) {
                this.装备采购 = 装备采购;
            }
        }

        public static class Level2Bean {
            /**
             * 创建案件 : true
             * 定位指挥 : true
             * 审批管理 : false
             * 库房状态 : true
             * 库管人员 : true
             * 操作人员 : true
             * 操作日志 : true
             * 案例分析 : true
             * 用户管理 : true
             * 终端认证 : true
             * 装备培训 : true
             * 装备管理 : true
             * 角色管理 : true
             * 部门管理 : true
             */

            private boolean 创建案件;
            private boolean 定位指挥;
            private boolean 审批管理;
            private boolean 库房状态;
            private boolean 库管人员;
            private boolean 操作人员;
            private boolean 操作日志;
            private boolean 案例分析;
            private boolean 用户管理;
            private boolean 终端认证;
            private boolean 装备培训;
            private boolean 装备管理;
            private boolean 角色管理;
            private boolean 部门管理;

            public boolean is创建案件() {
                return 创建案件;
            }

            public void set创建案件(boolean 创建案件) {
                this.创建案件 = 创建案件;
            }

            public boolean is定位指挥() {
                return 定位指挥;
            }

            public void set定位指挥(boolean 定位指挥) {
                this.定位指挥 = 定位指挥;
            }

            public boolean is审批管理() {
                return 审批管理;
            }

            public void set审批管理(boolean 审批管理) {
                this.审批管理 = 审批管理;
            }

            public boolean is库房状态() {
                return 库房状态;
            }

            public void set库房状态(boolean 库房状态) {
                this.库房状态 = 库房状态;
            }

            public boolean is库管人员() {
                return 库管人员;
            }

            public void set库管人员(boolean 库管人员) {
                this.库管人员 = 库管人员;
            }

            public boolean is操作人员() {
                return 操作人员;
            }

            public void set操作人员(boolean 操作人员) {
                this.操作人员 = 操作人员;
            }

            public boolean is操作日志() {
                return 操作日志;
            }

            public void set操作日志(boolean 操作日志) {
                this.操作日志 = 操作日志;
            }

            public boolean is案例分析() {
                return 案例分析;
            }

            public void set案例分析(boolean 案例分析) {
                this.案例分析 = 案例分析;
            }

            public boolean is用户管理() {
                return 用户管理;
            }

            public void set用户管理(boolean 用户管理) {
                this.用户管理 = 用户管理;
            }

            public boolean is终端认证() {
                return 终端认证;
            }

            public void set终端认证(boolean 终端认证) {
                this.终端认证 = 终端认证;
            }

            public boolean is装备培训() {
                return 装备培训;
            }

            public void set装备培训(boolean 装备培训) {
                this.装备培训 = 装备培训;
            }

            public boolean is装备管理() {
                return 装备管理;
            }

            public void set装备管理(boolean 装备管理) {
                this.装备管理 = 装备管理;
            }

            public boolean is角色管理() {
                return 角色管理;
            }

            public void set角色管理(boolean 角色管理) {
                this.角色管理 = 角色管理;
            }

            public boolean is部门管理() {
                return 部门管理;
            }

            public void set部门管理(boolean 部门管理) {
                this.部门管理 = 部门管理;
            }
        }
    }
}
