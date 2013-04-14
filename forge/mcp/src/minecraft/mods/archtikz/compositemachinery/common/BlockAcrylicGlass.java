package mods.archtikz.compositemachinery.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAcrylicGlass extends Block {

	String texture;
	
	public BlockAcrylicGlass(int i, Material par1material, String texture) {
		super(i , Material.glass);
		setCreativeTab(CreativeTabs.tabBlock);
		this.texture = texture;
	}

	public Icon getBlockTextureFromSideAndMetadata(int i,int j){
		return this.blockIcon;
	}

	public void registerIcons(IconRegister iconRegister) {
	   this.blockIcon = iconRegister.registerIcon("archtikz:" + texture);
	}
	
	public int quantityDropped(Random r){ //This will stop the block from dropping
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() //Put 0 for fully transparent or opaque blocks, and 1 for semi-transparent
	{
         return 0;
	}

	public boolean isOpaqueCube() //Tells the game if the block is transparent
	{
         return false;
	}

	public boolean renderAsNormalBlock() //Tells the game how to render the block
	{
         return false;
	}

	protected boolean canSilkHarvest() //Tells the game if silk touch can harvest the block
	{
		return true;
	}

	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) //Renders neighbouring blocks
	{

	int i = par1IBlockAccess.getBlockId(par2, par3, par4);

	return i == blockID ? false:true;
	}
}
