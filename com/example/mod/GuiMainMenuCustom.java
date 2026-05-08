package com.example.mod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiMainMenuCustom extends GuiScreen {

    @Override
    public void func_73866_w_() {
        this.field_146292_n.clear();

        String[] names = new String[] {"Coords", "Alt Coords", "Days", "FPS"};
        Config.HudElement[] elements = new Config.HudElement[] {
                Config.coords, Config.altCoords, Config.days, Config.fps
        };

        int centerX = field_146294_l / 2;
        int startY = field_146295_m / 4 + 20;

        for (int i = 0; i < names.length; i++) {
            String state = elements[i].enabled ? "§aON" : "§cOFF";

            
            this.field_146292_n.add(new GuiButton(
                    i,
                    centerX - 100,
                    startY + i * 24,
                    150,
                    20,
                    names[i] + ": " + state
            ));

            
            this.field_146292_n.add(new GuiButton(
                    i + 10,
                    centerX + 55,
                    startY + i * 24,
                    45,
                    20,
                    "Custom"
            ));
        }

        
        this.field_146292_n.add(new GuiButton(100, centerX - 100, startY + 120, "Back"));
    }

    @Override
    protected void func_146284_a(GuiButton b) {
        Config.HudElement[] elements = new Config.HudElement[] {
                Config.coords, Config.altCoords, Config.days, Config.fps
        };

        if (b.field_146127_k == 100) {
            field_146297_k.func_147108_a(null);
        }
        else if (b.field_146127_k < 4) {
            elements[b.field_146127_k].enabled = !elements[b.field_146127_k].enabled;
            Config.save();
            func_73866_w_();
        }
        else if (b.field_146127_k >= 10 && b.field_146127_k < 14) {
            field_146297_k.func_147108_a(new GuiElementSettings(elements[b.field_146127_k - 10], (b.field_146127_k == 11)));
        }
    }

    @Override
    public void func_73863_a(int x, int y, float p) {
        func_146276_q_();
        func_73732_a(field_146289_q, "Custom HUD", field_146294_l / 2, field_146295_m / 4 - 10, 0xFFFFFF);
        super.func_73863_a(x, y, p);
    }
}
