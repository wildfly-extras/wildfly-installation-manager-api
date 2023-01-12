package org.wildfly.installationmanager;

import java.time.Instant;
import java.util.Objects;

public class HistoryResult {

    private String hash;
    private Instant timestamp;
    private String type;

    public HistoryResult(String hash, Instant timestamp, String type) {
        this.hash = hash;
        this.timestamp = timestamp;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return this.hash;
    }

    public Instant timestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryResult that = (HistoryResult) o;
        return Objects.equals(hash, that.hash) && Objects.equals(timestamp, that.timestamp) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, timestamp, type);
    }
}
