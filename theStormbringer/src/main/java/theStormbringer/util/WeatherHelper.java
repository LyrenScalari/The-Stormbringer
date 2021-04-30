package theStormbringer.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;

public class WeatherHelper {
    public static String CurrentWeather;
    public static final String ClearWeather = "Clear";
    public static final String RainWeather = "Rain";
    public static final String SunWeather = "Sun";
    public static final String HailWeather = "Hail";
    public static final String SandWeather = "Sandstorm";
    public void init(){

    }

    public static int WeatherTimer = 5;

    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card){
        if (CurrentWeather.equals(RainWeather)){                      
            if (card.hasTag(CardTags.Water)){                         
                return damage += Math.floor(card.damage * 0.10);      
            }                                                         
        }
        if (CurrentWeather.equals(SunWeather)){
            if (card.hasTag(CardTags.Fire)){
                return damage += Math.floor(card.damage * 0.10);
            }
        }
        
        return damage;
    }

    public void atStartofTurn(){
        
    }
}
