package xenrose.world.blocks.production;

import arc.audio.Sound;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.struct.ObjectFloatMap;
import arc.util.*;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.world.blocks.production.WallCrafter;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.Env;
import xenrose.XenAttributes;
import xenrose.XenContent.XenItems;

public class WallBurstDrill extends WallCrafter {
    public float shake = 2f;
    public Interp speedCurve = Interp.pow2In;
    public float hardnessDrillMultiplier = 50f;
    public float drillEffectRnd = -1f;
    public Effect drillEffect = Fx.mine;

    public TextureRegion topRegion;
    public TextureRegion arrowRegion;
    public TextureRegion arrowBlurRegion;
    public TextureRegion glowRegion;


    public float invertedTime = 200f;
    public float arrowSpacing = 4f, arrowOffset = 0f;
    public int arrows = 3;
    public boolean drawMineItem = true;
    public Color arrowColor = Color.valueOf("feb380"), baseArrowColor = Color.valueOf("6e7080");
    public Color glowColor = arrowColor.cpy();

    public Item output = XenItems.damascus;

    public Sound drillSound = Sounds.drillImpact;
    public float drillSoundVolume = 0.6f, drillSoundPitchRand = 0.1f;
    public ObjectFloatMap<Item> drillMultipliers = new ObjectFloatMap<>();
    public Attribute attribute = XenAttributes.damascus;

    public WallBurstDrill(String name) {
        super(name);
        hardnessDrillMultiplier = 0f;
        //generally at center
        drillEffectRnd = 0f;
        drillEffect = Fx.shockwave;
        ambientSoundVolume = 0.18f;
        ambientSound = Sounds.drillCharge;
        rotate = true;
        update = true;
        solid = true;
        regionRotated1 = 1;


        envEnabled |= Env.space;
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, topRegion};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        Draw.rect(topRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
    }


       public class WallBurstDrillBuild extends WallCrafterBuild{
           public float smoothProgress = 0f;
           public float invertTime = 0f;
           public int dominantItems;
           public Item dominantItem;

           @Override
           public void draw(){
               Draw.rect(region, x, y);
               drawDefaultCracks();

               Draw.rect(block.region, x, y);
               Draw.rect(topRegion, x, y, rotdeg());
               float ds = 0.6f, dx = Geometry.d4x(rotation) * ds, dy = Geometry.d4y(rotation) * ds;
               if(invertTime > 0 && topRegion.found()){
                   Draw.alpha(Interp.pow3Out.apply(invertTime));
                   Draw.color();
               }

               if(dominantItem != null && drawMineItem){
                   Draw.color(dominantItem.color);
                   Draw.color();
               }

               float fract = smoothProgress;
               Draw.color(arrowColor);
               for(int i = 0; i < 4; i++){
                   for(int j = 0; j < arrows; j++){
                       float arrowFract = (arrows - 1 - j);
                       float a = Mathf.clamp(fract * arrows - arrowFract);
                       Tmp.v1.trns(i * 90, j * arrowSpacing + arrowOffset);

                       //TODO maybe just use arrow alpha and draw gray on the base?
                       Draw.z(Layer.block);
                       Draw.color(baseArrowColor, arrowColor, a);
                       Draw.rect(arrowRegion, x + Tmp.v1.x, y + Tmp.v1.y, i * 1);

                       Draw.color(arrowColor);

                       if(arrowBlurRegion.found()){
                           Draw.z(Layer.blockAdditive);
                           Draw.blend(Blending.additive);
                           Draw.alpha(Mathf.pow(a, 10f));
                           Draw.rect(arrowBlurRegion, x + Tmp.v1.x, y + Tmp.v1.y, i * 1);
                           Draw.blend();
                       }
                   }
               }
               Draw.color();

               if(glowRegion.found()){
                   Drawf.additive(glowRegion, Tmp.c2.set(glowColor).a(Mathf.pow(fract, 3f) * glowColor.a), x, y);
               }
           }

           public void drawDefaultCracks(){
               super.drawCracks();
           }
    }
}
