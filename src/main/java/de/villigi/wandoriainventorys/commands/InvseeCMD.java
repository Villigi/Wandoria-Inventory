package de.villigi.wandoriainventorys.commands;

import de.villigi.wandoriainventorys.Inventorys;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InvseeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("commands.invsee")) {
            if(args.length == 1) {
                String arg = args[0];
                Player target = Bukkit.getPlayer(arg);
                if(target.isOnline()) {
                    p.openInventory(target.getInventory());
                    String message = Inventorys.getInstance().getConfiguration().getString("messages.opened_inventory");
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
                    p.sendMessage(Inventorys.prefix + ChatColor.translateAlternateColorCodes('&', newText));
                }else{
                    p.sendMessage(Inventorys.prefix + ChatColor.translateAlternateColorCodes('&', Inventorys.getInstance().getConfiguration().getString("messages.player_not_online")));
                }
            }else{
                p.sendMessage(Inventorys.prefix + ChatColor.translateAlternateColorCodes('&', Inventorys.getInstance().getConfiguration().getString("messages.wrong_syntax")));
            }
        }else{
            p.sendMessage(Inventorys.prefix + ChatColor.translateAlternateColorCodes('&', Inventorys.getInstance().getConfiguration().getString("messages.no_permissions")));

        }


        return false;
    }
}
