package gloomyfolken.hooklib.realism;

import org.objectweb.asm.Type;

import gloomyfolken.hooklib.asm.AsmHook;

import gloomyfolken.hooklib.asm.ReturnCondition;
import gloomyfolken.hooklib.asm.TypeHelper;
import gloomyfolken.hooklib.minecraft.HookLoader;
import gloomyfolken.hooklib.minecraft.PrimaryClassTransformer;

public class RealismHookLoader extends HookLoader {

    // включает саму HookLib'у. Делать это можно только в одном из HookLoader'ов.
    // При желании, можно включить gloomyfolken.hooklib.minecraft.HookLibPlugin и не указывать здесь это вовсе.
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{PrimaryClassTransformer.class.getName()};
    }

    @Override
    public void registerHooks() {
        //регистрируем класс, где есть методы с аннотацией @Hook
        registerHookContainer("gloomyfolken.hooklib.realism.RealismHooks");
        
//        HookLoader.registerHook(AsmHook.newBuilder()
//                .setTargetClass("net.minecraftforge.registries.GameData$BlockCallbacks")
//                .setTargetMethod("onAdd")
//                .addTargetMethodParameters("net.minecraftforge.registries.IForgeRegistryInternal<V>", "net.minecraftforge.registries.RegistryManager",Type.INT_TYPE.getClassName(), "net.minecraft.block.Block", "net.minecraft.block.Block")
//                .setHookClass("gloomyfolken.hooklib.realism.HookBlocksCallback")
//                .setHookMethod("onAdd")
//                .addHookMethodParameter("net.minecraftforge.registries.IForgeRegistryInternal<V>", 1)
//                .addHookMethodParameter("net.minecraftforge.registries.RegistryManager", 1)
//                .addHookMethodParameter(Type.INT_TYPE.getClassName(), 1)
//                .addHookMethodParameter("net.minecraft.block.Block", 1)
//                .addHookMethodParameter("net.minecraft.block.Block", 1)
//                .setReturnCondition(ReturnCondition.ALWAYS)
//                .build());
    }
    
  
}
