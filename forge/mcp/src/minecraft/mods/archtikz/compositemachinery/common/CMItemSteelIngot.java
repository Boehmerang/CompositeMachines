package mods.archtikz.compositemachinery.common;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CMItemSteelIngot extends Item {
	
	String texture;
	
	public CMItemSteelIngot(int par1, String texture) {
		super(par1);
		setCreativeTab(CreativeTabs.tabMaterials);
		this.texture = texture;
	}

	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister ir)
	{
		iconIndex = ir.registerIcon("archtikz:" + texture);
		System.out.println("Hello");
	}
}
