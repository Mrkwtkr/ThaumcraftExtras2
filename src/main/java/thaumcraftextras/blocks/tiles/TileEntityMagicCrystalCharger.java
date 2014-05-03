package thaumcraftextras.blocks.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.Constants;
import thaumcraftextras.api.interfaces.IMagicEnergyContainerItem;
import thaumcraftextras.api.misc.classes.MagicEnergy;
import thaumcraftextras.api.misc.tiles.MagicEnergyReceiver;

public class TileEntityMagicCrystalCharger extends MagicEnergyReceiver implements ISidedInventory{
	
	public TileEntityMagicCrystalCharger()
	{
		storage = new MagicEnergy(1000, 5);
	}
	public MagicEnergy storage;
    int add = 2;
    public ItemStack[] stacks = new ItemStack[1];
    int energy;
    public static final String ENERGY = "ENERGY_MAGIC";
    
    @Override
    public void updateEntity() 
    {
    	if(!worldObj.isRemote){
    		if(getStackInSlot(0) != null){
    			if(getStackInSlot(0).getItem() instanceof IMagicEnergyContainerItem){ 
    				ItemStack con = getStackInSlot(0);
    					if(!(con.getItemDamage() - add >= 0)){
    						isDone = true;
    						worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    					}else if((con.getItemDamage() - add >= 0)){
    						if(hasEnoughEnergy()){
    							chargeItem(con, add);
    							decreaseEnergy(storage.getMaxTransfer());
    						}
    					}else{
    						isDone = true;
    						worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    					}
    				}
    		}else{
    		}
    	}
    }
    
    public void chargeItem(ItemStack con, int add){
		con.setItemDamage(con.getItemDamage() - add);
    }
    
    @Override
    public boolean hasEnoughEnergy(){
    	if(storage.getEnergy() > 0)
    		return (storage.getEnergy() - storage.getMaxTransfer() >= 0);
    	
    	return false;
//    	if(storage.getEnergy() - storage.getMaxTransfer() >= 0)
//    		return true;
//    	else if(storage.getMaxEnergy() - storage.getEnergy() < 0)
//    		return false;
//    	else if(storage.getMaxEnergy() - storage.getEnergy() == 0)
//    		return false;
//    	else if(storage.getEnergy() == 0)
//    		return false;
//    	else
//    		return false;
    }
    
	@Override
	public boolean shouldReceive()
	{
//		if(storage.getEnergy() + storage.getMaxTransfer() <= storage.getMaxEnergy()){
			return (storage.getEnergy() + storage.getMaxTransfer() <= storage.getMaxEnergy());
//		}
////		if(getStackInSlot(0) != null){
////			if(getStackInSlot(0).getItem() instanceof IMagicEnergyContainerItem){
////				ItemStack con = getStackInSlot(0);
////						if((con.getItemDamage() - add >= 0)){
////							return true;
////						}
////			}
////		}
//		return false;
	}

	@Override
	public int getEnergy() {
		return storage.getEnergy();
	}

	@Override
	public int getMaxEnergy() {
		return storage.getMaxEnergy();
	}

	@Override
	public void setEnergy(int energy) {
		storage.setEnergy(energy);
	}

	@Override
	public void increaseEnergy(int energy) {
		storage.addEnergy(energy);
	}
	
	@Override
	public void decreaseEnergy(int energy) {
		storage.removeEnergy(energy);
	}
	
	@Override
	public int getMaxTransfer(){
		return storage.getMaxTransfer();
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		if(this.getEnergy() > 0)
			nbt.setInteger(ENERGY, this.getEnergy());
		
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < stacks.length; i++) {
                ItemStack stack = stacks[i];
                if (stack != null) {
                        NBTTagCompound tag = new NBTTagCompound();
                        tag.setByte("Slot", (byte) i);
                        stack.writeToNBT(tag);
                        itemList.appendTag(tag);
                }
        }
        nbt.setTag("Inventory", itemList);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		setEnergy(nbt.getInteger(ENERGY));

		
		  NBTTagList tagList = nbt.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
          for (int i = 0; i < tagList.tagCount(); i++) {
                  NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
                  byte slot = tag.getByte("Slot");
                  if (slot >= 0 && slot < stacks.length) {
                	  stacks[slot] = ItemStack.loadItemStackFromNBT(tag);
                  }
          }
	}
	
	@Override
	public Packet getDescriptionPacket() {
	    NBTTagCompound tagCompound = new NBTTagCompound();
	    writeToNBT(tagCompound);
	    
	    return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, -999, tagCompound);
	}
	
	@Override
	public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
		this.readFromNBT(packet.func_148857_g());
	}

	@Override
	public int getSizeInventory() {
		return stacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return stacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (stacks[i] != null)
        {

            if (stacks[i].stackSize <= j)
            {
                ItemStack itemstack = stacks[i];
                stacks[i] = null;
                return itemstack;
            }
            else
            {
                ItemStack itemstack1 = stacks[i].splitStack(j);

                if (stacks[i].stackSize == 0)
                {
                	stacks[i] = null;
                }

                return itemstack1;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
	      if (stacks[i] != null)
	        {
	            ItemStack itemstack = stacks[i];
	            stacks[i] = null;
	            return itemstack;
	        }
	        else
	        {
	            return null;
	        }
	      }

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		stacks[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		} 
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		  if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this)
	         {
	                 return false;
	         }

	         return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	     }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return false; 
	}

	@Override
	public String getInventoryName() {
		return "tce.charger_crystal";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void openInventory() {
		
	}

	@Override
	public void closeInventory() {		
	}
}