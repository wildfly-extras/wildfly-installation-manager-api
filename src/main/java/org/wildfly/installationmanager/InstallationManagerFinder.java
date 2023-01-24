package org.wildfly.installationmanager;

import org.wildfly.installationmanager.spi.InstallationManagerFactory;
import org.wildfly.security.manager.WildFlySecurityManager;

import java.util.Optional;
import java.util.ServiceLoader;

public class InstallationManagerFinder {

    public static Optional<InstallationManagerFactory> find(){
        final ClassLoader currentTccl = WildFlySecurityManager.getCurrentContextClassLoaderPrivileged();
        try {
            WildFlySecurityManager.setCurrentContextClassLoaderPrivileged(InstallationManagerFinder.class.getClassLoader());
            ServiceLoader<InstallationManagerFactory> sl = ServiceLoader.load(InstallationManagerFactory.class);
            for (InstallationManagerFactory imf : sl) {
               return Optional.of(imf);
            }
        } catch (Exception e) {
            // Cannot load InstallationManagerFactory service, ignored to return an empty Optional
        } finally {
            WildFlySecurityManager.setCurrentContextClassLoaderPrivileged(currentTccl);
        }

        return Optional.empty();
    }
}
