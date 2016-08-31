package com.uberverse.arkcraft.common.block;

import java.util.Random;

import com.uberverse.arkcraft.ARKCraft;
import com.uberverse.arkcraft.common.block.tile.TileInventoryCampfire;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCampfire extends BlockContainer
{
	private int ID;

	public BlockCampfire(Material material, int ID)
	{
		super(material);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BURNING, false));
		float f = 0.65F; // Height
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
		this.ID = ID;
	}

	// Called when the block is placed or loaded client side to get the tile
	// entity for the block
	// Should return a new instance of the tile entity for the block
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileInventoryCampfire();
	}

	@Override
	public String getHarvestTool(IBlockState state)
	{
		return null;
	}

	// Called when the block is right clicked
	// In this block it is used to open the blocks gui when right clicked by a
	// player
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		// Uses the gui handler registered to your mod to open the gui for the
		// given gui id
		// open on the server side only (not sure why you shouldn't open client
		// side too... vanilla doesn't, so we better not either)
		if (worldIn.isRemote) return true;

		playerIn.openGui(ARKCraft.instance(), ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.7D;
		double d2 = (double) pos.getZ() + 0.5D;
		IBlockState blockState = getActualState(getDefaultState(), worldIn, pos);
		boolean burning = (Boolean) blockState.getValue(BURNING);

		if (burning)
		{
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D,
					new int[0]);
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D,
					new int[0]);
		}

		// worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4 * 1, d1
		// + d3, d2 + d4 * 1, 0.0D, 0.0D, 0.0D, new int[0]);
		// worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4 * 1, d1 + d3,
		// d2 + d4 * 1, 0.0D, 0.0D, 0.0D, new int[0]);

	}

	// This is where you can do something when the block is broken. In this case
	// drop the inventory's contents
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof IInventory)
		{
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
		}

		// if (inventory != null){
		// // For each slot in the inventory
		// for (int i = 0; i < inventory.getSizeInventory(); i++){
		// // If the slot is not empty
		// if (inventory.getStackInSlot(i) != null)
		// {
		// // Create a new entity item with the item stack in the slot
		// EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5,
		// pos.getY() + 0.5, pos.getZ() + 0.5, inventory.getStackInSlot(i));
		//
		// // Apply some random motion to the item
		// float multiplier = 0.1f;
		// float motionX = worldIn.rand.nextFloat() - 0.5f;
		// float motionY = worldIn.rand.nextFloat() - 0.5f;
		// float motionZ = worldIn.rand.nextFloat() - 0.5f;
		//
		// item.motionX = motionX * multiplier;
		// item.motionY = motionY * multiplier;
		// item.motionZ = motionZ * multiplier;
		//
		// // Spawn the item in the world
		// worldIn.spawnEntityInWorld(item);
		// }
		// }
		//
		// // Clear the inventory so nothing else (such as another mod) can do
		// anything with the items
		// inventory.clear();
		// }

		// Super MUST be called last because it removes the tile entity
		super.breakBlock(worldIn, pos, state);
	}

	// ------------------------------------------------------------
	// The code below isn't necessary for illustrating the Inventory Furnace
	// concepts, it's just used for rendering.
	// For more background information see MBE03
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileInventoryCampfire)
		{
			TileInventoryCampfire tileInventoryForge = (TileInventoryCampfire) tileEntity;
			return state.withProperty(BURNING, tileInventoryForge.isBurning());
		}
		return state;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}

	// necessary to define which properties your blocks use
	// will also affect the variants listed in the blockstates model file. See
	// MBE03 for more info.
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { BURNING });
	}

	public static final PropertyBool BURNING = PropertyBool.create("burning");

	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos)
	{
		int lightValue = 0;
		IBlockState blockState = getActualState(getDefaultState(), world, pos);
		boolean burning = (Boolean) blockState.getValue(BURNING);

		if (burning)
		{
			lightValue = 15;
		}
		else
		{
			// linearly interpolate the light value depending on how many slots
			// are burning
			lightValue = 0;
		}
		lightValue = MathHelper.clamp_int(lightValue, 0, 15);
		return lightValue;
	}

	// the block will render in the SOLID layer. See
	// http://greyminecraftcoder.blogspot.co.at/2014/12/block-rendering-18.html
	// for more information.
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	// used by the renderer to control lighting and visibility of other blocks.
	// set to false because this block doesn't fill the entire 1x1x1 space
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	// used by the renderer to control lighting and visibility of other blocks,
	// also by
	// (eg) wall or fence to control whether the fence joins itself to this
	// block
	// set to false because this block doesn't fill the entire 1x1x1 space
	@Override
	public boolean isFullCube()
	{
		return false;
	}

	// render using a BakedModel
	// not strictly required because the default (super method) is 3.
	@Override
	public int getRenderType()
	{
		return 3;
	}
}
