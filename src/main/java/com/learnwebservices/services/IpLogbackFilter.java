package com.learnwebservices.services;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.MDC;
import org.slf4j.Marker;

import java.util.Arrays;
import java.util.List;

public class IpLogbackFilter extends TurboFilter {

    private List<String> ignoredIps = List.of();

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object[] objects, Throwable throwable) {
        String remoteIp = MDC.get(SetMdcFilter.REMOTE_IP_KEY);
        if (remoteIp != null && ignoredIps.contains(remoteIp)) {
            return FilterReply.DENY;
        }
        else {
            return FilterReply.NEUTRAL;
        }
    }

    public void setIgnoredIps(String ips) {
        if (!ips.isEmpty()) {
            ignoredIps = Arrays.asList(ips.split(","));
        }
    }
}
