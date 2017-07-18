package com.kilogate.hello.java.javase.jdkapi.io.regex;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式用法
 *
 * @author fengquanwei
 * @create 2017/7/4 10:33
 **/
public class RegularExpressUsage {
    public static void main(String[] args) throws IOException {
        RegularExpressUsage sv = new RegularExpressUsage();
//        sv.testQuantifier();
//        sv.groupMatch();
//        sv.hrefMatch();

        Pattern pattern = Pattern.compile("(\\d)");
        Matcher matcher = pattern.matcher("123456789");
        String replaceAll = matcher.replaceAll("$1$1");
        System.out.println(replaceAll);
        String replaceFirst = matcher.replaceFirst("0");
        System.out.println(replaceFirst);

        Pattern splitPattern = Pattern.compile("\\.");
        String[] strings = splitPattern.split("127.0.0.1",3);
        System.out.println(Arrays.toString(strings));
    }

    private void hrefMatch() throws IOException {
        InputStreamReader in = new InputStreamReader(new URL("https://www.baidu.com").openStream());
        StringBuilder input = new StringBuilder();

        int ch;
        while ((ch = in.read()) != -1) {
            input.append((char) ch);
        }

        Pattern pattern = Pattern.compile("<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            String match = input.substring(start, end);
            System.out.println(match);
        }

    }

    /**
     * 打印出匹配的群组
     */
    private void groupMatch() {
        // patter: ((1?[0-9]):([0-5][0-9]))[ap]m
        // input: 11:59am
        Scanner in = new Scanner(System.in);
        System.out.println("Enter pattern: ");
        String patternString = in.nextLine();

        Pattern pattern = Pattern.compile(patternString);

        while (true) {
            System.out.println("Enter string to match: ");
            String input = in.nextLine();

            if (input == null || input.equals("")) {
                return;
            }

            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                System.out.println("Match");
                int groupCount = matcher.groupCount();
                if (groupCount > 0) {
                    for (int i = 0; i < input.length(); i++) {
                        for (int j = 1; j <= groupCount; j++) {
                            if (i == matcher.start(j) && i == matcher.end(j)) {
                                System.out.print("()");
                            }
                        }
                        for (int j = 1; j <= groupCount; j++) {
                            if (i == matcher.start(j) && i != matcher.end(j)) {
                                System.out.print("(");
                            }
                        }
                        System.out.print(input.charAt(i));
                        for (int j = 1; j <= groupCount; j++) {
                            if (i + 1 != matcher.start(j) && i + 1 == matcher.end(j)) {
                                System.out.print(")");
                            }
                        }
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No match");
            }
        }
    }

    /**
     * 量词匹配测试
     */
    private void testQuantifier() {
        String html = "head<html>content</html>tail";

        // 贪婪型，默认
        String greediness = "<.+>";
        System.out.println(html.replaceAll(greediness, "###"));

        // 勉强型，?
        String reluctant = "<.+?>";
        System.out.println(html.replaceAll(reluctant, "###"));

        // 占有型，+
        String possessive = "<.++>";
        System.out.println(html.replaceAll(possessive, "###"));
    }
}
