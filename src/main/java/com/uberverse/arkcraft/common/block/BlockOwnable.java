package com.uberverse.arkcraft.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockOwnable extends BlockContainer
{

    public BlockOwnable(Material par1)
    {
        super(par1);
    }

    public int getRenderType()
    {
        return 3;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return null;
    }

}