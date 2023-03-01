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
     * @param callback
     * @return
     */
    InstallationManager create(Path installationDir, MavenOptions mavenOptions, ProgressCallback callback) throws Exception;

    /**
     * Gets the name of InstallationManager instance
     * @return
     */
    String getName();
}
