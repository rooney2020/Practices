package com.tsdl.practices.entity;

public class Dish {
    /**
     * id : 4
     * name : 锅包肉
     * remark : <p><strong>主料</strong>：猪里脊肉、土豆淀粉、豆油</p><p><strong>配料</strong>：胡萝卜丝、葱丝、姜丝、香菜段</p><p><strong>料汁</strong>：白糖、白醋、酱油、食盐</p><p><strong>制作过程</strong>：</p><p>1、将猪里脊肉切成筷子粗细的肉片；</p><p>2、使用菜刀拍打肉片，使肉质疏松；</p><p>3、用清水洗净肉片后攥干水分；</p><p>4、加入等量的土豆淀粉抓拌均匀；</p><p>5、分次少量加入清水抓拌均匀至淀粉微微成液态；</p><p>6、加入少许豆油抓拌均匀；</p><p>7、油温6成热下肉片炸制定型捞出；</p><p>8、油温升高放入肉片复炸15秒捞出；</p><p>9、锅留少许油放入炸好的肉片翻炒；</p><p>10、加入配料，大火烹入料汁翻炒10秒出锅；</p>
     * type : 6
     * typeName : 热菜
     * materialsRequired : 1,11,2001,24,28,30,9
     * materialsSelected : 1004,25,26,27
     * labels : 1
     * difficulty : 5
     * videoPath : test.mp4
     * imagePath : guobaorou.jpg
     */

    private int id;
    private String name;
    private String remark;
    private int type;
    private String typeName;
    private String materialsRequired;
    private String materialsSelected;
    private String labels;
    private int difficulty;
    private String videoPath;
    private String imagePath;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Dish{" + "id=" + id + ", name='" + name + '\'' + ", remark='" + remark + '\'' + ", type=" + type +
                ", typeName='" + typeName + '\'' + ", materialsRequired='" + materialsRequired + '\'' +
                ", materialsSelected='" + materialsSelected + '\'' + ", labels='" + labels + '\'' + ", difficulty=" +
                difficulty + ", videoPath='" + videoPath + '\'' + ", imagePath='" + imagePath + '\'' + '}';
    }
}
