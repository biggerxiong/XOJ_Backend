package me.xiongxuan.xoj.entity.response;

public class FontedRouterInfo {

    public String routerLink;

    public String routerName;

    public String iconType;

    public String iconTheme;

    public FontedRouterInfo(String routerLink, String routerName, String iconType, String iconTheme) {
        this.routerLink = routerLink;
        this.routerName = routerName;
        this.iconType = iconType;
        this.iconTheme = iconTheme;
    }

    public FontedRouterInfo(String routerLink, String routerName) {
        this.routerLink = routerLink;
        this.routerName = routerName;
        this.iconType = "smile";
        this.iconTheme = "twotone";
    }

    public FontedRouterInfo(String routerLink, String routerName, String iconType) {
        this.routerLink = routerLink;
        this.routerName = routerName;
        this.iconType = iconType;
        this.iconTheme = "fill";
    }

    public String getRouterLink() {
        return routerLink;
    }

    public void setRouterLink(String routerLink) {
        this.routerLink = routerLink;
    }

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getIconTheme() {
        return iconTheme;
    }

    public void setIconTheme(String iconTheme) {
        this.iconTheme = iconTheme;
    }

    @Override
    public String toString() {
        return "FontedRouterInfo{" +
                "routerLink='" + routerLink + '\'' +
                ", routerName='" + routerName + '\'' +
                ", iconType='" + iconType + '\'' +
                ", iconTheme='" + iconTheme + '\'' +
                '}';
    }
}
