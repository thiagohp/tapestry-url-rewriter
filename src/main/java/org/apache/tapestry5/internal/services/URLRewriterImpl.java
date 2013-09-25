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

import java.util.List;

import org.apache.tapestry5.ioc.annotations.UsesOrderedConfiguration;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.URLRewriter;
import org.apache.tapestry5.urlrewriter.URLRewriterRule;

/**
 * Default {@link org.apache.tapestry5.services.URLRewriter}
 * implementation.
 * 
 * @since 5.1.0.2
 */
@UsesOrderedConfiguration(URLRewriterRule.class)
public class URLRewriterImpl implements URLRewriter {

	private static final String URL_REWRITER_RULE_PROCESS_MUST_NOT_RETURN_NULL = "URLRewriterRule.process() must not return null";
	final private List<URLRewriterRule> rules;

	/**
	 * Single constructor of this class.
	 * 
	 * @param rules
	 *            a <code>List</code> of <code>URLRewriterRule</code>. It cannot
	 *            be null.
	 */
	public URLRewriterImpl(List<URLRewriterRule> rules) {
		assert rules != null;
		this.rules = rules;
	}

	public Request processRequest(Request request) {
		for (URLRewriterRule rule : rules) {
			request = rule.process(request);
			if (request == null) {
				throw new RuntimeException(URL_REWRITER_RULE_PROCESS_MUST_NOT_RETURN_NULL);
			}
		}
		return request;
	}

	public boolean hasRequestRules() {
		return !rules.isEmpty();
	}

}
