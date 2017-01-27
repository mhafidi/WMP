package com.wmp;

import com.wmp.core.threadManagement.WorkerInitializer;

public class Main
{


  public static void main(String[] args)
  {

    WorkerInitializer.getInstance().start();

  }
}
