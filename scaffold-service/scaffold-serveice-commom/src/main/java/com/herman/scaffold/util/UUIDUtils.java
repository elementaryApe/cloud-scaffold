package com.herman.scaffold.util;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author hsh
 * @create 2019-11-11 17:45
 **/
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }
}
