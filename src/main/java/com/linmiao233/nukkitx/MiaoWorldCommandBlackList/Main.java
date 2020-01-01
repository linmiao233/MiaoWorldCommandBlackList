package com.linmiao233.nukkitx.MiaoWorldCommandBlackList;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.util.List;

/**
 * @author linmiao233
 */
public class Main extends PluginBase implements Listener {

    
    Config c;
    Player player;


    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        c = getConfig();
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        player = e.getPlayer();
        List<String> cmds = c.getStringList(player.getLevel().getName());
        if (cmds.contains(e.getMessage().toLowerCase()) && !e.getPlayer().isOp()) {
            if (!cmds.contains(player.getLevel().getName()))
            e.setCancelled(true);
            sendMessage(e.getPlayer());
        }
    }

    public void sendMessage(Player p) {
        p.sendMessage(TextFormat.colorize('&', c.getString("message")));
    }


}
