package de.alphahelix.uhc.listeners.scenarios;

import de.alphahelix.alphalibary.listener.SimpleListener;
import de.alphahelix.alphalibary.utils.MinecraftVersion;
import de.alphahelix.alphalibary.utils.Util;
import de.alphahelix.uhc.enums.Scenarios;
import de.alphahelix.uhc.events.timers.LobbyEndEvent;
import de.alphahelix.uhc.util.PlayerUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class HealthDonorListener extends SimpleListener {

    @EventHandler
    public void onEnd(LobbyEndEvent e) {
        if (!Scenarios.isPlayedAndEnabled(Scenarios.HEALTH_DONOR))
            return;

        for (Player p : Util.makePlayerArray(PlayerUtil.getSurvivors())) {
            if (MinecraftVersion.getServer() != MinecraftVersion.EIGHT) {
                p.getInventory().addItem(new ItemStack(Material.getMaterial("END_ROD")));
            } else {
                p.getInventory().addItem(new ItemStack(Material.DEAD_BUSH));
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent e) {
        if (e.isCancelled())
            return;
        if (!Scenarios.isPlayedAndEnabled(Scenarios.HEALTH_DONOR))
            return;
        if (!(e.getRightClicked() instanceof Player))
            return;
        if (MinecraftVersion.getServer() != MinecraftVersion.EIGHT) {
            if (!e.getPlayer().getInventory().getItemInHand().getType().equals(Material.getMaterial("END_ROD")))
                return;
        } else {
            if (!e.getPlayer().getInventory().getItemInHand().getType().equals(Material.DEAD_BUSH))
                return;
        }
        Player receiver = (Player) e.getRightClicked();

        if (receiver.getHealth() >= 19.5)
            return;
        if (e.getPlayer().getHealth() < 1.5)
            return;

        e.getPlayer().damage(2.0);
        receiver.setHealth(receiver.getHealth() + 2.0);
    }
}
