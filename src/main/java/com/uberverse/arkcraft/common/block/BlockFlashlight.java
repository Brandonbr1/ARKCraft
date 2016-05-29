package com.uberverse.arkcraft.common.block;

import net.minecraft.block.BlockAir;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.uberverse.arkcraft.common.tileentity.TileFlashlight;

public class BlockFlashlight extends BlockAir implements ITileEntityProvider
{
	public BlockFlashlight()
	{
		super();
		this.setLightLevel(0.8F);
		// this.setUnlocalizedName("light_source");
		this.setBlockBounds(0, 0, 0, 0, 0, 0);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileFlashlight();
	}
}