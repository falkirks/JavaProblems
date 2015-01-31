package com.falkirks.snoopy.service;

@ServicePort(ports = {80, 8080, 7000, 3000, 8000})
public class Website extends Service{
    @Override
    boolean runExtendedTests() {
        return true;
    }
}

