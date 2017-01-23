package com.wmp.services.time;

import org.jetbrains.annotations.Nullable;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * PackageName com.wmp.services.time
 * Created by mhafidi on 22/01/2017.
 */
public class DateUtil
{
  private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  @PostConstruct
  public void initDateFormatter()
  {
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  public static String getStartDate()
  {

    return Calendar.getInstance().getTime().toString();
  }

  public static String getEndDate()
  {
    String startDate = getDateAsString(new Date());
    return startDate;
  }

  @Nullable
  private static String getDateAsString(Date date)
  {
    try
    {
      return sdf.format(date);
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
