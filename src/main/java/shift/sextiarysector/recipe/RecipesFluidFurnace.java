package shift.sextiarysector.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import shift.sextiarysector.SSItems;

public class RecipesFluidFurnace {

	public static void addRecipes(RecipeSimpleFluid recipe)
    {

		recipe.add(new ItemStack(SSItems.stringBobbin,1),new ItemStack(SSItems.cloth, 1), null);
		recipe.add(new ItemStack(Items.water_bucket,1),new ItemStack(Items.stick, 1), new FluidStack(FluidRegistry.WATER, 1000));

    }

}
