package com.beastnighttv.jak.recipe;

import org.jetbrains.annotations.Nullable;
import com.beastnighttv.jak.JAKMinecraftMod;
import com.beastnighttv.jak.util.FluidJSONUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

public class GemInfusingStationRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final FluidStack fluidStack;

    public GemInfusingStationRecipe(
        ResourceLocation id,
        ItemStack output,
        NonNullList<Ingredient> recipeItems,
        FluidStack fluidStack
    ) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.fluidStack = fluidStack;
    }

    public FluidStack getFluid() {
        return fluidStack;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }

        return recipeItems.get(0).test(pContainer.getItem(1));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    
    public static class Type implements RecipeType<GemInfusingStationRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "gem_infusing";
    }

    public static class Serializer implements RecipeSerializer<GemInfusingStationRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(JAKMinecraftMod.MODID, "gem_infusing");

        @Override
        public GemInfusingStationRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
            FluidStack fluid = FluidJSONUtil.readFluid(pSerializedRecipe.get("fluid").getAsJsonObject());

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new GemInfusingStationRecipe(pRecipeId, output, inputs, fluid);
        }

        @Override
        public @Nullable GemInfusingStationRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            FluidStack fluid = buf.readFluidStack();
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new GemInfusingStationRecipe(id, output, inputs, fluid);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, GemInfusingStationRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            buf.writeFluidStack(recipe.fluidStack);

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
}
