package com.happiest.minds.ffms;

public interface Webserver {

    String PROTOCOL_HTTP = "http://";
    String SERVER_IP = "10.16.35.96:";
    int SERVER_PORT = 8081;

    String SERVER_HOST = "http://10.16.35.96:8081";

    /*Sales service URI*/

    String SALES_COUNT_SERVICE = "/ticket/dashboard-count";
    String SALES_CARD_VIEW_URI = "/ticket/list-view";
    String SALES_LEAD_DETAILS = "/ticket/details";
    String SALES_LEAD_CREATE = "/ticket/create";
    String SALES_LEAD_UPDATE = "/ticket/basic-info-update";
    String ASSET_TYPE_URI = "/asset/selection";
    String MODEL_FOR_ASSET_TYPE_URI = "/product/assetType";
    String MODEL_SPECIFICATION_URI = "/productspec/model/";
    String BRANCH_URI = "/location/branchByCityId";
    String AREA_URI ="/location/areaByBranchId";
    String TITLE_SERVICE = "/customer/title";
    String IMAGE_URI = "/product/imagelist/model";
    String ORDER_URI = "/order/save";
    String ORDER_SYNC_URI = "/order/getByTicketId";
    String SEARCH_URI = "/ticket/filter";
}
