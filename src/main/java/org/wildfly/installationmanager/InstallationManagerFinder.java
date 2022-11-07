package org.wildfly.installationmanager;

import org.wildfly.installationmanager.spi.InstallationManager;
import org.wildfly.security.manager.WildFlySecurityManager;

import java.util.Optional;
import java.util.ServiceLoader;

public class InstallationManagerFinder {

    public static Optional<InstallationManager> find(){
        final ClassLoader currentTccl = WildFlySecurityManager.getCurrentContextClassLoaderPrivileged();
        try {
            WildFlySecurityManager.setCurrentContextClassLoaderPrivileged(InstallationManagerFinder.class.getClassLoader());
            ServiceLoader<InstallationManager> sl = ServiceLoader.load(InstallationManager.class);
            for (org.wildfly.installationmanager.spi.InstallationManager iml : sl) {
               return Optional.of(iml);
            }
        } finally {
            WildFlySecurityManager.setCurrentContextClassLoaderPrivileged(currentTccl);
        }

        return Optional.empty();
    }
}
