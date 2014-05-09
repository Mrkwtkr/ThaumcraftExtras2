package thaumcraftextras.blocks.tiles;

import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraftextras.main.ThaumcraftExtras;

public class TileEntityNoMove extends TileEntity{

	@SuppressWarnings("unchecked")
	@Override
	public void updateEntity()
	{
			double range = 15.0D;
			
			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord - range, yCoord - range, zCoord - range, xCoord + range, yCoord + range, zCoord + range);
			List<Entity> entities = worldObj.selectEntitiesWithinAABB(Entity.class, bb, getSelector());
			
			for(Entity entity : entities){
				double xPos = entity.posX;
				double yPos = entity.posY;
				double zPos = entity.posZ;
			
				float distance = (float) ((xCoord - xPos) * (xCoord - xPos) + (yCoord - yPos) * (yCoord - yPos) + (zCoord - zPos) * (zCoord - zPos));
		
				if(!worldObj.isRemote && distance < 15 && entity instanceof EntityLiving && !(entity instanceof EntityPlayer)){
					EntityLiving living = (EntityLiving)entity;
					living.motionX = 0.0D;
					living.motionY = 0.0D;
					living.motionZ = 0.0D;
					ThaumcraftExtras.proxy.spawnTrail(worldObj, xPos, yPos, zPos, (float)xCoord + 0.5F, (float)yCoord + 1.4F, (float)zCoord + 0.5F, 0);
				}
			}
	}
	
	public IEntitySelector getSelector(){
		return new IEntitySelector(){
			@Override
			public boolean isEntityApplicable(Entity entity) {
				return entity instanceof EntityLiving;}};}
}