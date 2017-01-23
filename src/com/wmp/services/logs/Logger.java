package com.wmp.services.logs;


import com.wmp.services.time.DateUtil;
import org.jetbrains.annotations.Contract;

import java.io.*;

/**
 * PackageName com.wmp.services
 * Created by mhafidi on 22/01/2017.
 */
public class Logger extends LogsLevels
{

  private static Logger instance = null;
  private Logger()
  {
    super();
  }
  static public Logger getInstance()
  {
    if(instance==null)
      return new Logger();
    else
      return instance;
  }


  //FormatLog
  @Contract(pure = true)
  private String formatLog(String aInTag, String aInLocalCode, String aInLogDetails, String aInDate)
  {
    String message = "";
    if (aInLocalCode == null || aInLogDetails == null)
    {
      message = (aInLocalCode == null) ? "" : (aInLocalCode + ":");
      message += (aInLogDetails == null) ? GENERIC_ERROR_MSG : aInLogDetails;
    } else
    {
      message = aInTag + "[" + aInDate + "]:" + aInLocalCode + ":" + aInLogDetails;
    }

    return message;

  }

  public void logError(String aInLocalCode, String aInLogDetails,OutputStream aInOutputStream)
  {
    String messageToPrint = formatLog(LOG_ERROR_TAG,aInLocalCode,aInLogDetails,DateUtil.getStartDate());
    messageToPrint = messageToPrint+"\n";
    if(aInOutputStream==null)
    {
      aInOutputStream = System.out;
    }

    try
    {
      aInOutputStream.write(messageToPrint.getBytes());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }


  }

  public void logInfo(String aInLocalCode, String aInLogDetails,OutputStream aInOutputStream)
  {
    String messageToPrint = formatLog(LOG_INFO_TAG,aInLocalCode,aInLogDetails,DateUtil.getStartDate());
    messageToPrint = messageToPrint+"\n";
    if(aInOutputStream==null)
    {
      aInOutputStream = System.out;
    }

    try
    {
      aInOutputStream.write(messageToPrint.getBytes());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }
  public void logWarning(String aInLocalCode, String aInLogDetails,OutputStream aInOutputStream)
  {
    String messageToPrint = formatLog(LOG_WARNING_TAG,aInLocalCode,aInLogDetails,DateUtil.getStartDate());

    messageToPrint = messageToPrint+"\n";

    if(aInOutputStream==null)
    {
      aInOutputStream = System.out;
    }

    try
    {
      aInOutputStream.write(messageToPrint.getBytes());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  public void logTestFailed(String aInLocalCode, String aInLogDetails)
  {
    System.out.println(formatLog(LOG_TEST_FAILED_TAG,aInLocalCode, aInLogDetails, DateUtil.getStartDate()));
  }

  public void logTestSucceeded(String aInLocalCode, String aInLogDetails)
  {
    System.out.printf(formatLog(LOG_TEST_SUCCEEDED_TAG,aInLocalCode, aInLogDetails, DateUtil.getStartDate()));
  }


}
