package br.com.aexo.nimbleway.client;

import java.util.ServiceLoader;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aexo.nimbleway.core.WampConnection;
import br.com.aexo.nimbleway.core.subprotocols.SubProtocol;

/**
 * class responsible for client functionality
 * 
 * @author carlosr
 *
 */
public class WampClient {

	private static Logger log = LoggerFactory.getLogger(WampClient.class);

	private WampConnection connection;

	private Consumer<ClientSession> onOpenCallback = (session) -> {
	};

	private Consumer<Exception> exceptionHandler = (Exception) -> {
	};

	/**
	 * create a wamp client instance using a WampConnection
	 * 
	 * @param connection
	 */
	public WampClient(WampConnection connection) {
		this.connection = connection;
	}

	/**
	 * callback called of wamp handshake complete successiful on router
	 * 
	 * @param onOpenCallback
	 */
	public void onOpen(Consumer<ClientSession> onOpenCallback) {
		this.onOpenCallback = onOpenCallback;
	}

	/**
	 * connect to router in especific realm
	 * 
	 * @param realm
	 */
	public void open(String realm) {
		log.debug("open connection to router");

		ServiceLoader<SubProtocol> subProtocols = ServiceLoader.load(SubProtocol.class);
		connection.onException(exceptionHandler);

		connection.onOpen((transport) -> {
			ClientSession session = new ClientSession(transport, onOpenCallback, exceptionHandler);
			session.open(realm);
		});

		connection.open(subProtocols.iterator());
	}

	public void onException(Consumer<Exception> exceptionHandler) {
		log.trace("configured on exception handler");
		this.exceptionHandler = exceptionHandler;
	}

}
