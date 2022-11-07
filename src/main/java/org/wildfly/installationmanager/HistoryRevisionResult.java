package org.wildfly.installationmanager;

public class HistoryRevisionResult {
    public enum Status {UPDATED, INSTALLED, REMOVED}

    private final String oldVersion;
    private final String newVersion;
    private final String oldGav;
    private final String newGav;
    private final Status status;

    public HistoryRevisionResult(String oldVersion, String newVersion, String oldGav, String newGav, Status status) {
        this.oldVersion = oldVersion;
        this.newVersion = newVersion;
        this.oldGav = oldGav;
        this.newGav = newGav;
        this.status = status;
    }

    public String getOldVersion() {
        return oldVersion;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public String getOldGav() {
        return oldGav;
    }

    public String getNewGav() {
        return newGav;
    }

    public Status getStatus() {
        return status;
    }
}
