package org.wildfly.installationmanager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MavenOptions {
    public static final Path LOCAL_MAVEN_REPO = Paths.get(System.getProperty("user.home"), ".m2", "repository");

    private Path localRepository;
    private boolean offline;


    public MavenOptions(Path localRepository, boolean offline) {
        this.localRepository = localRepository == null ? LOCAL_MAVEN_REPO : localRepository;
        this.offline = offline;
    }

    public Path getLocalRepository() {
        return localRepository;
    }

    public boolean isOffline() {
        return offline;
    }
}
