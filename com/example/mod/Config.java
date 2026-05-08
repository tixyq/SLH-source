package com.example.mod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.Minecraft;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    public static class HudElement {
        public boolean enabled = true;
        public int x, y;
        public String labelColor = "§f";
        public String valueColor = "§a";

        public HudElement(int x, int y) { 
            this.x = x; 
            this.y = y; 
        }
    }

    
public static HudElement coords = new HudElement(2, -50); 
    public static HudElement altCoords = new HudElement(2, -60);
    public static HudElement days = new HudElement(2, -70);
    public static HudElement fps = new HudElement(2, -80);
    
    public static String altNetherColor = "§6";

    private static final File configFile = new File(Minecraft.func_71410_x().field_71412_D, "config/customhud.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void save() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(configFile);
            gson.toJson(new ConfigData(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try { writer.close(); } catch (IOException e) {}
            }
        }
    }

    public static void load() {
        if (!configFile.exists()) { save(); return; }
        FileReader reader = null;
        try {
            reader = new FileReader(configFile);
            ConfigData data = gson.fromJson(reader, ConfigData.class);
            if (data != null) {
                coords = data.coords;
                altCoords = data.altCoords;
                days = data.days;
                fps = data.fps;
                altNetherColor = data.altNetherColor;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try { reader.close(); } catch (IOException e) {}
            }
        }
    }

    private static class ConfigData {
        HudElement coords = Config.coords;
        HudElement altCoords = Config.altCoords;
        HudElement days = Config.days;
        HudElement fps = Config.fps;
        String altNetherColor = Config.altNetherColor;
    }
    }
