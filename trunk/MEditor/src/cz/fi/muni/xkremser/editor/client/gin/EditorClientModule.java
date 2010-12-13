/**
 * Metadata Editor
 * @author Jiri Kremser
 *  
 */
package cz.fi.muni.xkremser.editor.client.gin;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.DefaultEventBus;
import com.gwtplatform.mvp.client.DefaultProxyFailureHandler;
import com.gwtplatform.mvp.client.EventBus;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

import cz.fi.muni.xkremser.editor.client.EditorPlaceManager;
import cz.fi.muni.xkremser.editor.client.NameTokens;
import cz.fi.muni.xkremser.editor.client.config.EditorClientConfiguration;
import cz.fi.muni.xkremser.editor.client.config.EditorClientConfigurationImpl;
import cz.fi.muni.xkremser.editor.client.dispatcher.CachingDispatchAsync;
import cz.fi.muni.xkremser.editor.client.presenter.AppPresenter;
import cz.fi.muni.xkremser.editor.client.presenter.DigitalObjectMenuPresenter;
import cz.fi.muni.xkremser.editor.client.presenter.HomePresenter;
import cz.fi.muni.xkremser.editor.client.presenter.ModifyPresenter;
import cz.fi.muni.xkremser.editor.client.presenter.UserPresenter;
import cz.fi.muni.xkremser.editor.client.view.AppView;
import cz.fi.muni.xkremser.editor.client.view.DigitalObjectMenuView;
import cz.fi.muni.xkremser.editor.client.view.HomeView;
import cz.fi.muni.xkremser.editor.client.view.ModifyView;
import cz.fi.muni.xkremser.editor.client.view.UserView;

// TODO: Auto-generated Javadoc
/**
 * The Class EditorClientModule.
 */
public class EditorClientModule extends AbstractPresenterModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.inject.client.AbstractGinModule#configure()
	 */
	@Override
	protected void configure() {
		// Singletons
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
		bind(PlaceManager.class).to(EditorPlaceManager.class).in(Singleton.class);
		bind(RootPresenter.class).asEagerSingleton();
		bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class).in(Singleton.class);

		// Constants
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.HOME);

		// Presenters
		bindPresenter(AppPresenter.class, AppPresenter.MyView.class, AppView.class, AppPresenter.MyProxy.class);
		bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);
		bindPresenter(UserPresenter.class, UserPresenter.MyView.class, UserView.class, UserPresenter.MyProxy.class);
		bindPresenter(ModifyPresenter.class, ModifyPresenter.MyView.class, ModifyView.class, ModifyPresenter.MyProxy.class);
		bindPresenter(DigitalObjectMenuPresenter.class, DigitalObjectMenuPresenter.MyView.class, DigitalObjectMenuView.class,
				DigitalObjectMenuPresenter.MyProxy.class);
		// bindPresenterWidget(DigitalObjectMenuPresenter.class,
		// DigitalObjectMenuPresenter.MyView.class,
		// DigitalObjectMenuView.class);

		bind(CachingDispatchAsync.class);
		bind(EditorClientConfiguration.class).to(EditorClientConfigurationImpl.class);

	}
}