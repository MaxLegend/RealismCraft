package com.legendgamer.realism.API.jsoncreator;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;



public class JsonCreatorMetaBlock {
//	static String dirBlockstates = "C:/Users/Max/Desktop/Modding/RealismCraft/src/main/resources/assets/realism/blockstates";
//	static String dirModelBlockTree = "C:/Users/Max/Desktop/Modding/RealismCraft/src/main/resources/assets/realism/models/block/tree";
//	static String dirModelItem = "C:/Users/Max/Desktop/Modding/RealismCraft/src/main/resources/assets/realism/models/item";
////	public static void createBlockstates() throws IOException {
////
////
////		{
////			for(BlocksTrees.EnumType trees : BlocksTrees.EnumType.values()) {
////				File file = new File(dirBlockstates, trees.getName() + ".json");
////				FileWriter jw = new FileWriter(file);
////
////				jw.write("{\r\n" + 
////						"    \"variants\": {\r\n" + 
////						"        \"axis=y,variant=" + trees.getName() + "\":  { \"model\": \"realism:"+ trees.getName() +"\" },\r\n" + 
////						"        \"axis=z,variant="+ trees.getName() +"\":   { \"model\": \"realism:"+ trees.getName() +"\", \"x\": 90 },\r\n" + 
////						"        \"axis=x,variant="+ trees.getName() +"\":   { \"model\": \"realism:"+ trees.getName() +"\", \"x\": 90, \"y\": 90 },\r\n" + 
////						"        \"axis=none,variant="+ trees.getName() +"\": { \"model\": \"realism:"+ trees.getName() +"\" }\r\n" + 
////						"    }\r\n" + 
////						"}");
////
////				jw.close();
////			}
////		}
////	}
//	public static void createTreeModelBlock() throws IOException {
//
//
//		{
//			for(BlocksTrees.EnumType trees : BlocksTrees.EnumType.values()) {
//				File file = new File(dirModelBlockTree, trees.getName() + ".json");
//				FileWriter jw = new FileWriter(file);
//
//				jw.write("{\r\n" + 
//						"	\"textures\": {\r\n" + 
//						"		\"0\": \"realism:blocks/tree/"+ trees.getName() +"_side\",\r\n" + 
//						"		\"1\": \"realism:blocks/tree/"+ trees.getName() +"_top\",\r\n" + 
//						"		\"particle\": \"realism:blocks/tree/"+ trees.getName() +"_side\"\r\n" + 
//						"	},\r\n" + 
//						"	\"elements\": [\r\n" + 
//						"		{\r\n" + 
//						"			\"from\": [0, 0, 0],\r\n" + 
//						"			\"to\": [16, 16, 16],\r\n" + 
//						"			\"faces\": {\r\n" + 
//						"				\"north\": {\"uv\": [1, 0, 15, 16], \"texture\": \"#0\"},\r\n" + 
//						"				\"east\": {\"uv\": [1, 0, 15, 16], \"texture\": \"#0\"},\r\n" + 
//						"				\"south\": {\"uv\": [1, 0, 15, 16], \"texture\": \"#0\"},\r\n" + 
//						"				\"west\": {\"uv\": [1, 0, 15, 16], \"texture\": \"#0\"},\r\n" + 
//						"				\"up\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#1\"},\r\n" + 
//						"				\"down\": {\"uv\": [0, 0, 16, 16], \"texture\": \"#1\"}\r\n" + 
//						"			}\r\n" + 
//						"		}\r\n" + 
//						"	]\r\n" + 
//						"}");
//
//				jw.close();
//			}
//		}
//	}
//		public static void createItemModel() throws IOException {
//
//
//			{
//				for(BlocksTrees.EnumType trees : BlocksTrees.EnumType.values()) {
//					File file = new File(dirModelItem, trees.getName() + ".json");
//					FileWriter jw = new FileWriter(file);
//
//					jw.write("{\r\n" + 
//							"    \"parent\": \"realism:block/tree/"+ trees.getName() +"\"\r\n" + 
//							"}");
//
//					jw.close();
//				}
//			}
//		}
//

}
