package com.falkirks.snoopy.lan;

import com.falkirks.snoopy.service.Service;

import java.util.ArrayList;

public class SubnetPeer {
    private String address;
    private ArrayList<Service> services;

    public SubnetPeer(String address) {
        this.address = address;
        this.services = new ArrayList<Service>();
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public boolean testService(Service service){
        if(service.runTest(this)){
            services.add(service);
            return true;
        }
        return false;
    }
    public boolean testService(Class<? extends Service> serviceClass){
        try {
            return testService(serviceClass.newInstance());
        }
        catch(IllegalAccessException e){
            return false;
        }
        catch(InstantiationException e){
            return false;
        }
    }
    public String getServiceList(){
        String out = "";
        for(Service service : services){
            out += service.getName() + ",";
        }
        return (out.length() > 0 ? out.substring(0, out.length()-1) : out);
    }
    @Override
    public String toString() {
        return address + ": " + " ping, " + getServiceList();
    }
}
