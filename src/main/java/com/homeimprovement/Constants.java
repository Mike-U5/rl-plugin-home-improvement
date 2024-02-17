package com.homeimprovement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	public static final int BUILD_MODE_VARBIT = 2176;
	public static final List<String> POH_OPTIONS = List.of("Remove" , "Upgrade" , "Revert" , "Remove-room" , "Remove-decorations" , "Build-in", "Build");
	public static final List<Integer> POH_DUNGEON_DOOR_HOTSPOT = List.of(15317, 37475);
	public static final int POH_PORTAL_ID = 4525;
	public static final int WINTUMBER_TREE_ID = 19038;
	// This array has all Mahogany Homes objects and the morph ID they have when they are in need of replacement
	public static final Map<Integer, Integer> MH_OBJECTS = new HashMap<>() {{
		// West of Hosidius Estate Agent
		put(40002, 40017);
		put(40003, 40025);
		put(40004, 40043);
		put(40005, 40031);
		put(40006, 40031);
		put(40288, 40037);
		// East of Hosidius Market
		put(40007, 40031);
		put(40008, 40031);
		put(40009, 40017);
		put(40291, 40050);
		// South of Hosidius
		put(40012, 40017);
		put(40013, 40043);
		put(40014, 40062);
		put(40015, 40062);
		put(40294, 40056);
		// North of Falador fountain
		put(40095, 40151);
		put(40096, 40151);
		put(40097, 40102);
		put(40098, 40102);
		// South-east of Falador fountain
		put(40084, 40102);
		put(40085, 40108);
		put(40086, 40115);
		put(40087, 40121);
		put(40088, 40121);
		// South of Falador fountain
		put(40090, 40102);
		put(40091, 40128);
		put(40092, 40134);
		put(40093, 40143);
		put(40094, 39967);
		// North of Ardougne Church
		put(40165, 40187);
		put(40166, 40187);
		put(40167, 40200);
		put(40169, 40207);
		// North of Ardougne Market
		put(40156, 39948);
		put(40157, 40115);
		put(40160, 40181);
		put(40161, 40108);
		put(40162, 40108);
		// East Ardougne
		put(40171, 40187);
		put(40172, 40187);
		put(40173, 39899);
		put(40174, 39905);
		put(40175, 40193);
		put(40176, 40108);
		// North-east Varrock
		put(39981, 39892);
		put(39983, 39899);
		put(39984, 39905);
		put(39985, 39911);
		put(39986, 39911);
		put(39987, 39917);
		put(39988, 39923);
		// Middle Varrock
		put(39989, 39929);
		put(39990, 39911);
		put(39991, 39935);
		put(39992, 39942);
		put(39993, 39923);
		put(39994, 39948);
		put(39996, 39955);
		// South Varrock
		put(39997, 39961);
		put(39998, 39974);
		put(39999, 39948);
		put(40000, 39967);
		put(40001, 39935);
	}};
}
