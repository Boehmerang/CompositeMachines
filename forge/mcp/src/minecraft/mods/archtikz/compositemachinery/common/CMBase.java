package mods.archtikz.compositemachinery.common;

import mods.archtikz.compositemachinery.client.ClientPacketHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

	public static Item compositeSheet = new ItemCmpSheet(800, "compositeSheet").setUnlocalizedName("compositeSheet");
	public static Item acrylicGlassSheet = new ItemStrPlastic(801, "acrylicGlassSheet").setUnlocalizedName("acrylicGlassSheet");
	public static Block acrylicGlass = new BlockAcrylicGlass(802, Material.glass, "acrylicGlass").setUnlocalizedName("acrylicGlass").setHardness(1.5F).setResistance(500F);
	//public static Block oilFabricator = new Block
	
	@Instance("CMV1.0")
	public static CMBase instance = new CMBase();

	@SidedProxy(clientSide = "mods.archtikz.compositemachinery.client.ClientProxy", serverSide = "mods.archtikz.compositemachinery.common.CommonProxy") //Tells Forge the location of your proxies
	public static CommonProxy proxy;

	@Mod.Init
	public void Init(FMLInitializationEvent event) {
		gameRegistry();
		languageRegistry();
		recipeRegistry();
	}

	public void recipeRegistry() {
		GameRegistry.addRecipe(new ItemStack(compositeSheet, 1), new Object[] {
			"III", "PPP", "III", 'I', Item.ingotIron, 'P', acrylicGlassSheet
		});
		GameRegistry.addRecipe(new ItemStack(acrylicGlass, 1), new Object[] {
			"PPP", "PPP", "PPP",'P', acrylicGlassSheet
		});
	}
	
	public void languageRegistry() {
		LanguageRegistry.addName(compositeSheet, "Composite Sheet");
		LanguageRegistry.addName(acrylicGlassSheet, "Acrylic Glass Sheet");
		LanguageRegistry.addName(acrylicGlass, "Acrylic Glass");
	}

	public void gameRegistry() {
		GameRegistry.registerItem(compositeSheet, "Composite Sheet");
		GameRegistry.registerItem(acrylicGlassSheet, "Acrylic Glass Sheet");
		GameRegistry.registerBlock(acrylicGlass, "Acrylic Glass");
	}

	@Mod.PreInit
	public void PreInit(FMLPreInitializationEvent event) {

	}

	@Mod.PostInit
	public void PostInit(FMLPostInitializationEvent event) {

	}
}
		