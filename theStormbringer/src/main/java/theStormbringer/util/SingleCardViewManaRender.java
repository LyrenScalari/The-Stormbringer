package theStormbringer.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import theStormbringer.cards.AbstractForetellCard;
import theStormbringer.cards.AbstractStormbringerCard;

import java.util.Map;

public class SingleCardViewManaRender {
    private static float yOffset = 100.0F * Settings.scale;
    private static float centerX = (float)Settings.WIDTH / 2.0F;
    private static float centerY = (float)Settings.HEIGHT / 2.0F;
    private static Texture Style,Foretell,Mana_Orb_Dark, Mana_Orb_Psychic , Mana_Orb_Fairy, Mana_Orb_Ghost, Mana_Orb_Normal;
    private static void renderElementHelper(SpriteBatch sb, Texture img, float drawX, float drawY) {
        sb.draw(img, drawX, drawY,
                0, 0, 125.0F, 125.0F,
                Settings.scale,  Settings.scale,
                0, 0, 0, 125, 125, false, false);

    }

    public static void renderElementCost(AbstractStormbringerCard card, SpriteBatch sb){
        int[] elementCost;

        float fScaleX = FontHelper.SCP_cardEnergyFont.getData().scaleX;

        FontHelper.SCP_cardEnergyFont.getData().setScale(0.75F);

        if(!card.isLocked && card.isSeen) {

            elementCost = card.getManaCost();
            boolean loop1 = false;
            boolean loop2 = false;
            int counter = 0;
            if(Mana_Orb_Dark == null){
                Mana_Orb_Dark = ImageMaster.loadImage("theStormbringerResources/images/DarkEnergyBase.png");
                Mana_Orb_Normal = ImageMaster.loadImage("theStormbringerResources/images/NormalEnergyBase.png");
                Mana_Orb_Psychic = ImageMaster.loadImage("theStormbringerResources/images/PsychicEnergyBase.png");
                Mana_Orb_Fairy = ImageMaster.loadImage("theStormbringerResources/images/FairyEnergyBase.png");
                Mana_Orb_Ghost = ImageMaster.loadImage("theStormbringerResources/images/GhostEnergyBase.png");
                Style = ImageMaster.loadImage("theStormbringerResources/images/Icons/Style.png");
                Foretell = ImageMaster.loadImage("theStormbringerResources/images/Icons/Foretell.png");
            }
            //logger.info("attempting render");
            for(Map.Entry<TypeEnergyHelper.Mana,Integer> e : card.energyCosts.entrySet()){
                TypeEnergyHelper.Mana mana = e.getKey();
                Texture tex;
                if (!loop1){
                    tex = Style;
                    renderElementHelper(sb, tex, centerX - 348.0F * Settings.scale,
                            centerY + 175.0F * Settings.scale - yOffset * counter);
                    loop1 = true;
                    counter++;
                }
                switch (mana) {
                    case Dark:
                        tex = Mana_Orb_Dark;
                        break;
                    case Psychic:
                        tex = Mana_Orb_Psychic;
                        break;
                    case Fairy:
                        tex = Mana_Orb_Fairy;
                        break;
                    case Ghost:
                        tex = Mana_Orb_Ghost;
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
                            centerY + 175.0F * Settings.scale - yOffset * counter);

                    Color c = Settings.CREAM_COLOR;
                    if (card.energyCosts.get(mana) == -1){
                        FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, "X", 682.0F * Settings.scale,
                                (float)Settings.HEIGHT / 2.0F + 280.0F * Settings.scale - yOffset * counter, c);
                    } else  {
                        FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, card.energyCosts.get(mana)+"", 682.0F * Settings.scale,
                                (float)Settings.HEIGHT / 2.0F + 280.0F * Settings.scale - yOffset * counter, c);
                    }


                    counter++;
                }
            }
            if (card instanceof AbstractForetellCard){
                for(Map.Entry<TypeEnergyHelper.Mana,Integer> e : ((AbstractForetellCard) card).ForetellCost.entrySet()) {
                    TypeEnergyHelper.Mana mana = e.getKey();
                    Texture tex;
                    if (!loop2){
                        tex = Foretell;
                        renderElementHelper(sb, tex, centerX - 348.0F * Settings.scale,
                                centerY + 175.0F * Settings.scale - yOffset * counter);
                        loop2 = true;
                        counter++;
                    }
                    switch (mana) {
                        case Dark:
                            tex = Mana_Orb_Dark;
                            break;
                        case Psychic:
                            tex = Mana_Orb_Psychic;
                            break;
                        case Fairy:
                            tex = Mana_Orb_Fairy;
                            break;
                        case Ghost:
                            tex = Mana_Orb_Ghost;
                            break;
                        case Colorless:
                            tex = Mana_Orb_Normal;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected mana type");
                    }
                    if (((AbstractForetellCard) card).ForetellCost.get(mana) != 0) {

                        //card.renderElementHelper(sb, (float)Settings.WIDTH / 2.0F - 270.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F + 380.0F * Settings.scale);
                        renderElementHelper(sb, tex, centerX - 348.0F * Settings.scale,
                                centerY + 175.0F * Settings.scale - yOffset * counter);

                        Color c = Settings.CREAM_COLOR;
                        if (((AbstractForetellCard) card).ForetellCost.get(mana) == -1){
                            FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, "X", 682.0F * Settings.scale,
                                    (float)Settings.HEIGHT / 2.0F + 280.0F * Settings.scale - yOffset * counter, c);
                        } else  {
                            FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, ((AbstractForetellCard) card).ForetellCost.get(mana)+"", 682.0F * Settings.scale,
                                    (float)Settings.HEIGHT / 2.0F + 280.0F * Settings.scale - yOffset * counter, c);
                        }


                        counter++;
                    }
                }
            }
        }
        FontHelper.SCP_cardEnergyFont.getData().setScale(fScaleX);
    }
}
