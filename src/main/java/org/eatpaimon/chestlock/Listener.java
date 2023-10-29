package org.eatpaimon.chestlock;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        Material block0 = event.getBlock().getType();
        if (block0 == Material.ACACIA_WALL_SIGN || block0 == Material.SPRUCE_WALL_SIGN ||
                block0 == Material.BAMBOO_WALL_SIGN || block0 == Material.BIRCH_WALL_SIGN ||
                block0 == Material.WARPED_WALL_SIGN || block0 == Material.CHERRY_WALL_SIGN ||
                block0 == Material.CRIMSON_WALL_SIGN || block0 == Material.OAK_WALL_SIGN ||
                block0 == Material.DARK_OAK_WALL_SIGN || block0 == Material.JUNGLE_WALL_SIGN ||
                block0 == Material.MANGROVE_WALL_SIGN ||
                block0 == Material.ACACIA_SIGN || block0 == Material.SPRUCE_SIGN ||
                block0 == Material.BAMBOO_SIGN || block0 == Material.BIRCH_SIGN ||
                block0 == Material.WARPED_SIGN || block0 == Material.CHERRY_SIGN ||
                block0 == Material.CRIMSON_SIGN || block0 == Material.OAK_SIGN ||
                block0 == Material.DARK_OAK_SIGN || block0 == Material.JUNGLE_SIGN ||
                block0 == Material.MANGROVE_SIGN) {
            Sign sign = (Sign) event.getBlock();
            String[] lines = sign.getLines();
            if (lines[0].equals("已上锁") || lines[0].equals("Locked")) {
                int X = event.getBlock().getX();
                int Y = event.getBlock().getY();
                int Z = event.getBlock().getZ();
                Location location0 = new Location(event.getBlock().getWorld(), X + 1, Y, Z);
                Location location1 = new Location(event.getBlock().getWorld(), X - 1, Y, Z);
                Location location2 = new Location(event.getBlock().getWorld(), X, Y - 1, Z);
                Location location3 = new Location(event.getBlock().getWorld(), X, Y, Z + 1);
                Location location4 = new Location(event.getBlock().getWorld(), X, Y, Z - 1);
                if (location0.getBlock().getType() == Material.CHEST ||
                        location1.getBlock().getType() == Material.CHEST ||
                        location2.getBlock().getType() == Material.CHEST ||
                        location3.getBlock().getType() == Material.CHEST ||
                        location4.getBlock().getType() == Material.CHEST) {
                    Player player = event.getPlayer();
                    lines[1] = player.getName();
                    event.setLine(1, lines[1]);
                    sign.setEditable(false);
                }
            }
        }
    }

    @EventHandler
    public void onChestOpen(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.CHEST) {
                int X = event.getClickedBlock().getX();
                int Y = event.getClickedBlock().getY();
                int Z = event.getClickedBlock().getZ();
                Location location0 = new Location(event.getClickedBlock().getWorld(), X + 1, Y, Z);
                Location location1 = new Location(event.getClickedBlock().getWorld(), X - 1, Y, Z);
                Location location2 = new Location(event.getClickedBlock().getWorld(), X, Y + 1, Z);
                Location location3 = new Location(event.getClickedBlock().getWorld(), X, Y, Z + 1);
                Location location4 = new Location(event.getClickedBlock().getWorld(), X, Y, Z - 1);

                Material block0 = location0.getBlock().getType();
                Material block1 = location1.getBlock().getType();
                Material block2 = location2.getBlock().getType();
                Material block3 = location3.getBlock().getType();
                Material block4 = location4.getBlock().getType();

                if (isSign(block0) || isSign(block1) || isSign(block2) || isSign(block3) ||
                isSign(block4)) {
                    Sign sign = (Sign) signLocation(event.getClickedBlock().getLocation()).getBlock().getState();
                    String[] lines = sign.getLines();

                    if (isLocked(location0) || isLocked(location1) || isLocked(location2) ||
                            isLocked(location3) || isLocked(location4)) {
                        String playerName = event.getPlayer().getName();
                        if (!playerName.equals(lines[1])) {
                            event.getPlayer().sendMessage("这个箱子被上锁了");
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material block0 = event.getBlock().getType();
        if (isSign(block0)) {
            Sign sign = (Sign) event.getBlock();
            String[] lines = sign.getLines();
            if (lines[0].equals("已上锁") || lines[0].equals("Locked")) {
                int X = event.getBlock().getX();
                int Y = event.getBlock().getY();
                int Z = event.getBlock().getZ();
                Location location0 = new Location(event.getBlock().getWorld(), X + 1, Y, Z);
                Location location1 = new Location(event.getBlock().getWorld(), X - 1, Y, Z);
                Location location2 = new Location(event.getBlock().getWorld(), X, Y - 1, Z);
                Location location3 = new Location(event.getBlock().getWorld(), X, Y, Z + 1);
                Location location4 = new Location(event.getBlock().getWorld(), X, Y, Z - 1);
                if (location0.getBlock().getType() == Material.CHEST ||
                        location1.getBlock().getType() == Material.CHEST ||
                        location2.getBlock().getType() == Material.CHEST ||
                        location3.getBlock().getType() == Material.CHEST ||
                        location4.getBlock().getType() == Material.CHEST) {
                    event.getPlayer().sendMessage("被上锁，您无法这样做！");
                    event.setCancelled(true);
                }
            }
        }
        if (block0 == Material.CHEST){
            int X = event.getBlock().getX();
            int Y = event.getBlock().getY();
            int Z = event.getBlock().getZ();
            Location location0 = new Location(event.getBlock().getWorld(), X + 1, Y, Z);
            Location location1 = new Location(event.getBlock().getWorld(), X - 1, Y, Z);
            Location location2 = new Location(event.getBlock().getWorld(), X, Y + 1, Z);
            Location location3 = new Location(event.getBlock().getWorld(), X, Y, Z + 1);
            Location location4 = new Location(event.getBlock().getWorld(), X, Y, Z - 1);
            if (isSign(location0.getBlock().getType()) || isSign(location1.getBlock().getType()) ||
                    isSign(location2.getBlock().getType()) ||isSign(location3.getBlock().getType()) ||
                    isSign(location4.getBlock().getType())){
                if (isLocked(location0) || isLocked(location1) || isLocked(location2) ||
                isLocked(location3) || isLocked(location4)){
                    event.getPlayer().sendMessage("被上锁，您无法这样做！");
                    event.setCancelled(true);
                }
            }
        }
    }

    boolean isSign(Material block0){
        return block0 == Material.ACACIA_WALL_SIGN || block0 == Material.SPRUCE_WALL_SIGN ||
                block0 == Material.BAMBOO_WALL_SIGN || block0 == Material.BIRCH_WALL_SIGN ||
                block0 == Material.WARPED_WALL_SIGN || block0 == Material.CHERRY_WALL_SIGN ||
                block0 == Material.CRIMSON_WALL_SIGN || block0 == Material.OAK_WALL_SIGN ||
                block0 == Material.DARK_OAK_WALL_SIGN || block0 == Material.JUNGLE_WALL_SIGN ||
                block0 == Material.MANGROVE_WALL_SIGN ||
                block0 == Material.ACACIA_SIGN || block0 == Material.SPRUCE_SIGN ||
                block0 == Material.BAMBOO_SIGN || block0 == Material.BIRCH_SIGN ||
                block0 == Material.WARPED_SIGN || block0 == Material.CHERRY_SIGN ||
                block0 == Material.CRIMSON_SIGN || block0 == Material.OAK_SIGN ||
                block0 == Material.DARK_OAK_SIGN || block0 == Material.JUNGLE_SIGN ||
                block0 == Material.MANGROVE_SIGN;
    }

    boolean isLocked(Location location) {
        if (isSign(location.getBlock().getType())) {
            Sign sign = (Sign) location.getBlock();
            String[] lines = sign.getLines();
            return lines[0].equals("已上锁") || lines[0].equals("Locked");
        }else {
            return false;
        }
    }

    Location signLocation(Location chestLocation) {
        Location location0 = new Location(chestLocation.getWorld(),
                chestLocation.getX() + 1, chestLocation.getY(), chestLocation.getZ());
        Location location1 = new Location(chestLocation.getWorld(),
                chestLocation.getX() - 1, chestLocation.getY(), chestLocation.getZ());
        Location location2 = new Location(chestLocation.getWorld(),
                chestLocation.getX(), chestLocation.getY() + 1, chestLocation.getZ());
        Location location3 = new Location(chestLocation.getWorld(),
                chestLocation.getX(), chestLocation.getY(), chestLocation.getZ() + 1);
        Location location4 = new Location(chestLocation.getWorld(),
                chestLocation.getX(), chestLocation.getY(), chestLocation.getZ() - 1);
        if (isSign(location0.getBlock().getType())) {
            return location0;
        } else if (isSign(location1.getBlock().getType())) {
            return location1;
        } else if (isSign(location2.getBlock().getType())) {
            return location2;
        } else if (isSign(location3.getBlock().getType())) {
            return location3;
        } else if (isSign(location4.getBlock().getType())) {
            return location4;
        }
        return null;
    }
}
