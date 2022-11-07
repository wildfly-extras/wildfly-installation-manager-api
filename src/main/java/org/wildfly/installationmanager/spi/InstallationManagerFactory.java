package org.wildfly.installationmanager.spi;

import org.wildfly.installationmanager.MavenOptions;

import java.nio.file.Path;

public interface InstallationManagerFactory {
    /**
     * Creates InstallationManger for server instance located at {@code installationDir}.
     * If the provided directory is empty, the manger needs to install the server before attempting any other operations.
     *
     * @param installationDir
     * @param mavenOptions
     * @return
     */
    InstallationManager create(Path installationDir, MavenOptions mavenOptions) throws Exception;

    /**
     * Gets the name of InstallatioManager instance
     * @return
     */
    String getName();
}
