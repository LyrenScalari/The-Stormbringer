package theStormbringer.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.vfx.BobEffect;
import theStormbringer.StormbringerMod;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.core.Settings.renderScale;

public class AbstractNotOrb {
    public String name;
    public String description;
    public String ID;
    public OrbStrings Sealstrings;
    protected ArrayList<PowerTip> tips = new ArrayList();
    public int BreakAmount = 0;
    public int PainAmount = 0;
    protected int baseBreakAmount = 0;
    protected int basePainAmount = 0;
    public float cX = 0.0F;
    public float cY = 0.0F;
    public float tX;
    public float tY;
    protected Color c;
    protected Color shineColor;
    protected static final int W = 96;
    public static  float dy2 = 200;
    public static float angleSpeed = 0.00f;
    public static float angle;
    public Hitbox hb;
    protected TextureAtlas.AtlasRegion img;
    protected BobEffect bobEffect;
    protected static final float NUM_X_OFFSET;
    protected static final float NUM_Y_OFFSET;
    protected float scale;
    protected float fontScale;
    protected boolean showEvokeValue;
    protected static final float CHANNEL_IN_TIME = 0.5F;
    protected float channelAnimTimer;
    protected Boolean AnimTimer = false;

    public void renderText(SpriteBatch sb) {
    }
    public void onStartOfTurn() {

    }
    public void render(SpriteBatch sb) {
        angle = (360f/ StormbringerMod.Mana.size()) * StormbringerMod.Mana.indexOf(this);
        cX = (AbstractDungeon.player.hb.cX-100f) + (float)(dy2*Math.cos((Math.toRadians(angle))));
        cY = (AbstractDungeon.player.hb.cY+50f) + (float)(dy2*Math.sin(Math.toRadians(angle)));
        sb.draw(img, cX - img.packedWidth / 2f, cY - img.packedHeight / 2f,
                img.packedWidth / 2, img.packedHeight / 2,
                img.packedWidth, img.packedHeight, renderScale * Settings.scale, renderScale * Settings.scale, 0);
    }
    public void onEndOfTurn() {
    }
    public void updateAnimation() {
        this.bobEffect.update();
        if (this.channelAnimTimer != 0.0F) {
            this.channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (this.channelAnimTimer < 0.0F) {
                this.channelAnimTimer = 0.0F;
            }
        }
        this.c.a = Interpolation.pow2In.apply(1.0F, 0.01F, this.channelAnimTimer / 0.5F);
        this.scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, this.channelAnimTimer / 0.5F);
    }
    public void updateDescription() {

    }
    public void update() {
        this.hb.update();
        angle = (360f/ StormbringerMod.Mana.size()) * StormbringerMod.Mana.indexOf(this);
        cX = (AbstractDungeon.player.hb.cX-50f) + (float)(dy2*Math.cos((Math.toRadians(angle))));
        cY = (AbstractDungeon.player.hb.cY+50f) + (float)(dy2*Math.sin(Math.toRadians(angle)));

        hb.move(cX, cY); //I think this is correct, but might not be. Might need some offset calculations
        if (this.hb.hovered) {
            TipHelper.renderGenericTip(this.cX + 96.0F * Settings.scale, this.cY + 64.0F * Settings.scale, this.name, this.description);
        }
        this.fontScale = MathHelper.scaleLerpSnap(this.fontScale, 0.7F);
    }
    static {
        NUM_X_OFFSET = 20.0F * Settings.scale;
        NUM_Y_OFFSET = -12.0F * Settings.scale;
    }
}
