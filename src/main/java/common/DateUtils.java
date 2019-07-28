package common;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * Removes time fields from the given Date object.
     * Can be used to compare dates while ignoring associated time.
     * @param date Date object where time is to be removed
     * @return Date object with time removed from input Date object
     */
    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Computes the difference in years between the two Date objects
     * @param first first Date object
     * @param second second Date object
     * @return difference in years between the input Dates
     */
    public static double getDiffYears(Date first, Date second) {
        Calendar firstCal = Calendar.getInstance();
        firstCal.setTime(first);

        Calendar secondCal = Calendar.getInstance();
        secondCal.setTime(second);

        int diffMonths = 12 * (firstCal.get(Calendar.YEAR) - secondCal.get(Calendar.YEAR));
        diffMonths += firstCal.get(Calendar.MONTH) - secondCal.get(Calendar.MONTH);
        return (diffMonths * 1.0)/12;
    }

    /**
     * Subtracts the noDays days from the given Date object
     * @param date Date object from which days are to be subtracted
     * @param noDays number of days to be subtracted
     * @return Date object after deducting the number of days from input Date
     */
    public static Date minusDays(Date date, long noDays) {
        return Date.from(date.toInstant().minus(Duration.ofDays(noDays)));
    }
}
