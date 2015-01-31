package com.falkirks.snoopy.lan;

import com.falkirks.snoopy.service.Service;

public class SubnetPeer {
    private String address;

    public SubnetPeer(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public boolean testService(Service service){
        service.runTest(this);
        return true;
    }

    @Override
    public String toString() {
        return "SubnetPeer{" +
                "address='" + address + '\'' +
                '}';
    }
}
