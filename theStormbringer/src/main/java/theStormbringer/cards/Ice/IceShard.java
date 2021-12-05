package theStormbringer.cards.Ice;

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

import static theStormbringer.StormbringerMod.*;

public class IceShard extends AbstractStormbringerCard {
    public final static String ID = makeID(IceShard.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // intellij stuff attack, enemy, common, 4, 1, , , 4, -1

    public IceShard() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, TheStormbringer.Enums.COLOR_NAVY);
        baseDamage = 4;
        baseSecondDamage = 8;
        baseMagicNumber = magicNumber = 3;
        setOrbTexture(Ice_Energy,Ice_Energy_Portrait);
        energyCosts = new EnumMap<TypeEnergyHelper.Mana, Integer>(TypeEnergyHelper.Mana.class);
        energyCosts.put(TypeEnergyHelper.Mana.Ice, magicNumber);
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (TypeEnergyHelper.getManaByEnum(TypeEnergyHelper.Mana.Ice) < magicNumber) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage)));
            addToBot(new GainTypedEnergyAction(TypeEnergyHelper.Mana.Ice,1));
        } else {
            addToBot(new EmpowerAction(energyCosts,()->new DamageAction(m, new DamageInfo(p, secondDamage))));
        }
    }

    public void upp() {
        upgradeDamage(1);
        upgradeSecondDamage(2);
        upgradeMagicNumber(-1);
        energyCosts.put(TypeEnergyHelper.Mana.Ice, magicNumber);
    }
}