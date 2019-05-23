package mx.jhcue.sts.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Clock;
import java.time.ZoneId;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class DateUtilsTest {

    private static final String PATTERN = "^\\d{4}(-\\d{2}){2}T\\d{2}(:\\d{2}){2}\\.\\d{2,3}Z$";

    @Test
    public void utcTime() {
        String utcTime = DateUtils.utcTime();
        log.info("Time read: {}", utcTime);
        assertThat(utcTime).matches(PATTERN);
    }

    @Test
    public void utcTimeWithClock() {
        Clock clock = Clock.systemUTC();
        String utcTime = DateUtils.utcTime(clock);
        log.info("Time read: {}", utcTime);
        assertThat(utcTime).matches(PATTERN);
    }

    @Test
    public void utcTimeWithClock_atSpecificLong() {
        long currentTimeMillis = System.currentTimeMillis();
        String utcTime = DateUtils.utcTime(currentTimeMillis);
        log.info("Time read: {}", utcTime);
        assertThat(utcTime).matches(PATTERN);
    }

    @Test
    public void utcTimeWithClock_2HoursAgo() {
        Clock clock = Clock.fixed(Clock.systemUTC().instant().minus(2, HOURS), ZoneId.of("UTC"));
        String utcTime = DateUtils.utcTime(clock);
        log.info("Time read: {}", utcTime);
        assertThat(utcTime).matches(PATTERN);
    }

    @Test
    public void utcTimeWithClock_1HourLater() {
        Clock clock = Clock.fixed(Clock.systemUTC().instant().plus(1, HOURS), ZoneId.of("UTC"));
        String utcTime = DateUtils.utcTime(clock);
        log.info("Time read: {}", utcTime);
        assertThat(utcTime).matches(PATTERN);
    }

    @Test
    public void asLongInEpoch() {
        long t0 = System.currentTimeMillis();
        String isoDate = DateUtils.utcTime(t0);
        long t = DateUtils.asLongInEpoch(isoDate);
        log.info("Time Millis {} gives UTC time {} and returns {} long", t0, isoDate, t);
        assertThat(t).isEqualTo(t0);
    }
}
