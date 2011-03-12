/*
 * Description: Recipe service interface contract. 
 * 
 * @author: jacob
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
 *
 */
package com.jacobheric.youbrew.service.contract;

import com.jacobheric.youbrew.dao.criteria.RecipeCriteria;
import com.jacobheric.youbrew.domain.Recipe;

import java.util.List;

public interface IRecipeService {

	public Recipe insert(Recipe recipe);

	public Recipe update(Recipe recipe);

	public void delete(Recipe recipe);

	public void deleteById(Long id);

	public Recipe findById(Long id);

	public List<Recipe> findByExample(Recipe exampleClass, String[] excludeProperty);

	public List<Recipe> findAll();

	/**
	 * @param criteria - the recipe criteria object
	 * @return - list of found recipes
	 */
	public List<Recipe> search(RecipeCriteria criteria);
	
}
