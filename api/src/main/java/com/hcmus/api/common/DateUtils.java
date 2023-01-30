package com.hcmus.api.common;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

public class DateUtils {
    private static final String TIME_ZONE = "Asia/Ho_Chi_Minh";
    private static final int VN_UTC = 7 * 3600; // +7GMT

    public static long convertLocalDateTimeToTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.ofTotalSeconds(VN_UTC));
    }

    public static LocalDateTime convertTimeStampToLocalDateTime(long timeStamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), TimeZone.getTimeZone(TIME_ZONE).toZoneId());
    }
}
