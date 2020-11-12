package tfcanimalsplus.core;

import java.io.File;
import java.util.HashMap;

import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import tfcanimalsplus.common.EntitySpawnData;

public class ModOptions
{
	// General
	public static HashMap<String, EntitySpawnData> entitySpawnList = new HashMap<String, EntitySpawnData>();

	public static void loadSettings()
	{	
		System.out.println("[" + ModDetails.ModName + "] Loading options.");

		Configuration config;

		try
		{
			config = new Configuration(new File(tfcanimalsplus.TFCAnimalsPlus.instance.getMinecraftDirectory(), ModDetails.ConfigFilePath+ModDetails.ConfigFileName));
			config.load();
		} 
		catch (Exception ex) 
		{
			System.out.println("["+ModDetails.ModName+"] Error while trying to access settings configuration!");
			config = null;
		}
		
		/** Start Here **/

		// General
		entitySpawnList.clear();
		entitySpawnList.put("EntityButterfly", getEntitySpawnData(config, "EntityButterfly", true, 1, 1, 1, EnumCreatureType.ambient, new String[]{ "Plains", "HighPlains", "HighHills", "HighHillsEdge", "RollingHills" }));
		entitySpawnList.put("EntityCentipede", getEntitySpawnData(config, "EntityCentipede", true, 1, 1, 1, EnumCreatureType.ambient, new String[]{ "Plains", "HighPlains" }));
		entitySpawnList.put("EntityCricket", getEntitySpawnData(config, "EntityCricket", true, 1, 1, 1, EnumCreatureType.ambient, new String[]{ "Plains", "Swampland" }));
		entitySpawnList.put("EntityMoth", getEntitySpawnData(config, "EntityMoth", true, 1, 1, 1, EnumCreatureType.ambient, new String[]{ "Swampland", "HighHills", "HighHillsEdge", "Mountains", "MountainsEdge" }));

		entitySpawnList.put("EntityBird", getEntitySpawnData(config, "EntityBird", true, 1, 1, 1, EnumCreatureType.ambient, new String[]{ "Plains", "HighPlains", "HighHills", "HighHillsEdge", "RollingHills", "Mountains", "MountainsEdge" }));
		entitySpawnList.put("EntityDuck", getEntitySpawnData(config, "EntityDuck", true, 1, 1, 1, EnumCreatureType.ambient, new String[]{ "Lake" }));
		entitySpawnList.put("EntityLizard", getEntitySpawnData(config, "EntityLizard", true, 1, 1, 1, EnumCreatureType.ambient, new String[]{ "Plains" }));
		entitySpawnList.put("EntityMouse", getEntitySpawnData(config, "EntityMouse", true, 1, 1, 1, EnumCreatureType.ambient, new String[]{ "RollingHills" }));
		entitySpawnList.put("EntityPenguin", getEntitySpawnData(config, "EntityPenguin", false, 1, 1, 1, EnumCreatureType.ambient, new String[]{ }));
		entitySpawnList.put("EntitySnake", getEntitySpawnData(config, "EntitySnake", true, 1, 1, 1, EnumCreatureType.ambient, new String[]{ "Swampland" }));

		entitySpawnList.put("EntityAngler", getEntitySpawnData(config, "EntityAngler", true, 1, 1, 1, EnumCreatureType.waterCreature, new String[]{ "DeepOcean" }));
		entitySpawnList.put("EntityFish", getEntitySpawnData(config, "EntityFish", true, 1, 1, 1, EnumCreatureType.waterCreature, new String[]{ "Ocean", "River", "DeepOcean" }));
		entitySpawnList.put("EntityMantaRay", getEntitySpawnData(config, "EntityMantaRay", true, 1, 1, 1, EnumCreatureType.waterCreature, new String[]{ "Ocean", "DeepOcean" }));
		entitySpawnList.put("EntityPiranha", getEntitySpawnData(config, "EntityPiranha", true, 1, 1, 1, EnumCreatureType.waterCreature, new String[]{ "Ocean", "DeepOcean" }));
		entitySpawnList.put("EntityShark", getEntitySpawnData(config, "EntityShark", true, 1, 1, 1, EnumCreatureType.waterCreature, new String[]{ "Ocean", "DeepOcean" }));
		entitySpawnList.put("EntityTropiFish", getEntitySpawnData(config, "EntityTropiFish", true, 1, 1, 1, EnumCreatureType.waterCreature, new String[]{ "Ocean", "River" }));
		entitySpawnList.put("EntityWhale", getEntitySpawnData(config, "EntityWhale", true, 1, 1, 1, EnumCreatureType.waterCreature, new String[]{ "DeepOcean" }));

		/** End Here*/
		if (config != null)
			config.save();

		System.out.println("[" + ModDetails.ModName + "] Done loading options.");
	}
	
	private static EntitySpawnData getEntitySpawnData(Configuration config, String entityName, boolean enableSpawn, int spawnProbability, int minSpawn, int maxSpawn, EnumCreatureType typeOfCreature, String[] biomeNames)
	{
		return new EntitySpawnData( config.get(entityName, "enableSpawn", enableSpawn, "set to true to spawn creature.").getBoolean(),
									config.get(entityName, "spawnProbability", spawnProbability, "the weighted probability of your mob spawning (higher number = higher chance, lower number = lower chance).").getInt(),
									config.get(entityName, "minSpawn", minSpawn, "the minimum number of creatures in a spawn group.").getInt(),
									config.get(entityName, "maxSpawn", maxSpawn, "the maximum number of creatures in a spawn group.").getInt(),
									typeOfCreature,
									config.get(entityName, "biomeNames", biomeNames, "the list of biome names that limits the creature spawn to one or more biomes.").getStringList()
								);
	}

	public static boolean getBooleanFor(Configuration config,String heading, String item, boolean value)
	{
		if (config == null)
			return value;
		try
		{
			Property prop = config.get(heading, item, value);
			return prop.getBoolean(value);
		}
		catch (Exception e)
		{
			System.out.println("[" + ModDetails.ModName + "] Error while trying to add Integer, config wasn't loaded properly!");
		}
		return value;
	}

	public static boolean getBooleanFor(Configuration config,String heading, String item, boolean value, String comment)
	{
		if (config == null)
			return value;
		try
		{
			Property prop = config.get(heading, item, value);
			prop.comment = comment;
			return prop.getBoolean(value);
		}
		catch (Exception e)
		{
			System.out.println("[" + ModDetails.ModName + "] Error while trying to add Integer, config wasn't loaded properly!");
		}
		return value;
	}

	public static int getIntFor(Configuration config, String heading, String item, int value)
	{
		if (config == null)
			return value;
		try
		{
			Property prop = config.get(heading, item, value);
			return prop.getInt(value);
		}
		catch (Exception e)
		{
			System.out.println("[" + ModDetails.ModName + "] Error while trying to add Integer, config wasn't loaded properly!");
		}
		return value;
	}

	public static int getIntFor(Configuration config,String heading, String item, int value, String comment)
	{
		if (config == null)
			return value;
		try
		{
			Property prop = config.get(heading, item, value);
			prop.comment = comment;
			return prop.getInt(value);
		}
		catch (Exception e)
		{
			System.out.println("[" + ModDetails.ModName + "] Error while trying to add Integer, config wasn't loaded properly!");
		}
		return value;
	}

	public static double getDoubleFor(Configuration config,String heading, String item, double value)
	{
		if (config == null)
			return value;
		try
		{
			Property prop = config.get(heading, item, value);
			return prop.getDouble(value);
		}
		catch (Exception e)
		{
			System.out.println("[" + ModDetails.ModName + "] Error while trying to add Double, config wasn't loaded properly!");
		}
		return value;
	}

	public static double getDoubleFor(Configuration config,String heading, String item, double value, String comment)
	{
		if (config == null)
			return value;
		try
		{
			Property prop = config.get(heading, item, value);
			prop.comment = comment;
			return prop.getDouble(value);
		}
		catch (Exception e)
		{
			System.out.println("[" + ModDetails.ModName + "] Error while trying to add Double, config wasn't loaded properly!");
		}
		return value;
	}

	public static String getStringFor(Configuration config, String heading, String item, String value)
	{
		if (config == null)
			return value;
		try
		{
			Property prop = config.get(heading, item, value);
			return prop.getString();
		} catch (Exception e)
		{
			System.out.println("[" + ModDetails.ModName + "] Error while trying to add String, config wasn't loaded properly!");
		}
		return value;
	}

	public static String getStringFor(Configuration config, String heading, String item, String value, String comment)
	{
		if (config == null)
			return value;
		try
		{
			Property prop = config.get(heading, item, value);
			prop.comment = comment;
			return prop.getString();
		} catch (Exception e)
		{
			System.out.println("[" + ModDetails.ModName + "] Error while trying to add String, config wasn't loaded properly!");
		}
		return value;
	}
}