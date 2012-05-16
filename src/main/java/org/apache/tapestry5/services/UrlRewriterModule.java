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

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.tapestry5.internal.services.ComponentEventLinkEncoderMethodAdvice;
import org.apache.tapestry5.internal.services.URLRewriterImpl;
import org.apache.tapestry5.internal.services.URLRewriterRequestFilter;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.services.AspectDecorator;
import org.apache.tapestry5.ioc.services.AspectInterceptorBuilder;

/**
 * Tapestry-IoC module for the URL Rewriter API.
 */
public class UrlRewriterModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(URLRewriter.class, URLRewriterImpl.class);
	}

	public void contributeRequestHandler(
			OrderedConfiguration<RequestFilter> configuration,
			URLRewriter urlRewriter) {

		// we just need the URLRewriterRequestFilter if we have URL rewriter
		// rules, of course.
		if (urlRewriter.hasRequestRules()) {

			URLRewriterRequestFilter urlRewriterRequestFilter = new URLRewriterRequestFilter(
					urlRewriter);
			configuration.add("URLRewriter", urlRewriterRequestFilter,
					"before:StaticFiles");

		}

	}

	/**
	 * @throws Exception
	 * @since 5.1.0.2
	 */
	public static ComponentEventLinkEncoder decorateComponentEventLinkEncoder(
			ComponentEventLinkEncoder encoder, URLRewriter urlRewriter,
			Request request, HttpServletRequest httpServletRequest,
			Response response, AspectDecorator aspectDecorator,
			ContextPathEncoder contextPathEncoder, BaseURLSource baseURLSource)
			throws Exception {

		// no rules, no link rewriting.
		if (!urlRewriter.hasLinkRules()) {
			return null;
		}

		ComponentEventLinkEncoderMethodAdvice pageLinkAdvice = new ComponentEventLinkEncoderMethodAdvice(
				urlRewriter, request, httpServletRequest, response, true,
				contextPathEncoder, baseURLSource);

		ComponentEventLinkEncoderMethodAdvice eventLinkAdvice = new ComponentEventLinkEncoderMethodAdvice(
				urlRewriter, request, httpServletRequest, response, false,
				contextPathEncoder, baseURLSource);

		Class<ComponentEventLinkEncoder> clasz = ComponentEventLinkEncoder.class;

		Method createPageRenderLink = clasz.getMethod("createPageRenderLink",
				PageRenderRequestParameters.class);

		Method createComponentEventLink = clasz.getMethod(
				"createComponentEventLink",
				ComponentEventRequestParameters.class, boolean.class);

		final AspectInterceptorBuilder<ComponentEventLinkEncoder> builder = aspectDecorator
				.createBuilder(clasz, encoder, "Link rewriting");

		builder.adviseMethod(createComponentEventLink, eventLinkAdvice);
		builder.adviseMethod(createPageRenderLink, pageLinkAdvice);

		return builder.build();

	}

}
