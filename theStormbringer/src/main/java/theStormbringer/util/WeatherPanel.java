package theStormbringer.util;

import theStormbringer.StormbringerMod;
import theStormbringer.util.WeatherEffects.ClearWeather;

public class WeatherPanel extends EasyInfoDisplayPanel {

    public WeatherPanel() {
        super(180, 900, 300);
    } // NOTE: X, Y, Width are all multipled by settings.scale on constructor, so use values like this.

    @Override
    public String getTitle() {
        if (StormbringerMod.currentWeather != null){
            return StormbringerMod.currentWeather.name;
        }

        return "";
    }

    @Override
    public String getDescription() {
        String s = "";
        if (StormbringerMod.currentWeather != null && !(StormbringerMod.currentWeather instanceof ClearWeather)){
            s += StormbringerMod.currentWeather.updateDescription();
                s += " NL ";
                if (StormbringerMod.currentWeather.weathertimer > 1) {
                    s += StormbringerMod.currentWeather.weathertimer;
                    s += " turns remaining of ";
                    s += StormbringerMod.currentWeather.name;
                    s += ".";
                } else {
                    s += StormbringerMod.currentWeather.name;
                    s += " ends next turn.";
                }
        } else return "NORENDER";
        if (s.isEmpty()){
            return "NORENDER";
        }
        return s;
    }

    @Override
    public RENDER_TIMING getTiming() {
        return RENDER_TIMING.TIMING_PLAYER_RENDER;
    }
}
