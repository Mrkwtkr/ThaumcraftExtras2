package thaumcraftextras.main.init.intergration.computercraft;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrel;
import thaumcraftextras.blocks.tiles.TileEntityMagicBattery;
import thaumcraftextras.blocks.tiles.TileEntityMagicCrystalCharger;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

public class PeripheralHandler implements IPeripheralProvider {

	@Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileEntityMagicBattery)
			return new MagicBatteryPeripheral((TileEntityMagicBattery) tile);
		
		if(tile instanceof TileEntityMagicCrystalCharger)
			return new CrystalChargerPeripheral((TileEntityMagicCrystalCharger) tile);
		
		if(tile instanceof TileEntityEssentiaBarrel)
			return new EssentiaBarrelPeripheral((TileEntityEssentiaBarrel) tile);
		return null;
	}

}