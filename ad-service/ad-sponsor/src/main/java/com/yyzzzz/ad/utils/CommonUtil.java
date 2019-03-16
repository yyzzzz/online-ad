package com.yyzzzz.ad.utils;

import com.yyzzzz.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by yyzzzz on 2019/3/16.
 */
public class CommonUtil {

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"};

    public static String md5(String value) {
        return DigestUtils.md5Hex(value);
    }

    public static Date parseString2Date(String dateString) throws AdException{
        try {
            return DateUtils.parseDate(dateString, parsePatterns);
        } catch (ParseException e) {
            throw new AdException(e.getMessage());
        }
    }
}
