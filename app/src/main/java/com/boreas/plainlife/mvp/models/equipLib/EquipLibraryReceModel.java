package com.boreas.plainlife.mvp.models.equipLib;

import com.boreas.plainlife.mvp.ModelInterface;

import java.util.ArrayList;
import java.util.List;

public class EquipLibraryReceModel implements ModelInterface{
    /**
     * data : {"totalCount":18,"data":[{"discard":2,"takeCount":0,"num":9993,"equipmentType":"定位","operator":{"id":1,"sex":null,"passwordExpired":false,"post":null,"approvalTemplates":[{"id":1},{"id":2}],"applys":[{"id":13},{"id":14}],"accountExpired":false,"profilePhotoUrl":"group1/M00/00/2A/DA0pOl4O0BiADhjnAAwJW48-kiI690.jpg","mobile":"13520164579","position":null,"department":{"id":2},"username":"admin","accountLocked":false,"name":"admin","password":"$2a$10$LKn5lvqjfThWY3XxZKSAvu6fHIwY3m.UpBYsW.uQeiO0MhJYB/W0O","approvalTemplate":{"id":1},"note":null,"enabled":true,"email":"325478896@qq.com"},"manufacturer":"智诚","wareHouse":2,"presentation":null,"score":0,"maintenancePeriodUnit":"月","parameter":[{"id":1,"equipmentId":"1594174483562","name":"装备尺寸","value":"3x5"},{"id":2,"equipmentId":"1594174483562","name":"装备精度","value":"两米以内"}],"id":"1594174483562","accessorys":[{"name":"3","id":"15941744835"}],"modelUrl":null,"searchType":"主动","contract":null,"carNum":"京A88888","equipmentScore":[],"depName":"总队","localType":"车载","headId":34,"headName":"鲁达11","createTime":"2020-07-08T02:14:44Z","discardUnit":"月","frequencyType":"2G","imageUrls":[],"name":"sdf","imei":null,"depId":3,"purchaseTime":"2020-07-08T02:14:44Z","position":null,"remarks":null,"maintenancePeriod":1,"status":null}]}
     * msg : OK
     * status : 200
     */

    private EquipData data;
    private String msg;
    private int status;

    public EquipData getData() {
        return data;
    }

    public void setData(EquipData data) {
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

    public static class EquipData implements ModelInterface{
        /**
         * totalCount : 18
         * data : [{"discard":2,"takeCount":0,"num":9993,"equipmentType":"定位","operator":{"id":1,"sex":null,"passwordExpired":false,"post":null,"approvalTemplates":[{"id":1},{"id":2}],"applys":[{"id":13},{"id":14}],"accountExpired":false,"profilePhotoUrl":"group1/M00/00/2A/DA0pOl4O0BiADhjnAAwJW48-kiI690.jpg","mobile":"13520164579","position":null,"department":{"id":2},"username":"admin","accountLocked":false,"name":"admin","password":"$2a$10$LKn5lvqjfThWY3XxZKSAvu6fHIwY3m.UpBYsW.uQeiO0MhJYB/W0O","approvalTemplate":{"id":1},"note":null,"enabled":true,"email":"325478896@qq.com"},"manufacturer":"智诚","wareHouse":2,"presentation":null,"score":0,"maintenancePeriodUnit":"月","parameter":[{"id":1,"equipmentId":"1594174483562","name":"装备尺寸","value":"3x5"},{"id":2,"equipmentId":"1594174483562","name":"装备精度","value":"两米以内"}],"id":"1594174483562","accessorys":[{"name":"3","id":"15941744835"}],"modelUrl":null,"searchType":"主动","contract":null,"carNum":"京A88888","equipmentScore":[],"depName":"总队","localType":"车载","headId":34,"headName":"鲁达11","createTime":"2020-07-08T02:14:44Z","discardUnit":"月","frequencyType":"2G","imageUrls":[],"name":"sdf","imei":null,"depId":3,"purchaseTime":"2020-07-08T02:14:44Z","position":null,"remarks":null,"maintenancePeriod":1,"status":null}]
         */

        private int totalCount;
        private ArrayList<EquipDataListModel> data;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public ArrayList<EquipDataListModel> getData() {
            return data;
        }

        public void setData(ArrayList<EquipDataListModel> data) {
            this.data = data;
        }


    }

    public static class EquipDataListModel implements ModelInterface{
        /**
         * discard : 2
         * takeCount : 0
         * num : 9993
         * equipmentType : 定位
         * operator : {"id":1,"sex":null,"passwordExpired":false,"post":null,"approvalTemplates":[{"id":1},{"id":2}],"applys":[{"id":13},{"id":14}],"accountExpired":false,"profilePhotoUrl":"group1/M00/00/2A/DA0pOl4O0BiADhjnAAwJW48-kiI690.jpg","mobile":"13520164579","position":null,"department":{"id":2},"username":"admin","accountLocked":false,"name":"admin","password":"$2a$10$LKn5lvqjfThWY3XxZKSAvu6fHIwY3m.UpBYsW.uQeiO0MhJYB/W0O","approvalTemplate":{"id":1},"note":null,"enabled":true,"email":"325478896@qq.com"}
         * manufacturer : 智诚
         * wareHouse : 2
         * presentation : null
         * score : 0
         * maintenancePeriodUnit : 月
         * parameter : [{"id":1,"equipmentId":"1594174483562","name":"装备尺寸","value":"3x5"},{"id":2,"equipmentId":"1594174483562","name":"装备精度","value":"两米以内"}]
         * id : 1594174483562
         * accessorys : [{"name":"3","id":"15941744835"}]
         * modelUrl : null
         * searchType : 主动
         * contract : null
         * carNum : 京A88888
         * equipmentScore : []
         * depName : 总队
         * localType : 车载
         * headId : 34
         * headName : 鲁达11
         * createTime : 2020-07-08T02:14:44Z
         * discardUnit : 月
         * frequencyType : 2G
         * imageUrls : []
         * name : sdf
         * imei : null
         * depId : 3
         * purchaseTime : 2020-07-08T02:14:44Z
         * position : null
         * remarks : null
         * maintenancePeriod : 1
         * status : null
         */



        private int discard;
        private int takeCount;
        private String num;
        private String equipmentType;
        private String equipmentType_en;
        private OperatorBean operator;
        private String manufacturer;
        private int wareHouse;
        private String presentation;
        private int score;
        private String maintenancePeriodUnit;
        private String id;
        private String modelUrl;
        private String searchType;
        private String contract;
        private String carNum;
        private String depName;
        private String localType;
        private int headId;
        private String headName;
        private String createTime;
        private String discardUnit;
        private String frequencyType;
        private String name;
        private String imei;
        private int depId;
        private String purchaseTime;
        private String position;
        private String remarks;
        private int maintenancePeriod;
        private String status;
        private List<ParameterBean> parameter;
        private List<AccessorysBean> accessorys;
        private List<String> imageUrls;

        public EquipDataListModel() {
        }

        public String getEquipmentType_en() {
            return equipmentType_en;
        }

        public void setEquipmentType_en(String equipmentType_en) {
            this.equipmentType_en = equipmentType_en;
        }

        public int getDiscard() {
            return discard;
        }

        public void setDiscard(int discard) {
            this.discard = discard;
        }

        public int getTakeCount() {
            return takeCount;
        }

        public void setTakeCount(int takeCount) {
            this.takeCount = takeCount;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getEquipmentType() {
            return equipmentType;
        }

        public void setEquipmentType(String equipmentType) {
            this.equipmentType = equipmentType;
        }

        public OperatorBean getOperator() {
            return operator;
        }

        public void setOperator(OperatorBean operator) {
            this.operator = operator;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public int getWareHouse() {
            return wareHouse;
        }

        public void setWareHouse(int wareHouse) {
            this.wareHouse = wareHouse;
        }

        public String getPresentation() {
            return presentation;
        }

        public void setPresentation(String presentation) {
            this.presentation = presentation;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getMaintenancePeriodUnit() {
            return maintenancePeriodUnit;
        }

        public void setMaintenancePeriodUnit(String maintenancePeriodUnit) {
            this.maintenancePeriodUnit = maintenancePeriodUnit;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getModelUrl() {
            return modelUrl;
        }

        public void setModelUrl(String modelUrl) {
            this.modelUrl = modelUrl;
        }

        public String getSearchType() {
            return searchType;
        }

        public void setSearchType(String searchType) {
            this.searchType = searchType;
        }


        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public String getDepName() {
            return depName;
        }

        public void setDepName(String depName) {
            this.depName = depName;
        }

        public String getLocalType() {
            return localType;
        }

        public void setLocalType(String localType) {
            this.localType = localType;
        }

        public int getHeadId() {
            return headId;
        }

        public void setHeadId(int headId) {
            this.headId = headId;
        }

        public String getHeadName() {
            return headName;
        }

        public void setHeadName(String headName) {
            this.headName = headName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDiscardUnit() {
            return discardUnit;
        }

        public void setDiscardUnit(String discardUnit) {
            this.discardUnit = discardUnit;
        }

        public String getFrequencyType() {
            return frequencyType;
        }

        public void setFrequencyType(String frequencyType) {
            this.frequencyType = frequencyType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public int getDepId() {
            return depId;
        }

        public void setDepId(int depId) {
            this.depId = depId;
        }

        public String getPurchaseTime() {
            return purchaseTime;
        }

        public void setPurchaseTime(String purchaseTime) {
            this.purchaseTime = purchaseTime;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getMaintenancePeriod() {
            return maintenancePeriod;
        }

        public void setMaintenancePeriod(int maintenancePeriod) {
            this.maintenancePeriod = maintenancePeriod;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ParameterBean> getParameter() {
            return parameter;
        }

        public void setParameter(List<ParameterBean> parameter) {
            this.parameter = parameter;
        }

        public List<AccessorysBean> getAccessorys() {
            return accessorys;
        }

        public void setAccessorys(List<AccessorysBean> accessorys) {
            this.accessorys = accessorys;
        }

        public String getContract() {
            return contract;
        }

        public void setContract(String contract) {
            this.contract = contract;
        }

        public List<String> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<String> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public static class OperatorBean implements ModelInterface{
            /**
             * id : 1
             * sex : null
             * passwordExpired : false
             * post : null
             * approvalTemplates : [{"id":1},{"id":2}]
             * applys : [{"id":13},{"id":14}]
             * accountExpired : false
             * profilePhotoUrl : group1/M00/00/2A/DA0pOl4O0BiADhjnAAwJW48-kiI690.jpg
             * mobile : 13520164579
             * position : null
             * department : {"id":2}
             * username : admin
             * accountLocked : false
             * name : admin
             * password : $2a$10$LKn5lvqjfThWY3XxZKSAvu6fHIwY3m.UpBYsW.uQeiO0MhJYB/W0O
             * approvalTemplate : {"id":1}
             * note : null
             * enabled : true
             * email : 325478896@qq.com
             */

            private int id;
            private String sex;
            private boolean passwordExpired;
            private boolean accountExpired;
            private String profilePhotoUrl;
            private String mobile;
            private String position;
            private DepartmentBean department;
            private String username;
            private boolean accountLocked;
            private String name;
            private String password;
            private ApprovalTemplateBean approvalTemplate;
            private String note;
            private boolean enabled;
            private String email;
            private List<ApprovalTemplatesBean> approvalTemplates;
            private List<ApplysBean> applys;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public boolean isPasswordExpired() {
                return passwordExpired;
            }

            public void setPasswordExpired(boolean passwordExpired) {
                this.passwordExpired = passwordExpired;
            }


            public boolean isAccountExpired() {
                return accountExpired;
            }

            public void setAccountExpired(boolean accountExpired) {
                this.accountExpired = accountExpired;
            }

            public String getProfilePhotoUrl() {
                return profilePhotoUrl;
            }

            public void setProfilePhotoUrl(String profilePhotoUrl) {
                this.profilePhotoUrl = profilePhotoUrl;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public DepartmentBean getDepartment() {
                return department;
            }

            public void setDepartment(DepartmentBean department) {
                this.department = department;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public boolean isAccountLocked() {
                return accountLocked;
            }

            public void setAccountLocked(boolean accountLocked) {
                this.accountLocked = accountLocked;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public ApprovalTemplateBean getApprovalTemplate() {
                return approvalTemplate;
            }

            public void setApprovalTemplate(ApprovalTemplateBean approvalTemplate) {
                this.approvalTemplate = approvalTemplate;
            }


            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public List<ApprovalTemplatesBean> getApprovalTemplates() {
                return approvalTemplates;
            }

            public void setApprovalTemplates(List<ApprovalTemplatesBean> approvalTemplates) {
                this.approvalTemplates = approvalTemplates;
            }

            public List<ApplysBean> getApplys() {
                return applys;
            }

            public void setApplys(List<ApplysBean> applys) {
                this.applys = applys;
            }

            public static class DepartmentBean implements ModelInterface{
                /**
                 * id : 2
                 */

                private int id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }

            public static class ApprovalTemplateBean implements ModelInterface{
                /**
                 * id : 1
                 */

                private int id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }

            public static class ApprovalTemplatesBean implements ModelInterface{
                /**
                 * id : 1
                 */

                private int id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }

            public static class ApplysBean implements ModelInterface {
                /**
                 * id : 13
                 */

                private int id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }

        public static class ParameterBean implements ModelInterface {
            /**
             * id : 1
             * equipmentId : 1594174483562
             * name : 装备尺寸
             * value : 3x5
             */

            private int id;
            private String equipmentId;
            private String name;
            private String value;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class AccessorysBean implements ModelInterface{
            /**
             * name : 3
             * id : 15941744835
             */

            private String name;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }


}
