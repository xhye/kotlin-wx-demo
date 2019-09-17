package com.aegis.utils

import org.apache.commons.lang.StringUtils
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 对日期的【天】进行加/减
 * @param days 天数，负数为减
 * @return 加/减几天后的日期
 */
fun Date.addDateDays(days: Int): Date {
  val dateTime = DateTime(this)
  return dateTime.plusDays(days).toDate()
}

/*
 * 日期类扩展函数
 * @Date 2018/9/17
 * @since 0.0.1
 */

/**
 * 对日期的【小时】进行加/减
 * @param hours 小时数，负数为减
 * @return 加/减几小时后的日期
 */
fun Date.addDateHours(hours: Int): Date {
  val dateTime = DateTime(this)
  return dateTime.plusHours(hours).toDate()
}

/**
 * 对日期的【分钟】进行加/减
 * @param minutes 分钟数，负数为减
 * @return 加/减几分钟后的日期
 */
fun Date.addDateMinutes(minutes: Int): Date {
  val dateTime = DateTime(this)
  return dateTime.plusMinutes(minutes).toDate()
}

/**
 * 对日期的【月】进行加/减
 * @param months 月数，负数为减
 * @return 加/减几月后的日期
 */
fun Date.addDateMonths(months: Int): Date {
  val dateTime = DateTime(this)
  return dateTime.plusMonths(months).toDate()
}

/**
 * 对日期的【秒】进行加/减
 * @param seconds 秒数，负数为减
 * @return 加/减几秒后的日期
 */
fun Date.addDateSeconds(seconds: Int): Date {
  val dateTime = DateTime(this)
  return dateTime.plusSeconds(seconds).toDate()
}

/**
 * 对日期的【周】进行加/减
 * @param weeks 周数，负数为减
 * @return 加/减几周后的日期
 */
fun Date.addDateWeeks(weeks: Int): Date {
  val dateTime = DateTime(this)
  return dateTime.plusWeeks(weeks).toDate()
}

/**
 * 对日期的【年】进行加/减
 * @param years 年数，负数为减
 * @return 加/减几年后的日期
 */
fun Date.addDateYears(years: Int): Date {
  val dateTime = DateTime(this)
  return dateTime.plusYears(years).toDate()
}

/**
 * 日期格式化 日期格式为：yyyy-MM-dd
 * @return  返回yyyy-MM-dd格式日期
 */
fun Date.format(): String? {
  return this.format(DateUtils.DATE_PATTERN)
}

/**
 * 日期格式化 日期格式为：yyyy-MM-dd
 * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
 * @return  返回yyyy-MM-dd格式日期
 */
fun Date.format(pattern: String): String? {
  val df = SimpleDateFormat(pattern)
  return df.format(this)
}

/**
 * 日期格式化 日期格式为：yyyy-MM-dd HH:mm:ss
 * @return  返回yyyy-MM-dd HH:mm:ss格式日期
 */
fun Date.formatNowDateTime(): String? {
  return this.format(DateUtils.DATE_TIME_PATTERN)
}

/**
 *  时间工具类
 * Created by COPY on 2019/5/17.
 * @author COPY
 * @since 0.0.1
 */
object DateUtils {

  /**  规则 */
  const val DATE_PATTERN: String = "yyyy-MM-dd"
  const val DATE_TIME_PATTERN: String = "yyyy-MM-dd HH:mm:ss"
  const val MONTH_PATTERN: String = "yyyy-MM"

  /**
   * 两时间的天数差
   * @param startMonth 开始月份
   * @param endMonth 结束月份
   * @return 相差的天数
   */
  @Throws(ParseException::class)
  fun diffDayOfTwoDate(startMonth: String, endMonth: String): Int {
    val simpleFormat = SimpleDateFormat(MONTH_PATTERN)
    val startMonthDate = simpleFormat.parse(startMonth)
    val endMonthDate = simpleFormat.parse(endMonth)
    var days = ((endMonthDate.time - startMonthDate.time) / (1000 * 60 * 60 * 24)).toInt()
    val endMonthDateCalendar = Calendar.getInstance()
    endMonthDateCalendar.time = endMonthDate
    days += endMonthDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    return days
  }

  /**
   * 当前季度的开始时间
   * @return String
   */
  fun getCurrentQuarterStartDateString(): String? {
    val c = Calendar.getInstance()
    val currentMonth = c.get(Calendar.MONTH) + 1
    val ftm = getSimpleDateFtm(DATE_PATTERN)
    try {
      when (currentMonth) {
        in 1..3   -> c.set(Calendar.MONTH, 0) //1
        in 4..6   -> c.set(Calendar.MONTH, 3)//4
        in 7..9   -> c.set(Calendar.MONTH, 6)//7
        in 10..12 -> c.set(Calendar.MONTH, 9)//10
      }
      c.set(Calendar.DATE, 1)
      return ftm.format(c.time) + " 00:00:00"
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return ""
  }

  /**
   * 根据 pat获取 SimpleDateFormat
   */
  fun getSimpleDateFtm(pat: String): SimpleDateFormat {
    return SimpleDateFormat(pat)
  }

  /**
   * 根据周数，获取开始日期、结束日期
   * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
   * @return  返回date[0]开始日期、date[1]结束日期
   */
  fun getWeekStartAndEnd(week: Int): Array<Date> {
    val dateTime = DateTime()
    var date = LocalDate(dateTime.plusWeeks(week))

    date = date.dayOfWeek().withMinimumValue()
    val beginDate = date.toDate()
    val endDate = date.plusDays(6).toDate()
    return arrayOf(beginDate, endDate)
  }

  /**
   * 字符串转换成日期
   * @param strDate 日期字符串
   * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
   */
  fun stringToDate(strDate: String?, pattern: String): Date? {
    if (StringUtils.isBlank(strDate)) {
      return null
    }

    val fmt = DateTimeFormat.forPattern(pattern)
    return fmt.parseLocalDateTime(strDate).toDate()
  }

}
