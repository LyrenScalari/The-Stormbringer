package theStormbringer.util.WeatherEffects;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;

import static theStormbringer.StormbringerMod.makeID;

public class ClearWeather extends AbstractWeather {
    static final String Weather_ID = makeID("Clear");
    static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(Weather_ID);
    public ClearWeather(){
        name = stanceString.NAME;
        description = stanceString.DESCRIPTION[0];
    }
    @Override
    public String updateDescription() {
        return description;
    }

}
