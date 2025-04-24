package com.auto.model;

import lombok.Data;

@Data
public class WeimobActivityCreateCase {
    private int id;
    private String templateKey;
    private String themeCode;
    private String basicSettings;
    private String prizeSettings;
    private String ruleSettings;
    private String basicInfo;
    private String i18n;
    private String extendInfo;
    private String event;
    private String action;
    private String description;
    private int generateWay;
    private int number;
}
