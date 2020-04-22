package com.legendgamer.realism;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.legendgamer.realism.command.RealismCommand;
import com.legendgamer.realism.creativetabs.TabOres;
import com.legendgamer.realism.creativetabs.TabRock;
import com.legendgamer.realism.creativetabs.TabTrees;
import com.legendgamer.realism.proxy.CommonProxy;
import com.legendgamer.realism.reg.BlocksList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Realism.MODID, name = "realism",version = Realism.VERSION)
public class Realism {
	public static final String MODID = "realism";
	public static final String VERSION = "0.1 Redux";
	public static final String NAME = "Realism Mod";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String[] AUTHORS = new String[] {"LegendGamer"};
	public static CreativeTabs tabRocks = new TabRock("tabRocks");
	public static CreativeTabs tabOres = new TabOres("tabOres");
	//public static CreativeTabs tabTool = new TabRocks("tabTool");
	public static CreativeTabs tabTrees = new TabTrees("tabTrees");
	@Mod.Instance
	public static Realism INSTANCE;
	public static final ResourceLocation DATE = new ResourceLocation("seasons","date");
	
   	@SidedProxy(clientSide = "com.legendgamer.realism.proxy.ClientProxy", serverSide = "com.legendgamer.realism.proxy.CommonProxy")
   	public static CommonProxy proxy;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        LOGGER.info("[MOD] Realism Mod enabled and loaded");
    //	MinecraftForge.EVENT_BUS.register(new EventTeleport());
    	
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
      
      //  MinecraftForge.EVENT_BUS.register(new FallingTreeEvent());
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) throws IOException {
        proxy.postInit(event);
//		try {
//			createJson();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
   
    }
    @EventHandler
    public  void onStart(FMLServerStartingEvent event){
        event.registerServerCommand(new RealismCommand());
    }
    

}
