package tfcanimalsplus.core;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModClientProxy extends ModCommonProxy
{
	@Override
	public String getCurrentLanguage()
	{
		return Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode();
	}

	@Override
	public World getCurrentWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public boolean getGraphicsLevel()
	{
		Minecraft.getMinecraft();
		return Minecraft.isFancyGraphicsEnabled();
	}

	@Override
	public File getMinecraftDirectory()
	{
		return Minecraft.getMinecraft().mcDataDir;
	}

	@Override
	public boolean isRemote()
	{
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void loadOptions()
	{
		//Load our settings from the server
		ModOptions.loadSettings();
	}

	@Override
	public void registerGuiHandler()
	{
	}

	@Override
	public void registerHandlers()
	{
	}

	@Override
	public void registerKeys()
	{
		uploadKeyBindingsToGame();
	}

	@Override
	public void registerKeyBindingHandler()
	{
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderInformation()
	{
	}
	
	@Override
	public void registerTileEntities(boolean flag)
	{
		super.registerTileEntities(false);
		
		// TESR registers
	}

	@Override
	public void uploadKeyBindingsToGame()
	{
	}
}
