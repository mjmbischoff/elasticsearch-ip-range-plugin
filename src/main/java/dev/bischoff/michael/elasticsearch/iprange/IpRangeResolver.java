package dev.bischoff.michael.elasticsearch.iprange;

import org.elasticsearch.common.network.CIDRUtils;
import org.elasticsearch.common.network.NetworkService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;

public class IpRangeResolver implements NetworkService.CustomNameResolver {
    @Override
    public InetAddress[] resolveDefault() {
        return null; // we don't have a default
    }

    @Override
    public InetAddress[] resolveIfPossible(String entry) throws IOException {
        if(!entry.startsWith("range:")) return null;
        String range = entry.substring(6);
        // use NetworkUtils.getInterfaces()
        InetAddress[] addresses = Collections.list(NetworkInterface.getNetworkInterfaces()).stream()
                .flatMap(networkInterface -> Collections.list(networkInterface.getInetAddresses()).stream())
                .filter(address -> inRange(address, range))
                .toArray(InetAddress[]::new);

        return addresses.length > 0 ? addresses : null;
    }

    private boolean inRange(InetAddress address, String range) {
        return CIDRUtils.isInRange(address.getHostAddress(), range);
    }
}
