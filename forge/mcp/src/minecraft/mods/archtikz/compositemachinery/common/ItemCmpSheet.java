package mods.archtikz.compositemachinery.common;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCmpSheet extends Item {

	String texture;
	
	public ItemCmpSheet(int par1, String texture) {
		super(par1);
		setCreativeTab(CreativeTabs.tabMaterials);
		this.texture = texture;
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2Player, List par3List, boolean par4Boolean){ //Additional info (eg. the names of music discs)
		par3List.add("The basic composite sheet for this mod!");
	}

	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister ir)
	{
		iconIndex = ir.registerIcon("archtikz:" + texture);
		System.out.println("Hello");
	}
}
