package com.hatsukaze.amuletofreturning.datagen;

import com.hatsukaze.amuletofreturning.AmuletOfReturningMain;
import com.hatsukaze.amuletofreturning.register.ModAccessoriesRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

/**
 * ModRecipeProvider
 * amuletOfReturningのようにコピペ生成でレシピ生成可能
 */
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        amuletOfReturning(recipeOutput);
    }

    /**
     * Amulet of Returning（帰還のアミュレット）
     * S = Tags.Items.STRINGS（糸タグ）
     * O = Tags.Items.OBSIDIANS（黒曜石タグ）
     * A = Items.AMETHYST_SHARD（直指定）
     */
    private void amuletOfReturning(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModAccessoriesRegister.AMULET_OF_RETURN.get())
                .pattern(" S ")
                .pattern("S S")
                .pattern("AOA")
                .define('S', Tags.Items.STRINGS)
                .define('O', Tags.Items.OBSIDIANS)
                .define('A', Items.AMETHYST_SHARD)
                .unlockedBy("has_obsidian", has(Tags.Items.OBSIDIANS))
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                .save(recipeOutput);
    }
}
