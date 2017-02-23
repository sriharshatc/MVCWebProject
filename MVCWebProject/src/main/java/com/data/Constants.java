package com.data;

// Project Constants
public interface Constants {

	// Pop-Up Alert Messages
	String ALERT = "POPUP_ALERT_MSG";
	
	// User Details
	String CON_FNAME = "PARAM_FIRSTNAME";
	String CON_LNAME = "PARAM_LASTNAME";
	String CON_UNAME = "PARAM_USERNAME";

	// Password
	String CON_PASS = "PARAM_PASSWORD";
	String CON_CNFM = "PARAM_CONFIRM";

	// Session Details
	String CON_USER = "PARAM_USER";
	String CON_PLIST = "PARAM_INVENTORY";
	String CON_SCART = "PARAM_SCART";
	String CON_TXNS = "PARAM_TRANSACTIONS";

	// Product Details
	String CON_PID = "PARAM_PRODUCTID";
	String CON_PNAME = "PARAM_PRODNAME";
	String CON_PRICE = "PARAM_PRODPRICE";
	String CON_QTY = "PARAM_QUANTITY";
	
	// Operation Codes
	int SUCCESS = 0;

	// Error Codes
	int ERR_FNAME = 1;
	int ERR_LNAME = 2;
	int ERR_UNAME = 3;
	int ERR_PASS = 4;
	int ERR_PID = 5;
	int ERR_PNAME = 6;
	int ERR_PRICE = 7;

	// User Input Codes
	int EMPTY_FIELDS = 8;
	int PASS_MISMATCH = 9;

	// Database Codes
	int DB_FAILED = 10;
	int DB_EXISTS = 11;
	int DB_EXCP = 12;
}