package me.ikileey.psessentials.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class UItens {

	public static ItemStack getItem(String nome, Integer qtd){
		return new ItemStack(Material.getMaterial(nome), qtd);
	}
}
