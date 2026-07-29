package cc.synkdev.gui.builder.gui;

import cc.synkdev.gui.components.GuiType;
import cc.synkdev.gui.guis.Gui;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * The simple GUI builder is used for creating a {@link Gui}
 */
public final class ChestGuiBuilder extends BaseChestGuiBuilder<Gui, ChestGuiBuilder> {

    /**
     * Sets the {@link GuiType} to use on the GUI
     * This method is unique to the simple GUI
     *
     * @param guiType The {@link GuiType}
     * @return The current builder
     */
    @NotNull
    @Contract("_ -> new")
    public TypedGuiBuilder type(@NotNull final GuiType guiType) {
        return new TypedGuiBuilder(guiType, this);
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
        final Gui gui = new Gui(createContainer(), getModifiers());
        final Consumer<Gui> consumer = getConsumer();
        if (consumer != null) consumer.accept(gui);
        return gui;
    }
}
