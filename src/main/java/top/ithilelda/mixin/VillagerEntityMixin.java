package top.ithilelda.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.ithilelda.GiveVillagerTrade;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {
    @Inject(at= @At("TAIL"), method = "loot")
    private void lootAfter(ItemEntity item, CallbackInfo ci) {
        VillagerEntity villager = (VillagerEntity)(Object)this;
        if (villager.getVillagerData().getProfession() == VillagerProfession.LIBRARIAN) {
            updateLibrarianTradeOffer(villager);
        }
    }


    @Unique
    private void updateLibrarianTradeOffer(VillagerEntity librarian) {
        for(int i = 0; i < librarian.getInventory().size(); i++) {
            ItemStack stack = librarian.getInventory().getStack(i);
            if (stack.getItem() instanceof EnchantedBookItem eb) {
                boolean succeed = librarian.getOffers().add(new TradeOffer(new ItemStack(Items.EMERALD, calculateBookPrice(stack)), new ItemStack(Items.BOOK), stack, 12, 1, 0.2f));
                if (succeed ) librarian.getInventory().removeStack(i);
                GiveVillagerTrade.LOGGER.info("Enchanted book added to librarian trade.");
            }
        }
    }
    @Unique
    private int calculateBookPrice(ItemStack stack) {
        NbtList nbtList = EnchantedBookItem.getEnchantmentNbt(stack);
        int level = 1;
        for (int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            Identifier identifier = EnchantmentHelper.getIdFromNbt(nbtCompound);
            if (identifier != null) {
                level += EnchantmentHelper.getLevelFromNbt(nbtCompound);
            }
        }
        Random random = Random.create();
        int i = MathHelper.nextInt(random, 1, level);
        int j = 2 + random.nextInt(5 + i * 10) + 3 * i;
        if (j > 64) {
            j = 64;
        }
        return j;
    }
}
