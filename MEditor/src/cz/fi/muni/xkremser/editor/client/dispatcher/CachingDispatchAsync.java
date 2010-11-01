/**
 * Metadata Editor
 * @author Jiri Kremser
 *  
 */
package cz.fi.muni.xkremser.editor.client.dispatcher;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.dispatch.client.DispatchRequest;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.Result;

// TODO: Auto-generated Javadoc
// TODO: test
/**
 * The Class CachingDispatchAsync.
 */
public class CachingDispatchAsync implements DispatchAsync {

	/** The m dispatcher. */
	private final DispatchAsync mDispatcher;
	
	/** The m cache. */
	private final Map<Action<Result>, Result> mCache = new HashMap<Action<Result>, Result>();

	/**
	 * Instantiates a new caching dispatch async.
	 *
	 * @param dispatcher the dispatcher
	 */
	@Inject
	public CachingDispatchAsync(final DispatchAsync dispatcher) {
		this.mDispatcher = dispatcher;
	}

	/* (non-Javadoc)
	 * @see com.gwtplatform.dispatch.client.DispatchAsync#execute(A, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	@Override
	public <A extends Action<R>, R extends Result> DispatchRequest execute(final A action, final AsyncCallback<R> callback) {
		return mDispatcher.execute(action, callback);
	}

	/**
	 * Execute with cache.
	 *
	 * @param <A> the generic type
	 * @param <R> the generic type
	 * @param action the action
	 * @param callback the callback
	 */
	@SuppressWarnings("unchecked")
	public <A extends Action<R>, R extends Result> void executeWithCache(final A action, final AsyncCallback<R> callback) {
		final Result r = mCache.get(action);

		if (r != null) {
			callback.onSuccess((R) r);
		} else {
			execute(action, new AsyncCallback<R>() {

				@Override
				public void onFailure(Throwable caught) {
					callback.onFailure(caught);
				}

				@Override
				public void onSuccess(R result) {
					mCache.put((Action<Result>) action, (Result) result);
					callback.onSuccess(result);
				}

			});
		}
	}

	/**
	 * Clear.
	 */
	public void clear() {
		mCache.clear();
	}

	/* (non-Javadoc)
	 * @see com.gwtplatform.dispatch.client.DispatchAsync#undo(A, R, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	@Override
	public <A extends Action<R>, R extends Result> DispatchRequest undo(A action, R result, AsyncCallback<Void> callback) {
		// TODO Auto-generated method stub
		return null;
	}
}
