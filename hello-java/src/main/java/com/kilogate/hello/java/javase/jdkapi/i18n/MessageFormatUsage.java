package com.kilogate.hello.java.javase.jdkapi.i18n;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 消息格式化用法
 *
 * @author fengquanwei
 * @create 2017/8/4 16:14
 **/
public class MessageFormatUsage {
    public static void main(String[] args) {
        // 占位符格式：{索引,类型,风格}
        String pattern = "On {2,date,long}, a {0} destroyed {1} houses and caused {3,number,currency} of damage.";
        String message = MessageFormat.format(pattern, "hurricane", 99, new GregorianCalendar(1990, 0, 1).getTime(), 10.0E8);
        System.out.println(message);

        // 本地化
        MessageFormat germanyMessageFormat = new MessageFormat(pattern, Locale.GERMANY);
        String germanyMessage = germanyMessageFormat.format(new Object[]{"hurricane", 99, new GregorianCalendar(1990, 0, 1).getTime(), 10.0E8});
        System.out.println(germanyMessage);

        // 选择格式
        String choicePattern = "{0,choice,0#no houses|1#one house|2#{0} houses}";
        System.out.println(MessageFormat.format(choicePattern, -1));
        System.out.println(MessageFormat.format(choicePattern, 0));
        System.out.println(MessageFormat.format(choicePattern, 1));
        System.out.println(MessageFormat.format(choicePattern, 2));
        System.out.println(MessageFormat.format(choicePattern, 3));
        System.out.println(MessageFormat.format(choicePattern, 100));
        // #（\u2264） 表示 <=
        System.out.println(MessageFormat.format("{0,choice,0\u2264no houses|1\u2264one house|2\u2264{0} houses}", -1));
        System.out.println(MessageFormat.format("{0,choice,0\u2264no houses|1\u2264one house|2\u2264{0} houses}", 0));
        System.out.println(MessageFormat.format("{0,choice,0\u2264no houses|1\u2264one house|2\u2264{0} houses}", 1));
        System.out.println(MessageFormat.format("{0,choice,0\u2264no houses|1\u2264one house|2\u2264{0} houses}", 2));
        System.out.println(MessageFormat.format("{0,choice,0\u2264no houses|1\u2264one house|2\u2264{0} houses}", 3));
        System.out.println(MessageFormat.format("{0,choice,0\u2264no houses|1\u2264one house|2\u2264{0} houses}", 100));
        // \u221E 表示无穷
        System.out.println(MessageFormat.format("{0,choice,-\u221E<no houses|0<one house|2<{0} houses}", -1));
        System.out.println(MessageFormat.format("{0,choice,-\u221E<no houses|0<one house|2<{0} houses}", 0));
        System.out.println(MessageFormat.format("{0,choice,-\u221E<no houses|0<one house|2<{0} houses}", 1));
        System.out.println(MessageFormat.format("{0,choice,-\u221E<no houses|0<one house|2<{0} houses}", 2));
        System.out.println(MessageFormat.format("{0,choice,-\u221E<no houses|0<one house|2<{0} houses}", 3));
        System.out.println(MessageFormat.format("{0,choice,-\u221E<no houses|0<one house|2<{0} houses}", 100));
    }
}
