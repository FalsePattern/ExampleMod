package com.example.mymod.core;

import com.example.mymod.Tags;
import com.falsepattern.deploader.DeploaderStub;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.TransformerExclusions(Tags.ROOT_PKG + ".core")
public class CoreLoadingPlugin implements IFMLLoadingPlugin {
    //Deploader MUST be called from coremod static initializer
    static {
        //The intended sequence
        //The bootstrap parameter is always FALSE, unless you're calling it from a RetroFuturaBootstrap plugin!
        DeploaderStub.bootstrap(false);
        DeploaderStub.runDepLoader();
    }
    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
