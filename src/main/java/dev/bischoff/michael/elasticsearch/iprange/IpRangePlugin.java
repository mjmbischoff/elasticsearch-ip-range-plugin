package dev.bischoff.michael.elasticsearch.iprange;

import org.elasticsearch.common.network.NetworkService;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.DiscoveryPlugin;
import org.elasticsearch.plugins.Plugin;

public class IpRangePlugin extends Plugin implements DiscoveryPlugin {

    @Override
    public NetworkService.CustomNameResolver getCustomNameResolver(Settings settings) {
        return new IpRangeResolver();
    }
}
