package mx.jhcue.sts.utils;

import lombok.experimental.UtilityClass;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

@UtilityClass
public class DateUtils {

    public String utcTime() {
        return utcTime(System.currentTimeMillis());
    }

    public String utcTime(Clock clock) {
        return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now(clock));
    }

    public String utcTime(long timeMillis) {
        Clock clock = Clock.fixed(Instant.ofEpochMilli(timeMillis), ZoneId.of("UTC"));
        return utcTime(clock);
    }

    public long asLongInEpoch(String isoDate) {
        TemporalAccessor temporalAccessor = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(isoDate);
        return temporalAccessor.getLong(ChronoField.INSTANT_SECONDS) * 1000 + temporalAccessor.get(ChronoField.MILLI_OF_SECOND);
    }
}
