package me.ikileey.psessentials.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.ikileey.psessentials.messages.Mensagens;

public class UKits {

	public static Data data = Data.getInstance();

	public static boolean containsKit(String kit) {
		if (data.getData().contains("Kits." + kit.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	public static void newKit(String kit) {
		data.getData().set("Kits." + kit.toLowerCase()+".Inventory", null);
		data.getData().set("Kits." + kit.toLowerCase()+".Cooldown", 5);
		data.saveData();
	}
	
	public static void setDelay(String kit, Integer delay){
		data.getData().set("Kits." + kit.toLowerCase()+".Cooldown", delay);
		data.saveData();
	}
	
	public static void deleteKit(String kit){
		data.getData().set("Kits."+kit.toLowerCase()+".Inventory", null);
		data.getData().set("Kits."+kit.toLowerCase()+".Armor", null);
		data.getData().set("Kits."+kit.toLowerCase()+".Cooldown", null);
		data.getData().set("Kits."+kit.toLowerCase(), null);
		data.saveData();
	}

	
	public static void saveItens(final Player p, String kit) {
		data.getData().set("Kits." + kit.toLowerCase() + ".Inventory", p.getInventory().getContents());
		data.getData().set("Kits." + kit.toLowerCase() + ".Armor", p.getInventory().getArmorContents());
		data.saveData();
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.sendMessage(Mensagens.getMensagem("kit_itens_setado").replace("@Kit", kit));
	}

	public static boolean hasItensSet(String kit){
		 Object a = data.getData().get("Kits."+kit.toLowerCase()+".Inventory");
         Object b = data.getData().get("Kits."+kit.toLowerCase()+".Armor");
         if(a == null || b == null){
             return false;
         }else{
        	 return true;
         }
	}
	
    @SuppressWarnings("deprecation")
	public static void loadKit(final Player p, String kit){
		 Object a = data.getData().get("Kits."+kit.toLowerCase()+".Inventory");
         Object b = data.getData().get("Kits."+kit.toLowerCase()+".Armor");
         if(a == null || b == null){
        	 p.sendMessage(Mensagens.getErro("erro12").replace("@Kit", kit));
             return;
         }
         ItemStack[] inventory = null;
         ItemStack[] armor = null;
         if (a instanceof ItemStack[]){
               inventory = (ItemStack[]) a;
         } else if (a instanceof List){
                 List<?> lista = (List<?>) a;
                 inventory = (ItemStack[]) lista.toArray(new ItemStack[0]);
         }
         if (b instanceof ItemStack[]){
                 armor = (ItemStack[]) b;
           } else if (b instanceof List){
               List<?> listb = (List<?>) b;
               armor = (ItemStack[]) listb.toArray(new ItemStack[0]);
           }
         for(ItemStack itens : inventory){
        	 if(itens != null){
        		 if(itens.getType() != Material.AIR){
        			 if(p.getInventory().firstEmpty() == -1){
            			 p.getWorld().dropItem(p.getLocation(), itens);
            		 }else{
            			 p.getInventory().addItem(itens);       
            		 }        			 
        		 }
        	 }
         }
         for(ItemStack itens2 : armor){
        	 if(itens2 != null){
        		 if(itens2.getType() != Material.AIR){
        			 if(p.getInventory().firstEmpty() == -1){
            			 p.getWorld().dropItem(p.getLocation(), itens2);
            		 }else{
            			 p.getInventory().addItem(itens2);       
            		 }        			 
        		 }
        	 }
         }
         p.updateInventory();
    }
    
	public static String getKits(Player p){
		 final StringBuilder sb = new StringBuilder();
		 for (final String n : Data.getInstance().getData().getConfigurationSection("Kits").getKeys(false)) {
			 if(p.hasPermission("psessentials.kits."+n)){
				 sb.append(n+", ");				 
			 }
		 }
		 if(sb.toString().isEmpty()){
			 return "Nenhum kit.";
		 }else{
			 return sb.toString();			 
		 }
			 
	}
    
}
