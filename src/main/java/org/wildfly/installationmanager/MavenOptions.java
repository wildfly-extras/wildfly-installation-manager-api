package org.wildfly.installationmanager;

import java.nio.file.Path;

public class MavenOptions {
    private Path localRepository;
    private boolean offline;

    public MavenOptions(Path localRepository, boolean offline) {
        this.localRepository = localRepository;
        this.offline = offline;
    }

    public Path getLocalRepository() {
        return localRepository;
    }

    public boolean isOffline() {
        return offline;
    }
}
