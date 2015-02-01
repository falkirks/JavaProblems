package com.falkirks.snoopy.service;


@ReliablePorts(ports = {22})
public class SSH extends Service {
    @Override
    boolean runExtendedTests() {
        return true;
    }
}
