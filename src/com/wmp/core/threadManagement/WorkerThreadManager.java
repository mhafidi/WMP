package com.wmp.core.threadManagement;

import java.util.ArrayDeque;
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
  final static Integer CAPACITY= 20;
  private static WorkerThreadManager instance = null;

  private Queue<WorkerThread> workerThreadQueue;
  private Queue<WorkerThread> threadsInHold;
  private WorkerInitializer workerInitializer;

  //mainThreadWorker will be referenced with id = "0"
  private WorkerThreadManager()
  {
    workerThreadQueue = new ArrayDeque<>();
    threadsInHold = new ArrayDeque<WorkerThread>();
    workerThreadQueue.add(workerInitializer.getInstance());

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

  public Queue<WorkerThread> getWorkerThreadQueue()
  {
    return workerThreadQueue;
  }

  public void setWorkerThreadHashMap(Queue<WorkerThread> aInworkerThreadQueue)
  {
    this.workerThreadQueue = aInworkerThreadQueue;
  }

  public boolean isAnyThreadWorking()
  {
    return workerThreadQueue.size()>1;

  }

  public Queue<WorkerThread> getThreadsInHold()
  {
    return threadsInHold;
  }

  public void setThreadsInHold(Queue<WorkerThread> threadsInHold)
  {
    this.threadsInHold = threadsInHold;
  }
  public boolean isAnyThreadOnHold()
  {
    return threadsInHold.size()>0;
  }



}
