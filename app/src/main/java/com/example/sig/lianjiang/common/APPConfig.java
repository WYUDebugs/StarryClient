package com.example.sig.lianjiang.common;

/**
 * Created by wunaifu on 2018/8/13.
 */
public class APPConfig {

//    private static String base_url="http://10.0.2.2:8080/starry/";
private static String base_url="http://wyusig.com:8080/starry/";
    private static String img_url="http://192.168.0.105:8080/starry/img/";


    public static String login = base_url + "user/Login";
    public static String register = base_url + "user/register";
    public static String findUserByPhone = base_url + "user/findUserByPhone";
    public static String findAllUser = base_url + "user/findAllUser";
    public static String deleteUserByPhone = base_url + "user/deleteUserByPhone";



    public static String IS_LOGIN = "is_login";
    public static String ACCOUNT = "account";
    public static String PSW = "password";
    public static String USERDATA = "userData";//获取当前用户的key
    public static String TYPE = "userType";
}

