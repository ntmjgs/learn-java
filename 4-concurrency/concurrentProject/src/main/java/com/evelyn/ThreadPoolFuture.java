package com.evelyn;

import java.util.concurrent.*;

public class ThreadPoolFuture {
    public static void main(String[] args) {

        long start=System.currentTimeMillis();
        int result = 0;

        // 创建一个线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            public Integer call() throws Exception {
                return sum();
            }
        });
        //submit()的参数是包装了callable的futuretask
        executor.submit(task);
        executor.shutdown();

        try {
            System.out.println("异步计算结果为：" + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
