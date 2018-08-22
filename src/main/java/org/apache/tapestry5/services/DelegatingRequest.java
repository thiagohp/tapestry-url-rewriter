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

import java.util.List;
import java.util.Locale;

/**
 * Class that wraps an {@link Request}, delegating all its methods to it.
 * 
 * @since 5.1.0.1
 */
public class DelegatingRequest implements Request {

	private Request request;

	/**
	 * No-arg constructor. It should only be used for testing purposes.
	 */
	public DelegatingRequest() {
	}

	/**
	 * Constructor that receives a {@link Request}.
	 * 
	 * @param request
	 *            a {@link Request}. It cannot be null.
	 */
	public DelegatingRequest(Request request) {
		setRequest(request);
	}

	/**
	 * Sets the delegate request.
	 * 
	 * @param request
	 *            a {@link Request}. It cannot be null.
	 */
	public void setRequest(Request request) {
		assert request != null;
		this.request = request;
	}

	/**
	 * @param create
	 * @return
	 * @see org.apache.tapestry5.services.Request#getSession(boolean)
	 */
	public Session getSession(boolean create) {
		return request.getSession(create);
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#getContextPath()
	 */
	public String getContextPath() {
		return request.getContextPath();
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#getParameterNames()
	 */
	public List<String> getParameterNames() {
		return request.getParameterNames();
	}

	/**
	 * @param name
	 * @return
	 * @see org.apache.tapestry5.services.Request#getParameter(java.lang.String)
	 */
	public String getParameter(String name) {
		return request.getParameter(name);
	}

	/**
	 * @param name
	 * @return
	 * @see org.apache.tapestry5.services.Request#getParameters(java.lang.String)
	 */
	public String[] getParameters(String name) {
		return request.getParameters(name);
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#getPath()
	 */
	public String getPath() {
		return request.getPath();
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#getLocale()
	 */
	public Locale getLocale() {
		return request.getLocale();
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#getHeaderNames()
	 */
	public List<String> getHeaderNames() {
		return request.getHeaderNames();
	}

	/**
	 * @param name
	 * @return
	 * @see org.apache.tapestry5.services.Request#getDateHeader(java.lang.String)
	 */
	public long getDateHeader(String name) {
		return request.getDateHeader(name);
	}

	/**
	 * @param name
	 * @return
	 * @see org.apache.tapestry5.services.Request#getHeader(java.lang.String)
	 */
	public String getHeader(String name) {
		return request.getHeader(name);
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#isXHR()
	 */
	public boolean isXHR() {
		return request.isXHR();
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#isSecure()
	 */
	public boolean isSecure() {
		return request.isSecure();
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#getServerName()
	 */
	public String getServerName() {
		return request.getServerName();
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#isRequestedSessionIdValid()
	 */
	public boolean isRequestedSessionIdValid() {
		return request.isRequestedSessionIdValid();
	}

	/**
	 * @param name
	 * @return
	 * @see org.apache.tapestry5.services.Request#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String name) {
		return request.getAttribute(name);
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#isSessionInvalidated()
	 */
	public List<String> getAttributeNames() {
		return request.getAttributeNames();
	}

	/**
	 * @param name
	 * @param value
	 * @see org.apache.tapestry5.services.Request#setAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	public void setAttribute(String name, Object value) {
		request.setAttribute(name, value);
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#getMethod()
	 */
	public String getMethod() {
		return request.getMethod();
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#getLocalPort()
	 */
	public int getLocalPort() {
		return request.getLocalPort();
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#getServerPort()
	 */
	public int getServerPort() {
		return request.getServerPort();
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#getRemoteHost()
	 */
	public String getRemoteHost() {
		return request.getRemoteHost();
	}

	/**
	 * @return
	 * @see org.apache.tapestry5.services.Request#isSessionInvalidated()
	 */
	public boolean isSessionInvalidated() {
		return request.isSessionInvalidated();
	}

}