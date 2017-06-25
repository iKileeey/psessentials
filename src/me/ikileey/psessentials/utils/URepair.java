package me.ikileey.psessentials.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class URepair {

	@SuppressWarnings("deprecation")
	public static void repairAll(Player p, List<String> repaired) {
		List<Material> Items = Arrays.asList(Material.DIAMOND_SWORD, Material.DIAMOND_SPADE, Material.DIAMOND_PICKAXE,
				Material.DIAMOND_HOE,Material.DIAMOND_AXE, Material.IRON_SPADE, Material.IRON_PICKAXE, Material.IRON_AXE, Material.IRON_SWORD,
				Material.IRON_HOE, Material.GOLD_SWORD, Material.GOLD_SPADE, Material.GOLD_PICKAXE, Material.GOLD_PICKAXE,
				Material.GOLD_AXE, Material.GOLD_HOE, Material.STONE_SWORD, Material.STONE_SPADE, Material.STONE_PICKAXE,
				Material.STONE_AXE, Material.STONE_HOE, Material.WOOD_SWORD, Material.WOOD_SPADE, Material.WOOD_PICKAXE,
				Material.WOOD_AXE, Material.WOOD_HOE, Material.FISHING_ROD, Material.getMaterial(306), Material.getMaterial(310), Material.getMaterial(314),
				Material.getMaterial(307), Material.getMaterial(311), Material.getMaterial(315),
				Material.getMaterial(308), Material.getMaterial(312), Material.getMaterial(316),
				Material.getMaterial(309), Material.getMaterial(313), Material.getMaterial(317),
				Material.getMaterial(298), Material.getMaterial(299), Material.getMaterial(300),
				Material.getMaterial(301), Material.BOW);

		for (ItemStack It : p.getInventory().getContents()) {
			if (It != null) {
				if (!It.getType().isBlock()) {
					if (Items.contains(It.getType())) {
						if (It.getDurability() != 0) {
							It.setDurability((short) 0);
							repaired.add(It.getType().toString());
						}
					}
					
				}
				
			}
			
		}
		for (ItemStack It : p.getInventory().getArmorContents()) {
			if(It.getType() != Material.AIR){
				if (It.getDurability() != 0) {
					It.setDurability((short) 0);
					repaired.add(It.getType().toString());
				}				
			}
		}
		p.updateInventory();
	}

}
