package com.boreas.plainlife.mvp.models.indexoperator;

import com.boreas.plainlife.mvp.ModelInterface;

import java.io.Serializable;
import java.util.List;

public class TaskModel implements ModelInterface {

    /**
     * data : {"status":1,"data":[{"id":12,"name":"测试案件","tasks":[{"id":11,"name":"任务10000","identifier":"2020-07-06 16:27:18","create_time":"2020-07-06T08:27:37Z","equipment":[]}]},{"id":36,"name":"秀清专用案件","tasks":[{"id":19,"name":"秀清任务1","identifier":"2020-07-09 14:33:50","create_time":"2020-07-09T06:34:23Z","equipment":[]},{"id":20,"name":"秀清任务2","identifier":"2020-07-09 14:34:09","create_time":"2020-07-09T06:34:23Z","equipment":[]}]},{"id":35,"name":"让金玉专用案件","tasks":[{"id":18,"name":"小玉任务02","identifier":"2020-07-09 14:32:31","create_time":"2020-07-09T06:32:47Z","equipment":[{"equipment_id":1594266792162,"equipment_type":"LOCAL","equipment_name":"定位测试003","equipment_img":"group1/M00/00/00/wKgLuV8GlRmAZPXnAAKO5BNUSpk414.jpg"},{"equipment_id":1594266578825,"equipment_type":"LOCAL","equipment_name":"定位测试002","equipment_img":"group1/M00/00/00/wKgLuV8GlH2AWHrCAAYvzyC8Dkc759.jpg"}]},{"id":17,"name":"小玉任务01","identifier":"2020-07-09 14:32:11","create_time":"2020-07-09T06:32:30Z","equipment":[{"equipment_id":1594265468437,"equipment_type":"LOCAL","equipment_name":"反制测试001","equipment_img":"group1/M00/00/34/DA0pOl7x_aCAT9_cAAALI36jaqU695.png"}]}]}]}
     * msg : OK
     * status : 200
     */

    private DataBeanX data;
    private String msg;
    private int status;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
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

    public static class DataBeanX implements Serializable {
        /**
         * status : 1
         * data : [{"id":12,"name":"测试案件","tasks":[{"id":11,"name":"任务10000","identifier":"2020-07-06 16:27:18","create_time":"2020-07-06T08:27:37Z","equipment":[]}]},{"id":36,"name":"秀清专用案件","tasks":[{"id":19,"name":"秀清任务1","identifier":"2020-07-09 14:33:50","create_time":"2020-07-09T06:34:23Z","equipment":[]},{"id":20,"name":"秀清任务2","identifier":"2020-07-09 14:34:09","create_time":"2020-07-09T06:34:23Z","equipment":[]}]},{"id":35,"name":"让金玉专用案件","tasks":[{"id":18,"name":"小玉任务02","identifier":"2020-07-09 14:32:31","create_time":"2020-07-09T06:32:47Z","equipment":[{"equipment_id":1594266792162,"equipment_type":"LOCAL","equipment_name":"定位测试003","equipment_img":"group1/M00/00/00/wKgLuV8GlRmAZPXnAAKO5BNUSpk414.jpg"},{"equipment_id":1594266578825,"equipment_type":"LOCAL","equipment_name":"定位测试002","equipment_img":"group1/M00/00/00/wKgLuV8GlH2AWHrCAAYvzyC8Dkc759.jpg"}]},{"id":17,"name":"小玉任务01","identifier":"2020-07-09 14:32:11","create_time":"2020-07-09T06:32:30Z","equipment":[{"equipment_id":1594265468437,"equipment_type":"LOCAL","equipment_name":"反制测试001","equipment_img":"group1/M00/00/34/DA0pOl7x_aCAT9_cAAALI36jaqU695.png"}]}]}]
         */

        private int status;
        private List<DataBean> data;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            /**
             * id : 12
             * name : 测试案件
             * tasks : [{"id":11,"name":"任务10000","identifier":"2020-07-06 16:27:18","create_time":"2020-07-06T08:27:37Z","equipment":[]}]
             */

            private int id;
            private String name;
            private List<TasksBean> tasks;

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

            public List<TasksBean> getTasks() {
                return tasks;
            }

            public void setTasks(List<TasksBean> tasks) {
                this.tasks = tasks;
            }

            public static class TasksBean implements Serializable{
                /**
                 * id : 11
                 * name : 任务10000
                 * identifier : 2020-07-06 16:27:18
                 * create_time : 2020-07-06T08:27:37Z
                 * equipment : []
                 */

                private int id;
                private String name;
                private String identifier;
                private String create_time;
                private List<EquipmentBean> equipment;

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

                public String getIdentifier() {
                    return identifier;
                }

                public void setIdentifier(String identifier) {
                    this.identifier = identifier;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public List<EquipmentBean> getEquipment() {
                    return equipment;
                }

                public void setEquipment(List<EquipmentBean> equipment) {
                    this.equipment = equipment;
                }

                public static class EquipmentBean implements Serializable{

                    /**
                     * equipment_id : 1594266792162
                     * equipment_type : LOCAL
                     * equipment_name : 定位测试003
                     * equipment_img : group1/M00/00/00/wKgLuV8GlRmAZPXnAAKO5BNUSpk414.jpg
                     */

                    private long equipment_id;
                    private String equipment_type;
                    private String equipment_name;
                    private String equipment_img;

                    public long getEquipment_id() {
                        return equipment_id;
                    }

                    public void setEquipment_id(long equipment_id) {
                        this.equipment_id = equipment_id;
                    }

                    public String getEquipment_type() {
                        return equipment_type;
                    }

                    public void setEquipment_type(String equipment_type) {
                        this.equipment_type = equipment_type;
                    }

                    public String getEquipment_name() {
                        return equipment_name;
                    }

                    public void setEquipment_name(String equipment_name) {
                        this.equipment_name = equipment_name;
                    }

                    public String getEquipment_img() {
                        return equipment_img;
                    }

                    public void setEquipment_img(String equipment_img) {
                        this.equipment_img = equipment_img;
                    }
                }
            }
        }
    }
}
