package com.example.mod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiElementSettings extends GuiScreen {

    private final Config.HudElement element;
    private final boolean isAlt;

    private final String[] colors = new String[] {
        "§f","§e","§a","§b","§c","§d","§6","§7","§1","§2","§3","§4","§5"
    };

    public GuiElementSettings(Config.HudElement el, boolean isAlt) { 
        this.element = el; 
        this.isAlt = isAlt; 
    }

    @Override
    public void func_73866_w_() {
        this.field_146292_n.clear();

        int centerX = field_146294_l / 2;
        int startY = field_146295_m / 4 + 20;
        int step = 24;

        String valueName = isAlt ? "Overworld Color" : "Value Color";

        int i = 0;

        
        this.field_146292_n.add(new GuiButton(1, centerX - 100, startY + (i++ * step),
                "Label Color: " + element.labelColor + "Hello!"));

        
        this.field_146292_n.add(new GuiButton(4, centerX - 100, startY + (i++ * step),
                valueName + ": " + element.valueColor + "1234"));

        
        if (isAlt) {
            this.field_146292_n.add(new GuiButton(2, centerX - 100, startY + (i++ * step),
                    "Nether Color: " + Config.altNetherColor + "1488"));
        }

        
        i++;

        this.field_146292_n.add(new GuiButton(3, centerX - 100, startY + (i++ * step),
                "§eMove Element"));

        
        this.field_146292_n.add(new GuiButton(0, centerX - 100, startY + 120, "Back"));
    }

    @Override
    protected void func_146284_a(GuiButton b) {

        if (b.field_146127_k == 0) {
            field_146297_k.func_147108_a(new GuiMainMenuCustom());
        }

        if (b.field_146127_k == 1) {
            element.labelColor = cycle(element.labelColor);
            Config.save();
            func_73866_w_();
        }

        if (b.field_146127_k == 4) {
            element.valueColor = cycle(element.valueColor);
            Config.save();
            func_73866_w_();
        }

        if (b.field_146127_k == 2) {
            Config.altNetherColor = cycle(Config.altNetherColor);
            Config.save();
            func_73866_w_();
        }

        if (b.field_146127_k == 3) {
            field_146297_k.func_147108_a(new GuiMoveElement(element));
        }
    }

    private String cycle(String current) {
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals(current)) return colors[(i + 1) % colors.length];
        }
        return colors[0];
    }

    @Override
    public void func_73863_a(int x, int y, float p) {
        func_146276_q_();

        
        func_73732_a(field_146289_q,
                "Element Settings",
                field_146294_l / 2,
                field_146295_m / 4 - 10,
                0xFFFFFF
        );

        super.func_73863_a(x, y, p);
    }
              }
