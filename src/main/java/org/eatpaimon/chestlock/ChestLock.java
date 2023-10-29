package org.eatpaimon.chestlock;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class ChestLock extends JavaPlugin {

    static ChestLock main;

    @Override
    public void onEnable() {

        main = this;
        Bukkit.getPluginManager().registerEvents(new Listener(), this);
        saveDefaultConfig();

        Bukkit.getLogger().info("[箱子锁]插件已加载 作者：吃一口pai蒙");
        // Plugin startup logic


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
