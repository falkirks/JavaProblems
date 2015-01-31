package com.falkirks.snoopy.lan;

import java.net.InetAddress;
import java.util.concurrent.Callable;

public class PeerPingTask implements Callable<Boolean> {
    public static final int PING_TIMEOUT = 1000;
    private String address;
    public PeerPingTask(String address) {
        this.address = address;
    }

    @Override
    public Boolean call() throws Exception {
        //System.out.println("Pinging " + address);
        return InetAddress.getByName(address).isReachable(PING_TIMEOUT);
    }
}
