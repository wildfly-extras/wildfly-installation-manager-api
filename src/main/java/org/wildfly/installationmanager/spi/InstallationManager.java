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

import org.wildfly.installationmanager.InstallationChanges;
import org.wildfly.installationmanager.Channel;
import org.wildfly.installationmanager.HistoryResult;
import org.wildfly.installationmanager.ArtifactChange;
import org.wildfly.installationmanager.ManifestVersion;
import org.wildfly.installationmanager.OperationNotAvailableException;
import org.wildfly.installationmanager.Repository;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface InstallationManager {
    /**
     * Return all changes from the managed server history.
     *
     * @return A list of changes, or an empty list if no changes were found.
     * @throws Exception In case of an error.
     */
    List<HistoryResult> history() throws Exception;

    /**
     * Return description of a changes since {@code revision} from the managed server history.
     *
     * @param revision hash of a revision record to be reverted to. Cannot be {@code null}.
     * @return An InstallationChanges instance including non-null lists that represent Artifacts and Channels changes.
     * If there are no changes, the corresponding list will be empty.
     * @throws Exception In case of an error.
     */
    InstallationChanges revisionDetails(String revision) throws Exception;

    /**
     * Prepares a reverted version of the server installation in {@code candidatePath}.
     *
     * @param revision      hash of a revision record to be reverted to.
     * @param candidatePath {@code Path} were the updated version of the server should be located.
     * @param repositories  List of repositories to be used to prepare this update.I f it is null or an empty list,
     *                      the default repositories will be used instead.
     * @throws Exception In case of an error.
     */
    void prepareRevert(String revision, Path candidatePath, List<Repository> repositories) throws Exception;

    /**
     * Prepares an updated version of the server installation in {@code candidatePath}.
     * If no updates are found, this operation does nothing.
     *
     * @param candidatePath {@code Path} were the updated version of the server should be located.
     * @param repositories  List of repositories to be used to prepare this update.If it is null or an empty list,
     *                      the default repositories will be used instead.
     * @return true if the update candidate was generated, false if candidate was no generated due to not finding any pending updates
     * @throws IllegalArgumentException if the Path is not writable.
     * @throws Exception                In case of an error.
     */
    boolean prepareUpdate(Path candidatePath, List<Repository> repositories) throws Exception;

    /**
     * Lists updates available for the server installation.
     *
     * @param repositories List of repositories to be used to find the available updates. If it is null or an empty list,
     *                     the default repositories will be used instead.
     * @return list of {@code ArtifactChange} available for update.
     * @throws Exception In case of an error.
     */
    List<ArtifactChange> findUpdates(List<Repository> repositories) throws Exception;

    /**
     * Lists channels the server installation is subscribed to.
     * If the servers is not subscribed to any channels, empty list is returned.
     *
     * @return Collection of {@code Channel}
     * @throws Exception - if unable to read the installation metadata
     */
    Collection<Channel> listChannels() throws Exception;

    /**
     * Unsubscribes the server installation from a channel.
     *
     * @param channelName - name of the channel to be removed.
     * @throws Exception - if unable to read the installation metadata, or the Channel index doesn't exist
     */
    void removeChannel(String channelName) throws Exception;

    /**
     * Subscribe the server installation to a new channel.
     *
     * @param channel - new {@code Channel}
     * @throws Exception - if unable to read the installation metadata, or the Channel index doesn't exist
     */
    void addChannel(Channel channel) throws Exception;

    /**
     * Persists changes to a channel that the server installation is subscribed to.
     *
     * @param channel - modified {@code Channel} to be stored.
     * @throws Exception - if the channel has no name defined or the channel name doesn't match any already present channels.
     */
    void changeChannel(Channel channel) throws Exception;

    /**
     * Create a snapshot of installation's metadata and exports it as a zip bundle in {@code targetPath}.
     *
     * @param targetPath - path of the exported zip. If the path points to a directory, a file "im-snapshot-<TIMESTAMP>.zip" will be created
     * @return path to the exported snapshot zip
     * @throws IllegalArgumentException if the file already exist.
     * @throws Exception                In case of an error.
     */
    Path createSnapshot(Path targetPath) throws Exception;

    /**
     * Generate an apply update CLI command.
     * The generated command can be run in separate process to apply changes.
     *
     * @param candidatePath - Specify the directory path of the candidate installation to apply.
     * @param scriptHome - Specify the directory path containing the script used to execute the apply command.
     * @return a CLI command.
     * @throws OperationNotAvailableException - if the installation manager CLI support is not installed
     */
    @Deprecated
    String generateApplyUpdateCommand(Path scriptHome, Path candidatePath) throws OperationNotAvailableException;

    /**
     * Generate an apply rollback CLI command.
     * The generated command can be run in separate process to apply changes.
     *
     * @param candidatePath - Specify the directory path of the candidate installation to apply.
     * @param scriptHome - Specify the directory path containing the script used to execute the apply command.
     * @return a CLI command.
     * @throws OperationNotAvailableException - if the installation manager CLI support is not installed
     */
    @Deprecated
    String generateApplyRevertCommand(Path scriptHome, Path candidatePath) throws OperationNotAvailableException;

    /**
     * Generate an apply update CLI command.
     * The generated command can be run in separate process to apply changes.
     *
     * @param candidatePath - Specify the directory path of the candidate installation to apply.
     * @param scriptHome - Specify the directory path containing the script used to execute the apply command.
     * @param shell - Specify what shell should the script be runnable in
     * @return a CLI command.
     * @throws OperationNotAvailableException - if the installation manager CLI support is not installed
     */
    String generateApplyUpdateCommand(Path scriptHome, Path candidatePath, OsShell shell) throws OperationNotAvailableException;

    /**
     * Generate an apply rollback CLI command.
     * The generated command can be run in separate process to apply changes.
     *
     * @param candidatePath - Specify the directory path of the candidate installation to apply.
     * @param scriptHome - Specify the directory path containing the script used to execute the apply command.
     * @param shell - Specify what shell should the script be runnable in
     * @return a CLI command.
     * @throws OperationNotAvailableException - if the installation manager CLI support is not installed
     */
    String generateApplyRevertCommand(Path scriptHome, Path candidatePath, OsShell shell) throws OperationNotAvailableException;

    /**
     * Reports latest versions of manifests used in latest update/install operation for each registered channels.
     * @return list of {@link ManifestVersion}s
     */
    Collection<ManifestVersion> getInstalledVersions();
}
