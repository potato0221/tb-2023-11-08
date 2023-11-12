package com.ll.global.rq;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private final String action;
    private final String queryString;
    private Map<String,String> params;

    public Rq(String cmd) {
        final String[] cmdBIts = cmd.split("\\?", 2);
        action = cmdBIts[0];
        queryString = cmdBIts.length == 2 ? cmdBIts[1].trim() : "";
        params=new HashMap<>();

        if(queryString.isBlank()) return;

        final String[] paramBits=queryString.split("=",2);
        String paramName=paramBits[0].trim();
        String paramValue=paramBits[1].trim();
        params.put(paramName,paramValue);
    }

    public String getAction() {

        return action;
    }

    public String getParameter(String paramName) {
        return params.get(paramName);
    }
}
