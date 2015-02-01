package com.falkirks.snoopy.service;


import com.falkirks.snoopy.lan.SubnetPeer;

import java.io.IOException;
import java.net.Socket;

abstract public class Service {
    public boolean runTest(SubnetPeer subnetPeer){
        ReliablePorts reliablePorts = this.getClass().getAnnotation(ReliablePorts.class);
        if(reliablePorts != null) {
            for (int port : reliablePorts.ports()) {
                if (testPort(subnetPeer.getAddress(), port)) {
                    return runExtendedTests();
                }
            }
            return false;
        }
        return true;
    }
    protected boolean testPort(String address, int port){
        Socket socket = null;
        try {
            socket = new Socket(address, port);
            return true;
        }
        catch(IOException e){
            return false;
        }
        finally {
            if (socket != null) try { socket.close(); } catch(IOException e) {}
        }
    }
    public String getName(){
        return this.getClass().getSimpleName();
    }
    abstract boolean runExtendedTests();
}
