package com.legendgamer.realism.API;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum EnumMaterials
{
    WOOD(0, 59, 2.0F, 0.0F, 15),
    STONE(1, 131, 4.0F, 1.0F, 5),
    IRON(2, 250, 6.0F, 2.0F, 14),
    DIAMOND(3, 1561, 8.0F, 3.0F, 10),
    GOLD(0, 32, 12.0F, 0.0F, 22);

    /** The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = WOOD/GOLD) */
    private final int harvestLevel;
    /** The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32) */
    private final int maxUses;
    /** The strength of this tool material against blocks which it is effective against. */
    private final float efficiency;
    /** Damage versus entities. */
    private final float attackDamage;
    /** Defines the natural enchantability factor of the material. */
    private final int enchantability;
    //Added by forge for custom Tool materials.
    private ItemStack repairMaterial = ItemStack.EMPTY;

    private EnumMaterials(int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability)
    {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = damageVsEntity;
        this.enchantability = enchantability;
    }

    public int getMaxUses()
    {
        return this.maxUses;
    }

    public float getEfficiency()
    {
        return this.efficiency;
    }

    public float getAttackDamage()
    {
        return this.attackDamage;
    }

    public int getHarvestLevel()
    {
        return this.harvestLevel;
    }

    public int getEnchantability()
    {
        return this.enchantability;
    }

    @Deprecated
    public Item getRepairItem()
    {
        if (this == WOOD)
        {
            return Item.getItemFromBlock(Blocks.PLANKS);
        }
        else if (this == STONE)
        {
            return Item.getItemFromBlock(Blocks.COBBLESTONE);
        }
        else if (this == GOLD)
        {
            return Items.GOLD_INGOT;
        }
        else if (this == IRON)
        {
            return Items.IRON_INGOT;
        }
        else
        {
            return this == DIAMOND ? Items.DIAMOND : null;
        }
    }

    public EnumMaterials setRepairItem(ItemStack stack)
    {
        if (!this.repairMaterial.isEmpty()) throw new RuntimeException("Repair material has already been set");
        if (this == WOOD || this == STONE || this == GOLD || this == IRON || this == DIAMOND) throw new RuntimeException("Can not change vanilla tool repair materials");
        this.repairMaterial = stack;
        return this;
    }

    public ItemStack getRepairItemStack()
    {
        if (!repairMaterial.isEmpty()) return repairMaterial;
        Item ret = this.getRepairItem();
        if (ret != null) repairMaterial = new ItemStack(ret, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE);
        return repairMaterial;
    }
}