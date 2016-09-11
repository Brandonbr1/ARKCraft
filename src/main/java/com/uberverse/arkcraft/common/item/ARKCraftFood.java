package com.uberverse.arkcraft.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.uberverse.arkcraft.ARKCraft;
import com.uberverse.arkcraft.init.ARKCraftItems;

/**
 * @author Vastatio
 */
public class ARKCraftFood extends ItemFood implements IDecayable
{

	private PotionEffect[] effects;
	private boolean alwaysEdible;
	public static int globalHealAmount; // Used For Adding XP On Register
	public int decayTime;

	public ARKCraftFood(int healAmount, float sat, boolean fav, boolean alwaysEdible, int decayTime, PotionEffect... effects)
	{
		super(healAmount, sat, fav);
		this.effects = effects;
		this.alwaysEdible = alwaysEdible;
		this.setCreativeTab(ARKCraft.tabARK);
		/**
		 * FIXME: Please do not check in code with warnings A few things to note
		 * about this: 1) The warning here, should not be ignored. I don't know
		 * why this isn't an error, because accessing a class variable (static)
		 * with "this." implies that it is an instance variable, and it is not.
		 * In other words, all food created with this class will be sharing this
		 * amount. So yes, in that sense it is a "global". 2) I think what you
		 * wanted was for each type of food to have its own healAmount value,
		 * but using a static will not do this.
		 *
		 * So maybe just a mistake? Then remove the static. If not talk to me
		 * about Java coding or read a tutorial on how static works. 3) We can
		 * discuss this in our next group coding meeting, but the intent of
		 * the @author on a file is that you don't edit the file without asking
		 * the author. Probably doesn't apply to this file, so maybe we
		 * shouldn't put our name on all files we check-in. 4) Don't check in
		 * any files with warnings if you can avoid it. There are only a few
		 * types of warnings you should leave in a file, probably just these
		 * two: "@SuppressWarnings({"unchecked", "rawtypes"})". Others if you
		 * leave them are because you plan to fix them eventually. (Like this
		 * one below.)
		 */
		globalHealAmount = healAmount;
		this.decayTime = decayTime;
		setMaxDamage(decayTime * 20);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
	{
		super.onFoodEaten(stack, worldIn, player);
		for (int i = 0; i < effects.length; i++)
		{
			if (!worldIn.isRemote && effects[i] != null && effects[i].getPotionID() > 0)
			{
				player.addPotionEffect(new PotionEffect(this.effects[i].getPotionID(),
						this.effects[i].getDuration(), this.effects[i].getAmplifier(),
						this.effects[i].getIsAmbient(), this.effects[i].getIsShowParticles()));
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
	{
		if (playerIn.canEat(this.alwaysEdible))
		{
			playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
		}

		return itemStackIn;
	}

	@Override
	public ItemFood setAlwaysEdible()
	{
		this.alwaysEdible = true;
		return this;
	}

	public static ItemStack getSeedForBerry(ItemStack stack)
	{
		if (stack != null)
		{
			if (stack.getItem() instanceof ARKCraftSeed)
			{
				if (stack.getItem() == ARKCraftItems.amarBerrySeed) { return new ItemStack(
						ARKCraftItems.amarBerry); }
				if (stack.getItem() == ARKCraftItems.azulBerrySeed) { return new ItemStack(
						ARKCraftItems.azulBerry); }
				if (stack.getItem() == ARKCraftItems.mejoBerrySeed) { return new ItemStack(
						ARKCraftItems.mejoBerry); }
				if (stack.getItem() == ARKCraftItems.narcoBerrySeed) { return new ItemStack(
						ARKCraftItems.narcoBerry); }
				if (stack.getItem() == ARKCraftItems.tintoBerrySeed) { return new ItemStack(
						ARKCraftItems.tintoBerry); }
			}
		}
		return null;
	}

	// Seconds that this food will tame a Dino
	public static int getItemFeedTime(ItemStack stack)
	{
		if (stack != null)
		{
			if (stack.getItem() instanceof ARKCraftFood)
			{
				if (stack.getItem() == ARKCraftItems.meat_cooked) { return 25; }
				if (stack.getItem() == ARKCraftItems.meat_raw) { return 10; }
				if (stack.getItem() == ARKCraftItems.primemeat_cooked) { return 50; }
				if (stack.getItem() == ARKCraftItems.primemeat_raw) { return 25; }
			}
		}
		return 0;
	}

	// Seconds that this food will keep a dino unconscious
	public static int getItemTorporTime(ItemStack stack)
	{
		if (stack != null)
		{
			if (stack.getItem() instanceof ARKCraftFood)
			{
				if (stack.getItem() == ARKCraftItems.narcoBerry) { return 25; }
			}
		}
		return 0;
	}

	public static int getXPFromSmelting()
	{
		return globalHealAmount / 2;
	}
	public int getMaxDecayTime(ItemStack stack){
		return decayTime;
	}
	/**
	 * allows items to add custom lines of information to the mouseover
	 * description
	 *
	 * @param tooltip
	 *            All lines to display in the Item's tooltip. This is a List of
	 *            Strings.
	 * @param advanced
	 *            Whether the setting "Advanced tooltips" is enabled
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer playerIn, List tooltip, boolean advanced)
	{
		tooltip.add("Decomposes in " + ((getMaxDamage() - itemStack.getItemDamage()) / 20) + " seconds");
	}
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
	{
		return slotChanged || (oldStack != null && newStack != null && (oldStack.getItem() != newStack.getItem()));
	}

	@Override
	public void decayTick(IInventory inventory, int itemSlot, double decayModifier, ItemStack stack) {
		if(stack.getMetadata() > (getMaxDecayTime(stack) * 20)){
			inventory.setInventorySlotContents(itemSlot, null);
		}else{
			stack.setItemDamage(MathHelper.floor_double(stack.getMetadata() + (20 * decayModifier)));
		}
	}
}
