package theStormbringer.relics;

import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theStormbringer.StormbringerMod;
import theStormbringer.actions.ChangeWeatherAction;
import theStormbringer.cards.Fire.SunnyDay;
import theStormbringer.cards.Ice.Hail;
import theStormbringer.cards.Rain.RainDance;
import theStormbringer.util.TextureLoader;
import theStormbringer.util.WeatherEffects.*;

import java.util.ArrayList;

import static theStormbringer.StormbringerMod.makeRelicOutlinePath;
import static theStormbringer.StormbringerMod.makeRelicPath;

public class CrackedIris extends AbstractEasyRelic {
    public static final String ID = StormbringerMod.makeID("CrackedIris");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    private static ArrayList<AbstractWeather> WeatherCards = new ArrayList<>();
    public CrackedIris() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK);
        AbstractWeather Rain = new HeavyRain();
        AbstractWeather Sun = new HarshSunlight();
        AbstractWeather Hail = new Hailstorm();
        WeatherCards.add(Rain);
        WeatherCards.add(Sun);
        WeatherCards.add(Hail);
    }
    @Override
    public void atPreBattle() {
        AbstractWeather StarterWeather = WeatherCards.get(AbstractDungeon.miscRng.random(WeatherCards.size()-1));
        StarterWeather.weathertimer += 1;
        AbstractDungeon.actionManager.addToBottom(new ChangeWeatherAction(StarterWeather));
    }
}
