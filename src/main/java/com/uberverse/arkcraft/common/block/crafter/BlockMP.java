package com.uberverse.arkcraft.common.block.crafter;

import java.util.Random;

import com.uberverse.arkcraft.ARKCraft;
import com.uberverse.arkcraft.common.tileentity.crafter.TileEntityMP;
import com.uberverse.arkcraft.common.tileentity.crafter.TileInventoryMP;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author wildbill22
 */
public class BlockMP extends BlockContainer
{
	private int renderType = 3; // default value
	private boolean isOpaque = false;
	private int ID;
	private boolean render = false;

	public BlockMP(Material mat, int ID)
	{
		super(mat);
		this.setHardness(0.5F);
		this.ID = ID;
		float f = 0.25F;
		float f1 = 0.25F; // Height
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
	}

	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityMP();
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
	{
		return this.blockMaterial != Material.air;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos blockPos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!playerIn.isSneaking())
		{
			playerIn.openGui(ARKCraft.instance(), ID, worldIn, blockPos.getX(), blockPos.getY(),
					blockPos.getZ());
			return true;
		}
		return false;
	}

	public void setRenderType(int renderType)
	{
		this.renderType = renderType;
	}

	public int getRenderType()
	{
		return renderType;
	}

	public void setOpaque(boolean opaque)
	{
		opaque = isOpaque;
	}

	public boolean isOpaqueCube()
	{
		return isOpaque;
	}

	public void setRenderAsNormalBlock(boolean b)
	{
		render = b;
	}

	public boolean renderAsNormalBlock()
	{
		return render;
	}

	/**
	 * Returns randomly, about 1/2 of the recipe items
	 */
	/*
	@Override
	public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof TileInventoryMP)
		{
			TileInventoryMP tiMP = (TileInventoryMP) tileEntity;
			for (int i = 0; i < TileInventoryMP.INVENTORY_SLOTS_COUNT; ++i)
			{
				if (rand.nextInt(2) == 0)
				{
					ret.add(tiMP.getStackInSlot(TileInventoryMP.FIRST_INVENTORY_SLOT + i));
				}
			}
		}
		return ret;
	}	*/
}
