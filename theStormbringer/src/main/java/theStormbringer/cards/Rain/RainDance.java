package theStormbringer.cards.Rain;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.actions.ChangeWeatherAction;
import theStormbringer.actions.EmpowerAction;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.cards.Ice.Hail;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.RainDancePower;
import theStormbringer.powers.SurfPower;
import theStormbringer.powers.WaterPulsePower;
import theStormbringer.util.TypeEnergyHelper;
import theStormbringer.util.WeatherEffects.Hailstorm;
import theStormbringer.util.WeatherEffects.HeavyRain;

import java.util.EnumMap;

import static theStormbringer.StormbringerMod.*;

public class RainDance extends AbstractStormbringerCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Strike Deal 7(9) damage.
     */

    // TEXT DECLARATION

    public static final String ID = makeID(RainDance.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Skill.png");
    // Setting the image as as easy as can possibly be now. You just need to provide the image name
    // and make sure it's in the correct folder. That's all.
    // There's makeCardPath, makeRelicPath, power, orb, event, etc..
    // The list of all of them can be found in the main StormbringerMod.java file in the
    // ==INPUT TEXTURE LOCATION== section under ==MAKE IMAGE PATHS==


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheStormbringer.Enums.COLOR_NAVY;

    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 2;

    public RainDance () {
        super(ID,COST,TYPE,RARITY,TARGET,COLOR);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 3;
        isMultiDamage = true;
        setOrbTexture(Water_Energy,Water_Energy_Portrait);
        energyCosts = new EnumMap<TypeEnergyHelper.Mana, Integer>(TypeEnergyHelper.Mana.class);
        energyCosts.put(TypeEnergyHelper.Mana.Water, 1);
        energyCosts.put(TypeEnergyHelper.Mana.Colorless, 1);
        Type = TypeEnergyHelper.Mana.Water;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChangeWeatherAction(new HeavyRain()));
        if (!TypeEnergyHelper.hasEnoughMana(energyCosts).containsValue(false)) {
            addToBot(new EmpowerAction(energyCosts,()-> new ApplyPowerAction(p,p,new RainDancePower("Storm Waters",damage))));
        } else super.use(p,m);
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeBaseCost(0);
        initializeDescription();
    }
}