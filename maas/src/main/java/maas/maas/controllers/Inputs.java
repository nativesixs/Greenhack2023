package maas.maas.controllers;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Inputs {

    private String ckodField;
    private String simckodField;
    private String dpuckodField;
    private String inventoryField;
    private int mode;
    private String lvmField;
    private String platoField;
    private String placeckodField;

    private String id;

    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceckodField() {
        return placeckodField;
    }

    public void setPlaceckodField(String placeckodField) {
        this.placeckodField = placeckodField;
    }

    public String getLvmField() {
        return lvmField;
    }

    public void setLvmField(String lvmField) {
        this.lvmField = lvmField;
    }

    public String getPlatoField() {
        return platoField;
    }

    public void setPlatoField(String platoField) {
        this.platoField = platoField;
    }

    public String getSimckodField() {
        return simckodField;
    }

    public void setSimckodField(String simckodField) {
        this.simckodField = simckodField;
    }

    public String getDpuckodField() {
        return dpuckodField;
    }

    public void setDpuckodField(String dpuckodField) {
        this.dpuckodField = dpuckodField;
    }

    public String getCkodField() {
        return ckodField;
    }

    public void setCkodField(String ckodField) {
        this.ckodField = ckodField;
    }

    public String getInventoryField() {
        return inventoryField;
    }

    public void setInventoryField(String inventoryField) {
        this.inventoryField = inventoryField;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
