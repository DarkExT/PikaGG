
package me.disaaltdev.pikagg.commands;

import me.disaaltdev.pikagg.gui.SettingsGui;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;

import java.util.Arrays;
import java.util.List;


public class AutoGGCommand implements ICommand {

    @Override
    public String getCommandName() {
        return "goodgame";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return EnumChatFormatting.RED + "Usage: /goodgame";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("gg", "autogg");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            new SettingsGui().display();
        }else {
            new SettingsGui(args[0]).display();
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
