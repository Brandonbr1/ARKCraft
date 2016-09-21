package com.uberverse.arkcraft.common.item.tools;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

import com.google.common.collect.ImmutableSet;
import com.uberverse.arkcraft.ARKCraft;

public class ItemStonePick extends ItemPickaxe
{

	public ItemStonePick(ToolMaterial material)
	{
		super(material);
		this.setCreativeTab(ARKCraft.tabARK);
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack)
	{
		return ImmutableSet.of("pickaxe", "axe");
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block)
	{

		return block.getMaterial() != Material.iron
				&& block.getMaterial() != Material.anvil
				&& block.getMaterial() != Material.rock
						? super.getStrVsBlock(stack, block)
						: this.efficiencyOnProperMaterial;

	}

	public boolean isArkTool()
	{
		return true;
	}
}
