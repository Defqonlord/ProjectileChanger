package com.example;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ProjectileChanger
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ProjectileChangerPlugin.class);
		RuneLite.main(args);
	}
}