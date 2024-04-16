package com.zy.seckill.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {
    public static Integer hmsToSeconds(String hms) {
        return hmsToSeconds(hms, ":");
    }


    public static Integer hmsToSeconds(String hms, String splitor) {
        String[] hmsArr = hms.split(splitor);
        Date baseDate = new Date(0);
        Date newDate = new Date(0);
        newDate = org.apache.commons.lang3.time.DateUtils.addHours(newDate, Integer.parseInt(hmsArr[0]));
        newDate = org.apache.commons.lang3.time.DateUtils.addMinutes(newDate, Integer.parseInt(hmsArr[1]));
        newDate = org.apache.commons.lang3.time.DateUtils.addSeconds(newDate, Integer.parseInt(hmsArr[2]));
        return (int) (newDate.getTime() - baseDate.getTime()) / 1000;
    }

    /**
     * 格式化秒， 如120秒 格式化成2分钟
     *
     * @param seconds
     * @return
     */
    public static String formatSeconds(Long seconds) {
        long secondTime = seconds;
        long minuteTime = 0;// 分
        long hourTime = 0;// 小时
        if (secondTime > 60) {//如果秒数大于60，将秒数转换成整数
            //获取分钟，除以60取整数，得到整数分钟
            minuteTime = seconds / 60;
            //获取秒数，秒数取佘，得到整数秒数
            secondTime = secondTime % 60;
            //如果分钟大于60，将分钟转换成小时
            if (minuteTime > 60) {
                //获取小时，获取分钟除以60，得到整数小时
                hourTime = minuteTime / 60;
                //获取小时后取佘的分，获取分钟除以60取佘的分
                minuteTime = minuteTime % 60;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(secondTime).append("秒");
        if (minuteTime > 0) {
            sb.insert(0, minuteTime + "分");
        }
        if (hourTime > 0) {
            sb.insert(0, hourTime + "小时");
        }
        return sb.toString();
    }

    //获取当前日期开始时间 0点
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }


    //获取当前日期结束时间 23点
    public static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    //计算两个Date类型相隔几天
    public static Long getIntervalTime(Date beginDate, Date endDate) {
        /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        beginDate = format.parse("2007-12-24 12:03:20");
        beginDate = format.parse("2007-12-24 12:03:20");
        Date parse = format.parse("2007-12-25 11:30:20");*/

        long day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /*
     * @Author: zhangyong
     * description: 获取指定时间 flag个月的前后的时间
     *              flag = -1 代表一个月前的时间，flag = 3 代表三个月后的时间
     * @Date: 2019/11/14 14:12
     * @Param:
     * @Return:
     */
    public static Long getSomeMonthTime(Long appointedTime, int flag) {
        Long res = 0L;
        Date date = appointedTime == null ? new Date() : new Date(appointedTime);//当前日期
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        calendar.add(Calendar.MONTH, flag);//月份减一为-1，加一为1
        res = calendar.getTime().getTime();//System.out.println(sdf.format());//输出格式化的日期
        String format1 = format.format(date);
        String format2 = format.format(new Date(res));
        System.out.println("当前时间" + format1);
        System.out.println(flag + "月前/后的时间：" + format2);
        return res;
    }

    //通过指定的日期，获取加上指定天数后的日期
    public static Date addDate(Date date, long appointday) throws ParseException {
        long time = date.getTime(); // 得到指定日期的毫秒数
        appointday = appointday * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += appointday; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    //通过指定的日期的时间戳，返回加上指定天数后的时间戳
    public static Long addTime(Long appointTime, long appointday) {
        long time = appointTime + appointday * 24 * 60 * 60 * 1000; // 获取指定天数后的时间戳
        return time;
    }

    //判断两个日期是否相等
    public static boolean sameDate(Date d1, Date d2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //fmt.setTimeZone(new TimeZone()); // 如果需要设置时间区域，可以在这里设置
        return fmt.format(d1).equals(fmt.format(d2));
    }

    //判断两个日期是的大小
    //如果d1=d2,i=0,如果d1<d2,i<0,如果d1>d2，i>0
    public static boolean compareDate(Date d1, Date d2) {
        int i = d1.compareTo(d2);
        return i >= 0;
    }

    //判断当前时间是否 超过指定时间的 指定毫秒(比如当前时间是否超过一个指定的时间5000毫秒)
    public static boolean compareTheTime(Long theTime, Long seconds) {
        Boolean flag = false;
        Long curTime = new Date().getTime();
        Long compareTime = theTime + seconds;

        if (curTime > compareTime) {
            flag = true;
        }
        return flag;
    }

    //判断前面的时间是否大于后面的时间
    public static boolean compareTime(Long time1, Long time2) {
        Boolean flag = false;
        if (time1 > time2) {
            flag = true;
        }
        return flag;
    }

    /*
     * @Author zhangyong
     * @Description //时间戳转换为date
     * @Date 下午 2:38 2019/7/17 0017
     * @Param
     * @return
     **/
    public static Date timeStamp2date(Long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(timeStamp);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 日期转字符串
     *
     * @param date
     * @return
     */
    public static String date2DateStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = null;
        dateStr = format.format(date);
        return dateStr;
    }

    /*
     * @Author zhangyong
     * @Description //时间戳转换为时间字符串
     * @Date 下午 2:38 2019/7/17 0017
     * @Param
     * @return
     **/
    public static String timeStamp2dateString(Long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(timeStamp);
        return dateString;
    }

    public static String ts2dateStr(Long timeStamp) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd't'HHmmss'z'");
        String dateString = df.format(timeStamp);
        return dateString;
    }

    public static String timeStamp2dateStr(Long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(timeStamp);
        return dateString;
    }


    public static String timeStamp2TimeStr(Long timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String dateString = format.format(timeStamp);
        return dateString;
    }


    //字符串转时间戳
    public static Long dateStr2timeStamp(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = null;
        try {
            time = sdf.parse(dateString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }


    //字符串转时间
    public static Date dateStr2Date(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time = null;
        try {
            time = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    //字符串转时间戳
    public static Long dateString2timeStamp(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Long time = null;
        try {
            time = sdf.parse(dateString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    //字符串转时间戳
    public static Long ds2timeStamp(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Long time = null;
        try {
            time = sdf.parse(dateString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static boolean isLegalDate(String sDate, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(sDate);
            return sDate.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * @Author zhangyong
     * @Description //计算两个时间戳相差的天数
     * @Date 上午 9:09 2019/7/29 0029
     * @Param
     * @return
     **/
    public static Long calculateDayDifferenceOfTwoTimestamp(Long bigerTime, Long littleTime) {
        //java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //java.util.Calendar calendar = Calendar.getInstance(); //
        //Date today = sdf.parse(sdf.format(new Date()));
        // 将今天的日期格式化成 yyyy-MM-dd //
        //Date chooseDate = sdf.parse("2015-10-01");
        // 将选择是日期转换成Date //
        long t = bigerTime - littleTime;
        // 计算两个日期的时间差 //
        long d = t / (1000 * 60 * 60 * 24); //计算两个日期相差的天数
        return d;
    }


    //判断是否是周末
    public static Boolean isWeekend(Long time) throws ParseException {
        Date date = timeStamp2date(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }

    }

    /*
     * @Author zhangyong
     * @Description //获取今天的日期
     * @Date 下午 2:54 2019/7/17 0017
     * @Param
     * @return
     **/
    public static String getTodayDateString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = format.format(new Date());
        return todayString;
    }

    /*
     * @Author zhangyong
     * @Description //获取今天零点零分零秒的秒数(时间戳)
     * @Date 下午 4:31 2019/7/17 0017
     * @Param
     * @return
     **/
    public static Long getTodayZeroTime() {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        return zero;
    }


    /*
     * @Author zhangyong
     * @Description //获取今天23.59.59的秒数(时间戳)
     * @Date 下午 4:32 2019/7/17 0017
     * @Param
     * @return
     **/
    public static Long getTodayTwelveTime() {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        return twelve;
    }


    /*
     * @Author zhangyong
     * @Description //根据时间戳获取时间段字符串(把一天分为24个时间段，比如13:56:23的时间段为14(代表13-14点时间段))
     * @Date 下午 4:31 2019/7/17 0017
     * @Param
     * @return
     **/
    public static String getTimeSection(Long timeStamp) {
        Date date = new Date(timeStamp);
        int hours = date.getHours();
        return String.valueOf(hours + 1);
    }

    /*
     * @Author zhangyong
     * @Description //获取昨天这一时刻的秒数
     * @Date 下午 4:33 2019/7/17 0017
     * @Param
     * @return
     **/
    public static Long getYesterdayThisTime() {
        long yesterday = System.currentTimeMillis() - 24 * 60 * 60 * 1000;//昨天的这一时间的毫秒数
        return yesterday / 1000;
    }


    /*
     * @Author zhangyong
     * @Description  //通过指定的日期的时间戳，返回当前时间最近的出入记录统计时间点(03:00,06:00,09:00。。。没三个小时为一个时间点)
                     //比如最新记录时间为上午8点13分20秒，那么取出8点，第一个统计时间点就为6点（8/3 余2，8-2(余数)=6点)
     * @Date 下午 4:01 2019/7/22 0022
     * @Param []
     * @return java.lang.Long
     **/
    public static Long getFirstStatisticsPointTime(Long assignTime) {

        //1.获取指定时间的日期
        Date d = new Date(assignTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(d);

        //2.获取离当前时间最近的统计时间点小时
        Calendar calendar = Calendar.getInstance();
        //calendar.setTime(new Date());
        calendar.setTimeInMillis(assignTime);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        String firstHourStr;
        int firstHour = currentHour - (currentHour % 3);
        if (firstHour < 10) {
            firstHourStr = "0" + firstHour;
        } else {
            firstHourStr = firstHour + "";
        }


        //3.获取统计时间点的日期
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dateStr + " " + firstHourStr + ":00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //4.将日期转换为时间戳
        Long firstPointTime = date.getTime();

        return firstPointTime;
    }


    /*
     * @Author zhangyong
     * @Description  //通过指定的日期的时间戳，返回当前时间最近的的巡更记录统计时间点(02:00-04:00,。。。每2个小时为一个时间点)
     * @Date 下午 4:01 2019/7/22 0022
     * @Param []
     * @return java.lang.Long
     **/
    public static Long getFirstPatrolRecordStatisticsPointTime(Long assignTime) {

        //1.获取指定时间的日期
        Date d = new Date(assignTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(d);

        //2.获取离当前时间最近的统计时间点小时
        Calendar calendar = Calendar.getInstance();
        //calendar.setTime(new Date());
        calendar.setTimeInMillis(assignTime);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        String firstHourStr;
        int firstHour = currentHour - (currentHour % 2);
        if (firstHour < 10) {
            firstHourStr = "0" + firstHour;
        } else {
            firstHourStr = firstHour + "";
        }

        //3.获取统计时间点的日期
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dateStr + " " + firstHourStr + ":00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //4.将日期转换为时间戳
        Long firstPointTime = date.getTime();

        return firstPointTime;
    }


    /*
     * @Author zhangyong
     * @Description //获取指定时间后的时间戳
     * @Date 下午 4:03 2019/7/22 0022
     * @Param
     * @return
     **/
    public static Long getReduceTime(long assignTime, long reduceTime) {
        //Long reduceTime = hour*60*60*1000; // 要减去的小时数转换成毫秒数
        assignTime -= reduceTime; // 相减得到新的毫秒数
        return assignTime; // 将毫秒数转换成日期
    }

    //判读时间格式(yyyy-MM-dd HH:mm:ss)
    public static boolean isTimeLegal(String patternString) {
        Pattern a = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher b = a.matcher(patternString);
        if (b.matches()) {
            return true;
        } else {
            return false;
        }
    }


    /*
     * @Author zhangyong
     * @Description //计算出某个时间点前后 指定时间的时间段(比如18:00:00前后5分钟的时间段)
     * @Date 下午 4:03 2019/7/22 0022
     * @Param
     * @return
     **/
    public static Map<String, Object> getTimeParagraph(long assignTime, long beforeTime, long afterTime) {
        Long startTime = assignTime - beforeTime; // 要减去的毫秒数
        Long endTime = assignTime + afterTime; // 要加上的毫秒数
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("startTime", startTime);
        resultMap.put("endTime", endTime);
        return resultMap;
    }


    /*
     * @Author zhangyong
     * @Description //获取当前月份第一天00:00:00的时间戳
     * @Date 上午 9:09 2019/7/29 0029
     * @Param
     * @return
     **/
    public static Long getCurrentMonthFirstDayTime() {
        Calendar calendar = Calendar.getInstance();
        //将天至1
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        //获得当前月第一天
        Date startDate = calendar.getTime();
        long currentMonthFirstDayTime = startDate.getTime();
        return currentMonthFirstDayTime;
    }

    /*
     * @Author zhangyong
     * @Description //获取当前月份最后一天23:59:59的时间戳
     * @Date 上午 9:09 2019/7/29 0029
     * @Param
     * @return
     **/
    public static Long getCurrentMonthLastDayTime() {
        Calendar calendar = Calendar.getInstance();
        //将天至1
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        //将当前月加1；
        calendar.add(Calendar.MONTH, 1);
        //在当前月的 下一个月基础上减去1毫秒
        calendar.add(Calendar.MILLISECOND, -1);
        //获得当前月最后一天
        Date endDate = calendar.getTime();
        long currentMonthLastDayTime = endDate.getTime();
        return currentMonthLastDayTime;
    }


    /*
     * @Author zhangyong
     * @Description //获取指定月份第一天的日期(月份从0开始)
     * @Date 上午 9:09 2019/7/29 0029
     * @Param
     * @return
     **/
    public static int getMonthFirstDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);//指定年份year
        calendar.set(Calendar.MONTH, month);//指定月份month
        int maxDate = calendar.getActualMinimum((Calendar.DATE));
        return maxDate;
    }

    public static Long getCurrentFirstDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0); //获取当前月第一天
        c.set(Calendar.DAY_OF_MONTH, 1); //设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0); //将小时至0
        c.set(Calendar.MINUTE, 0); //将分钟至0
        c.set(Calendar.SECOND, 0); //将秒至0
        c.set(Calendar.MILLISECOND, 0); //将毫秒至0
        return c.getTime().getTime();
    }


    public static Long getCurrentLastDay() {
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.DAY_OF_MONTH, c2.getActualMaximum(Calendar.DAY_OF_MONTH)); //获取当前月最后一天
        c2.set(Calendar.HOUR_OF_DAY, 23); //将小时至23
        c2.set(Calendar.MINUTE, 59); //将分钟至59
        c2.set(Calendar.SECOND, 59); //将秒至59
        return c2.getTime().getTime();
    }

    /*
     * @Author zhangyong
     * @Description //获取指定月份最后一天的日期(月份从0开始)
     * @Date 上午 9:09 2019/7/29 0029
     * @Param
     * @return
     **/
    public static int getMonthLastDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);//指定年份year
        calendar.set(Calendar.MONTH, month);//指定月份month
        int maxDate = calendar.getActualMaximum(Calendar.DATE);
        return maxDate;
    }

    //获取指定月份第一天00:00:00的时间戳(月份从0开始)
    public static Long getMonthStartTime(int year, int month) {
        //(1).获取指定月份第一天的号数
        int monthFirstDay = getMonthFirstDay(year, month);
        //(2).获取指定月份指定天数的第一天00:00:00的时间戳
        Long currentDayStartTime = getCurrentDayStartTime(year, month - 1, monthFirstDay);
        return currentDayStartTime;

    }

    //获取指定月份最后一天23:59:59的时间戳(月份从0开始)
    public static Long getMonthEndTime(int year, int month) {
        //(1).获取指定月份最后一天的号数(根据月份不同，最后一天可能有28,29,30,31)
        int monthFirstDay = getMonthLastDay(year, month);
        //(2).获取指定月份指定天数的最后一天23:59:59的时间戳
        Long currentDayStartTime = getCurrentDayEndTime(year, month - 1, monthFirstDay);
        return currentDayStartTime;
    }


    /*
     * @Author zhangyong
     * @Description //计算当前月有多少天
     * @Date 上午 9:28 2019/7/29 0029
     * @Param [year, month]
     * @return int
     **/
    public static int getCurrentMonthDays(int year, int month) {
        int days = 0;
        if (month != 2) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    days = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    days = 30;

            }
        } else {
            // 闰年
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                days = 29;
            else
                days = 28;
        }
        return days;
    }


    /*
     * @Author zhangyong
     * @Description //获取年的月数 如7(月份为0--11,所以计算出来需要+1)
     * @Date 上午 9:09 2019/7/29 0029
     * @Param
     * @return
     **/
    public static int getCurrentMonthNumber() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    /*
     * @Author zhangyong
     * @Description //获取年的年数 如2019
     * @Date 上午 9:09 2019/7/29 0029
     * @Param
     * @return
     **/
    public static int getCurrentYearNumber() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /*
     * @Author zhangyong
     * @Description //获取指定年数，月数，天数的00:00:00的时间戳(月份从0开始为第一月,所以实际月份需要-1)
     * @Date 上午 9:09 2019/7/29 0029
     * @Param
     * @return
     **/
    public static Long getCurrentDayStartTime(Integer year, Integer month, Integer day) {
        Calendar calendar = Calendar.getInstance();
        //将年设置为指定年数
        calendar.set(Calendar.YEAR, year);
        //将月设置为指定月数
        calendar.set(Calendar.MONTH, month);
        //将天至1
        calendar.set(Calendar.DAY_OF_MONTH, day);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);

        Date startTime = calendar.getTime();
        long currentDayStartTime = startTime.getTime();
        return currentDayStartTime;
    }


    /*
     * @Author zhangyong
     * @Description //获取指定年数，月数，天数23:59:59的时间戳
     * @Date 上午 9:09 2019/7/29 0029
     * @Param
     * @return
     **/
    public static Long getCurrentDayEndTime(Integer year, Integer month, Integer day) {
        Calendar calendar = Calendar.getInstance();
        //将年设置为指定年数
        calendar.set(Calendar.YEAR, year);
        //将月设置为指定月数
        calendar.set(Calendar.MONTH, month);
        //将天至1
        calendar.set(Calendar.DAY_OF_MONTH, day);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 59);
        //将秒至0
        calendar.set(Calendar.SECOND, 59);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 59);

        Date endTime = calendar.getTime();
        long currentDayEndTime = endTime.getTime();
        return currentDayEndTime;
    }

    public static Map<String, Object> getYearMonthDayByTimeStamp(Long timeStamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeStamp);

        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int doy = cal.get(Calendar.DAY_OF_YEAR);

        System.out.println("当期时间: " + cal.getTime());
        System.out.println("日期: " + day);
        System.out.println("月份: " + month);
        System.out.println("年份: " + year);
        System.out.println("一周的第几天: " + dow);  // 星期日为一周的第一天输出为 1，星期一输出为 2，以此类推
        System.out.println("一月中的第几天: " + dom);
        System.out.println("一年的第几天: " + doy);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("day", day);
        resultMap.put("month", month);
        resultMap.put("year", year);
        resultMap.put("dow", dow);
        resultMap.put("dom", dom);
        resultMap.put("doy", doy);

        return resultMap;
    }


    /*
     * @Author zhangyong
     * @Description //获取当前时间格式化后的时间
     * @Date 上午 9:09 2019/7/29 0029
     * @Param
     * @return
     **/
    public static String getCurrentTimeStr() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return fmt.format(new Date());
    }

    public static String getCurrentTimeString() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(new Date());
    }


    /*
     * @Author: zhangyong
     * description: 获取指定月份的每一天的时间字符串列表
     * @Date: 2020-06-22 13:29
     * @Param:
     * @Return:
     */
    public static List<String> getDayListOfMonth(int yearNumber, int monthNumber) {
        List<String> list = new ArrayList<String>();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        /*int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份*/
        //Calendar 月份从0开始 这里要减1
        aCalendar.set(Calendar.YEAR, yearNumber);
        aCalendar.set(Calendar.MONTH, monthNumber - 1);
//        int day = aCalendar.getActualMaximum(Calendar.DATE);
        int day = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= day; i++) {
            String aDate = String.valueOf(yearNumber + "-" + (monthNumber < 10 ? "0" + monthNumber : monthNumber) + "-" + (i < 10 ? "0" + i : i));
            list.add(aDate);
        }
        return list;
    }


    public static List<String> getMonthListOfYear(int yearNumber) {
        List<String> list = new ArrayList<String>();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        /*int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份*/
        aCalendar.set(Calendar.YEAR, yearNumber);
        int monthNumber = aCalendar.getActualMaximum(Calendar.MONTH);
        for (int i = 1; i <= monthNumber + 1; i++) {
            String aDate = String.valueOf(yearNumber) + "-" + (i < 10 ? "0" + i : i);
            list.add(aDate);
        }
        return list;
    }

    /*
     * @Author: zhangyong
     * description: 获取指定月份每一天的开始和结束时间戳
     * @Date: 2020-06-22 13:29
     * @Param:
     * @Return:
     */
    public static List<Map<String, Object>> getEveryDayOfMonth(int yearNumber, int monthNumber) {
        List<Map<String, Object>> list = new ArrayList<>();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        aCalendar.set(Calendar.YEAR, yearNumber);
        aCalendar.set(Calendar.MONTH, monthNumber);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String aDate = String.valueOf(yearNumber) + "-" + monthNumber + "-" + i;
            Long startTime = getCurrentDayStartTime(yearNumber, monthNumber, i);
            Long endTime = getCurrentDayEndTime(yearNumber, monthNumber, i);
            Map<String, Object> map = new HashMap<>();
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            list.add(map);
        }
        return list;
    }


    /*
     * @Author: zhangyong
     * description: 获取指定指定月份的天数
     * @Date: 2020-06-22 13:33
     * @Param:
     * @Return:
     */
    public static Integer getDayAmountOfMonth(int yearNumber, int monthNumber) {
        List<String> list = new ArrayList<String>();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        /*int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份*/
        aCalendar.set(Calendar.YEAR, yearNumber);
        aCalendar.set(Calendar.MONTH, monthNumber);
        int day = aCalendar.getActualMaximum(Calendar.DATE);

        return day;
    }


    //将毫秒数转为*天*小时*分*秒的形式
    public static String formatDateByTimeStamp(Long ms) {
        String dateTimes;
        long ss = ms / 1000;
        long days = ss / (60 * 60 * 24);
        long hours = (ss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (ss % (60 * 60)) / 60;
        long seconds = ss % 60;
        if (days > 0) {
            dateTimes = days + "天" + hours + "小时" + minutes + "分" + seconds + "秒";
        } else if (hours > 0) {
            dateTimes = hours + "小时" + minutes + "分" + seconds + "秒";
        } else if (minutes > 0) {
            dateTimes = minutes + "分" + seconds + "秒";
        } else {
            dateTimes = seconds + "秒";
        }
        return dateTimes;
    }

    public static Boolean isToday(Long time) {
        Boolean flag = true;
        Long todayZeroTime = getTodayZeroTime();
        Long todayTwelveTime = getTodayTwelveTime();
        if (time > todayTwelveTime) {
            flag = false;
        }

        if (time < todayZeroTime) {
            flag = false;
        }
        return flag;
    }

    public static Boolean isYesterday(Long time) {
        Boolean flag = true;
        Long todayZeroTime = getTodayZeroTime();
        Long todayTwelveTime = getTodayTwelveTime();
        Long oneDay = 24 * 60 * 60 * 1000L;
        Long yesterdayZeroTime = todayZeroTime - oneDay;
        Long yesterdayTwelveTime = todayTwelveTime - oneDay;
        if (time > yesterdayTwelveTime) {
            flag = false;
        }

        if (time < yesterdayZeroTime) {
            flag = false;
        }
        return flag;
    }

    //获取今天整点的时间
    public static Long getOclockTimeStampByNumber(Integer OclockNumber) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, OclockNumber);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTime().getTime();
        return time;
    }

    /**
     * @param //要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return days + " days " + hours + " hours " + minutes + " minutes "
                + seconds + " seconds ";
    }

    /**
     * @param //要转换的毫秒数
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     */
    public static String formatDuringDh(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return days + "天" + hours + "小时";
    }


    /**
     * 查询当月每一天日期
     *
     * @param today
     * @return
     */
    public static List<String> dateOfMonth(Date today) {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 月初
        System.out.println("月初" + sdf.format(getMonthStart(today)));
        // 月末
        System.out.println("月末" + sdf.format(getMonthEnd(today)));

        Date date = getMonthStart(today);
        dateList.add(sdf.format(date));
        Date monthEnd = getMonthEnd(today);
        while (!date.after(monthEnd)) {
            date = getNext(date);
            dateList.add(sdf.format(date));
        }
        return dateList;
    }

    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }

    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }

    private static Date getNext(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }


    public static Date getLastMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getLastMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
//        int month=calendar.get(Calendar.MONTH);
        calendar.add(Calendar.MONTH, -1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }

    /**
     * 获取当年的第一天
     *
     * @param
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *
     * @param
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    //获取指定天数/或者指定天数之前的时间戳()
    public static Long getDayTimestamp(Integer dayCount) {
        Calendar calendar = Calendar.getInstance();
        //为0即不设置日期，即当天
        if (dayCount != 0) {
            // 设为1即当前月的1号()，设为5即5号，设为-3即上个月27号
            calendar.add(Calendar.DAY_OF_MONTH, dayCount);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    //获取指定月份/或者指定月份之前的时间戳()
    public static Long getMonthTimestamp(Integer monthCount, Integer dayCount) {
        Calendar calendar = Calendar.getInstance();
        if (dayCount != 0) {
            //设为0即今天， 设为1即当前月的1号()，设为5即5号，设为-3即上个月27号
            calendar.set(Calendar.DAY_OF_MONTH, dayCount);
        }
        calendar.add(Calendar.MONTH, monthCount);// 0表示当前月，-2就是当前月-2
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

/* public static void main(String[] args) throws ParseException {


     Long dayTimestamp1 = getDayTimestamp(-1);
     //Long dayTimestamp3 = getDayTimestamp(-5);
     System.out.println("dayTimestamp1-->"+dayTimestamp1);
     //System.out.println("dayTimestamp3-->"+dayTimestamp3);

 }*/

}