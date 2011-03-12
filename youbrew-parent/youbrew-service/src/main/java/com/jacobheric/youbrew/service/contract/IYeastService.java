/*
 * Description: Yeast service interface, adds specificity to base service. 
 * 
 * @author: jacob heric
 * @created: 1/27/2010
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
package com.jacobheric.youbrew.service.contract;

import com.jacobheric.youbrew.domain.Yeast;

import java.util.List;

public interface IYeastService {

	public Yeast insert(Yeast yeast);

	public Yeast update(Yeast y);

	public void delete(Yeast y);

	public void deleteById(Long id);

	public Yeast findById(Long id);

	public List<Yeast> findByName(String name);	

	public List<Yeast> findByExample(Yeast exampleClass, String[] excludeProperty);

	public List<Yeast> findAll();
	
}
