package com.ben.dwjkd.rxreokbendemo.config;

public class UrlConfig {

    public static String baseUrl;

    static {
        if (ApplicationConfig.APP_Model == ApplicationConfig.AppModel.Product) {
            baseUrl = "http://product_url/";
        } else if (ApplicationConfig.APP_Model == ApplicationConfig.AppModel.Test) {
            baseUrl = "http://test_url/";
        } else if (ApplicationConfig.APP_Model == ApplicationConfig.AppModel.InnerTest) {
            baseUrl = "http://innertest_url/";
        } else {
            baseUrl = "http://local_url/";
        }

    }
}