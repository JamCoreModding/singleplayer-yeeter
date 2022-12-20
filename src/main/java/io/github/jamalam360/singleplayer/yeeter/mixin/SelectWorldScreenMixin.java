/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 Jamalam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.jamalam360.singleplayer.yeeter.mixin;

import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
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
        this.text = MultilineText.create(this.textRenderer, Text.literal(
              "Singleplayer worlds have been disabled in this modpack to prevent cheating via exploration of the multiplayer seed."
        ), this.width - 50);

        this.addDrawableChild(
              ButtonWidget.builder(Text.translatable("gui.toTitle"), (b) -> this.client.setScreen(null)).size(150, 20).position(this.width / 2 - 75, this.height / 6 + 96).build()
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
