package com.indocyber.DTO.Category;


import com.indocyber.Entities.Category;

public class CategoryDto {

    private String name;

    private Integer floor;

    private String isle;

    private Integer bay;

    private Integer totalBooks;

    private String category;

    public CategoryDto(Category category) {
        this.category = category.getName();
        this.floor = category.getFloor();
        this.isle = category.getIsle();
        this.bay = category.getBay();
    }

    public CategoryDto(String name, Integer floor, String isle, Integer bay, Integer totalBooks, String category) {
        this.name = name;
        this.floor = floor;
        this.isle = isle;
        this.bay = bay;
        this.totalBooks = totalBooks;
        this.category = category;
    }

    public CategoryDto(String name, Integer floor, String isle, Integer bay, Integer totalBooks) {
        this.name = name;
        this.floor = floor;
        this.isle = isle;
        this.bay = bay;
        this.totalBooks = totalBooks;
    }

    public CategoryDto(String name, Integer floor, String isle, Integer bay) {
        this.name = name;
        this.floor = floor;
        this.isle = isle;
        this.bay = bay;
    }

    public CategoryDto() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(Integer totalBooks) {
        this.totalBooks = totalBooks;
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
