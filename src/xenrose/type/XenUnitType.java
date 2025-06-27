package xenrose.type;

import mindustry.gen.EntityMapping;
import mindustry.type.UnitType;

public class XenUnitType extends UnitType{

    public XenUnitType(String name){
        super(name);

        // Try to immediately resolve the Unit constructor based on EntityMapping entry, if it is set.
        // This is the default Vanilla behavior - it won't work properly for mods (see comment in `init()`)!
        constructor = EntityMapping.map(this.name);
        selectionSize = 30f;
    }
}
