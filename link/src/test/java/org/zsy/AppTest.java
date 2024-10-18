package org.zsy;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Console;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void captcha() {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(500, 100);

//图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write("/Users/zhangsongyang/code/myself/zsywish/link/line.png");
//输出code
        Console.log(lineCaptcha.getCode());
//验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");

////重新生成验证码
//        lineCaptcha.createCode();
//        lineCaptcha.write("/Users/zhangsongyang/code/myself/zsywish/link/line.png");
////新的验证码
//        Console.log(lineCaptcha.getCode());
////验证图形验证码的有效性，返回boolean值
//        System.out.println(lineCaptcha.verify("1234"));
    }

    @Test
    public void dataTime() {
        // 假设 dateArg 是一个 Date 对象
        Date dateArg = new Date();

        // 指定时区
        String timeZone = "Asia/Shanghai";

        // 将 Date 转换为 LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(dateArg.toInstant(), ZoneId.of(timeZone));
        System.out.println("LocalDateTime: " + localDateTime);

        // 将 Date 转换为 ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(dateArg.toInstant(), ZoneId.of(timeZone));
        System.out.println("ZonedDateTime: " + zonedDateTime);
    }


    @Test
    public void rexg() {
        String regex = "^(?=.*\\b(success)\\b)(?=.*\\b(code)\\b)(?=.*\\b(msg)\\b).*$";
        String testString1 = "msg: something, code: 200, success: true";
        String testString2 = "code: 404, msg: Not found";
        String testString3 = "success: true, msg: success message, code: 100";
        String testString4 = "{\"success\":false,\"code\":-50002,\"msg\":\"/ by zero\",\"data\":null}\n";

        System.out.println(testString1.matches(regex)); // true
        System.out.println(testString2.matches(regex)); // false
        System.out.println(testString3.matches(regex)); // true
        System.out.println(testString4.matches(regex)); // true
    }

    @Test
    public void rexgA() {
        String regex = "^(?=.*\"code\"\\s*:)\\s*(?=.*\"msg\"\\s*:).*$";

        String testString1 = "{\"success\":false,\"msg\":\"/ by zero\",\"data\":null}";
        String testString2 = "{\"code\"  :   -404,\"msg\"   :   \"Not found\"    ,   \"success\":false}";
        String testString3 = "{\"msg\":    \"success message\",\"code\":\"100\"  ,    \"success\":  true}";

        System.out.println(testString1.matches(regex)); // true
        System.out.println(testString2.matches(regex)); // true
        System.out.println(testString3.matches(regex)); // true
        Long dd = 1L;
        System.out.println("".equals(dd));

        Long d = Long.valueOf("q11");
        System.out.println(d);
        System.out.println("11");
    }
}
