package xenrose.XenContent;

import arc.Core;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart;
import mindustry.entities.part.HaloPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.part.ShapePart;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.ShieldWall;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.DuctBridge;
import mindustry.world.blocks.distribution.DuctRouter;
import mindustry.world.blocks.distribution.Junction;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidJunction;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.blocks.production.BeamDrill;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Pump;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.consumers.ConsumeItemCharged;
import mindustry.world.consumers.ConsumeItemExplode;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.BuildVisibility;
import xenrose.world.blocks.defense.Armor;
import xenrose.world.blocks.defense.ArmoredWall;
import xenrose.world.blocks.defense.turret.AccelerationTurret;
import xenrose.world.blocks.disbruction.*;
import xenrose.*;
import xenrose.world.blocks.enviroments.EffectFloor;
import xenrose.world.blocks.enviroments.Pit;
import xenrose.world.blocks.liquid.BreakableConduit;
import xenrose.world.blocks.liquid.BreakableLiquidBridge;
import xenrose.world.blocks.liquid.BreakableLiquidJunction;
import xenrose.world.blocks.liquid.BreakableRouter;
import xenrose.world.blocks.power.*;
import xenrose.world.blocks.production.EnergyDrill;
import xenrose.world.blocks.production.MechanicalDrill;
import xenrose.world.draw.DrawAccelerationHeat;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Interp.pow3Out;
import static mindustry.content.Fx.shootSmokeTitan;
import static mindustry.gen.Sounds.none;
import static mindustry.type.ItemStack.with;

public class XenBlocks {
    public static Block
    //environments
    damascusWall, crackedDamascusWall, unevenDamascusWall, kirmiteStoneWall, oxidizedWall, burnedDamscusWall, graphiticStoneWall, burntDamascusWall, burnedKirmiteStoneWall, orinilWall, peatWall,
    damascusStoneTree, damascusStoneTreeMin, damascusBoulder, damascusCluster, kirmiteStoneTree, kirmiteCrystalOrbs, kirmiteStoneBoulder, kirmiteLargeBoulder, kirmiteTree, oxidizedCluster, oxidizedBoulder, oxidizedPit, graphiticStoneBoulder, burnedDamscusBoulder, burnedDamscusTree, burnedDamscusLargeBoulder, burnedDamscusPit, orinilBoulder, orinilCrystal, peatBoulder,
    softDamascusGround, damascusGround, crackedDamascusGround, deepenedDamascusFloor, damascusPit,  kirmiteStoneFloor, kirmiteStoneGround, solidifiedKirmiteLiquid, kirmite, orinil, deepKirnite, oxidizedFloor, oxidizedGround, burnedDamscusFloor, burnedDamscusGround, graphiticStoneFloor, burntDamascusFloor, burnedKirmiteStoneFloor, orinilGround, orinilFloor, peatFloor,
    zinkWallOre, protexideWallOre,
    //distribution
    damascusConveyor, damascusJunction, damascusRouter, damascusBridge,
    //liquid
    hydraulicPump, fragilePipeline, fragileLiquidRouter, fragileLiquidBridge, reinforcedPipelineJunction,
    //drills
    energyDrill, energyChargedDrill, airMechanicalDrill,
    //production
    pyrometallurgicalInstallation, crusher, waterReformer, dantstalinSmelter, orinilCrucible, energyStabilizingBoiler,energyChargingSplitter,
    //power
    kirmiteEvaporator, cableNode, PowerUpgrader,
    //walls
    reinforcedDamascusWall, reinforcedDamascusWallLarge, diocasiumArmor, enemyShieldWall, enemyShieldWallLarge,
    //units
    groundUnitsAssembler, hoverUnitsAssembler, orinilReassembler,
    //turrets
    samum, desiccation, calmness, dehydration, merge,
    //storage
    coreSunrise, coreZenith;

    public static void load() {
        {
            {
                //environments
                damascusWall = new StaticWall("damascus-wall") {{
                    variants = 2;
                }};
                crackedDamascusWall = new StaticWall("cracked-damascus-wall") {{
                    variants = 2;
                    itemDrop = XenItems.damascus;
                    attributes.set(XenAttributes.damascus, 0.25f);
                }};
                unevenDamascusWall = new StaticWall("uneven-damascus-wall"){{
                    variants = 2;
                }};
                kirmiteStoneWall = new StaticWall("kirmite-stone-wall") {{
                    variants = 3;
                }};
                burntDamascusWall = new StaticWall("burnt-damascus-wall"){{
                    variants = 3;
                }};
                burnedKirmiteStoneWall = new StaticWall("burned-kirmite-stone-wall"){{
                    variants = 3;
                }};
                oxidizedWall = new StaticWall("oxidized-wall") {{
                    variants = 4;
                }};
                burnedDamscusWall = new StaticWall("burned-damscus-wall") {{
                    variants = 3;
                }};
                graphiticStoneWall = new StaticWall("graphitic-stone-wall") {{
                    variants = 3;
                }};
                orinilWall = new StaticWall("orinil-wall"){{
                    variants = 3;
                }};
                peatWall = new StaticWall("peat-wall"){{
                    variants = 3;
                }};

                damascusStoneTree = new TreeBlock("damascus-stone-tree") {{
                    variants = 2;
                    shadowOffset = -1.1f;
                }};
                damascusStoneTreeMin = new TreeBlock("damascus-stone-tree-min") {{
                    variants = 2;
                    shadowOffset = -1.1f;
                }};
                damascusBoulder = new Prop("damascus-boulder") {{
                    variants = 2;
                }};
                damascusCluster = new TallBlock("damascus-cluster") {{
                    variants = 2;
                }};
                kirmiteStoneTree = new TallBlock("kirmite-stone-tree") {{
                    variants = 2;
                    shadowOffset = -1.1f;
                }};
                kirmiteCrystalOrbs = new TallBlock("kirmite-crystal-orbs") {{
                    variants = 2;
                    shadowOffset = -1.1f;
                }};
                kirmiteStoneBoulder = new Prop("kirmite-stone-boulder") {{
                    variants = 2;
                }};
                oxidizedCluster = new TallBlock("oxidized-cluster") {{
                    variants = 3;
                }};
                oxidizedBoulder = new Prop("oxidized-boulder") {{
                    variants = 2;
                }};
                oxidizedPit = new OverlayFloor("oxidized-pit") {{
                    variants = 2;
                }};
                graphiticStoneBoulder = new Prop("graphitic-stone-boulder") {{
                    variants = 2;
                }};
                burnedDamscusBoulder = new Prop("burned-damscus-boulder"){{
                    variants = 2;
                }};
                burnedDamscusLargeBoulder = new Prop("burned-damscus-large-boulder"){{
                    variants = 2;
                    breakable = false;
                }};
                burnedDamscusPit = new Pit("burned-damscus-pit");
                orinilBoulder = new Prop("orinil-boulder"){{
                    variants = 2;
                }};
                orinilCrystal = new TallBlock("orinil-crystal"){{
                    variants = 2;
                }};
                burnedDamscusTree = new TallBlock("burned-damscus-tree");
                kirmiteLargeBoulder = new Prop("kirmite-large-boulder"){{
                    variants = 2;
                    breakable = false;
                }};
                kirmiteTree = new TallBlock("kirmite-tree");
                peatBoulder = new Prop("peat-boulder"){{
                    variants = 2;
                }};

                softDamascusGround = new Floor("soft-damascus-ground") {{
                    variants = 2;
                }};
                damascusGround = new Floor("damascus-ground") {{
                    variants = 3;
                }};
                crackedDamascusGround = new Floor("cracked-damascus-ground") {{
                    variants = 2;
                }};
                deepenedDamascusFloor = new Floor("deepened-damascus-floor"){{
                    variants = 3;
                }};
                damascusPit = new SteamVent("damascus-pit"){{
                    status = StatusEffects.melting;
                    statusDuration = 180f;
                    speedMultiplier = 0.19f;
                    size = 4;
                    variants = 2;
                    effect = new ParticleEffect() {{
                        particles =  3;
                        length = 210;
                        lifetime = 432;
                        cone = 20;
                        baseRotation = 30;
                        sizeFrom = 0;
                        sizeTo = 19;
                        colorFrom = Color.valueOf("6a615285");
                        colorTo = Color.valueOf("c1bba57f");
                        sizeInterp = pow3Out;
                        interp = pow3Out;
                    }};
                    effectColor = Color.valueOf("9d91758c");
                }};
                kirmiteStoneFloor = new Floor("kirmite-stone-floor") {{
                    variants = 3;
                }};
                kirmiteStoneGround = new Floor("kirmite-stone-ground") {{
                    variants = 3;
                }};
                solidifiedKirmiteLiquid = new Floor("solidified-kirmite-liquid") {{
                    variants = 3;
                }};
                kirmite = new EffectFloor("kirmite") {{
                    attributes.set(XenAttributes.kirmite, 0.25f);
                    speedMultiplier = 0.5f;
                    variants = 2;
                    liquidDrop = XenLiquids.liquidKirmit;
                    effect = XenFx.kirmiteSteam;
                    effectChance = 0.0015f;
                    effectColor = Color.valueOf("9265bd");
                    isLiquid = true;
                    cacheLayer = CacheLayer.water;
                    albedo = 0.9f;
                    supportsOverlay = true;
                }};
                deepKirnite = new EffectFloor("kirmite-deep") {{
                    attributes.set(XenAttributes.kirmite, 0.25f);
                    speedMultiplier = 0.5f;
                    variants = 2;
                    liquidDrop = XenLiquids.liquidKirmit;
                    effect = XenFx.kirmiteSteam;
                    effectChance = 0.0015f;
                    effectColor = Color.valueOf("9265bd");
                    isLiquid = true;
                    drownTime = 170f;
                    cacheLayer = CacheLayer.water;
                    albedo = 0.9f;
                    supportsOverlay = true;
                }};
                orinil = new EffectFloor("orinil"){{
                    speedMultiplier = 0.18f;
                    variants = 2;
                    liquidDrop = XenLiquids.liquidOrinil;
                    effect = Fx.fire;
                    effectChance = 0.004f;
                    effectColor = Color.valueOf("9265bd");
                    isLiquid = true;
                    drownTime = 200f;
                    cacheLayer = XenCacheLayer.orinil;
                }};
                oxidizedGround = new Floor("oxidized-ground") {{
                    variants = 2;
                }};
                oxidizedFloor = new Floor("oxidized-floor") {{
                    variants = 3;
                }};
                burnedDamscusFloor = new Floor("burned-damscus-floor") {{
                    variants = 3;
                }};
                burnedDamscusGround = new Floor("burned-damscus-ground") {{
                    variants = 3;
                }};
                graphiticStoneFloor = new Floor("graphitic-stone-floor") {{
                    variants = 4;
                }};
                burntDamascusFloor = new Floor("burnt-damascus-floor"){{
                    variants = 3;
                }};
                burnedKirmiteStoneFloor = new Floor("burned-kirmite-stone-floor"){{
                    variants = 3;
                }};
                orinilGround = new Floor("orinil-ground"){{
                    variants = 2;
                }};
                orinilFloor = new Floor("orinil-floor"){{
                    variants = 4;
                }};
                peatFloor = new Floor("peat-floor"){{
                    variants = 4;
                }};

                zinkWallOre = new OreBlock("zink-wall-ore") {{
                    variants = 2;
                    itemDrop = XenItems.zinc;
                    wallOre = true;
                }};
                protexideWallOre = new OreBlock("protexide-wall-ore"){{
                    variants = 3;
                    itemDrop = XenItems.protexide;
                    wallOre = true;
                }};

                //distribution
                damascusConveyor = new ShadedConveyor("damascus-conveyor") {{
                    requirements(Category.distribution, with(XenItems.damascus, 2));
                    health = 180;
                    speed = 0.04f;
                    displayedSpeed = 2.5f;
                    buildCostMultiplier = 2f;
                    itemCapacity = 3;
                    bridgeReplacement = damascusBridge;
                    junctionReplacement = damascusJunction;

                    hasPower = true;
                    consumesPower = true;
                    conductivePower = true;
                    consumePower(0.5f / 60f);

                    underBullets = true;
                    researchCost = with(XenItems.damascus, 10);
                }};
                damascusJunction = new Junction("damascus-junction") {{
                    requirements(Category.distribution, with(XenItems.damascus, 5));
                    health = 80;
                    capacity = 10;
                    speed = 33f;

                    hasPower = true;
                    consumesPower = true;
                    conductivePower = true;

                    researchCost = with(XenItems.damascus, 30);
                }};
                ((Conveyor)damascusConveyor).junctionReplacement = damascusJunction;
                damascusRouter = new DuctRouter("damascus-router") {{
                    requirements(Category.distribution, with(XenItems.damascus, 8));
                    health = 80;
                    speed = 3.5f;
                    regionRotated1 = 1;
                    solid = false;

                    hasPower = true;
                    consumesPower = true;
                    conductivePower = true;

                    researchCost = with(XenItems.damascus, 40);
                }};
                damascusBridge = new DuctBridge("damascus-bridge") {{
                    requirements(Category.distribution, with(XenItems.damascus, 10));
                    health = 130;
                    speed = 3.5f;
                    solid = false;
                    buildCostMultiplier = 1.5f;

                    hasPower = true;
                    consumesPower = true;
                    conductivePower = true;
                    consumePower(1f / 60f);

                    researchCost = with(XenItems.damascus, 50);
                }};
                ((Conveyor)damascusConveyor).bridgeReplacement = damascusBridge;

                //liquid
                hydraulicPump = new Pump("hydraulic-pump"){{
                    requirements(Category.liquid, with(XenItems.damascus, 50, XenItems.zinc, 20, XenItems.gold, 10));

                    squareSprite = false;
                    pumpAmount = 20f / 60f / 4f;
                    liquidCapacity = 80f;
                    size = 2;

                    researchCost = with(XenItems.damascus, 400, XenItems.zinc, 250, XenItems.gold, 100);
                }};
                fragilePipeline = new BreakableConduit("fragile-pipeline"){{
                    requirements(Category.liquid, with(XenItems.damascus, 3));

                    health = 55;
                    liquidCapacity = 10;
                    liquidLimit = 5;
                    botColor = Color.valueOf("1b1915");

                    researchCost = with(XenItems.damascus, 50);
                }};
                fragileLiquidRouter = new BreakableRouter("fragile-liquid-router"){{
                    requirements(Category.liquid, with(XenItems.damascus, 8));

                    health = 90;
                    liquidCapacity = 15;
                    liquidLimit = 10;

                    researchCost = with(XenItems.damascus, 110);
                }};
                fragileLiquidBridge = new BreakableLiquidBridge("fragile-liquid-bridge"){{
                    requirements(Category.liquid, with(XenItems.damascus, 8, XenItems.zinc, 2));

                    fadeIn = moveArrows = false;
                    arrowSpacing = 6f;
                    range = 5;
                    hasPower = false;
                    liquidLimit = 28;
                    liquidCapacity = 35f;

                    researchCost = with(XenItems.damascus, 100, XenItems.zinc, 80);
                }};
                ((Conduit)fragilePipeline).bridgeReplacement = fragileLiquidBridge;
                reinforcedPipelineJunction =  new LiquidJunction("reinforced-pipeline-junction"){{
                    requirements(Category.liquid, with(XenItems.damascus, 6, XenItems.dantstalin, 2));
                    researchCost = with(XenItems.damascus, 650, XenItems.dantstalin, 550);
                    solid = false;
                }};
                ((Conduit) fragilePipeline).junctionReplacement = reinforcedPipelineJunction;

                //drills
                energyDrill = new MechanicalDrill("energy-drill") {{
                    requirements(Category.production, ItemStack.with(XenItems.damascus, 15));

                    drillTime = 505;
                    size = 2;
                    range = 6;
                    fogRadius = 2;
                    tier = 3;

                    drillEffect = new MultiEffect(
                            new ParticleEffect(){{
                                particles = 8;
                                length = 170;
                                lifetime = 520;
                                sizeFrom = 2f;
                                sizeTo = 6.5f;
                                cone = 6;
                                baseRotation = 50;
                                useRotation = false;
                                interp = pow3Out;
                                colorFrom = Color.valueOf("aab3bfa1");
                                colorTo = Color.valueOf("f29d4e00");
                                layer = 100.2f;
                            }});

                    consumePower(25f / 60f);
                    consumeLiquid(XenLiquids.oxygen, 0.8f / 60f).boost();

                    researchCost = ItemStack.with(XenItems.damascus, 20);
                }};
                energyChargedDrill = new Drill("energy-charged-drill"){{
                    requirements(Category.production, with(XenItems.damascus, 80, XenItems.protexide, 65, XenItems.zinc, 50, XenItems.gold, 15));

                    drillEffect = new MultiEffect(
                            new ParticleEffect(){{
                                particles = 18;
                                length = 170;
                                lifetime = 520;
                                sizeFrom = 2f;
                                sizeTo = 6.5f;
                                cone = 6;
                                baseRotation = 50;
                                useRotation = false;
                                interp = pow3Out;
                                colorFrom = Color.valueOf("aab3bfa1");
                                colorTo = Color.valueOf("f29d4e00");
                                layer = 100.2f;
                            }});
                    tier = 6;
                    drillTime = 380;
                    size = 3;
                    consumeLiquid(Liquids.hydrogen, 3f / 60f);
                    consumePower(120f / 60f);

                    researchCost = with(XenItems.damascus, 1450,XenItems.protexide, 1350, XenItems.zinc, 1300, XenItems.gold, 900);
                }};
                airMechanicalDrill = new MechanicalDrill("air-mechanical-drill"){{
                    requirements(Category.production, with(XenItems.damascus, 120, XenItems.dantstalin, 65, XenItems.zinc, 50, XenItems.gold, 15));

                    drillEffect = new MultiEffect(
                            new ParticleEffect(){{
                                particles = 9;
                                length = 170;
                                lifetime = 520;
                                sizeFrom = 2f;
                                sizeTo = 6.5f;
                                cone = 6;
                                baseRotation = 50;
                                useRotation = false;
                                interp = pow3Out;
                                colorFrom = Color.valueOf("c6cfdab1");
                                colorTo = Color.valueOf("f29d4e00");
                                layer = 100.2f;
                            }},
                            new ParticleEffect(){{
                                particles = 9;
                                length = 170;
                                lifetime = 520;
                                sizeFrom = 2f;
                                sizeTo = 6.5f;
                                cone = 6;
                                baseRotation = 40;
                                useRotation = false;
                                interp = pow3Out;
                                colorFrom = Color.valueOf("9fadbc77");
                                colorTo = Color.valueOf("f29d4e00");
                                layer = 100.2f;
                    }});
                    tier = 6;
                    drillTime = 430;
                    size = 3;
                    consumeLiquid(XenLiquids.oxygen, 6f / 60f);
                    consumePower(220f / 60f);
                }};

                //production
                pyrometallurgicalInstallation = new GenericCrafter("pyrometallurgical-installation") {{
                    requirements(Category.crafting, ItemStack.with(XenItems.damascus, 70, XenItems.zinc, 50));
                    size = 3;
                    outputItem = new ItemStack(XenItems.gold, 2);
                    squareSprite = false;
                    hasItems = true;
                    liquidCapacity = 35f;
                    craftTime = 3 * 60f;
                    craftEffect = new MultiEffect(
                        new ParticleEffect(){{
                        particles = 8;
                        length = 210;
                        lifetime = 670;
                        sizeFrom = 1.5f;
                        sizeTo = 6;
                        cone = 6;
                        baseRotation = 50;
                        useRotation = false;
                        interp = pow3Out;
                        colorFrom = Color.valueOf("ffbd7f9d");
                        colorTo = Color.valueOf("f29d4e00");
                        layer = 100.2f;
                    }},
                    new ParticleEffect(){{
                        particles = 6;
                        length = 210;
                        lifetime = 670;
                        sizeFrom = 1.5f;
                        sizeTo = 5;
                        cone = 6;
                        baseRotation = 50;
                        useRotation = false;
                        interp = pow3Out;
                        colorFrom = Color.valueOf("ff8a1c95");
                        colorTo = Color.valueOf("f29d4e00");
                        layer = 100.2f;
                    }});

                    drawer = new DrawMulti(new DrawRegion("-bottom"),
                            new DrawParticles() {{
                                particles = 12;
                                color = Pal.turretHeat;
                                blending = Blending.additive;
                                particleInterp = Interp.linear;
                                particleRad = 7f;
                                particleSize = 4f;
                            }},
                            new DrawParticles() {{
                                particles = 7;
                                color = Pal.lightOrange;
                                blending = Blending.additive;
                                particleInterp = Interp.one;
                                particleRad = 5f;
                                particleSize = 3f;
                            }},
                            new DrawPistons(){{
                                sinMag = 2f;
                                sinScl = 3f;
                                sides = 2;
                            }},
                            new DrawDefault());

                    consumeLiquid(Liquids.water, 4f / 60f);
                    consumeItems(with(XenItems.damascus, 1, XenItems.zinc, 1));
                    consumePower(80f / 60f);
                    researchCost = with(XenItems.damascus, 50, XenItems.zinc, 50);
                }};
                crusher = new GenericCrafter("crusher"){{
                    requirements(Category.crafting, ItemStack.with(XenItems.damascus, 10, XenItems.zinc, 5));
                    size = 2;
                    outputItem = new ItemStack(Items.sand, 2);
                    squareSprite = false;
                    hasItems = true;
                    craftTime = 1.5f * 60f;
                    craftEffect = new RadialEffect(XenFx.crusherSmoke, 3, 90f, 0.8f);

                    drawer = new DrawMulti(new DrawRegion("-bottom"),
                            new DrawRegion("-rotator", 1.5f){{
                                spinSprite = true;
                            }},
                            new DrawDefault()
                            );

                    consumeItem(XenItems.damascus, 1);
                    consumePower(10f / 60f);
                    fogRadius = 2;
                    researchCost = with(XenItems.damascus, 50, XenItems.zinc, 30);
                }};
                waterReformer = new GenericCrafter("water-reformer"){{
                    requirements(Category.crafting, ItemStack.with(XenItems.damascus, 100, XenItems.zinc, 80, XenItems.gold, 55));
                    size = 3;
                    squareSprite = false;
                    hasItems = false;
                    craftTime = 8f;

                    rotate = true;
                    invertFlip = true;
                    group = BlockGroup.liquids;
                    itemCapacity = 0;

                    liquidCapacity = 40f;

                    drawer = new DrawMulti(
                            new DrawRegion("-bottom"),
                            new DrawLiquidTile(Liquids.water, 2f),
                            new DrawBubbles(Color.valueOf("7693e3")){{
                                sides = 10;
                                recurrence = 3f;
                                spread = 6;
                                radius = 1.5f;
                                amount = 20;
                            }}, new DrawRegion("-rotator", 1.3f){{
                                spinSprite = true;
                        }}, new DrawRegion(), new DrawLiquidOutputs(), new DrawRegion("-top"));

                    ambientSound = Sounds.electricHum;
                    ambientSoundVolume = 0.08f;
                    consumeLiquid(Liquids.water, 18f / 60f);
                    consumePower(160f / 60f);

                    regionRotated1 = 3;
                    outputLiquids = LiquidStack.with(XenLiquids.oxygen, 6f / 60, Liquids.hydrogen, 5f / 60);
                    liquidOutputDirections = new int[]{1, 3};
                    researchCost = with(XenItems.damascus, 1300, XenItems.zinc, 1400, XenItems.gold, 800);
                }};
                dantstalinSmelter = new GenericCrafter("dantstalin-smelter"){{
                    requirements(Category.crafting, ItemStack.with(XenItems.damascus, 120, XenItems.zinc, 90, XenItems.gold, 60));
                    size = 2;
                    squareSprite = false;
                    hasItems = true;
                    hasLiquids = true;
                    craftTime = 1.2f * 60f;
                    outputItem = new ItemStack(XenItems.dantstalin, 2);
                    itemCapacity = 15;
                    liquidCapacity = 25;
                    craftEffect = new MultiEffect(
                            new WaveEffect(){{
                                lifetime = 20f;
                                colorFrom = Color.valueOf("ffffff");
                                colorTo = Color.valueOf("c6cfda");
                                sizeFrom = 0;
                                sizeTo = 10;
                                lightScl = 3;
                                lightOpacity = 1;
                                strokeFrom = 2.4f;
                                strokeTo = 0;
                            }},
                            new ParticleEffect(){{
                                lifetime = 28f;
                                colorFrom = Color.valueOf("c6cfda");
                                colorTo = Color.valueOf("ffffff");
                                particles = 5;
                                length = 10;
                                baseLength = 2;
                                spin = 0;
                                sizeFrom = 2f;
                                sizeTo = 0;
                                offset = 1;
                            }});

                    drawer = new DrawMulti(new DrawDefault(), new DrawGlowRegion(){{
                        color = Color.valueOf("9ecaff");
                    }});

                    ambientSound = Sounds.electricHum;
                    ambientSoundVolume = 0.095f;
                    consumeItem(XenItems.zinc, 2);
                    consumeLiquids(LiquidStack.with(XenLiquids.liquidKirmit, 46f / 60f, XenLiquids.oxygen, 2f / 60f));
                    consumePower(290f / 60f);
                    researchCost = with(XenItems.damascus, 2870, XenItems.zinc, 2670, XenItems.gold, 2140);
                }};
                orinilCrucible = new GenericCrafter("orinil-crucible"){{
                    requirements(Category.crafting, ItemStack.with(XenItems.damascus, 90, Items.tungsten, 80, XenItems.zinc, 65, XenItems.gold, 40));
                    size = 3;
                    outputItem = new ItemStack(XenItems.diocasium, 1);
                    squareSprite = false;
                    hasItems = true;
                    craftTime = 2f * 60f;
                    craftEffect = new MultiEffect(
                            new ParticleEffect(){{
                                particles = 6;
                                length = 210;
                                lifetime = 670;
                                sizeFrom = 1.5f;
                                sizeTo = 5;
                                cone = 6;
                                baseRotation = 50;
                                useRotation = false;
                                interp = pow3Out;
                                colorFrom = Color.valueOf("ffc77d8d");
                                colorTo = Color.valueOf("f29d4e00");
                                layer = 100.2f;
                            }},
                            new ParticleEffect(){{
                                particles = 10;
                                length = 210;
                                lifetime = 670;
                                sizeFrom = 1.5f;
                                sizeTo = 5;
                                cone = 6;
                                baseRotation = 50;
                                useRotation = false;
                                interp = pow3Out;
                                colorFrom = Color.valueOf("ffaa3b8d");
                                colorTo = Color.valueOf("f29d4e00");
                                layer = 100.2f;
                            }});

                    drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(XenLiquids.liquidOrinil, 2.8f),
                            new DrawParticles() {{
                                particles = 15;
                                color =  Color.valueOf("ff8c3bb0");
                                blending = Blending.additive;
                                particleInterp = Interp.linear;
                                particleRad = 9f;
                                particleSize = 2.5f;
                            }},
                            new DrawParticles() {{
                                particles = 15;
                                color =  Color.valueOf("ff450d8a");
                                blending = Blending.additive;
                                particleInterp = Interp.linear;
                                particleRad = 9f;
                                particleSize = 2.5f;
                            }}, new DrawDefault(), new DrawRegion("-top"));

                    consumeLiquids(LiquidStack.with(XenLiquids.liquidOrinil, 20f / 60f, XenLiquids.oxygen, 6f / 60f));
                    consumeItem(XenItems.damascus, 3);
                    consumePower(140f / 60f);
                    fogRadius = 3;
                    researchCost = with(XenItems.damascus, 2560, Items.tungsten, 1960, XenItems.zinc, 1600, XenItems.gold, 800);
                }};
                energyStabilizingBoiler = new GenericCrafter("energy-stabilizing-boiler"){{
                    requirements(Category.crafting, ItemStack.with(XenItems.damascus, 160, XenItems.zinc, 120, Items.tungsten, 100, XenItems.gold, 50, XenItems.diocasium, 10));
                    size = 3;
                    outputItem = new ItemStack(XenItems.isoteron, 3);
                    squareSprite = false;
                    hasItems = true;
                    hasLiquids = true;
                    craftTime = 3.5f * 60f;

                    drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(XenLiquids.liquidOrinil, 1.9f), new DrawCells(){{
                        color = Color.valueOf("e18d47");
                        particleColorFrom = Color.valueOf("fbbb67");
                        particleColorTo = Color.valueOf("a95712");
                        particles = 42;
                        range = 6f;
                    }}, new DrawRegion("-rotator",0.7f){{
                        spinSprite = true;
                    }}, new DrawDefault(), new DrawGlowRegion(), new DrawRegion("-top"));

                    consumeLiquids(LiquidStack.with(XenLiquids.liquidKirmit, 20f / 60f, XenLiquids.liquidOrinil, 25f / 60f));
                    consumeItems(with(XenItems.damascus, 3, XenItems.diocasium, 1));
                    consumePower(420f / 60f);
                    fogRadius = 3;
                    liquidCapacity = 50;
                    itemCapacity = 25;
                    researchCost = with(XenItems.damascus, 2700, XenItems.zinc, 2680, Items.tungsten, 2450, XenItems.gold, 2000, XenItems.diocasium, 650);
                }};
                energyChargingSplitter = new GenericCrafter("energy-charging-splitter"){{
                    requirements(Category.crafting, ItemStack.with(XenItems.damascus, 200, XenItems.zinc, 120, Items.tungsten, 95, XenItems.gold, 80, XenItems.dantstalin, 50, XenItems.diocasium, 25, XenItems.isoteron, 10));
                    size = 4;
                    outputItem = new ItemStack(XenItems.moluron, 2);
                    squareSprite = false;
                    hasItems = true;
                    itemCapacity = 45;
                    liquidCapacity = 50;
                    hasLiquids = true;
                    craftTime = 1.6f * 60f;
                    craftEffect = new MultiEffect(
                            new ParticleEffect(){{
                                particles = 14;
                                length = 270;
                                lifetime = 730;
                                sizeFrom = 2.3f;
                                sizeTo = 6;
                                cone = 6;
                                baseRotation = 50;
                                useRotation = false;
                                interp = pow3Out;
                                colorFrom = Color.valueOf("e1c1ffaa");
                                colorTo = Color.valueOf("f29d4e00");
                                layer = 100.2f;
                            }},
                            new ParticleEffect(){{
                                particles = 10;
                                length = 270;
                                lifetime = 730;
                                sizeFrom = 2.3f;
                                sizeTo = 6;
                                cone = 6;
                                baseRotation = 50;
                                useRotation = false;
                                interp = pow3Out;
                                colorFrom = Color.valueOf("c29cffab");
                                colorTo = Color.valueOf("f29d4e00");
                                layer = 100.2f;
                            }});

                    drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(XenLiquids.liquidKirmit, 3.5f),
                            new DrawParticles() {{
                                particles = 15;
                                color =  Color.valueOf("c29cff9e");
                                blending = Blending.additive;
                                particleInterp = Interp.linear;
                                particleRad = 15f;
                                particleSize = 3f;
                            }},
                            new DrawParticles() {{
                                particles = 15;
                                color =  Color.valueOf("d49cff96");
                                blending = Blending.additive;
                                particleInterp = Interp.linear;
                                particleRad = 13f;
                                particleSize = 2.3f;
                            }}, new DrawDefault(), new DrawGlowRegion(), new DrawRegion("-top"));

                    consumeLiquids(LiquidStack.with(XenLiquids.liquidKirmit, 45f / 60f, XenLiquids.oxygen, 10f / 60f));
                    consumeItems(with(XenItems.damascus, 6, XenItems.diocasium, 1));
                    consumePower(550f / 60f);
                    fogRadius = 4;
                    researchCost = with(XenItems.damascus, 6300, XenItems.zinc, 6200, Items.tungsten, 5960, XenItems.gold, 5400, XenItems.dantstalin, 3760, XenItems.diocasium, 2500, XenItems.isoteron, 550);
                }};

                //power
                kirmiteEvaporator = new BetterConsumeGenerator("kirmite-evaporator") {{
                    requirements(Category.power, with(XenItems.damascus, 50, XenItems.zinc, 20));
                    attribute = XenAttributes.kirmite;
                    powerProduction = 1.33333333f;
                    displayEfficiency = false;
                    size = 2;
                    ambientSound = Sounds.hum;
                    ambientSoundVolume = 0.06f;
                    squareSprite = false;

                    drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(),
                            new DrawRegion("-rotator", 1.1f) {{
                                spinSprite = true;
                            }},
                            new DrawRegion("-top"),
                            new DrawSoftParticles() {{
                                alpha = 0.35f;
                                particleRad = 19f;
                                particleSize = 2.3f;
                                particleLife = 95f;
                                particles = 10;
                                color = Color.valueOf("bd8cec");
                            }});

                    hasItems = true;
                    hasLiquids = true;
                    outputLiquid = new LiquidStack(Liquids.water, 2f / 60f);
                    liquidCapacity = 30f;
                    itemDuration = 120;
                    itemCapacity = 10;
                    fogRadius = 2;
                    consume(new ConsumeItemCharged(4));
                    researchCost = with(XenItems.damascus, 15, XenItems.zinc, 20);
                }};

                cableNode = new CableNode("cable-node") {{
                    requirements(Category.power, with(XenItems.zinc, 5));

                    size = 1;
                    range = 7;
                    researchCost = with(XenItems.zinc, 20);
                }};
                PowerUpgrader = new PowerUpgrader("power-upgrader"){{
                    requirements(Category.power, with(XenItems.damascus, 80, XenItems.zinc, 40));

                    upgrade = 2;
                    size = 2;
                    consumeLiquid(XenLiquids.liquidOrinil, 8f / 60f);
                    researchCost = with(XenItems.damascus, 1550, XenItems.zinc, 1300, XenItems.gold, 850);
                }};

                //walls
                reinforcedDamascusWall = new ArmoredWall("reinforced-damascus-wall") {{
                    researchCost = ItemStack.with(XenItems.damascus, 80f, XenItems.zinc, 20);
                    requirements(Category.defense, ItemStack.with(XenItems.damascus, 6f, XenItems.zinc, 3));
                    health = 310;
                    armor = 2;
                    shieldHealth = 1200;
                }};
                reinforcedDamascusWallLarge = new ArmoredWall("reinforced-damascus-wall-large") {{
                    researchCost = ItemStack.with(XenItems.damascus, 250f, XenItems.zinc, 100);
                    requirements(Category.defense, ItemStack.with(XenItems.damascus, 6 * 4, XenItems.zinc, 3 * 4));
                    health = 310 * 4;
                    armor = 2;
                    size = 2;
                    shieldHealth = 1200;
                }};
                diocasiumArmor = new Armor("diocasium-armor"){{
                    researchCost = ItemStack.with(XenItems.damascus, 1f);
                }};
                enemyShieldWall = new ShieldWall("enemy-reinforced-damascus-wall"){{
                    requirements(Category.defense, ItemStack.with(XenItems.damascus, 6f, XenItems.zinc, 3));
                    health = 310;
                    armor = 2;
                    shieldHealth = 1200;
                }};
                enemyShieldWallLarge = new ShieldWall("enemy-reinforced-damascus-wall-large"){{
                    requirements(Category.defense, ItemStack.with(XenItems.damascus, 6 * 4, XenItems.zinc, 3 * 4));
                    health = 310 * 4;
                    armor = 2;
                    size = 2;
                    shieldHealth = 1200;
                }};

                //units
                groundUnitsAssembler = new UnitFactory("ground-units-assembler"){{
                    requirements(Category.units, with(XenItems.damascus, 150,XenItems.zinc, 120, XenItems.gold, 50));
                    size = 3;
                    configurable = false;
                    plans.add(new UnitPlan(XenUnits.zanar, 60f * 38f, with(XenItems.zinc, 35, XenItems.gold, 20)));
                    regionSuffix = "-dark";
                    fogRadius = 3;
                    consumePower(1.666666666666667f);
                    consumeLiquid(XenLiquids.liquidKirmit, 14f/60f);
                    researchCost = with(XenItems.damascus, 650,XenItems.zinc, 600, XenItems.gold, 300);
                }};
                hoverUnitsAssembler =new UnitFactory("hover-units-assembler"){{
                    requirements(Category.units, with(XenItems.damascus, 180,XenItems.zinc, 130, XenItems.gold, 60));
                    size = 3;
                    configurable = false;
                    plans.add(new UnitPlan(XenUnits.imitation, 60f * 26f, with(XenItems.zinc, 40, XenItems.gold, 15)));
                    regionSuffix = "-dark";
                    fogRadius = 3;
                    consumePower(1.666666666666667f);
                    consumeLiquid(XenLiquids.liquidKirmit, 20f/60f);
                    researchCost = with(XenItems.damascus, 1650, XenItems.zinc, 1460, XenItems.gold, 1100);
                }};
                orinilReassembler = new Reconstructor("orinil-reassembler"){{
                    requirements(Category.units, with(XenItems.damascus, 340, XenItems.zinc, 250, XenItems.dantstalin, 70, XenItems.gold, 50));
                    size = 4;
                    consumePower(380f / 60f);
                    consumeItems(with(XenItems.gold, 50, XenItems.dantstalin, 30));
                    consumeLiquids(LiquidStack.with(XenLiquids.liquidOrinil, 20f / 60f, XenLiquids.oxygen, 5f / 60f));

                    upgrades.addAll(
                            new UnitType[]{XenUnits.zanar, XenUnits.inorn},
                            new UnitType[]{XenUnits.imitation, XenUnits.simulation}
                    );

                    constructTime = 28f * 60f;
                    researchCost = with(XenItems.damascus, 4920, XenItems.zinc, 4560, XenItems.dantstalin, 3200, XenItems.gold, 1890);
                }};

                //turrets
                samum = new AccelerationTurret("samum") {{
                    requirements(Category.turret, with(XenItems.damascus, 70, XenItems.zinc, 40));

                    ammo(
                            XenItems.zinc, new BasicBulletType(4f, 25){{
                                    sprite = "xenrose-star-bullet";
                                    width = 8f;
                                    height = 13f;
                                    lifetime = 70f;
                                    ammoMultiplier = 2;
                                    pierceCap = 2;
                                    pierce = true;
                                    pierceBuilding = true;
                                    buildingDamageMultiplier = 0.3f;
                                    trailWidth = 2.3f;
                                    trailLength = 8;
                                    backColor = Color.valueOf("605e6e");
                                    frontColor = trailColor = Color.valueOf("c3c0d9");
                                    trailEffect = Fx.hitSquaresColor;
                                    trailRotation = true;
                                    trailInterval = 8f;
                                    hitEffect = despawnEffect = new ExplosionEffect() {{
                                        lifetime = 34f;
                                        waveStroke = 2f;
                                        waveColor = sparkColor = trailColor;
                                        waveRad = 24f;
                                        smokeSize = 0f;
                                        smokeSizeBase = 0f;
                                        sparks = 5;
                                        sparkRad = 16f;
                                        sparkLen = 6f;
                                        sparkStroke = 2f;
                                    }};
                                }},
                            XenItems.gold, new BasicBulletType(4.5f, 40){{
                                    sprite = "xenrose-star-bullet";
                                    width = 10f;
                                    height = 15f;
                                    lifetime = 85f;
                                    ammoMultiplier = 3;
                                    pierceCap = 3;
                                    pierce = true;
                                    pierceBuilding = true;
                                    buildingDamageMultiplier = 0.3f;
                                    trailWidth = 2.8f;
                                    trailLength = 14;
                                    backColor = Color.valueOf("ffa931");
                                    frontColor = trailColor = Color.valueOf("ffd9a5");
                                    trailEffect = Fx.hitSquaresColor;
                                    trailRotation = true;
                                    trailInterval = 6f;
                                    hitEffect = despawnEffect = new ExplosionEffect() {{
                                        lifetime = 34f;
                                        waveStroke = 2f;
                                        waveColor = sparkColor = trailColor;
                                        waveRad = 24f;
                                        smokeSize = 0f;
                                        smokeSizeBase = 0f;
                                        sparks = 5;
                                        sparkRad = 16f;
                                        sparkLen = 6f;
                                        sparkStroke = 2f;
                                }};

                                fragBullets = 6;
                                fragSpread = 40;
                                fragBullet = new BasicBulletType(4f, 15) {{
                                    sprite = "xenrose-star-bullet";
                                    width = 9f;
                                    height = 9f;
                                    lifetime = 20f;
                                    homingPower = 0.11f;
                                    homingRange = 65f;
                                    buildingDamageMultiplier = 0.3f;
                                    backColor = Color.valueOf("ffa931");
                                    frontColor = trailColor = Color.valueOf("ffd9a5");
                                    despawnEffect = hitEffect = new ParticleEffect() {{
                                        lifetime = 36f;
                                        colorFrom = Color.valueOf("ffd9a5");
                                        colorTo = Color.valueOf("ffa931");
                                        particles = 5;
                                        cone = 45;
                                        length = 15;
                                        baseLength = 2;
                                        spin = 0;
                                        sizeFrom = 2.5f;
                                        sizeTo = 0;
                                        offset = 1;
                                    }};
                                }};
                                }}
                            );
                    drawer = new DrawTurret() {{
                        new DrawAccelerationHeat("-accelheat");
                        parts.add(new RegionPart("-side") {{
                            progress = PartProgress.warmup;
                            moveY = -0.4f;
                            moveRot = -8f;
                            mirror = true;
                            under = true;
                            moves.add(new PartMove(PartProgress.recoil, 0, -0.2f, -0.9f));
                            researchCost = ItemStack.with(XenItems.damascus, 60, XenItems.zinc, 40);
                        }});
                    }};

                    outlineColor = Color.valueOf("211c18");
                    size = 2;
                    range = 247f;
                    reload = 60f;
                    consumeAmmoOnce = false;
                    recoil = 1.8f;
                    shake = 1f;
                    shoot.shots = 5;
                    shoot.shotDelay = 3f;
                    shootSound = Sounds.dullExplosion;
                    researchCost = ItemStack.with(XenItems.damascus, 150f, XenItems.zinc, 60);
                    coolant = consume(new ConsumeLiquid(XenLiquids.liquidOrinil, 15f / 60f));
                }};
                desiccation = new AccelerationTurret("desiccation"){{
                    requirements(Category.turret, with(XenItems.damascus, 50, XenItems.zinc, 20, XenItems.gold, 10));

                    ammo(
                            Items.sand, new ArtilleryBulletType(2.3f, 30, "xenrose-sand-bullet") {{
                                    splashDamage = 45;
                                    splashDamageRadius = 20;
                                    width = 11f;
                                    height = 11.2f;
                                    lifetime = 80f;
                                    ammoMultiplier = 2;
                                    trailWidth = 2f;
                                    trailLength = 4;
                                    buildingDamageMultiplier = 0.2f;
                                    backColor = Color.valueOf("d3ae8d");
                                    frontColor = trailColor = Color.valueOf("f7cba4");
                                    fragBullets = 4;
                                    fragSpread = 30;
                                    hitSound = Sounds.dullExplosion;
                                    fragBullet = new ArtilleryBulletType(1.3f, 10) {{
                                        sprite = "xenrose-sand-bullet";
                                        splashDamage = 10;
                                        splashDamageRadius = 10;
                                        width = 9f;
                                        height = 9.2f;
                                        lifetime = 20f;
                                        buildingDamageMultiplier = 0.3f;
                                        backColor = Color.valueOf("d3ae8d");
                                        frontColor = trailColor = Color.valueOf("f7cba4");
                                        hitSound = Sounds.dullExplosion;
                                        despawnEffect = hitEffect = new ParticleEffect() {{
                                            lifetime = 36f;
                                            colorFrom = Color.valueOf("d3ae8d");
                                            colorTo = Color.valueOf("f7cba4");
                                            particles = 5;
                                            cone = 45;
                                            length = 15;
                                            baseLength = 2;
                                            spin = 0;
                                            sizeFrom = 2.5f;
                                            sizeTo = 0;
                                            offset = 1;
                                        }};
                                    }};
                                    intervalBullet = new ArtilleryBulletType(1,20){{
                                        lifetime = 15;
                                        splashDamage = 25;
                                        splashDamageRadius = 15;
                                        collidesAir = false;
                                        ammoMultiplier = 1f;
                                        buildingDamageMultiplier = 0.3f;
                                        backColor = Color.valueOf("d3ae8d");
                                        frontColor = trailColor = Color.valueOf("f7cba4");
                                        hitSound = none;
                                        despawnEffect = hitEffect = new ParticleEffect() {{
                                                lifetime = 36f;
                                                colorFrom = Color.valueOf("d3ae8d");
                                                colorTo = Color.valueOf("f7cba4");
                                                particles = 1;
                                                cone = 45;
                                                length = 10;
                                                baseLength = 2;
                                                spin = 0;
                                                sizeFrom = 1.3f;
                                                sizeTo = 0;
                                                offset = 1;
                                            }};
                                    }};
                                    bulletInterval = 8;
                                    intervalBullets = 3;
                                    intervalAngle = 180f;
                                    intervalSpread = 300f;
                                    }
                            }
                    );
                    outlineColor = Color.valueOf("211c18");
                    targetAir = false;
                    size = 2;
                    range = 312f;
                    reload = 40f;
                    consumeAmmoOnce = false;
                    recoil = 1;
                    shake = 0.3f;
                    shootSound = Sounds.dullExplosion;
                    coolant = consume(new ConsumeLiquid(XenLiquids.liquidOrinil, 15f / 60f));
                    researchCost = ItemStack.with(XenItems.damascus, 900f, XenItems.zinc, 860, XenItems.gold, 550);
                }};
                calmness = new AccelerationTurret("calmness"){{
                        requirements(Category.turret, with(XenItems.damascus, 150, XenItems.zinc, 100, XenItems.gold, 80, XenItems.protexide, 50));
                        ammo(
                                XenItems.gold, new BasicBulletType(5.4f, 70, "xenrose-rhomb-bullet"){{
                                    width = 13f;
                                    height = 16f;
                                    lifetime = 80f;
                                    splashDamage = 50;
                                    splashDamageRadius = 30;
                                    ammoMultiplier = 2;
                                    trailWidth = 2.3f;
                                    trailLength = 10;
                                    pierce = true;
                                    pierceBuilding = true;
                                    pierceCap = 3;
                                    buildingDamageMultiplier = 0.2f;
                                    backColor = Color.valueOf("ce7e27");
                                    frontColor = trailColor = Color.valueOf("ffe0b5");
                                    homingPower = 0.17f;
                                    homingDelay = 20f;
                                    homingRange = 120f;
                                    trailEffect = XenFx.zanarTrail;
                                    trailRotation = true;
                                    trailInterval = 4f;
                                    hitEffect = despawnEffect = new MultiEffect(
                                            new WaveEffect() {{
                                                lifetime = 34f;
                                                colorFrom = Color.valueOf("ffe0b5");
                                                colorTo = Color.valueOf("d28111");
                                                sizeFrom = 32;
                                                sizeTo = 32;
                                                lightScl = 3;
                                                lightOpacity = 1;
                                                strokeFrom = 4f;
                                                strokeTo = 0;
                                            }},
                                            new ParticleEffect(){{
                                                colorFrom = Color.valueOf("ffe0b5");
                                                colorTo = Color.valueOf("d28111");
                                                particles = 15;
                                                cone = 360;
                                                baseLength = 40;
                                                lightScl = 3;
                                                lightOpacity = 2;
                                                spin = 0;
                                                sizeFrom = 3f;
                                                sizeTo = 0;
                                                offset = 1;
                                            }});
                                }},
                                XenItems.protexide, new BasicBulletType(5.4f, 130, "xenrose-rhomb-bullet"){{
                                    width = 14f;
                                    height = 18f;
                                    lifetime = 90f;
                                    splashDamage = 80;
                                    splashDamageRadius = 35;
                                    ammoMultiplier = 2;
                                    trailWidth = 2.7f;
                                    trailLength = 12;
                                    pierce = true;
                                    pierceBuilding = true;
                                    pierceCap = 4;
                                    buildingDamageMultiplier = 0.1f;
                                    backColor = Color.valueOf("69549a");
                                    frontColor = trailColor = Color.valueOf("e2d6ff");
                                    homingPower = 0.17f;
                                    homingDelay = 13f;
                                    homingRange = 120f;
                                    hitEffect = despawnEffect = new MultiEffect(
                                            new WaveEffect() {{
                                                lifetime = 43f;
                                                colorFrom = Color.valueOf("e2d6ff");
                                                colorTo = Color.valueOf("69549a");
                                                sizeFrom = 39;
                                                sizeTo = 35;
                                                lightScl = 4;
                                                lightOpacity = 1;
                                                strokeFrom = 5.2f;
                                                strokeTo = 0;
                                            }},
                                            new ParticleEffect(){{
                                                colorFrom = Color.valueOf("e2d6ff");
                                                colorTo = Color.valueOf("69549a");
                                                particles = 20;
                                                cone = 360;
                                                baseLength = 50;
                                                lightScl = 3;
                                                lightOpacity = 2;
                                                spin = 0;
                                                sizeFrom = 4f;
                                                sizeTo = 0;
                                                offset = 1;
                                            }});
                                }});

                                    drawer = new DrawTurret() {{
                                        parts.add(new RegionPart("-wing") {{
                                                      progress = PartProgress.warmup;
                                                      moveRot = -3f;
                                                      mirror = true;
                                                      under = false;
                                                      moves.add(new PartMove(PartProgress.recoil, 0, 0.29f, -1.8f));
                                                  }},
                                                new RegionPart("-gun") {{
                                                    progress = PartProgress.warmup;
                                                    moveY = -0.4f;
                                                    mirror = true;
                                                    under = false;
                                                    moves.add(new PartMove(PartProgress.recoil, 0, -0.6f, 0f));
                                                }});
                                    }};

                                    outlineColor = Color.valueOf("211c18");
                                    size = 3;
                                    range = 423f;
                                    reload = 355f;
                                    consumeAmmoOnce = false;
                                    recoil = 1.8f;
                                    shake = 1f;
                                    shoot.shots = 12;
                                    shoot.shotDelay = 6f;
                                    shoot.firstShotDelay = 70;
                                    inaccuracy = 14;
                                    shootSound = Sounds.dullExplosion;
                                    consumePower(80f / 60f);
                                    researchCost = ItemStack.with(XenItems.damascus, 2890, XenItems.zinc, 2800, XenItems.gold, 2550, XenItems.protexide, 2350);
                                    coolant = consume(new ConsumeLiquid(XenLiquids.liquidOrinil, 15f / 60f));
                    }};
                merge = new ItemTurret("merge"){{
                    requirements(Category.turret, with(XenItems.damascus, 250, XenItems.zinc, 150, XenItems.protexide, 100, XenItems.gold, 80, Items.tungsten, 50, XenItems.diocasium, 15));
                    ammo(
                            XenItems.diocasium, new ArtilleryBulletType(6, 90){{
                                width = height = 23;
                                lifetime = 80;
                                pierce = true;
                                pierceBuilding = true;
                                pierceCap = 2;
                                frontColor = Color.valueOf("fbbb67");
                                backColor = trailColor = hitColor = Color.valueOf("e18d47");
                                trailLength = 17;
                                trailWidth = 4.6f;
                                buildingDamageMultiplier = 0.25f;
                                homingPower = 0.17f;
                                homingDelay = 20f;
                                homingRange = 100f;
                                trailEffect = Fx.artilleryTrailSmoke;
                                trailRotation = true;
                                trailInterval = 8f;
                                despawnShake = 2;
                                splashDamage = 80;
                                splashDamageRadius = 35;

                                despawnEffect = new MultiEffect(
                                    new ExplosionEffect(){{
                                    lifetime = 130;
                                    waveStroke = 9;
                                    waveLife = 16;
                                    waveColor = Color.valueOf("ffd8a6");
                                    sparkColor = Color.valueOf("f0b467");
                                    waveRad = 57;
                                    smokeSize = 9;
                                    smokes = 9;
                                    smokeRad = 55;
                                    smokeColor = Color.valueOf("f0b467");
                                    smokeSizeBase = 0;
                                    sparks = 12;
                                    sparkRad = 60;
                                    sparkLen = 8;
                                    sparkStroke = 2f;
                                }});

                                smokeEffect = Fx.shootSmokeTitan;

                                fragBullets = 1;
                                fragSpread = 0;
                                fragRandomSpread = 0f;
                                fragBullet = new ArtilleryBulletType(7, 90){{
                                        width = height = 23;
                                        lifetime = 50;
                                        pierce = true;
                                        pierceBuilding = true;
                                        pierceCap = 2;
                                        pierceArmor = true;
                                        frontColor = Color.valueOf("fbbb67");
                                        backColor = trailColor = hitColor = Color.valueOf("e18d47");
                                        trailLength = 17;
                                        trailWidth = 4.6f;
                                        buildingDamageMultiplier = 0.25f;
                                        homingPower = 0.17f;
                                        homingDelay = 20f;
                                        homingRange = 100f;
                                        trailEffect = Fx.artilleryTrailSmoke;
                                        trailRotation = true;
                                        trailInterval = 8f;
                                        despawnShake = 2;
                                        splashDamage = 110;
                                        splashDamageRadius = 35;

                                        despawnEffect = new MultiEffect(
                                                new ExplosionEffect() {{
                                                    lifetime = 130;
                                                    waveStroke = 9;
                                                    waveLife = 16;
                                                    waveColor = Color.valueOf("ffd8a6");
                                                    sparkColor = Color.valueOf("f0b467");
                                                    waveRad = 57;
                                                    smokeSize = 9;
                                                    smokes = 9;
                                                    smokeRad = 55;
                                                    smokeColor = Color.valueOf("f0b467");
                                                    smokeSizeBase = 0;
                                                    sparks = 12;
                                                    sparkRad = 60;
                                                    sparkLen = 8;
                                                    sparkStroke = 2f;
                                                }});

                                        smokeEffect = Fx.shootSmokeTitan;

                                        fragBullets = 1;
                                        fragSpread = 0;
                                        fragRandomSpread = 50f;
                                        fragBullet = new LaserBulletType(80f) {{
                                            colors = new Color[]{Color.valueOf("e9bd85").cpy().a(0.4f), Color.valueOf("fbbb67"), Color.white};
                                            buildingDamageMultiplier = 0.25f;
                                            width = 30f;
                                            hitEffect = Fx.hitLancer;
                                            sideAngle = 175f;
                                            sideWidth = 1f;
                                            sideLength = 40f;
                                            lifetime = 110f;
                                            drawSize = 400f;
                                            length = 130f;
                                            pierceArmor = true;
                                            pierceCap = 2;
                                        }};
                                    }};
                            }});

                    drawer = new DrawTurret() {{
                        new DrawAccelerationHeat("-accelheat");
                        parts.add(new RegionPart("-side") {{
                            progress = PartProgress.warmup;
                            moveY = -0.8f;
                            moveRot = -2f;
                            mirror = true;
                            under = false;
                            moves.add(new PartMove(PartProgress.recoil, 0, -0.4f, -0.7f));
                            researchCost = ItemStack.with(XenItems.damascus, 60, XenItems.zinc, 40);
                        }},
                                new ShapePart(){{
                                    progress = PartProgress.warmup.blend(PartProgress.reload, 1.2f);
                                    color = Color.valueOf("fbbb67");
                                    circle = false;
                                    hollow = true;
                                    stroke = 1f;
                                    strokeTo = 1.5f;
                                    radius = 5f;
                                    sides = 8;
                                    layer = Layer.effect;
                                    y = -4;
                                    rotateSpeed = 1;
                                }},
                                new HaloPart(){{
                                    color = Color.valueOf("fbbb67");
                                    layer = Layer.effect;
                                    y = -1;
                                    haloRotation = 90f;
                                    shapes = 4;
                                    triLength = 0f;
                                    triLengthTo = 10f;
                                    haloRadius = 8f;
                                    tri = true;
                                    radius = 4f;
                                    haloRotateSpeed = 1;
                                    y = -4;
                                }},
                                new HaloPart(){{
                                    color = Color.valueOf("fbbb67");;
                                    layer = Layer.effect;
                                    y = -1;
                                    haloRotation = 90f;
                                    shapes = 4;
                                    triLength = 0f;
                                    triLengthTo = 5f;
                                    haloRadius = 8f;
                                    tri = true;
                                    radius = 4f;
                                    shapeRotation = 180f;
                                    haloRotateSpeed = 1;
                                    y = -4;
                                }});
                        parts.add(new ShapePart(){{
                            color = Color.valueOf("fbbb67");
                            circle = false;
                            hollow = true;
                            stroke = 1.8f;
                            radius = 0.8f;
                            sides = 9;
                            layer = Layer.effect;
                            y = -4;
                            rotateSpeed = -1;
                        }});
                    }};

                    outlineColor = Color.valueOf("211c18");
                    size = 3;
                    range = 386f;
                    reload = 320f;
                    consumeAmmoOnce = false;
                    consumePower(220f / 60f);
                    consumeLiquid(Liquids.hydrogen, 6f / 60f);
                    shootY = 1;
                    recoil = 1.5f;
                    shake = 1.4f;
                    shoot.shots = 3;
                    shoot.firstShotDelay = 50;
                    inaccuracy = 18;
                    shootSound = Sounds.dullExplosion;
                    researchCost = ItemStack.with(XenItems.damascus, 4600f, XenItems.zinc, 4550, XenItems.protexide, 4050, XenItems.gold, 3650, XenItems.diocasium, 1200);
                }};

                //storage
                coreSunrise = new CoreBlock("core-sunrise") {{
                    requirements(Category.effect, BuildVisibility.shown, with(XenItems.damascus, 950, XenItems.zinc, 670, XenItems.gold, 350));
                    alwaysUnlocked = true;

                    isFirstTier = true;
                    unitType = XenUnits.spraying;
                    health = 3240;
                    itemCapacity = 5000;
                    size = 3;
                    squareSprite = false;

                    buildCostMultiplier = 1.5f;

                    unitCapModifier = 8;
                }};
                coreZenith = new CoreBlock("core-zenith"){{
                    requirements(Category.effect, BuildVisibility.shown, with(XenItems.damascus, 950, XenItems.zinc, 670, XenItems.gold, 350));

                    unitType = XenUnits.spread;
                    health = 6930;
                    itemCapacity = 9500;
                    size = 4;
                    squareSprite = false;

                    buildCostMultiplier = 1.5f;

                    unitCapModifier = 18;
                }};
            }
        }
    }
}