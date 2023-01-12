package org.wildfly.installationmanager;

import java.util.Optional;

/**
 * Represents a change in channel configuration.
 */
public class ChannelChange {

    public enum Status {ADDED, REMOVED, MODIFIED}

    private Optional<Channel> oldChannel;

    private Optional<Channel> newChannel;

    private final Status status;

    /**
     * constructs a channel change. At least one of the channels has to be non-null.
     * If both {@code oldChannel} and {@code newChannel} are provided their names have to be the same
     *
     * @param oldChannel
     * @param newChannel
     */
    public ChannelChange(Channel oldChannel, Channel newChannel, Status status) {
        this.status = status;
        this.oldChannel = Optional.ofNullable(oldChannel);
        this.newChannel = Optional.ofNullable(newChannel);

        if (this.oldChannel.isEmpty() && this.newChannel.isEmpty()) {
            throw new IllegalArgumentException("Both channels in change cannot be null");
        }

        if (this.oldChannel.isPresent() && this.newChannel.isPresent() &&
                !this.oldChannel.get().getName().equals(this.newChannel.get().getName())) {
            throw new IllegalArgumentException("Both channels has to have the same name");
        }
    }

    public Status getStatus() {
        return status;
    }

    /**
     * starting version of the changed channel.
     *
     * @return - Empty if a new channel was added to the installation, otherwise {@code Channel}
     */
    public Optional<Channel> getOldChannel() {
        return oldChannel;
    }

    /**
     * final version of the changed channel.
     *
     * @return - Empty if the change was removed from the installation, otherwise {@code Channel}
     */
    public Optional<Channel> getNewChannel() {
        return newChannel;
    }

    /**
     * name of the modified channel
     *
     * @return
     */
    public String getName() {
        return newChannel.isPresent() ? newChannel.get().getName() : oldChannel.get().getName();
    }
}
