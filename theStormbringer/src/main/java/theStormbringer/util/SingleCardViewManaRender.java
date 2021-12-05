package theStormbringer.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import theStormbringer.cards.AbstractStormbringerCard;

import java.util.EnumMap;
import java.util.Map;

public class SingleCardViewManaRender {
    private static float yOffset = 120.0F * Settings.scale;
    private static float centerX = (float)Settings.WIDTH / 2.0F;
    private static float centerY = (float)Settings.HEIGHT / 2.0F;
    private static Texture Mana_Orb_Dark, Mana_Orb_Psychic , Mana_Orb_Water, Mana_Orb_Fire, Mana_Orb_Ice, Mana_Orb_Fairy, Mana_Orb_Normal, Mana_Orb_Electric;
    private static void renderElementHelper(SpriteBatch sb, Texture img, float drawX, float drawY) {
        sb.draw(img, drawX, drawY,
                0, 0, 164.0F, 164.0F,
                Settings.scale,  Settings.scale,
                0, 0, 0, 164, 164, false, false);

    }

    public static void renderElementCost(AbstractStormbringerCard card, SpriteBatch sb){
        int[] elementCost;

        float fScaleX = FontHelper.SCP_cardEnergyFont.getData().scaleX;

        FontHelper.SCP_cardEnergyFont.getData().setScale(0.75F);

        if(!card.isLocked && card.isSeen) {

            elementCost = card.getManaCost();

            int counter = 0;
            if(Mana_Orb_Water == null){
                Mana_Orb_Water = ImageMaster.loadImage("theStormbringerResources/images/WaterEnergyBase.png");
                Mana_Orb_Fire = ImageMaster.loadImage("theStormbringerResources/images/FireEnergyBase.png");
                Mana_Orb_Ice = ImageMaster.loadImage("theStormbringerResources/images/IceEnergyBase.png");
                Mana_Orb_Dark = ImageMaster.loadImage("theStormbringerResources/images/WaterEnergyBase.png");
                Mana_Orb_Normal = ImageMaster.loadImage("theStormbringerResources/images/NormalEnergyBase.png");
                Mana_Orb_Psychic = ImageMaster.loadImage("theStormbringerResources/images/PsychicEnergyBase.png");
                Mana_Orb_Fairy = ImageMaster.loadImage("theStormbringerResources/images/IceEnergyBase.png");
                Mana_Orb_Electric = ImageMaster.loadImage("theStormbringerResources/images/ElectricEnergyBase.png");
            }
            //logger.info("attempting render");
            for(Map.Entry<TypeEnergyHelper.Mana,Integer> e : card.energyCosts.entrySet()){
                TypeEnergyHelper.Mana mana = e.getKey();
                Texture tex;
                switch (mana) {
                    case Water:
                        tex = Mana_Orb_Water;
                        break;
                    case Fire:
                        tex = Mana_Orb_Fire;
                        break;
                    case Ice:
                        tex = Mana_Orb_Ice;
                        break;
                    case Dark:
                        tex = Mana_Orb_Dark;
                        break;
                    case Psychic:
                        tex = Mana_Orb_Psychic;
                        break;
                    case Fairy:
                        tex = Mana_Orb_Fairy;
                        break;
                    case Electric:
                        tex = Mana_Orb_Electric;
                        break;
                    case Colorless:
                        tex = Mana_Orb_Normal;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected mana type");
                }
                if (card.energyCosts.get(mana) != 0) {

                    //card.renderElementHelper(sb, (float)Settings.WIDTH / 2.0F - 270.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F + 380.0F * Settings.scale);
                    renderElementHelper(sb, tex, centerX - 348.0F * Settings.scale,
                            centerY + 163.0F * Settings.scale - yOffset * counter);

                    Color c = Settings.CREAM_COLOR;
                    FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, card.energyCosts.get(mana)+"", 682.0F * Settings.scale,
                            (float)Settings.HEIGHT / 2.0F + 268.0F * Settings.scale - yOffset * counter, c);

                    counter++;
                }
            }
        }
        FontHelper.SCP_cardEnergyFont.getData().setScale(fScaleX);
    }
}
