package com.pugz.mortal.common.block;

import com.pugz.mortal.core.registry.BlockRegistry;
import com.pugz.mortal.core.registry.ItemRegistry;
import com.pugz.mortal.core.registry.ParticleRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

public class SoulUrnBlock extends Block {
    public static final IntegerProperty SIZE = IntegerProperty.create("size", 1, 3);
    public static final BooleanProperty LINKED = BooleanProperty.create("linked");
    private static final VoxelShape LARGE_URN = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
    private static final VoxelShape LARGE_LID_BASE = Block.makeCuboidShape(4.0D, 10.0D, 4.0D, 12.0D, 12.0D, 12.0D);
    private static final VoxelShape LARGE_LID = Block.makeCuboidShape(3.0D, 12.0D, 3.0D, 13.0D, 14.0D, 13.0D);
    private static final VoxelShape LARGE_URN_AABB = VoxelShapes.or(LARGE_URN, LARGE_LID_BASE, LARGE_LID);
    private static final VoxelShape MEDIUM_URN = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
    private static final VoxelShape MEDIUM_LID_BASE = Block.makeCuboidShape(5.0D, 9.0D, 5.0D, 11.0D, 10.0D, 12.0D);
    private static final VoxelShape MEDIUM_LID = Block.makeCuboidShape(4.0D, 10.0D, 4.0D, 12.0D, 12.0D, 12.0D);
    private static final VoxelShape MEDIUM_URN_AABB = VoxelShapes.or(MEDIUM_URN, MEDIUM_LID_BASE, MEDIUM_LID);
    private static final VoxelShape SMALL_URN = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    private static final VoxelShape SMALL_LID_BASE = Block.makeCuboidShape(6.0D, 6.0D, 6.0D, 10.0D, 7.0D, 10.0D);
    private static final VoxelShape SMALL_LID = Block.makeCuboidShape(5.0D, 7.0D, 5.0D, 11.0D, 9.0D, 11.0D);
    private static final VoxelShape SMALL_URN_AABB = VoxelShapes.or(SMALL_URN, SMALL_LID_BASE, SMALL_LID);

    public SoulUrnBlock(Block.Properties properties) {
        super(properties);
        setDefaultState(stateContainer.getBaseState().with(SIZE, 1).with(LINKED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(SIZE)) {
            case 1:
                return SMALL_URN_AABB;
            case 2:
                return MEDIUM_URN_AABB;
            case 3:
                return LARGE_URN_AABB;
        }
        return VoxelShapes.empty();
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack held = player.getHeldItem(hand);
        if (held.getItem() == BlockRegistry.SOUL_URN.asItem() && state.get(SIZE) < 3 && !state.get(LINKED)) {
            if (!player.isCreative()) held.setCount(held.getCount() - 1);
            world.setBlockState(pos, BlockRegistry.SOUL_URN.getDefaultState().with(SIZE, state.get(SIZE) + 1));
            return true;
        }
        else if (held.getItem() == ItemRegistry.WISP_CRYSTALS && !state.get(LINKED)) {
            onLinked(state, world, pos, player, hand);
            return true;
        }
        else return false;
    }

    public static void onLinked(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        ItemStack held = player.getHeldItem(hand);
        if (!player.isCreative()) held.setCount(held.getCount() - 1);
        world.setBlockState(pos, BlockRegistry.SOUL_URN.getDefaultState().with(SIZE, state.get(SIZE)).with(LINKED, true));
        Random rand = new Random();
        double xOffset = rand.nextBoolean() ? -(Math.random() * 0.6D) : (Math.random() * 0.6D);
        double yOffset = rand.nextBoolean() ? -(Math.random() * 0.6D) : (Math.random() * 0.6D);
        double zOffset = rand.nextBoolean() ? -(Math.random() * 0.6D) : (Math.random() * 0.6D);
        double d0 = (double) pos.getX() + 0.6D + xOffset;
        double d1 = (double) pos.getY() + 0.6D + yOffset;
        double d2 = (double) pos.getZ() + 0.6D + zOffset;
        for (int i = 0; i < 3; ++i) {
            world.addParticle(ParticleRegistry.SOUL_WISP, d0, d1, d2, 0.002D, 0.004D, 0.002D);
        }
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(BlockRegistry.SOUL_URN);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SIZE, LINKED);
    }
}