package com.legendgamer.realism.capevent;

public class PlayerCAPEventHandler {
//	public static final ResourceLocation LEVEL_CAP = new ResourceLocation(Realism.MODID ,"waterLevel");
//
//	/**
//	 * ƒанные методы имеют отношение ко всей капе игрока - сохранение после смерти, при перезаходе в мир и так далее. ј также регисрации капы
//	 * 
//	 */
//	@SubscribeEvent
//	public void attachCapability(AttachCapabilitiesEvent event) {
//		if(event.getObject() instanceof EntityPlayer){
//			event.addCapability(LEVEL_CAP, new PlayerCapProvider());
//		}
//	}
//
//	@SubscribeEvent
//	public void onPlayerClone(PlayerEvent.Clone event) {
//		EntityPlayer player = event.getEntityPlayer();
//		IPlayerCap newCap = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);
//		IPlayerCap oldCap = event.getOriginal().getCapability(PlayerCapProvider.LEVEL_CAP, null);
//		newCap.copyCapabilities(oldCap);
//	}
//	/**
//	 * Ёти методы уже относ€тс€ к непосредственно обновлению waterstorage
//	 */
//
//	//ѕополнение капы действием предмета
//	@SubscribeEvent
//	public void counterDrinkFinish(LivingEntityUseItemEvent.Finish event) {
//		EntityPlayer player = (EntityPlayer) event.getEntity();
//		if(!player.world.isRemote) {
//			IPlayerCap capabilities = event.getEntity().getCapability(PlayerCapProvider.LEVEL_CAP, null);
//			/*	if (event.getItem().getItemUseAction() == EnumAction.DRINK) {
//				if (event.getItem().getItem() != null && event.getItem().getItem() == RegItems.itemcupfill) {
//					capabilities.reduceWaterLevel(30);
//					event.getItem().shrink(1);
//					player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(RegItems.itemcup));
//				}
//				NetworkHandler.INSTANCE.sendToAll(new HUDSyncMessage(capabilities.getWaterLevel()));	
//			 */
//		}
//	}
//	//сохранение(передача новому игроку) капы после смерти сущности(игрока)
//	@SubscribeEvent
//	public void onDeath(LivingDeathEvent event) {
//		if (event.getEntity() instanceof EntityPlayer) {
//
//			EntityPlayer player = (EntityPlayer) event.getEntity();
//			IPlayerCap capabilities = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);
//
//			NetworkHandler.network.sendTo(new HUDSyncMessage(capabilities.getWaterLevel()),(EntityPlayerMP)player);
//		}
//	}
//	//основной метод где с течением времени с капой что то происходит.	
//	@SubscribeEvent
//	public void updateTicker(LivingUpdateEvent event) {
//		if(event.getEntity() instanceof EntityPlayer && !FMLCommonHandler.instance().getEffectiveSide().isClient()) {
//
//			EntityPlayer player = (EntityPlayer)event.getEntity();
//			IPlayerCap capabilities = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);
//			if( !event.getEntity().getEntityWorld().isRemote) {
//				if(player.ticksExisted % 60 == 0) {
//
//					Biome biome = player.getEntityWorld().getBiomeForCoordsBody(new BlockPos(player));
//
//					if((biome == Biomes.DESERT_HILLS || biome == Biomes.DESERT)){
//						if(player.isSprinting()) {
//							capabilities.addWaterLevel(30);
//
//						} else
//							capabilities.addWaterLevel(20);
//
//					} else
//						if(player.isSprinting()) {
//							capabilities.addWaterLevel(20);
//
//						} else
//							capabilities.addWaterLevel(10);
//
//					NetworkHandler.network.sendTo(new HUDSyncMessage(capabilities.getWaterLevel()),(EntityPlayerMP)player);
//
//
//				}
//			}
//		}
//	}
//	@SideOnly(Side.CLIENT)
//	@SubscribeEvent
//	public void hadDrinken(PlayerInteractEvent.RightClickBlock event){
//
//		final World world = (World)Minecraft.getMinecraft().world;
//		EntityPlayer player = (EntityPlayer) event.getEntity();
//		ItemStack is = player.getHeldItem(EnumHand.MAIN_HAND);
//		RayTraceResult raytraceresult = this.rayTrace(world, player, true);	
//
//		if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
//		{
//			BlockPos blockpos = raytraceresult.getBlockPos();
//			IPlayerCap capabilities = event.getEntity().getCapability(PlayerCapProvider.LEVEL_CAP, null);
//
//			if (world.getBlockState(blockpos).getMaterial() == Material.WATER)
//			{
//				if(is.isEmpty()){
//					capabilities.reduceWaterLevel(5);
//
//					player.world.playSound(player, blockpos, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.NEUTRAL, 0.8F, 1.0F);
//					NetworkHandler.network.sendToServer(new HUDSyncMessageServer(capabilities.getWaterLevel()));
//					
//				}
//			}
//		}
//	}
//	protected RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids)
//	{
//		float f = playerIn.rotationPitch;
//		float f1 = playerIn.rotationYaw;
//		double d0 = playerIn.posX;
//		double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
//		double d2 = playerIn.posZ;
//		Vec3d vec3d = new Vec3d(d0, d1, d2);
//		float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
//		float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
//		float f4 = -MathHelper.cos(-f * 0.017453292F);
//		float f5 = MathHelper.sin(-f * 0.017453292F);
//		float f6 = f3 * f4;
//		float f7 = f2 * f4;
//		double d3 = 5.0D;
//		if (playerIn instanceof net.minecraft.entity.player.EntityPlayerMP)
//		{
//			d3 = ((net.minecraft.entity.player.EntityPlayerMP)playerIn).interactionManager.getBlockReachDistance();
//		}
//		Vec3d vec3d1 = vec3d.addVector((double)f6 * d3, (double)f5 * d3, (double)f7 * d3);
//		return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
//	}
//	
//	/**
//	 * температура игрока
//	 * 
//	 */
//	@SubscribeEvent
//	public void onSick(LivingUpdateEvent event) {
//		if(event.getEntity() instanceof EntityPlayer && !FMLCommonHandler.instance().getEffectiveSide().isClient()) {
//
//			EntityPlayer player = (EntityPlayer) event.getEntity();
//			IPlayerCap capabilities = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);
//
//			if(player.ticksExisted % 80 == 0) {
//				Biome biome = player.getEntityWorld().getBiomeForCoordsBody(new BlockPos(player));
//
//				int variator = player.getEntityWorld().rand.nextInt(40);
//
//				if((biome == Biomes.TAIGA || biome == Biomes.COLD_TAIGA || biome == Biomes.COLD_TAIGA_HILLS || biome == Biomes.TAIGA_HILLS || biome == Biomes.COLD_BEACH)&& variator == 32){
//					capabilities.setTempBody(37.8F);
//					capabilities.setCommonCold(true);
//				}
//				if((biome == Biomes.TAIGA || biome == Biomes.COLD_TAIGA || biome == Biomes.COLD_TAIGA_HILLS || biome == Biomes.TAIGA_HILLS || biome == Biomes.COLD_BEACH)&& variator == 24){
//					capabilities.setTempBody(39.1F);
//					int intovariator = player.getEntityWorld().rand.nextInt(3);
//					if(intovariator == 2){
//						capabilities.setTempBody(38.8F);
//					}
//					if(intovariator == 1){
//						capabilities.setTempBody(38.6F);
//					}
//					if(intovariator == 0){
//						capabilities.setTempBody(38.9F);
//					}
//					capabilities.setGrippe(true);
//				}
//
//
//				if(variator == 34){
//					capabilities.setTempBody(36.6F);
//					int intovariator = player.getEntityWorld().rand.nextInt(3);
//					if(intovariator == 2){
//						capabilities.setTempBody(36.7F);
//					}
//					if(intovariator == 1){
//						capabilities.setTempBody(36.5F);
//					}
//					if(intovariator == 0){
//						capabilities.setTempBody(36.4F);
//					}
//					capabilities.setCommonCold(false);
//				}
//				if(variator == 13){
//					capabilities.setTempBody(36.6F);
//					int intovariator = player.getEntityWorld().rand.nextInt(3);
//					if(intovariator == 2){
//						capabilities.setTempBody(36.7F);
//					}
//					if(intovariator == 1){
//						capabilities.setTempBody(36.5F);
//					}
//					if(intovariator == 0){
//						capabilities.setTempBody(36.4F);
//					}
//					capabilities.setGrippe(false);
//				}
//
//			//	System.out.println("pre b " + capabilities.getCommonCold());
//			//	System.out.println("pre " + capabilities.getTempBody());
//				NetworkHandler.network.sendTo(new TemperatureBodyClientMessage(capabilities.getTempBody()), (EntityPlayerMP)player);
//				//NetworkHandler.network.sendTo(new ColdMessageClient(capabilities.getCommonCold()), (EntityPlayerMP)player);
//			//	System.out.println("post " + capabilities.getTempBody());
//			//	System.out.println("post b " + capabilities.getCommonCold());
//			}
//		}
//
//	}

}
/*
for(float i = 36; i < 38F; i += 0.01f) {
	cap.addTempBody(i);
	hl.setColor((int)i);
	*/
