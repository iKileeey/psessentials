package me.ikileey.psessentials;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.ikileey.psessentials.automessager.AutoMessager;
import me.ikileey.psessentials.banitem.BanItem;
import me.ikileey.psessentials.commands.Clear;
import me.ikileey.psessentials.commands.Enchant;
import me.ikileey.psessentials.commands.Enderchest;
import me.ikileey.psessentials.commands.Fly;
import me.ikileey.psessentials.commands.Food;
import me.ikileey.psessentials.commands.Gamemode;
import me.ikileey.psessentials.commands.Give;
import me.ikileey.psessentials.commands.God;
import me.ikileey.psessentials.commands.Heal;
import me.ikileey.psessentials.commands.Invsee;
import me.ikileey.psessentials.commands.Kill;
import me.ikileey.psessentials.commands.Mobkill;
import me.ikileey.psessentials.commands.Repair;
import me.ikileey.psessentials.commands.Spawnmob;
import me.ikileey.psessentials.commands.Speed;
import me.ikileey.psessentials.commands.Time;
import me.ikileey.psessentials.createcmds.CreateCmds;
import me.ikileey.psessentials.databases.SQLite;
import me.ikileey.psessentials.events.Eventos;
import me.ikileey.psessentials.homes.Homes;
import me.ikileey.psessentials.kits.Cooldown;
import me.ikileey.psessentials.kits.Kits;
import me.ikileey.psessentials.limpeza.Comandos;
import me.ikileey.psessentials.limpeza.Limpeza;
import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.motds.Motd;
import me.ikileey.psessentials.spawn.Spawn;
import me.ikileey.psessentials.teleports.Call;
import me.ikileey.psessentials.teleports.TP;
import me.ikileey.psessentials.teleports.TPAll;
import me.ikileey.psessentials.teleports.TPPos;
import me.ikileey.psessentials.teleports.Tphere;
import me.ikileey.psessentials.utils.Data;
import me.ikileey.psessentials.utils.UCreateCmds;
import me.ikileey.psessentials.utils.Utils;
import me.ikileey.psessentials.vault.VaultAPI;
import me.ikileey.psessentials.warps.Warp;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		pl = this;
		data.setup(this);
		UCreateCmds.setupCommands();
		getServer().getPluginManager().registerEvents(new Eventos(), this);
		getServer().getPluginManager().registerEvents(new CreateCmds(), this);
		getCommand("gm").setExecutor(new Gamemode());
		getCommand("tp").setExecutor(new TP());
		getCommand("food").setExecutor(new Food());
		getCommand("vida").setExecutor(new Heal());
		getCommand("deus").setExecutor(new God());
		getCommand("speed").setExecutor(new Speed());
		getCommand("clear").setExecutor(new Clear());
		getCommand("echest").setExecutor(new Enderchest());
		getCommand("kill").setExecutor(new Kill());
		getCommand("tpall").setExecutor(new TPAll());
		getCommand("tppos").setExecutor(new TPPos());
		getCommand("call").setExecutor(new Call());
		getCommand("bring").setExecutor(new Call());
		getCommand("puxar").setExecutor(new Tphere());
		getCommand("spawnmob").setExecutor(new Spawnmob());
		getCommand("mobkill").setExecutor(new Mobkill());
		getCommand("enchant").setExecutor(new Enchant());
		getCommand("enchants").setExecutor(new Enchant());
		getCommand("fix").setExecutor(new Repair());
		getCommand("i").setExecutor(new Give());
		getCommand("give").setExecutor(new Give());
		getCommand("time").setExecutor(new Time());
		getCommand("invsee").setExecutor(new Invsee());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("setspawn").setExecutor(new Spawn());
		getCommand("setwarp").setExecutor(new Warp());
		getCommand("warp").setExecutor(new Warp());
		getCommand("warps").setExecutor(new Warp());
		getCommand("delwarp").setExecutor(new Warp());
		getCommand("skit").setExecutor(new Kits());
		getCommand("kit").setExecutor(new Kits());
		getCommand("kits").setExecutor(new Kits());
		getCommand("fly").setExecutor(new Fly());
		getCommand("sethome").setExecutor(new Homes());
		getCommand("home").setExecutor(new Homes());
		getCommand("homes").setExecutor(new Homes());
		getCommand("delhome").setExecutor(new Homes());
		getCommand("publica").setExecutor(new Homes());
		getCommand("particular").setExecutor(new Homes());
		getCommand("infohome").setExecutor(new Homes());
		getCommand("limpeza").setExecutor(new Comandos());
		getCommand("motd").setExecutor(new Motd());
		getCommand("item").setExecutor(new BanItem());
		saveDefaultConfig();
		Utils.setup();
		if(VaultAPI.setupEconomy()){
			System.out.println("[PsEssentials] Plugin 'Economia' encontrado.");
		}
		if(Utils.autombar){
			if(getServer().getPluginManager().getPlugin("BarAPI") == null){
				Utils.autombar = false;
				System.out.println("[PsEssentials] Plugin 'barapi' nao encontrado.");
				System.out.println("[PsEssentials] Funcao 'automessagerbossbar' desativada.");
			}else{
				System.out.println("[PsEssentials] Plugin 'barapi' encontrado.");
			}			
		}
		if(Utils.limpeza){
			Limpeza.startTask();
		}
		if(Utils.automchat){
			AutoMessager.startAutoMessagerChat();
		}
		if(Utils.autombar){
			AutoMessager.startAutoMessagerBossBar();
		}
		Mensagens.loadMensagens();
		cool = new Cooldown(getConfig(), new File(getDataFolder(), "config.yml"));
		loadDatabases();
		System.out.println("[PsEssentials] Plugin Habilitado com sucesso!");
	}
	
	@Override
	public void onDisable() {
		try {
			SQLite.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Plugin pl;
	public static Cooldown cool;
	
	Data data = Data.getInstance();
	
	public static void loadDatabases(){
		final File databaseFile = new File(Main.pl.getDataFolder() + File.separator + "database.db");
        if (!databaseFile.exists()) {
            try {
                databaseFile.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Bukkit.getConsoleSender().sendMessage("[PsEssentials] Database.db criada com sucesso!");
        }
        SQLite.startDatabase();
	}
}
