package thaumcraftextras.api.core;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipe;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipeManager;
import thaumcraftextras.api.core.recipes.ClasherRecipeManager;
import thaumcraftextras.api.core.recipes.DarkInfuserRecipeManager;
public class TCEApi {
	/**
	 * Entries using null will not be registered!
	 * @param input
	 * Input Item
	 * @param output
	 * Output ItemStack
	 */
	public static void addDarkInfusionRecipe(Item input, ItemStack output) {
		if(input == null || output == null){
			System.out.println("[TCE2] " +  " A mod is trying to register an invalid recipe, ignoring");
		}else{
			DarkInfuserRecipeManager.addDarkInfusionRecipe(input, output);
		}
	}
	public static DarkInfuserRecipeManager darkInfuser;

	public static void addClasherRecipe(Item input1, Item input2, ItemStack output) {
		if(input1 == null || input2 == null || output == null){
			System.out.println("[TCE2] " +  " A mod is trying to register an invalid recipe, ignoring");
		}else{
			ClasherRecipeManager.addClasherRecipe(input1, input2, output);
		}
	}
	public static ClasherRecipeManager clasher;

	public static void addAdvancedAltarRecipe(Item input, AdvancedAltarRecipe recipe)
	{
		if(input == null || recipe == null)
			System.out.println("[TCE2] " +  " A mod is trying to register an invalid recipe, ignoring");
		else
			AdvancedAltarRecipeManager.advancedAltar.put(input, recipe);
	}
	public static AdvancedAltarRecipeManager advancedAltar;
	
	public static void addExchange(Block input, Block output)
	{
		if(input == null || output == null){
			System.out.println("[TCE2] " +  " A mod is trying to register an invalid block, ignoring");
		}else{
			exchange.put(input, output);
			exchange.put(output, input);
		}
	}
	public static HashMap<Block, Block> exchange = new HashMap<Block, Block>();
	
	public static void addExchangeMeta(Block input, int meta)
	{
		if(input == null)
			System.out.println("[TCE2] " +  " A mod is trying to register an invalid block, ignoring");
		else
			exchangeMeta.put(input, meta);
	}
	public static HashMap<Block, Integer> exchangeMeta = new HashMap<Block, Integer>();
	
}