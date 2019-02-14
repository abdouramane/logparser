package fr.logparser.models;

import java.util.HashSet;
import java.util.Set;

public class Server {

    private String name;
    private Set<String> connectedServers;
    private Integer numberOfConnections = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getConnectedServers() {
        if(connectedServers == null) {
            connectedServers = new HashSet<>();
        }

        return connectedServers;
    }

    public void setConnectedServers(Set<String> connectedServers) {
        this.connectedServers = connectedServers;
    }

    public Integer getNumberOfConnections() {
        return numberOfConnections;
    }

    public void setNumberOfConnections(Integer numberOfConnections) {
        this.numberOfConnections = numberOfConnections;
    }

    public void updateAmoutOfConnection() {
        this.numberOfConnections++;
    }

    @Override
    public String toString() {
        return "Server{" +
                "name='" + name + '\'' +
                ", connectedServers=" + connectedServers +
                ", numberOfConnections=" + numberOfConnections +
                '}';
    }
}
