package theStormbringer.util;

public class TypeEnergyPanel extends EasyInfoDisplayPanel {

    public TypeEnergyPanel() {
        super(30, 600, 100);
    } // NOTE: X, Y, Width are all multipled by settings.scale on constructor, so use values like this.

    @Override
    public String getTitle() {
        return "Typed Energy";
    }

    @Override
    public String getDescription() {
        String s = "";
        if (TypeEnergyHelper.PsyEnergy > 0){
            s += "Psychic : " + TypeEnergyHelper.PsyEnergy;
        }
        if (TypeEnergyHelper.DarkEnergy > 0){
            if (!s.equals("")) {
                s += " NL Dark : " + TypeEnergyHelper.DarkEnergy;
            } else s += "Dark : " + TypeEnergyHelper.DarkEnergy;
        }
        if (TypeEnergyHelper.ElecEnergy > 0){
            if (!s.equals("")) {
                s += " NL Electric : " + TypeEnergyHelper.ElecEnergy;
            } else s += "Electric : " + TypeEnergyHelper.ElecEnergy;
        }
        if (TypeEnergyHelper.FireEnergy > 0){
            if (!s.equals("")) {
                s += " NL Fire : " + TypeEnergyHelper.FireEnergy;
            } else s += "Fire : " + TypeEnergyHelper.FireEnergy;
        }
        if (TypeEnergyHelper.IceEnergy > 0){
            if (!s.equals("")) {
                s += " NL Ice : " + TypeEnergyHelper.IceEnergy;
            } else s += "Ice : " + TypeEnergyHelper.IceEnergy;
        }
        if (TypeEnergyHelper.RockEnergy > 0){
            if (!s.equals("")) {
                s += " NL Rock : " + TypeEnergyHelper.RockEnergy;
            } else s += "Rock : " + TypeEnergyHelper.RockEnergy;
        }
        if (TypeEnergyHelper.WaterEnergy > 0){
            if (!s.equals("")) {
                s += " NL Rain : " + TypeEnergyHelper.WaterEnergy;
            } else s += "Rain : " + TypeEnergyHelper.WaterEnergy;
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
