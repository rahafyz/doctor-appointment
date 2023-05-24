package com.blubank.doctorappointment.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LockUtil {

    private static final String APPOINTMENT_SLOT_LOCK_PREFIX = "CM:LOCK:APPOINTMENT_SLOT:%s";

    private final RedissonClient redissonClient;
    public LockUtil(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    private boolean lock(String name, long waitTimeMilliseconds, long leasTimeMilliseconds) {
        try {
            return this.redissonClient.getLock(name).tryLock(waitTimeMilliseconds, leasTimeMilliseconds, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignore) {
            log.error("Exception in acquiring redisson lock {}", name);
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private boolean lock(String name) {
        return this.lock(name, 0, 1000);
    }

    private void releaseLock(String name) {
        this.redissonClient.getLock(name).unlock();
    }

    public boolean getLockForAppointmentSlot(Long timeSlotId) {
        return this.lock(String.format(APPOINTMENT_SLOT_LOCK_PREFIX, timeSlotId));
    }
    public void releaseLockForAppointmentSlotTimeSlot(Long timeSlotId) {
        this.releaseLock(String.format(APPOINTMENT_SLOT_LOCK_PREFIX, timeSlotId));
    }

}