package theStormbringer.util.WeatherEffects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theStormbringer.actions.ChangeWeatherAction;

public abstract class AbstractWeather {
    public String name;
    public String description;
    public String ID;
    public int weathertimer;
    protected Color c;
    protected static final int W = 512;
    protected Texture img;
    protected float angle;
    protected float particleTimer;
    protected float particleTimer2;

    public AbstractWeather() {
        this.c = Color.WHITE.cpy();
        this.img = null;
        this.particleTimer = 0.0F;
        this.particleTimer2 = 0.0F;
    }

    public abstract String updateDescription();

    public void atStartOfTurn() {
        weathertimer -= 1;
        if (weathertimer < 1){
            AbstractDungeon.actionManager.addToBottom(new ChangeWeatherAction(new ClearWeather()));
        }
    }

    public void onEndOfTurn() {
    }

    public void onSummonWeather() {
        weathertimer = 3;
    }

    public void onWeatherSubside() {
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return damage;
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return this.atDamageGive(damage, type);
    }

    public void update() {
        this.updateAnimation();
    }

    public void updateAnimation() {
        updateDescription();
    }

    public void render(SpriteBatch sb) {
        if (this.img != null) {
            sb.setColor(this.c);
            sb.setBlendFunction(770, 1);
            sb.draw(this.img, AbstractDungeon.player.drawX - 256.0F + AbstractDungeon.player.animX, AbstractDungeon.player.drawY - 256.0F + AbstractDungeon.player.animY + AbstractDungeon.player.hb_h / 2.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, -this.angle, 0, 0, 512, 512, false, false);
            sb.setBlendFunction(770, 771);
        }
    }

    public void stopIdleSfx() {
    }
}
