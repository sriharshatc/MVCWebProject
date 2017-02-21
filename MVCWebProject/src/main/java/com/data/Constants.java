package com.data;

public interface Constants {

	String ALERT = "POPUP_ALERT_MSG";
	
	String CON_FNAME = "PARAM_FIRSTNAME";
	String CON_LNAME = "PARAM_LASTNAME";
	String CON_UNAME = "PARAM_USERNAME";
	String CON_USER = "PARAM_USER_INFO";

	String CON_PASS = "PARAM_PASSWORD";
	String CON_CNFM = "PARAM_CONFIRM";

	int SUCCESS = 0;

	int ERR_FNAME = 1;
	int ERR_LNAME = 2;
	int ERR_UNAME = 3;
	int ERR_PASS = 4;

	int EMPTY_FIELDS = 5;
	int PASS_MISMATCH = 6;

	int DB_FAILED = 7;
	int DB_UNAME = 8;
	int DB_EXCP = 9;
}