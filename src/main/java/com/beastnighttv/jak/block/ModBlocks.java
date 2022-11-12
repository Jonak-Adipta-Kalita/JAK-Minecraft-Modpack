package com.beastnighttv.jak.block;

import java.util.function.Supplier;

import com.beastnighttv.jak.JAKMinecraftMod;
import com.beastnighttv.jak.ModCreativeModeTab;
import com.beastnighttv.jak.block.custom.BlueBerryCropBlock;
import com.beastnighttv.jak.block.custom.JumpyBlock;
import com.beastnighttv.jak.block.custom.ZirconLampBlock;
import com.beastnighttv.jak.fluid.ModFluids;
import com.beastnighttv.jak.item.ModItems;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JAKMinecraftMod.MODID);

    public static final RegistryObject<Block> ZIRCON_BLOCK = registerBlock(
        "zircon_block",
        () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()),
        ModCreativeModeTab.JAK_TAB
    );
    public static final RegistryObject<DropExperienceBlock> ZIRCON_ORE = registerBlock(
        "zircon_ore",
        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)),
        ModCreativeModeTab.JAK_TAB
    );
    public static final RegistryObject<DropExperienceBlock> DEEPSLATE_ZIRCON_ORE = registerBlock(
        "deepslate_zircon_ore",
        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)),
        ModCreativeModeTab.JAK_TAB
    );
    public static final RegistryObject<DropExperienceBlock> NETHERRACK_ZIRCON_ORE = registerBlock(
        "netherrack_zircon_ore",
        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)),
        ModCreativeModeTab.JAK_TAB
    );
    public static final RegistryObject<DropExperienceBlock> ENDSTONE_ZIRCON_ORE = registerBlock(
        "endstone_zircon_ore",
        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)),
        ModCreativeModeTab.JAK_TAB
    );
    public static final RegistryObject<ZirconLampBlock> ZIRCON_LAMP = registerBlock(
        "zircon_lamp",
        () -> new ZirconLampBlock(BlockBehaviour.Properties.of(Material.STONE).strength(2f).lightLevel(state -> state.getValue(ZirconLampBlock.LIT) ? 15 : 0)),
        ModCreativeModeTab.JAK_TAB
    );
    public static final RegistryObject<JumpyBlock> JUMPY_BLOCK = registerBlock(
        "jumpy_block",
        () -> new JumpyBlock(BlockBehaviour.Properties.of(Material.CLAY).strength(2f)),
        ModCreativeModeTab.JAK_TAB
    );
    public static final RegistryObject<BlueBerryCropBlock> BLUEBERYY_CROP = BLOCKS.register(
        "blueberry_crop",
        () -> new BlueBerryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT))
    );
    public static final RegistryObject<LiquidBlock> SOAP_WATER_BLOCK = BLOCKS.register(
        "soap_water_block",
        () -> new LiquidBlock(ModFluids.SOURCE_SOAP_WATER, BlockBehaviour.Properties.copy(Blocks.WATER))
    );

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);

        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(
        String name, RegistryObject<T> block, CreativeModeTab tab
    ) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
