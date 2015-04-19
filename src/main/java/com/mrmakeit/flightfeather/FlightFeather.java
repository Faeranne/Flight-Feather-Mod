package com.mrmakeit.flightfeather;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = FlightFeather.MODID, version = FlightFeather.VERSION)
public class FlightFeather
{
    public static final String MODID = "flightfeather";
    public static final String VERSION = "0.1";
    public static Item feather;
    
    @EventHandler
    public void load(FMLPreInitializationEvent event)
    {
    	feather = new ItemFeather();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	GameRegistry.registerItem(feather, "Flight Feather");
    	ItemStack nillaFeather = new ItemStack(Items.feather);
    	ItemStack pistion = new ItemStack(Blocks.piston);
        GameRegistry.addShapelessRecipe(new ItemStack(feather,3), nillaFeather,pistion);
    }
}
