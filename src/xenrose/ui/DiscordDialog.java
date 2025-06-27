package xenrose.ui;

import arc.*;
import arc.graphics.*;
import arc.scene.ui.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import xenrose.util.XenIcons;

import static mindustry.Vars.*;
import static xenrose.core.XenVars.XenDiscordURL;

public class DiscordDialog extends Dialog{

    public DiscordDialog(){
        super("");

        float h = 80;

        cont.margin(12f);

        Color color = Color.valueOf("742cb8");

        cont.table(t -> {
            t.background(Tex.button).margin(10);

            t.table(img -> {
                img.image().height(h - 5).width(40f).color(color);
                img.row();
                img.image().height(14).width(40f).color(color.cpy().mul(0.8f, 0.8f, 0.8f, 1f));
            }).expandY();

            t.table(i -> {
                i.image(XenIcons.xenDiscord);
            }).size(h).left();

            t.add("@discord").color(Pal.accent).growX().padLeft(10f);
        }).size(520f, h).pad(10f);

        buttons.defaults().size(170f, 50);

        buttons.button("@back", Icon.left, this::hide);
        buttons.button("@copylink", Icon.copy, () -> {
            Core.app.setClipboardText(XenDiscordURL);
            ui.showInfoFade("@copied");
        });
        buttons.button("@openlink", XenIcons.xenDiscord, () -> {
            if(!Core.app.openURI(XenDiscordURL)){
                ui.showErrorMessage("@linkfail");
                Core.app.setClipboardText(XenDiscordURL);
            }
        });
    }
}
