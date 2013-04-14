package mods.archtikz.compositemachinery.common;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.components.common.BasicComponents;
import universalelectricity.components.common.block.BlockBasicMachine;
import universalelectricity.components.common.tileentity.TileEntityBatteryBox;
import universalelectricity.components.common.tileentity.TileEntityCoalGenerator;
import universalelectricity.components.common.tileentity.TileEntityElectricFurnace;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.prefab.block.BlockAdvanced;

public class CMBasicMachine extends BlockAdvanced {

	String texture1;

	private Icon iconMachineSide;
	private Icon iconInput;
	private Icon iconOutput;
	private Icon iconOilFabricator;
	private Icon iconCoalGenerator;
	private Icon iconBatteryBox;

	public CMBasicMachine(int id, int textureIndex) {
		super(id, UniversalElectricity.machine);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		   this.blockIcon = par1IconRegister.registerIcon("archtikz:" + "machine");
		 //  this.iconInput = par1IconRegister.registerIcon("archtikz:" + "machineinput");
		   this.iconOutput = par1IconRegister.registerIcon("archtikz:" + "machineoutput");
		   this.iconMachineSide = par1IconRegister.registerIcon("archtikz:" + "machineside");
		   this.iconOilFabricator = par1IconRegister.registerIcon("archtikz:" + "oilFabricator");
	}
	
	@Override
	public Icon getIcon(int side, int metadata)
	{
		if (side == metadata + 2)
		{
			return this.iconOilFabricator;
		}
		else if (side == ForgeDirection.getOrientation(metadata + 2).getOpposite().ordinal())
		{
			return this.iconInput;
		}
		else if (side == 0 || side == 1)
		{
			return this.blockIcon;
		}
		else
		{
			return this.iconMachineSide;
		}
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack)
	{
		int metadata = world.getBlockMetadata(x, y, z);

		int side = MathHelper.floor_double((entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int change = 0;

		switch (side)
		{
			case 0:
				change = 1;
				break;
			case 1:
				change = 2;
				break;
			case 2:
				change = 0;
				break;
			case 3:
				change = 3;
				break;
		}

		world.setBlockMetadataWithNotify(x, y, z, change, 3);
	}

	@Override
	public boolean onUseWrench(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		int metadata = par1World.getBlockMetadata(x, y, z);
		int original = metadata;

		int change = 0;

		// Re-orient the block
		switch (original)
		{
			case 0:
				change = 3;
				break;
			case 3:
				change = 1;
				break;
			case 1:
				change = 2;
				break;
			case 2:
				change = 0;
				break;
		}

		par1World.setBlockMetadataWithNotify(x, y, z, change, 3);
		return true;
	}

	/**
	 * Called when the block is right clicked by the player
	 */
	@Override
	public boolean onMachineActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		int metadata = par1World.getBlockMetadata(x, y, z);

		if (!par1World.isRemote)
		{
			par5EntityPlayer.openGui(BasicComponents.getFirstDependant(), 1, par1World, x, y, z);
			return true;
		}

		return true;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityCoalGenerator();
	}
}
