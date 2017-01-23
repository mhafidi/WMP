package com.wmp.core;

import java.util.HashMap;
import java.util.Queue;

/**
 * PackageName com.wmp.core
 * Created by mhafidi on 22/01/2017.
 *
 * Description: the WorkerThreadManager is singleton which contains the mainThreadWorkerHandler
 */
public class WorkerThreadManager
{
  private static WorkerThreadManager instance = null;
  private HashMap<Integer,WorkerThread> workerThreadHashMap;
  private WorkerThread mainThreadHandler;
  private Queue<WorkerThread> threadsInHold;

  private WorkerThreadManager()
  {

  }


  public static WorkerThreadManager getInstance()
  {
    if(instance==null)
    {
      return new WorkerThreadManager();
    }
    else
      return instance;
  }

}
