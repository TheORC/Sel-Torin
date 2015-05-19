package com.Gamesareme.TCO.libs;

import java.util.ArrayList;

public class BlockType {
	
	public static final int GRASS = -1;
	public static final int DIRT = -65536;
	public static final int STONE = -2359296;
	public static final int TREE_TRUNK = -16720896;
	public static final int LEAVES = -16726016;
	public static final int GRASS_STALK = -2293761;
	public static final int VOID = -65290;
	public static final int EMPTY = -16753921;
	public static final int MUSHROOM = -16711768;
	
	public static ArrayList<Integer> walkThroughAblePlayer = new ArrayList<Integer>();
	public static ArrayList<Integer> walkThroughAbleEntity = new ArrayList<Integer>();

	
	public BlockType(){
		//Player
		walkThroughAblePlayer.add(GRASS_STALK);
		walkThroughAblePlayer.add(TREE_TRUNK);
		walkThroughAblePlayer.add(LEAVES);
		walkThroughAblePlayer.add(EMPTY);
		walkThroughAblePlayer.add(MUSHROOM);
		//Entity
		walkThroughAbleEntity.add(GRASS_STALK);
		walkThroughAbleEntity.add(TREE_TRUNK);
		walkThroughAbleEntity.add(LEAVES);
		walkThroughAbleEntity.add(MUSHROOM);
	}

}
