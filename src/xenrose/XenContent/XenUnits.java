package xenrose.XenContent;

import arc.graphics.Color;
import arc.math.Mathf;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.BuilderAI;
import mindustry.ai.types.FlyingFollowAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.TargetPriority;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.abilities.ShieldArcAbility;
import mindustry.entities.abilities.UnitSpawnAbility;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.part.HaloPart;
import mindustry.entities.part.HoverPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.ShootHelix;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import xenrose.type.XenUnitType;


public class XenUnits {
    public static UnitType
            //core
            spraying, spread,
            //zanar tech tree
            zanar, inorn, manler, inzeran,
            //imitation tech tree
            imitation, simulation, fusion,
            //sinar tech tree
            sinar,
            //xanit tech tree
            xanit, manul, amiren,
            //suport units
            kinor;

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
                         x = 0f;
                         y = 5f;
                         mirror = false;
                         rotate = false;
                         reload = 35;
                         recoil = 1.5f;
                         top = false;
                         shoot.shots = 3;
                         shoot.firstShotDelay = 15;
                         inaccuracy = 14;
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
                 health = 520;
                 armor = 5;
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
                     shoot.shots = 6;
                     shoot.shotDelay = 9;
                     shoot.firstShotDelay = 30;
                     inaccuracy = 12f;
                     shootSound = XenSounds.zanarShoot;

                     bullet = new BasicBulletType(3.9f, 35f) {{
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
                                 width = 6f;
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
                 speed = 7f;
                 drag = 0.18f;
                 rotateSpeed = 14f;
                 accel = 0.04f;
                 hitSize = 20f;
                 lowAltitude = true;
                 setEnginesMirror(
                         new UnitEngine(4.5f, -9.5f, 2.6f, 275f)
                 );

                 abilities.add(
                         new MoveEffectAbility(7.75f, -11.5f, Pal.sapBulletBack, XenFx.imitationTrail, 3f){{
                             color = Color.valueOf("c696f2b7");
                             rotateEffect = true;
                         }},
                         new MoveEffectAbility(-7.75f, -11.5f, Pal.sapBulletBack, XenFx.imitationTrail, 3f){{
                             color = Color.valueOf("c696f2b7");
                             rotateEffect = true;
                         }});
                 parts.add(
                         new RegionPart("-back"){{
                             moveY = 0.7f;
                             mirror = true;
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
                     shoot.shots = 25;
                     shoot.shotDelay = 4;
                     shoot.firstShotDelay = 35;
                     inaccuracy = 7;

                     bullet = new BasicBulletType(6f, 45f, "xenrose-basic-bullet1"){{
                         width = 10;
                         height = 12;
                         lifetime = 60;
                         pierce = true;
                         pierceBuilding = true;
                         pierceCap = 3;
                         frontColor = trailColor = Color.valueOf("d5a8ff");
                         backColor =  Color.valueOf("633e8f");
                         trailWidth = 2.3f;
                         trailLength = 12;
                         trailEffect = XenFx.imitationTrail;
                         trailRotation = true;
                         trailInterval = 5;
                         chargeEffect = new MultiEffect(
                                 new WaveEffect(){{
                                     rotWithParent = true;
                                     followParent = true;
                                     lifetime = 34;
                                     sizeFrom = 7;
                                     sizeTo = 0;
                                     strokeFrom = 0;
                                     strokeTo = 1.3f;
                                     colorFrom = Color.valueOf("e5c9ff");
                                     colorTo = Color.valueOf("cf9cff");
                                 }},
                                 new ParticleEffect(){{
                                     particles = 2;
                                     length = -50;
                                     baseLength = 50;
                                     cone = 50;
                                     lifetime = 30;
                                     sizeFrom = 0;
                                     sizeTo = 2.5f;
                                     colorFrom = Color.valueOf("d5a8ff");
                                     colorTo = Color.valueOf("cf9cff");
                                 }});
                         despawnEffect = hitEffect = new MultiEffect(
                                 new WaveEffect(){{
                                     rotWithParent = true;
                                     followParent = true;
                                     lifetime = 36;
                                     sizeFrom = 0;
                                     sizeTo = 12;
                                     strokeFrom = 2;
                                     strokeTo = 0f;
                                     colorFrom = Color.valueOf("e5c9ff");
                                     colorTo = Color.valueOf("cf9cff");
                                 }},
                                 new ParticleEffect(){{
                                     particles = 17;
                                     length = 40;
                                     baseLength = 0;
                                     cone = 60;
                                     lifetime = 65;
                                     sizeFrom = 2.5f;
                                     sizeTo = 0f;
                                     colorFrom = Color.valueOf("d5a8ff");
                                     colorTo = Color.valueOf("cf9cff");
                                 }});
                     }};
                 }});
                 weapons.add(new Weapon(){{
                     x = 0f;
                     y = 5f;
                     rotate = false;
                     mirror = false;
                     recoil = 0f;
                     reload = 285;
                     shootSound = XenSounds.xanitShoot;
                     shoot = new ShootSpread(3, 25);

                     parts.add(
                             new RegionPart("xenrose-fusion-blade"){{
                                 y = -5.2f;
                                 progress = PartProgress.warmup;
                                 under = true;
                                 mirror = true;
                                 moveY = -0.4f;
                                 moveRot = -4;
                                 moves.add(new PartMove(PartProgress.recoil, 0f, -0.3f, -4f));
                             }});

                     bullet = new BasicBulletType(4f, 110f, "xenrose-basic-bullet1"){{
                         width = 13;
                         height = 15;
                         lifetime = 124;
                         trailInterval = 1.65f;
                         trailLength = 10;
                         trailWidth = 3.2f;
                         homingPower = 0.19f;
                         homingRange = 50;
                         homingDelay = 15;
                         frontColor = trailColor = Color.valueOf("e5c9ff");
                         backColor = Color.valueOf("633e8f");
                         trailEffect = hitEffect = despawnEffect = new ParticleEffect(){{
                             colorFrom = Color.valueOf("e5c9ff");
                             colorTo = Color.valueOf("cf9cff");
                             particles = 1;
                             sizeFrom = 3;
                             sizeTo = 0;
                         }};
                         chargeEffect = new MultiEffect(
                                 new WaveEffect(){{
                                     rotWithParent = true;
                                     followParent = true;
                                     lifetime = 50;
                                     sizeFrom = 7;
                                     sizeTo = 0;
                                     strokeFrom = 0;
                                     strokeTo = 1.3f;
                                     colorFrom = Color.valueOf("e5c9ff");
                                     colorTo = Color.valueOf("cf9cff");
                                 }},
                                 new ParticleEffect(){{
                                     particles = 10;
                                     length = -70;
                                     baseLength = 70;
                                     cone = 50;
                                     lifetime = 65;
                                     sizeFrom = 0;
                                     sizeTo = 2.5f;
                                     colorFrom = Color.valueOf("d5a8ff");
                                     colorTo = Color.valueOf("cf9cff");
                                 }});
                         despawnEffect = hitEffect = new MultiEffect(
                                 new WaveEffect(){{
                                     rotWithParent = true;
                                     followParent = true;
                                     lifetime = 70;
                                     sizeFrom = 28;
                                     sizeTo = 28;
                                     strokeFrom = 2.8f;
                                     strokeTo = 0f;
                                     colorFrom = Color.valueOf("e5c9ff");
                                     colorTo = Color.valueOf("cf9cff");
                                 }},
                                 new WaveEffect(){{
                                     rotWithParent = true;
                                     followParent = true;
                                     lifetime = 58;
                                     sizeFrom = 13;
                                     sizeTo = 12;
                                     strokeFrom = 1.9f;
                                     strokeTo = 0f;
                                     colorFrom = Color.valueOf("e5c9ff");
                                     colorTo = Color.valueOf("cf9cff");
                                 }},
                                 new ParticleEffect(){{
                                     particles = 10;
                                     length = 40;
                                     baseLength = 0;
                                     lifetime = 65;
                                     sizeFrom = 4;
                                     sizeTo = 0f;
                                     colorFrom = Color.valueOf("d5a8ff");
                                     colorTo = Color.valueOf("cf9cff");
                                 }});
                     }};
                 }});
             }};

             //sinar tech tree
             sinar = new UnitType("sinar"){{
                 constructor = UnitEntity::create;
                 defaultCommand = UnitCommand.repairCommand;

                 outlineColor = Color.valueOf("211c18");
                 health = 1340;
                 hitSize = 12f;

                 flying = true;
                 drag = 0.085f;
                 speed = 4f;
                 rotateSpeed = 7f;
                 accel = 0.2f;
                 itemCapacity = 12;
                 engineSize = 3f;
                 engineOffset = 20f / 4f;

                 weapons.add(new Weapon("xenrose-sinar-weapon"){{
                     x = 13f / 4f;
                     y = 10.75f / 4f;
                     reload = 48f;
                     recoil = 0.6f;
                     rotate = false;
                     mirror = true;
                     top = false;
                     alternate = false;
                     shootSound = XenSounds.weaponMinShoot1;

                     shoot.shots = 2;
                     shoot.shotDelay = 3.4f;
                     shoot.firstShotDelay = 50;
                     inaccuracy = 8;

                     bullet = new BasicBulletType(3, 25, "xenrose-basic-bullet1"){{
                         height = 11;
                         width = 9;
                         lifetime = 76;
                         frontColor = trailColor = Color.valueOf("c6cfda");
                         backColor = Color.valueOf("89939e");
                         trailWidth = 2.1f;
                         trailLength = 8;
                         homingPower = 0.195f;
                         homingRange = 35;
                         homingDelay = 10;

                         healColor = Color.valueOf("c6cfda");
                         healPercent = 1.7f;
                         collidesTeam = true;
                         reflectable = false;
                         hitEffect = despawnEffect = new ExplosionEffect() {{
                             lifetime = 30f;
                             waveStroke = 1.6f;
                             waveColor = Color.white;
                             sparkColor = trailColor;
                             waveRad = 30f;
                             smokeSize = 2f;
                             smokeSizeBase = 1f;
                             smokeColor = Color.valueOf("c6cfda");
                             sparks = 4;
                             sparkRad = 25f;
                             sparkLen = 7f;
                             sparkStroke = 2f;
                         }};
                     }};
                 }});
             }};

        //support units
        kinor = new UnitType("kinor"){{
            constructor = UnitEntity::create;
            aiController = FlyingFollowAI::new;

            outlineColor = Color.valueOf("211c18");
            health = 340;
            hitSize = 12f;

            flying = true;
            drag = 0.085f;
            speed = 3.4f;
            rotateSpeed = 10f;
            accel = 0.2f;
            itemCapacity = 15;
            engineSize = 3.2f;
            engineOffset = 22 / 4f;

            weapons.add(new Weapon("xenrose-kinor-weapon"){{
                x = 13f / 4f;
                y = 1.3f;
                reload = 40f;
                recoil = 0.6f;
                rotate = false;
                mirror = true;
                top = false;
                shootSound = XenSounds.weaponMinShoot1;

                shoot.shots = 4;
                shoot.shotDelay = 5;
                shoot.firstShotDelay = 50;
                inaccuracy = 8;

                bullet = new BasicBulletType(3.4f, 20, "xenrose-basic-bullet1") {{
                    width = 6f;
                    height = 8f;
                    lifetime = 67f;
                    frontColor = trailColor = Color.valueOf("cff4ed");
                    backColor = Color.valueOf("5b8f92");
                    trailWidth = 1.4f;
                    trailLength = 7;
                    homingPower = 0.195f;
                    homingRange = 35;
                    homingDelay = 10;
                    hitEffect = despawnEffect = new ExplosionEffect() {{
                        lifetime = 30f;
                        waveStroke = 1.6f;
                        waveColor = Color.white;
                        sparkColor = trailColor;
                        waveRad = 30f;
                        smokeSize = 2f;
                        smokeSizeBase = 1f;
                        smokeColor = Color.valueOf("cff4ed");
                        sparks = 4;
                        sparkRad = 25f;
                        sparkLen = 7f;
                        sparkStroke = 2f;
                    }};
                }};
            }});
        }};

             //xanit tech tree
             xanit = new UnitType("xanit"){{
                 constructor = UnitWaterMove::create;

                 speed = 1.9f;
                 drag = 0.13f;
                 hitSize = 9f;
                 health = 1420;
                 armor = 2f;
                 accel = 0.4f;
                 rotateSpeed = 3.3f;
                 faceTarget = false;
                 outlineColor = Color.valueOf("211c18");

                 trailLength = 18;
                 waveTrailX = 3.4f;
                 waveTrailY = -30f / 4f;
                 trailScl = 1.8f;

                 weapons.add(new Weapon("xenrose-xanit-weapon"){{
                     x = 0;
                     y = -0.15f;
                     reload = 26f;
                     shootY = 1.5f;
                     rotate = true;
                     rotateSpeed = 1.4f;
                     ejectEffect = Fx.casing1;
                     mirror = false;
                     shootSound = XenSounds.xanitShoot;
                     bullet = new BasicBulletType(2.5f, 35, "xenrose-basic-bullet1"){{
                         width = 7f;
                         height = 10f;
                         lifetime = 86f;
                         frontColor = trailColor = Color.valueOf("cff4ed");
                         backColor = Color.valueOf("5b8f92");
                         trailWidth = 1.8f;
                         trailLength = 10;
                         trailEffect = Fx.missileTrail;
                         trailInterval = 5f;
                         trailParam = 2.3f;
                         homingPower = 0.19f;
                         homingRange = 40;
                         homingDelay = 15;
                         hitEffect = despawnEffect = new MultiEffect(
                                 new WaveEffect() {{
                                     lifetime = 34f;
                                     colorFrom = Color.valueOf("cff4ed");
                                     colorTo = Color.valueOf("5b8f92");
                                     sizeFrom = 10;
                                     sizeTo = 6;
                                     lightScl = 3;
                                     lightOpacity = 1;
                                     strokeFrom = 2f;
                                     strokeTo = 0;
                                 }},
                                 new ParticleEffect(){{
                                     colorFrom = Color.valueOf("cff4ed");
                                     colorTo = Color.valueOf("5b8f92");
                                     particles = 10;
                                     cone = 360;
                                     baseLength = 16;
                                     lightScl = 3;
                                     lightOpacity = 2;
                                     spin = 0;
                                     sizeFrom = 2.5f;
                                     sizeTo = 0;
                                     offset = 1;
                                 }});
                     }};
                 }});
             }};
             manul = new UnitType("manul"){{
                 constructor = UnitWaterMove::create;

                 speed = 1.4f;
                 drag = 0.13f;
                 hitSize = 17f;
                 health = 2630;
                 armor = 2f;
                 accel = 0.4f;
                 rotateSpeed = 2.9f;
                 faceTarget = false;
                 outlineColor = Color.valueOf("211c18");

                 trailLength = 21;
                 waveTrailX = 6f;
                 waveTrailY = -33f / 4f;
                 trailScl = 1.8f;

                 parts.add(
                         new RegionPart("-blade"){{
                             progress = PartProgress.warmup;
                             moveY = -0.8f;
                             moveX = - 0.7f;
                             mirror = true;
                             under = true;
                             layerOffset = -0.0001f;
                         }});

                 weapons.add(new Weapon("xenrose-manul-weapon"){{
                     x = 0;
                     y = 1f;
                     reload = 84f;
                     shootY = 7f / 4f;
                     rotate = true;
                     rotateSpeed = 1.1f;
                     ejectEffect = Fx.casing1;
                     mirror = false;
                     shootSound = XenSounds.xanitShoot;

                     shoot.shots = 4;
                     shoot.shotDelay = 9;
                     shoot.firstShotDelay = 50;
                     inaccuracy = 9;
                     bullet = new BasicBulletType(3.1f, 50, "xenrose-basic-bullet1"){{
                         width = 10f;
                         height = 13f;
                         lifetime = 105f;
                         frontColor = trailColor = Color.valueOf("cff4ed");
                         backColor = Color.valueOf("5b8f92");
                         trailWidth = 2.1f;
                         trailLength = 14;
                         trailEffect = Fx.missileTrail;
                         trailInterval = 4f;
                         trailParam = 2.7f;
                         homingPower = 0.19f;
                         homingRange = 65;
                         homingDelay = 15;
                         chargeEffect = new MultiEffect(
                                 new WaveEffect(){{
                                     rotWithParent = true;
                                     followParent = true;
                                     lifetime = 20;
                                     sizeFrom = 10;
                                     sizeTo = 0;
                                     strokeFrom = 0;
                                     strokeTo = 1.5f;
                                     colorFrom = Color.valueOf("cff4ed");
                                     colorTo = Color.valueOf("5b8f92");
                                 }},
                                 new WaveEffect(){{
                                     rotWithParent = true;
                                     followParent = true;
                                     lifetime = 29;
                                     sizeFrom = 17;
                                     sizeTo = 0;
                                     strokeFrom = 0;
                                     strokeTo = 1.5f;
                                     colorFrom = Color.valueOf("cff4ed");
                                     colorTo = Color.valueOf("5b8f92");
                                 }});
                         hitEffect = despawnEffect = new MultiEffect(
                                 new WaveEffect() {{
                                     lifetime = 40f;
                                     colorFrom = Color.valueOf("cff4ed");
                                     colorTo = Color.valueOf("5b8f92");
                                     sizeFrom = 14;
                                     sizeTo = 7;
                                     lightScl = 3;
                                     lightOpacity = 1;
                                     strokeFrom = 3.2f;
                                     strokeTo = 0;
                                 }},
                                 new ParticleEffect(){{
                                     colorFrom = Color.valueOf("cff4ed");
                                     colorTo = Color.valueOf("5b8f92");
                                     particles = 10;
                                     cone = 360;
                                     baseLength = 16;
                                     lightScl = 3;
                                     lightOpacity = 2;
                                     spin = 0;
                                     sizeFrom = 2.5f;
                                     sizeTo = 0;
                                     offset = 1;
                                 }});
                     }};
                 }});
             }};
             amiren = new UnitType("amiren"){{
                 constructor = UnitWaterMove::create;

                 speed = 1.1f;
                 drag = 0.19f;
                 hitSize = 29f;
                 health = 5920;
                 armor = 4f;
                 accel = 0.2f;
                 rotateSpeed = 1.8f;
                 faceTarget = false;
                 outlineColor = Color.valueOf("211c18");

                 float spawnTime = 60f * 12f;

                 abilities.add(new UnitSpawnAbility(XenUnits.kinor, spawnTime, 0f, -7.75f));

                 trailLength = 27;
                 waveTrailX = 43f / 4f;
                 waveTrailY = -48f / 4f;
                 trailScl = 1.95f;

                 weapons.add(new Weapon("xenrose-amiren-weapon"){{
                         x = 30f / 4f;
                         y = 0.25f;
                         reload = 50f;
                         shootY = 1;
                         rotate = true;
                         rotateSpeed = 0.85f;
                         ejectEffect = Fx.casing1;
                         mirror = true;
                         shootSound = XenSounds.xanitShoot;
                         alternate = false;

                         shoot.shots = 3;
                         shoot.shotDelay = 5;
                         shoot.firstShotDelay = 50;
                         inaccuracy = 16;

                     bullet = new BasicBulletType(2.6f, 60, "xenrose-basic-bullet1"){{
                         width = 9f;
                         height = 12f;
                         lifetime = 153f;
                         frontColor = trailColor = Color.valueOf("cff4ed");
                         backColor = Color.valueOf("5b8f92");
                         trailWidth = 2.1f;
                         trailLength = 14;
                         trailEffect = Fx.missileTrail;
                         trailInterval = 6f;
                         trailParam = 2.95f;
                         homingPower = 0.195f;
                         homingRange = 65;
                         homingDelay = 15;
                         chargeEffect = new MultiEffect(
                                 new WaveEffect() {{
                                     rotWithParent = true;
                                     followParent = true;
                                     lifetime = 20;
                                     sizeFrom = 10;
                                     sizeTo = 0;
                                     strokeFrom = 0;
                                     strokeTo = 1.5f;
                                     colorFrom = Color.valueOf("cff4ed");
                                     colorTo = Color.valueOf("5b8f92");
                                 }},
                                 new WaveEffect() {{
                                     rotWithParent = true;
                                     followParent = true;
                                     lifetime = 29;
                                     sizeFrom = 17;
                                     sizeTo = 0;
                                     strokeFrom = 0;
                                     strokeTo = 1.5f;
                                     colorFrom = Color.valueOf("cff4ed");
                                     colorTo = Color.valueOf("5b8f92");
                                 }});
                             hitEffect = despawnEffect = new MultiEffect(
                                     new WaveEffect() {{
                                         lifetime = 40f;
                                         colorFrom = Color.valueOf("cff4ed");
                                         colorTo = Color.valueOf("5b8f92");
                                         sizeFrom = 14;
                                         sizeTo = 7;
                                         lightScl = 3;
                                         lightOpacity = 1;
                                         strokeFrom = 3.2f;
                                         strokeTo = 0;
                                     }},
                                     new ParticleEffect() {{
                                         colorFrom = Color.valueOf("cff4ed");
                                         colorTo = Color.valueOf("5b8f92");
                                         particles = 10;
                                         cone = 360;
                                         baseLength = 16;
                                         lightScl = 3;
                                         lightOpacity = 2;
                                         spin = 0;
                                         sizeFrom = 2.5f;
                                         sizeTo = 0;
                                         offset = 1;
                                     }});
                         }};
                     }});
                 }};
    }
}

