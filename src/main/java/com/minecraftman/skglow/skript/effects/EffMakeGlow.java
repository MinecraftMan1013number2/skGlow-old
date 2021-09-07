package com.minecraftman.skglow.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import com.minecraftman.skglow.utils.ColorTransfer;
import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.API.Enum.EGlowColor;
import me.MrGraycat.eGlow.EGlow;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

@Name("Make Glow")
@Description({"Make a player glow a color"})
@Examples({"make loop-player glow pink", "make player glow red"})
@Since("1.0.0")
@RequiredPlugins("eGlow")

public class EffMakeGlow extends Effect {
	
	EGlowAPI api = EGlow.getAPI();
	
	static {
		Skript.registerEffect(EffMakeGlow.class, "make %player% glow %color%");
	}
	
	private Expression<Player> player;
	private Expression<SkriptColor> color;
	
	@Override
	protected void execute(Event event) {
		
		SkriptColor skriptColor = color.getSingle(event);
		Player p = player.getSingle(event);
		if(skriptColor == null || p == null) return;
		if(ColorTransfer.fromSkript(skriptColor) == null) {
			p.sendMessage(ChatColor.DARK_RED + "The color you are trying to use does not exist!");
			System.out.println("[skGlow] " + ChatColor.DARK_RED + "The color you are trying to use does not exist!");
			return;
		}
		
		EGlowColor color = ColorTransfer.fromSkript(skriptColor);
		api.enableGlow(p, color);
	}
	
	@Override
	public String toString(Event e, boolean debug) {
		return "make a player glow a color";
	}
	
	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		
		this.player = (Expression<Player>) expressions[0];
		this.color = (Expression<SkriptColor>) expressions[1];
		
		return true;
	}
}
