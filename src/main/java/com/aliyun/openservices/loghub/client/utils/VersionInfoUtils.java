package com.aliyun.openservices.loghub.client.utils;

import java.io.InputStream;
import java.util.Properties;

public class VersionInfoUtils {
    private static final String VERSION_INFO_FILE = "loghub-versioninfo.properties";
    private static final String USER_AGENT_PREFIX = "aliyun-log-consumer-java";

    private static String version = null;

    private static String defaultUserAgent = null;

    private static String getVersion() {
        if (version == null) {
            initializeVersion();
        }
        return version;
    }

    public static String getDefaultUserAgent() {
        if (defaultUserAgent == null) {
            defaultUserAgent = USER_AGENT_PREFIX + "-" + getVersion()+"/" + System.getProperty("java.version");
        }
        return defaultUserAgent;
    }

    private static void initializeVersion() {
        InputStream inputStream = VersionInfoUtils.class.getClassLoader().getResourceAsStream(VERSION_INFO_FILE);
        Properties versionInfoProperties = new Properties();
        try {
            if (inputStream == null) {
                throw new IllegalArgumentException(VERSION_INFO_FILE + " not found on classpath");
            }
            versionInfoProperties.load(inputStream);
            version = versionInfoProperties.getProperty("version");
        } catch (Exception e) {
            version = "unknown-version";
        }
    }
}
