package com.homeimprovement;

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Home Improvement",
	description = "Removes several irrelevant menu options from furniture while not in build-mode such a \"build\", \"remove\" and \"upgrade\".",
	tags = {"home", "improvement", "poh", "menu", "context", "cleanup"}
)
public class HomeImprovementPlugin extends Plugin {
	private static final int BUILD_MODE_VARBIT = 2176;
	private static final List<String> POH_OPTIONS = List.of("Remove" , "Upgrade" , "Revert" , "Remove-room" , "Remove-decorations" , "Build-in");
	private static final int POH_PORTAL_ID = 4525;
	private static boolean inPoh = false;
	@Inject
	private Client client;

	@Subscribe
	public void onGameObjectSpawned(final GameObjectSpawned e) {
		if (this.client.isInInstancedRegion()) {
			if (e.getGameObject().getId() == POH_PORTAL_ID) {
				HomeImprovementPlugin.inPoh = true;
			}
		} else {
			HomeImprovementPlugin.inPoh = false;
		}
	}

	@Subscribe
	public void onMenuEntryAdded(final MenuEntryAdded e) {
		// PoH
		if (client.getVarbitValue(BUILD_MODE_VARBIT) == 0 && HomeImprovementPlugin.inPoh) {
			final MenuEntry[] entries = Arrays.stream(client.getMenuEntries())
				.filter(entry -> !this.isObjEntry(entry) || !POH_OPTIONS.contains(entry.getOption()))
				.toArray(MenuEntry[]::new);

			client.setMenuEntries(entries);
		}
	}

	// Objects and worn equipment always have an ID of -1 and an X/Y value
	private boolean isObjEntry(final MenuEntry entry) {
		return entry.getItemId() == -1 && entry.getParam0() >= 0 && entry.getParam1() >= 0;
	}
}