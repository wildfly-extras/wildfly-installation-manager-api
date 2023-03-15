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

import java.nio.file.Path;
import java.nio.file.Paths;

public class MavenOptions {
    public static final Path LOCAL_MAVEN_REPO = Paths.get(System.getProperty("user.home"), ".m2", "repository");

    private final Path localRepository;
    private final boolean offline;


    public MavenOptions(Path localRepository, boolean offline) {
        this(localRepository, false, offline);
    }

    public MavenOptions(Path localRepository, boolean noResolveLocalCache, boolean offline) {
        if (noResolveLocalCache) {
            this.localRepository = null;
        } else {
            this.localRepository = localRepository == null ? LOCAL_MAVEN_REPO : localRepository;
        }
        this.offline = offline;
    }

    public Path getLocalRepository() {
        return localRepository;
    }

    public boolean isOffline() {
        return offline;
    }
}
