package tfcanimalsplus.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EnumCreatureType;

import com.bioxx.tfc.WorldGen.TFCBiome;

public class EntitySpawnData 
{
	public boolean enableSpawn;
	public int spawnProbability;
	public int minSpawn;
	public int maxSpawn;
	public EnumCreatureType typeOfCreature;
	
	public TFCBiome[] biomes;
	
	public EntitySpawnData(boolean enableSpawn, int spawnProbability, int minSpawn, int maxSpawn, EnumCreatureType typeOfCreature, String[] biomeNames)
	{
		this.enableSpawn = enableSpawn;
		this.spawnProbability = Math.max(0, spawnProbability);
		this.minSpawn = Math.max(0, minSpawn);
		this.maxSpawn = Math.max(this.minSpawn, maxSpawn);
		this.typeOfCreature = typeOfCreature;
		
		List<TFCBiome> tempList = new ArrayList<TFCBiome>();

		if (biomeNames != null && biomeNames.length > 0)
		{
			for (String biomeName : biomeNames)
			{
				TFCBiome biome = getBiome(biomeName);
				if (biome != null)
					tempList.add(biome);
			}
		}
		
		this.biomes = tempList.toArray(new TFCBiome[0]);
	}
	
	private TFCBiome getBiome(String biomeName)
	{
		if (biomeName == null || biomeName.equalsIgnoreCase(""))
			return null;
		
		// get the TFC biomes list.
		TFCBiome[] biomeList = TFCBiome.getBiomeGenArray();
		
		// check for a valid biomes lists.
		if (biomeList == null || biomeList.length == 0)
			return null;
		
		for (TFCBiome biome : biomeList)
		{
			if (biome == null || biome.biomeName == null) continue;
			
			String tfcBiomeName = biome.biomeName.toLowerCase().replace(" ", "");
			if (biomeName.equalsIgnoreCase(tfcBiomeName))
				return biome;
		}

		return null;
	}
}
