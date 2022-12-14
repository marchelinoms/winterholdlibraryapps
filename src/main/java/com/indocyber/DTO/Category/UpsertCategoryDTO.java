package com.indocyber.DTO.Category;

public class UpsertCategoryDTO {

    private String name;

    private Integer floor;

    private String isle;

    private Integer bay;

    public UpsertCategoryDTO() {
    }

    public UpsertCategoryDTO(String name, Integer floor, String isle, Integer bay) {
        this.name = name;
        this.floor = floor;
        this.isle = isle;
        this.bay = bay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getIsle() {
        return isle;
    }

    public void setIsle(String isle) {
        this.isle = isle;
    }

    public Integer getBay() {
        return bay;
    }

    public void setBay(Integer bay) {
        this.bay = bay;
    }
}
