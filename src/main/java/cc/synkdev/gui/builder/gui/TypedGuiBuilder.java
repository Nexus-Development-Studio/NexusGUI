package cc.synkdev.gui.builder.gui;

import cc.synkdev.gui.components.GuiContainer;
import cc.synkdev.gui.components.GuiType;
import cc.synkdev.gui.components.InventoryProvider;
import cc.synkdev.gui.components.util.Legacy;
import cc.synkdev.gui.guis.Gui;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * The simple GUI builder is used for creating a {@link Gui}
 */
public final class TypedGuiBuilder extends BaseGuiBuilder<Gui, TypedGuiBuilder> {

    private GuiType guiType;
    private InventoryProvider.Typed inventoryProvider =
        (title, owner, type) -> Bukkit.createInventory(owner, type, Legacy.SERIALIZER.serialize(title));

    /**
     * Main constructor
     *
     * @param guiType The {@link GuiType} to default to
     */
    public TypedGuiBuilder(final @NotNull GuiType guiType) {
        this.guiType = guiType;
    }

    public TypedGuiBuilder(final @NotNull GuiType guiType, final @NotNull ChestGuiBuilder builder) {
        this.guiType = guiType;
        consumeBuilder(builder);
    }

    /**
     * Sets the {@link GuiType} to use on the GUI
     * This method is unique to the simple GUI
     *
     * @param guiType The {@link GuiType}
     * @return The current builder
     */
    @NotNull
    @Contract("_ -> this")
    public TypedGuiBuilder type(final @NotNull GuiType guiType) {
        this.guiType = guiType;
        return this;
    }

    @NotNull
    @Contract("_ -> this")
    public TypedGuiBuilder inventory(@NotNull final InventoryProvider.Typed inventoryProvider) {
        this.inventoryProvider = inventoryProvider;
        return this;
    }

    /**
     * Creates a new {@link Gui}
     *
     * @return A new {@link Gui}
     */
    @NotNull
    @Override
    @Contract(" -> new")
    public Gui create() {
        final Gui gui = new Gui(new GuiContainer.Typed(getTitle(), inventoryProvider, guiType), getModifiers());
        final Consumer<Gui> consumer = getConsumer();
        if (consumer != null) consumer.accept(gui);
        return gui;
    }

}
