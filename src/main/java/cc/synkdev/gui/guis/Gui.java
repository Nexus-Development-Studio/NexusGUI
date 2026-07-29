package cc.synkdev.gui.guis;

import cc.synkdev.gui.builder.gui.PaginatedBuilder;
import cc.synkdev.gui.builder.gui.ScrollingBuilder;
import cc.synkdev.gui.builder.gui.ChestGuiBuilder;
import cc.synkdev.gui.builder.gui.StorageBuilder;
import cc.synkdev.gui.builder.gui.TypedGuiBuilder;
import cc.synkdev.gui.components.GuiContainer;
import cc.synkdev.gui.components.GuiType;
import cc.synkdev.gui.components.InteractionModifier;
import cc.synkdev.gui.components.ScrollType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Standard GUI implementation of {@link BaseGui}
 */
public class Gui extends BaseGui {

    public Gui(final @NotNull GuiContainer guiContainer, final @NotNull Set<InteractionModifier> interactionModifiers) {
        super(guiContainer, interactionModifiers);
    }

    /**
     * Creates a {@link TypedGuiBuilder} to build a {@link Gui}
     *
     * @param type The {@link GuiType} to be used
     * @return A {@link TypedGuiBuilder}
     * @since 3.0.0
     */
    @Contract("_ -> new")
    public static @NotNull TypedGuiBuilder gui(final @NotNull GuiType type) {
        return new TypedGuiBuilder(type);
    }

    /**
     * Creates a {@link ChestGuiBuilder} with CHEST as the {@link GuiType}
     *
     * @return A CHEST {@link ChestGuiBuilder}
     * @since 3.0.0
     */
    @Contract(" -> new")
    public static @NotNull ChestGuiBuilder gui() {
        return new ChestGuiBuilder();
    }

    /**
     * Creates a {@link StorageBuilder}.
     *
     * @return A CHEST {@link StorageBuilder}.
     * @since 3.0.0.
     */
    @Contract(" -> new")
    public static @NotNull StorageBuilder storage() {
        return new StorageBuilder();
    }

    /**
     * Creates a {@link PaginatedBuilder} to build a {@link PaginatedGui}
     *
     * @return A {@link PaginatedBuilder}
     * @since 3.0.0
     */
    @Contract(" -> new")
    public static @NotNull PaginatedBuilder paginated() {
        return new PaginatedBuilder();
    }

    /**
     * Creates a {@link ScrollingBuilder} to build a {@link ScrollingGui}
     *
     * @param scrollType The {@link ScrollType} to be used by the GUI
     * @return A {@link ScrollingBuilder}
     * @since 3.0.0
     */
    @Contract("_ -> new")
    public static @NotNull ScrollingBuilder scrolling(@NotNull final ScrollType scrollType) {
        return new ScrollingBuilder(scrollType);
    }

    /**
     * Creates a {@link ScrollingBuilder} with VERTICAL as the {@link ScrollType}
     *
     * @return A vertical {@link ChestGuiBuilder}
     * @since 3.0.0
     */
    @Contract(" -> new")
    public static @NotNull ScrollingBuilder scrolling() {
        return scrolling(ScrollType.VERTICAL);
    }
}
