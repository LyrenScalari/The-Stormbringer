package theStormbringer.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theStormbringer.cards.AbstractStormbringerCard;

import static theStormbringer.StormbringerMod.makeID;

public class SecondDamage extends DynamicVariable {

    //For in-depth comments, check the other variable(DefaultCustomVariable). It's nearly identical.

    @Override
    public String key() {
        return makeID("D2");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractStormbringerCard) card).isDamageModified;

    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractStormbringerCard) card).secondDamage;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractStormbringerCard) card).baseSecondDamage;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractStormbringerCard) card).upgradedSecondDamage;
    }
}
