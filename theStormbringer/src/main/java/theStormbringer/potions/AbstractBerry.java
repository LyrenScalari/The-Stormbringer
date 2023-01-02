package theStormbringer.potions;

import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import theStormbringer.util.TextureLoader;

public abstract class AbstractBerry extends CustomPotion {
    public Texture Icon;
    public AbstractBerry(String name, String id, PotionRarity rarity, PotionSize size, PotionColor color) {
        super(name, id, rarity, size, color);
    }
    public void render(SpriteBatch sb) {
        sb.draw(Icon, this.posX - 32.0F, this.posY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0f, 0, 0, 64, 64, false, false);

        if (this.hb != null) {
            this.hb.render(sb);
        }
    }
    public void renderShiny(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.1F));
        sb.draw(Icon, this.posX - 32.0F, this.posY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0f, 0, 0, 64, 64, false, false);
        sb.setBlendFunction(770, 771);

    }
    public void shopRender(SpriteBatch sb) {
        this.generateSparkles(0.0F, 0.0F, false);
        if (this.hb.hovered) {
            TipHelper.queuePowerTips((float) InputHelper.mX + 50.0F * Settings.scale, (float)InputHelper.mY + 50.0F * Settings.scale, this.tips);
            this.scale = 1.5F * Settings.scale;
        } else {
            this.scale = MathHelper.scaleLerpSnap(this.scale, 1.2F * Settings.scale);
        }

        this.renderOutline(sb);
        sb.draw(Icon, this.posX - 32.0F, this.posY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0f, 0, 0, 64, 64, false, false);

        if (this.hb != null) {
            this.hb.render(sb);
        }

    }
    public void labRender(SpriteBatch sb) {
        if (this.hb.hovered) {
            TipHelper.queuePowerTips(150.0F * Settings.scale, 800.0F * Settings.scale, this.tips);
            this.scale = 1.5F * Settings.scale;
        } else {
            this.scale = MathHelper.scaleLerpSnap(this.scale, 1.2F * Settings.scale);
        }
        this.renderOutline(sb, this.labOutlineColor);
        sb.draw(Icon, this.posX - 32.0F, this.posY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0f, 0, 0, 64, 64, false, false);

        if (this.hb != null) {
            this.hb.render(sb);
        }

    }
    public void renderLightOutline(SpriteBatch sb) {
    }
    public void renderOutline(SpriteBatch sb) {
    }
    public void renderOutline(SpriteBatch sb, Color c) {
    }
}
