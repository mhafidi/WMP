package com.wmp.core.threadManagement;

/**
 * PackageName com.wmp.core
 * Created by mhafidi on 22/01/2017.
 * Description: this class will be extended by all the other actions of the project, so they can work concurrently
 */
public class WorkerThread extends Thread
{
  protected Integer threadId;
  protected String description;
  protected volatile boolean isRunning = false;

  public WorkerThread(Integer aInThreadId)
  {
    threadId = aInThreadId;
    new Thread();
  }

  public Integer getThreadId()
  {
    return threadId;
  }
  public boolean isWorkerThreadIdEqual(Integer aInthreadId)
  {
    return aInthreadId==threadId;
  }
  @Override
  public void run()
  {
  }

}
