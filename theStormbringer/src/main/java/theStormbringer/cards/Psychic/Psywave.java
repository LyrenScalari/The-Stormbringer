package theStormbringer.cards.Psychic;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theStormbringer.cards.AbstractDefaultCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;

import static theStormbringer.StormbringerMod.*;

public class Psywave extends AbstractDefaultCard {

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
        magicNumber = baseMagicNumber = 3;
        setOrbTexture(Psy_Energy,Psy_Energy_Portrait);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (TypeEnergyHelper.PsyEnergy < magicNumber) {
            int RngDmg = AbstractDungeon.miscRng.random(damage, secondDamage);
            addToBot(new DamageAction(m, new DamageInfo(p, RngDmg)));
            TypeEnergyHelper.PsyEnergy += 1;
        } else {
            TypeEnergyHelper.PsyEnergy -= magicNumber;
            addToBot(new DamageAction(m, new DamageInfo(p, secondDamage)));
            addToBot(new DamageAction(m, new DamageInfo(p, secondDamage)));
        }

    }

    // Upgraded stats.
    @Override
    public void upp() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeSecondDamage(2);
            initializeDescription();
        }
    }
}