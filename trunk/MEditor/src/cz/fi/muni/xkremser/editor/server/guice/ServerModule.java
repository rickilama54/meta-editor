package cz.fi.muni.xkremser.editor.server.guice;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import org.apache.commons.logging.Log;

import com.google.inject.Singleton;

import cz.fi.muni.xkremser.editor.server.config.EditorConfiguration;
import cz.fi.muni.xkremser.editor.server.config.EditorConfigurationImpl;
import cz.fi.muni.xkremser.editor.server.handler.ScanInputQueueHandler;
import cz.fi.muni.xkremser.editor.server.handler.SendGreetingHandler;

/**
 * Module which binds the handlers and configurations
 * 
 */
public class ServerModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(SendGreetingHandler.class);
		bindHandler(ScanInputQueueHandler.class);

		bind(Log.class).toProvider(LogProvider.class).in(Singleton.class);
		bind(EditorConfiguration.class).to(EditorConfigurationImpl.class).in(
				Singleton.class);
	}
}