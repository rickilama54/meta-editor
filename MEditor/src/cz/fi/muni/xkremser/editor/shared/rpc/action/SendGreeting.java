/*
 * Metadata Editor
 * @author Jiri Kremser
 * 
 * 
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Jiri Kremser (kremser@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */
package cz.fi.muni.xkremser.editor.shared.rpc.action;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

import cz.fi.muni.xkremser.editor.shared.rpc.result.SendGreetingResult;

// TODO: Auto-generated Javadoc
/**
 * The Class SendGreeting.
 */
public class SendGreeting extends UnsecuredActionImpl<SendGreetingResult> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5804421607858017477L;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new send greeting.
	 */
	@SuppressWarnings("unused")
	private SendGreeting() {
	}

	/**
	 * Instantiates a new send greeting.
	 *
	 * @param name the name
	 */
	public SendGreeting(final String name) {
		this.name = name;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}