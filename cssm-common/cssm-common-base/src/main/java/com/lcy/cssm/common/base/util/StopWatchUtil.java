package com.lcy.cssm.common.base.util;

import org.springframework.util.StopWatch;

/**
 * 记录一个请求执行的时间
 * @author 王培
 * @create 2016-12-01 18:15
 */
public class StopWatchUtil {
    static ThreadLocal<StopWatch> stopWatchThreadLocal = new ThreadLocal<StopWatch>();
    public static void startWatch(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        stopWatchThreadLocal.set(stopWatch);
    }

    public static long getTotalTime(){
        StopWatch stopWatch = stopWatchThreadLocal.get();
        if(stopWatch!=null){
            stopWatch.stop();
            long time = stopWatch.getTotalTimeMillis();
            stopWatchThreadLocal.remove();
            return time;
        }else {
            return 0;
        }
    }
}
