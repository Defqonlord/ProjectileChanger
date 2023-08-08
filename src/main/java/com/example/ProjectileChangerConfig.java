package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("example")
public interface ProjectileChangerConfig extends Config
{
	@ConfigSection(
			name = "Vardorvis",
			description = "Configuration settings for Vardorvis",
			position = 1,
			closedByDefault = true
	)
	String VARDORVIS = "vardorvis";
	enum ProjectileRangeEnum
	{
		INFERNO_RANGE,
		LEVIATHAN_RANGE
	}
	@ConfigItem(
			position = 0,
			keyName = "projectileRange",
			name = "Range Projectile",
			description = "Change the projectile to one of the listed projectiles",
			section = VARDORVIS
	)
	default ProjectileRangeEnum rangeEnumConfig() { return ProjectileRangeEnum.INFERNO_RANGE; }


	enum ProjectileMageEnum
	{
		INFERNO_MAGE,
		LEVIATHAN_MAGE
	}
	@ConfigItem(
			position = 1,
			keyName = "projectileMage",
			name = "Mage Projectile",
			description = "Change the projectile to one of the listed projectiles",
			section = VARDORVIS
	)
	default ProjectileMageEnum mageEnumConfig() { return ProjectileMageEnum.INFERNO_MAGE; }
}
