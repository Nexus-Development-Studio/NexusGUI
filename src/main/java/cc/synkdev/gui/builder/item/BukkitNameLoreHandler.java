package cc.synkdev.gui.builder.item;

import cc.synkdev.gui.components.exception.GuiException;
import cc.synkdev.gui.components.util.Legacy;
import cc.synkdev.gui.components.util.VersionHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class BukkitNameLoreHandler implements NameLoreHandler {

    private static final Field DISPLAY_NAME_FIELD;
    private static final Field LORE_FIELD;

    private static final BukkitNameLoreHandler INSTANCE = new BukkitNameLoreHandler();

    static {
        try {
            final Class<?> metaClass = VersionHelper.craftClass("inventory.CraftMetaItem");

            DISPLAY_NAME_FIELD = metaClass.getDeclaredField("displayName");
            DISPLAY_NAME_FIELD.setAccessible(true);

            LORE_FIELD = metaClass.getDeclaredField("lore");
            LORE_FIELD.setAccessible(true);
        } catch (NoSuchFieldException | ClassNotFoundException exception) {
            throw new GuiException("Could not retrieve displayName nor lore field for ItemBuilder.", exception);
        }
    }

    public static @NotNull BukkitNameLoreHandler getInstance() {
        return INSTANCE;
    }

    @Override
    public void name(@NotNull ItemMeta itemMeta, @NotNull Component name) {
        itemMeta.setDisplayName(
                LegacyComponentSerializer.legacySection().serialize(name)
        );
    }

    @Override
    public void lore(final @NotNull ItemMeta itemMeta, final @NotNull List<Component> lore) {
        itemMeta.setLore(lore.stream().filter(Objects::nonNull).map(Legacy.SERIALIZER::serialize).collect(Collectors.toList()));
    }

    @Override
    public void lore(final @NotNull ItemMeta itemMeta, final @NotNull Consumer<List<@Nullable Component>> lore) {
        List<Component> components;
        if (VersionHelper.IS_COMPONENT_LEGACY) {
            final List<String> stringLore = itemMeta.getLore();
            components = (stringLore == null) ? new ArrayList<>() : stringLore.stream().map(Legacy.SERIALIZER::deserialize).collect(Collectors.toList());
        } else {
            try {
                final List<Object> jsonLore = (List<Object>) LORE_FIELD.get(itemMeta);
                // The field is null by default ._.
                components = (jsonLore == null) ? new ArrayList<>() : jsonLore.stream().map(this::deserializeComponent).collect(Collectors.toList());
            } catch (IllegalAccessException exception) {
                throw new GuiException("Could not get lore for ItemBuilder.", exception);
            }
        }

        lore.accept(components);
        lore(itemMeta, components);
    }

    /**
     * Serializes the component with the right {@link ComponentSerializer} for the current MC version
     *
     * @param component component to serialize
     * @return the serialized representation of the component
     */
    private @NotNull Object serializeComponent(@NotNull final Component component) {
        return GsonComponentSerializer.gson().serialize(component);
    }

    /**
     * Deserializes the object with the right {@link ComponentSerializer} for the current MC version
     *
     * @param obj object to deserialize
     * @return the component
     */
    private @NotNull Component deserializeComponent(@NotNull final Object obj) {
        return GsonComponentSerializer.gson().deserialize((String) obj);
    }
}
