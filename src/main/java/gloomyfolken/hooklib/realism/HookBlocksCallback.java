package gloomyfolken.hooklib.realism;

import javax.annotation.Nullable;

import com.google.common.collect.BiMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockObserver;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryInternal;
import net.minecraftforge.registries.RegistryManager;

public class HookBlocksCallback {
    private static final ResourceLocation BLOCK_TO_ITEM    = new ResourceLocation("minecraft:blocktoitemmap");
    private static final ResourceLocation BLOCKSTATE_TO_ID = new ResourceLocation("minecraft:blockstatetoid");
    static class ClearableObjectIntIdentityMap<I> extends ObjectIntIdentityMap<I>
    {
        void clear()
        {
            this.identityMap.clear();
            this.objectList.clear();
        }
    }
	  public static void onAdd(IForgeRegistryInternal<Block> owner, RegistryManager stage, int id, Block block, @Nullable Block oldBlock)
      {
          @SuppressWarnings("unchecked")
          ClearableObjectIntIdentityMap<IBlockState> blockstateMap = owner.getSlaveMap(BLOCKSTATE_TO_ID, ClearableObjectIntIdentityMap.class);

          if ("minecraft:tripwire".equals(block.getRegistryName().toString())) //Tripwire is crap so we have to special case whee!
          {
              for (int meta = 0; meta < 15; meta++)
                  blockstateMap.put(block.getStateFromMeta(meta), id << 4 | meta);
          }
System.out.println("pidor");
          //So, due to blocks having more in-world states then metadata allows, we have to turn the map into a semi-milti-bimap.
          //We can do this however because the implementation of the map is last set wins. So we can add all states, then fix the meta bimap.
          //Multiple states -> meta. But meta to CORRECT state.

          final boolean[] usedMeta = new boolean[16]; //Hold a list of known meta from all states.
          for (IBlockState state : block.getBlockState().getValidStates())
          {
              final int meta = block.getMetaFromState(state);
              blockstateMap.put(state, id << 4 | meta); //Add ALL the things!
              usedMeta[meta] = true;
          }

          for (int meta = 0; meta < 16; meta++)
          {
              if (block.getClass() == BlockObserver.class)
                  continue; //Observers are bad and have non-cyclical states. So we HAVE to use the vanilla logic above.
              if (usedMeta[meta])
                  blockstateMap.put(block.getStateFromMeta(meta), id << 4 | meta); // Put the CORRECT thing!
          }

          if (oldBlock != null)
          {
              @SuppressWarnings("unchecked")
              BiMap<Block, Item> blockToItem = owner.getSlaveMap(BLOCK_TO_ITEM, BiMap.class);
              Item item = blockToItem.get(oldBlock);
              if (item != null)
                  blockToItem.forcePut(block, item);
          }
      }
}
