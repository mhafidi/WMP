package com.wmp.core;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

/**
 * PackageName com.wmp.core
 * Created by mhafidi on 22/01/2017.
 *
 * Description:
 *            -the WorkerThreadManager is singleton which contains a map of working threads and in hold threads
 *            -it updates the state of working and in hold threads
 *
 */
public class WorkerThreadManager
{
  final static Integer CPACITY= 20;
  private static WorkerThreadManager instance = null;
  private HashMap<Integer,WorkerThread> workerThreadHashMap;
  private WorkerInitializer workerInitializer;
  private Queue<WorkerThread> threadsInHold;


  //mainThreadWorker will be referenced with id = "0"
  private WorkerThreadManager()
  {
    workerThreadHashMap = new HashMap<>();
    threadsInHold = new ArrayDeque<WorkerThread>();
    workerThreadHashMap.put(0,workerInitializer.getInstance());

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

  public HashMap<Integer, WorkerThread> getWorkerThreadHashMap()
  {
    return workerThreadHashMap;
  }

  public void setWorkerThreadHashMap(HashMap<Integer, WorkerThread> workerThreadHashMap)
  {
    this.workerThreadHashMap = workerThreadHashMap;
  }

  public boolean isThreadWorking()
  {
    return workerThreadHashMap.size()>1;

  }

  public Queue<WorkerThread> getThreadsInHold()
  {
    return threadsInHold;
  }

  public void setThreadsInHold(Queue<WorkerThread> threadsInHold)
  {
    this.threadsInHold = threadsInHold;
  }
  public boolean areThreadsInHold()
  {
    return threadsInHold.size()>0;
  }


}
