package net.minecraft.command;

import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;

public class CommandEnchant extends CommandBase
{
    public String getCommandName()
    {
        return "enchant";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return par1ICommandSender.translateString("commands.enchant.usage", new Object[0]);
    }

    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        if (par2ArrayOfStr.length < 2)
        {
            throw new WrongUsageException("commands.enchant.usage", new Object[0]);
        }
        else
        {
            EntityPlayerMP entityplayermp = func_82359_c(par1ICommandSender, par2ArrayOfStr[0]);
            int i = parseIntBounded(par1ICommandSender, par2ArrayOfStr[1], 0, Enchantment.enchantmentsList.length - 1);
            int j = 1;
            ItemStack itemstack = entityplayermp.getCurrentEquippedItem();

            if (itemstack == null)
            {
                notifyAdmins(par1ICommandSender, "commands.enchant.noItem", new Object[0]);
            }
            else
            {
                Enchantment enchantment = Enchantment.enchantmentsList[i];

                if (enchantment == null)
                {
                    throw new NumberInvalidException("commands.enchant.notFound", new Object[] {Integer.valueOf(i)});
                }
                else if (!enchantment.func_92089_a(itemstack))
                {
                    notifyAdmins(par1ICommandSender, "commands.enchant.cantEnchant", new Object[0]);
                }
                else
                {
                    if (par2ArrayOfStr.length >= 3)
                    {
                        j = parseIntBounded(par1ICommandSender, par2ArrayOfStr[2], enchantment.getMinLevel(), enchantment.getMaxLevel());
                    }

                    if (itemstack.hasTagCompound())
                    {
                        NBTTagList nbttaglist = itemstack.getEnchantmentTagList();

                        if (nbttaglist != null)
                        {
                            for (int k = 0; k < nbttaglist.tagCount(); ++k)
                            {
                                short short1 = ((NBTTagCompound)nbttaglist.tagAt(k)).getShort("id");

                                if (Enchantment.enchantmentsList[short1] != null)
                                {
                                    Enchantment enchantment1 = Enchantment.enchantmentsList[short1];

                                    if (!enchantment1.canApplyTogether(enchantment))
                                    {
                                        notifyAdmins(par1ICommandSender, "commands.enchant.cantCombine", new Object[] {enchantment.getTranslatedName(j), enchantment1.getTranslatedName(((NBTTagCompound)nbttaglist.tagAt(k)).getShort("lvl"))});
                                        return;
                                    }
                                }
                            }
                        }
                    }

                    itemstack.addEnchantment(enchantment, j);
                    notifyAdmins(par1ICommandSender, "commands.enchant.success", new Object[0]);
                }
            }
        }
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, this.getListOfPlayers()) : null;
    }

    protected String[] getListOfPlayers()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2)
    {
        return par2 == 0;
    }
}
