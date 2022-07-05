package com.pei.learn.utils.shortUrl;

/**
 * 如何将一个长URL转换为一个短URL？
 * https://blog.csdn.net/xlgen157387/article/details/80026452
 *
 * Twitter的SnowFlake算法,使用SnowFlake算法生成一个整数，然后转化为62进制变成一个短地址URL
 * @author beyond
 * @author xuliugen
 * @date 2018/04/23
 */
public class SnowFlakeShortUrl {

    /**
     * 起始的时间戳
     */
    private final static long START_TIMESTAMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12;   //序列号占用的位数
    private final static long MACHINE_BIT = 5;     //机器标识占用的位数
    private final static long DATA_CENTER_BIT = 5; //数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    private long dataCenterId;  //数据中心
    private long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastTimeStamp = -1L;  //上一次时间戳

    /**
     * 根据指定的数据中心ID和机器标志ID生成指定的序列号
     * @param dataCenterId 数据中心ID
     * @param machineId    机器标志ID
     */
    public SnowFlakeShortUrl(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("DtaCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0！");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("MachineId can't be greater than MAX_MACHINE_NUM or less than 0！");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     * @return
     */
    public synchronized long nextId() {
        long currTimeStamp = getNewTimeStamp();
        if (currTimeStamp < lastTimeStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currTimeStamp == lastTimeStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currTimeStamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastTimeStamp = currTimeStamp;

        return (currTimeStamp - START_TIMESTAMP) << TIMESTAMP_LEFT //时间戳部分
                | dataCenterId << DATA_CENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewTimeStamp();
        while (mill <= lastTimeStamp) {
            mill = getNewTimeStamp();
        }
        return mill;
    }

    private long getNewTimeStamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowFlakeShortUrl snowFlake = new SnowFlakeShortUrl(2, 3);

        for (int i = 0; i < (1 << 4); i++) {
            //10进制
            Long id = snowFlake.nextId();
            //62进制
            String convertedNumStr = NumericConvertUtils.toOtherNumberSystem(id, 62);

            //10进制转化为62进制
            System.out.println("10进制：" + id + "  62进制:" + convertedNumStr);

            //TODO 执行具体的存储操作，可以存放在Redis等中

            //62进制转化为10进制
            System.out.println("62进制：" + convertedNumStr + "  10进制:" + NumericConvertUtils.toDecimalNumber(convertedNumStr, 62));
            System.out.println();
        }
    }
}
////生成结果：
//10进制：185784275776581632  62进制:dITqmhW2He
//62进制：dITqmhW2He  10进制:185784275776581632
//
//10进制：185784284689477632  62进制:dITqw17E6k
//62进制：dITqw17E6k  10进制:185784284689477632
//
//10进制：185784284689477633  62进制:dITqw17E6l
//62进制：dITqw17E6l  10进制:185784284689477633
//
//10进制：185784284689477634  62进制:dITqw17E6m
//62进制：dITqw17E6m  10进制:185784284689477634
//
//10进制：185784284689477635  62进制:dITqw17E6n
//62进制：dITqw17E6n  10进制:185784284689477635
//
//10进制：185784284689477636  62进制:dITqw17E6o
//62进制：dITqw17E6o  10进制:185784284689477636
//
//10进制：185784284689477637  62进制:dITqw17E6p
//62进制：dITqw17E6p  10进制:185784284689477637
//
//10进制：185784284693671936  62进制:dITqw1pfeo
//62进制：dITqw1pfeo  10进制:185784284693671936
//
//10进制：185784284693671937  62进制:dITqw1pfep
//62进制：dITqw1pfep  10进制:185784284693671937
//
//10进制：185784284693671938  62进制:dITqw1pfeq
//62进制：dITqw1pfeq  10进制:185784284693671938
//
//10进制：185784284693671939  62进制:dITqw1pfer
//62进制：dITqw1pfer  10进制:185784284693671939
//
//10进制：185784284693671940  62进制:dITqw1pfes
//62进制：dITqw1pfes  10进制:185784284693671940
//
//10进制：185784284693671941  62进制:dITqw1pfet
//62进制：dITqw1pfet  10进制:185784284693671941
//
//10进制：185784284693671942  62进制:dITqw1pfeu
//62进制：dITqw1pfeu  10进制:185784284693671942
//
//10进制：185784284693671943  62进制:dITqw1pfev
//62进制：dITqw1pfev  10进制:185784284693671943
//
//10进制：185784284693671944  62进制:dITqw1pfew
//62进制：dITqw1pfew  10进制:185784284693671944
