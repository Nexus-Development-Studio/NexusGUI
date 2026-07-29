package cc.synkdev.gui.builder.gui;

import cc.synkdev.gui.guis.StorageGui;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * The simple GUI builder is used for creating a {@link StorageGui}
 */
public final class StorageBuilder extends BaseChestGuiBuilder<StorageGui, StorageBuilder> {

    /**
     * Creates a new {@link StorageGui}
     *
     * @return A new {@link StorageGui}
     */
    @NotNull
    @Override
    @Contract(" -> new")
    public StorageGui create() {
        final StorageGui gui = new StorageGui(createContainer(), getModifiers());

        final Consumer<StorageGui> consumer = getConsumer();
        if (consumer != null) consumer.accept(gui);

        return gui;
    }

}
