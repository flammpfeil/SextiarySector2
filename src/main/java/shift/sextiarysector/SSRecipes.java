package shift.sextiarysector;

import net.minecraft.item.crafting.CraftingManager;
import shift.sextiarysector.recipe.RecipeSimpleMachine;
import shift.sextiarysector.recipe.RecipesMachine;
import shift.sextiarysector.recipe.RecipesMillstone;
import shift.sextiarysector.recipe.RecipesNormalBlock;

public class SSRecipes {

	public static RecipeSimpleMachine millstone;

	public static void initRecipeLists(){

		millstone = new RecipeSimpleMachine();

	}

	public static void initRecipes(){

		CraftingManager m = CraftingManager.getInstance();


		RecipesNormalBlock.addRecipes(m);
		RecipesMachine.addRecipes(m);


		//Millstone
		RecipesMillstone.addRecipes(millstone);

	}

}