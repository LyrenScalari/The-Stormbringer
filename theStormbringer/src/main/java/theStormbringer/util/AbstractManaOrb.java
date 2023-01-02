package theStormbringer.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.vfx.BobEffect;
import theStormbringer.StormbringerMod;

import static theStormbringer.util.TypeEnergyHelper.currentMana;

public abstract class AbstractManaOrb extends AbstractNotOrb {
    public static TypeEnergyHelper.Mana Type;
    public AbstractManaOrb (TypeEnergyHelper.Mana type) {
        this.c = Settings.CREAM_COLOR.cpy();
        this.shineColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
        this.hb = new Hitbox(96.0F * Settings.scale, 96.0F * Settings.scale);
        this.bobEffect = new BobEffect(3.0F * Settings.scale, 3.0F);
        this.fontScale = 0.7F;
        this.showEvokeValue = true;
        this.channelAnimTimer = 0.5F;
        Type = type;
        this.hb = new Hitbox(96.0F * Settings.scale, 96.0F * Settings.scale);
    }
    public void Burst(){

    }
    public void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.BreakAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
        if (currentMana.get(Type) != null){
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.PainAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale, new Color(0.5F, 0.0F, 3.0F, this.c.a), this.fontScale);
        }
    }
    public void update() {
        if (this.hb.hovered) {
            TipHelper.renderGenericTip(this.cX + 96.0F * Settings.scale, this.cY + 64.0F * Settings.scale, this.name, this.description);
        }
        if (currentMana.get(Type) <= 0){
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    StormbringerMod.Mana.remove(AbstractManaOrb.this);
                    isDone = true;
                }
            });
        }
    }
}
