package theStormbringer.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theStormbringer.cards.AbstractStormbringerCard;

import static theStormbringer.StormbringerMod.makeID;


public class SecondMagicNumber extends DynamicVariable {
    @Override
    public String key() {
        return makeID("M2");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractStormbringerCard) card).isSecondMagicModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractStormbringerCard) card).secondMagic;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractStormbringerCard) card).baseSecondMagic;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractStormbringerCard) card).upgradedSecondMagic;
    }
}