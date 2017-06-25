package me.ikileey.psessentials.utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;

public class UEnchant {

	public static void enchant(Player p, String enchant, Integer level){
		if(enchant.equalsIgnoreCase("protection")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("fireprotection")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("featherfalling")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_FALL, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("blastprotection")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("projectileprotection")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("respiration")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.OXYGEN, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("aquaaffinity")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.WATER_WORKER, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("thorns")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.THORNS, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("sharpness")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("smite")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("baneofarthropods")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("knockback")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.KNOCKBACK, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("fireaspect")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.FIRE_ASPECT, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("looting")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("efficiency")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.DIG_SPEED, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("silktouch")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.SILK_TOUCH, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("unbreaking")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.DURABILITY, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("fortune")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("power")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("punch")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("flame")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_FIRE, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		if(enchant.equalsIgnoreCase("infinity")){
			p.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_INFINITE, level);
			p.sendMessage(Mensagens.getMensagem("enchant").replace("@Enchant", enchant).replace("@Level", Integer.toString(level)));
		}
		
	}
}
