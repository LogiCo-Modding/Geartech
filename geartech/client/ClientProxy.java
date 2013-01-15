package gearteam.geartech.client;

import gearteam.geartech.CommonProxy;
import gearteam.geartech.gear.client.ClientProxyGear;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderInformation() {
        ClientProxyGear.registerRenderInformation();
    }
    
    @Override
    public void registerTiles() {
        ClientProxyGear.registerTiles();
    }
}
