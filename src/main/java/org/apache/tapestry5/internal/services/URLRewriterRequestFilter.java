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
package org.apache.tapestry5.internal.services;

import java.io.IOException;

import org.apache.tapestry5.http.services.Request;
import org.apache.tapestry5.http.services.RequestFilter;
import org.apache.tapestry5.http.services.RequestHandler;
import org.apache.tapestry5.http.services.Response;
import org.apache.tapestry5.services.URLRewriter;

/**
 * {@link RequestFilter} that applies the URL rewriting rules to requests.
 */
public class URLRewriterRequestFilter implements RequestFilter {

	final private URLRewriter urlRewriter;

	/**
	 * Single constructor of this class.
	 * 
	 * @param urlRewriter an {@link URLRewriter}. It cannot be null.
	 */
	public URLRewriterRequestFilter(URLRewriter urlRewriter) {
		assert urlRewriter != null;
		this.urlRewriter = urlRewriter;
	}

	public boolean service(Request request, Response response,
			RequestHandler handler) throws IOException {

		request = urlRewriter.processRequest(request);

		return handler.service(request, response);

	}

}