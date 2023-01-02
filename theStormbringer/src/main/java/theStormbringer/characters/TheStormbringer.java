package theStormbringer.characters;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import theStormbringer.cards.*;
import theStormbringer.relics.CrackedIris;
import theStormbringer.relics.KineticGenerator;

import java.util.ArrayList;

import static theStormbringer.StormbringerMod.*;
import static theStormbringer.characters.TheStormbringer.Enums.COLOR_SILVER;

//Wiki-page https://github.com/daviscook477/BaseMod/wiki/Custom-Characters
//and https://github.com/daviscook477/BaseMod/wiki/Migrating-to-5.0
//All text (starting description and loadout, anything labeled TEXT[]) can be found in StormbringerMod-character-Strings.json in the resources

public class TheStormbringer extends CustomPlayer {
    private static final String[] orbTextures = {
            modID + "Resources/images/mainChar/defaultCharacter/orb/layer1.png",
            modID + "Resources/images/mainChar/defaultCharacter/orb/layer2.png",
            modID + "Resources/images/mainChar/defaultCharacter/orb/layer3.png",
            modID + "Resources/images/mainChar/defaultCharacter/orb/layer4.png",
            modID + "Resources/images/mainChar/defaultCharacter/orb/layer5.png",
            modID + "Resources/images/mainChar/defaultCharacter/orb/layer6.png",
            modID + "Resources/images/mainChar/defaultCharacter/orb/layer1d.png",
            modID + "Resources/images/mainChar/defaultCharacter/orb/layer2d.png",
            modID + "Resources/images/mainChar/defaultCharacter/orb/layer3d.png",
            modID + "Resources/images/mainChar/defaultCharacter/orb/layer4d.png",
            modID + "Resources/images/mainChar/defaultCharacter/orb/layer5d.png",};
    static final String ID = makeID("TheStormbringer"); //TODO: Change this
    public static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    public static final String[] NAMES = characterStrings.NAMES;
    public static final String[] TEXT = characterStrings.TEXT;
    public static float[] layerSpeeds = new float[]{-20.0F, 20.0F, -40.0F, 40.0F, 0.0F};

    public TheStormbringer(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, modID + "Resources/images/mainChar/defaultCharacter/orb/vfx.png", layerSpeeds), new SpineAnimation("theStormbringerResources/images//mainChar/Stormbringer/TheStormbringer.atlas","theStormbringerResources/images//mainChar/Stormbringer/TheStormbringer.json",1.0f));
        initializeClass(null,
                SHOULDER1,
                SHOULDER2,
                CORPSE,
                getLoadout(), 20.0F, -10.0F, 166.0F, 327.0F, new EnergyManager(3));
        loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animtion0", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                75, 75, 0, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            retVal.add(Strike.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(Defend.ID);
        }
        retVal.add(Bite.ID);
        retVal.add(Sawitcoming.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(CrackedIris.ID);
        retVal.add(KineticGenerator.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_SILVER;
    }

    @Override
    public Color getCardTrailColor() {
        return STORMBRINGER_SILVER.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return chooseEventStarterCard();
    }

    public AbstractCard chooseEventStarterCard(){
        switch (AbstractDungeon.miscRng.random(0,2)){
            case 0 :{
                System.out.println("Event Starter Card : Razor Wind");
                return new RazorWind();
            }
            case 1 : {
                System.out.println("Event Starter Card : Bite");
                return new Bite();
            }
        }
        System.out.println("Random Starting card for event assignment Failed!");
        return null;
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheStormbringer(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return STORMBRINGER_SILVER.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return STORMBRINGER_SILVER.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        //TODO: Change these.
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_STORMBRINGER;
        @SpireEnum(name = "COLOR_SILVER")
        public static AbstractCard.CardColor COLOR_SILVER;
        @SpireEnum(name = "COLOR_SILVER")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
}
