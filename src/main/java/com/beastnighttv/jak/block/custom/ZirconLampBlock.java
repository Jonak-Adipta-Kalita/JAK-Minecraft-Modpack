package com.beastnighttv.jak.block.custom;

import javax.annotation.Nullable;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;

public class ZirconLampBlock extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public ZirconLampBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(
        BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult
    ) {
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            level.setBlock(blockPos, blockState.cycle(LIT), 3);
        }

        return super.use(blockState, level, blockPos, player, hand, blockHitResult);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter blockGetter, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.literal("Right click to On or Off the Lamp!!").withStyle(ChatFormatting.AQUA));
        } else {
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.YELLOW));
        }

        super.appendHoverText(itemStack, blockGetter, components, flag);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
