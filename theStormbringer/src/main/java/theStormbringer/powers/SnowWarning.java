package theStormbringer.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theStormbringer.StormbringerMod;
import theStormbringer.util.WeatherEffects.Hailstorm;

public class SnowWarning  extends AbstractPower {
    public AbstractCreature source;
    public static final String POWER_ID = StormbringerMod.makeID("SnowWarning");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SnowWarning(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;
        this.loadRegion("ai");
        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those txtures here.

        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (StormbringerMod.currentWeather instanceof Hailstorm){
            StormbringerMod.currentWeather.weathertimer += stackAmount;
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}