// Copyright 2009 The Apache Software Foundation
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package org.apache.tapestry5.urlrewriter;

import org.apache.tapestry5.services.DelegatingRequest;
import org.apache.tapestry5.services.Request;

/**
 * Class that wraps a {@linkplain Request}. It delegates all methods except ones
 * related to URL rewriting.
 */
public class SimpleRequestWrapper extends DelegatingRequest {

	final private String path;

	final private String serverName;

	/**
	 * Constructor that receives a request, a server name and a path.
	 * 
	 * @param request
	 *            a {@link Request}. It cannot be null.
	 * @param serverName
	 *            a {@link String}.
	 * @param path
	 *            a {@link String}. It cannot be null.
	 */
	public SimpleRequestWrapper(Request request, String serverName, String path) {

		super(request);
		assert request != null;
		if (serverName == null) {
			this.serverName = request.getServerName();
		} else {
			this.serverName = serverName;
		}
		this.path = path;

	}

	/**
	 * Constructor that receives a request and a path. The server name used is
	 * got from the request.
	 * 
	 * @param request
	 *            a {@link Request}. It cannot be null.
	 * @param path
	 *            a {@link String}. It cannot be null.
	 */
	public SimpleRequestWrapper(Request request, String path) {
		this(request, null, path);
	}

	public String getPath() {
		return path;
	}

	public String getServerName() {
		return serverName;
	}

}
