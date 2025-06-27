package xenrose.XenContent;

import mindustry.graphics.CacheLayer;

public class XenCacheLayer {

    public static CacheLayer orinil;

    public static void load(){
        CacheLayer.add(
                orinil = new CacheLayer.ShaderLayer(XenShaders.orinil)
        );
    }
}
