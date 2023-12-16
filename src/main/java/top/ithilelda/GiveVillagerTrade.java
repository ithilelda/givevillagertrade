package top.ithilelda;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ithilelda.mixin.VillagerProfessionAccessor;

public class GiveVillagerTrade implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("givevillagertrade");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Give Villager Trade initialized.");
		((VillagerProfessionAccessor)(Object)VillagerProfession.LIBRARIAN).setGatherableItems(ImmutableSet.of(Items.ENCHANTED_BOOK));
	}

}