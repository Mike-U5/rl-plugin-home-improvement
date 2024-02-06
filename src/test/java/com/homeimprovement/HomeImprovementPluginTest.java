package com.homeimprovement;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HomeImprovementPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HomeImprovementPlugin.class);
		RuneLite.main(args);
	}
}