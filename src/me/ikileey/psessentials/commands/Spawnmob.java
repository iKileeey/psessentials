package me.ikileey.psessentials.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;

import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.Utils;

public class Spawnmob implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("spawnmob")){
			if(sender instanceof Player){
				Player p = (Player)sender;		
				if(p.hasPermission("psessentials.spawnmob")){
					if(args.length == 2 && args[0].equalsIgnoreCase("porco")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Pig.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("galinha")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Chicken.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("vaca")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Cow.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("ovelha")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Sheep.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("lobo")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Wolf.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("morcego")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Bat.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("zumbi")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Zombie.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("esqueleto")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Skeleton.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("Creeper")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Creeper.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("Slime")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Slime.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("enderman")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Enderman.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("blaze")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Blaze.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("bruxa")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), Witch.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("vaca_cogumelo")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), MushroomCow.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("dragao")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), EnderDragon.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("aranha_caverna")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), CaveSpider.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("porco_zumbi")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							for (int i = 0; i < Integer.parseInt(args[1]); i++) {
								w.spawn(p.getLocation(), PigZombie.class);							
							}
							p.sendMessage(Mensagens.getMensagem("spawnmob").replace("@Qtd", args[1]).replace("@Mob", args[0].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));
						}
					}
					if(args.length == 0){
						p.sendMessage("§c[Mobs] Bruxa, Blaze, Enderman, Slime, Creeper, Esqueleto, Zumbi, Morcego, Lobo, Ovelha, Vaca, Galinha, Porco, Vaca_Cogumelo, Dragao, Aranha_Caverna, Porco_Zumbi");
					}				
					if(args.length == 1){
						p.sendMessage("§cUse: /spawnmob <mob> <quantidade>");
					}
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}else{
				sender.sendMessage("§cExecutável apenas in-game.");
			}
		}
		return false;
	}
}
