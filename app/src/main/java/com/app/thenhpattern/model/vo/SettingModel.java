package com.app.thenhpattern.model.vo;


public class SettingModel {

    Integer itemIcon;
    String itemTitle;
    Integer itemId;

    public static SettingModel createItem(Integer itemIcon, String itemTitle, Integer itemId){
        return new SettingModel(itemIcon,itemTitle,itemId);
    }

    private SettingModel(Integer itemIcon, String itemTitle,Integer itemId) {
        this.itemIcon = itemIcon;
        this.itemTitle = itemTitle;
        this.itemId = itemId;
    }

    public Integer getItemIcon() {
        return itemIcon;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public Integer getItemId() { return itemId; }
}
