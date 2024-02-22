package alexander.az_climb.gui_Extra;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.DrawableHelper;

import static alexander.az_climb.AZ_Climb.MOD_ID;

public class QuitButtonWidget extends ButtonWidget {
    private static final Identifier QUIT_BUTTON = new Identifier(MOD_ID, "textures/gui/quitbutton.png");
    private static final int BORDER_COLOR = 0xFFFFFFFF;
    public QuitButtonWidget(int x, int y, int width, int height, Text message, PressAction onPress) {
        super(x, y, width, height, message, onPress);
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        minecraft.getTextureManager().bindTexture(QUIT_BUTTON);

        // The width and height for rendering the button's texture.
        int textureWidth = 50;
        int textureHeight = 20;

        // Draw the button's texture. Assuming the entire image is used for the button.
        // The texture is not switched based on state, so 'u' and 'v' are always 0.
        drawTexture(matrices, this.x, this.y, 0, 0, textureWidth, textureHeight, textureWidth, textureHeight);

        // If hovered, draw a white border around the button.
        if (isHovered()) {
            drawBorder(matrices, this.x, this.y, textureWidth, textureHeight);
        }

        // Draw button text centered. Adjust the text position as needed.
        drawCenteredText(matrices, minecraft.textRenderer, getMessage(), this.x + textureWidth / 2, this.y + (textureHeight - 8) / 2, getTextColor(isHovered()));
    }
    private void drawBorder(MatrixStack matrices, int x, int y, int width, int height) {
        // Top border
        DrawableHelper.fill(matrices, x, y, x + width, y + 1, BORDER_COLOR);
        // Bottom border
        DrawableHelper.fill(matrices, x, y + height - 1, x + width, y + height, BORDER_COLOR);
        // Left border
        DrawableHelper.fill(matrices, x, y, x + 1, y + height, BORDER_COLOR);
        // Right border
        DrawableHelper.fill(matrices, x + width - 1, y, x + width, y + height, BORDER_COLOR);
    }

    private int getTextColor(boolean hovered) {
        // Adjust the text color based on hover state if needed
        return hovered ? 0xFFE0E0E0 : 0xFFFFFFFF;
    }
}

