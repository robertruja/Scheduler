package com.gigi.scheduler.util;


import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by coco on 25-Jan-18.
 */

public class Config {

    private static final String configFileLocation = "configFileLocation";

    private Map<String,String> headers;

    private String mainUrl;
    private String urlRoot;
    private String oddUrlPrefix;
    private String oddUrlSuffix;

    private long minRequestInterval;
    private String jdbcDriverClass;
    private String jdbcURL;
    private String jdbcUserName;
    private String jdbcPassword;
    private String eventsTableName;
    private short maxMockRequestInterval;
    private int groupRequestInterval;
    private int maxGroupRequestAmount;
    private String oddsTableName;

    @PostConstruct
    public void load() {

        String path = System.getProperty(configFileLocation);

        if(path == null) {
            throw new RuntimeException("No configuration found as System property for flash");
        }
        try {
            Config config = FileUtils.readJsonFromFile(path, getClass());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public long getMinRequestInterval() {
        return minRequestInterval;
    }

    public String getOddUrlPrefix() {
        return oddUrlPrefix;
    }

    public String getOddUrlSuffix() {
        return oddUrlSuffix;
    }

    public String getJdbcDriverClass() {
        return jdbcDriverClass;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public String getJdbcUserName() {
        return jdbcUserName;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public String getEventsTableName() {
        return eventsTableName;
    }

    public short getMaxMockRequestInterval() {
        return maxMockRequestInterval;
    }

    public int getGroupRequestInterval() {
        return groupRequestInterval;
    }

    public int getMaxGroupRequestAmount() {
        return maxGroupRequestAmount;
    }

    public String getOddsTableName() {
        return oddsTableName;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setMainUrl(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public void setUrlRoot(String urlRoot) {
        this.urlRoot = urlRoot;
    }

    public void setOddUrlPrefix(String oddUrlPrefix) {
        this.oddUrlPrefix = oddUrlPrefix;
    }

    public void setOddUrlSuffix(String oddUrlSuffix) {
        this.oddUrlSuffix = oddUrlSuffix;
    }

    public void setMinRequestInterval(long minRequestInterval) {
        this.minRequestInterval = minRequestInterval;
    }

    public void setJdbcDriverClass(String jdbcDriverClass) {
        this.jdbcDriverClass = jdbcDriverClass;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }

    public void setJdbcUserName(String jdbcUserName) {
        this.jdbcUserName = jdbcUserName;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public void setEventsTableName(String eventsTableName) {
        this.eventsTableName = eventsTableName;
    }

    public void setMaxMockRequestInterval(short maxMockRequestInterval) {
        this.maxMockRequestInterval = maxMockRequestInterval;
    }

    public void setGroupRequestInterval(int groupRequestInterval) {
        this.groupRequestInterval = groupRequestInterval;
    }

    public void setMaxGroupRequestAmount(int maxGroupRequestAmount) {
        this.maxGroupRequestAmount = maxGroupRequestAmount;
    }

    public void setOddsTableName(String oddsTableName) {
        this.oddsTableName = oddsTableName;
    }
}
