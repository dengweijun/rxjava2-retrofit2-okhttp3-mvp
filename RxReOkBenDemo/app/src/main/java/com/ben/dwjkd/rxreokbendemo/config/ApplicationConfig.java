package com.ben.dwjkd.rxreokbendemo.config;


public class ApplicationConfig {

    // 程序模式,开发
    public static AppModel APP_Model = AppModel.Test;

    public enum AppModel {
        Product, Test, Develop, InnerTest
    }

}
