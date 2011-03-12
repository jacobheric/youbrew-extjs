
/**
 * @author: jacob
 * @created: Oct 25, 2010
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
package com.jacobheric.youbrew.service.impl;

import com.jacobheric.youbrew.dao.contract.IHopDAO;
import com.jacobheric.youbrew.dao.contract.IYeastDAO;
import com.jacobheric.youbrew.domain.Hop;
import com.jacobheric.youbrew.domain.Yeast;
import com.jacobheric.youbrew.service.contract.IYeastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("yeastService")
public class YeastServiceImpl implements IYeastService {

	@Autowired
	private IYeastDAO yeastDAO;

	public Yeast insert(Yeast yeast) {
		return (Yeast) this.yeastDAO.insert(yeast);
	}

	public Yeast update(Yeast y) {
		return (Yeast) this.yeastDAO.update(y);
	}

	public void delete(Yeast y) {
		this.yeastDAO.delete(y);
	}

	public void deleteById(Long id) {
		this.delete(this.findById(id));
	}

	public Yeast findById(Long id) {
		 return (Yeast) this.yeastDAO.findById(id);
	}

	public List<Yeast> findByName(String name) {
		return this.findByExample(new Yeast(name), new String[]{"id", "attenuation", "description"});
	}

	public List<Yeast> findByExample(Yeast exampleClass, String[] excludeProperty) {
		return (List<Yeast>) this.yeastDAO.findByExample(exampleClass, excludeProperty);
	}

	public List<Yeast> findAll() {
		return (List<Yeast>) this.yeastDAO.findAll();
	}

}
