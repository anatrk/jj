package com.anattests.test1good;

public class EmailService {

    private final String host;
    private final int port;

    public EmailService(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void send(String to, String subject, String body) {
        // Stub: no real SMTP delivery, just log what would be sent
        System.out.println("[mail " + host + ":" + port + "] to=" + to + " subject=" + subject + " body=" + body);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
