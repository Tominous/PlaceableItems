package me.ferdz.placeableitems.proxy;

import me.ferdz.placeableitems.block.tool.BlockSword;
import me.ferdz.placeableitems.init.ModBlocks;
import me.ferdz.placeableitems.state.EnumPreciseFacing;
import me.ferdz.placeableitems.state.tool.EnumSword;
import me.ferdz.placeableitems.state.tool.EnumToolMaterial;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		IStateMapper map = new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				EnumPreciseFacing facing = state.getValue(BlockSword.FACING);
				EnumToolMaterial material = state.getValue(BlockSword.MATERIAL);
				EnumSword model = state.getValue(BlockSword.MODEL);
				
				// this is used to remove the missing variant errors. The following models never actually happen in game
				if(facing == EnumPreciseFacing.D135 || facing == EnumPreciseFacing.D225 || facing == EnumPreciseFacing.D315 || facing == EnumPreciseFacing.D45) {
					if(model.getName().contains("side"))
						facing = EnumPreciseFacing.D0;
				}
				
				String location = "facing=" + facing.getName() + ",smodel=" + model.getName();
				return new ModelResourceLocation("placeableitems:tool/block_sword_" + material, location);
			}
		};
		
		ModelLoader.setCustomStateMapper(ModBlocks.blockSword, map);
	}
	
	@Override
	public void checkUpdate() {
	}
}