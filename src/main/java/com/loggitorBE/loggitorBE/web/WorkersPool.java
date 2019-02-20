package com.loggitorBE.loggitorBE.web;

public class WorkersPool {

//	private Worker[] pool;
//	private DefinedEventList job;
//	private int numOfWorkers;
//	private int activeWorkers;
//	
//	public WorkersPool(int size) {
//		super();
//		pool = new Worker[size];
//		this.numOfWorkers = size;
//		this.activeWorkers = 0;
//		
//		// workers init
//		this.initPool();
//		
//	}
//	
//	
//	
//	
//	
//	
//
//	
//	public void setJob(DefinedEventList job) {
//		this.job = job;
//	}
//
//
//
//
//
//
//
//
//	public void startWork()
//	{
//		if(this.job == null)
//		{
//			System.out.println("There is no job to execute");
//			return;
//		}
//		
//		
//		this.activeWorkers = (int)job.getSize() / 1;
//		
//		if(this.activeWorkers > this.numOfWorkers)
//			this.activeWorkers = this.numOfWorkers;
//		
//		System.out.println("Checking Events is Started");
//		
//		// notify workers
//		for(int i=0; i < this.activeWorkers; i++)
//		{
//			this.pool[i].notify();
//		}
//		
//		// make them work
//		for(int i=0; i < this.activeWorkers; i++)
//		{
//			this.pool[i].work(job);
//		}
//
//		// wait them to finish
//		for(int i=0; i < this.activeWorkers; i++)
//		{
//			try {
//				this.pool[i].join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		
//		// make them wait
//		for(int i=0; i < this.activeWorkers; i++)
//		{
//			try {
//				this.pool[i].wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		this.job = null;
//		this.activeWorkers = 0;
//		
//		System.out.println("Checking Events is Ended");
//		
//	}
//	
//	
//	
//	
//	public void shutdown()
//	{
//		// notify workers
//		for(int i=0; i < this.numOfWorkers; i++)
//		{
//			this.pool[i] = null;
//		}
//	}
//	
//	
//	
//	public void initPool()
//	{
//		// notify workers
//		for(int i=0; i < this.numOfWorkers; i++)
//		{
//			if(this.pool[i] == null) {
//				pool[i] = new Worker(i);
//			
//				// make it wait
//				try {
//					pool[i].wait();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
	
}
