package mods.archtikz.compositemachinery.common;

import mods.archtikz.compositemachinery.client.ClientPacketHandler;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(clientSideRequired=true,serverSideRequired=false, //Whether client side and server side are needed
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"CMV1.0" }, packetHandler = ClientPacketHandler.class), //For clientside packet handling
serverPacketHandlerSpec = @SidedPacketHandler(channels = {}, packetHandler = ServerPacketHandler.class)) //For serverside packet handling
@Mod(modid="CMV1.0",name="CompositeMachinery",version="1.0") //Gives Forge extra info about your mod
public class CMBase {

	public static Item compositeSheet = new ItemCmpSheet(800, 1).setUnlocalizedName("compositesheet");
	
	@Instance("CMV1.0")
	public static CMBase instance = new CMBase();

	@SidedProxy(clientSide = "mods.archtikz.compositemachinery.client.ClientProxy", serverSide = "mods.archtikz.compositemachinery.common.CommonProxy") //Tells Forge the location of your proxies
	public static CommonProxy proxy;

	@Mod.Init
	public void Init(FMLInitializationEvent event) {
		gameRegistry();
		languageRegistry();
	}

	public void languageRegistry() {
		LanguageRegistry.addName(compositeSheet, "Composite Sheet");
	}

	public void gameRegistry() {
		GameRegistry.registerItem(compositeSheet, "Composite Sheet");
	}

	@Mod.PreInit
	public void PreInit(FMLPreInitializationEvent event) {

	}

	@Mod.PostInit
	public void PostInit(FMLPostInitializationEvent event) {

	}
}
		