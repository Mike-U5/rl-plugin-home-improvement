package com.homeimprovement;

import java.util.Arrays;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.MenuEntry;
import net.runelite.api.ObjectComposition;
import net.runelite.api.TileObject;
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
	private static boolean inPoh = false;
	@Inject
	private Client client;

	@Subscribe
	public void onGameObjectSpawned(final GameObjectSpawned e) {
		if (this.client.isInInstancedRegion()) {
			final GameObject obj = e.getGameObject();

			if (obj.getId() == Constants.POH_PORTAL_ID || obj.getId() == Constants.WINTUMBER_TREE_ID) {
				HomeImprovementPlugin.inPoh = true;
			}
		} else {
			HomeImprovementPlugin.inPoh = false;
		}
	}

	@Subscribe
	public void onMenuEntryAdded(final MenuEntryAdded e) {
		if (this.client.isInInstancedRegion()) {
			// PoH
			if (client.getVarbitValue(Constants.BUILD_MODE_VARBIT) == 0 && HomeImprovementPlugin.inPoh) {
				final MenuEntry[] entries = Arrays.stream(client.getMenuEntries())
					.filter(entry -> !ObjectHelper.isObjEntry(entry) || !Constants.POH_OPTIONS.contains(entry.getOption()))
					.toArray(MenuEntry[]::new);

				client.setMenuEntries(entries);
			}
		} else if (e.getOption().equals("Remove") && ObjectHelper.isObjEntry(e.getMenuEntry())) {
			// Mahogany Homes
			final TileObject obj = ObjectHelper.getHoveredObject(this.client);

			if (obj != null && Constants.MH_OBJECTS.containsKey(obj.getId())) {
				final ObjectComposition composition = this.client.getObjectDefinition(obj.getId());

				if (composition != null && composition.getImpostorIds() != null && Constants.MH_OBJECTS.get(obj.getId()) != composition.getImpostor().getId()) {
					final MenuEntry[] entries = Arrays.stream(client.getMenuEntries())
						.filter(entry -> entry.getIdentifier() != e.getIdentifier())
						.toArray(MenuEntry[]::new);

					client.setMenuEntries(entries);
				}
			}
		}
	}
}