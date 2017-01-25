package com.wmp.core;

/**
 * PackageName com.wmp.core
 * Created by mhafidi on 22/01/2017.
 * Description: this class will be extended by all the other actions of the project, so they can work concurrently
 */
public class WorkerThread extends Thread
{
  protected Integer threadId;
  protected String description;

  public WorkerThread(Integer aInThreadId)
  {
    threadId = aInThreadId;
    new Thread();
  }


}
