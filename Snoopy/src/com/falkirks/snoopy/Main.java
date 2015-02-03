package com.falkirks.snoopy;


import com.falkirks.snoopy.gui.SnoopyGUI;
import com.falkirks.snoopy.lan.Subnet;
import com.falkirks.snoopy.service.Service;
import org.reflections.Reflections;

import java.net.*;
import java.util.ArrayList;
import java.util.Set;


public class Main {

    public static void main(String[] args) throws UnknownHostException, SocketException{
        /*InetAddress localHost = Inet4Address.getLocalHost();
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);

        for (InterfaceAddress address : networkInterface.getInterfaceAddresses()) {
            System.out.println(address + " " + address.getNetworkPrefixLength());
        }*/

        SnoopyGUI snoopyGUI = new SnoopyGUI();
        Subnet subnet = new Subnet("10.32.47");

        snoopyGUI.addPeers(subnet.getSubnetPeers());

        Reflections reflections = new Reflections("com.falkirks.snoopy.service");
        Set<Class<? extends Service>> classes = reflections.getSubTypesOf(Service.class);
        for(Class<? extends Service> serviceClass : classes){
            subnet.testAll(serviceClass);
            snoopyGUI.updatePeers();
        }
    }
}
