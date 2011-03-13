/**
 * Description:  A base dao class.  It implements the base dao contract, 
 *   consolidates shared dao activity, operates directly on the hibernate session factory.
 *
 * @author jacob heric
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

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.jacobheric.youbrew.dao.contract.IBaseDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author jacob
 */
@Repository("baseDAO")
public abstract class BaseDAOImpl<T, ID extends Serializable> implements IBaseDAO<T, ID> {

	@Autowired
	protected SessionFactory sessionFactory; //injected by Spring container
	@SuppressWarnings("unchecked")
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		this.clazz = (Class<T>)
			  ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
	}

	/**
	 * §
	 *
	 * @return - hibernate sessionfactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessFactory - hibernate session factory
	 *                    Note injection by annotation.
	 */
	public void setSessionFactory(SessionFactory sessFactory) {
		this.sessionFactory = sessFactory;
	}

	/**
	 * @param o - delete a persistent object
	 */
	public void delete(T o) {
		this.sessionFactory.getCurrentSession().delete(o);
	}

	/**
	 * @param o - Extends BaseObject, determined by implementing classes.
	 */
	public T update(T o) {
		return insert(o);
	}

	/**
	 * @param o - Extends BaseObject, determined by implementing classes.
	 */
	public T insert(T o) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(o);
		return o;
	}

	/**
	 * @return List of T
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return (List<T>) this.findByCriteria();
	}

	/**
	 *
	 * @param id - id of entity to delete
	 */
	public void deleteById(ID id) {
		this.delete(this.findById(id));
	}

	/**
	 * @return T - Extends BaseObject, determined by implementing classes.
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public T findById(ID id){
		return (T) this.sessionFactory.getCurrentSession().get(this.clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(this.clazz);
		Example example = Example.create(exampleInstance);
			for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

    /**
     * Convenience criteria method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion...criterion) {
        Criteria crit = this.getSessionFactory().getCurrentSession().createCriteria(this.clazz);
		
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
   }
}
