package com.topshow.utils;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

public abstract class CreationNumber {
    private static long numberCount = 0L;

    //private static final int maxPerMSECSize = 20000;

    private static final FastDateFormat pattern = FastDateFormat.getInstance("yyyyMMddHHmmss");

    public static String makeOrderCode(String lock) {
        ReferenceQueue<StringBuilder> queue = new ReferenceQueue<StringBuilder>();
        WeakReference<StringBuilder> weakRef = new WeakReference<StringBuilder>(new StringBuilder(25), queue);

        synchronized (lock) {
            if (null == weakRef.get()) {
                weakRef = new WeakReference<StringBuilder>(new StringBuilder(25), queue);
            }

            if (numberCount >= 20000L) {
                numberCount = 0L;
            }

            ((StringBuilder) weakRef.get()).append(pattern.format(Instant.now().toEpochMilli()));

            ((StringBuilder) weakRef.get()).append(Math.abs(lock.hashCode()));

            ((StringBuilder) weakRef.get()).append(numberCount++);
            return ((StringBuilder) weakRef.get()).toString();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Set<String> set = new HashSet<String>();
        FutureTask<String> task = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Callable<String> callable = new Callable<String>() {
                public String call() throws Exception {
                    return CreationNumber.makeOrderCode(StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
                }
            };
            task = new FutureTask<String>(callable);
            (new Thread(task)).start();

            set.add(task.get());
        }
        System.out.println("总共耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("*************** " + set.size());
    }
}
