package com.happiest.minds.ffms;

public interface Webserver {

    String PROTOCOL_HTTP = "http://";
    String SERVER_IP = "10.16.35.96:";
    int SERVER_PORT = 8081;

    String SERVER_HOST = "http://10.16.35.96:8081";

    /*Sales service URI*/

    String SALES_CARD_VIEW_URI = "/ticket/list-view";
    String SALES_LEAD_DETAILS = "/ticket/details";
    String SALES_LEAD_CREATE = "/ticket/create";
}
