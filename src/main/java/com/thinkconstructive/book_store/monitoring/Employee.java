package com.thinkconstructive.book_store.monitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Component
public class Employee implements HealthIndicator {

    @Autowired
    private Environment env;

    @Override
    public Health health() {
        try {
            if(isServiceUp()) {
                return Health.up().withDetail("Employee Service", "Service is up and running").build();
            } else {
                return Health.down().withDetail("Employee Service", "Service is down").build();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isServiceUp() throws IOException {
        String address = env.getProperty("EmployeeService.address");
        String port = env.getProperty("EmployeeService.port");
        return isAddressReachable(address, Integer.parseInt(port), 3000);
    }

    private static boolean isAddressReachable(String address, int port, int timeout) throws IOException {
        Socket isSocket = new Socket();
        try {
            //connect this socket to the server with a specified timeout value
            isSocket.connect(new InetSocketAddress(address, port), timeout);
            //Return true if connection successful
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            //Return false if connection fails
            return false;
        } finally {
            isSocket.close();
        }
    }
}
