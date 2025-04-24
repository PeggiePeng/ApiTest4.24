package com.auto.utils;

import com.auto.model.InterfaceName;
import com.auto.model.WeimobInterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class UrlConfigFile {
    //读取application.properties文件
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    //按照自定义的枚举值传值，进行url拼接
    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri="";
        String testUrl;

        if (name==InterfaceName.ADDUSER){
            uri=bundle.getString("addUser.uri");
        }

        if(name==InterfaceName.GETUSERINFO){
            uri=bundle.getString("getUserInfo.uri");
        }

        if (name==InterfaceName.LOGIN){
            uri=bundle.getString("login.uri");
        }

        if(name==InterfaceName.GETUSERLIST){
            uri=bundle.getString("getUserList.uri");
        }

        if(name==InterfaceName.UPDATEUSERINFO){
            uri=bundle.getString("updateUserInfo.uri");
        }

        testUrl = address + uri;
        return testUrl;
    }

    public static String getWeimobUrl(WeimobInterfaceName name){
        String address = bundle.getString("weimobqa.url");
        String uri="";
        String testUrl;

        if(name== WeimobInterfaceName.BIGWHEELCREATEID){
            uri=bundle.getString("bigwheelCreateId.uri");
        }
        if (name==WeimobInterfaceName.BIGWHEELCREATE){
            uri=bundle.getString("bigwheelCreate.uri");
        }
        if(name==WeimobInterfaceName.BIGWHEELSTATE){
            uri=bundle.getString("bigwheelstate.uri");
        }
        if(name==WeimobInterfaceName.BIGWHEELUPDATE){
            uri=bundle.getString("bigwheelupdate.uri");
        }
        if(name == WeimobInterfaceName.CREATESNCODE){
            uri=bundle.getString("createSnCode.uri");
        }

        testUrl = address + uri;
        return testUrl;
    }
}
