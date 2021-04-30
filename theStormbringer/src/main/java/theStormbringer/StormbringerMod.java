package theStormbringer;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theStormbringer.cards.AbstractDefaultCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.*;
import theStormbringer.variables.SecondDamage;
import theStormbringer.variables.SecondMagicNumber;
import theStormbringer.relics.AbstractEasyRelic;

import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class StormbringerMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        StartGameSubscriber,
        OnStartBattleSubscriber{

    public static final String modID = "Stormbringer"; //TODO: Change this.

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color characterColor = new Color(17.0f, 45.0f, 112.0f, 1); // This should be changed eventually

    public static final String SHOULDER1 = "StormbringerResources/images/char/mainChar/defaultCharacter/shoulder.png";
    public static final String SHOULDER2 = "StormbringerResources/images/char/mainChar/defaultCharacter/shoulder2.png";
    public static final String CORPSE = "StormbringerResources/images/char/mainChar/defaultCharacter/corpse.png";
    private static final String ATTACK_S_ART = "StormbringerResources/images/512/attack.png";
    private static final String SKILL_S_ART = "StormbringerResources/images/512/skill.png";
    private static final String POWER_S_ART = "StormbringerResources/images/512/power.png";
    private static final String CARD_ENERGY_S = "StormbringerResources/images/512/energy.png";
    private static final String TEXT_ENERGY = "StormbringerResources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = "StormbringerResources/images/1024/attack.png";
    private static final String SKILL_L_ART = "StormbringerResources/images/1024/skill.png";
    private static final String POWER_L_ART = "StormbringerResources/images/1024/power.png";
    private static final String CARD_ENERGY_L = "StormbringerResources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = "StormbringerResources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = "StormbringerResources/images/charSelect/charBG.png";
    
    // Typed Energy
    public static final String Dark_Energy = "StormbringerResources/images/512/Dark/Dark_512.png";
    public static final String Dark_Energy_Small =  "StormbringerResources/images/512/Dark/Dark_Small.png";
    public static final String Dark_Energy_Portrait = "StormbringerResources/images/1024/Dark/Dark_1024.png";

    public static final String Psy_Energy = "StormbringerResources/images/512/Psychic/Psychic_512.png";
    public static final String Psy_Energy_Small = "StormbringerResources/images/512/Psychic/Psychic_Small.png";
    public static final String Psy_Energy_Portrait = "StormbringerResources/images/1024/Psychic/Psychic_1024.png";

    public static final String Water_Energy = "StormbringerResources/images/512/Water/Water_512.png";
    public static final String Water_Energy_Small = "StormbringerResources/images/512/Water/Water_Small.png";
    public static final String Water_Energy_Portrait = "StormbringerResources/images/1024/Water/Water_1024.png";

    public static final String Elec_Energy = "StormbringerResources/images/512/Electric/Electric_512.png";
    public static final String Elec_Energy_Small = "StormbringerResources/images/512/Electric/Electric_Small.png";
    public static final String Elec_Energy_Portrait = "StormbringerResources/images/1024/Electric/Electric_1024.png";

    public static final String Fire_Energy = "StormbringerResources/images/512/Fire/Fire_512.png";
    public static final String Fire_Energy_Small = "StormbringerResources/images/512/Fire/Fire_Small.png";
    public static final String Fire_Energy_Portrait = "StormbringerResources/images/1024/Fire/Fire_1024.png";

    public static final String Ice_Energy = "StormbringerResources/images/512/Ice/Ice_512.png";
    public static final String Ice_Energy_Small = "StormbringerResources/images/512/Ice/Ice_Small.png";
    public static final String Ice_Energy_Portrait = "StormbringerResources/images/1024/Ice/Ice_1024.png";

    public static final String Rock_Energy = "StormbringerResources/images/512/Rock/Rock_512.png";
    public static final String Rock_Energy_Small = "StormbringerResources/images/512/Rock/Rock_Small.png";
    public  static final String Rock_Energy_Portrait = "StormbringerResources/images/1024/Rock/Rock_1024.png";

    public static TextureAtlas TypeEnergyAtlas = new TextureAtlas();
    public StormbringerMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheStormbringer.Enums.COLOR_NAVY, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        StormbringerMod thismod = new StormbringerMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheStormbringer(TheStormbringer.characterStrings.NAMES[1], TheStormbringer.Enums.THE_STORMBRINGER),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheStormbringer.Enums.THE_STORMBRINGER);
        TypeEnergyAtlas.addRegion("[FireEn]", ImageMaster.loadImage(Fire_Energy_Small),0,0,22,22);
        TypeEnergyAtlas.addRegion("[WaterEn]",ImageMaster.loadImage(Water_Energy_Small),0,0,22,22);
        TypeEnergyAtlas.addRegion("[ElectricEn]",ImageMaster.loadImage(Elec_Energy_Small),0,0,22,22);
        TypeEnergyAtlas.addRegion("[IceEn]",ImageMaster.loadImage(Ice_Energy_Small),0,0,22,22);
        TypeEnergyAtlas.addRegion("[RockEn]",ImageMaster.loadImage(Rock_Energy_Small),0,0,22,22);
        TypeEnergyAtlas.addRegion("[DarkEn]",ImageMaster.loadImage(Dark_Energy_Small),0,0,22,22);
        TypeEnergyAtlas.addRegion("[PsyEn]",ImageMaster.loadImage(Psy_Energy_Small),0,0,22,22);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(modID)
                .packageFilter(AbstractDefaultCard.class)
                .setDefaultSeen(true)
                .cards();
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/eng/Charstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/eng/Powerstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    @Override
    public void receiveStartGame() {
        EasyInfoDisplayPanel.specialDisplays.add(new WeatherPanel());
        EasyInfoDisplayPanel.specialDisplays.add(new TypeEnergyPanel());
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        WeatherHelper.CurrentWeather = WeatherHelper.ClearWeather;
        System.out.println("Weather Initialized");
        TypeEnergyHelper.FireEnergy = 0;
        TypeEnergyHelper.WaterEnergy = 0;
        TypeEnergyHelper.ElecEnergy = 0;
        TypeEnergyHelper.IceEnergy = 0;
        TypeEnergyHelper.RockEnergy = 0;
        TypeEnergyHelper.DarkEnergy = 0;
        TypeEnergyHelper.PsyEnergy = 0;
    }
}
