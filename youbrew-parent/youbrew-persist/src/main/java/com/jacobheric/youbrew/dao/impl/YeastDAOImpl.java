/*
 * Description: Yeast DAO, implements the yeast dao interface & extends the baseDao
 * 
 * @author: jacob heric
 * @date: 1/27/2010
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
package com.jacobheric.youbrew.dao.impl;

import com.jacobheric.youbrew.dao.contract.IYeastDAO;
import com.jacobheric.youbrew.domain.Yeast;
import org.springframework.stereotype.Repository;

import java.util.List;
//
//PersistenceExceptions will be auto-translated due to @Repository
@Repository("yeastDAO")
public class YeastDAOImpl extends BaseDAOImpl<Yeast, Long> implements IYeastDAO {

}
