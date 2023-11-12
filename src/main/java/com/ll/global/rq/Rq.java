package com.ll.global.rq;

public class Rq {
    private final String action;

    public Rq(String cmd) {
        final String[] cmdBIts=cmd.split("\\?",2);
        action=cmdBIts[0];
    }

    public String getAction() {

        return action;
    }

    public String getParameter(String 이름) {
        return null;
    }
}
