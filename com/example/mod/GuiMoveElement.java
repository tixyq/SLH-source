package com.example.mod;

import net.minecraft.client.gui.GuiScreen;

public class GuiMoveElement extends GuiScreen {
    private final Config.HudElement element;

    public GuiMoveElement(Config.HudElement el) { 
        this.element = el; 
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.func_146276_q_();
        
        String preview = "§eElement";
        int textWidth = this.field_146289_q.func_78256_a(preview);
        int textHeight = this.field_146289_q.field_78288_b;

        element.x = Math.max(10, Math.min(mouseX, this.field_146294_l - textWidth - 10));
        element.y = Math.max(10, Math.min(mouseY, this.field_146295_m - textHeight - 10));
        
        this.field_146289_q.func_175063_a(preview, element.x, element.y, 0xFFFFFF);
        
        this.func_73732_a(field_146289_q, "Now you moving element. Click to confirm.", field_146294_l / 2, 10, 0xFFFF00);
    }

    @Override
    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
        Config.save(); 
        field_146297_k.func_147108_a(new GuiElementSettings(element, false));
    }
}
