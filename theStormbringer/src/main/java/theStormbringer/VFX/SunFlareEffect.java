package theStormbringer.VFX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;

public class SunFlareEffect extends AbstractGameEffect {
    private static TextureAtlas.AtlasRegion outer;
    private static TextureAtlas.AtlasRegion inner;
    private float scaleY;
    private static final float DUR = 0.5F;
    private Color color2;

    public SunFlareEffect() {
        if (outer == null) {
            outer = ImageMaster.vfxAtlas.findRegion("combat/orbFlareOuter");
            inner = ImageMaster.vfxAtlas.findRegion("combat/orbFlareInner");
        }
        this.color = Color.CORAL.cpy();
        this.color2 = Color.CYAN.cpy();
        this.renderBehind = true;
        this.duration = 0.5F;
        this.startingDuration = 0.5F;
        this.scale = 12.0F * Settings.scale;
        this.scaleY = 8.0F * Settings.scale;
    }


    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.duration = 0.0F;
            this.isDone = true;
        }

        this.scaleY = Interpolation.elasticIn.apply(2.2F, 0.8F, this.duration * 2.0F);
        this.scale = Interpolation.elasticIn.apply(2.1F, 1.9F, this.duration * 2.0F);
        this.color.a = Interpolation.pow2Out.apply(0.0F, 0.6F, this.duration * 2.0F);
        this.color2.a = this.color.a;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color2);
        sb.draw(inner,800 - (float)inner.packedWidth / 2.0F, AbstractDungeon.floorY + 600 - (float)inner.packedHeight / 2.0F, (float)inner.packedWidth / 2.0F, (float)inner.packedHeight / 2.0F, (float)inner.packedWidth, (float)inner.packedHeight, this.scale * Settings.scale * 1.1F, this.scaleY * Settings.scale, MathUtils.random(-1.0F, 1.0F));
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(outer, 800 - (float)outer.packedWidth / 2.0F, AbstractDungeon.floorY + 600 - (float)outer.packedHeight / 2.0F, (float)outer.packedWidth / 2.0F, (float)outer.packedHeight / 2.0F, (float)outer.packedWidth, (float)outer.packedHeight, this.scale, this.scaleY * Settings.scale, MathUtils.random(-2.0F, 2.0F));
        sb.draw(outer, 800 - (float)outer.packedWidth / 2.0F, AbstractDungeon.floorY + 600 - (float)outer.packedHeight / 2.0F, (float)outer.packedWidth / 2.0F, (float)outer.packedHeight / 2.0F, (float)outer.packedWidth, (float)outer.packedHeight, this.scale, this.scaleY * Settings.scale, MathUtils.random(-2.0F, 2.0F));
        sb.setBlendFunction(770, 771);
        sb.setColor(this.color2);
        sb.draw(inner, 800 - (float)inner.packedWidth / 2.0F, AbstractDungeon.floorY + 600 - (float)inner.packedHeight / 2.0F, (float)inner.packedWidth / 2.0F, (float)inner.packedHeight / 2.0F, (float)inner.packedWidth, (float)inner.packedHeight, this.scale * Settings.scale * 1.1F, this.scaleY * Settings.scale, MathUtils.random(-1.0F, 1.0F));
    }

    public void dispose() {
    }
}
