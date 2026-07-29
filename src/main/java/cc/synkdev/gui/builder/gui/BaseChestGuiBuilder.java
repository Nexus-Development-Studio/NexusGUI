package cc.synkdev.gui.builder.gui;

import cc.synkdev.gui.components.GuiContainer;
import cc.synkdev.gui.components.GuiType;
import cc.synkdev.gui.components.InventoryProvider;
import cc.synkdev.gui.components.util.Legacy;
import cc.synkdev.gui.guis.BaseGui;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unchecked")
public abstract class BaseChestGuiBuilder<G extends BaseGui, B extends BaseChestGuiBuilder<G, B>> extends BaseGuiBuilder<G, B> {

    private int rows = 1;
    private InventoryProvider.Chest inventoryProvider =
            (title, owner, rows) -> Bukkit.createInventory(owner, rows, Legacy.SERIALIZER.serialize(title));

    /**
     * Sets the rows for the GUI
     * This will only work on CHEST {@link GuiType}
     *
     * @param rows The number of rows
     * @return The builder
     */
    @NotNull
    @Contract("_ -> this")
    public B rows(final int rows) {
        this.rows = rows;
        return (B) this;
    }

    public B inventory(@NotNull final InventoryProvider.Chest inventoryProvider) {
        this.inventoryProvider = inventoryProvider;
        return (B) this;
    }

    /**
     * Getter for the rows
     *
     * @return The amount of rows
     */
    protected int getRows() {
        return rows;
    }

    protected @NotNull InventoryProvider.Chest getInventoryProvider() {
        return inventoryProvider;
    }

    protected @NotNull GuiContainer.Chest createContainer() {
        return new GuiContainer.Chest(getTitle(), inventoryProvider, getRows());
    }
}
