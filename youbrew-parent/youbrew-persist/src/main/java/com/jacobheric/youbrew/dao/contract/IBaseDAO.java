/**
 * Description:  Base DAO interface, defines the base set of dao operations.
 * 
 * @author: jacob heric
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
package com.jacobheric.youbrew.dao.contract;

import java.io.Serializable;
import java.util.List;

public interface IBaseDAO<T, ID extends Serializable> {
   
   public T insert(T o);
   public T update(T o);
   public void delete(T o);
   public void deleteById(ID id);
   public T findById(ID id);
   public List<T> findByExample(T exampleClass, String[] excludeProperty);
   public List<T> findAll();  

}
