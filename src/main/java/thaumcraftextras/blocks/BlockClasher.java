package thaumcraftextras.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipeManager;
import thaumcraftextras.blocks.tiles.TileEntityClasher;
import thaumcraftextras.helpers.RenderingHelper;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockClasher extends BlockContainer{
	public BlockClasher() {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(TCETabs.tabMain);
		setBlockBounds(0f, 0f, 0f, 1f, 0.9f, 1f);
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + "clasher");
		
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	
	@Override
    public int getRenderType()
    {
        return RenderingHelper.render_clasher;
    }
	
	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir){
		blockIcon = ir.registerIcon("thaumcraft:arcaneearbottom");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int side) {
		return new TileEntityClasher();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
	        float hitY, float hitZ){
		if(!player.isSneaking()){
			if(!world.isRemote){
				if(player.getCurrentEquippedItem() != null && AdvancedAltarRecipeManager.advancedAltar.containsKey(player.getCurrentEquippedItem().getItem())){
					TileEntity tile = world.getTileEntity(x, y, z);
					TileEntityClasher te = (TileEntityClasher)tile;
					if(te.getStackInSlot(0) == null){
						te.setInventorySlotContents(0, player.getCurrentEquippedItem().copy());
						world.markBlockForUpdate(x, y, z);

						if(player.getCurrentEquippedItem().stackSize > 1)
							player.getCurrentEquippedItem().stackSize--;
						else
							player.setCurrentItemOrArmor(0, null);
					}
				}
			}
		}else{
			if(!world.isRemote && player.isSneaking()){
				if(player.getCurrentEquippedItem() == null){
					TileEntity tile = world.getTileEntity(x, y, z);
					TileEntityClasher te = (TileEntityClasher)tile;
					dropItems(world, x, y, z);
					te.setInventorySlotContents(0, null);
					world.markBlockForUpdate(x, y, z);
				}
			}
		}
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) 
	{
		dropItems(world, x, y, z);
		world.removeTileEntity(x, y, z);	
	}
	
	private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
                return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack item = inventory.getStackInSlot(i);

                if (item != null && item.stackSize > 0) {
                        float rx = rand.nextFloat() * 0.8F + 0.1F;
                        float ry = rand.nextFloat() * 0.8F + 0.1F;
                        float rz = rand.nextFloat() * 0.8F + 0.1F;

                        EntityItem entityItem = new EntityItem(world,
                                        x + rx, y + ry, z + rz,
                                        new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

                        float factor = 0.05F;
                        entityItem.motionX = rand.nextGaussian() * factor;
                        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                        entityItem.motionZ = rand.nextGaussian() * factor;
                        world.spawnEntityInWorld(entityItem);
                }
        }
    }
	
}