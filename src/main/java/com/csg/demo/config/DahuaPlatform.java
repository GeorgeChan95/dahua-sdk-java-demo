package com.csg.demo.config;

public enum DahuaPlatform {
    WINDOWS64("windows64"),
    LINUX64("linux64"),
    UNSUPPORTED("unsupported");

    private final String configKey;

    DahuaPlatform(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigKey() {
        return configKey;
    }

    public boolean isSupported() {
        return this != UNSUPPORTED;
    }
}
