* 暂用3种方法实现主线程调用子线程并获取返回值
	1. ThreadFuture: 通过new Thread(Runnable task)创建线程，传入Runnable实现类FutureTask，并获取执行的返回值
	2. ThreadPoolCallable：创建线程池，通过submit(Callable task)的方式submit
	3. ThreadPoolFuture：创建线程池，通过submit(Runnable task)的方式submit
* 列出多线程知识点脑图
	 