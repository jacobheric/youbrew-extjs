/**
 * Description: Recipe Controller
 * @author Jacob Heric
 * October, 25 2010
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
package com.jacobheric.youbrew.controller;

import com.jacobheric.youbrew.dao.criteria.RecipeCriteria;
import com.jacobheric.youbrew.domain.Recipe;
import com.jacobheric.youbrew.domain.Yeast;
import com.jacobheric.youbrew.service.contract.IRecipeService;
import com.jacobheric.youbrew.service.contract.IYeastService;
import com.jacobheric.youbrew.view.DeleteInput;
import com.jacobheric.youbrew.view.RecipeListInput;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value="/recipe")
public class RecipeController extends BaseController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RecipeController.class);

	@Autowired
	private IRecipeService recipeService;

	@Autowired
	private IYeastService yeastService;

	/**
	 * Default constructor
	 */
	public RecipeController() {
	}

	@RequestMapping(method = {RequestMethod.GET})
	public @ResponseBody ServiceResultWrapper<Recipe> SearchRecipes(@ModelAttribute RecipeCriteria criteria)
	{
		return new ServiceResultWrapper(this.recipeService.search(criteria), true, "Search successful", criteria.getTotal());
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ServiceResultWrapper<List<Recipe>> addRecipes(@RequestBody RecipeListInput recipeListInput)
	{

		List<Recipe> recipes = recipeListInput.getItems();
		//
		//some reasonable defaults
		ServiceResultWrapper<List<Recipe>> result = new ServiceResultWrapper<List<Recipe>>(null, true, "Recipe(s) saved successfully", 0);

		//
		//Validate this entity
		this.validateEntities(result, recipes);

		if (!result.getSuccess()){
			return result;
		}

		try {

			//
			//Must send back all data including generated PK for UI grids to synch up
			for (Recipe r : recipes){
				//
				//Load the recipe from db
				if (!StringUtils.isEmpty(r.getYeastName())){
					r.setYeast(this.yeastService.findByName(r.getYeastName()).get(0));
				}
				recipeService.insert(r);
			}

			result.setItem(recipes);

		}
		catch(Exception e){
			log.error("error", e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			result.setItem(recipes);
		}

		return result;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody ServiceResultWrapper<Void> updateRecipes(@RequestBody RecipeListInput recipeListInput)
	{

		List<Recipe> recipes = recipeListInput.getItems();
		//
		//some reasonable defaults
		ServiceResultWrapper<Void> result = new ServiceResultWrapper<Void>(null, true, "Recipe(s) updated successfully", 0);

		//
		//Validate this entity
		this.validateEntities(result, recipes);

		if (!result.getSuccess()){
			return result;
		}

		try {

			for (Recipe r : recipes){
				//
				//Load the recipe from db
				if (!StringUtils.isEmpty(r.getYeastName())){
					r.setYeast(this.yeastService.findByName(r.getYeastName()).get(0));
				}
				
				recipeService.update(r);
			}

		}
		catch(Exception e){
			log.error("error", e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());			
		}

		return result;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody ServiceResultWrapper<Void> deleteRecipes(@RequestBody DeleteInput delInput)
	{
		//
		//some reasonable defaults
		ServiceResultWrapper<Void> result = new ServiceResultWrapper<Void>(null, true, "Recipe(s) deleted successfully", 0);

		try {

			for (Long id : delInput.getItems()){
				recipeService.deleteById(id);
			}

		}
		catch(Exception e){
			log.error("error", e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}

		return result;
	}

	/**
	 * TODO:  move to yeast controller
	 * @return list of available yeasts
	 */
	@RequestMapping(value = "/yeast", method = RequestMethod.GET)
	public @ResponseBody ServiceResultWrapper<Yeast> getYeasts()
	{
		List<Yeast> yeasties = this.yeastService.findAll();
		return new ServiceResultWrapper(yeasties, true, "", yeasties.size());
	}

}