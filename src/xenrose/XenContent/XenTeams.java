package xenrose.XenContent;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.game.Team;

public class XenTeams {
    public static Seq<Team> all = Seq.with();
    public static Team renars;
    public static void load() {
        renars = newTeam(5,"renars", Color.valueOf("37dcac"));
        renars.hasPalette = true;

        Team.baseTeams[5] = renars;
    }

    private static Team newTeam(int id, String name, Color color) {
        Team team = Team.get(id);
        team.name = name;
        team.color.set(color);
        all.add(team);

        team.palette[0] = color;
        team.palette[1] = color.cpy().mul(0.75f);
        team.palette[2] = color.cpy().mul(0.5f);

        for(int i = 0; i < 3; i++){
            team.palettei[i] = team.palette[i].rgba();
        }
        return team;
    }
}
