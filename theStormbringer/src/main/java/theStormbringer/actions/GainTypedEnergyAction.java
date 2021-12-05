package theStormbringer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theStormbringer.StormbringerMod;
import theStormbringer.characters.TheStormbringer;
import theStormbringer.util.TypeEnergyHelper;
import theStormbringer.util.WeatherEffects.AbstractWeather;

public class GainTypedEnergyAction extends AbstractGameAction {
    TypeEnergyHelper.Mana EnergyType;
    int amount;
    public GainTypedEnergyAction(TypeEnergyHelper.Mana mana , int Amount) {
        EnergyType = mana;
        amount = Amount;
    }
    public void update() {
        TypeEnergyHelper.setManaByEnum(EnergyType,TypeEnergyHelper.getManaByEnum(EnergyType)+amount);
        this.isDone = true;
    }
}
