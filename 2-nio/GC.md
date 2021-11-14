java8 -Xmx1g -Xms1g下运行GCLogAnalysis进行测试：

| |并行GC | 串行GC | CMS | G1 |
| :-----| :-----:| :----: | :----:|:----:|
| youngGC次数 | 10 | 8 | 8 | 41|
| fullGC次数 | 1 | 0 | 1| 0|
| 总GC时间(ms) | 470 | 550 | 510 | 278|
| 最大GC时间(ms) | 60 | 110 | 70| 34.4|

* G1 gc的次数最多，GC时间控制的很好
* 并行GC的GC时间优秀程度仅次于G1
* 串行GC的GC次数最少，但是停顿时间最长

***

#### 串行GC
*  Serial+Serial Old收集器，新生代用复制算法，老年代用标记-整理算法，垃圾收集时需要STW
*  依然是HotSpot client模式下默认的新生代收集器，适合单核处理器，简单高效，避免了GC线程交互的开销

#### 并行GC
* ParNew+Serial Old收集器，串行GC的多线程版，新生代GC时多线程并行，其余和串行GC一致（算法一致，也需要STW）
* 在多核处理器上，比串行GC更高效

#### CMS
* ParNew+CMS收集器（用-XX:+UseConcMarkSweepGC参数后可以看到GC日志里young区显示ParNew）,采用标记-清除算法
* 关注停顿时间，对老年代的GC更加细化，分成四个步骤，尽可能地做到并发
	1. 初始标记 initial mark (STW)
	2. 并发标记 concurrent mark
	3. 重新标记 remark  (STW)
	4. 并发清除 concurrent sweep
* 适用于B/S系统的服务器端，关注服务响应速度，提升用户体验（本机测试GCLogAnalysis的场景下表现没有并行GC好）

#### G1
* 开创性地把堆内存分成region，不再区分年轻代、老年代，那块region里垃圾多就先回收
* 借鉴CMS的思路，把回收过程分成四个步骤
	1. 初始标记 initial mark (STW)
	2. 并发标记 concurrent mark
	3. 最终标记 final remark  (STW)
	4. 筛选回收 live data count and evacuation （STW）
* 化整为零的思路，适合于大内存的机器，减少GC时间
  
