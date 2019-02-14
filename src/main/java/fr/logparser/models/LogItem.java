package fr.logparser.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class LogItem implements Serializable {

    private Long logTimestamp;
    private Instant logTime;
    private String serverHostname;
    private String connectedServerHostname;

    public Instant getLogTime() {
        return logTime;
    }

    public void setLogTime(Instant logTime) {
        this.logTime = logTime;
    }

    public String getServerHostname() {
        return serverHostname;
    }

    public void setServerHostname(String serverHostname) {
        this.serverHostname = serverHostname;
    }

    public String getConnectedServerHostname() {
        return connectedServerHostname;
    }

    public void setConnectedServerHostname(String connectedServerHostname) {
        this.connectedServerHostname = connectedServerHostname;
    }

    @Override
    public String toString() {
        return "LogItem{" +
                "logTime=" + logTime +
                ", serverHostname='" + serverHostname + '\'' +
                ", connectedServerHostname='" + connectedServerHostname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogItem logItem = (LogItem) o;
        return Objects.equals(getServerHostname(), logItem.getServerHostname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServerHostname());
    }

    public Long getLogTimestamp() {
        return logTimestamp;
    }

    public void setLogTimestamp(Long logTimestamp) {
        this.logTimestamp = logTimestamp;
    }
}
