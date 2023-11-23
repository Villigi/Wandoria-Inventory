package de.villigi.wandoriainventorys.commands;

import de.villigi.wandoriainventorys.Inventorys;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EnderChestCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        player.openInventory(player.getEnderChest());
        if(args.length == 1) {
            if(player.hasPermission("commands.openenderchest")) {
                Player target = Bukkit.getPlayer(args[0]);
                if(target.isOnline()) {
                    String message = Inventorys.getInstance().getConfiguration().getString("messages.open_ec");
                    String newText = "";
                    for(String s : message.split(" ")) {
                        if(newText == ""){
                            if(s.equalsIgnoreCase("%player%")) {
                                s = target.getDisplayName();
                                newText = newText  + s;
                                System.out.println(s);
                            }else{
                                newText = newText  + s;
                            }
                        }else{
                            if(s.equalsIgnoreCase("%player%")) {
                                s = target.getDisplayName();
                                newText = newText + " " + s;
                                System.out.println(s);
                            }else{
                                newText = newText + " " + s;
                            }
                        }

                    }
                    player.openInventory(target.getEnderChest());
                    player.getOpenInventory().setTitle("Enderchest von " + target.getDisplayName());
                    player.sendMessage(Inventorys.prefix + ChatColor.translateAlternateColorCodes('&', newText));
                }else{
                    player.sendMessage(Inventorys.prefix + ChatColor.translateAlternateColorCodes('&', Inventorys.getInstance().getConfiguration().getString("messages.player_not_online")));
                }
            }else{
                player.openInventory(player.getEnderChest());
            }
        }else{
            player.openInventory(player.getEnderChest());
        }
        return false;
    }
}
