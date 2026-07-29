package cc.synkdev.gui.builder.item;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public interface NameLoreHandler {

    void name(final @NotNull ItemMeta itemMeta, final @NotNull Component name);

    void lore(final @NotNull ItemMeta itemMeta, final @NotNull  List<Component> lore);

    void lore(final @NotNull ItemMeta itemMeta, final @NotNull Consumer<List<@Nullable Component>> lore);
}
