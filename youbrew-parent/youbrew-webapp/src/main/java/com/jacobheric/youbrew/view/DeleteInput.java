package com.jacobheric.youbrew.view;
/**
 * Description:  a dumb wrapper for returning lists to the view in a format that ext js likes.
 * @author: jacob heric
 * @created: 3/1/2011
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   see <http://www.gnu.org/licenses/>.
 */
import java.util.List;

/**
 * A simple wrapper to encapsulate incoming serializabe entity ids for deletion.
 * User: jacob
 * Date: Jan 21, 2011
 */

public class DeleteInput {
	List<Long> items;

	public List<Long> getItems() {
		return items;
	}

	public void setItems(List<Long> items) {
		this.items = items;
	}
}
