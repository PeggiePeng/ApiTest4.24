package com.auto.model;

import lombok.Data;
import org.json.JSONObject;

@Data
public class WeimobActivityCreateIdCase {
    private int id;
    private String templateKey;
    private String basicInfo;
    private String i18n;
    private String extendInfo;
    private String description;
}
