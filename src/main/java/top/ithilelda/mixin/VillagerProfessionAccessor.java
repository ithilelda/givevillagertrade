package top.ithilelda.mixin;


import com.google.common.collect.ImmutableSet;
import net.minecraft.item.Item;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(VillagerProfession.class)
public interface VillagerProfessionAccessor {
    @Accessor
    @Mutable
    void setGatherableItems(ImmutableSet<Item> items);
}
