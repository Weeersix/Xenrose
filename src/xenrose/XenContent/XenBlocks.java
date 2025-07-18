package xenrose.XenContent;

import arc.graphics.Blending;
import arc.graphics.Color;
import arc.math.Interp;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.LightningBulletType;
import mindustry.entities.effect.*;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.ShieldWall;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.DuctBridge;
import mindustry.world.blocks.distribution.DuctRouter;
import mindustry.world.blocks.distribution.Junction;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.liquid.LiquidJunction;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.blocks.payloads.PayloadConveyor;
import mindustry.world.blocks.payloads.PayloadRouter;
import mindustry.world.blocks.power.BeamNode;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Pump;
import mindustry.world.blocks.production.Separator;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.consumers.ConsumeItemCharged;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.BuildVisibility;
import xenrose.XenAttributes;
import xenrose.graphics.Rotor;
import xenrose.world.blocks.defense.Armor;
import xenrose.world.blocks.defense.ArmoredWall;
import xenrose.world.blocks.defense.turret.AccelerationTurret;
import xenrose.world.blocks.distribution.ShadedConveyor;
import xenrose.world.blocks.enviroments.EffectFloor;
import xenrose.world.blocks.liquid.*;
import xenrose.world.blocks.power.BetterConsumeGenerator;
import xenrose.world.blocks.production.MechanicalDrill;
import xenrose.world.blocks.storage.RecoveringCore;
import xenrose.world.blocks.units.StatusBlock;
import xenrose.world.draw.DrawAccelerationHeat;
import xenrose.world.draw.DrawRotor;

import static arc.math.Interp.fastSlow;
import static arc.math.Interp.pow3Out;
import static mindustry.type.ItemStack.with;

public class XenBlocks {
    public static Block
    //environments
    damascusWall, crackedDamascusWall, unevenDamascusWall, kirmiteStoneWall, oxidizedWall, burnedDamscusWall, graphiticStoneWall, burntDamascusWall, burnedKirmiteStoneWall, orinilWall, peatWall,
    damascusStoneTree, damascusStoneTreeMin, damascusBoulder, damascusCluster, kirmiteStoneTree, kirmiteCrystalOrbs, kirmiteStoneBoulder, kirmiteLargeBoulder, kirmiteTree, oxidizedCluster, oxidizedBoulder, graphiticStoneBoulder, burnedDamscusBoulder, burnedDamscusTree, burnedDamscusLargeBoulder, orinilBoulder, orinilCrystal, peatBoulder,
    softDamascusGround, damascusGround, crackedDamascusGround, deepenedDamascusFloor,  kirmiteStoneFloor, kirmiteStoneGround, solidifiedKirmiteLiquid, kirmite, orinil, deepKirnite, oxidizedFloor, oxidizedGround, burnedDamscusFloor, burnedDamscusGround, graphiticStoneFloor, burntDamascusFloor, burnedKirmiteStoneFloor, orinilGround, orinilFloor, peatFloor,
    zinkWallOre, protexideWallOre,
    //distribution
    damascusConveyor, dantstalinConveyor, damascusJunction, damascusRouter, damascusBridge,
    //liquid
    hydraulicPump, energyPump, fragilePipeline, hardenedPipeline, fragileLiquidRouter, hardenedPipelineRouter, fragileLiquidBridge, hardenedPipelineBridge, reinforcedPipelineJunction,
    //drills
    energyDrill, energyChargedDrill, airMechanicalDrill,
    //production
    pyrometallurgicalInstallation, crusher, waterReformer, siliconCentrifuge, liquidSeparator, dantstalinSmelter, waterCollerctor, orinilCrucible, energyStabilizingBoiler,energyChargingSplitter,
    //power
    kirmiteEvaporator, cableNode,
    //walls
    reinforcedDamascusWall, reinforcedDamascusWallLarge, diocasiumArmor, enemyShieldWall, enemyShieldWallLarge,
    //units
    groundUnitsAssembler, hoverUnitsAssembler, floatingUnitsAssembler, orinilReassembler, thermalReassemblingFactory, shieldTower,
    //payload
    cargoBelt, cargoRouter,
    //turrets
    samum, desiccation, overflow, tributary, merge, calmness,
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
                    effectChance = 0.0001f;
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
                    effectChance = 0.0001f;
                    effectColor = Color.valueOf("9265bd");
                    isLiquid = true;
                    cacheLayer = CacheLayer.water;
                    drownTime = 350;
                    albedo = 0.9f;
                    supportsOverlay = true;
                }};
                orinil = new EffectFloor("orinil"){{
                    speedMultiplier = 0.18f;
                    variants = 2;
                    liquidDrop = XenLiquids.liquidOrinil;
                    status = StatusEffects.melting;
                    statusDuration = 390f;
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
                dantstalinConveyor = new ShadedConveyor("dantstalin-conveyor"){{
                    requirements(Category.distribution, with(XenItems.damascus, 2, XenItems.dantstalin, 1));
                    health = 360;
                    speed = 0.12f;
                    displayedSpeed = 6.5f;
                    buildCostMultiplier = 2f;
                    itemCapacity = 3;
                    bridgeReplacement = damascusBridge;
                    junctionReplacement = damascusJunction;

                    hasPower = true;
                    consumesPower = true;
                    conductivePower = true;
                    consumePower(0.25f / 60f);

                    underBullets = true;
                    researchCost = with(XenItems.damascus, 840, XenItems.dantstalin, 350);
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
                ((Conveyor)dantstalinConveyor).junctionReplacement = damascusJunction;
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
                hydraulicPump = new BreackablePump("hydraulic-pump"){{
                    requirements(Category.liquid, with(XenItems.damascus, 50, XenItems.zinc, 20, XenItems.gold, 10));

                    squareSprite = false;
                    pumpAmount = 20f / 60f / 4f;
                    liquidCapacity = 80f;
                    size = 2;

                    researchCost = with(XenItems.damascus, 250, XenItems.zinc, 170, XenItems.gold, 50);
                }};
                energyPump = new Pump("energy-pump"){{
                    requirements(Category.liquid, with(XenItems.damascus, 80, XenItems.zinc, 50, XenItems.dantstalin, 20));

                    squareSprite = false;
                    pumpAmount = 45f / 60f / 4f;
                    liquidCapacity = 150f;
                    size = 3;
                    consumePower(100f / 60f);
                    consumeLiquid(Liquids.hydrogen, 3f / 60f);

                    researchCost = with(XenItems.damascus, 3950, XenItems.zinc, 3290, XenItems.dantstalin, 1420);
                }};
                fragilePipeline = new BreakableConduit("fragile-pipeline"){{
                    requirements(Category.liquid, with(XenItems.damascus, 3));

                    health = 55;
                    liquidCapacity = 10;
                    liquidLimit = 5;
                    botColor = Color.valueOf("1b1915");

                    researchCost = with(XenItems.damascus, 50);
                }};
                hardenedPipeline = new ShadedConduit("hardened-pipeline"){{
                    requirements(Category.liquid, with(XenItems.damascus, 2, XenItems.dantstalin, 1));

                    liquidCapacity = 18f;
                    health = 125;
                    botColor = Color.valueOf("1e1c1b");

                    researchCost = with(XenItems.damascus, 1830, XenItems.dantstalin, 850);
                }};
                fragileLiquidRouter = new BreakableRouter("fragile-liquid-router"){{
                    requirements(Category.liquid, with(XenItems.damascus, 8));

                    health = 90;
                    liquidCapacity = 15;
                    liquidLimit = 10;

                    researchCost = with(XenItems.damascus, 110);
                }};
                hardenedPipelineRouter = new LiquidRouter("hardened-pipeline-router"){{
                    requirements(Category.liquid, with(XenItems.damascus, 4, XenItems.dantstalin, 2));

                    health = 140;
                    liquidCapacity = 25;

                    researchCost = with(XenItems.damascus, 1540, XenItems.dantstalin, 620);
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
                hardenedPipelineBridge = new LiquidBridge("hardened-pipeline-bridge"){{
                    requirements(Category.liquid, with(XenItems.damascus, 6, XenItems.dantstalin, 2));

                    health = 140;
                    fadeIn = moveArrows = false;
                    arrowSpacing = 6f;
                    range = 7;
                    hasPower = false;
                    liquidCapacity = 40f;

                    researchCost = with(XenItems.damascus, 1850, XenItems.dantstalin, 680);
                }};
                ((Conduit)hardenedPipeline).bridgeReplacement = hardenedPipelineBridge;
                reinforcedPipelineJunction =  new LiquidJunction("reinforced-pipeline-junction"){{
                    requirements(Category.liquid, with(XenItems.damascus, 6, XenItems.dantstalin, 2));
                    researchCost = with(XenItems.damascus, 650, XenItems.dantstalin, 550);
                }};
                ((Conduit) fragilePipeline).junctionReplacement = reinforcedPipelineJunction;
                ((Conduit) hardenedPipeline).junctionReplacement = reinforcedPipelineJunction;

                //drills
                energyDrill = new MechanicalDrill("energy-drill") {{
                    requirements(Category.production, ItemStack.with(XenItems.damascus, 15));

                    drillTime = 390;
                    size = 2;
                    range = 6;
                    fogRadius = 2;
                    tier = 3;

                    drillEffect = new MultiEffect(
                            new ParticleEffect(){{
                                particles = 4;
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
                                particles = 10;
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
                                particles = 4;
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
                                particles = 3;
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
                    drillTime = 300;
                    size = 3;
                    consumeLiquid(XenLiquids.oxygen, 0.5f / 60f);
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
                        particles = 4;
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
                        particles = 3;
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
                    outputItem = new ItemStack(Items.sand, 3);
                    consumeItem(XenItems.damascus, 1);
                    hasItems = true;
                    squareSprite = false;
                    craftTime = 1.1f * 60f;
                    craftEffect = new RadialEffect(XenFx.crusherSmoke, 3, 90f, 0.8f);

                    drawer = new DrawMulti(new DrawRegion("-bottom"),
                            new DrawRegion("-rotator", 1.5f){{
                                spinSprite = true;
                            }},
                            new DrawDefault()
                            );

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
                    researchCost = with(XenItems.damascus, 1300, XenItems.zinc, 1240, XenItems.gold, 800);
                }};
                siliconCentrifuge = new GenericCrafter("silicon-centrifuge"){{
                    requirements(Category.crafting, ItemStack.with(XenItems.damascus, 100, XenItems.zinc, 80, XenItems.gold, 55));
                    size = 3;
                    squareSprite = false;
                    hasItems = true;
                    craftTime = 2f * 60f;
                    itemCapacity = 20;
                    liquidCapacity = 30f;
                    outputItem = new ItemStack(Items.silicon, 3);

                    drawer = new DrawMulti(
                            new DrawRegion("-bottom"),
                            new DrawRotor(
                                    new Rotor("-rotor"){{
                                        x = 0f;
                                        y = 0f;

                                        height = 3.5f;

                                        pixelHeight = 7;

                                        palLight = Color.valueOf("7c746b");
                                        palMedium = Color.valueOf("57534e");
                                        palDark = Color.valueOf("2f2d2a");
                                    }},
                                    new Rotor("-rotor"){{
                                        x = 0f;
                                        y = 0f;

                                        height = 3.5f;

                                        pixelHeight = 7;

                                        palLight = Color.valueOf("7c746b");
                                        palMedium = Color.valueOf("57534e");
                                        palDark = Color.valueOf("2f2d2a");

                                        icon = false;
                                    }}),
                            new DrawRegion()
                    );

                    consumePower(180f / 60f);
                    consumeLiquid(XenLiquids.liquidKirmit, 12f / 60f);
                    consumeItem(Items.sand, 2);
                    researchCost = with(XenItems.damascus, 2870, XenItems.zinc, 2670, XenItems.gold, 2140);

                }};
                liquidSeparator = new Separator("liquid-separator"){{
                    requirements(Category.crafting, ItemStack.with(XenItems.damascus, 60, XenItems.zinc, 35, XenItems.gold, 10));
                    size = 2;
                    squareSprite = false;
                    hasItems = true;
                    hasLiquids = true;
                    hasPower = true;
                    craftTime = 1.05f * 60f;
                    itemCapacity = 20;
                    liquidCapacity = 30;
                    results = with(
                            XenItems.damascus, 4,
                            XenItems.zinc, 3,
                            XenItems.gold, 1
                    );
                    drawer = new DrawMulti( new DrawRegion("-bottom"), new DrawLiquidTile(XenLiquids.liquidKirmit, 1.4f),
                            new DrawParticles() {{
                                particles = 10;
                                color = Color.valueOf("cb94ff");
                                blending = Blending.additive;
                                particleInterp = Interp.linear;
                                particleRad = 3f;
                                particleSize = 3f;
                            }},
                            new DrawRegion("-top", 1.3f){{
                                spinSprite = true;
                    }}, new DrawDefault());
                    consumePower(90f / 60f);
                    consumeLiquids(LiquidStack.with(XenLiquids.liquidKirmit, 10f / 60f, Liquids.hydrogen, 2f / 60f));
                    researchCost = with(XenItems.damascus, 2870, XenItems.zinc, 2670, XenItems.gold, 2140);
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
                                sizeFrom = 0;
                                sizeTo = 18;
                                strokeFrom = 2;
                                strokeTo = 0f;
                                lifetime = 20;
                                colorFrom = Color.valueOf("b8a0a0");
                                colorTo = Color.valueOf("bf6f56");
                            }},
                            new ParticleEffect(){{
                                particles = 10;
                                length = 28;
                                lifetime = 30;
                                sizeFrom = 4;
                                sizeTo = 0;
                                startDelay = 2;
                                interp = fastSlow;
                                colorFrom = Color.valueOf("b8a0a0");
                                colorTo = Color.valueOf("bf6f56");
                            }});

                    drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 1.2f){{
                        spinSprite = true;
                    }});

                    ambientSound = Sounds.electricHum;
                    ambientSoundVolume = 0.095f;
                    consumeItem(XenItems.zinc, 2);
                    consumeLiquids(LiquidStack.with(XenLiquids.liquidKirmit, 46f / 60f, XenLiquids.oxygen, 2f / 60f));
                    consumePower(290f / 60f);
                    researchCost = with(XenItems.damascus, 2110, XenItems.zinc, 1670, XenItems.gold, 1000);
                }};
                waterCollerctor = new GenericCrafter("water-collector"){{
                    requirements(Category.crafting, ItemStack.with(XenItems.damascus, 150, XenItems.zinc, 120, XenItems.dantstalin, 75, XenItems.gold, 50));
                    size = 4;
                    outputLiquid = new LiquidStack(Liquids.water, 18f / 60f);
                    liquidCapacity = 120;
                    squareSprite = false;
                    hasLiquids = true;
                    craftTime = 1.2f * 60f;
                    drawer = new DrawMulti(new DrawRegion("-mid"), new DrawLiquidTile(Liquids.water, 3.2f), new DrawRegion("-bottom"), new DrawDefault(), new DrawGlowRegion(),
                            new DrawParticles() {{
                                particles = 12;
                                color =  Color.valueOf("e0e4ffa0");
                                blending = Blending.additive;
                                particleInterp = Interp.linear;
                                particleRad = 16f;
                                particleSize = 2.5f;
                    }},
                            new DrawParticles() {{
                                particles = 10;
                                color =  Color.valueOf("b6bde99a");
                                blending = Blending.additive;
                                particleInterp = Interp.linear;
                                particleRad = 19f;
                                particleSize = 2.5f;
                            }},
                            new DrawParticles() {{
                                particles = 18;
                                color =  Color.valueOf("e0e4ffa0");
                                blending = Blending.additive;
                                particleInterp = Interp.linear;
                                particleRad = 21f;
                                particleSize = 2.5f;
                    }});

                    consumePower(250f / 60f);
                    fogRadius = 4;
                    researchCost = with(XenItems.damascus, 3950, XenItems.zinc, 3670, XenItems.dantstalin, 1630, XenItems.gold, 2740);
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
                                particles = 4;
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
                                particles = 5;
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
                cableNode = new BeamNode("cable-node") {{
                    requirements(Category.power, with(XenItems.zinc, 5));

                    size = 1;
                    range = 7;
                    researchCost = with(XenItems.zinc, 20);
                }};

                //walls
                reinforcedDamascusWall = new ArmoredWall("reinforced-damascus-wall") {{
                    researchCost = ItemStack.with(XenItems.damascus, 80f, XenItems.zinc, 20);
                    requirements(Category.defense, ItemStack.with(XenItems.damascus, 6f, XenItems.zinc, 3));
                    health = 310;
                    armor = 2;
                    shieldHealth = 1200;
                    floating = true;
                }};
                reinforcedDamascusWallLarge = new ArmoredWall("reinforced-damascus-wall-large") {{
                    researchCost = ItemStack.with(XenItems.damascus, 250f, XenItems.zinc, 100);
                    requirements(Category.defense, ItemStack.with(XenItems.damascus, 6 * 4, XenItems.zinc, 3 * 4));
                    health = 310 * 4;
                    armor = 2;
                    size = 2;
                    shieldHealth = 1200;
                    floating = true;
                }};
                diocasiumArmor = new Armor("diocasium-armor"){{
                    researchCost = ItemStack.with(XenItems.damascus, 1f);
                }};
                enemyShieldWall = new ShieldWall("enemy-reinforced-damascus-wall"){{
                    requirements(Category.defense, ItemStack.with(XenItems.damascus, 6f, XenItems.zinc, 3));
                    health = 310;
                    armor = 2;
                    shieldHealth = 1200;
                    floating = true;
                }};
                enemyShieldWallLarge = new ShieldWall("enemy-reinforced-damascus-wall-large"){{
                    requirements(Category.defense, ItemStack.with(XenItems.damascus, 6 * 4, XenItems.zinc, 3 * 4));
                    health = 310 * 4;
                    armor = 2;
                    size = 2;
                    shieldHealth = 1200;
                    floating = true;
                }};

                //units
                groundUnitsAssembler = new UnitFactory("ground-units-assembler"){{
                    requirements(Category.units, with(XenItems.damascus, 150,XenItems.zinc, 120, XenItems.gold, 50));
                    size = 3;
                    configurable = false;
                    plans.add(new UnitPlan(XenUnits.zanar, 60f * 20f, with(XenItems.zinc, 35, XenItems.gold, 20)));
                    regionSuffix = "-dark";
                    fogRadius = 3;
                    consumePower(1.666666666666667f);
                    consumeLiquid(XenLiquids.liquidKirmit, 14f/60f);
                    researchCost = with(XenItems.damascus, 250,XenItems.zinc, 250, XenItems.gold, 150);
                }};
                hoverUnitsAssembler = new UnitFactory("hover-units-assembler"){{
                    requirements(Category.units, with(XenItems.damascus, 180,XenItems.zinc, 130, XenItems.gold, 60));
                    size = 3;
                    configurable = false;
                    plans.add(new UnitPlan(XenUnits.imitation, 60f * 17f, with(XenItems.zinc, 40, XenItems.gold, 15)));
                    regionSuffix = "-dark";
                    fogRadius = 3;
                    consumePower(1.666666666666667f);
                    consumeLiquid(XenLiquids.liquidKirmit, 20f/60f);
                    researchCost = with(XenItems.damascus, 1650, XenItems.zinc, 1460, XenItems.gold, 1100);
                }};
                floatingUnitsAssembler = new UnitFactory("floating-units-assembler"){{
                    requirements(Category.units, with(XenItems.damascus, 200, XenItems.zinc, 120, XenItems.gold, 50));
                    size = 3;
                    configurable = false;
                    plans.add(new UnitPlan(XenUnits.xanit, 60f * 20f, with(XenItems.zinc, 45, XenItems.gold, 25)));
                    regionSuffix = "-dark";
                    fogRadius = 3;
                    consumePower(1.666666666666667f);
                    consumeLiquid(XenLiquids.liquidKirmit, 30f/60f);
                    researchCost = with(XenItems.damascus, 3860, XenItems.zinc, 3550, XenItems.gold, 2990);
                }};
                orinilReassembler = new Reconstructor("orinil-reassembler"){{
                    requirements(Category.units, with(XenItems.damascus, 340, XenItems.zinc, 250, XenItems.dantstalin, 70, XenItems.gold, 50));
                    size = 4;
                    consumePower(380f / 60f);
                    consumeItems(with(XenItems.gold, 50, XenItems.dantstalin, 30));
                    consumeLiquids(LiquidStack.with(XenLiquids.liquidOrinil, 20f / 60f, XenLiquids.oxygen, 5f / 60f));

                    upgrades.addAll(
                            new UnitType[]{XenUnits.zanar, XenUnits.inorn},
                            new UnitType[]{XenUnits.imitation, XenUnits.simulation},
                            new UnitType[]{XenUnits.xanit, XenUnits.manul}
                    );

                    constructTime = 28f * 60f;
                    researchCost = with(XenItems.damascus, 4920, XenItems.zinc, 3560, XenItems.gold, 1890, XenItems.dantstalin, 200);
                }};
                thermalReassemblingFactory = new Reconstructor("thermal-reassembling-factory"){{
                    requirements(Category.units, with(XenItems.damascus, 500, XenItems.zinc, 320, XenItems.dantstalin, 240, XenItems.protexide, 100, XenItems.gold, 50));
                    size = 5;
                    consumePower(660f / 60f);
                    consumeItems(with(XenItems.gold, 85, XenItems.dantstalin, 40, XenItems.protexide, 25, XenItems.isoteron, 5));
                    consumeLiquids(LiquidStack.with(XenLiquids.liquidOrinil, 90f / 60f, Liquids.hydrogen, 25f / 60f));

                    upgrades.addAll(
                            new UnitType[]{XenUnits.inorn, XenUnits.manler},
                            new UnitType[]{XenUnits.simulation, XenUnits.fusion},
                            new UnitType[]{XenUnits.manul, XenUnits.amiren}
                    );

                    constructTime = 65f * 60f;
                    researchCost = with(XenItems.damascus, 7740, XenItems.zinc, 7210, XenItems.dantstalin, 6320, XenItems.protexide, 4200, XenItems.gold, 4086);
                }};
                shieldTower = new StatusBlock("shield-tower"){{
                    requirements(Category.units, with(XenItems.damascus, 80, XenItems.zinc, 50, XenItems.dantstalin, 20));

                    size = 2;
                    squareSprite = false;
                    status = StatusEffects.shielded;
                    statusDuration = 2100;
                    researchCost = ItemStack.with(XenItems.damascus, 2530, XenItems.zinc, 2130, XenItems.dantstalin, 1437);
                }};

                //payload
                cargoBelt = new PayloadConveyor("cargo-belt"){{
                    requirements(Category.units, with(XenItems.damascus, 30, XenItems.zinc, 20));
                    canOverdrive = false;
                    underBullets = true;
                    health = 420;
                    researchCost = ItemStack.with(XenItems.damascus, 1670f, XenItems.zinc, 1520);
                }};
                cargoRouter = new PayloadRouter("cargo-router"){{
                    requirements(Category.units, with(XenItems.damascus, 40, XenItems.zinc, 15));
                    canOverdrive = false;
                    health = 500;
                    researchCost = ItemStack.with(XenItems.damascus, 1600, XenItems.zinc, 1220);
                }};

                //turrets
                samum = new AccelerationTurret("samum") {{
                    requirements(Category.turret, with(XenItems.damascus, 45, XenItems.zinc, 20));

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

                    squareSprite = false;
                    floating = true;
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
                    researchCost = ItemStack.with(XenItems.damascus, 50f, XenItems.zinc, 25);
                    coolant = consume(new ConsumeLiquid(XenLiquids.liquidOrinil, 15f / 60f));
                }};
                desiccation = new AccelerationTurret("desiccation"){{
                    requirements(Category.turret, with(XenItems.damascus, 50, XenItems.zinc, 20, XenItems.gold, 10));

                    ammo(
                            Items.sand, new ArtilleryBulletType(2.3f, 22, "xenrose-sand-bullet") {{
                                    splashDamage = 50;
                                    splashDamageRadius = 20;
                                    width = 11f;
                                    height = 11.2f;
                                    lifetime = 80f;
                                    ammoMultiplier = 2;
                                    trailWidth = 2f;
                                    trailLength = 6;
                                    buildingDamageMultiplier = 0.2f;
                                    backColor = Color.valueOf("d3ae8d");
                                    frontColor = trailColor = Color.valueOf("f7cba4");
                                    fragBullets = 2;
                                    fragSpread = 30;
                                    hitSound = Sounds.dullExplosion;
                                    trailEffect = hitEffect = despawnEffect = new ParticleEffect(){{
                                        colorFrom = Color.valueOf("ddba8c");
                                        colorTo = Color.valueOf("b48758");
                                        particles = 1;
                                        sizeFrom = 2;
                                        sizeTo = 0;
                                    }};
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
                            }
                            }
                    );
                    shoot = new ShootSpread(5, 10);

                    squareSprite = false;
                    floating = true;
                    outlineColor = Color.valueOf("211c18");
                    targetAir = false;
                    size = 2;
                    range = 312f;
                    reload = 40f;
                    ammoPerShot = 2;
                    consumeAmmoOnce = false;
                    recoil = 1;
                    shake = 0.3f;
                    shootSound = Sounds.dullExplosion;
                    coolant = consume(new ConsumeLiquid(XenLiquids.liquidOrinil, 15f / 60f));
                    researchCost = ItemStack.with(XenItems.damascus, 900f, XenItems.zinc, 860, XenItems.gold, 550);
                }};
                overflow = new PowerTurret("overflow"){{
                    requirements(Category.turret, with(XenItems.damascus, 90, XenItems.zinc, 75, XenItems.gold, 40));

                    shootType = new BasicBulletType(8.6f,30, "xenrose-basic-bullet1"){{
                            width = 9;
                            height = 11;
                            lifetime = 40;
                            trailWidth = 2.7f;
                            trailLength = 14;
                            frontColor = trailColor = Color.valueOf("d4fcf4");
                            backColor = hitColor = Color.valueOf("5b898c");
                            pierceArmor = true;
                            buildingDamageMultiplier = 0.15f;
                            fragBullets = 5;
                            fragBullet = new LightningBulletType(){{
                                damage = 20;
                                lightningColor = Color.valueOf("d4e6e3");
                                lightningLength = 13;
                                lightningType = new BulletType(0.0001f, 1f) {{
                                    lifetime = Fx.lightning.lifetime;
                                    hitEffect = despawnEffect = Fx.none;
                                    hittable = false;
                                    lightColor = Color.valueOf("d4e6e3");
                                    collidesAir = false;
                                    buildingDamageMultiplier = 0.25f;
                                }};
                            }};
                        }};
                    squareSprite = false;
                    shootEffect = Fx.none;
                    floating = true;
                    outlineColor = Color.valueOf("211c18");
                    size = 2;
                    range = 253;
                    reload = 75;
                    rotateSpeed = 1.9f;
                    targetAir = false;
                    consumePower(150f / 60f);
                    shootY = 0f;
                    recoil = 1.45f;
                    shootSound = Sounds.spark;
                    researchCost = ItemStack.with(XenItems.damascus, 2310, XenItems.zinc, 2100, XenItems.gold, 1260);
                }};
                tributary = new LiquidTurret("tributary"){{
                    requirements(Category.turret, with(XenItems.damascus, 80, XenItems.zinc, 50, XenItems.dantstalin, 30, XenItems.gold, 20));
                    ammo(
                            Liquids.hydrogen, new BasicBulletType(5,8){{
                                width = 18;
                                height = 14;
                                lifetime = 40f;
                                drag = 0.03f;
                                trailInterval = 4f;
                                knockback = 1.5f;
                                buildingDamageMultiplier = 0.15f;
                                trailEffect = hitEffect = despawnEffect = new ParticleEffect(){{
                                    colorFrom = Color.valueOf("d7f7ff");
                                    colorTo = Color.valueOf("7797ab");
                                    particles = 2;
                                    sizeFrom = 4;
                                    sizeTo = 0;
                                }};
                            }},
                            XenLiquids.oxygen, new BasicBulletType(5,11){{
                                width = 18;
                                height = 14;
                                lifetime = 40f;
                                drag = 0.03f;
                                trailInterval = 4f;
                                status = StatusEffects.burning;
                                statusDuration = 14 * 60;
                                knockback = 1.5f;
                                buildingDamageMultiplier = 0.15f;
                                trailEffect = hitEffect = despawnEffect = new ParticleEffect(){{
                                    colorFrom = Color.valueOf("e3e3e3");
                                    colorTo = Color.valueOf("ababab");
                                    particles = 2;
                                    sizeFrom = 4;
                                    sizeTo = 0;
                                }};
                            }},
                            XenLiquids.liquidOrinil, new BasicBulletType(6.8f,19,"xenrose-basic-bullet1"){{
                                width = 10;
                                height = 16;
                                lifetime = 80f;
                                drag = 0.03f;
                                trailInterval = 6f;
                                backColor = Color.valueOf("e18d47");
                                frontColor = trailColor = Color.valueOf("ffd297");
                                trailWidth = 3;
                                trailLength = 8;
                                status = StatusEffects.burning;
                                statusDuration = 15 * 60;
                                knockback = 2f;
                                pierce = true;
                                pierceBuilding = true;
                                pierceCap = 2;
                                buildingDamageMultiplier = 0.15f;
                                trailEffect = hitEffect = despawnEffect = new ParticleEffect(){{
                                    colorFrom = Color.valueOf("ffd297");
                                    colorTo = Color.valueOf("e18d47");
                                    particles = 1;
                                    sizeFrom = 3.5f;
                                    sizeTo = 0;
                                }};
                            }});
                    shoot = new ShootSpread(11,6);

                    squareSprite = false;
                    floating = true;
                    outlineColor = Color.valueOf("211c18");
                    size = 2;
                    range = 116f;
                    reload = 40;
                    rotateSpeed = 2.3f;
                    shootY = 19f / 4f;
                    recoil = 1.2f;
                    shootSound = Sounds.dullExplosion;
                    researchCost = ItemStack.with(XenItems.damascus, 4760, XenItems.zinc, 4360, XenItems.dantstalin, 2900, XenItems.gold, 2750);

                }};
                merge = new AccelerationTurret("merge"){{
                    requirements(Category.turret, with(XenItems.damascus, 85, XenItems.zinc, 60, XenItems.dantstalin, 45, XenItems.gold, 20));
                    ammo(
                            XenItems.damascus, new BasicBulletType(4.8f, 45, "xenrose-basic-bullet1"){{
                                width = 13;
                                height = 15;
                                lifetime = 74;
                                trailWidth = 3f;
                                trailLength = 5;
                                buildingDamageMultiplier = 0.3f;
                                backColor = Color.valueOf("807569");
                                frontColor = trailColor = Color.valueOf("d6c6b4");
                                trailEffect = Fx.hitSquaresColor;
                                trailParam = 3;
                                trailInterval = 8f;
                                trailEffect = new ParticleEffect(){{
                                    colorFrom = Color.valueOf("d6c6b4");
                                    colorTo = Color.valueOf("807569");
                                    particles = 1;
                                    sizeFrom = 2;
                                    sizeTo = 0;
                                }};
                                hitEffect = despawnEffect = new ExplosionEffect() {{
                                    lifetime = 34f;
                                    waveStroke = 2f;
                                    waveColor = sparkColor = trailColor;
                                    waveRad = 24f;
                                    smokeSize = 3f;
                                    smokeSizeBase = 1f;
                                    sparks = 5;
                                    sparkRad = 16f;
                                    sparkLen = 6f;
                                    sparkStroke = 2f;
                                }};
                            }});

                    drawer = new DrawTurret() {{
                        parts.add(new RegionPart("-side") {{
                            progress = PartProgress.warmup;
                            moveY = -0.4f;
                            moveX = -0.35f;
                            moveRot = -4f;
                            mirror = true;
                            under = false;
                            moves.add(new PartMove(PartProgress.recoil, 0, 0, -0.7f));
                        }});
                    }};

                    squareSprite = false;
                    floating = true;
                    outlineColor = Color.valueOf("211c18");
                    size = 2;
                    range = 294f;
                    reload = 60;
                    rotateSpeed = 2.3f;
                    ammoPerShot = 6;
                    consumeAmmoOnce = false;
                    targetGround = false;
                    consumePower(140f / 60f);
                    shootY = 22f / 4f;
                    recoil = 1.45f;
                    shoot.shots = 15;
                    shoot.firstShotDelay = 20;
                    inaccuracy = 19;
                    shootSound = Sounds.dullExplosion;
                    researchCost = ItemStack.with(XenItems.damascus, 4760, XenItems.zinc, 4360, XenItems.dantstalin, 2900, XenItems.gold, 2750);
                    coolant = consume(new ConsumeLiquid(XenLiquids.liquidOrinil, 12f / 60f));
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
                                    shoot.shots = 12;
                                    shoot.shotDelay = 6f;
                                    shoot.firstShotDelay = 70;
                                    inaccuracy = 14;
                                    
                                    squareSprite = false;
                                    floating = true;
                                    outlineColor = Color.valueOf("211c18");
                                    size = 3;
                                    range = 423f;
                                    reload = 355f;
                                    ammoPerShot = 5;
                                    consumeAmmoOnce = false;
                                    recoil = 1.8f;
                                    shake = 1f;
                                    shootSound = Sounds.dullExplosion;
                                    consumePower(80f / 60f);
                                    researchCost = ItemStack.with(XenItems.damascus, 2890, XenItems.zinc, 2800, XenItems.gold, 2550, XenItems.protexide, 2350);
                                    coolant = consume(new ConsumeLiquid(XenLiquids.liquidOrinil, 15f / 60f));
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
                coreZenith = new RecoveringCore("core-zenith"){{
                    requirements(Category.effect, BuildVisibility.shown, with(XenItems.damascus, 1250, XenItems.zinc, 1100, XenItems.gold, 950, XenItems.dantstalin, 550));

                    unitType = XenUnits.spread;
                    health = 6930;
                    itemCapacity = 9500;
                    size = 4;
                    squareSprite = false;

                    buildCostMultiplier = 1.5f;

                    unitCapModifier = 18;
                    researchCost = ItemStack.with(XenItems.damascus, 3850, XenItems.zinc, 3580, XenItems.gold, 2160, XenItems.dantstalin, 1420);
                }};
            }
        }
    }
}