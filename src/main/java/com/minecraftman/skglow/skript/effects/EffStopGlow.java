package com.minecraftman.skglow.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.EGlow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

@Name("Stop Glow")
@Description({"Make a player stop glowing"})
@Examples({"make loop-player stop glowing", "make player stop glowing"})
@Since("1.0.0")
@RequiredPlugins("eGlow")

public class EffStopGlow extends Effect {
	
	EGlowAPI api = EGlow.getAPI();
	
	static {
		Skript.registerEffect(EffStopGlow.class, "make %player% stop glowing");
	}
	
	private Expression<Player> player;
	
	@Override
	protected void execute(Event event) {
		
		Player p = player.getSingle(event);
		if(p == null) {
			return;
		}
		
		api.disableGlow(p);
	}
	
	@Override
	public String toString(Event e, boolean debug) {
		return "make player stop glowing";
	}
	
	@Override
	public boolean init(Expression<?>[] expression, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		this.player = (Expression<Player>) expression[0];
		return true;
	}
}
