package cc.synkdev.gui.builder.gui;

import cc.synkdev.gui.guis.PaginatedGui;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * GUI builder for creating a {@link PaginatedGui}
 */
public class PaginatedBuilder extends BaseChestGuiBuilder<PaginatedGui, PaginatedBuilder> {

    private int pageSize = 0;

    /**
     * Sets the desirable page size, most of the time this isn't needed
     *
     * @param pageSize The amount of free slots that page items should occupy
     * @return The current builder
     */
    @NotNull
    @Contract("_ -> this")
    public PaginatedBuilder pageSize(final int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * Creates a new {@link PaginatedGui}
     *
     * @return A new {@link PaginatedGui}
     */
    @NotNull
    @Override
    @Contract(" -> new")
    public PaginatedGui create() {
        final PaginatedGui gui = new PaginatedGui(createContainer(), pageSize, getModifiers());

        final Consumer<PaginatedGui> consumer = getConsumer();
        if (consumer != null) consumer.accept(gui);

        return gui;
    }

}
