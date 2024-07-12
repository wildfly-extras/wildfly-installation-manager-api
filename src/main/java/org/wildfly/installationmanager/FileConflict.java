/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2024 Red Hat, Inc., and individual contributors
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
import java.util.Objects;

/**
 * Represents a file modified by both the update and by the user. Such files cause a conflict and are resolved using
 * a backup file to preserve either user or update changes.
 */
public class FileConflict {

    /**
     * Describes the change to the file - it can be modified, removed, added, or not-existing (e.g. when a new file was added by user
     * and doesn't exist in the update).
     */
    public enum Status {
        MODIFIED,
        REMOVED,
        ADDED,
        NONE }
    private Path relativePath;
    private Status userChanges;
    private Status updateChanges;
    private boolean overwritten;

    public FileConflict(Path relativePath, Status userChanges, Status updateChanges, boolean overwritten) {
        this.relativePath = relativePath;
        this.userChanges = userChanges;
        this.updateChanges = updateChanges;
        this.overwritten = overwritten;
    }

    /**
     * File's Relative path to the server root
     */
    public Path getRelativePath() {
        return relativePath;
    }

    /**
     * The change to the current copy of the file, i.e. compared to previous installation/update state.
     */
    public Status getUserChanges() {
        return userChanges;
    }

    /**
     * The change performed in the update compared to the previous installation/update state.
     */
    public Status getUpdateChanges() {
        return updateChanges;
    }

    /**
     * Whether the user or update changes have been used to resolve conflict. Set to true if the update state overrides user changes, false otherwise.
     */
    public boolean isOverwritten() {
        return overwritten;
    }

    @Override
    public String toString() {
        return "FileConflict{" +
                "relativePath=" + relativePath +
                ", userChanges=" + userChanges +
                ", updateChanges=" + updateChanges +
                ", overwritten=" + overwritten +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileConflict that = (FileConflict) o;
        return overwritten == that.overwritten && Objects.equals(relativePath, that.relativePath) && userChanges == that.userChanges && updateChanges == that.updateChanges;
    }

    @Override
    public int hashCode() {
        return Objects.hash(relativePath, userChanges, updateChanges, overwritten);
    }
}
