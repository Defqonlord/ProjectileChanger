package com.example;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Projectile;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.api.events.*;

import java.awt.Color;

import net.runelite.client.config.ConfigManager;

@Slf4j
@PluginDescriptor(
		name = "Projectile Changer",
		description = "Changes Specific Projectiles"
)
public class ProjectileChangerPlugin extends Plugin
{
	private static final Color HIGHLIGHT_COLOR = Color.YELLOW;
	int INFERNO_RANGE = 1378;
	int INFERNO_MAGE = 1380;
	int RANGE_ORB = 1607;
	int VARDORVIS_HEAD_RANGE = 2521;
	int VARDORVIS_HEAD_MAGE = 2520;
	int LEVI_MELEE = 2488;
	int LEVI_MAGE = 2489;
	int LEVI_RANGE = 2487;
	@Inject
	private Client client;
	@Inject
	private ProjectileChangerConfig config;

	@Override
	protected void startUp() throws Exception
	{
		// Initialization code, if needed
	}

	@Override
	protected void shutDown() throws Exception
	{
		// Clean up code, if needed
	}

	@Subscribe
	public void onProjectileMoved(ProjectileMoved projectileMoved)
	{
		int replacement = -1;
		Projectile projectile = projectileMoved.getProjectile();
		int projectileId = projectile.getId();
		//System.out.println("Projectile ID: " + projectileId);


		if(projectileId == VARDORVIS_HEAD_RANGE)
		{
			if(config.rangeEnumConfig().equals(ProjectileChangerConfig.ProjectileRangeEnum.LEVIATHAN_RANGE))
			{
				replacement = LEVI_RANGE;
			}
			else if (config.rangeEnumConfig().equals(ProjectileChangerConfig.ProjectileRangeEnum.INFERNO_RANGE))
			{
				replacement = INFERNO_RANGE;
			}

			System.out.println("Head Range Attack");
			Projectile p = client.createProjectile(replacement,
					projectile.getFloor(),
					projectile.getX1(), projectile.getY1(),
					projectile.getHeight(),
					projectile.getStartCycle(), projectile.getEndCycle(),
					projectile.getSlope(),
					projectile.getStartHeight(), projectile.getEndHeight(),
					projectile.getInteracting(),
					projectile.getTarget().getX(), projectile.getTarget().getY());

			client.getProjectiles().addLast(p);
			projectile.setEndCycle(0);
		}
		else if(projectileId == VARDORVIS_HEAD_MAGE)
		{
			if (config.mageEnumConfig().equals(ProjectileChangerConfig.ProjectileMageEnum.INFERNO_MAGE))
			{
				replacement = INFERNO_MAGE;
			}
			else if (config.mageEnumConfig().equals(ProjectileChangerConfig.ProjectileMageEnum.LEVIATHAN_MAGE))
			{
				replacement = LEVI_MAGE;
			}
			System.out.println("Head Mage Attack");
			Projectile p = client.createProjectile(replacement,
					projectile.getFloor(),
					projectile.getX1(), projectile.getY1(),
					projectile.getHeight(),
					projectile.getStartCycle(), projectile.getEndCycle(),
					projectile.getSlope(),
					projectile.getStartHeight(), projectile.getEndHeight(),
					projectile.getInteracting(),
					projectile.getTarget().getX(), projectile.getTarget().getY());

			client.getProjectiles().addLast(p);
			projectile.setEndCycle(0);
		}
	}
	@Provides
	ProjectileChangerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ProjectileChangerConfig.class);
	}
}
