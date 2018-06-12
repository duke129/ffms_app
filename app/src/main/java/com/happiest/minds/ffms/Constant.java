package com.happiest.minds.ffms;

public interface Constant {

    String TAG = Constant.class.getName();

    String FWMP_PREFERENCE = "FWMP_PREFERENCE";

    int NEW_LEADS = 101;
    int IN_PROGRESS_LEADS = 102;
    int COMPLETED_LEADS = 103;
    int SEARCH_LEADS = 104;
    int CREATE_NEW_LEADS = 105;
    int REJECTED_LEADS = 106;
    String SALES_ACTIVITY_DEMO = "Demo";
    String SALES_ACTIVITY_ORDER = "Order";
    public static final int PREFFERED_DATE_HOURS_DIFFERENCE = 2;
    String EMAIL_RE = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    Long CITY_BANGALORE_ID = 1L;
    String DATE_FORMATE = "dd/MM/yyyy HH:mm:ss";
}
