package com.example.mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "slhbytixyq", version = "2.2", clientSideOnly = true)
public class MyMod {

    public static KeyBinding openGuiKey;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Config.load();
        openGuiKey = new KeyBinding("Open HUD Menu", Keyboard.KEY_X, "Custom HUD");
        ClientRegistry.registerKeyBinding(openGuiKey);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (openGuiKey.func_151468_f()) {
            Minecraft.func_71410_x().func_147108_a(new GuiMainMenuCustom());
        }
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71439_g == null || mc.field_71462_r != null) return;

        int cx = event.resolution.func_78326_a() / 2;
        int by = event.resolution.func_78328_b();

        if (Config.coords.enabled) {
            renderLabel(Config.coords, cx, by, "XYZ: ", (int)mc.field_71439_g.field_70165_t + ", " + (int)mc.field_71439_g.field_70163_u + ", " + (int)mc.field_71439_g.field_70161_v);
        }
        
        if (Config.altCoords.enabled && mc.field_71439_g.field_71093_bK != 1) {
            String vColor = (mc.field_71439_g.field_71093_bK == 0) ? Config.altNetherColor : Config.altCoords.valueColor;
            String label = (mc.field_71439_g.field_71093_bK == 0) ? "Nether: " : "Overworld: ";
            String vals = (mc.field_71439_g.field_71093_bK == 0) ? 
                (int)mc.field_71439_g.field_70165_t/8 + ", " + (int)mc.field_71439_g.field_70163_u + ", " + (int)mc.field_71439_g.field_70161_v/8 : 
                (int)mc.field_71439_g.field_70165_t*8 + ", " + (int)mc.field_71439_g.field_70163_u + ", " + (int)mc.field_71439_g.field_70161_v*8;
            renderLabel(Config.altCoords, cx, by, label, vals, vColor);
        }
        
        if (Config.days.enabled) {
            renderLabel(Config.days, cx, by, "Days: ", String.valueOf(mc.field_71441_e.func_82737_E()/24000L));
        }

        if (Config.fps.enabled) {
            renderLabel(Config.fps, cx, by, "FPS: ", String.valueOf(Minecraft.func_175610_ah()));
        }
    }

    private void renderLabel(Config.HudElement el, int cx, int by, String label, String value) {
        renderLabel(el, cx, by, label, value, el.valueColor);
    }

    private void renderLabel(Config.HudElement el, int cx, int by, String label, String value, String vColor) {
        String fullText = el.labelColor + label + vColor + value;
        
        int fx = (el.x < 10) ? (cx + el.x) : el.x;
        int fy = (el.y < 0) ? (by + el.y) : el.y;
        Minecraft.func_71410_x().field_71466_p.func_175063_a(fullText, fx, fy, 0xFFFFFF);
    }

    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui instanceof GuiIngameMenu) {
            
            int optionsY = event.gui.field_146295_m / 4 + 80; 
            event.buttonList.add(new GuiButton(500, event.gui.field_146294_l / 2 - 124, optionsY, 20, 20, "M"));
        }
    }

    @SubscribeEvent
    public void onAction(GuiScreenEvent.ActionPerformedEvent.Post event) {
        if (event.button.field_146127_k == 500) {
            Minecraft.func_71410_x().func_147108_a(new GuiMainMenuCustom());
        }
    }
                         }
