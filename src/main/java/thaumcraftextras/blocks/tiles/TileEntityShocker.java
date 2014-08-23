package thaumcraftextras.blocks.tiles;

import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import thaumcraft.client.fx.bolt.FXLightningBoltCommon;
import wasliecore.helpers.MathHelper;

public class TileEntityShocker extends TileEntity{
	
	@SuppressWarnings("unchecked")
	@Override
	public void updateEntity()
	{				
			double range = 8.0D;

			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord - range, yCoord - range, zCoord - range, xCoord + range, yCoord + range, zCoord + range);
			List<Entity> entities = worldObj.selectEntitiesWithinAABB(Entity.class, bb, getSelector());
			
			for(Entity entity : entities){
				double xPos = entity.posX;
				double yPos = entity.posY;
				double zPos = entity.posZ;
			
				float distance = (float) ((xCoord - xPos) * (xCoord - xPos) + (yCoord - yPos) * (yCoord - yPos) + (zCoord - zPos) * (zCoord - zPos));
		
				if(distance < 8 && entity instanceof EntityLiving && !(entity instanceof EntityPlayer)){
					EntityLiving living = (EntityLiving)entity;
					performAction(living, xPos, yPos, zPos);
				
//					Thaumcraft.proxy.sourceStreamFX(worldObj, (float)xPos, (float)yPos, (float)zPos, (float)xCoord, (float)yCoord, (float)zCoord, 0);
				}
			}
		}
	public int mode = 0;
	public int maxMode = 2;
	public void performAction(EntityLiving living, double xPos, double yPos, double zPos)
	{
		if(!worldObj.isRemote && mode == 0){
			living.setHealth(living.getHealth() - 0.2F);
			living.attackEntityFrom(DamageSource.generic, 0.2F);
//			living.performHurtAnimation();
//
			if(living.getHealth() - 0.2F == 0)
				living.onDeath(DamageSource.generic);
			
			FXLightningBoltCommon light = new FXLightningBoltCommon(worldObj, xCoord + 0.5, yCoord + 1.6, zCoord + 0.5, xPos, yPos + 0.5, zPos, worldObj.getSeed(), 1, 1);
			light.defaultFractal();
			light.type = 2;
			light.finalizeBolt();
//	        for (int a = 0; a < 5; a++) {
//	        	ThaumcraftExtras.proxy.spawnSprinkle((float)xPos + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.6F, (float)yPos + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.6F, (float)zPos + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.6F, 2.0F + this.worldObj.rand.nextFloat(), 2, 0.05F + this.worldObj.rand.nextFloat() * 0.05F);
//	        }
		}else if(!worldObj.isRemote && mode == 1){
			living.attackEntityFrom(DamageSource.inFire, 0.2F);
			if(living.getHealth() - 0.2F == 0)
				living.onDeath(DamageSource.inFire);
			
				living.setFire(2);
				
				FXLightningBoltCommon light = new FXLightningBoltCommon(worldObj, xCoord + 0.5, yCoord + 1.6, zCoord + 0.5, xPos, yPos + 0.5, zPos, worldObj.getSeed(), 1, 2);
				light.defaultFractal();
				light.type = 3;
				light.finalizeBolt();
//				living.onDeath(DamageSource.inFire)
//		        for (int a = 0; a < 5; a++) {
//		        	Thaumcraft.proxy.sparkle((float)xPos + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.6F, (float)yPos + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.6F, (float)zPos + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.6F, 2.0F + this.worldObj.rand.nextFloat(), 2, 0.05F + this.worldObj.rand.nextFloat() * 0.05F);
//		        }
		}else if(!worldObj.isRemote && mode == 2){
			living.motionY = 2.0F;
			living.hurtResistantTime = MathHelper.secondToTick(10);
//	        for (int a = 0; a < 5; a++) {
//	        	Thaumcraft.proxy.sparkle((float)xPos + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.6F, (float)yPos + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.6F, (float)zPos + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.6F, 2.0F + this.worldObj.rand.nextFloat(), 2, 0.05F + this.worldObj.rand.nextFloat() * 0.05F);
//	        }
		}
	}
	
	public IEntitySelector getSelector(){
		return new IEntitySelector(){
			@Override
			public boolean isEntityApplicable(Entity entity) {
				return entity instanceof EntityLiving;}};}
}