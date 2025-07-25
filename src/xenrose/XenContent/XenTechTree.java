package xenrose.XenContent;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.TechTree;
import mindustry.game.Objectives;

import static mindustry.content.TechTree.node;
import static mindustry.content.TechTree.nodeProduce;

public class XenTechTree {
    public static void load() {
        XenPlanets.xenor.techTree = TechTree.nodeRoot("Xenor", XenBlocks.coreSunrise, () -> {
            //Drills
            node(XenBlocks.energyDrill, Seq.with(new Objectives.Produce(XenItems.damascus)), () -> {
                node(XenBlocks.airMechanicalDrill, () -> {});
                node(XenBlocks.energyChargedDrill, () -> {});
            });
            //Logistics
            node(XenBlocks.damascusConveyor, Seq.with(new Objectives.Produce(XenItems.damascus)), () -> {
                node(XenBlocks.damascusJunction, () -> {
                    node(XenBlocks.damascusRouter);
                    node(XenBlocks.damascusBridge);
                });
                node(XenBlocks.dantstalinConveyor, () -> {});
            });
            //Defense
            node(XenBlocks.samum, Seq.with(new Objectives.Produce(XenItems.zinc)), () -> {
                node(XenBlocks.desiccation, () -> {
                    node(XenBlocks.merge, Seq.with(new Objectives.OnSector(XenSectorPresets.BurntHills)), () -> {});
                    node(XenBlocks.tributary, () -> {});
                    node(XenBlocks.overflow, Seq.with(new Objectives.OnSector(XenSectorPresets.BurntHills)), () -> {});
                });
                node(XenBlocks.calmness, () -> {});
                node(XenBlocks.reinforcedDamascusWall, Seq.with(new Objectives.Produce(XenItems.zinc)), () -> {
                    node(XenBlocks.reinforcedDamascusWallLarge, () -> {
                        node(XenBlocks.diocasiumArmor, Seq.with(new Objectives.Produce(XenItems.diocasium)), () -> {});
                    });
                });
            });
            //Liquid Blocks
            node(XenBlocks.hydraulicPump, Seq.with(new Objectives.Produce(XenItems.gold)), () -> {
                node(XenBlocks.fragilePipeline, () -> {
                    node(XenBlocks.fragileLiquidRouter, () -> {
                        node(XenBlocks.fragileLiquidBridge, Seq.with(new Objectives.Produce(XenItems.zinc)), () -> {});
                    });
                    node(XenBlocks.hardenedPipeline, () -> {
                        node(XenBlocks.reinforcedPipelineJunction, () -> {});
                        node(XenBlocks.hardenedPipelineRouter, () -> {
                            node(XenBlocks.hardenedPipelineBridge, () -> {});
                        });
                    });
                });
                node(XenBlocks.energyPump, () -> {});
            });
            //Power Blocks
            node(XenBlocks.kirmiteEvaporator, Seq.with(new Objectives.Produce(XenItems.zinc)), () -> {
                node(XenBlocks.accumulator, Seq.with(new Objectives.SectorComplete(XenSectorPresets.BurntHills)), () -> {});
                node(XenBlocks.cableNode, () -> {});
            });
            //Production
            node(XenBlocks.pyrometallurgicalInstallation, Seq.with(new Objectives.Produce(XenItems.zinc)), () -> {
                node(XenBlocks.crusher, () -> {
                    node(XenBlocks.waterReformer, Seq.with(new Objectives.SectorComplete(XenSectorPresets.KirmitCoast)), () -> {
                        node(XenBlocks.dantstalinSmelter, Seq.with(new Objectives.OnSector(XenSectorPresets.OrinilFault)), () -> {
                            node(XenBlocks.waterCollerctor, Seq.with(new Objectives.Produce(XenItems.dantstalin)), () -> {});
                        });
                    });
                    node(XenBlocks.orinilCrucible, Seq.with(new Objectives.Produce(XenLiquids.liquidOrinil)), () -> {
                        node(XenBlocks.energyChargingSplitter, () -> {});
                        node(XenBlocks.energyStabilizingBoiler, () -> {});
                    });
                });
            });
            //Unit and Payload Blocks
            node(XenBlocks.groundUnitsAssembler, Seq.with(new Objectives.OnSector(XenSectorPresets.DryingThickets)), () -> {
                node(XenUnits.zanar, Seq.with(new Objectives.Research(XenBlocks.groundUnitsAssembler)), () -> {});

                node(XenBlocks.hoverUnitsAssembler, Seq.with(new Objectives.SectorComplete(XenSectorPresets.DryingThickets)), () -> {
                    node(XenUnits.imitation, Seq.with(new Objectives.Research(XenBlocks.hoverUnitsAssembler)), () -> {});

                    node(XenBlocks.floatingUnitsAssembler, Seq.with(new Objectives.SectorComplete(XenSectorPresets.KirmitCoast)), () -> {
                        node(XenUnits.xanit, Seq.with(new Objectives.Research(XenBlocks.floatingUnitsAssembler)), () -> {});

                        node(XenBlocks.orinilReassembler, () -> {
                            node(XenUnits.inorn, Seq.with(new Objectives.Research(XenBlocks.orinilReassembler)), () -> {});
                            node(XenUnits.simulation, Seq.with(new Objectives.Research(XenBlocks.orinilReassembler)), () -> {});
                            node(XenUnits.manul, Seq.with(new Objectives.Research(XenBlocks.orinilReassembler)), () -> {});
                            node(XenBlocks.thermalReassemblingFactory, () -> {
                                node(XenUnits.manler, Seq.with(new Objectives.Research(XenBlocks.thermalReassemblingFactory)), () -> {});
                                node(XenUnits.fusion, Seq.with(new Objectives.Research(XenBlocks.thermalReassemblingFactory)), () -> {});
                                node(XenUnits.amiren, Seq.with(new Objectives.Research(XenBlocks.thermalReassemblingFactory)), () -> {});
                            });
                    });
                    });
                });
                node(XenBlocks.cargoBelt, Seq.with(new Objectives.Produce(XenItems.zinc)), () -> {
                    node(XenBlocks.cargoRouter, () -> {});
                });
            });
            //Sectors
            node(XenSectorPresets.Landing, () -> {
                node(XenSectorPresets.BurntHills, Seq.with(new Objectives.SectorComplete(XenSectorPresets.Landing)), () -> {
                    node(XenSectorPresets.LightLowland, Seq.with(new Objectives.SectorComplete(XenSectorPresets.BurntHills)), () -> {
                        node(XenSectorPresets.OrinilFault, Seq.with(new Objectives.SectorComplete(XenSectorPresets.LightLowland), new Objectives.Research(XenBlocks.waterReformer), new Objectives.Research(XenBlocks.overflow)), () -> {});
                    });
                });
                node(XenSectorPresets.DryingThickets, Seq.with(new Objectives.SectorComplete(XenSectorPresets.Landing)), () -> {
                    node(XenSectorPresets.KirmitCoast, Seq.with(new Objectives.Research(XenBlocks.hoverUnitsAssembler)), () -> {});
                });
            });
            //Items & Liquids
            nodeProduce(XenItems.damascus, () -> {
                nodeProduce(Items.sand, () -> {
                    nodeProduce(Items.silicon, () -> {});
                });
                nodeProduce(XenItems.zinc, () -> {
                    nodeProduce(XenItems.gold, () -> {
                        nodeProduce(XenLiquids.liquidKirmit, () -> {});
                        nodeProduce(XenItems.protexide, () -> {
                            nodeProduce(Items.tungsten, () -> {});
                        });
                        nodeProduce(XenLiquids.liquidOrinil, () -> {
                            nodeProduce(XenItems.diocasium, () -> {
                                nodeProduce(XenItems.isoteron, () -> {
                                    nodeProduce(XenItems.asteril, () -> {});
                                });
                                nodeProduce(XenItems.moluron, () -> {});
                            });
                        });
                    });
                    nodeProduce(Liquids.water, () -> {
                        nodeProduce(Liquids.hydrogen, () -> {});
                        nodeProduce(XenLiquids.oxygen, () -> {});
                    });
                    nodeProduce(XenItems.dantstalin, () -> {});
                });
            });
            //Cores
            node(XenBlocks.coreZenith, () -> {});
        });
    }
}