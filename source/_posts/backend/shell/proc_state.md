
# ／proc/state
[TOC]

## 介绍
/proc文件系统是一个伪文件系统，它只存在内存当中，而不占用外存空间。它以文件系统的方式为内核与进程提供通信的接口。用户和应用程序可以通过/proc得到系统的信息，并可以改变内核的某些参数。由于系统的信息，如进程，是动态改变的，所以用户或应用程序读取/proc目录中的文件时，proc文件系统是动态从系统内核读出所需信息并提交的。

```
#cat /proc/stat   
cpu  15579 99 13680 698457 10939 40 651 0 0
cpu0 1669 7 1974 338065 1396 5 9 0 0  
cpu1 13910 91 11705 360391 9542 35 641 0 0  
intr 957831 163 8 0 1 1 0 5 0 1 0 0 0 101 0 0 3582 0 37804 3657 22410 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
ctxt 501479  
btime 1363495431  
processes 40101  
procs_running 1  
procs_blocked 0  
softirq 1396087 0 693403 12972 15932 35928 3 44577 479 592793   
```
第一行的数值表示的是CPU总的使用情况，所以我们只要用第一行的数字计算就可以了。下表解析第一行各数值的含义：

参数          解析（单位：jiffies）

(jiffies是内核中的一个全局变量，用来记录自系统启动一来产生的节拍数，在linux中，一个节拍大致可理解为操作系统进程调度的最小时间片，不同linux内核可能值有不同，通常在1ms到10ms之间)

user ( 15579 )    从系统启动开始累计到当前时刻，处于**用户态**的运行时间，不包含 nice值为负进程。

nice (99)      从系统启动开始累计到当前时刻，**nice值为负**的进程所占用的CPU时间

system (13680)  从系统启动开始累计到当前时刻，处于**核心态**的运行时间

***idle*** (698457)   从系统启动开始累计到当前时刻，除IO等待时间以外的其它**等待时间**

iowait (10939) 从系统启动开始累计到当前时刻，**IO等待时间**(since 2.5.41)

irq (40)           从系统启动开始累计到当前时刻，**硬中断**时间(since 2.6.0-test4)

softirq (651)      从系统启动开始累计到当前时刻，**软中断**时间(since 2.6.0-test4)

stealstolen(0)     which is the time spent in other operating systems when running in a virtualized environment(since 2.6.11)

guest(0)        which is the time spent running a virtual  CPU  for  guest operating systems under the control of the Linux kernel(since 2.6.24)

 结论：总的cpu时间totalCpuTime = user + nice + system + idle + iowait + irq + softirq + stealstolen +guest

s2 - s1得到这个时间间隔内的所有时间片，即totalCpuTime = s2 - s1 ;
idle=第二次的idle - 第一次的idle
pcpu =100* (total-idle)/total

used1=user + nice + system + idle + iowait + irq + softirq


grep -w  精确匹配
grep -e 显示文件中符合条件的字符 



