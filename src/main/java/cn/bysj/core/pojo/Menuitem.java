package cn.bysj.core.pojo;

public class Menuitem {
    private Integer menuitemId;

    private String itemName;

    private String itemUrl;

    private String detail;

    private Integer parentId;

    private Integer childId;

    public Integer getMenuitemId() {
        return menuitemId;
    }

    public void setMenuitemId(Integer menuitemId) {
        this.menuitemId = menuitemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl == null ? null : itemUrl.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }
}