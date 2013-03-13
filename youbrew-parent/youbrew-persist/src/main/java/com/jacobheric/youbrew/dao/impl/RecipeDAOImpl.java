/*
 * Description: Recipe DAO, implements the recipe dao interface & extends the baseDao
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
 *
 */
package com.jacobheric.youbrew.dao.impl;


import com.jacobheric.youbrew.dao.contract.IRecipeDAO;
import com.jacobheric.youbrew.dao.criteria.RecipeCriteria;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jacobheric.youbrew.domain.Recipe;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

//PersistenceExceptions will be auto-translated due to @Repository

@Repository("recipeDAO")
public class RecipeDAOImpl extends BaseDAOImpl<Recipe, Long> implements IRecipeDAO {

	/**
	 * @param recipeCriteria - custom criteria object
	 * @return list of recipe criteria
	 */
	@SuppressWarnings("unchecked")
	public List<Recipe> search(RecipeCriteria recipeCriteria) {

		Criteria c = this.getSessionFactory().getCurrentSession().createCriteria(Recipe.class);

		//
		//Property restrictions
		if (recipeCriteria.getQuery() != null) {
			Disjunction d = Restrictions.disjunction();

			d.add(Restrictions.like("name", recipeCriteria.getQuery().trim(), MatchMode.ANYWHERE));
			d.add(Restrictions.like("tasteNotes", recipeCriteria.getQuery().trim(), MatchMode.ANYWHERE));
			d.add(Restrictions.like("brewNotes", recipeCriteria.getQuery().trim(), MatchMode.ANYWHERE));

			c.add(d);
		}

		//
		//Determine the total before limiting (useful for paging)
		c.setProjection(Projections.rowCount());
		recipeCriteria.setTotal(((Integer) c.uniqueResult()).intValue());
		c.setProjection(null);
		c.setResultTransformer(Criteria.ROOT_ENTITY);
		

		//
		//Start & limit grid page restrictions
		if (recipeCriteria.getStart() != null){
			c.setFirstResult(recipeCriteria.getStart());
		}

		if (recipeCriteria.getLimit() != null){
			c.setMaxResults(recipeCriteria.getLimit());
		}

		

		return c.list();
	}	

}
