package top.ithilelda;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class GiveVillagerTrade implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("givevillagertrade");
	public static final HashMap<VillagerProfession, ImmutableSet<Item>> AdditionalGatherableItems = new HashMap<>();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Give Villager Trade initialized.");
		AdditionalGatherableItems.put(VillagerProfession.LIBRARIAN, ImmutableSet.of(Items.ENCHANTED_BOOK));
	}

}