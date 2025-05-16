package com.auto.utils;

import com.auto.config.GetApplicationData;
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
        GetApplicationData applicationData = new GetApplicationData();
        String address = applicationData.address;
        String uri="";
        String testUrl;

        if(name== WeimobInterfaceName.CREATEID){
            uri=applicationData.createIdUri;
        }
        if (name==WeimobInterfaceName.CREATE){
            uri=applicationData.createUri;
        }
        if(name==WeimobInterfaceName.STATE){
            uri=applicationData.stateUri;
        }
        if(name==WeimobInterfaceName.UPDATE){
            uri=applicationData.updateUri;
        }
        if(name == WeimobInterfaceName.CREATESNCODE){
            uri=applicationData.createSnCodeUri;
        }
        if (name == WeimobInterfaceName.PLAY){
            uri=applicationData.playUri;
        }
        if (name == WeimobInterfaceName.LISTWINNERBYPAGE){
            uri=applicationData.listWinnerByPageUri;
        }
        if(name==WeimobInterfaceName.USERQUERY){
            uri=applicationData.userList;
        }

        testUrl = address + uri;
        return testUrl;
    }
}
