/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.config;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author avni
 */
public class ResourceMappingConfig {

    private static Map<String, String> urlResourceMap = new HashMap<>();

    static {
        urlResourceMap.put("/project/user.xhtml", "userManagement");
        urlResourceMap.put("/project/staff.xhtml", "staffManagement");
        urlResourceMap.put("/project/client.xhtml", "clientManagement");
    }

    public static String getResourceNameForUrl(String url) {
        return urlResourceMap.get(url);
    }
    
}
