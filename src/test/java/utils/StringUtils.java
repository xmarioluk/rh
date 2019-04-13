package utils;

import java.util.UUID;

public final class StringUtils {

    public static final String LONG_TITLE = "sEy0itGYgF..."; // 16384 characters
    public static final String TOO_LONG_TITLE = "BsEy0itGYg..."; // 16385 characters
    public static final String UNICODE_TITLE = "觻۩센 䋽軍휴x�𘦄枢g񮗎焖xɌ䒹`̍ꥷ疽Ǧ󣬯-āỒ򪜒櫿ۨ퐠󵥖@"; // multi-byte unicode
    public static final String DANGEROUS_TITLE = "& < > / \\ ' ` \" ? + # % * , - ; = ^";

    private StringUtils() {}

    public static String getUniqueId(String prefix) {
        return prefix + "-" + UUID.randomUUID();
    }

    public static String getIdFromUrl(String url) {
        String[] matches = url.split("/");
        return matches[matches.length - 2];
    }

    public static String getFileNameFromPath(String path) {
        String[] matches = path.split("\\\\");
        return matches[matches.length - 1];
    }

    public static String escapeXPath(String xpath) {
        return xpath.replace("'", "''");
    }
}