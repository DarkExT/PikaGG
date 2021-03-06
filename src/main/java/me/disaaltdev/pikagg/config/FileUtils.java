
package me.disaaltdev.pikagg.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.disaaltdev.pikagg.PikaGG;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    private File configFile;

    private JsonObject config = new JsonObject();

    public FileUtils(File configFile) {
        this.configFile = configFile;
    }

    public boolean configExists() {
        return exists(this.configFile.getPath());
    }

    public void loadConfig() {
        if (configExists()) {
            try {
                FileReader reader = new FileReader(this.configFile);
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder builder = new StringBuilder();

                String currentLine;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    builder.append(currentLine);
                }
                String complete = builder.toString();

                this.config = new JsonParser().parse(complete).getAsJsonObject();
            } catch (Exception ex) {
                saveConfig();
            }
            PikaGG.getInstance().setOn(this.config.has("enabled") && this.config.get("enabled").getAsBoolean());
            PikaGG.getInstance().setTickDelay(this.config.has("delay") ? this.config.get("delay").getAsInt() : 20);
        } else {
            System.out.println("Config does not exist! Saving...");
            saveConfig();
        }
    }

    public void saveConfig() {
        config = new JsonObject();
        try {
            configFile.createNewFile();
            FileWriter writer = new FileWriter(configFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            config.addProperty("enabled", PikaGG.getInstance().isOn());
            config.addProperty("delay", PikaGG.getInstance().getTickDelay());
            config.addProperty("message", "gg");

            bufferedWriter.write(config.toString());
            bufferedWriter.close();
            writer.close();
        } catch (Exception ex) {
            System.out.println("Could not save config!");
            ex.printStackTrace();
        }
    }

    private boolean exists(String path) {
        return Files.exists(Paths.get(path));
    }
}
