package tfcanimalsplus;

import java.io.File;

import tfcanimalsplus.core.ModCommonProxy;
import tfcanimalsplus.core.ModDetails;
import tfcanimalsplus.core.ModEntities;
import tfcanimalsplus.core.ModOptions;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

// before:*		required-after:FML		
@Mod(modid = ModDetails.ModID, name = ModDetails.ModName, version = ModDetails.ModVersion, dependencies = ModDetails.ModDependencies)
public class TFCAnimalsPlus
{
	@Instance(ModDetails.ModID)
	public static TFCAnimalsPlus instance;

	@SidedProxy(clientSide = ModDetails.CLIENT_PROXY_CLASS, serverSide = ModDetails.SERVER_PROXY_CLASS)
	public static ModCommonProxy proxy;
	
	public File getMinecraftDirectory()
	{
		return proxy.getMinecraftDirectory();
	}
	
	@EventHandler
	public void preInitialize(FMLPreInitializationEvent e)
	{
		String updatePath = ModDetails.VersionCheckerUpdatePath;
		FMLInterModComms.sendRuntimeMessage(ModDetails.ModID, "VersionChecker", "addVersionCheck", updatePath);

		instance = this;
		
		ModOptions.loadSettings();
	}

	@EventHandler
	public void initialize(FMLInitializationEvent e)
	{
	}

	@EventHandler
	public void postInitialize(FMLPostInitializationEvent e)
	{
		ModEntities.initialise();
	}
}
