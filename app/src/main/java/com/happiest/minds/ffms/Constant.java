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

    int NEW = 103;
    int IN_PROGRESS = 104;
    int REJECTED = 105;
    int COMPLETED = 106;
    int SEARCH = 107;

    int BASIC_INFO_UPDATE = 1;
    int DEMO = 2;
    int ORDER = 3;

    int ACTIVITY_COMPLETED = 101;
    int ACTIVITY_NOT_DONE = 102;
    Integer ACTIVITY_REJECTED = 107;

    Long TICKET_TYPE_SALES = 1L;
    Long TICKET_TYPE_SR = 2L;

    String CUSTOMER_INTERESTED_YES = "Yes";
    String CUSTOMER_INTERESTED_NO = "No";


}
