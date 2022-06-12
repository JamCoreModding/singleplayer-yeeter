package io.github.jamalam360.singleplayer.yeeter.mixin;

import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SelectWorldScreen.class)
public abstract class SelectWorldScreenMixin extends Screen {
    public MultilineText text = MultilineText.EMPTY;

    protected SelectWorldScreenMixin(Text text) {
        super(text);
    }

    /**
     * @reason Yeet singleplayer
     * @author Jamalam
     */
    @Overwrite
    public void init() {
        this.text = MultilineText.create(this.textRenderer, new LiteralText(
                "Singleplayer worlds have been disabled in this modpack to prevent cheating via exploration of the multiplayer seed."
        ), this.width - 50);

        this.addDrawableChild(
                new ButtonWidget(this.width / 2 - 155 + 160, this.height / 6 + 96, 150, 20, new TranslatableText("gui.toTitle"), button -> this.client.setScreen(null))
        );
    }

    /**
     * @reason Yeet singleplayer
     * @author Jamalam
     */
    @Overwrite
    public void tick() {
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.text.drawCenterWithShadow(matrices, this.width / 2, 70);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
