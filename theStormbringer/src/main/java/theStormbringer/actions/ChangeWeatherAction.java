package theStormbringer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;
import theStormbringer.StormbringerMod;
import theStormbringer.util.WeatherEffects.AbstractWeather;

import java.util.Iterator;

public class ChangeWeatherAction extends AbstractGameAction {
    private String id;
    private AbstractWeather newStance;

    public ChangeWeatherAction(String stanceId) {
        this.newStance = null;
        this.duration = Settings.ACTION_DUR_FAST;
        this.id = stanceId;
    }

    public ChangeWeatherAction(AbstractWeather newStance) {
        this(newStance.name);
        this.newStance = newStance;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractWeather oldStance = StormbringerMod.currentWeather;
            if (oldStance != null) {
                if (!oldStance.name.equals(this.id)) {
                    oldStance.onWeatherSubside();
                    StormbringerMod.currentWeather = this.newStance;
                    this.newStance.onSummonWeather();
                }

                AbstractDungeon.onModifyPower();
                if (Settings.FAST_MODE) {
                    this.isDone = true;
                    return;
                }
            }
        }
        this.tickDuration();
    }
}
