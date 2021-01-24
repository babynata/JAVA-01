学习笔记
#第二课笔记
##工具有哪些？
* jps
> 查看当前java进程pid
* jmap
> 查看堆信息：堆设置+堆使用情况
* jstat -gc
> 各分区容量以及使用情况
* jstat -gcutil
> 各分区占当前容量百分比
* jstack -l
> 线程信息
* jcmd
> 综合以上功能
***
##GC有哪些？
* Serial GC
> 串行GC
* Parallel GC
> 并行GC,并行执行GC，提高CPU利用率和缩短GC的暂停时间，在非GC时不会与应用线程并行，提高吞吐量
* CMS
> 并发GC，将GC步骤拆分，GC线程与应用线程并发进行（1:4），进一步缩短每次GC的暂停时间，有效降低业务延迟
* G1
 ***
 #第二课作业
 ## 使用G1 GC启动一个程序，分析JVM情况
 ### 堆大小1g
````
java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -jar gateway-server-0.0.1-SNAPSHOT.jar
````
 ***
 #### 使用jmap -heap
 ```$xslt
Attaching to process ID 16152, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.151-b12

using thread-local object allocation.
Garbage-First (G1) GC with 4 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 40
   MaxHeapFreeRatio         = 70
   MaxHeapSize              = 1073741824 (1024.0MB) //堆最大值
   NewSize                  = 1363144 (1.2999954223632812MB) //xmn=MaxHeapSize/3
   MaxNewSize               = 643825664 (614.0MB) //xmn=MaxHeapSize/3
   OldSize                  = 5452592 (5.1999969482421875MB) //老年代大小，是新生代的两倍，新生代：老年代=1：2,是堆的2/3
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB) //压缩类元信息空间大小
   MaxMetaspaceSize         = 17592186044415 MB //metaspace最大值
   G1HeapRegionSize         = 1048576 (1.0MB) //G1的region大小

Heap Usage:
G1 Heap:
   regions  = 1024
   capacity = 1073741824 (1024.0MB)
   used     = 235995632 (225.06297302246094MB)
   free     = 837746192 (798.9370269775391MB)
   21.9788059592247% used
G1 Young Generation:
Eden Space:
   regions  = 213
   capacity = 387973120 (370.0MB)
   used     = 223346688 (213.0MB)
   free     = 164626432 (157.0MB)
   57.567567567567565% used
Survivor Space:
   regions  = 10
   capacity = 10485760 (10.0MB)
   used     = 10485760 (10.0MB)
   free     = 0 (0.0MB)
   100.0% used
G1 Old Generation:
   regions  = 3
   capacity = 675282944 (644.0MB)
   used     = 2163184 (2.0629730224609375MB)
   free     = 673119760 (641.9370269775391MB)
   0.32033742584797165% used
```
***
#### 使用jstat -gc 1 10
```$xslt
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT   
 0.0   10240.0  0.0   10240.0 378880.0 218112.0  659456.0    2112.5   29820.0 28205.8 3964.0 3631.1      8    0.092   0      0.000    0.092
 0.0   10240.0  0.0   10240.0 378880.0 218112.0  659456.0    2112.5   29820.0 28205.8 3964.0 3631.1      8    0.092   0      0.000    0.092
 0.0   10240.0  0.0   10240.0 378880.0 218112.0  659456.0    2112.5   29820.0 28205.8 3964.0 3631.1      8    0.092   0      0.000    0.092
 0.0   10240.0  0.0   10240.0 378880.0 218112.0  659456.0    2112.5   29820.0 28205.8 3964.0 3631.1      8    0.092   0      0.000    0.092
 0.0   10240.0  0.0   10240.0 378880.0 218112.0  659456.0    2112.5   29820.0 28205.8 3964.0 3631.1      8    0.092   0      0.000    0.092
 0.0   10240.0  0.0   10240.0 378880.0 218112.0  659456.0    2112.5   29820.0 28205.8 3964.0 3631.1      8    0.092   0      0.000    0.092
 0.0   10240.0  0.0   10240.0 378880.0 218112.0  659456.0    2112.5   29820.0 28205.8 3964.0 3631.1      8    0.092   0      0.000    0.092
 0.0   10240.0  0.0   10240.0 378880.0 218112.0  659456.0    2112.5   29820.0 28205.8 3964.0 3631.1      8    0.092   0      0.000    0.092
 0.0   10240.0  0.0   10240.0 378880.0 218112.0  659456.0    2112.5   29820.0 28205.8 3964.0 3631.1      8    0.092   0      0.000    0.092
 0.0   10240.0  0.0   10240.0 378880.0 218112.0  659456.0    2112.5   29820.0 28205.8 3964.0 3631.1      8    0.092   0      0.000    0.092
```
1. S1C:S1区当前容量
2. S1U:S1区的使用量
3. EC:Eden区当前容量
4. EU:Eden区的使用量
5. OC:Old区当前容量
6. OU：Old区的使用量
7. MC:元数据区当前容量
8. MU:元数据区的使用量
9. YGC:年轻代GC次数
10. YGCT：年轻代GC消耗的总时间
11. FGC：老年代GC次数
12. FGCT:老年代GC消耗的总时间
***
> 
***
# 第三课作业
## 使用不同GC进行分析
### Serial GC
#### 512m
```$xsl
执行结束!共生成对象次数:2296120
CommandLine flags: -XX:InitialHeapSize=1073741824 -XX:MaxHeapSize=1073741824 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:-UseAdaptiveSizePolicy -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseSerialGC 
2021-01-22T19:38:57.940-0800: 0.418: [GC (Allocation Failure) 2021-01-22T19:38:57.940-0800: 0.418: [DefNew: 139776K->17471K(157248K), 0.0440671 secs] 139776K->42292K(506816K), 0.0442106 secs] [Times: user=0.02 sys=0.01, real=0.04 secs] 
2021-01-22T19:38:58.010-0800: 0.488: [GC (Allocation Failure) 2021-01-22T19:38:58.010-0800: 0.488: [DefNew: 157247K->17466K(157248K), 0.0762379 secs] 182068K->90713K(506816K), 0.0763347 secs] [Times: user=0.02 sys=0.03, real=0.07 secs] 
2021-01-22T19:38:58.104-0800: 0.582: [GC (Allocation Failure) 2021-01-22T19:38:58.104-0800: 0.582: [DefNew: 157242K->17471K(157248K), 0.0358874 secs] 230489K->135325K(506816K), 0.0359966 secs] [Times: user=0.02 sys=0.01, real=0.04 secs] 
2021-01-22T19:38:58.159-0800: 0.636: [GC (Allocation Failure) 2021-01-22T19:38:58.159-0800: 0.636: [DefNew: 157247K->17471K(157248K), 0.0286932 secs] 275101K->178127K(506816K), 0.0287910 secs] [Times: user=0.01 sys=0.01, real=0.03 secs] 
2021-01-22T19:38:58.204-0800: 0.682: [GC (Allocation Failure) 2021-01-22T19:38:58.204-0800: 0.682: [DefNew: 157247K->17466K(157248K), 0.0281988 secs] 317903K->218183K(506816K), 0.0282840 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2021-01-22T19:38:58.248-0800: 0.725: [GC (Allocation Failure) 2021-01-22T19:38:58.248-0800: 0.726: [DefNew: 157242K->17471K(157248K), 0.0345576 secs] 357959K->261778K(506816K), 0.0346552 secs] [Times: user=0.02 sys=0.01, real=0.04 secs] 
2021-01-22T19:38:58.301-0800: 0.778: [GC (Allocation Failure) 2021-01-22T19:38:58.301-0800: 0.778: [DefNew: 157247K->17470K(157248K), 0.0368251 secs] 401554K->308109K(506816K), 0.0369182 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2021-01-22T19:38:58.359-0800: 0.836: [GC (Allocation Failure) 2021-01-22T19:38:58.359-0800: 0.836: [DefNew: 157246K->17471K(157248K), 0.0331977 secs] 447885K->354238K(506816K), 0.0332981 secs] [Times: user=0.02 sys=0.01, real=0.04 secs] 

```
> 执行到836ms时，YGC执行了8次，平均每
***
### Parallel GC
```$xslt
执行结束!共生成对象次数:8981
CommandLine flags: -XX:InitialHeapSize=1073741824 -XX:MaxHeapSize=1073741824 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:-UseAdaptiveSizePolicy -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 
2021-01-21T00:13:46.024-0800: 0.382: [GC (Allocation Failure) [PSYoungGen: 262144K->43507K(305664K)] 262144K->74984K(1005056K), 0.0315235 secs] [Times: user=0.03 sys=0.03, real=0.03 secs] 
2021-01-21T00:13:46.100-0800: 0.458: [GC (Allocation Failure) [PSYoungGen: 305651K->43512K(305664K)] 337128K->150058K(1005056K), 0.0595575 secs] [Times: user=0.04 sys=0.05, real=0.06 secs] 
2021-01-21T00:13:46.192-0800: 0.550: [GC (Allocation Failure) [PSYoungGen: 305656K->43502K(305664K)] 412202K->213531K(1005056K), 0.0366672 secs] [Times: user=0.04 sys=0.03, real=0.03 secs] 
2021-01-21T00:13:46.259-0800: 0.617: [GC (Allocation Failure) [PSYoungGen: 305646K->43517K(305664K)] 475675K->283717K(1005056K), 0.0369520 secs] [Times: user=0.04 sys=0.04, real=0.04 secs] 
2021-01-21T00:13:46.325-0800: 0.683: [GC (Allocation Failure) [PSYoungGen: 305661K->43508K(305664K)] 545861K->363033K(1005056K), 0.0737971 secs] [Times: user=0.06 sys=0.04, real=0.07 secs] 
2021-01-21T00:13:46.431-0800: 0.789: [GC (Allocation Failure) [PSYoungGen: 305652K->43516K(305664K)] 625177K->440534K(1005056K), 0.0937503 secs] [Times: user=0.04 sys=0.03, real=0.09 secs] 
2021-01-21T00:13:46.555-0800: 0.913: [GC (Allocation Failure) [PSYoungGen: 305660K->43505K(305664K)] 702678K->516266K(1005056K), 0.0529198 secs] [Times: user=0.06 sys=0.04, real=0.05 secs] 
2021-01-21T00:13:46.639-0800: 0.997: [GC (Allocation Failure) [PSYoungGen: 305649K->43519K(305664K)] 778410K->589188K(1005056K), 0.0824367 secs] [Times: user=0.05 sys=0.04, real=0.09 secs] 
2021-01-21T00:13:46.794-0800: 1.152: [GC (Allocation Failure) [PSYoungGen: 305663K->43506K(305664K)] 851332K->649887K(1005056K), 0.0535096 secs] [Times: user=0.07 sys=0.05, real=0.05 secs] 
2021-01-21T00:13:46.848-0800: 1.206: [Full GC (Ergonomics) [PSYoungGen: 43506K->0K(305664K)] [ParOldGen: 606381K->315651K(699392K)] 649887K->315651K(1005056K), [Metaspace: 2691K->2691K(1056768K)], 0.1409517 secs] [Times: user=0.15 sys=0.19, real=0.14 secs]
```
> 一共发生了9次YGC，平均每次YGC消耗0.02s，发生了1次FCG，FGC后young区使用量为0K，old区从606381K减少到315651K，占old区比例86%降低到45%，整个FGC用了0.14s
***
### Concurrent Mark Sweep GC
```$xslt
执行结束!共生成对象次数:9252
CommandLine flags: -XX:InitialHeapSize=1073741824 -XX:MaxHeapSize=1073741824 -XX:MaxNewSize=348966912 -XX:MaxTenuringThreshold=6 -XX:NewSize=348966912 -XX:OldPLABSize=16 -XX:OldSize=697933824 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:-UseAdaptiveSizePolicy -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:+UseParNewGC 
2021-01-21T00:23:03.537-0800: 0.253: [GC (Allocation Failure) 2021-01-21T00:23:03.537-0800: 0.253: [ParNew: 272640K->34048K(306688K), 0.0350139 secs] 272640K->83765K(1014528K), 0.0351436 secs] [Times: user=0.04 sys=0.08, real=0.04 secs] 
2021-01-21T00:23:03.604-0800: 0.320: [GC (Allocation Failure) 2021-01-21T00:23:03.604-0800: 0.320: [ParNew: 306688K->34046K(306688K), 0.0466728 secs] 356405K->164230K(1014528K), 0.0467644 secs] [Times: user=0.05 sys=0.07, real=0.05 secs] 
2021-01-21T00:23:03.687-0800: 0.403: [GC (Allocation Failure) 2021-01-21T00:23:03.687-0800: 0.403: [ParNew: 306686K->34048K(306688K), 0.0614102 secs] 436870K->247215K(1014528K), 0.0614953 secs] [Times: user=0.18 sys=0.03, real=0.06 secs] 
2021-01-21T00:23:03.779-0800: 0.495: [GC (Allocation Failure) 2021-01-21T00:23:03.779-0800: 0.495: [ParNew: 306688K->34048K(306688K), 0.0875911 secs] 519855K->325056K(1014528K), 0.0876864 secs] [Times: user=0.10 sys=0.03, real=0.09 secs] 
2021-01-21T00:23:03.916-0800: 0.632: [GC (Allocation Failure) 2021-01-21T00:23:03.916-0800: 0.632: [ParNew: 306688K->34048K(306688K), 0.0674238 secs] 597696K->408337K(1014528K), 0.0675539 secs] [Times: user=0.16 sys=0.03, real=0.07 secs] 
2021-01-21T00:23:03.985-0800: 0.701: [GC (CMS Initial Mark) [1 CMS-initial-mark: 374289K(707840K)] 421483K(1014528K), 0.0011566 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-01-21T00:23:03.986-0800: 0.702: [CMS-concurrent-mark-start]
2021-01-21T00:23:03.999-0800: 0.715: [CMS-concurrent-mark: 0.013/0.013 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2021-01-21T00:23:03.999-0800: 0.715: [CMS-concurrent-preclean-start]
2021-01-21T00:23:04.001-0800: 0.717: [CMS-concurrent-preclean: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-01-21T00:23:04.001-0800: 0.717: [CMS-concurrent-abortable-preclean-start]
2021-01-21T00:23:04.019-0800: 0.735: [GC (Allocation Failure) 2021-01-21T00:23:04.019-0800: 0.735: [ParNew2021-01-21T00:23:04.103-0800: 0.819: [CMS-concurrent-abortable-preclean: 0.001/0.102 secs] [Times: user=0.16 sys=0.03, real=0.10 secs] 
: 306376K->34048K(306688K), 0.1005273 secs] 680665K->490616K(1014528K), 0.1006361 secs] [Times: user=0.16 sys=0.04, real=0.11 secs] 
2021-01-21T00:23:04.120-0800: 0.836: [GC (CMS Final Remark) [YG occupancy: 40452 K (306688 K)]2021-01-21T00:23:04.120-0800: 0.836: [Rescan (parallel) , 0.0020569 secs]2021-01-21T00:23:04.122-0800: 0.838: [weak refs processing, 0.0000271 secs]2021-01-21T00:23:04.122-0800: 0.838: [class unloading, 0.0004398 secs]2021-01-21T00:23:04.123-0800: 0.839: [scrub symbol table, 0.0004550 secs]2021-01-21T00:23:04.123-0800: 0.839: [scrub string table, 0.0001241 secs][1 CMS-remark: 456568K(707840K)] 497021K(1014528K), 0.0032279 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
```
> 
### G1 GC
```$xslt
执行结束!共生成对象次数:8345
CommandLine flags: -XX:InitialHeapSize=1073741824 -XX:MaxHeapSize=1073741824 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:-UseAdaptiveSizePolicy -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC 
2021-01-21T00:28:56.705-0800: 0.250: [GC pause (G1 Evacuation Pause) (young), 0.0088842 secs]
   [Parallel Time: 7.9 ms, GC Workers: 4]
      [GC Worker Start (ms): Min: 250.0, Avg: 250.1, Max: 250.1, Diff: 0.1]
      [Ext Root Scanning (ms): Min: 0.2, Avg: 0.3, Max: 0.5, Diff: 0.3, Sum: 1.0]
      [Update RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
         [Processed Buffers: Min: 0, Avg: 0.0, Max: 0, Diff: 0, Sum: 0]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Object Copy (ms): Min: 6.6, Avg: 7.0, Max: 7.5, Diff: 0.9, Sum: 28.0]
      [Termination (ms): Min: 0.0, Avg: 0.5, Max: 0.7, Diff: 0.7, Sum: 2.0]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [GC Worker Total (ms): Min: 7.8, Avg: 7.8, Max: 7.9, Diff: 0.1, Sum: 31.2]
      [GC Worker End (ms): Min: 257.8, Avg: 257.9, Max: 257.9, Diff: 0.0]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.0 ms]
   [Other: 0.9 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.7 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.0 ms]
      [Humongous Register: 0.0 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.0 ms]
   [Eden: 51.0M(51.0M)->0.0B(44.0M) Survivors: 0.0B->7168.0K Heap: 65.2M(1024.0M)->24.2M(1024.0M)]
 [Times: user=0.01 sys=0.01, real=0.01 secs] 
2021-01-21T00:28:56.729-0800: 0.274: [GC pause (G1 Evacuation Pause) (young), 0.0074726 secs]
```
> 
## 使用压测工具
### Serial GC
#### 512m
```$xslt
Running 30s test @ http://localhost:8088/api/hello
  8 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    31.34ms   62.02ms 991.79ms   91.30%
    Req/Sec     1.66k     0.97k    8.13k    65.44%
  Latency Distribution
     50%   11.50ms
     75%   24.56ms
     90%   81.39ms
     99%  319.29ms
  392932 requests in 30.11s, 46.91MB read
  Socket errors: connect 0, read 223, write 0, timeout 0
Requests/sec:  13051.25
Transfer/sec:      1.56MB
```
#### 1g
```$xslt
  8 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    37.24ms   74.69ms   1.14s    90.59%
    Req/Sec     1.74k     1.00k    8.35k    63.94%
  Latency Distribution
     50%   11.38ms
     75%   27.04ms
     90%  105.09ms
     99%  377.18ms
  410690 requests in 30.08s, 49.03MB read
  Socket errors: connect 0, read 87, write 24, timeout 0
Requests/sec:  13653.13
Transfer/sec:      1.63MB
```
### Parallel GC
#### 512m
```$xslt
Running 30s test @ http://localhost:8088/api/hello
  8 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     8.27ms    8.37ms 211.42ms   90.73%
    Req/Sec     3.28k     1.25k   10.71k    70.10%
  Latency Distribution
     50%    6.57ms
     75%   10.46ms
     90%   16.12ms
     99%   42.34ms
  780076 requests in 30.08s, 93.13MB read
  Socket errors: connect 0, read 52, write 7, timeout 0
Requests/sec:  25932.55
Transfer/sec:      3.10MB
```
#### 1g
```$xslt
Running 30s test @ http://localhost:8088/api/hello
  8 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.41ms    7.32ms 196.99ms   91.14%
    Req/Sec     3.57k     1.29k   10.00k    69.45%
  Latency Distribution
     50%    6.09ms
     75%    9.39ms
     90%   14.01ms
     99%   36.25ms
  848026 requests in 30.05s, 101.24MB read
Requests/sec:  28223.70
Transfer/sec:      3.37MB
```
### CMS GC
#### 512m
```$xslt
Running 30s test @ http://localhost:8088/api/hello
  8 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     8.29ms    8.33ms 132.31ms   91.15%
    Req/Sec     3.22k     1.18k   12.66k    73.77%
  Latency Distribution
     50%    6.68ms
     75%   10.52ms
     90%   15.75ms
     99%   42.18ms
  765484 requests in 30.08s, 91.39MB read
  Socket errors: connect 0, read 17, write 0, timeout 0
Requests/sec:  25450.81
Transfer/sec:      3.04MB
```
#### 1g
```$xslt
Running 30s test @ http://localhost:8088/api/hello
  8 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    16.03ms   64.54ms 811.40ms   97.75%
    Req/Sec     3.53k     1.22k   10.79k    72.20%
  Latency Distribution
     50%    6.20ms
     75%    9.79ms
     90%   15.90ms
     99%  416.02ms
  823299 requests in 30.09s, 98.29MB read
  Socket errors: connect 0, read 20, write 0, timeout 0
Requests/sec:  27363.23
Transfer/sec:      3.27MB
```
### G1 GC
#### 512m
```$xslt
Running 30s test @ http://localhost:8088/api/hello
  8 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.78ms    7.21ms 141.85ms   88.93%
    Req/Sec     3.34k     1.00k   10.34k    73.04%
  Latency Distribution
     50%    6.68ms
     75%    9.95ms
     90%   14.26ms
     99%   33.79ms
  795396 requests in 30.08s, 94.96MB read
Requests/sec:  26438.45
Transfer/sec:      3.16MB
```
#### 1g
```$xslt
Running 5m test @ http://localhost:8088/api/hello
  8 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    11.38ms   25.05ms 993.81ms   95.20%
    Req/Sec     3.22k     1.31k   16.04k    71.05%
  Latency Distribution
     50%    6.48ms
     75%   10.58ms
     90%   19.64ms
     99%  110.63ms
  7632028 requests in 5.00m, 0.89GB read
  Socket errors: connect 0, read 219, write 6, timeout 0
Requests/sec:  25431.17
Transfer/sec:      3.04MB
```
#### 2g
```$xslt
Running 5m test @ http://localhost:8088/api/hello
  8 threads and 200 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     8.92ms   19.75ms 538.56ms   96.14%
    Req/Sec     3.61k     1.14k   17.76k    74.37%
  Latency Distribution
     50%    6.05ms
     75%    9.08ms
     90%   14.58ms
     99%   58.88ms
  8547706 requests in 5.00m, 1.00GB read
  Socket errors: connect 0, read 21, write 5, timeout 0
Requests/sec:  28482.63
Transfer/sec:      3.40MB
```
# 总结
## G1




 
 
 
