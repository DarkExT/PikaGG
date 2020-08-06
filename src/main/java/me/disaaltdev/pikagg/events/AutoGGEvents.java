/*
 *     Copyright (C) 2018 boomboompower
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.disaaltdev.pikagg.events;

import me.disaaltdev.pikagg.PikaGG;
import me.disaaltdev.pikagg.utils.ChatColor;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class AutoGGEvents {
    private final List<String> endingStrings = Arrays.asList(
            "Click here to play again",
            "won the game!",
            "Rewards:");


    private int tick = -1;

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onGameEndEvent(ClientChatReceivedEvent event) {
        if (event.isCanceled()) {
            return;
        }

        String message = ChatColor.stripColor(event.message.getUnformattedText());

        if (message.isEmpty()) {
            return;
        }

        try {
            if (!PikaGG.getInstance().isOn()) {
                return;
            }

            if (isEndOfGame(message)) {
                this.tick = PikaGG.getInstance().getTickDelay();
            }
        } catch (Exception ex) {
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onGameTick(TickEvent.ClientTickEvent event) {
        if (this.tick == 0) {
            if (PikaGG.getInstance().isOn())  {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("gg");
            }
            this.tick = -1;
        } else {
            if (this.tick > 0) {
                this.tick--;
            }
        }
    }


    private boolean isEndOfGame(String message) {
        for (String endingString : this.endingStrings) {
            if (message.contains(endingString)) {
                return true;
            }
        }
        return false;
    }
}
