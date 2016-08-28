package com.uberverse.arkcraft.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

/**
 * @author wildbill22
 *         Like InventoryBasic
 */
public class InventoryPlayerCrafting implements IInventory
{
    private String inventoryTitle;
    private boolean hasCustomName;
    private EntityPlayer player;

    public InventoryPlayerCrafting(String title, boolean customName, EntityPlayer player)
    {
        this.inventoryTitle = title;
        this.hasCustomName = customName;
        this.player = player;
    }

    public void loadInventoryFromNBT(NBTTagCompound nbt)
    {
        final byte NBT_TYPE_COMPOUND = 10;
        NBTTagList dataForAllItems = nbt.getTagList("Items", NBT_TYPE_COMPOUND);
        loadInventoryFromNBT(dataForAllItems);
    }
    
    public void getPlayerInventory(EntityPlayer player)
    {
    	player.getInventory();
    }

    public void loadInventoryFromNBT(NBTTagList nbt)
    {
        int i;
        for (i = 0; i < this.getSizeInventory(); ++i)
        {
            this.setInventorySlotContents(i, (ItemStack) null);
        }
        for (i = 0; i < nbt.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbt.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;

            if (j >= 0 && j < this.getSizeInventory())
            {
                this.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound));
            }
        }
    }

    public void saveInventoryToNBT(NBTTagCompound nbt)
    {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.getSizeInventory(); ++i)
        {
            ItemStack itemstack = this.getStackInSlot(i);
            if (itemstack != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte) i);
                itemstack.writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
//                LogHelper.info("InventoryPlayerCrafting: Saved a " + itemstack.getItem() + " to inventory.");
            }
        }
        nbt.setTag("Items", nbttaglist);
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    public ItemStack[] getItemStacks()
    {
        return player.getInventory();
    }

    @Override
    public String getName()
    {
        return this.inventoryTitle;
    }

    @Override
    public boolean hasCustomName()
    {
        return hasCustomName;
    }

    @Override
    public IChatComponent getDisplayName()
    {
        return (IChatComponent) (this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
    }

    @Override
    public int getSizeInventory()
    {
        return this.player.inventory.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
    	return this.player.inventory.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.getStackInSlot(index) != null)
        {
            ItemStack itemstack;
            if (this.getStackInSlot(index).stackSize <= count)
            {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, null);
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.getStackInSlot(index).splitStack(count);
                if (this.getStackInSlot(index).stackSize == 0)
                {
                    this.setInventorySlotContents(index, null);
                }
                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.getStackInSlot(index) != null)
        {
            ItemStack itemstack = this.getStackInSlot(index);
            this.setInventorySlotContents(index, null);
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        //this.setInventorySlotContents(index, stack);
        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void markDirty()
    {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < this.getItemStacks().length; ++i)
        {
            this.setInventorySlotContents(i, null);
        }
    }
}
