package com.myretail.product.domain;

import java.io.Serializable;

public class HeartBeatResponse {
    private String appName;
    private String version;

    public HeartBeatResponse(){

    }

    public HeartBeatResponse(String appName, String version){
        this.appName = appName;
        this.version = version;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
