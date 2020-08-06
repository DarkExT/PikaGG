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
package me.disaaltdev.pikagg;


import me.disaaltdev.pikagg.commands.AutoGGCommand;
import me.disaaltdev.pikagg.config.FileUtils;
import me.disaaltdev.pikagg.events.AutoGGEvents;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PikaGG.MODID, version = PikaGG.VERSION, acceptedMinecraftVersions="[1.8.9]")
public class PikaGG {

    public static final String MODID = "PikaGG";
    public static final String VERSION = "0.23";

    @Mod.Instance
    private static PikaGG instance;

    private FileUtils fileUtils;
    private String message = "GG";
    private boolean isOn = true;
    private int tickDelay = 20;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.fileUtils = new FileUtils(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        this.fileUtils.loadConfig();

        ClientCommandHandler.instance.registerCommand(new AutoGGCommand());
        MinecraftForge.EVENT_BUS.register(new AutoGGEvents());
    }

    public FileUtils getFileUtils() {
        return this.fileUtils;
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void setOn(boolean on) {
        this.isOn = on;
    }

    public int getTickDelay() {
        return this.tickDelay;
    }

    public void setTickDelay(int delay) {
        this.tickDelay = delay;
    }

    public static PikaGG getInstance() {
        return instance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}