package com.example.minecraft.prometheus;

import com.example.minecraft.query.MCQuery;
import com.example.minecraft.query.QueryResponse;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

@Component
public class MetricChanger {

    @Autowired
    CollectorRegistry registry;

    private QueryResponse queryResponse;

    private Gauge onlinePlayer;

    private Map<String, Gauge> players;

    @Value("${mc.address}")
    private String address;

    @Value("${mc.query_port}")
    private int queryPort;

    @PostConstruct
    public void init() {
        onlinePlayer = Gauge.build("online_player", "online player").register(registry);
    }

    public void run() throws IOException {
        queryResponse = new MCQuery(address, queryPort).fullStat();
        updateOnlinePlayer();
    }

    public void updateOnlinePlayer() {
        onlinePlayer.set(queryResponse.getOnlinePlayers());
    }

}
