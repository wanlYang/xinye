package com.topshow.constant;

public interface TopShowConstant {
    public static final boolean DEVELOPMENT_MODEL = false;
    public static final boolean NOT_DEVELOPMENT_MODEL = false;
    public static final Integer USER_STATE_NOTACTIVE = Integer.valueOf(0);

    public static final Integer USER_STATE_ACTIVE = Integer.valueOf(1);

    public static final String SAVE_NAMEPASS = "TRUE";

    public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String ROLE_USER = "ROLE_USER";

    public static final Integer IS_HOT_PRODUCT = Integer.valueOf(1);

    public static final Integer IS_NOT_HOT_PRODUCT = Integer.valueOf(0);

    public static final Integer IS_UP = Integer.valueOf(1);

    public static final Integer IS_DOWN = Integer.valueOf(0);

    public static final Integer ORDER_NOTPAY = Integer.valueOf(0);

    public static final Integer ORDER_PAY = Integer.valueOf(1);

    public static final Integer ORDER_TO_BE_SHIPPED = Integer.valueOf(2);

    public static final Integer ORDER_SHIPPED = Integer.valueOf(3);

    public static final Integer ORDER_SUCCESS = Integer.valueOf(4);

    public static final Integer ADMIN_ABNORMAL = Integer.valueOf(0);

    public static final Integer ADMIN_NORMAL = Integer.valueOf(1);

    public static final Integer TO_EXAMINE = Integer.valueOf(0);

    public static final Integer TO_EXAMINE_SUCCESS = Integer.valueOf(1);

    public static final Integer IS_DELETE = Integer.valueOf(2);

    public static final Integer IS_TOP = Integer.valueOf(1);

    public static final Integer NOT_TOP = Integer.valueOf(0);

    public static final String USER_COOKIE = "USER.COOKIE";

    public static final String USER_SESSION = "USER_SESSION";

    public static final String ADMIN_SESSION = "ADMIN_SESSION";

    public static final String ANONYMOUS = "匿名";

    public static final String OTHER = "其他";

    public static final String NORMAL = "正常";
    
    public static final String ABNORMAL = "异常";

    public static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    public static final String X_REQUEST_WITH = "X-Requested-With";

    public static final String XML_HTTP_REQUEST = "XMLHttpRequest";

    public static final String REQUEST_METHOD_POST = "POST";

    public static final String REQUEST_METHOD_GET = "GET";

    public static final String UTF_8 = "UTF-8";

    public static final String CONTENT_TYPE_APP_JSON_UTF_8 = "application/json;charset=utf-8";

    public static final String CONTENT_TYPR_TEXT_HTML_UTF_8 = "text/html;charset=utf-8";

    public static final int MAX_TOP_LEVEL_NAV = 8;

    public static final String DEFAULT_ROLE_NAME = "DEFAULT_ROLE_NAME";

    public static final String DEFAULT_DESCRITPION = "默认描述";

    public static final String REQUEST = "request";
    
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";
    
    public static final String QINIU_IMG = "http://video.jytopshow.com/";
    public static final String QINIU_VIDEO = "http://video.jytopshow.com/";
}
