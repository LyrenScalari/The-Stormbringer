package theStormbringer.cards.Psychic;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.actions.EmpowerAction;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;

import java.util.EnumMap;
import java.util.Map;

import static theStormbringer.StormbringerMod.*;

public class Psywave extends AbstractStormbringerCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Strike Deal 7(9) damage.
     */

    // TEXT DECLARATION

    public static final String ID = makeID(Psywave.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");
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
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheStormbringer.Enums.COLOR_NAVY;

    private static final int COST = 1;
    private static final int DAMAGE = 3;
    private static final int UPGRADE_PLUS_DMG = 2;

    public Psywave() {
        super(ID,COST,TYPE,RARITY,TARGET,COLOR);
        baseDamage = DAMAGE;
        secondDamage = baseSecondDamage = 9;
        setOrbTexture(Psy_Energy,Psy_Energy_Portrait);
        energyCosts = new EnumMap<TypeEnergyHelper.Mana, Integer>(TypeEnergyHelper.Mana.class);
        energyCosts.put(TypeEnergyHelper.Mana.Psychic, 1);
        energyCosts.put(TypeEnergyHelper.Mana.Colorless, 1);
        Type = TypeEnergyHelper.Mana.Psychic;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (TypeEnergyHelper.hasEnoughMana(energyCosts).containsValue(false)) {
            int RngDmg = AbstractDungeon.miscRng.random(damage, secondDamage);
            addToBot(new DamageAction(m, new DamageInfo(p, RngDmg)));
            super.use(p,m);
        } else {
            addToBot(new EmpowerAction(energyCosts,()->new DamageAction(m, new DamageInfo(p, secondDamage))));
        }
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeName();
        upgradeDamage(UPGRADE_PLUS_DMG);
        upgradeSecondDamage(2);
        initializeDescription();
    }
}