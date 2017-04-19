package com.epam.booklibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.epam.booklibrary.handler.Handler;
import com.epam.booklibrary.method.Request;
import com.epam.booklibrary.utils.MatcherUtils;
import com.epam.booklibrary.handler.Handler;
import com.epam.booklibrary.handler.IHandle;
import com.epam.booklibrary.handler.imphandler.DefHandler;

import com.epam.booklibrary.method.Response;


public class Server {

	private final List<Handler> handlers = new ArrayList<Handler>();
	private ExecutorService pool;
	private int port;
	private int sizeOfTreadPool;

	public Server() {
	}

	public Server(int port, int sizeOfThreadPool) {
		this.port = port;
		this.sizeOfTreadPool = sizeOfThreadPool;
	}

	@SuppressWarnings("resource")
	public void start() throws IOException {

		pool = Executors.newFixedThreadPool(sizeOfTreadPool);
		ServerSocket serSocket = new ServerSocket(port);
		while (true) {
			Socket sock = serSocket.accept();
			pool.submit(new SocketProcessor(sock));
		}
	}

	public void stop() {
		pool.shutdown();
	}

	public Handler findHendler(Request rq) throws IOException {
		Handler defHandler = new Handler(null, null, new DefHandler());
		String metnodFromRequest = rq.getMethod();
		String pathFromRequest = rq.getPath();

		for (Handler handler : handlers) {
			if (metnodFromRequest.equals(handler.getMethod()) && MatcherUtils.isMatches(handler.getUri(), pathFromRequest)) {
				return handler;
			}
		}
		return defHandler;
	}

	public void addHendler(String method, String path, IHandle handler) {
		handlers.add(new Handler(method, path, handler));
	}

	private class SocketProcessor implements Runnable {

		private Request rq;
		private Response rp;
		private Socket socket;

		public SocketProcessor(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				rq = new Request(new BufferedReader(new InputStreamReader(socket.getInputStream())));
				rp = new Response(socket.getOutputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {

				Handler handlerForInvoke = findHendler(rq);
				handlerForInvoke.getiHandle().handle(rq, rp);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
