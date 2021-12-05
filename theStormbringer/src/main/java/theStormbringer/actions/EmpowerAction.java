package theStormbringer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theStormbringer.patches.RenderFloatyMana;
import theStormbringer.util.TypeEnergyHelper;

import java.util.EnumMap;
import java.util.function.Supplier;

import static theStormbringer.util.TypeEnergyHelper.getManaByEnum;
import static theStormbringer.util.TypeEnergyHelper.setManaByEnum;

public class EmpowerAction extends AbstractGameAction {
    Supplier<AbstractGameAction> EmpowerEffect;
    EnumMap<TypeEnergyHelper.Mana, Integer> energyCosts;
    public EmpowerAction(EnumMap<TypeEnergyHelper.Mana, Integer> energyCosts, Supplier<AbstractGameAction> EmpowerEffect) {
        this.EmpowerEffect = EmpowerEffect;
        this.duration = Settings.ACTION_DUR_FAST;
        this.energyCosts = energyCosts;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if(TypeEnergyHelper.handleElementalCosts(energyCosts)){
                addToTop(EmpowerEffect.get());
            }
            isDone = true;
        }else isDone = true;
        this.tickDuration();
    }

}