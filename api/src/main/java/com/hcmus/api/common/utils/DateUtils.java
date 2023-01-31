package com.hcmus.api.common.utils;

import com.hcmus.api.common.variables.Time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

public class DateUtils {
    private static final String TIME_ZONE = "Asia/Ho_Chi_Minh";

    public static long convertLocalDateTimeToTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.ofTotalSeconds(Time.VN_UTC));
    }

    public static LocalDateTime convertTimeStampToLocalDateTime(long timeStamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), TimeZone.getTimeZone(TIME_ZONE).toZoneId());
    }
}
