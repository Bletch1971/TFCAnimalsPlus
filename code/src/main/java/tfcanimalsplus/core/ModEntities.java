package tfcanimalsplus.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import tfcanimalsplus.common.EntitySpawnData;
import tfcanimalsplus.core.ModDetails;

import com.bioxx.tfc.WorldGen.TFCBiome;

import cpw.mods.fml.common.Loader;

public class ModEntities 
{
	public static final String NAMESPACE_BASE = "clickme.animals.entity";
	
	public static void initialise()
	{
		if (!Loader.isModLoaded(ModDetails.MODID_ANIMALS_PLUS)) 
			return;
			
		System.out.println("[" + ModDetails.ModName + "] Registering Entities");
		
		registerSpawning();
		
		System.out.println("[" + ModDetails.ModName + "] Done Registering Entities");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void registerSpawning()
	{
		List<Class> entityClasses = new ArrayList<Class>();
		
		// get a list of the registered entities.
		Collection values = EntityList.stringToClassMapping.values();
		for (Object value : values)
		{
			if (!(value instanceof Class)) continue;
			
			Class clazz = (Class)value;
			if (!clazz.getName().startsWith(NAMESPACE_BASE)) continue;
			
			entityClasses.add(clazz);
		}
		
		HashMap<String, EntitySpawnData> entitySpawnList = ModOptions.entitySpawnList;
		
		// loop through each of the entities.
		for (Class entityClass : entityClasses)
		{
			if (entityClass == null) continue;
			
			String className = entityClass.getSimpleName();
			EntitySpawnData entitySpawnData = entitySpawnList.get(className);
			if (entitySpawnData == null || !entitySpawnData.enableSpawn) 
			{
				System.out.println("[" + ModDetails.ModName + "] Entity not registered ("+className+")");
				continue;
			}
			
			addSpawn(entityClass, entitySpawnData.spawnProbability, entitySpawnData.minSpawn, entitySpawnData.maxSpawn, entitySpawnData.typeOfCreature, entitySpawnData.biomes);
		}
	}

	/**
	 * 
	 * @param entityClass The class of the entity to be spawned.
	 * @param spawnProbability is the weighted probability of your mob spawning. See the constructor in BiomeGenBase.java to see the rarity of vanilla mobs; Sheep are probability 10 while Endermen are probability 1.
	 * @param minSpawn is the minimum number of creatures in a spawn group. Setting it to "3" for instance would ensure that no fewer than 3 of your mob spawn at a time.
	 * @param maxSpawn is the maximum number of creatures in a spawn group.
	 * @param typeOfCreature represents the "rules" Minecraft uses to determine spawning, based on creature type.
	 * @param biomes is an optional parameter of type TFCBiome that limits the creature spawn to one or more biome types.
	 */
    private static void addSpawn(Class <? extends EntityLiving > entityClass, int spawnProbability, int minSpawn, int maxSpawn, EnumCreatureType typeOfCreature, TFCBiome... biomes)
    {
        for (TFCBiome biome : biomes)
        {
            @SuppressWarnings("unchecked")
            List<SpawnListEntry> spawns = biome.getSpawnableList(typeOfCreature);

            for (SpawnListEntry entry : spawns)
            {
                // Adjusting an existing spawn entry
                if (entry.entityClass == entityClass)
                {
                    entry.itemWeight = spawnProbability;
                    entry.minGroupCount = minSpawn;
                    entry.maxGroupCount = maxSpawn;
                    break;
                }
            }

            spawns.add(new SpawnListEntry(entityClass, spawnProbability, minSpawn, maxSpawn));
            System.out.println("[" + ModDetails.ModName + "] Entity registered ("+entityClass.getSimpleName()+";"+biome.biomeName+";"+spawnProbability+";"+minSpawn+";"+maxSpawn+";"+typeOfCreature.name()+")");
        }
    }
}
