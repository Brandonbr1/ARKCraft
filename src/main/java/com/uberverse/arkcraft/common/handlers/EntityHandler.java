package com.uberverse.arkcraft.common.handlers;

import java.util.Random;

import com.uberverse.arkcraft.ARKCraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EntityHandler
{
	static int entityID = 50;

	public EntityHandler()
	{
	}

	public static void registerMonster(Class eClass, String name, BiomeGenBase... biomes)
	{
		int eggID = EntityRegistry.findGlobalUniqueEntityId();
		Random rand = new Random(name.hashCode());
		int mainColor = rand.nextInt() * 16777215;
		int secondColor = rand.nextInt() * 16777215;

		EntityRegistry.registerGlobalEntityID(eClass, name, eggID);
		EntityRegistry.addSpawn(eClass, 25, 2, 4, EnumCreatureType.CREATURE, biomes);
		EntityRegistry.registerModEntity(eClass, name, ++entityID, ARKCraft.instance, 64, 3, true);
		EntityList.entityEggs.put(Integer.valueOf(eggID),
				new EntityList.EntityEggInfo(++entityID, mainColor, secondColor));
	}

	public static void registerMonster(Class eClass, String name)
	{
		int eggID = EntityRegistry.findGlobalUniqueEntityId();
		Random rand = new Random(name.hashCode());
		int mainColor = rand.nextInt() * 16777215;
		int secondColor = rand.nextInt() * 16777215;

		EntityRegistry.registerGlobalEntityID(eClass, name, eggID);
		EntityRegistry.addSpawn(eClass, 25, 2, 4, EnumCreatureType.CREATURE, BiomeGenBase.beach,
				BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.birchForest,
				BiomeGenBase.extremeHills);
		EntityRegistry.registerModEntity(eClass, name, entityID, ARKCraft.instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(eggID),
				new EntityList.EntityEggInfo(entityID, mainColor, secondColor));
	}

	public static void registerEntityEgg(Class eClass, String name, BiomeGenBase... biomes)
	{
		int id = getUniqueEntityId();
		Random rand = new Random(name.hashCode());
		int mainColor = rand.nextInt() * 16777215;
		int secondColor = rand.nextInt() * 16777215;

		EntityRegistry.registerGlobalEntityID(eClass, name, id);
		// TODO Model error
		// EntityRegistry.registerModEntity(eClass, name, id,
		// ARKCraft.instance(), 64, 4, true);
		EntityRegistry.addSpawn(eClass, 5, 2, 4, EnumCreatureType.CREATURE, biomes);
		EntityList.idToClassMapping.put(id, eClass);
		EntityList.entityEggs.put(Integer.valueOf(id),
				new EntityList.EntityEggInfo(id, mainColor, secondColor));
	}

	public static void registerEntity(Class<? extends Entity> entity, int primaryColor, int secondaryColor)
	{
		int id = getUniqueEntityId();
		EntityList.idToClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}

	public static int getUniqueEntityId()
	{
		while (EntityList.getStringFromID(entityID) != null)
			entityID++;
		return entityID;
	}

	// public static void registerPassive(Class eClass, String name) {
	// int entityID = EntityRegistry.findGlobalUniqueEntityId();
	// Random rand = new Random(name.hashCode());
	// int mainColor = rand.nextInt() * 16777215;
	// int secondColor = rand.nextInt() * 16777215;
	//
	// EntityRegistry.registerGlobalEntityID(eClass, name, entityID);
	// EntityRegistry.addSpawn(eClass, 15, 2, 4, EnumCreatureType.CREATURE,
	// BiomeGenBase.plains, BiomeGenBase.savanna,
	// BiomeGenBase.beach, BiomeGenBase.desert, BiomeGenBase.extremeHills,
	// BiomeGenBase.coldBeach, BiomeGenBase.jungleEdge,
	// BiomeGenBase.jungle, BiomeGenBase.plains, BiomeGenBase.swampland);
	// EntityRegistry.registerModEntity(eClass, name, entityID,
	// ARKCraft.instance(), 64, 10, true);
	// EntityList.entityEggs.put(Integer.valueOf(entityID), new
	// EntityList.EntityEggInfo(entityID, mainColor, secondColor));
	// }

	public static void registerModEntity(Class<? extends Entity> eClass, String name, Object mainClass, int trackRange, int updateFreq, boolean sVU)
	{
		EntityRegistry.registerModEntity(eClass, name, ++entityID, mainClass, trackRange,
				updateFreq, sVU);
	}

}
