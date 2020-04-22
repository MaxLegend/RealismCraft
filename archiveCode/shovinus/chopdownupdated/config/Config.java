package com.shovinus.chopdownupdated.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;

import com.google.gson.Gson;
import com.shovinus.chopdownupdated.ChopDown;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class Config {

	public static String CATEGORY = "General";

	public static boolean lowerLogs;
	public static boolean breakLeaves;
	public static int maxDropsPerTickPerTree;
	public static int maxFallingBlockBeforeManualMove;
	public static boolean useFallingEntities;
	public static String[] allowedPlayers;
	public static String[] ignoreTools;

	public static HashMap<UUID, PersonalConfig> playerConfigs = new HashMap<UUID, PersonalConfig>();
	public static TreeConfiguration[] treeConfigurations;

	public static String[] leaves;
	public static String[] logs;
	public static String[] sharedLeaves;

	public static PersonalConfig getPlayerConfig(UUID player) {
		PersonalConfig playerConfig;
		if (playerConfigs.containsKey(player)) {
			playerConfig = Config.playerConfigs.get(player);
		} else {
			playerConfig = new PersonalConfig();
			playerConfigs.put(player, playerConfig);
		}
		return playerConfig;

	}

	public static Configuration config;

	public static void load(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile(),ChopDown.VERSION);
		if(!config.getDefinedConfigVersion().equals(config.getLoadedConfigVersion())) {
			event.getSuggestedConfigurationFile().delete();
			config = new Configuration(event.getSuggestedConfigurationFile(),ChopDown.VERSION);
		}
		reloadConfig();
	}
	public static String[] DefaultTreeConfigs () {
		return new String[] {
				"{\"radius\":16,\"trunk_radius\":3,\"logs\":[\"biomesoplenty:log_0:4\"],\"leaves\":[\"biomesoplenty:leaves_3:7\"]}",
				"{\"logs\":[\"biomesoplenty:log_0:5\"],\"leaves\":[\"biomesoplenty:leaves_2:2\",\"biomesoplenty:leaves_2:1\"]}",
				"{\"logs\":[\"biomesoplenty:log_0:7\"],\"leaves\":[\"biomesoplenty:leaves_1:6\"]}",
				"{\"logs\":[\"biomesoplenty:log_1:4\"],\"leaves\":[\"biomesoplenty:leaves_0:6\"]}",
				"{\"logs\":[\"biomesoplenty:log_1:5\"],\"leaves\":[\"biomesoplenty:leaves_0:3\"]}",
				"{\"logs\":[\"biomesoplenty:log_1:6\"],\"leaves\":[\"biomesoplenty:leaves_4:0\"]}",
				"{\"logs\":[\"biomesoplenty:log_1:7\"],\"leaves\":[\"biomesoplenty:leaves_4:9\"]}",
				"{\"trunk_radius\":5,\"logs\":[\"biomesoplenty:log_2:4\"],\"leaves\":[\"biomesoplenty:leaves_4:2\"]}",
				"{\"min_vertical_logs\":4,\"logs\":[\"biomesoplenty:log_2:5\"],\"leaves\":[\"biomesoplenty:leaves_4:3\"]}",
				"{\"logs\":[\"biomesoplenty:log_2:6\"],\"leaves\":[\"biomesoplenty:leaves_5:4\"]}",
				"{\"logs\":[\"biomesoplenty:log_3:4\"],\"leaves\":[\"biomesoplenty:leaves_3:6\"]}",
				"{\"logs\":[\"biomesoplenty:log_3:5\"],\"leaves\":[\"biomesoplenty:leaves_5:5\"]}",
				"{\"logs\":[\"biomesoplenty:log_3:6\"],\"leaves\":[\"biomesoplenty:leaves_5:6\"]}",
				"{\"logs\":[\"biomesoplenty:log_3:7\"],\"leaves\":[\"biomesoplenty:leaves_5:7\"]}",
				"{\"logs\":[\"biomesoplenty:log_4:5\"],\"leaves\":[\"forestry:leaves:0\"]}",
				"{\"logs\":[\"extratrees:logs:0\"],\"leaves\":[]}",
				"{\"logs\":[\"forestry:logs.0:0\"],\"leaves\":[\"forestry:leaves.default.2:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.0:1\"],\"leaves\":[\"forestry:leaves.default.4:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.0:2\"],\"leaves\":[\"forestry:leaves.default.5:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.0:3\"],\"leaves\":[\"forestry:leaves.default.0:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.1:0\"],\"leaves\":[\"forestry:leaves.default.1:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.1:1\"],\"leaves\":[\"forestry:leaves.default.6:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.1:2\"],\"leaves\":[\"forestry:leaves.default.7:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.1:3\"],\"leaves\":[\"forestry:leaves.default.3:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.2:0\"],\"leaves\":[\"forestry:leaves.default.4:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.2:1\"],\"leaves\":[\"forestry:leaves.default.4:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.2:2\"],\"leaves\":[\"forestry:leaves.default.5:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.2:3\"],\"leaves\":[\"forestry:leaves.default.6:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.3:0\"],\"leaves\":[\"forestry:leaves.default.7:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.3:1\"],\"leaves\":[\"forestry:leaves.default.1:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.3:2\"],\"leaves\":[\"forestry:leaves.default.7:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.3:3\"],\"leaves\":[\"forestry:leaves.default.1:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.4:0\"],\"leaves\":[\"forestry:leaves.default.7:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.4:1\"],\"leaves\":[\"forestry:leaves.default.8:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.4:2\"],\"leaves\":[\"forestry:leaves.default.8:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.4:3\"],\"leaves\":[\"forestry:leaves.default.8:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.5:0\"],\"leaves\":[\"forestry:leaves.default.3:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.5:1\"],\"leaves\":[\"forestry:leaves.default.2:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.5:2\"],\"leaves\":[\"forestry:leaves.default.2:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.5:3\"],\"leaves\":[\"forestry:leaves.default.1:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.6:0\"],\"leaves\":[\"forestry:leaves.default.3:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.6:1\"],\"leaves\":[\"forestry:leaves.default.4:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.6:2\"],\"leaves\":[\"forestry:leaves.default.6:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.6:3\"],\"leaves\":[\"forestry:leaves.default.6:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.7:0\"],\"leaves\":[\"forestry:leaves.default.5:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.0:0\"],\"leaves\":[\"forestry:leaves.default.2:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.0:1\"],\"leaves\":[\"forestry:leaves.default.4:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.0:2\"],\"leaves\":[\"forestry:leaves.default.5:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.0:3\"],\"leaves\":[\"forestry:leaves.default.0:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.1:0\"],\"leaves\":[\"forestry:leaves.default.1:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.1:1\"],\"leaves\":[\"forestry:leaves.default.6:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.1:2\"],\"leaves\":[\"forestry:leaves.default.7:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.1:3\"],\"leaves\":[\"forestry:leaves.default.3:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.2:0\"],\"leaves\":[\"forestry:leaves.default.4:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.2:1\"],\"leaves\":[\"forestry:leaves.default.4:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.2:2\"],\"leaves\":[\"forestry:leaves.default.5:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.2:3\"],\"leaves\":[\"forestry:leaves.default.6:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.3:0\"],\"leaves\":[\"forestry:leaves.default.7:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.3:1\"],\"leaves\":[\"forestry:leaves.default.1:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.3:2\"],\"leaves\":[\"forestry:leaves.default.7:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.3:3\"],\"leaves\":[\"forestry:leaves.default.1:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.4:0\"],\"leaves\":[\"forestry:leaves.default.7:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.4:1\"],\"leaves\":[\"forestry:leaves.default.8:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.4:2\"],\"leaves\":[\"forestry:leaves.default.8:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.4:3\"],\"leaves\":[\"forestry:leaves.default.8:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.5:0\"],\"leaves\":[\"forestry:leaves.default.3:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.5:1\"],\"leaves\":[\"forestry:leaves.default.2:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.5:2\"],\"leaves\":[\"forestry:leaves.default.2:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.5:3\"],\"leaves\":[\"forestry:leaves.default.1:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.6:0\"],\"leaves\":[\"forestry:leaves.default.3:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.6:1\"],\"leaves\":[\"forestry:leaves.default.4:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.6:2\"],\"leaves\":[\"forestry:leaves.default.6:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.6:3\"],\"leaves\":[\"forestry:leaves.default.6:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.fireproof.7:0\"],\"leaves\":[\"forestry:leaves.default.5:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.vanilla.fireproof.0:0\"],\"leaves\":[\"forestry:leaves.default.0:0\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.vanilla.fireproof.0:1\"],\"leaves\":[\"forestry:leaves.default.2:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.vanilla.fireproof.0:2\"],\"leaves\":[\"forestry:leaves.default.0:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.vanilla.fireproof.0:3\"],\"leaves\":[\"forestry:leaves.default.3:3\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.vanilla.fireproof.1:0\"],\"leaves\":[\"forestry:leaves.default.5:2\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"forestry:logs.vanilla.fireproof.1:1\"],\"leaves\":[\"forestry:leaves.default.0:1\",\"forestry:leaves:0\"]}",
				"{\"logs\":[\"harvestcraft:pamcinnamon:0\"],\"leaves\":[\"minecraft:leaves:3\"]}",
				"{\"logs\":[\"harvestcraft:pammaple:0\"],\"leaves\":[\"minecraft:leaves:1\"]}",
				"{\"logs\":[\"ic2:rubber_wood:0\"],\"leaves\":[\"ic2:leaves:0\"]}",
				"{\"logs\":[\"integrateddynamics:menrilLog:0\",\"integrateddynamics:menrilFilledLog:0\"],\"leaves\":[\"integrateddynamics:menrilLeaves:0\"]}",
				"{\"logs\":[\"minecraft:log:0\",\"leatherworks:debarked_log_oak\"],\"leaves\":[\"minecraft:leaves:0\",\"biomesoplenty:leaves_0:1\",\"biomesoplenty:leaves_3:9\",\"biomesoplenty:leaves_3:5\",\"biomesoplenty:leaves_2:3\",\"biomesoplenty:leaves_1:5\",\"forestry:leaves:0\",\"traverse:red_autumnal_leaves:0\",\"traverse:brown_autumnal_leaves:0\",\"traverse:yellow_autumnal_leaves:0\",\"tropicraft:leaves:3\",\"tropicraft:leaves_fruit:0\",\"tropicraft:leaves_fruit:1\",\"tropicraft:leaves_fruit:2\",\"tropicraft:leaves_fruit:3\",\"forestry:leaves.default.0:0\"]}",
				"{\"logs\":[\"minecraft:log:1\",\"leatherworks:debarked_log_spruce\"],\"leaves\":[\"minecraft:leaves:1\",\"forestry:leaves:0\",\"forestry:leaves.default.2:2\"]}",
				"{\"logs\":[\"minecraft:log:2\",\"leatherworks:debarked_log_birch\"],\"leaves\":[\"minecraft:leaves:2\",\"biomesoplenty:leaves_0:0\",\"forestry:leaves:0\",\"forestry:leaves.default.0:2\"]}",
				"{\"logs\":[\"minecraft:log:3\",\"leatherworks:debarked_log_jungle\"],\"leaves\":[\"minecraft:leaves:3\",\"harvestcraft:pampaperbark:0\",\"forestry:leaves:0\",\"forestry:leaves.default.3:3\"]}",
				"{\"logs\":[\"minecraft:log2:0\",\"leatherworks:debarked_log_acacia\"],\"leaves\":[\"minecraft:leaves2:0\",\"biomesoplenty:leaves_0:1\",\"forestry:leaves:0\",\"forestry:leaves.default.5:2\"]}",
				"{\"logs\":[\"minecraft:log2:1\",\"leatherworks:debarked_log_darkoak\"],\"leaves\":[\"minecraft:leaves2:1\",\"biomesoplenty:leaves_0:1\",\"biomesoplenty:leaves_1:5\",\"forestry:leaves:0\",\"forestry:leaves.default.0:1\"]}",
				"{\"logs\":[\"natura:nether_logs:0\"],\"leaves\":[\"natura:nether_leaves:0\"]}",
				"{\"logs\":[\"natura:nether_logs:1\"],\"leaves\":[\"natura:nether_leaves2:0\",\"natura:nether_leaves2:1\",\"natura:nether_leaves2:2\",\"natura:nether_leaves2:10\"]}",
				"{\"logs\":[\"natura:nether_logs:2\"],\"leaves\":[\"natura:nether_leaves:1\"]}",
				"{\"logs\":[\"natura:nether_logs2:0\",\"natura:nether_logs2:1\"],\"leaves\":[\"natura:nether_leaves:0\"]}",
				"{\"logs\":[\"natura:overworld_logs:0\"],\"leaves\":[\"natura:overworld_leaves:0\"]}",
				"{\"logs\":[\"natura:overworld_logs:1\"],\"leaves\":[\"natura:overworld_leaves:1\"]}",
				"{\"logs\":[\"natura:overworld_logs:2\"],\"leaves\":[\"natura:overworld_leaves:2\"]}",
				"{\"logs\":[\"natura:overworld_logs:3\"],\"leaves\":[\"natura:overworld_leaves:3\"]}",
				"{\"logs\":[\"natura:overworld_logs2:0\"],\"leaves\":[\"natura:overworld_leaves2:0\"]}",
				"{\"logs\":[\"natura:overworld_logs2:1\"],\"leaves\":[\"natura:overworld_leaves2:1\"]}",
				"{\"logs\":[\"natura:overworld_logs2:2\"],\"leaves\":[\"natura:overworld_leaves2:2\"]}",
				"{\"logs\":[\"natura:overworld_logs2:3\"],\"leaves\":[\"natura:overworld_leaves2:3\"]}",
				"{\"radius\":32,\"leaf_limit\":32,\"trunk_radius\":8,\"logs\":[\"natura:redwood_logs:0\",\"natura:redwood_logs:1\",\"natura:redwood_logs:2\"],\"leaves\":[\"natura:redwood_leaves:0\"]}",
				"{\"logs\":[\"rustic:log:0\"],\"leaves\":[\"rustic:leaves:0\"]}",
				"{\"logs\":[\"rustic:log:1\"],\"leaves\":[\"rustic:leaves:1\"]}",
				"{\"logs\":[\"terra:blackspruce_log:0\"],\"leaves\":[\"terra:blackspruce_leaves:0\"]}",
				"{\"logs\":[\"terra:bluespruce_log:0\"],\"leaves\":[\"terra:bluespruce_leaves:0\"]}",
				"{\"logs\":[\"terra:cherry_log:0\"],\"leaves\":[\"terra:cherry_leaves_white:0\",\"terra:cherry_leaves_purple:0\"]}",
				"{\"logs\":[\"terra:ebony_log:0\"],\"leaves\":[\"terra:palm_leaves:0\"]}",
				"{\"logs\":[\"terra:elm_log:0\"],\"leaves\":[\"terra:elm_leaves:0\"]}",
				"{\"logs\":[\"terra:jacaranda_log:0\"],\"leaves\":[\"terra:jacaranda_leaves_magenta:0\",\"terra:jacaranda_leaves_green:0\"]}",
				"{\"logs\":[\"terra:mahogany_log:0\"],\"leaves\":[\"terra:mahogany_leaves:0\"]}",
				"{\"logs\":[\"terra:palm_log:0\"],\"leaves\":[\"terra:palm_leaves:0\"]}",
				"{\"logs\":[\"terra:paulownia_log:0\"],\"leaves\":[\"terra:paulownia_leaves_green:0\",\"terra:paulownia_leaves_white:0\",\"terra:paulownia_leaves_blue:0\"]}",
				"{\"logs\":[\"terra:redspruce_log:0\"],\"leaves\":[\"terra:redspruce_leaves:0\"]}",
				"{\"logs\":[\"terra:whitespruce_log:0\"],\"leaves\":[\"terra:whitespruce_leaves:0\"]}",
				"{\"logs\":[\"thaumcraft:log_greatwood:0\"],\"leaves\":[\"thaumcraft:leaves_greatwood:0\"]}",
				"{\"logs\":[\"thaumcraft:log_silverwood:0\"],\"leaves\":[\"thaumcraft:leaves_silverwood:0\"]}",
				"{\"logs\":[\"traverse:fir_log:0\"],\"leaves\":[\"traverse:fir_leaves:0\"]}",
				"{\"radius\":15,\"leaf_limit\":10,\"logs\":[\"tropicraft:log:0\"],\"leaves\":[\"tropicraft:leaves:0\"]}",
				"{\"logs\":[\"tropicraft:log:1\"],\"leaves\":[\"tropicraft:leaves:1\"]}",
				"{\"logs\":[\"plants2:log_0:0\"],\"leaves\":[\"plants2:leaves_0:0\"]}",
				"{\"logs\":[\"plants2:log_0:1\"],\"leaves\":[\"plants2:leaves_0:1\"]}",
				"{\"logs\":[\"plants2:log_0:2\"],\"leaves\":[\"plants2:leaves_0:2\"]}",
				"{\"logs\":[\"plants2:log_0:3\"],\"leaves\":[\"plants2:leaves_0:3\"]}",
				"{\"logs\":[\"plants2:crystal_log:0\"],\"leaves\":[\"plants2:crystal_leaves:0\"]}",
				"{\"logs\":[\"plants2:crystal_log:1\"],\"leaves\":[\"plants2:crystal_leaves:1\"]}",
				"{\"logs\":[\"defiledlands:tenebra_log:0\"],\"leaves\":[\"defiledlands:tenebra_leaves:0\"]}",
				"{\"logs\":[\"integrateddynamics:menril_log:0\",\"integrateddynamics:menril_log_filled:0\"],\"leaves\":[\"integrateddynamics:menril_leaves:0\"]}",
				};
	}
	public static void reloadConfig() {

		lowerLogs = config.getBoolean("lowerLogs", CATEGORY, true,
				"Whether to move logs down through leaves prior to dropping, makes for a better looking fallen tree but adds a few extra iterations, try setting to false if server not handling well.");
		maxDropsPerTickPerTree = config.getInt("maxDropsPerTickPerTree", CATEGORY, 150, 1, 1000000,
				"Maximum number of blocks to drop per tick for each tree thats falling");
		maxFallingBlockBeforeManualMove = config.getInt("maxFallingBlockBeforeManualMove", CATEGORY, 1500, 1, 1000000,
				"If the total blocks in the tree is above this amount instead of creating entities then it will place the blocks directly on the floor, this is for really large trees like the natura Redwood");
		String[] tempTreeConfig = config.getStringList("treeConfigurations", CATEGORY,
				DefaultTreeConfigs(),
				"List of possible trees, i.e. spruce log and spruce leaves, this makes felling trees more acurate for mixed trees, it also allows large trees like natura redwoods to be chopped more acurately, normally this tree would get ignored because its leaves reach further than a normal tree and its radius is much wider");
		breakLeaves = config.getBoolean("breakLeaves", CATEGORY, false,
				"When you chop a tree down the leaves all fall off and do their drops instead of falling with the tree, this can be better as a) less load and b)The falling of trees gets less messy, you still need to chop the logs but the leaves don't get in the way");
		useFallingEntities = config.getBoolean("useFallingEntities", CATEGORY, true,
				"Whether to use falling entities for the block fall, looks slightly smoother but can be abused to pop off logs instead of having to chop them");
		sharedLeaves = config.getStringList("sharedLeaves", CATEGORY, new String[] {
			"harvestcraft:beehive:0"
		}, "Not necessarily leaves, objects that if seemingly attached to the tree should fall down with it, such as beehives");
		allowedPlayers = config.getStringList("allowedPlayers", CATEGORY, new String[] {
				EntityPlayerMP.class.getName(),
				"micdoodle8.mods.galacticraft.core.entities.player.GCEntityPlayerMP",
				"clayborn.universalremote.hooks.entity.HookedEntityPlayerMP"
		}, "List of all the player classes allowed to chop down trees, used to distinguish fake and real players");
		ignoreTools = config.getStringList("ignoreTools", CATEGORY, new String[] {
				"tconstruct:lumberaxe:.*"
		}, "List of tools to ignore chop down on, such as tinkers lumberaxe, any tool that veinmines or similar should be ignored for chopdown");
		
		List<TreeConfiguration> tempTreeConfigurations = new ArrayList<TreeConfiguration>();
		for (String treeConfig : tempTreeConfig) {
			tempTreeConfigurations.add(new Gson().fromJson(treeConfig, TreeConfiguration.class));
		}
		treeConfigurations = tempTreeConfigurations.toArray(new TreeConfiguration[tempTreeConfigurations.size()]);
		GenerateLeavesAndLogs();
		config.save();

	}
	public static boolean MatchesTool(String name) {
		for(String tool : Config.ignoreTools) {
			if(tool.equals(name) || name.matches(tool)) {
				return true;
			}
		}
		return false;
	}
	static String[] MergeArray(String[] a, String[] b) {
		String[] d = a;
		for(String c:b) {
			if(!ArrayUtils.contains(d, c)) {
				d = ArrayUtils.add(d, c);
			}
		}
		return d;
	}
	private static void GenerateLeavesAndLogs() {
		leaves = new String[] {};
		logs = new String[] {};
		for (TreeConfiguration treeConfig : treeConfigurations) {
			leaves = MergeArray(leaves,treeConfig.Leaves());
			logs = MergeArray(logs,treeConfig.Logs());
		}
	}
	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(ChopDown.MODID)) {
			reloadConfig();
		}
	}

}
