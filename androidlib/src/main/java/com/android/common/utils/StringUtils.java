/*
 * File Name: StringUtils.java 
 * History:
 * Created by wangyl on 2011-9-5
 */
package com.android.common.utils;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 字符串工具类
 * 
 * @author wangyl
 * @version
 */
public class StringUtils {
    // ==========================================================================
    // Constants
    // ==========================================================================
    /** 默认首字母 */
    private static final String DEFAULT_HEADER_LETTER = "#";

    /** 国标码和区位码转换常量 */
    private static final int GB_SP_DIFF = 0xa0;

    /** 存放国标一级汉字不同读音的起始区位码 */
    private static final int[] SEC_POS_VALUES = { 1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212,
            3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600 };

    /** 拼音首字母(大写) */
    public static final char[] UPPERCASE_PINYIN_BEGIN_LETTERS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z' };

    /** 拼音首字母(小写) */
    public static final char[] LOWERCASE_PINYIN_BEGIN_LETTERS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x', 'y', 'z' };

    /** 大写字母 */
    public static final char[] UPPERCASE_LETTERS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    /** 小写字母 */
    public static final char[] LOWERCASE_LETTERS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    public static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f' };

    private static final char REPLACEMENT_CHAR = (char) 0xfffd;

    public static final String APOSTROPHE = "...";
    // ==========================================================================
    // Fields
    // ==========================================================================

    // ==========================================================================
    // Constructors
    // ==========================================================================

    // ==========================================================================
    // Getters
    // ==========================================================================

    // ==========================================================================
    // Setters
    // ==========================================================================

    // ==========================================================================
    // Methods
    // ==========================================================================
    /**
     * 是否以ASCII码开头
     */
    public static final boolean isASCIIHeader(String str) {
        if (str != null && str.length() > 0) {
            char c = str.charAt(0);
            return c < 128 && c > 0;
        }
        return false;
    }

    /**
     * 是否含有中文字符
     * 
     * @param s
     * @return
     */
    public static boolean regxChinese(String s) {
        boolean result;
        if (null == s || s.length() == 0) {
            result = false;
        } else {
            s = new String(s.getBytes());
            String pattern = "[\u4e00-\u9fa5]+";
            Pattern p = Pattern.compile(pattern);
            Matcher matcher = p.matcher(s);
            result = matcher.find();
        }
        return result;
    }

    /**
     * 获取字符串的拼音首字母
     * 
     * @param chineseStr
     * @return 拼音首字母如果不在常用汉字中，返回{@link #DEFAULT_HEADER_LETTER}
     * @throws UnsupportedEncodingException
     */
    public static final String getHeadLetterFromChinese(String chineseStr, boolean uppercase, String defaultLetter) {
        String result;
        if (defaultLetter == null) {
            result = DEFAULT_HEADER_LETTER;
        } else {
            result = defaultLetter;
        }
        if (chineseStr != null && chineseStr.length() != 0) {
            byte[] chBytes = null;
            try {
                chBytes = chineseStr.getBytes("gb2312");
            } catch (Throwable tr) {
                return result;
            }
            if (chBytes != null && chBytes.length > 1) {
                chBytes[0] -= GB_SP_DIFF;
                chBytes[1] -= GB_SP_DIFF;
                int secPosvalue = chBytes[0] * 100 + chBytes[1];
                for (int i = 0; i < 23; i++) {
                    if (secPosvalue >= SEC_POS_VALUES[i] && secPosvalue < SEC_POS_VALUES[i + 1]) {
                        if (uppercase) {
                            result = String.valueOf(UPPERCASE_PINYIN_BEGIN_LETTERS[i]);
                        } else {
                            result = String.valueOf(LOWERCASE_PINYIN_BEGIN_LETTERS[i]);
                        }
                        break;
                    }
                }
                return result;
            }
        }
        return result;

    }


    /**
     * 判断字符串的编码
     * 
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                LogUtils.d("----" + s);
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                LogUtils.d("----" + s1);
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                LogUtils.d("----" + s2);
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                LogUtils.d("----" + s3);
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }

    public static CharSequence getHighLightText(String content, int color, int start, int end) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length() ? end : content.length();
        SpannableString spannable = new SpannableString(content);
        CharacterStyle span = new ForegroundColorSpan(color);
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 封装 针对html格式字符串 封装为Spanned
     * 
     * @param content
     * @param fallback
     *            异常情况 的默认值
     * @return
     */
    public static CharSequence getHighLightTextByHtml(CharSequence content, String fallback) {

        if (content instanceof SpannableString) {
            return content;
        }
        if (TextUtils.isEmpty(content) || !(content instanceof String)) {
            return fallback;
        }
        try {
            Spanned spannable = Html.fromHtml((String) content);
            return spannable;
        } catch (Throwable tr) {
            LogUtils.e(tr);
        }
        return fallback;
    }

    public static CharSequence getHighLightTextByHtml(CharSequence content) {
        return getHighLightTextByHtml(content, "");
    }

    public static String getHexString(int i) {
        StringBuilder sb = new StringBuilder(8);
        String a = Integer.toHexString(i);
        int len = 8 - a.length();
        for (int j = 0; j < len; j++) {
            sb.append('0');
        }
        sb.append(a);
        return sb.toString();
    }

    public static String getHexString(byte[] byteArray) {
        if (null == byteArray) {
            return "";
        }
        StringBuilder sb = new StringBuilder(byteArray.length * 3);
        for (int i = 0; i < byteArray.length; i++) {
            byte b = byteArray[i];
            sb.append(HEX_DIGITS[(b >> 4) & 0x0f]);
            sb.append(HEX_DIGITS[b & 0x0f]);
            sb.append(' ');
        }
        return sb.toString();
    }


    public static String longFormatYMDHMS(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }

    public static int parseInt(String str) {
        return parseInt(str, 0);
    }

    public static int parseInt(String str, int fallback) {
        int i = fallback;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            LogUtils.w(e.getMessage());
        }
        return i;
    }

    public static long parseLong(String str) {
        return parseLong(str, 0);
    }

    public static long parseLong(String str, long fallback) {
        long i = 0;
        try {
            i = Long.parseLong(str);
        } catch (NumberFormatException e) {
            LogUtils.w(e.getMessage());
        }
        return i;
    }

    /**
     * 获取链接样式的字符串
     * 
     * @return
     */
    public static Spanned getHtmlStyleString(Context context, int resId) {
        String htmlRes = "<a href=\"\"><u><b>" + context.getString(resId) + " </b></u></a>";
        return Html.fromHtml(htmlRes);
    }

    public static String getUrlEncodedString(String src, String def) {
        try {
            src = URLEncoder.encode(src, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtils.e(e);
            src = def;
        }
        return src;
    }

    public static int getDefaultTextColor(Context context) {
        int color = new TextView(context).getTextColors().getDefaultColor();
        return color;
    }

    public static String getColorInHexString(int color) {
        char[] hex = new char[6];
        for (int i = 5; i >= 0; i--) {
            hex[i] = "0123456789abcdef".charAt(color & 0x0f);
            color = color >> 4;
        }
        return String.valueOf(hex);
    }

    public static boolean isEmpty(CharSequence str) {
        return isEmpty(str, false);
    }

    public static boolean isEmpty(CharSequence str, boolean trim) {
        if (trim && str != null) {
            str = String.valueOf(str).trim();
        }
        return TextUtils.isEmpty(str) || "null".equals(str);
    }

    public static String getFormattedDateTime(long timestamp, String formatStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            Date date = new Date(timestamp);
            return format.format(date.getTime());
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return "";
    }

    /**
     * 通配符批配
     * 
     * @param str
     *            比较的 字符串
     * @param wildcardStr
     *            通配符
     * @return
     */
    public static boolean matchByWildcard(String str, String wildcardStr) {
        return str.matches(
                wildcardStr.replace("*", "[a-zA-Z0-9]{0,}").replace("?", "[a-zA-Z0-9]{1}").replace(".", "\\."));
    }

    /**
     * 通配符批配
     * 
     * @param str
     *            比较的 字符串
     * @param wildcardStr
     *            通配符
     * @return
     */
    public static boolean matchByWildcard(String str, String wildcardStr, boolean supportSecondDomain) {
        return str.matches(wildcardStr.replace("*", supportSecondDomain ? "[a-zA-Z0-9\\.]{0,}" : "[a-zA-Z0-9]{0,}")
                .replace("?", "[a-zA-Z0-9]{1}").replace(".", "\\."));
    }

    // 比较两个字符串，按照字典排序，如果为空串，则认为在前；
    public static long Compare(String str1, String str2) {
        if (isEmpty(str1) && isEmpty(str2)) {
            return 0;
        } else if (isEmpty(str1) && !isEmpty(str2)) {
            return -1;
        } else if (!isEmpty(str1) && isEmpty(str2)) {
            return 1;
        } else {
            return str1.compareTo(str2);
        }
    }

    /** convert the chars to String encoded by unicode */
    public static String getUnicodeByStr(char[] in, int off, int len) {
        char[] convtBuf = new char[len * 2];
        /*
         * if (convtBuf.length < len) { int newLen = len * 2; if (newLen < 0) { newLen = Integer.MAX_VALUE; } convtBuf =
         * new char[newLen]; }
         */
        char aChar;
        char[] out = convtBuf;
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[off++];
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    out[outLen++] = aChar;
                }
            } else {
                out[outLen++] = (char) aChar;
            }
        }
        return new String(out, 0, outLen);
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static void copyText(Context context, CharSequence text) {
        if (VersionUtils.hasHoneycomb()) {
            ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setText(text);
        } else {
            android.text.ClipboardManager cmb = (android.text.ClipboardManager) context
                    .getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setText(text);
        }
    }

    /**
     * 获取一段字符串的字符个数（包含中英文，一个中文算2个字符）
     */
    public static int getCharacterNum(String content) {
        if (isEmpty(content)) {
            return 0;
        } else {
            return (content.length() + getChineseNum(content));
        }
    }

    /**
     * 返回字符串里中文字或者全角字符的个数
     */
    public static int getChineseNum(String s) {
        int num = 0;
        char[] myChar = s.toCharArray();
        for (int i = 0; i < myChar.length; i++) {
            if ((char) (byte) myChar[i] != myChar[i]) {
                num++;
            }
        }
        return num;
    }

    /**
     * 字节数组转换为 utf8编码的字符数组
     * 
     * @param bytes
     * @return
     */
    public static char[] getUtf8Chars(byte[] bytes) {
        return getUtf8Chars(bytes, 0, bytes.length);
    }

    /**
     * 字节数组转换为 utf8编码的字符数组
     * 
     * @param bytes
     * @param offset
     * @param byteCount
     * @return
     */
    public static char[] getUtf8Chars(byte[] bytes, int offset, int byteCount) {
        char[] value;
        byte[] d = bytes;
        char[] v = new char[byteCount];

        int idx = offset;
        int last = offset + byteCount;
        int s = 0;
        outer: while (idx < last) {
            byte b0 = d[idx++];
            if ((b0 & 0x80) == 0) {
                // 0xxxxxxx
                // Range: U-00000000 - U-0000007F
                int val = b0 & 0xff;
                v[s++] = (char) val;
            } else if (((b0 & 0xe0) == 0xc0) || ((b0 & 0xf0) == 0xe0) || ((b0 & 0xf8) == 0xf0) || ((b0 & 0xfc) == 0xf8)
                    || ((b0 & 0xfe) == 0xfc)) {
                int utfCount = 1;
                if ((b0 & 0xf0) == 0xe0)
                    utfCount = 2;
                else if ((b0 & 0xf8) == 0xf0)
                    utfCount = 3;
                else if ((b0 & 0xfc) == 0xf8)
                    utfCount = 4;
                else if ((b0 & 0xfe) == 0xfc)
                    utfCount = 5;

                if (idx + utfCount > last) {
                    v[s++] = REPLACEMENT_CHAR;
                    break;
                }

                // Extract usable bits from b0
                int val = b0 & (0x1f >> (utfCount - 1));
                for (int i = 0; i < utfCount; i++) {
                    byte b = d[idx++];
                    if ((b & 0xC0) != 0x80) {
                        v[s++] = REPLACEMENT_CHAR;
                        idx--; // Put the input char back
                        continue outer;
                    }
                    // Push new bits in from the right side
                    val <<= 6;
                    val |= b & 0x3f;
                }

                if ((utfCount != 2) && (val >= 0xD800) && (val <= 0xDFFF)) {
                    v[s++] = REPLACEMENT_CHAR;
                    continue;
                }

                // Reject chars greater than the Unicode maximum of U+10FFFF.
                if (val > 0x10FFFF) {
                    v[s++] = REPLACEMENT_CHAR;
                    continue;
                }

                // Encode chars from U+10000 up as surrogate pairs
                if (val < 0x10000) {
                    v[s++] = (char) val;
                } else {
                    int x = val & 0xffff;
                    int u = (val >> 16) & 0x1f;
                    int w = (u - 1) & 0xffff;
                    int hi = 0xd800 | (w << 6) | (x >> 10);
                    int lo = 0xdc00 | (x & 0x3ff);
                    v[s++] = (char) hi;
                    v[s++] = (char) lo;
                }
            } else {
                // Illegal values 0x8*, 0x9*, 0xa*, 0xb*, 0xfd-0xff
                v[s++] = REPLACEMENT_CHAR;
            }
        }

        if (s == byteCount) {
            // We guessed right, so we can use our temporary array as-is.
            offset = 0;
            value = v;
        } else {
            // Our temporary array was too big, so reallocate and copy.
            offset = 0;
            value = new char[s];
            System.arraycopy(v, 0, value, 0, s);
        }
        return value;
    }

    /**
     * 一个汉字按两个字符，英文按一个字符进行截取
     *
     * @param text          要截取的文本
     * @param maxCharLength 最大字符长度
     * @return
     */
    public static String substring(String text, int maxCharLength, boolean isAddPoint) {
        if (text == null || text.length() == 0) {
            return text;
        }

        int index = 0;
        int currentTotalLength = 0;
        int currentCharLength = 0;
        do {
            currentTotalLength += currentCharLength;
            if (text.charAt(index) > 'z') {
                // 认定为汉字
                currentCharLength = 2;
            } else {
                // 认定为字母
                currentCharLength = 1;
            }
        }
        while ((currentTotalLength + currentCharLength <= maxCharLength) && (++index > Integer.MIN_VALUE)
                && index < text.length());

        String result = text.substring(0, index);
        if (index < text.length() && isAddPoint) {
            // 对文字进行截取了
            result += APOSTROPHE;
        }

        return result;
    }

    // ==========================================================================
    // Inner/Nested Classes
    // ==========================================================================
}
