package com.uberverse.arkcraft.common.entity.dispense;

import java.util.Random;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;

import com.uberverse.arkcraft.ARKCraft;
import com.uberverse.arkcraft.common.entity.EntitySimpleShotgunAmmo;

public class DispenseSimpleShotgunAmmo extends BehaviorDefaultDispenseItem
{
    private Random rand;

    public DispenseSimpleShotgunAmmo()
    {
        rand = new Random();
    }

    @Override
    public ItemStack dispenseStack(IBlockSource blocksource, ItemStack itemstack)
    {
        EnumFacing face = EnumFacing.getFront(blocksource.getBlockMetadata());

        IPosition pos = BlockDispenser.getDispensePosition(blocksource);
        EntitySimpleShotgunAmmo.fireFromDispenser(blocksource.getWorld(), pos.getX() + face.getFrontOffsetX(), pos.getY() + face.getFrontOffsetY(), pos.getZ() + face.getFrontOffsetZ(), face.getFrontOffsetX(), face.getFrontOffsetY(), face.getFrontOffsetZ());
        itemstack.splitStack(1);
        return itemstack;
    }

    @Override
    protected void playDispenseSound(IBlockSource blocksource)
    {
        blocksource.getWorld().playSoundEffect(blocksource.getX(), blocksource.getY(), blocksource.getZ(), ARKCraft.MODID + ":" + "shotgun_shot", 3.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.6F));
    }

    @Override
    protected void spawnDispenseParticles(IBlockSource blocksource, EnumFacing face)
    {
        super.spawnDispenseParticles(blocksource, face);
        IPosition pos = BlockDispenser.getDispensePosition(blocksource);
        blocksource.getWorld().spawnParticle(EnumParticleTypes.FLAME, pos.getX() + face.getFrontOffsetX(), pos.getY() + face.getFrontOffsetY(), pos.getZ() + face.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
    }
}