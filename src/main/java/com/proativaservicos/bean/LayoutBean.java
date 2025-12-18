package com.proativaservicos.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class LayoutBean implements Serializable {


    private static final long serialVersionUID = 1L;
    //moody
    //bluegrey
    private String layout = "blue";

    private String theme = "blue";

    private boolean darkMenu = true;

    private boolean slimMenu = false;


    //overlayMenu ou caso falso staicMenu
    private boolean overlayMenu = true;


    public boolean isSlimMenu() {
        return slimMenu;
    }

    public void setSlimMenu(boolean slimMenu) {
        this.slimMenu = slimMenu;
    }

    public boolean isOverlayMenu() {
        return overlayMenu;
    }

    public void setOverlayMenu(boolean overlayMenu) {
        this.overlayMenu = overlayMenu;
    }


    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isDarkMenu() {
        return darkMenu;
    }

    public void setDarkMenu(boolean darkMenu) {
        this.darkMenu = darkMenu;
    }


}
