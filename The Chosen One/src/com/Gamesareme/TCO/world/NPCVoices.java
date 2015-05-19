package com.Gamesareme.TCO.world;

import java.util.ArrayList;
import java.util.HashMap;

public class NPCVoices {
	private static HashMap<String, ArrayList<String>> npcVoices = new HashMap<String, ArrayList<String>>();
	
	public NPCVoices(){
		ArrayList<String> npc1 = new ArrayList<String>();
		npc1.add("Our Hero!");
		npc1.add("Clean up the");
		npc1.add("remaning beasts");
		npcVoices.put("npc1", npc1);
		ArrayList<String> npc2 = new ArrayList<String>();
		npc2.add("Produced By");
		npc2.add("Oliver Clarke");
		npcVoices.put("npc2", npc2);

		ArrayList<String> npc3 = new ArrayList<String>();
		npc3.add("Coded By");
		npc3.add("Oliver Clarke");
		npcVoices.put("npc3", npc3);

		ArrayList<String> npc4 = new ArrayList<String>();
		npc4.add("Story By");
		npc4.add("Oliver Clarke");
		npcVoices.put("npc4", npc4);

		ArrayList<String> npc5 = new ArrayList<String>();
		npc5.add("Art By");
		npc5.add("Various sources");
		npcVoices.put("npc5", npc5);

		ArrayList<String> npc6 = new ArrayList<String>();
		npc6.add("The End");
		npcVoices.put("npc6", npc6);

	}

	/**
	 * @return the npcVoices
	 */
	public static HashMap<String, ArrayList<String>> getNpcVoices() {
		return npcVoices;
	}

}
