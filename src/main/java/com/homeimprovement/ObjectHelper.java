package com.homeimprovement;

import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.MenuEntry;
import net.runelite.api.Point;
import net.runelite.api.Tile;

public class ObjectHelper {
	public static GameObject getHoveredObject(final Client client) {
		final MenuEntry[] menuEntries = client.getMenuEntries();
		if (menuEntries.length == 0) {
			return null;
		}

		final MenuEntry entry = client.isMenuOpen() ? getHoveredMenuEntry(client, menuEntries) : menuEntries[menuEntries.length - 1];

		switch (entry.getType()) {
			case WIDGET_TARGET_ON_GAME_OBJECT:
			case GAME_OBJECT_FIRST_OPTION:
			case GAME_OBJECT_SECOND_OPTION:
			case GAME_OBJECT_THIRD_OPTION:
			case GAME_OBJECT_FOURTH_OPTION:
			case GAME_OBJECT_FIFTH_OPTION:
			case EXAMINE_OBJECT:
			{
				return findTileGameObject(client, entry.getParam0(), entry.getParam1(), entry.getIdentifier());
			}
		}

		return null;
	}

	private static MenuEntry getHoveredMenuEntry(final Client client, final MenuEntry[] menuEntries) {
		final int menuX = client.getMenuX();
		final int menuY = client.getMenuY();
		final int menuWidth = client.getMenuWidth();
		final Point mousePosition = client.getMouseCanvasPosition();

		final int dy = mousePosition.getY() - menuY - 19;
		if (dy < 0) {
			return menuEntries[menuEntries.length - 1];
		}

		final int idx = menuEntries.length - 1 - (dy / 15);
		if (mousePosition.getX() > menuX && mousePosition.getX() < menuX + menuWidth && idx >= 0 && idx < menuEntries.length) {
			return menuEntries[idx];
		}

		return menuEntries[menuEntries.length - 1];
	}

	private static GameObject findTileGameObject(final Client client, final int x, final int y, final int id) {
		final Tile[][][] tiles = client.getScene().getTiles();
		final Tile tile = tiles[client.getPlane()][x][y];

		if (tile != null) {
			for (final GameObject gameObject : tile.getGameObjects()) {
				if (gameObject != null && gameObject.getId() == id) {
					return gameObject;
				}
			}
		}

		return null;
	}

	// Object entries always have an item ID of -1 and non-negative param values representing its x/y position
	public static boolean isObjEntry(final MenuEntry entry) {
		return entry.getItemId() == -1 && entry.getParam0() >= 0 && entry.getParam1() >= 0;
	}
}