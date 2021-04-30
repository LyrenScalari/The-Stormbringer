package theStormbringer.util;

import theStormbringer.StormbringerMod;

public class WeatherPanel extends EasyInfoDisplayPanel {

    public WeatherPanel() {
        super(180, 900, 300);
    } // NOTE: X, Y, Width are all multipled by settings.scale on constructor, so use values like this.

    @Override
    public String getTitle() {
        if (WeatherHelper.CurrentWeather == WeatherHelper.RainWeather){
            return "Weather : Heavy Rainfall";

        } else if  (WeatherHelper.CurrentWeather == WeatherHelper.SunWeather){
            return "Weather : Harsh Sunlight";

        } else if  (WeatherHelper.CurrentWeather == WeatherHelper.HailWeather){
            return "Weather : Hailstorm";

        } else if  (WeatherHelper.CurrentWeather == WeatherHelper.SandWeather){
            return "Weather : Sandstorm";
        }

        return WeatherHelper.ClearWeather;
    }

    @Override
    public String getDescription() {
        String s = "";
        if (WeatherHelper.CurrentWeather == WeatherHelper.ClearWeather){
            s += "No effects";

        } else if  (WeatherHelper.CurrentWeather == WeatherHelper.RainWeather){
            s += "Rain card power #b+10%. NL ";
            s += "At the start of your turn gain 1 Rain Energy. NL";
            s += "#yCurrent effects activate.";

        } else if  (WeatherHelper.CurrentWeather == WeatherHelper.SunWeather){
            s += "Fire card power #b+10%. NL ";
            s += "At the start of your turn gain 1 Fire Energy. NL ";
            s += "#ySunny effects activate.";

        } else if  (WeatherHelper.CurrentWeather == WeatherHelper.HailWeather){
            s += "All enemies are striken by the hail at the end of your turn, and take #b15% of their Current HP in damage. NL ";
            s += "At the start of your turn gain 1 Ice Energy. NL ";
            s += "#yCrystalize effects activate.";

        } else if  (WeatherHelper.CurrentWeather == WeatherHelper.SandWeather){
            s += "All enemies are buffed by the sand at the end of your turn, and take #b15% of their Current HP in damage. NL ";
            s += "At the start of your turn gain 1 Rock Energy. NL ";
            s += "#yAbrasive effects activate.";

        }
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
