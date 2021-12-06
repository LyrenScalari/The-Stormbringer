package theStormbringer.cards.Rain;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import theStormbringer.actions.EmpowerAction;
import theStormbringer.actions.GainTypedEnergyAction;
import theStormbringer.cards.AbstractStormbringerCard;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.powers.AquaRingPower;
import theStormbringer.util.TypeEnergyHelper;
import theStormbringer.util.Wiz;

import java.util.EnumMap;

import static theStormbringer.StormbringerMod.*;

public class AquaRing extends AbstractStormbringerCard {
    public final static String ID = makeID(AquaRing.class.getSimpleName());
    // intellij stuff skill, self, common, , , 8, 2, 1, 1

    public AquaRing() {
        super(ID,1 , CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, TheStormbringer.Enums.COLOR_NAVY);
        baseBlock = 8;
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
        setOrbTexture(Water_Energy, Water_Energy_Portrait);
        energyCosts = new EnumMap<TypeEnergyHelper.Mana, Integer>(TypeEnergyHelper.Mana.class);
        energyCosts.put(TypeEnergyHelper.Mana.Water, 2);
        Type = TypeEnergyHelper.Mana.Water;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (!TypeEnergyHelper.hasEnoughMana(energyCosts).containsValue(false)) {
            addToBot(new EmpowerAction(energyCosts,()-> new AbstractGameAction() {
                @Override
                public void update() { Wiz.applyToSelf(new AquaRingPower("Clear Water", block)); }
            }));
        } else super.use(p,m);
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}