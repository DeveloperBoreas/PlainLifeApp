package com.xwzx.equipmanager.mvp.models.equipLib;

public class EquipLibraryModel {
    private int from;//页数
    private int size;//条数
    private String wareHouse;//所在库房
    private String equipmentType;
    private String manufacturer;
    private int depId;
    private int status;
    private int headId;

    public EquipLibraryModel() {
    }

    /**
     * @param from 页数
     * @param size 条数
     * @param wareHouse 所在库房
     * @param equipmentType 装备类型
     * @param manufacturer 厂商
     * @param depId 部门Id
     * @param status 装备状态
     * @param headId 负责人id(暂不启用)
     */
    public EquipLibraryModel(int from, int size, String wareHouse, String equipmentType, String manufacturer, int depId, int status, int headId) {
        this.from = from;
        this.size = size;
        this.wareHouse = wareHouse;
        this.equipmentType = equipmentType;
        this.manufacturer = manufacturer;
        this.depId = depId;
        this.status = status;
        this.headId = headId;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }
}
