/*
 * Description: Hop DAO, implements the hop dao interface & extends the baseDao
 * 
 * @author: jacob heric
 * Date: 1/27/2010
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

import com.jacobheric.youbrew.dao.contract.IHopDAO;
import com.jacobheric.youbrew.dao.contract.IYeastDAO;
import com.jacobheric.youbrew.domain.Hop;
import com.jacobheric.youbrew.domain.Yeast;
import org.springframework.stereotype.Repository;

@Repository("hopDAO")
public class HopDAOImpl extends BaseDAOImpl<Hop, Long> implements IHopDAO {

}
