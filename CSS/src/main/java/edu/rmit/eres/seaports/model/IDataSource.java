/**
 * Copyright (c) 2014, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package edu.rmit.eres.seaports.model;

import java.util.List;

/**
 * Interface defining the method any source of data should implement
 * @author Guillaume Prevost
 * @since 21th Jan. 2014
 */
public interface IDataSource {
	
	public void init(Object obj);
	
	public void flush();
	
	public int getId();
	public void setId(int id);
	
	public String getName();
	public void setName(String name);
	
	public List<DataSourceParameter> getParameters();
	public void setParameters(List<DataSourceParameter> parameters);
	
	public List<DisplayType> getDisplayTypes();
	public void setDisplayTypes(List<DisplayType> displayTypes);
	
	public List<Seaport> getSeaports();
	public void setSeaports(List<Seaport> seaports);
	
	public List<ElementCategory> getCategories();
	public void setCategories(List<ElementCategory> categories);
	
	public List<?> getData(DataElement dataElement);
}
