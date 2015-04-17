package com.mrmakeit.flightfeather;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFeather extends Item {

	boolean compensate;
	float lastDamage;
	public ItemFeather()
	{
		super();
		setCreativeTab(CreativeTabs.tabCombat);
		setUnlocalizedName(FlightFeather.MODID + ":" + "feather");
		setTextureName(FlightFeather.MODID + ":" + "feather");
		this.setMaxDamage(10);
	}
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		player.motionY = 0.5D;
		if(!world.isRemote){
			stack.damageItem(1, player);
		}
		if(stack.getItemDamage() >= stack.getMaxDamage()){
			player.inventory.consumeInventoryItem(this);
		}
		return stack;
	}
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		if(!world.isRemote){
			if(entity instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) entity;
				if(player.getCurrentEquippedItem() !=null && player.getCurrentEquippedItem().getItem() instanceof ItemFeather)
				{
					if(player.fallDistance > 4){
						compensate = true;
						lastDamage = player.fallDistance;
						player.fallDistance = 0.3F;
					}else if(!(player.fallDistance > 0)){
						if(compensate){
							compensate = false;
							stack.damageItem(3, player);
						}
					}
				}else{
					if(compensate){
						if(!(player.fallDistance > 0)){
							player.fallDistance = lastDamage;
							stack.damageItem(3, player);
							compensate = false;
						}
					}
				}
			}
		}
	}
	
}
