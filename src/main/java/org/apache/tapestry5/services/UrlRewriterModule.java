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

package org.apache.tapestry5.services;

import org.apache.tapestry5.internal.services.URLRewriterImpl;
import org.apache.tapestry5.internal.services.URLRewriterRequestFilter;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;

/**
 * Tapestry-IoC module for the URL Rewriter API.
 */
public class UrlRewriterModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(URLRewriter.class, URLRewriterImpl.class);
	}

	/**
	 * Contributes the URL rewriter request filter if there are URL rewriter rules.
	 * @param configuration an {@link OrderedConfiguration}.
	 * @param urlRewriter an {@link URLRewriter}.
	 */
	public void contributeRequestHandler(
			OrderedConfiguration<RequestFilter> configuration,
			URLRewriter urlRewriter) {

		// we just need the URLRewriterRequestFilter if we have URL rewriter
		// rules, of course.
		if (urlRewriter.hasRequestRules()) {

			URLRewriterRequestFilter urlRewriterRequestFilter = new URLRewriterRequestFilter(
					urlRewriter);
			configuration.add("URLRewriter", urlRewriterRequestFilter, "before:StaticFiles");

		}

	}

}
