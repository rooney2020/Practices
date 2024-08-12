package com.tsdl.practices.entity;

public class Type {

    /**
     * id : 4
     * name : 主食
     * remark : 如馒头、面条、米饭等
     * type : 1
     */

    private int id;
    private String name;
    private String remark;
    private int type;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type{" + "id=" + id + ", name='" + name + '\'' + ", remark='" + remark + '\'' + ", type=" + type + '}';
    }
}
