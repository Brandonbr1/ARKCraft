package com.uberverse.arkcraft.common.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.uberverse.arkcraft.common.block.crafter.BlockSmithy;
import com.uberverse.arkcraft.init.ARKCraftBlocks;

public class ItemSmithy extends ItemBlockARK
{
	public ItemSmithy(Block block)
	{
		super(block);
		this.setMaxStackSize(1);
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 *
	 * @param pos
	 *            The block being right-clicked
	 * @param side
	 *            The side being right-clicked
	 */
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn,
			World worldIn, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ)
	{
		if (worldIn.isRemote)
		{
			return true;
		}
		else if (side != EnumFacing.UP)
		{
			return false;
		}
		else
		{
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();
			boolean flag = block.isReplaceable(worldIn, pos);
			if (!flag)
			{
				pos = pos.up();
			}
			int i = MathHelper.floor_double(
					(double) (playerIn.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			EnumFacing enumfacing1 = EnumFacing.getHorizontal(i);
			// BlockPos blockpos1 = pos.offset(enumfacing1); // like a bed,
			// placed vertically
			BlockPos blockpos1 = pos.offset(enumfacing1.rotateYCCW());
			boolean flag1 = block.isReplaceable(worldIn, blockpos1);
			boolean flag2 = worldIn.isAirBlock(pos) || flag;
			boolean flag3 = worldIn.isAirBlock(blockpos1) || flag1;

			if (playerIn.canPlayerEdit(pos, side, stack)
					&& playerIn.canPlayerEdit(blockpos1, side, stack))
			{
				if (flag2 && flag3
						&& World.doesBlockHaveSolidTopSurface(worldIn,
								pos.down())
						&& World.doesBlockHaveSolidTopSurface(worldIn,
								blockpos1.down()))
				{
					IBlockState iblockstate1 =
							ARKCraftBlocks.smithy.getDefaultState()
									.withProperty(BlockSmithy.FACING,
											enumfacing1)
									.withProperty(BlockSmithy.PART,
											BlockSmithy.EnumPart.RIGHT);
					if (worldIn.setBlockState(pos, iblockstate1, 3))
					{
						IBlockState iblockstate2 =
								iblockstate1.withProperty(BlockSmithy.PART,
										BlockSmithy.EnumPart.LEFT);
						worldIn.setBlockState(blockpos1, iblockstate2, 3);
					}
					--stack.stackSize;
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
	}
}
