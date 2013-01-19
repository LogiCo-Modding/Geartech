package logico.geartech.client;

import logico.geartech.CommonProxy;
import logico.geartech.gear.client.ClientProxyGear;

public class ClientProxy extends CommonProxy {

	@Override public void registerRenderInformation () {

		ClientProxyGear.registerRenderInformation();

	}

	@Override public void registerTiles () {

		ClientProxyGear.registerTiles();

	}

}