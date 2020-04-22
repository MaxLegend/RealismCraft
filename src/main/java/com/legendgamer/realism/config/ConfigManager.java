package com.legendgamer.realism.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import scala.Int;

public class ConfigManager
{
   public static Configuration config;
   //metamorphic
   public static int rare_mtm_coal;
   public static int rare_mtm_copper;
   public static int rare_mtm_tin;
   public static int rare_mtm_titan;
   public static int rare_mtm_nickel;
   public static int rare_mtm_vanadium;
   //magmatic
   public static int rare_mgm_coal;
   public static int rare_mgm_copper;
   public static int rare_mgm_tin;
   public static int rare_mgm_iron;
   public static int rare_mgm_titan;
   public static int rare_mgm_nickel;
   public static int rare_mgm_chrome;
   public static int rare_mgm_vanadium;
   
   //sedimentary
   public static int rare_sed_coal;
   public static int rare_sed_copper;
   public static int rare_sed_tin;
   public static int rare_sed_iron;
   public static int rare_sed_tungsten;

   
//   public static float volume_change_megalamp;
//   public static float volume_change_sovietlamp;

   public static void register(FMLPreInitializationEvent e)
   {
       config = new Configuration(e.getSuggestedConfigurationFile());
       config.load();
       //chance gen mtm ores
       rare_mtm_coal = config.getInt("Chance Metamorphic Coal", "Ore gens", 150, 1, Int.MaxValue(),  "Rare Gen Metamorphic Coal", "config.rare_mtm_coal.name");
       rare_mtm_copper = config.getInt("Chance Metamorphic Copper", "Ore gens", 250, 1, Int.MaxValue(), "Rare Gen Metamorphic Copper", "config.rare_mtm_copper.name");
       rare_mtm_tin = config.getInt("Chance Metamorphic Tin", "Ore gens", 250, 1, Int.MaxValue(),  "Rare Gen Metamorphic Tin", "config.rare_mtm_tin.name");
       rare_mtm_titan = config.getInt("Chance Metamorphic Titan", "Ore gens", 320, 1, Int.MaxValue(), "Rare Gen Metamorphic Titan", "config.rare_mtm_titan.name");
       rare_mtm_nickel = config.getInt("Chance Metamorphic Nickel", "Ore gens", 450, 1, Int.MaxValue(),  "Rare Gen Metamorphic Nickel", "config.rare_mtm_tin.name");
       rare_mtm_vanadium = config.getInt("Chance Metamorphic Vanadium", "Ore gens", 860, 1, Int.MaxValue(), "Rare Gen Metamorphic Vanadium", "config.rare_mtm_titan.name");
       
       //chance gen mgm ores
       rare_mgm_coal = config.getInt("Chance Magmatic Coal", "Ore gens", 150, 1, Int.MaxValue(),  "Rare Gen Magmatic Coal", "config.rare_mgm_coal.name");
       rare_mgm_copper = config.getInt("Chance Magmatic Copper", "Ore gens", 250, 1, Int.MaxValue(), "Rare Gen Magmatic Copper", "config.rare_mgm_copper.name");
       rare_mgm_tin = config.getInt("Chance Magmatic Tin", "Ore gens", 250, 1, Int.MaxValue(),  "Rare Gen Magmatic Tin", "config.rare_mgm_tin.name");
       rare_mgm_iron = config.getInt("Chance Magmatic Iron", "Ore gens", 290, 1, Int.MaxValue(), "Rare Gen Magmatic Iron", "config.rare_mgm_iron.name");
       rare_mgm_titan = config.getInt("Chance Magmatic Titan", "Ore gens", 320, 1, Int.MaxValue(), "Rare Gen Magmatic Titan", "config.rare_mgm_titan.name");
       rare_mgm_nickel = config.getInt("Chance Magmatic Nickel", "Ore gens", 450, 1, Int.MaxValue(),  "Rare Gen Magmatic Nickel", "config.rare_mgm_nickel.name");
       rare_mgm_chrome = config.getInt("Chance Magmatic Chrome", "Ore gens", 980, 1, Int.MaxValue(), "Rare Gen Magmatic Chrome", "config.rare_mgm_chrome.name");
       rare_mgm_vanadium = config.getInt("Chance Magmatic Vanadium", "Ore gens", 780, 1, Int.MaxValue(), "Rare Gen Magmatic Vanadium", "config.rare_mgm_vanadium.name");

       //chance gen sed ores
       rare_sed_coal = config.getInt("Chance Sedimentary Coal", "Ore gens", 150, 1, Int.MaxValue(),  "Rare Gen Sedimentary Coal", "config.rare_sed_coal.name");
       rare_sed_copper = config.getInt("Chance Sedimentary Copper", "Ore gens", 250, 1, Int.MaxValue(), "Rare Gen Sedimentary Copper", "config.rare_sed_copper.name");
       rare_sed_tin = config.getInt("Chance Sedimentary Tin", "Ore gens", 250, 1, Int.MaxValue(),  "Rare Gen Sedimentary Tin", "config.rare_sed_tin.name");
       rare_sed_iron = config.getInt("Chance Sedimentary Iron", "Ore gens", 290, 1, Int.MaxValue(), "Rare Gen Sedimentary Iron", "config.rare_sed_iron.name");
       rare_sed_tungsten = config.getInt("Chance Sedimentary Tungsten", "Ore gens", 320, 1, Int.MaxValue(), "Rare Gen Sedimentary Tungsten", "config.rare_sed_tungsten.name");
   
       config.save();
   }
}
