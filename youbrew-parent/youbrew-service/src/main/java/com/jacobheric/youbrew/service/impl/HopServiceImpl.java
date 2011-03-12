
/**
 * @author: jacob
 * @date: Oct 25, 2010
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
package com.jacobheric.youbrew.service.impl;

import com.jacobheric.youbrew.dao.contract.IHopDAO;
import com.jacobheric.youbrew.dao.contract.IRecipeDAO;
import com.jacobheric.youbrew.domain.Hop;
import com.jacobheric.youbrew.service.contract.IHopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("hopService")
public class HopServiceImpl implements IHopService {

	@Autowired
	IHopDAO hopDAO;
	
	public Hop insert(Hop hop) {
		return (Hop) this.hopDAO.insert(hop);
	}

	public Hop update(Hop o) {
		return (Hop) this.hopDAO.update(o);
	}

	public void delete(Hop o) {
		this.hopDAO.delete(o);
	}

	public void deleteById(Long id) {
		this.delete(this.findById(id));
	}

	public Hop findById(Long id) {
		 return (Hop) this.hopDAO.findById(id);
	}

	public List<Hop> findByExample(Hop exampleClass, String[] excludeProperty) {
		return (List<Hop>) this.hopDAO.findByExample(exampleClass, excludeProperty);
	}

	public List<Hop> findAll() {
		return (List<Hop>) this.hopDAO.findAll();
	}


}
