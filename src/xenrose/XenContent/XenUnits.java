package xenrose.XenContent;

import arc.graphics.Color;
import arc.math.Interp;
import arc.math.Mathf;
import arc.struct.Seq;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.*;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.entities.TargetPriority;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.abilities.ShieldArcAbility;
import mindustry.entities.abilities.ShieldRegenFieldAbility;
import mindustry.entities.abilities.SpawnDeathAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.part.*;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootHelix;
import mindustry.entities.pattern.ShootSine;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.weapons.RepairBeamWeapon;
import mindustry.world.meta.Env;
import xenrose.type.XenUnitType;

import static mindustry.Vars.tilesize;
import static mindustry.gen.Nulls.unit;


public class XenUnits {
    public static UnitType
            //core
            spraying, spread,
            //zanar tech tree
            zanar, inorn, manler, inzeran,
            //imitation tech tree
            imitation, simulation, fusion;

    public static void load() {
             spraying = new UnitType("spraying"){{
                    coreUnitDock = true;
                    controller = u -> new BuilderAI(true, 500f);
                    constructor = UnitEntity::create;
                    outlineColor = Color.valueOf("211c18");
                    envDisabled = 0;
                    health = 240;
                    hitSize = 10f;

                    faceTarget = true;
                    targetPriority = -2;
                    lowAltitude = false;
                    pickupUnits = false;
                    flying = true;
                    mineSpeed = 8f;
                    mineTier = 4;
                    buildSpeed = 0.9f;
                    drag = 0.085f;
                    speed = 3.8f;
                    rotateSpeed = 15f;
                    accel = 0.7f;
                    itemCapacity = 20;
                    useEngineElevation = false;
                    engineSize = 3f;
                    engineOffset = 20 / 4f;
                    fogRadius = 0;

                    alwaysUnlocked = true;
                    mineWalls = true;
                    mineFloor = true;

                    weapons.add(new Weapon(){{
                        shootSound = Sounds.blaster;
                        x = 0;
                        y = 18f / 4f;
                        mirror = true;
                        rotate = false;
                        reload = 40f;
                        recoil = 0;
                        top = false;
                        shoot = new ShootHelix();
                        bullet = new BasicBulletType(4f, 10f) {{
                            sprite = "xenrose-rhomb-bullet";
                            width = 10f;
                            height = 14f;
                            lifetime = 35f;
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.shootBigSmoke;
                            hitColor = backColor = trailColor = Color.valueOf("cea36b");
                            frontColor = Color.valueOf("ffd2a4");
                            trailWidth = 1.55f;
                            trailLength = 5;
                            buildingDamageMultiplier = 0.1f;
                            hitEffect = despawnEffect = new ExplosionEffect(){{
                                lifetime = 26f;
                                waveStroke = 2f;
                                waveColor = sparkColor = smokeColor =  trailColor;
                                waveRad = 10f;
                                smokeSize = 1f;
                                smokeSizeBase = 0.8f;
                                sparks = 3;
                                sparkRad = 18f;
                                sparkLen = 5f;
                                sparkStroke = 1f;
                            }};;
                        }};
                    }});
                }};
             spread = new UnitType("spread"){{
                 coreUnitDock = true;
                 controller = u -> new BuilderAI(true, 500f);
                 constructor = UnitEntity::create;
                 outlineColor = Color.valueOf("211c18");
                 envDisabled = 0;
                 health = 370;
                 hitSize = 17f;

                 faceTarget = true;
                 targetPriority = -2;
                 lowAltitude = false;
                 pickupUnits = false;
                 flying = true;
                 mineSpeed = 9.5f;
                 mineTier = 4;
                 buildSpeed = 1.1f;
                 drag = 0.085f;
                 speed = 4f;
                 rotateSpeed = 12f;
                 accel = 0.7f;
                 itemCapacity = 45;
                 useEngineElevation = false;
                 engineSize = 3f;
                 engineOffset = 7f;
                 fogRadius = 0;

                 alwaysUnlocked = true;
                 mineWalls = true;
                 mineFloor = true;

                 weapons.add(new Weapon("xenrose-spread-weapon"){{
                         shootSound = Sounds.blaster;
                         x = 5;
                         y = 0.25f;
                         mirror = true;
                         rotate = true;
                         rotateSpeed = 7;
                         rotationLimit = 90;
                         reload = 15f;
                         recoil = 1.2f;
                         top = true;
                         bullet = new BasicBulletType(5f, 15f) {{
                             width = 9f;
                             height = 12f;
                             lifetime = 55f;
                             shootEffect = Fx.sparkShoot;
                             smokeEffect = Fx.shootBigSmoke;
                             hitColor = backColor = trailColor = Color.valueOf("cea36b");
                             frontColor = Color.valueOf("ffd2a4");
                             trailWidth = 1.2f;
                             trailLength = 8;
                             homingPower = 0.2f;
                             homingDelay = 16f;
                             homingRange = 30;
                             buildingDamageMultiplier = 0.1f;
                             hitEffect = despawnEffect = new ExplosionEffect(){{
                                 lifetime = 26f;
                                 waveStroke = 1.5f;
                                 waveColor = sparkColor = smokeColor =  trailColor;
                                 waveRad = 7f;
                                 smokeSize = 1f;
                                 smokeSizeBase = 0.8f;
                                 sparks = 3;
                                 sparkRad = 10f;
                                 sparkLen = 5f;
                                 sparkStroke = 1f;
                             }};
                         }};
                 }});
             }};

             //zanar tech tree
             zanar = new UnitType("zanar"){{
                 constructor = MechUnit::create;
                 outlineColor = Color.valueOf("211c18");
                 targetPriority = TargetPriority.core;
                 speed = 0.6f;
                 hitSize = 10f;
                 health = 570;
                 armor = 6;
                 researchCostMultiplier = 0f;

                 weapons.add(new Weapon("xenrose-zanar-weapon"){{
                     x = 24.3f / 4f;
                     y = 1f;
                     rotate = false;
                     mirror = true;
                     recoil = 0.8f;
                     reload = 40f;
                     top = false;
                     shoot.shots = 2;
                     shoot.shotDelay = 4f;
                     shoot.firstShotDelay = 15;
                     inaccuracy = 12f;
                     shootSound = XenSounds.zanarShoot;

                     bullet = new BasicBulletType(3.5f, 23f) {{
                         sprite = "xenrose-basic-bullet1";
                         width = 8f;
                         height = 8f;
                         lifetime = 70f;
                         shootEffect = Fx.sparkShoot;
                         hitColor = backColor = trailColor = Color.valueOf("af8460");
                         frontColor = Color.valueOf("fbcf95");
                         trailLength = 9;
                         trailWidth = 2f;
                         trailSinScl = 1.2f;
                         trailSinMag = 1.2f;
                         trailEffect = XenFx.zanarTrail;
                         trailRotation = true;
                         trailInterval = 4f;

                         trailInterp = v -> Math.max(Mathf.slope(v), 0.7f);
                         shrinkX = 0.2f;
                         shrinkY = 0.1f;

                         hitEffect = despawnEffect = new ExplosionEffect() {{
                             lifetime = 26f;
                             waveStroke = 2f;
                             waveColor = sparkColor = smokeColor =  trailColor;
                             waveRad = 20f;
                             smokeSize = 2f;
                             smokeSizeBase = 1f;
                             sparks = 3;
                             sparkRad = 18f;
                             sparkLen = 5f;
                             sparkStroke = 1f;
                         }};
                     }};
                 }});

                 parts.add(
                         new ShapePart(){{
                             progress = PartProgress.warmup.blend(PartProgress.reload, 1.2f);
                             color = Color.valueOf("bd9765");
                             circle = false;
                             hollow = true;
                             stroke = 1f;
                             strokeTo = 1.5f;
                             radius = 4f;
                             sides = 7;
                             layer = Layer.effect;
                             y = -1;
                             rotateSpeed = 1;
                         }},
                         new HaloPart(){{
                             color = Color.valueOf("bd9765");
                             layer = Layer.effect;
                             y = -1;
                             haloRotation = 90f;
                             shapes = 2;
                             triLength = 0f;
                             triLengthTo = 10f;
                             haloRadius = 8f;
                             tri = true;
                             radius = 4f;
                         }},
                         new HaloPart(){{
                             color = Color.valueOf("af8460");;
                             layer = Layer.effect;
                             y = -1;
                             haloRotation = 90f;
                             shapes = 2;
                             triLength = 0f;
                             triLengthTo = 3f;
                             haloRadius = 8f;
                             tri = true;
                             radius = 4f;
                             shapeRotation = 180f;
                         }});
             }};
             inorn = new UnitType("inorn"){{
                 constructor = MechUnit::create;
                 outlineColor = Color.valueOf("211c18");
                 targetPriority = TargetPriority.core;
                 speed = 0.5f;
                 hitSize = 18f;
                 health = 2050;
                 armor = 6;

                 weapons.add(new Weapon("xenrose-inorn-weapon"){{
                     x = 42f / 4f;
                     y = 0f;
                     rotate = false;
                     mirror = true;
                     recoil = 1.5f;
                     reload = 80f;
                     top = false;
                     shoot.shots = 4;
                     shoot.shotDelay = 9;
                     shoot.firstShotDelay = 30;
                     inaccuracy = 12f;
                     shootSound = XenSounds.zanarShoot;

                     bullet = new BasicBulletType(3.9f, 30f) {{
                         sprite = "xenrose-basic-bullet1";
                         width = 11f;
                         height = 11f;
                         lifetime = 65f;
                         shootEffect = Fx.sparkShoot;
                         hitColor = backColor = trailColor = Color.valueOf("af8460");
                         frontColor = Color.valueOf("fbcf95");
                         trailLength = 11;
                         trailWidth = 2.4f;
                         trailSinScl = 1.6f;
                         trailSinMag = 1.2f;
                         trailEffect = XenFx.zanarTrail;
                         trailRotation = true;
                         trailInterval = 3.5f;

                         trailInterp = v -> Math.max(Mathf.slope(v), 0.7f);
                         shrinkX = 0.2f;
                         shrinkY = 0.1f;

                         hitEffect = despawnEffect = new ExplosionEffect() {{
                             lifetime = 26f;
                             waveStroke = 2f;
                             waveColor = sparkColor = smokeColor =  trailColor;
                             waveRad = 22f;
                             smokeSize = 2f;
                             smokeSizeBase = 1f;
                             sparks = 4;
                             sparkRad = 18f;
                             sparkLen = 7f;
                             sparkStroke = 1f;
                         }};
                         fragBullets = 3;
                         fragRandomSpread = 85f;

                         fragBullet = new BasicBulletType(4f, 5){{
                             sprite = "xenrose-basic-bullet1";
                             width = 10f;
                             height = 10f;
                             lifetime = 40f;
                             hitSize = 4f;
                             pierceCap = 1;
                             pierce = true;
                             pierceBuilding = true;
                             hitColor = backColor = trailColor = Color.valueOf("af8460");
                             frontColor = Color.valueOf("fbcf95");
                             trailWidth = 2f;
                             trailLength = 5;
                             drag = 0.01f;
                             hitEffect = despawnEffect = new ExplosionEffect() {{
                                 lifetime = 26f;
                                 waveStroke = 1f;
                                 waveColor = sparkColor = smokeColor =  trailColor;
                                 waveRad = 18f;
                                 smokeSize = 1f;
                                 smokeSizeBase = 0.5f;
                                 sparks = 4;
                                 sparkRad = 18f;
                                 sparkLen = 6f;
                                 sparkStroke = 1f;
                             }};
                         }};
                     }};
                 }});

                 parts.add(
                         new ShapePart(){{
                             color = Color.valueOf("bd9765");
                             circle = false;
                             hollow = true;
                             stroke = 1.3f;
                             strokeTo = 1.8f;
                             radius = 4.3f;
                             sides = 8;
                             layer = Layer.effect;
                             rotateSpeed = 1;
                         }},
                         new HaloPart(){{
                             color = Color.valueOf("bd9765");
                             layer = Layer.effect;
                             shapes = 4;
                             triLength = 0f;
                             triLengthTo = 5f;
                             haloRadius = 4.5f;
                             tri = true;
                             radius = 3.4f;
                             haloRotateSpeed = 1f;
                         }},
                         new HaloPart(){{
                             color = Color.valueOf("bd9765");
                             layer = Layer.effect;
                             haloRotation = 90f;
                             shapes = 2;
                             triLength = 0f;
                             triLengthTo = 12f;
                             haloRadius = 16.7f;
                             tri = true;
                             radius = 4f;
                         }},
                         new HaloPart(){{
                             color = Color.valueOf("af8460");;
                             layer = Layer.effect;
                             haloRotation = 90f;
                             shapes = 2;
                             triLength = 0f;
                             triLengthTo = 7f;
                             haloRadius = 16.7f;
                             tri = true;
                             radius = 4f;
                             shapeRotation = 180;
                         }});
             }};
             manler = new UnitType("manler"){{
                     constructor = MechUnit::create;
                     outlineColor = Color.valueOf("211c18");
                     targetPriority = TargetPriority.core;
                     speed = 0.45f;
                     hitSize = 23f;
                     health = 5860;
                     armor = 9;

                     abilities.add(new ShieldArcAbility(){{
                         radius = 36f;
                         angle = 82f;
                         regen = 0.6f;
                         cooldown = 60f * 8f;
                         max = 1500f;
                         y = -16.75f;
                         width = 6f;
                         whenShooting = false;
                     }});

                     weapons.add(new Weapon("xenrose-manler-weapon") {{
                         x = 13.5f;
                         y = 0.75f;
                         rotate = false;
                         mirror = true;
                         recoil = 1.5f;
                         reload = 155f;
                         top = false;
                         shoot.shots = 6;
                         shoot.shotDelay = 6;
                         shoot.firstShotDelay = 20;
                         inaccuracy = 15f;
                         shootSound = XenSounds.zanarShoot;

                         bullet = new BasicBulletType(4.3f, 60f) {{
                             sprite = "xenrose-basic-bullet1";
                             width = 13f;
                             height = 13f;
                             lifetime = 68f;
                             shootEffect = Fx.sparkShoot;
                             hitColor = backColor = trailColor = Color.valueOf("af8460");
                             frontColor = Color.valueOf("fbcf95");
                             trailLength = 15;
                             trailWidth = 2.8f;
                             trailSinScl = 1.6f;
                             trailSinMag = 1.2f;
                             trailEffect = XenFx.zanarTrail;
                             trailRotation = true;
                             trailInterval = 3.5f;

                             trailInterp = v -> Math.max(Mathf.slope(v), 0.7f);
                             shrinkX = 0.2f;
                             shrinkY = 0.1f;

                             hitEffect = despawnEffect = new ExplosionEffect() {{
                                 lifetime = 26f;
                                 waveStroke = 1f;
                                 waveColor = sparkColor = smokeColor =  trailColor;
                                 waveRad = 18f;
                                 smokeSize = 1f;
                                 smokeSizeBase = 0.5f;
                                 sparks = 4;
                                 sparkRad = 18f;
                                 sparkLen = 6f;
                                 sparkStroke = 1f;
                             }};

                             intervalBullet = new BasicBulletType(3.4f, 20) {{
                                 width = 13f;
                                 hitSize = 9f;
                                 height = 13f;
                                 pierceCap = 2;
                                 lifetime = 6f;
                                 pierceBuilding = true;
                                 hitColor = backColor = trailColor = Color.valueOf("af8460");
                                 frontColor = Color.valueOf("fbcf95");
                                 trailWidth = 2.5f;
                                 trailLength = 7;
                                 hitEffect = despawnEffect = new WaveEffect() {{
                                     colorFrom = colorTo = Color.valueOf("fbcf95");
                                     sizeTo = 4f;
                                     strokeFrom = 4f;
                                     lifetime = 10f;
                                 }};
                             }};

                             bulletInterval = 6f;
                             intervalRandomSpread = 20f;
                             intervalBullets = 2;
                             intervalAngle = 180f;
                             intervalSpread = 300f;
                         }};
                     }});
                     weapons.add(new Weapon("xenrose-manler-weapon-min") {{
                         x = 6f;
                         y = 0;
                         rotate = true;
                         rotateSpeed = 1.6f;
                         mirror = true;
                         recoil = 1.5f;
                         reload = 90f;
                         shootY = 1.25f;
                         shoot.shots = 14;
                         shoot.shotDelay = 6;
                         shoot.firstShotDelay = 80;
                         inaccuracy = 5f;
                         shootSound = Sounds.mediumCannon;
                         top = true;

                         bullet = new BasicBulletType(6f, 20f) {{
                                 sprite = "xenrose-basic-bullet1";
                                 width = 9f;
                                 height = 9f;
                                 lifetime = 60f;
                                 pierceCap = 3;
                                 pierceBuilding = true;
                                 shootEffect = Fx.sparkShoot;
                                 hitColor = backColor = trailColor = Color.valueOf("af8460");
                                 frontColor = Color.valueOf("fbcf95");
                                 trailLength = 15;
                                 trailWidth = 2.8f;

                                 hitEffect = despawnEffect = new ExplosionEffect() {{
                                     lifetime = 22f;
                                     waveStroke = 1f;
                                     waveColor = sparkColor = smokeColor =  trailColor;
                                     waveRad = 14f;
                                     smokeSize = 1f;
                                     smokeSizeBase = 0.5f;
                                     sparks = 4;
                                     sparkRad = 18f;
                                     sparkLen = 6f;
                                     sparkStroke = 1f;
                                 }};
                             }};
                     }});

                     parts.add(
                             new ShapePart(){{
                                 color = Color.valueOf("bd9765");
                                 circle = false;
                                 hollow = true;
                                 stroke = 1.5f;
                                 strokeTo = 2f;
                                 radius = 5.3f;
                                 sides = 8;
                                 layer = Layer.effect;
                                 rotateSpeed = 1.3f;
                             }},
                             new HaloPart(){{
                                 color = Color.valueOf("bd9765");
                                 layer = Layer.effect;
                                 shapes = 4;
                                 triLength = 0f;
                                 triLengthTo = 9f;
                                 haloRadius = 4.9f;
                                 tri = true;
                                 radius = 3.4f;
                                 haloRotateSpeed = 1.3f;
                             }},
                             new HaloPart(){{
                                 color = Color.valueOf("bd9765");
                                 layer = Layer.effect;
                                 haloRotation = 90f;
                                 shapes = 2;
                                 triLength = 0f;
                                 triLengthTo = 15f;
                                 haloRadius = 18.7f;
                                 tri = true;
                                 radius = 6f;
                             }},
                             new HaloPart(){{
                                 color = Color.valueOf("af8460");;
                                 layer = Layer.effect;
                                 haloRotation = 90f;
                                 shapes = 2;
                                 triLength = 0f;
                                 triLengthTo = 8f;
                                 haloRadius = 18.7f;
                                 tri = true;
                                 radius = 6f;
                                 shapeRotation = 180;
                             }});
                     parts.add(
                             new HaloPart(){{
                                 color = Color.valueOf("bd9765");
                                 layer = Layer.effect;
                                 shapes = 4;
                                 triLength = 0f;
                                 triLengthTo = 6f;
                                 haloRadius = 4.9f;
                                 tri = true;
                                 radius = 3.4f;
                                 haloRotateSpeed = 1.3f;
                                 haloRotation = 45;
                             }},
                             new HaloPart(){{
                                 x = 18.7f;
                                 color = Color.valueOf("bd9765");
                                 layer = Layer.effect;
                                 shapes = 2;
                                 triLength = 0f;
                                 triLengthTo = 9f;
                                 haloRadius = 2.6f;
                                 tri = true;
                                 radius = 4f;

                             }},
                             new HaloPart(){{
                                 x = -18.7f;
                                 color = Color.valueOf("bd9765");
                                 layer = Layer.effect;
                                 shapes = 2;
                                 triLength = 0f;
                                 triLengthTo = 9f;
                                 haloRadius = 2.6f;
                                 tri = true;
                                 radius = 4f;

                             }});
                 }};
             inzeran = new XenUnitType("inzeran"){{
                 constructor = MechUnit::create;
                 outlineColor = Color.valueOf("211c18");
                 targetPriority = TargetPriority.core;
                 speed = 0.39f;
                 hitSize = 32f;
                 health = 9690;
                 armor = 12;
                 crushDamage = 10f;
                 stepShake = 1.2f;
                 rotateSpeed = 3.8f;

                 abilities.add(new ShieldArcAbility(){{
                         radius = 36f;
                         angle = 130f;
                         regen = 1.2f;
                         cooldown = 60f * 8f;
                         max = 4300f;
                         y = -15.25f;
                         width = 6f;
                         whenShooting = true;
                 }},
                         new MoveEffectAbility(12.25f, -12.5f, Color.valueOf("bd9765"), XenFx.inzeranTrail, 1f){{
                             rotateEffect = true;
                             minVelocity = 1.5f;
                 }},
                         new MoveEffectAbility(-12.25f, -12.5f, Color.valueOf("bd9765"), XenFx.inzeranTrail, 1f){{
                             rotateEffect = true;
                             minVelocity = 1.5f;
                 }},
                         new MoveEffectAbility(21.5f, -13.5f, Color.valueOf("bd9765"), XenFx.inzeranTrail, 1f){{
                             rotateEffect = true;
                             minVelocity = 1.5f;
                 }});
                 abilities.add(new MoveEffectAbility(-21.5f, -13.5f, Color.valueOf("bd9765"), XenFx.inzeranTrail, 1f){{
                     rotateEffect = true;
                     minVelocity = 1.5f;
                 }});

                 weapons.add(new Weapon("xenrose-inzeran-weapon"){{
                     x = 74.8f / 4f;
                     y = 0.75f;
                     rotate = false;
                     mirror = true;
                     recoil = 1.6f;
                     reload = 135f;
                     top = false;
                     shootSound = XenSounds.largeShoot1;

                     shoot.firstShotDelay = 60;
                     shoot.shotDelay = 10;
                     shoot.shots = 5;
                     inaccuracy = 8;

                     bullet = new BasicBulletType(4f, 80f){{
                         sprite = "xenrose-basic-bullet1";
                         width = 13f;
                         height = 13;
                         lifetime = 80f;
                         pierceCap = 1;
                         pierceBuilding = true;
                         shootEffect = Fx.sparkShoot;
                         hitColor = backColor = trailColor = Color.valueOf("af8460");
                         frontColor = Color.valueOf("fbcf95");
                         trailLength = 15;
                         trailWidth = 3f;
                         trailEffect = XenFx.zanarTrail;
                         trailRotation = true;
                         trailInterval = 1.3f;
                         hitEffect = despawnEffect = new ExplosionEffect() {{
                             lifetime = 22f;
                             waveStroke = 1f;
                             waveColor = sparkColor = smokeColor =  trailColor;
                             waveRad = 14f;
                             smokeSize = 1f;
                             smokeSizeBase = 0.5f;
                             sparks = 4;
                             sparkRad = 18f;
                             sparkLen = 6f;
                             sparkStroke = 1f;
                         }};
                         fragBullets = 5;
                         fragRandomSpread = 95f;

                         fragBullet = new BasicBulletType(5f, 25){{
                             sprite = "xenrose-basic-bullet1";
                             width = 12f;
                             height = 12f;
                             lifetime = 60f;
                             hitSize = 6f;
                             pierceCap = 5;
                             pierce = true;
                             pierceBuilding = true;
                             hitColor = backColor = trailColor = Color.valueOf("af8460");
                             frontColor = Color.valueOf("fbcf95");
                             trailWidth = 2.7f;
                             trailLength = 6;
                             drag = 0.01f;
                             homingPower = 0.9f;
                             hitEffect = despawnEffect = new ExplosionEffect(){{
                                 lifetime = 26f;
                                 waveStroke = 1f;
                                 waveColor = sparkColor = smokeColor =  trailColor;
                                 waveRad = 18f;
                                 smokeSize = 1f;
                                 smokeSizeBase = 0.5f;
                                 sparks = 4;
                                 sparkRad = 18f;
                                 sparkLen = 6f;
                                 sparkStroke = 1f;
                             }};
                         }};
                     }};
                 }});
                 weapons.add(new Weapon("xenrose-inzeran-weapon-min"){{
                         x = 35f / 4f;
                         y = 0f;
                         rotate = true;
                         rotateSpeed = 1.5f;
                         mirror = true;
                         recoil = 1.6f;
                         reload = 12f;
                         top = true;
                         shootY = 0.25f;
                         shootSound = XenSounds.weaponMinShoot1;

                         shoot.firstShotDelay = 20;
                         inaccuracy = 10;
                         bullet = new BasicBulletType(6f, 35f){{
                             sprite = "xenrose-basic-bullet1";
                             width = 10f;
                             height = 13;
                             lifetime = 55f;
                             pierceCap = 4;
                             pierceBuilding = true;
                             shootEffect = Fx.sparkShoot;
                             hitColor = backColor = trailColor = Color.valueOf("af8460");
                             frontColor = Color.valueOf("fbcf95");
                             trailLength = 11;
                             trailWidth = 2.4f;
                             hitEffect = despawnEffect = new ExplosionEffect() {{
                                 lifetime = 22f;
                                 waveStroke = 1f;
                                 waveColor = sparkColor = smokeColor = trailColor;
                                 waveRad = 14f;
                                 smokeSize = 1f;
                                 smokeSizeBase = 0.5f;
                                 sparks = 4;
                                 sparkRad = 18f;
                                 sparkLen = 6f;
                                 sparkStroke = 1f;
                             }};
                         }};
                     }});
                 weapons.add(new Weapon(){{
                     x = 0f;
                     y = -102f / 4f;
                     reload = 500f;
                     range = 200;
                     mirror = false;
                     shake = 5f;
                     shoot.firstShotDelay = 70;
                     shootStatus = StatusEffects.unmoving;
                     shootStatusDuration = 60f * 0.9f;
                     shoot.shots = 4;
                     shoot.shotDelay = 24;
                     shootSound = XenSounds.inzeranDash;
                     chargeSound = Sounds.lasercharge2;

                     bullet = new BasicBulletType(0, 100, "xenrose-star-bullet"){{
                         recoil = -20f;
                         splashDamage = 220f;
                         splashDamageRadius = 110f;
                         width = height = 22;
                         maxRange = 30f;
                         ignoreRotation = true;
                         backColor = Color.valueOf("af8460");
                         frontColor = Color.valueOf("fbcf95");
                         mixColorTo = Color.valueOf("fbcf95");
                         hitSound = Sounds.plasmaboom;
                         despawnEffect = hitEffect = XenFx.inzeranExplosion;
                         shootCone = 180f;
                         ejectEffect = Fx.none;
                         hitShake = 3f;
                         collidesAir = false;
                         lifetime = 60f;
                         chargeEffect = XenFx.inzeranCharge;
                         keepVelocity = false;
                         spin = 2f;
                         shrinkX = shrinkY = 0.7f;
                         collides = false;
                     }};
                 }});

                 parts.add(
                         new ShapePart(){{
                             color = Color.valueOf("bd9765");
                             circle = false;
                             hollow = true;
                             stroke = 1.7f;
                             strokeTo = 2.2f;
                             radius = 5.5f;
                             sides = 8;
                             layer = Layer.effect;
                             rotateSpeed = 1.5f;
                         }},
                         new HaloPart(){{
                             color = Color.valueOf("bd9765");
                             layer = Layer.effect;
                             haloRotation = 90f;
                             shapes = 4;
                             triLength = 7f;
                             triLengthTo = 12f;
                             haloRotateSpeed = 1.5f;
                             haloRadius = 5f;
                             haloRadiusTo = 14f;
                             tri = true;
                             radius = 5.2f;
                         }},
                         new HaloPart(){{
                             color = Color.valueOf("af8460");;
                             layer = Layer.effect;
                             haloRotation = 90f;
                             shapes = 4;
                             triLength = 0f;
                             triLengthTo = 5f;
                             haloRotateSpeed = 1.5f;
                             haloRadius = 5f;
                             haloRadiusTo = 14f;
                             tri = true;
                             radius = 5.2f;
                             shapeRotation = 180;
                         }});
                 parts.add(
                         new HaloPart(){{
                             y = 5;
                             color = Color.valueOf("bd9765");
                             layer = Layer.effect;
                             haloRotation = 90f;
                             shapes = 2;
                             triLength = 0f;
                             triLengthTo = 16;
                             haloRadius = 26.5f;
                             tri = true;
                             radius = 5f;
                         }},
                         new HaloPart(){{
                             y = 5f;
                             color = Color.valueOf("af8460");;
                             layer = Layer.effect;
                             haloRotation = 90f;
                             shapes = 2;
                             triLength = 0f;
                             triLengthTo = 8f;
                             haloRadius = 26.5f;
                             tri = true;
                             radius = 5f;
                             shapeRotation = 180;
                         }},
                         new HaloPart(){{
                             y = -3.5f;
                             color = Color.valueOf("bd9765");
                             layer = Layer.effect;
                             haloRotation = 90f;
                             shapes = 2;
                             triLength = 0f;
                             triLengthTo = 14;
                             haloRadius = 25f;
                             tri = true;
                             radius = 5f;
                         }},
                         new HaloPart(){{
                             y = -3.5f;
                             color = Color.valueOf("af8460");;
                             layer = Layer.effect;
                             haloRotation = 90f;
                             shapes = 2;
                             triLength = 0f;
                             triLengthTo = 7f;
                             haloRadius = 25f;
                             tri = true;
                             radius = 5f;
                             shapeRotation = 180;
                         }});
             }};

             //imitation tech tree
             imitation = new UnitType("imitation"){{
                 constructor = ElevationMoveUnit::create;

                 hovering = true;
                 canDrown = false;
                 useEngineElevation = false;
                 health = 740;
                 armor = 4;
                 outlineColor = Color.valueOf("211c18");
                 speed = 3f;
                 drag = 0.09f;
                 rotateSpeed = 19f;
                 accel = 0.6f;
                 hitSize = 12f;
                 health = 460;
                 shadowElevation = 0.1f;
                 engineOffset = 22f / 4f;
                 engineSize = 2.7f;

                 abilities.add(
                         new MoveEffectAbility(3.5f, -5f, Pal.sapBulletBack, XenFx.imitationTrail, 2.5f){{
                             color = Color.valueOf("c696f2b7");
                             rotateEffect = true;
                         }},
                         new MoveEffectAbility(-3.5f, -5f, Pal.sapBulletBack, XenFx.imitationTrail, 2.5f){{
                             color = Color.valueOf("c696f2b7");
                             rotateEffect = true;
                         }});

                 parts.add(
                         new RegionPart("-wing"){{
                                 moveRot = -15f;
                                 moveY = -0.5f;
                                 progress = PartProgress.warmup;
                                 mirror = true;
                                 under = true;
                                 children.add(new HoverPart(){{
                                     x = 6.25f;
                                     y = -5;
                                     mirror = true;
                                     sides = 5;
                                     radius = 5f;
                                     phase = 110f;
                                     stroke = 1.5f;
                                     layerOffset = -0.01f;
                                     color = Color.valueOf("c696f2b7");
                                 }});
                             }}
                 );

                 weapons.add(new Weapon("xenrose-imitation-weapon"){{
                         x = 0f;
                         y = -3f / 4f;
                         rotate = true;
                         rotateSpeed = 2.3f;
                         rotationLimit = 50;
                         mirror = false;
                         recoil = 1f;
                         reload = 80f;
                         top = true;
                         shootY = 0.75f;
                         layerOffset = 0.001f;
                         shootSound = XenSounds.imitationShoot;

                         shoot.firstShotDelay = 25;
                         shoot.shotDelay = 6;
                         shoot.shots = 3;
                         inaccuracy = 6;

                         bullet = new BasicBulletType(7.6f, 15f, "xenrose-basic-bullet1") {{
                             width = 5f;
                             height = 9.5f;
                             lifetime = 25f;
                             pierce = true;
                             pierceBuilding = true;
                             pierceCap = 2;
                             shootEffect = Fx.sparkShoot;
                             hitColor = backColor = trailColor = Color.valueOf("6f42a4");
                             frontColor = Color.valueOf("c282ff");
                             trailLength = 11;
                             trailWidth = 1.2f;
                             lightColor = Color.valueOf("c08af4");
                             lightOpacity = 0.2f;
                             lightRadius = 2;
                             hitEffect = despawnEffect = new ExplosionEffect() {{
                                 lifetime = 30f;
                                 waveStroke = 1.4f;
                                 waveColor = Color.white;
                                 sparkColor = trailColor;
                                 waveRad = 30f;
                                 smokeSize = 2f;
                                 smokeSizeBase = 1f;
                                 smokeColor = Color.valueOf("cf9cff");
                                 sparks = 4;
                                 sparkRad = 25f;
                                 sparkLen = 7f;
                                 sparkStroke = 2f;
                             }};
                         }};
                     }});
             }};
             simulation = new UnitType("simulation"){{
                 constructor = ElevationMoveUnit::create;

                 hovering = true;
                 canDrown = false;
                 useEngineElevation = false;
                 health = 1850;
                 armor = 5;
                 outlineColor = Color.valueOf("211c18");
                 speed = 2.4f;
                 drag = 0.09f;
                 rotateSpeed = 19f;
                 accel = 0.04f;
                 hitSize = 16f;
                 shadowElevation = 0.1f;
                 trailLength = 16;
                 trailColor = Color.valueOf("c696f2b7");
                 setEnginesMirror(
                         new UnitEngine(3, -8.5f, 2f, 275f)
                 );

                 abilities.add(
                         new MoveEffectAbility(8.5f, -8f, Pal.sapBulletBack, XenFx.imitationTrail, 3f){{
                             color = Color.valueOf("c696f2b7");
                             rotateEffect = true;
                         }},
                         new MoveEffectAbility(-8.5f, -8f, Pal.sapBulletBack, XenFx.imitationTrail, 3f){{
                             color = Color.valueOf("c696f2b7");
                             rotateEffect = true;
                         }});

                 parts.add(
                         new RegionPart("-wing"){{
                             moveRot = -9f;
                             moveY = 1.6f;
                             progress = PartProgress.warmup;
                             mirror = true;
                             under = true;
                             children.add(new HoverPart(){{
                                 x = 7.5f;
                                 y = -8.25f;
                                 mirror = true;
                                 sides = 5;
                                 radius = 7.25f;
                                 phase = 110f;
                                 stroke = 1.5f;
                                 layerOffset = -0.01f;
                                 color = Color.valueOf("c696f2b7");
                             }});
                         }},
                         new HoverPart(){{
                             x = 6.25f;
                             y = 5.25f;
                             mirror = true;
                             sides = 5;
                             radius = 7.25f;
                             phase = 110f;
                             stroke = 1.5f;
                             layerOffset = -0.01f;
                             color = Color.valueOf("c696f2b7");
                         }});

                 weapons.add(new Weapon("xenrose-simulation-weapon"){{
                     x = 0f;
                     y = 0f;
                     rotate = true;
                     rotateSpeed = 1.2f;
                     rotationLimit = 30;
                     mirror = false;
                     recoil = 1.5f;
                     shootY = 0.75f;
                     reload = 250;
                     top = true;
                     layerOffset = 0.001f;
                     accel = 0.2f;
                     shootSound = XenSounds.imitationShoot;
                     shoot.shots = 10;
                     shoot.shotDelay = 8;
                     shoot.firstShotDelay = 35;

                     bullet = new BasicBulletType(7.3f, 28f, "xenrose-basic-bullet1") {{
                         width = 6f;
                         height = 11f;
                         lifetime = 30f;
                         pierce = true;
                         pierceBuilding = true;
                         pierceCap = 3;
                         shootEffect = Fx.sparkShoot;
                         hitColor = backColor = trailColor = Color.valueOf("6f42a4");
                         frontColor = Color.valueOf("e5c9ff");
                         trailLength = 11;
                         trailWidth = 1.2f;
                         lightColor = Color.valueOf("e5c9ff");
                         lightOpacity = 0.2f;
                         lightRadius = 2;
                         hitEffect = despawnEffect = new ExplosionEffect() {{
                             lifetime = 30f;
                             waveStroke = 1.6f;
                             waveColor = Color.white;
                             sparkColor = trailColor;
                             waveRad = 30f;
                             smokeSize = 2f;
                             smokeSizeBase = 1f;
                             smokeColor = Color.valueOf("cf9cff");
                             sparks = 4;
                             sparkRad = 25f;
                             sparkLen = 7f;
                             sparkStroke = 2f;
                         }};
                         chargeEffect = new WaveEffect(){{
                             rotWithParent = true;
                             followParent = true;
                             lifetime = 34;
                             sizeFrom = 7;
                             sizeTo = 0;
                             strokeFrom = 0;
                             strokeTo = 1.3f;
                             colorFrom = Color.valueOf("e5c9ff");
                             colorTo = Color.valueOf("cf9cff");
                         }};
                     }};
                 }});
             }};

             fusion = new UnitType("fusion"){{
                 constructor = UnitEntity::create;

                 flying = true;
                 health = 3970;
                 armor = 6;
                 outlineColor = Color.valueOf("211c18");
                 speed = 2f;
                 drag = 0.09f;
                 rotateSpeed = 14f;
                 accel = 0.04f;
                 hitSize = 20f;
                 lowAltitude = true;
                 setEnginesMirror(
                         new UnitEngine(4.5f, -9.5f, 2.6f, 275f)
                 );

                 abilities.add(
                         new MoveEffectAbility(7.75f, -12.5f, Pal.sapBulletBack, XenFx.imitationTrail, 3f){{
                             color = Color.valueOf("c696f2b7");
                             rotateEffect = true;
                         }},
                         new MoveEffectAbility(-7.75f, -12.5f, Pal.sapBulletBack, XenFx.imitationTrail, 3f){{
                             color = Color.valueOf("c696f2b7");
                             rotateEffect = true;
                         }});
                 parts.add(
                         new RegionPart("-blade"){{
                             progress = PartProgress.warmup;
                             mirror = true;
                             moveY = -0.8f;
                             moveRot = -8;
                             moves.add(new PartMove(PartProgress.reload, 0f, -0.2f, -3f));
                         }},
                         new RegionPart("-back"){{
                             moveY = 0.7f;
                             progress = PartProgress.warmup;
                         }});

                 weapons.add(new Weapon("xenrose-fusion-weapon"){{
                     x = 0f;
                     y = 0.625f;
                     rotate = true;
                     rotateSpeed = 1.5f;
                     rotationLimit = 260;
                     mirror = false;
                     recoil = 1.65f;
                     shootY = 1.5f;
                     reload = 340;
                     top = true;
                     layerOffset = 0.001f;
                     shootSound = XenSounds.imitationShoot;
                     shoot.shots = 3;
                     shoot.shotDelay = 13;
                     shoot.firstShotDelay = 35;

                     bullet = new BasicBulletType(6f, 50f, "xenrose-basic-bullet1"){{
                         width = 9f;
                         height = 15f;
                         lifetime = 30f;
                         pierce = true;
                         pierceBuilding = true;
                         pierceCap = 2;
                         shootEffect = Fx.sparkShoot;
                         hitColor = backColor = trailColor = Color.valueOf("6f42a4");
                         frontColor = Color.valueOf("e5c9ff");
                         trailLength = 13;
                         trailWidth = 1.9f;
                         lightColor = Color.valueOf("e5c9ff");
                         hitEffect = despawnEffect = new ExplosionEffect() {{
                             lifetime = 30f;
                             waveStroke = 1.6f;
                             waveColor = Color.white;
                             sparkColor = trailColor;
                             waveRad = 30f;
                             smokeSize = 2f;
                             smokeSizeBase = 1f;
                             smokeColor = Color.valueOf("cf9cff");
                             sparks = 4;
                             sparkRad = 25f;
                             sparkLen = 7f;
                             sparkStroke = 2f;
                         }};
                         chargeEffect = new WaveEffect(){{
                             rotWithParent = true;
                             followParent = true;
                             lifetime = 34;
                             sizeFrom = 7;
                             sizeTo = 0;
                             strokeFrom = 0;
                             strokeTo = 1.3f;
                             colorFrom = Color.valueOf("e5c9ff");
                             colorTo = Color.valueOf("cf9cff");
                         }};
                     }};
                 }});
             }};
    }
}

