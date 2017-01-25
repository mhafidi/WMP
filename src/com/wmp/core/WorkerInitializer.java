package com.wmp.core;

import com.wmp.services.logs.Logger;

import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.InterruptibleChannel;
import java.nio.channels.Selector;

/**
 * PackageName com.wmp.core
 * Created by mhafidi on 23/01/2017.
 *
 * Description:
 *            - WorkerInitializer is the main thread which always runs while  some conditions are satisfied
 *            - The conditions depends on the workerThreadManager and the standby timer "twice 15 seconds" while no thread
 *            is working.
 */
public class WorkerInitializer extends WorkerThread
{
  private final String CLASS_NAME = WorkerInitializer.class.getName();


  private static WorkerInitializer  workerInitializer = null;
  private volatile long TotalUsedMemory = 0;
  private volatile boolean isRunning = false;
  private volatile long limitTimeToStop = 0;

  private WorkerInitializer(Integer aInThreadId)
  {
    super(aInThreadId);
  }

  static public WorkerInitializer getInstance()
  {
    if(workerInitializer!=null)
      return workerInitializer;
    else
      return  new WorkerInitializer(0);
  }


  public boolean isRunning()
  {
    return isRunning;
  }

  public void setRunning(boolean running)
  {
    isRunning = running;
  }





  @Override
  public void run()
  {

    Logger.getInstance().logInfo(CLASS_NAME,"Starting WMP setting Environment's Parameters",System.out);
    while(isRunning)
    {

      try
      {
        Thread.sleep(15000);
        TotalUsedMemory = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        Logger.getInstance().logInfo(CLASS_NAME,"Used Memory is= "+TotalUsedMemory,System.out);
        limitTimeToStop++;
        if(limitTimeToStop>=2 && !WorkerThreadManager.getInstance().isThreadWorking())
        {
          isRunning = false;
        }
        else if(WorkerThreadManager.getInstance().isThreadWorking())
        {
          limitTimeToStop--;
        }
        else if(WorkerThreadManager.getInstance().areThreadsInHold())
        {

          limitTimeToStop --;

        }

      }
      catch (InterruptedException e)
      {
        isRunning = false;
        Logger.getInstance().logError(CLASS_NAME,"Exception Occurred and stopped the main Thread",System.out);
      }

    }

  }


  void terminate()
  {
    isRunning = false;
  }

  @Override
  public void start()
  {
    isRunning = true;
    Logger.getInstance().logInfo(CLASS_NAME,"WorkerInitializer Started",System.out);
    super.start();
  }

  /**
   * Interrupts this thread.
   * <p>
   * <p> Unless the current thread is interrupting itself, which is
   * always permitted, the {@link #checkAccess() checkAccess} method
   * of this thread is invoked, which may cause a {@link
   * SecurityException} to be thrown.
   * <p>
   * <p> If this thread is blocked in an invocation of the {@link
   * Object#wait() wait()}, {@link Object#wait(long) wait(long)}, or {@link
   * Object#wait(long, int) wait(long, int)} methods of the {@link Object}
   * class, or of the {@link #join()}, {@link #join(long)}, {@link
   * #join(long, int)}, {@link #sleep(long)}, or {@link #sleep(long, int)},
   * methods of this class, then its interrupt status will be cleared and it
   * will receive an {@link InterruptedException}.
   * <p>
   * <p> If this thread is blocked in an I/O operation upon an {@link
   * InterruptibleChannel InterruptibleChannel}
   * then the channel will be closed, the thread's interrupt
   * status will be set, and the thread will receive a {@link
   * ClosedByInterruptException}.
   * <p>
   * <p> If this thread is blocked in a {@link Selector}
   * then the thread's interrupt status will be set and it will return
   * immediately from the selection operation, possibly with a non-zero
   * value, just as if the selector's {@link
   * Selector#wakeup wakeup} method were invoked.
   * <p>
   * <p> If none of the previous conditions hold then this thread's interrupt
   * status will be set. </p>
   * <p>
   * <p> Interrupting a thread that is not alive need not have any effect.
   *
   * @throws SecurityException if the current thread cannot modify this thread
   * @revised 6.0
   * @spec JSR-51
   */
  @Override
  public void interrupt()
  {
    isRunning= false;
    Logger.getInstance().logInfo(CLASS_NAME,"WorkerInitializer has been gracefully stopped",System.out);
    super.interrupt();
  }
}
