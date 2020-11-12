package tfcanimalsplus.core;

public class ModDetails 
{
	public static final String ModID = "tfcanimalsplus";
	public static final String ModName = "TFCAnimalsPlus";

	public static final int VersionMajor = 1;
	public static final int VersionMinor = 2;
	public static final int VersionRevision = 4;

	public static final String ModVersion = VersionMajor + "." + VersionMinor + "." + VersionRevision;
	public static final String ModDependencies = "after:TerraFirmaCraft";
	public static final String ModChannel = "TFCAnimalsPlus";
	public static final String SERVER_PROXY_CLASS = "tfcanimalsplus.core.ModCommonProxy";
	public static final String CLIENT_PROXY_CLASS = "tfcanimalsplus.core.ModClientProxy";
	
	public static final String AssetPath = "/assets/" + ModID + "/";
	public static final String AssetPathGui = "textures/gui/";
	
	public static final String ConfigFilePath = "/config/";
	public static final String ConfigFileName = "TFCAnimalsPlus.cfg";
	
	public static final String VersionCheckerUpdatePath = "https://dl.dropboxusercontent.com/u/87519140/TFC/animalsplus/1.2/tfcanimalsplus.json";

	public static final String MODID_ANIMALS_PLUS = "animalsPlus";
	public static final String MODID_TFC = "terrafirmacraft";

	public static final String MODNAME_ANIMALS_PLUS= "Animals+";
	public static final String MODNAME_TFC = "TerraFirmaCraft";
}
