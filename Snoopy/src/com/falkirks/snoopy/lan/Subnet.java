package com.falkirks.snoopy.lan;

import com.falkirks.snoopy.service.Service;
import org.apache.commons.net.util.SubnetUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Subnet {
    private ArrayList<SubnetPeer> subnetPeers;

    public ArrayList<SubnetPeer> getSubnetPeers() {
        return subnetPeers;
    }

    public Subnet() {
        subnetPeers = findHosts();
    }
    public void testAll(Service service){
        for(SubnetPeer peer: subnetPeers){
            peer.testService(service);
        }
    }
    public void testAll(Class<? extends Service> serviceClass){
        for(SubnetPeer peer: subnetPeers){
            peer.testService(serviceClass);
        }
    }
    public static ArrayList<SubnetPeer> findHosts(){
        try {
            HashMap<String, Future<Boolean>> futureList = new HashMap<String, Future<Boolean>>();
            ExecutorService executor = Executors.newFixedThreadPool(100);
            InetAddress localHost = Inet4Address.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);

            String[] addresses = new String[0];

            for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                if (!subnetStringFromMask(interfaceAddress.getNetworkPrefixLength()).equals("255.255.255.255")) {
                    SubnetUtils utils = new SubnetUtils(Inet4Address.getLocalHost().getHostAddress(), subnetStringFromMask(interfaceAddress.getNetworkPrefixLength()));
                    addresses = utils.getInfo().getAllAddresses();
                }
            }
            for (String address: addresses){
                futureList.put(address, executor.submit(new PeerPingTask(address)));
            }
            executor.shutdown();
            while (!executor.isTerminated()) ;

            ArrayList<SubnetPeer> peers = new ArrayList<SubnetPeer>();
            for (String address: addresses){
                try {
                    if (futureList.get(address).get()) {
                        peers.add(new SubnetPeer(address));
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return peers;
        }
        catch(Exception e){
            return null;
        }

    }
    public static String subnetStringFromMask(int subnetMask){
        String out = "";
        for(int i = 0; i < 4; i++){
            if(subnetMask >= 8){
                out += ".255";
            }
            else{
                out += ".0";
            }
            subnetMask = subnetMask-8;
        }
        return out.substring(1);
    }

}
