/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2023 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
