package com.uberverse.arkcraft.common.item.ranged;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.uberverse.arkcraft.ARKCraft;
import com.uberverse.arkcraft.client.event.ClientEventHandler;
import com.uberverse.arkcraft.common.data.WeaponModAttributes;
import com.uberverse.arkcraft.common.entity.projectile.EntityProjectile;
import com.uberverse.arkcraft.common.entity.projectile.ProjectileType;
import com.uberverse.arkcraft.common.inventory.InventoryAttachment;
import com.uberverse.arkcraft.common.item.ammo.ItemProjectile;
import com.uberverse.arkcraft.init.ARKCraftBlocks;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Lewis_McReu
 * @author BubbleTrouble
 */
public abstract class ItemRangedWeapon extends ItemBow
{
	protected static final int MAX_DELAY = 72000;

	private Set<ItemProjectile> projectiles;
	private final int maxAmmo;
	private final int ammoConsumption;
	private final String defaultAmmoType;
	private final long shotInterval;
	private final float speed;
	private final float inaccuracy;
	private long nextShotMillis = 0;
	private double damage;
	private int range;

	public ItemRangedWeapon(String name, int durability, int maxAmmo, String defaultAmmoType, int ammoConsumption, double shotInterval, float speed, float inaccuracy, double damage, int range)
	{
		super();
		this.speed = speed;
		this.inaccuracy = inaccuracy;
		this.shotInterval = (long) shotInterval * 1000;
		this.ammoConsumption = ammoConsumption;
		this.defaultAmmoType = defaultAmmoType;
		this.maxAmmo = maxAmmo;
		this.setMaxDamage(durability);
		this.setMaxStackSize(1);
		this.projectiles = new HashSet<ItemProjectile>();
		this.setCreativeTab(ARKCraft.tabARK);
		this.setUnlocalizedName(name);
		this.damage = damage;
		this.range = range;
	}

	@Override
	public String getUnlocalizedName()
	{
		String s = super.getUnlocalizedName();
		return s.substring(s.indexOf('.') + 1);
	}

	public int getMaxAmmo()
	{
		return this.maxAmmo;
	}

	public long getShotInterval()
	{
		return this.shotInterval;
	}

	public int getAmmoConsumption()
	{
		return this.ammoConsumption;
	}

	public boolean registerProjectile(ItemProjectile projectile)
	{
		return this.projectiles.add(projectile);
	}

	public boolean isValidProjectile(Item item)
	{
		return this.projectiles.contains(item);
	}

	@Override
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
	{
		String jsonPath = ARKCraft.MODID + ":weapons/" + this.getUnlocalizedName();
		InventoryAttachment att = InventoryAttachment.create(stack);
		if (att != null)
		{
			if (att.isScopePresent())
			{
				jsonPath = jsonPath + "_scope";
			}
			else if (att.isFlashPresent())
			{
				jsonPath = jsonPath + "_flashlight";
			}
			else if (att.isLaserPresent())
			{
				jsonPath = jsonPath + "_laser";
			}
			else if (att.isSilencerPresent())
			{
				jsonPath = jsonPath + "_silencer";
			}
			else if (att.isHoloScopePresent())
			{
				jsonPath = jsonPath + "_holo_scope";
			}
		}
		if (isReloading(stack))
		{
			jsonPath = jsonPath + "_reload";
		}
		return new ModelResourceLocation(jsonPath, "inventory");
	}

	public Random getItemRand()
	{
		return new Random();
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return MAX_DELAY;
	}

	public void setReloading(ItemStack stack, EntityPlayer player, boolean reloading)
	{
		stack.getTagCompound().setBoolean("reloading", reloading);
	}

	public boolean isReloading(ItemStack stack)
	{
		checkNBT(stack);
		return stack.getTagCompound().getBoolean("reloading");
	}

	public int getReloadTicks(ItemStack stack)
	{
		return stack.getTagCompound().getInteger("reloadTicks");
	}

	private void setReloadTicks(ItemStack stack, int reloadTicks)
	{
		stack.getTagCompound().setInteger("reloadTicks", reloadTicks);
	}

	private void checkNBT(ItemStack stack)
	{
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (entityIn instanceof EntityPlayer)
		{
			if (isSelected)
			{
				InventoryAttachment inv = InventoryAttachment.create(stack);
				if (inv != null && inv.isFlashPresent())
				{
					updateFlashlight(entityIn);
				}
				else if (inv != null && inv.isLaserPresent())
				{
					updateLaser(entityIn);
				}
			}
			else if (isReloading(stack))
			{
				resetReload(stack, (EntityPlayer) entityIn);
			}
		}
	}

	private void updateLaser(Entity entityIn)
	{
		World w = entityIn.worldObj;
		MovingObjectPosition mop = rayTrace(entityIn, 35, 1.0F);
		if (mop.typeOfHit == MovingObjectType.BLOCK)
		{
			double x = mop.hitVec.xCoord;
			double y = mop.hitVec.yCoord;
			double z = mop.hitVec.zCoord;

			w.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z, 0, 0, 0, 0);
		}
	}

	private void resetReload(ItemStack stack, EntityPlayer player)
	{
		setReloading(stack, player, false);
		setReloadTicks(stack, 0);
	}

	private void updateFlashlight(Entity entityIn)
	{
		MovingObjectPosition mop = rayTrace(entityIn, 20, 1.0F);
		if (mop != null && mop.typeOfHit != MovingObjectPosition.MovingObjectType.MISS)
		{
			World world = entityIn.worldObj;
			BlockPos pos;

			if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
			{
				pos = mop.entityHit.getPosition();
			}
			else
			{
				pos = mop.getBlockPos();
				pos = pos.offset(mop.sideHit);
			}

			if (world.isAirBlock(pos))
			{
				world.setBlockState(pos, ARKCraftBlocks.blockLight.getDefaultState());
				world.scheduleUpdate(pos, ARKCraftBlocks.blockLight, 2);
			}
		}
	}

	public static Vec3 getPositionEyes(Entity player, float partialTick)
	{
		if (partialTick == 1.0F)
		{
			return new Vec3(player.posX, player.posY + (double) player.getEyeHeight(), player.posZ);
		}
		else
		{
			double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) partialTick;
			double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) partialTick + (double) player
					.getEyeHeight();
			double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) partialTick;
			return new Vec3(d0, d1, d2);
		}
	}

	public static MovingObjectPosition rayTrace(Entity player, double distance, float partialTick)
	{
		Vec3 vec3 = getPositionEyes(player, partialTick);
		Vec3 vec31 = player.getLook(partialTick);
		Vec3 vec32 = vec3.addVector(vec31.xCoord * distance, vec31.yCoord * distance, vec31.zCoord * distance);
		return player.worldObj.rayTraceBlocks(vec3, vec32, false, false, true);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (stack.stackSize <= 0 || player.isUsingItem()) { return stack; }

		if (canFire(stack, player))
		{
			if (this.nextShotMillis < System.currentTimeMillis())
				// Start aiming weapon to fire
				player.setItemInUse(stack, getMaxItemUseDuration(stack));
		}
		else
		{
			// Can't reload; no ammo
			if (!this.isReloading(stack))
			{
				soundEmpty(stack, world, player);
			}
		}
		return stack;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.NONE;
	}

	public void hasAmmoAndConsume(ItemStack stack, EntityPlayer player)
	{
		int ammoFinal = getAmmoQuantity(stack);
		String type = "";
		ItemStack[] inventory = player.inventory.mainInventory;
		for (int i = 0; i < inventory.length; i++)
		{
			ItemStack invStack = inventory[i];
			if (invStack != null) if (isValidProjectile(invStack.getItem()))
			{
				int stackSize = invStack.stackSize;
				type = invStack.getItem().getUnlocalizedName().substring(5);
				int ammo = stackSize < this.getMaxAmmo() - ammoFinal ? stackSize : this.getMaxAmmo() - ammoFinal;
				ammoFinal += ammo;

				invStack.stackSize = stackSize - ammo;
				if (invStack.stackSize < 1) inventory[i] = null;
				if (ammoFinal == this.getMaxAmmo()) break;
			}
		}
		if (ammoFinal > 0)
		{
			setAmmoType(stack, type);
			setAmmoQuantity(stack, ammoFinal);
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int timeLeft)
	{
		if (canFire(stack, player))
		{
			fire(stack, world, player, timeLeft);
		}
	}

	public boolean canReload(ItemStack stack, EntityPlayer player)
	{
		return getAmmoQuantity(stack) < getMaxAmmo() && !player.capabilities.isCreativeMode;
	}

	public boolean canFire(ItemStack stack, EntityPlayer player)
	{
		return (player.capabilities.isCreativeMode || isLoaded(stack, player));
	}

	public boolean hasAmmoInInventory(EntityPlayer player)
	{
		return findAvailableAmmo(player) != null;
	}

	public ItemProjectile findAvailableAmmo(EntityPlayer player)
	{
		for (ItemProjectile projectile : projectiles)
		{
			if (player.inventory.hasItem(projectile)) return projectile;
		}
		return null;
	}

	public int getAmmoQuantityInInventory(ItemStack stack, EntityPlayer player)
	{
		InventoryPlayer inventory = player.inventory;
		String type = getAmmoType(stack);
		Item item = GameRegistry.findItem(ARKCraft.MODID, type);
		int out = 0;
		if (type != null && inventory.hasItem(item))
		{
			for (ItemStack s : inventory.mainInventory)
			{
				if (s != null && s.getItem().equals(item))
				{
					out += s.stackSize;
				}
			}
		}
		return out;
	}

	public int getAmmoQuantity(ItemStack stack)
	{
		if (stack.hasTagCompound()) return stack.getTagCompound().getInteger("ammo");
		else return 0;
	}

	public void setAmmoQuantity(ItemStack stack, int ammo)
	{
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger("ammo", ammo);
	}

	public String getAmmoType(ItemStack stack)
	{
		String type = null;
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("ammotype")) type = stack.getTagCompound()
				.getString("ammotype");
		if (type == null || type.equals("")) type = this.getDefaultAmmoType();
		return type.toLowerCase();
	}

	public void setAmmoType(ItemStack stack, String type)
	{
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setString("ammotype", type);
	}

	public String getDefaultAmmoType()
	{
		return this.defaultAmmoType;
	}

	public boolean isLoaded(ItemStack stack, EntityPlayer player)
	{
		return getAmmoQuantity(stack) > 0 || player.capabilities.isCreativeMode;
	}

	public void soundEmpty(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		world.playSoundAtEntity(entityplayer, "random.click", 1.0F, 1.0F / 0.8F);
	}

	public void soundCharge(ItemStack stack, World world, EntityPlayer player)
	{
		String name = ARKCraft.MODID + ":" + this.getUnlocalizedName() + "_reload";
		world.playSoundAtEntity(player, name, 0.7F, 0.9F / (getItemRand().nextFloat() * 0.2F + 0.0F));
	}

	public abstract int getReloadDuration();

	public void applyProjectileEnchantments(EntityProjectile entity, ItemStack itemstack)
	{
		int damage = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemstack);
		if (damage > 0)
		{
			entity.setDamage(damage);
		}

		int knockback = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemstack);
		if (knockback > 0)
		{
			entity.setKnockbackStrength(knockback);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemstack) > 0)
		{
			entity.setFire(100);
		}
	}

	public final void postShootingEffects(ItemStack itemstack, EntityPlayer entityplayer, World world)
	{
		effectPlayer(itemstack, entityplayer, world);
		effectShoot(itemstack, world, entityplayer.posX, entityplayer.posY, entityplayer.posZ, entityplayer.rotationYaw,
				entityplayer.rotationPitch);
	}

	public abstract void effectPlayer(ItemStack itemstack, EntityPlayer entityplayer, World world);

	public void effectShoot(ItemStack stack, World world, double x, double y, double z, float yaw, float pitch)
	{
		String soundPath = ARKCraft.MODID + ":" + this.getUnlocalizedName() + "_shoot";
		InventoryAttachment att = InventoryAttachment.create(stack);
		if (att != null && att.isSilencerPresent()) soundPath = soundPath + "_silenced";
		world.playSoundEffect(x, y, z, soundPath, 1.5F, 1F / (this.getItemRand().nextFloat() * 0.4F + 0.7F));

		float particleX = -MathHelper.sin(((yaw + 23) / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F);
		float particleY = -MathHelper.sin((pitch / 180F) * 3.141593F) - 0.1F;
		float particleZ = MathHelper.cos(((yaw + 23) / 180F) * 3.141593F) * MathHelper.cos((pitch / 180F) * 3.141593F);

		for (int i = 0; i < 3; i++)
		{
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x + particleX, y + particleY, z + particleZ, 0.0D, 0.0D,
					0.0D);
		}
		world.spawnParticle(EnumParticleTypes.FLAME, x + particleX, y + particleY, z + particleZ, 0.0D, 0.0D, 0.0D);
	}

	public void fire(ItemStack stack, World world, EntityPlayer player, int timeLeft)
	{
		if (!world.isRemote)
		{
			for (int i = 0; i < getAmmoConsumption(); i++)
			{
				EntityProjectile p = createProjectile(stack, world, player);
				applyProjectileEnchantments(p, stack);
				if (p != null) world.spawnEntityInWorld(p);
			}
		}
		afterFire(stack, world, player);
	}

	protected void afterFire(ItemStack stack, World world, EntityPlayer player)
	{
		if (!player.capabilities.isCreativeMode) this.setAmmoQuantity(stack, this.getAmmoQuantity(stack)
				- ammoConsumption);
		int damage = 1;
		int ammo = this.getAmmoQuantity(stack);
		if (stack.getItemDamage() + damage > stack.getMaxDamage())
		{
			String type = this.getAmmoType(stack);
			Item i = GameRegistry.findItem(ARKCraft.MODID, type);
			ItemStack s = new ItemStack(i, ammo);
			player.inventory.addItemStackToInventory(s);
		}
		else if (ammo < 1)
		{
			if (hasAmmoInInventory(player) && FMLCommonHandler.instance().getSide().isClient())
			{
				ClientEventHandler.doReload();
			}
			else
			{
				this.setAmmoType(stack, "");
			}
		}
		this.nextShotMillis = System.currentTimeMillis() + this.shotInterval;
		stack.damageItem(damage, player);
		postShootingEffects(stack, player, world);
	}

	protected EntityProjectile createProjectile(ItemStack stack, World world, EntityPlayer player)
	{
		try
		{
			String type = this.getAmmoType(stack);

			Class<?> c = Class.forName("com.uberverse.arkcraft.common.entity." + ProjectileType.valueOf(type
					.toUpperCase()).getEntity());
			Constructor<?> con = c.getConstructor(World.class, EntityLivingBase.class, float.class, float.class,
					double.class, int.class);
			return (EntityProjectile) con.newInstance(world, player, this.speed, this.inaccuracy, this.damage,
					this.range);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void effectReloadDone(ItemStack stack, World world, EntityPlayer player)
	{
		// player.swingItem();
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers()
	{
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();
		this.addItemAttributeModifiers(multimap);
		return multimap;
	}

	public void addItemAttributeModifiers(Multimap<String, AttributeModifier> multimap)
	{
		multimap.put(WeaponModAttributes.RELOAD_TIME.getAttributeUnlocalizedName(), new AttributeModifier(
				"Weapon reloadtime modifier", this.getReloadDuration(), 0));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public int getItemEnchantability()
	{
		return 0;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
	{
		return oldStack != null && newStack != null && oldStack.getItem() != newStack.getItem() && slotChanged;
	}
}
