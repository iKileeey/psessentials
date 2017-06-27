package me.ikileey.psessentials.vault;

import org.bukkit.plugin.RegisteredServiceProvider;

import me.ikileey.psessentials.Main;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class VaultAPI {

	public static Economy economy = null;
	public static Permission permission = null;
	
    public static boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = Main.pl.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
}
