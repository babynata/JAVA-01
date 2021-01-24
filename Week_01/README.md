学习笔记


1.工具有哪些？
jps //查看当前java进程pid
jmap //查看堆信息：堆设置+堆使用情况
jstat -gc //各分区容量以及使用情况
jstat -gcutil //各分区占当前容量百分比
jstack -l //线程信息
jcmd //综合以上功能

2.GC有哪些？有什么特点？
 2.1 Serial GC 串行GC
 2.2 Parallel GC 并行GC,并行执行GC，提高CPU利用率和缩短GC的暂停时间，在非GC时不会与应用线程并行，提高吞吐量
 2.3 CMS 并发GC，将GC步骤拆分，GC线程与应用线程并发进行（1:4），进一步缩短每次GC的暂停时间，有效降低业务延迟
 2.4 G1 
