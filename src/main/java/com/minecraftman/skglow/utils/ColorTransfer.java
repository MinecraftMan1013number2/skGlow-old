package com.minecraftman.skglow.utils;

import me.MrGraycat.eGlow.API.Enum.EGlowColor;
import ch.njol.skript.util.SkriptColor;

public class ColorTransfer {
	public static EGlowColor fromSkript(SkriptColor original) {
		return EGlowColor.valueOf(original.toString().toUpperCase());
	}
}
