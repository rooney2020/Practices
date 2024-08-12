package com.tsdl.practices.entity;

public class DishQueryParams {

    private int page = 1;
    private int limit = 10;
    private String key;
    private Integer type;
    private boolean allMatch;
    private String labels;
    private String materialsRequired;
    private String materialsSelected;

    public DishQueryParams() {
    }

    public DishQueryParams(String key, Integer type, boolean allMatch, String labels, String materialsRequired,
                           String materialsSelected) {
        this.key = key;
        this.type = type;
        this.allMatch = allMatch;
        this.labels = labels;
        this.materialsRequired = materialsRequired;
        this.materialsSelected = materialsSelected;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isAllMatch() {
        return allMatch;
    }

    public void setAllMatch(boolean allMatch) {
        this.allMatch = allMatch;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getMaterialsRequired() {
        return materialsRequired;
    }

    public void setMaterialsRequired(String materialsRequired) {
        this.materialsRequired = materialsRequired;
    }

    public String getMaterialsSelected() {
        return materialsSelected;
    }

    public void setMaterialsSelected(String materialsSelected) {
        this.materialsSelected = materialsSelected;
    }

    @Override
    public String toString() {
        return "?page=" + page + "&limit=" + limit
                + "&key=" + (key == null ? "" : key)
                + "&type=" + (type == null ? "" : type)
                + "&allMatch=" + allMatch
                + "&labels=" + (labels == null ? "" : labels)
                + "&materialsRequired=" + (materialsRequired == null ? "" : materialsRequired)
                + "&materialsSelected=" + (materialsSelected == null ? "" : materialsSelected);
    }
}
