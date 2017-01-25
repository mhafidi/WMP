package com.wmp.services.logs;

/**
 * PackageName com.wmp.services.logs
 * Created by mhafidi on 22/01/2017.
 */
public abstract class LogsLevels
{
  protected final static String LOG_INFO_TAG = "[LogInfo]";
  protected final static String LOG_ERROR_TAG = "[LogError]";
  protected final static String LOG_WARNING_TAG = "[LogWarning]";
  protected final static String LOG_TEST_FAILED_TAG = "[TEST FAILED]";
  protected final static String LOG_TEST_SUCCEEDED_TAG = "[TEST SUCCEEDED]";
  protected final static String GENERIC_ERROR_MSG = "An Unknown Error Occurred";
  public LogsLevels()
  {}
}
