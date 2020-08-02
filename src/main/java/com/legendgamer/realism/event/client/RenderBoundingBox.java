package com.legendgamer.realism.event.client;

import com.legendgamer.realism.blocks.tree.XBeam;
import com.legendgamer.realism.blocks.tree.ZBeam;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class RenderBoundingBox {

    @SubscribeEvent
    public void onDrawBlockHighlight(@Nonnull DrawBlockHighlightEvent event) {
        BlockPos pos = event.getTarget().getBlockPos();
        World world = event.getPlayer().world;

        if (event.getTarget().typeOfHit == RayTraceResult.Type.BLOCK) {
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock() instanceof XBeam) {
                switch (state.getValue(XBeam.CORNER)) {
                    default:
                        break;
                    case TULC:
                    case TURC:
                    case TDLC:
                    case TDRC:
                    case DRD:
                    case DLD:
                        event.setCanceled(true);
                        for (AxisAlignedBB axisAlignedBB :
                                ((XBeam) state.getBlock()).getCollisionBoundingBoxList(state, world, pos))
                            drawSelectionBox(event.getPlayer(), axisAlignedBB.offset(pos), event.getPartialTicks());
                }
            }
            else if (state.getBlock() instanceof ZBeam) {
                switch (state.getValue(ZBeam.CORNER)) {
                    default:
                        break;
                    case TULC:
                    case TURC:
                    case TDLC:
                    case TDRC:
                    case DRD:
                    case DLD:
                        event.setCanceled(true);
                        for (AxisAlignedBB axisAlignedBB :
                                ((ZBeam) state.getBlock()).getCollisionBoundingBoxList(state, world, pos))
                            drawSelectionBox(event.getPlayer(), axisAlignedBB.offset(pos), event.getPartialTicks());
                }
            }
        }
    }

    private void drawSelectionBox(@Nonnull EntityPlayer player, AxisAlignedBB bb, double partialTicks) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(2.0F);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);

        double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
        double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
        double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;
        RenderGlobal.drawSelectionBoundingBox(bb.offset(-d0, -d1, -d2), 0.0F, 0.0F, 0.0F, 0.4F);

        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}