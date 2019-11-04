package com.tbkk.yoyo.rpc.net;

import com.tbkk.yoyo.rpc.net.exchange.BaseCallback;
import lombok.extern.slf4j.Slf4j;

/**
 * server
 *
 * @author tbkk 2019-11-24 20:59:49
 */

@Slf4j
public abstract class Server {


	private BaseCallback startedCallback;
	private BaseCallback stopedCallback;

	public void setStartedCallback(BaseCallback startedCallback) {
		this.startedCallback = startedCallback;
	}

	public void setStopedCallback(BaseCallback stopedCallback) {
		this.stopedCallback = stopedCallback;
	}


	/**
	 * start server
	 *
	 * @throws Exception
	 */
	public abstract void start(int port) throws Exception;

	/**
	 * callback when started
	 */
	public void startCallBack() {
		if (startedCallback != null) {
			try {
				startedCallback.run();
			} catch (Exception e) {
				log.error("yoyo-rpc, server startedCallback error.", e);
			}
		}
	}

	/**
	 * stop server
	 *
	 * @throws Exception
	 */
	public abstract void stop() throws Exception;

	/**
	 * callback when stoped
	 */
	public void stopCallBack() {
		if (stopedCallback != null) {
			try {
				stopedCallback.run();
			} catch (Exception e) {
				log.error("yoyo-rpc, server stopedCallback error.", e);
			}
		}
	}

}
