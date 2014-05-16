package thaumcraftextras.main.init;

import java.awt.Color;

import net.minecraft.item.ItemArmor.ArmorMaterial;
import thaumcraftextras.api.misc.items.MagicEnergyCrystal;
import thaumcraftextras.items.ItemEnergyHelmet;
import thaumcraftextras.items.ItemFlamingChestplate;
import thaumcraftextras.items.ItemMagicEnergyReader;
import thaumcraftextras.items.ItemPouchColored;
import thaumcraftextras.items.ItemXPExtractor;
import thaumcraftextras.items.ItemXPShard;
import thaumcraftextras.items.TCEItem;
import thaumcraftextras.items.TCEItemShard;
import thaumcraftextras.items.baubles.AmuletAngel;
import thaumcraftextras.items.baubles.AmuletGhost;
import thaumcraftextras.items.foci.beam.FocusClean;
import thaumcraftextras.items.foci.beam.FocusTessela;
import thaumcraftextras.items.foci.beam.FocusTrampoline;
import thaumcraftextras.items.foci.normal.FocusExchange;
import thaumcraftextras.items.foci.normal.FocusPechTrade;
import thaumcraftextras.items.foci.normal.FocusPotionEffect;
import thaumcraftextras.items.foci.normal.FocusReturn;
import thaumcraftextras.items.guide.ItemGuide;
import thaumcraftextras.items.scepters.TCEItemScepter;
import wasliecore.helpers.ColorHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class TCEItems {
	public static void init()
	{
		initItems();
		initFoci();
		initScepters();
		initArmor();
		initCrystals();
		initBaubles();
	}
	
	public static void initItems()
	{
		essenceMagic = new TCEItem("Essence of Magic", "essence_magic", "essenceMagic");
		essenceDark = new TCEItem("Essence of Darkness", "essence_dark", "essenceDark");
		essenceLight = new TCEItem("Essence of Light", "essence_light", "essenceLight");

		darkThaumium = new TCEItem("Dark Thaumium Ingot", "ingot_darkthaumium", "ingotDarkThaumium");
		darkThaumiumNugget = new TCEItem("Dark Thaumium Nugget", "nugget_darkthaumium", "nuggetDarkThaumium");
		ignisFuel = new TCEItem("Ignis Fuel", "fuel_ignis", "fuelIgnis");
		reader = new ItemMagicEnergyReader("Magic Energy Reader", "magic_reader");

		darkShard = new TCEItemShard("dark", ColorHelper.getColorCodeFromRGB(50, 50, 50));
		
		xpShard = new ItemXPShard("xp", ColorHelper.getColorCodeFromColor(Color.green));
		xpExtractor = new ItemXPExtractor("Experience Extractor");
				
		pouch_color = new ItemPouchColored();
		guide = new ItemGuide("Thaumic Energy Guide", "guide");
	}
	public static TCEItem essenceMagic;
	public static TCEItem essenceLight;
	public static TCEItem essenceDark;
	public static TCEItem darkThaumium;
	public static TCEItem darkThaumiumNugget;
	public static TCEItem ignisFuel;
	
	public static ItemMagicEnergyReader reader;
	public static ItemPouchColored pouch_color;

	public static TCEItemShard darkShard;	
	public static ItemXPShard xpShard;
	public static ItemXPExtractor xpExtractor;
	public static ItemGuide guide;

	public static void initFoci()
	{
		focusClean = new FocusClean(ColorHelper.getColorCodeFromRGB(0, 0, 100), "Wand Focus: Clean");
		focusReturn = new FocusReturn(ColorHelper.getColorCodeFromRGB(50, 50, 0), "Wand Focus: Return");
		focusTessela = new FocusTessela(ColorHelper.getColorCodeFromRGB(0, 0, 200), "Wand Focus: Shock");
		focusTrampoline = new FocusTrampoline(ColorHelper.getColorCodeFromRGB(100, 0, 100), "Wand Focus: Trampoline");
		focusPechTrade = new FocusPechTrade(ColorHelper.getColorCodeFromRGB(50, 0, 100), "Wand Focus: Pech Trade");
		focusPotion = new FocusPotionEffect(ColorHelper.getColorCodeFromRGB(0, 100, 100), "Wand Focus: Potion");
		focusExchange = new FocusExchange(ColorHelper.getColorCodeFromRGB(0, 50, 0), "Wand Focus: Exchange");
	}
	public static FocusClean focusClean;
	public static FocusReturn focusReturn;
	public static FocusTessela focusTessela;
	public static FocusTrampoline focusTrampoline;
	public static FocusPechTrade focusPechTrade;
	public static FocusPotionEffect focusPotion;
	public static FocusExchange focusExchange;

	public static void initArmor()
	{
		fireChestplate = new ItemFlamingChestplate("Flaming Chestplate", "chestplate_flame", ArmorMaterial.DIAMOND, 2, 1, fireChestplateDamage, 7);
		GameRegistry.registerItem(fireChestplate, fireChestplate.getUnlocalizedName());
		
		energyHelmet = new ItemEnergyHelmet("Energetic Helmet", "helmet_energy", ArmorMaterial.DIAMOND, 0, 0, energyHelmetDamage, 5);
		GameRegistry.registerItem(energyHelmet, energyHelmet.getUnlocalizedName());
	}
	public static ItemFlamingChestplate fireChestplate;
	public static int fireChestplateDamage = 300;
	public static ItemEnergyHelmet energyHelmet;
	public static int energyHelmetDamage = 300;
	
	public static void initCrystals()
	{
		crystal_1 = new MagicEnergyCrystal(100, ColorHelper.getColorCodeFromRGB(100, 0, 0), 1);
		crystal_1.setCreativeTab(TCETabs.tabMain);
		
		crystal_2 = new MagicEnergyCrystal(250, ColorHelper.getColorCodeFromRGB(0, 100, 0), 2);
		crystal_2.setCreativeTab(TCETabs.tabMain);
		
		crystal_3 = new MagicEnergyCrystal(500, ColorHelper.getColorCodeFromRGB(0, 0, 100), 3);
		crystal_3.setCreativeTab(TCETabs.tabMain);
		
		crystal_4 = new MagicEnergyCrystal(750, ColorHelper.getColorCodeFromRGB(100, 0, 100), 4);
		crystal_4.setCreativeTab(TCETabs.tabMain);
		
		crystal_5 = new MagicEnergyCrystal(1000, ColorHelper.getColorCodeFromRGB(0, 100, 100), 5);
		crystal_5.setCreativeTab(TCETabs.tabMain);
		
		crystal_6 = new MagicEnergyCrystal(10000, ColorHelper.getColorCodeFromRGB(100, 100, 0), 6);
		crystal_6.setCreativeTab(TCETabs.tabMain);
		
		crystal_Creative = new MagicEnergyCrystal(1999999999, ColorHelper.getColorCodeFromColor(Color.pink), 100);
		crystal_Creative.setCreativeTab(TCETabs.tabMain);
	}
	public static MagicEnergyCrystal crystal_1;
	public static MagicEnergyCrystal crystal_2;
	public static MagicEnergyCrystal crystal_3;
	public static MagicEnergyCrystal crystal_4;
	public static MagicEnergyCrystal crystal_5;
	public static MagicEnergyCrystal crystal_6;
	public static MagicEnergyCrystal crystal_Creative;
	
	public static void initScepters()
	{
		scepter = new TCEItemScepter(100, ColorHelper.getColorCodeFromRGB(0, 0, 200), "base");		
	}
	public static TCEItemScepter scepter;
	
	public static String use = "USE";
	
	public static void initBaubles()
	{
		amulet_angel = new AmuletAngel("Amulet of The Angelic", "amulet_angel");
		amulet_ghost = new AmuletGhost("Amulet of The Invisible", "amulet_ghost");
	}
	public static AmuletAngel amulet_angel;
	public static AmuletGhost amulet_ghost;

}