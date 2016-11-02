package com.uberverse.arkcraft.common.entity.projectile;

import com.uberverse.arkcraft.common.entity.ITranquilizer;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * @author ?, Lewis_McReu
 */
public class EntityStone extends EntityThrowable implements ITranquilizer
{
	public EntityStone(World w)
	{
		super(w);
	}

	public EntityStone(World w, EntityLivingBase base)
	{
		super(w, base);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		/* Damage on impact */
		float dmg = 2;
		if (mop.entityHit != null)
		{
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), dmg);
			applyTorpor(mop.entityHit);
		}

		for (int i = 0; i < 4; i++)
		{
			this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
		if (this.worldObj.isRemote)
		{
			this.setDead();
		}
	}

	@Override
	public int getTorpor()
	{
		return 10;
	}
}