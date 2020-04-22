package com.legendgamer.realism.proxy;


import com.legendgamer.realism.capability.world_cap.DateStorage;
import com.legendgamer.realism.capability.world_cap.IDate;
import com.legendgamer.realism.config.ConfigManager;
import com.legendgamer.realism.packets.NetworkHandler;
import com.legendgamer.realism.reg.RegBiomes;
import com.legendgamer.realism.reg.RegBlocks;
import com.legendgamer.realism.reg.RegDim;
import com.legendgamer.realism.reg.RegEvents;
import com.legendgamer.realism.reg.RegFluids;
import com.legendgamer.realism.reg.RegItems;
import com.legendgamer.realism.reg.RegTileEntity;
import com.legendgamer.realism.reg.RegWorldGen;
import com.legendgamer.realism.world.type.RealismWorldType;

import net.minecraft.world.WorldType;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	//RealismTreeGenerator rtg = new RealismTreeGenerator();
//	public final WorldType REALISM_TYPE = new RealismWorldType("realsim_type");
    public void preInit(FMLPreInitializationEvent event) {
    	ConfigManager.register(event);
    	RegFluids.register();
    	
    	RegDim.registerDim();
    	RegBiomes.registerBiomes();
    	RegTileEntity.registry();
   
    	
        RegItems.register();
       
    	RegBlocks.register();
     	
    	RegEvents.Server();
    //	CapabilityManager.INSTANCE.register(IPlayerCap.class, new PlayerCapStorage(), PlayerCap.class);
  // 	CapabilityManager.INSTANCE.register(ICAPCustomInventory.class, new CAPCustomInventoryStorage(), CAPCustomInventory.class);
    	 CapabilityManager.INSTANCE.register(IDate.class, DateStorage.INSTANCE, DateStorage.INSTANCE);
    	NetworkHandler.init();
    	RegWorldGen.registry();
    	
    
    }
    public void init(FMLInitializationEvent event) {
   // 	GameRegistry.registerWorldGenerator(rtg, 0);
    }
    public void postInit(FMLPostInitializationEvent event) {
    	WorldType REALISM_TYPE = new RealismWorldType("realsim_type");
    	
    }
}
