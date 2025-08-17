package xenrose.type;

import arc.util.Nullable;
import mindustry.ctype.ContentType;
import mindustry.ctype.UnlockableContent;
import mindustry.type.ItemStack;

import static mindustry.ctype.ContentType.loadout_UNUSED;

public abstract class Modifier extends UnlockableContent {
    public @Nullable ItemStack[] researchCost;

    public Modifier(String name){
        super(name);
    }

    @Override
    public ItemStack[] researchRequirements(){
        if(researchCost != null) return researchCost;

        return this.researchRequirements();
    }

    @Override
    public ContentType getContentType() {
        return loadout_UNUSED;
    }
}
