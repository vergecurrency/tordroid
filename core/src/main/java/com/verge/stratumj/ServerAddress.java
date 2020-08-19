package com.vergepay.stratumj;

import java.net.Proxy;

/**
 * @author John L. Jegutanis
 */
final public class ServerAddress {
    final private String host;
    final private int port;
    final private Proxy proxy;

    public ServerAddress(String host, int port) {
        this.host = host;
        this.port = port;
        this.proxy = null;
    }

    public ServerAddress(String host, int port, Proxy proxy) {
        this.host = host;
        this.port = port;
        this.proxy = proxy;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "ServerAddress{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }

    public Proxy getProxy() {
        return proxy;
    }
}
