/*
 * HA-JDBC: High-Availability JDBC
 * Copyright (c) 2004-2006 Paul Ferraro
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation; either version 2.1 of the License, or (at your 
 * option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Contact: ferraro@users.sourceforge.net
 */
package net.sf.hajdbc.balancer;

import java.util.Collection;
import java.util.Collections;

import net.sf.hajdbc.Balancer;
import net.sf.hajdbc.Database;

/**
 * Abstract balancer implementation that implements most of the Balancer interface.
 * 
 * @author  Paul Ferraro
 * @since   1.0
 */
public abstract class AbstractBalancer implements Balancer
{
	/**
	 * @see net.sf.hajdbc.Balancer#beforeOperation(net.sf.hajdbc.Database)
	 */
	public void beforeOperation(Database database)
	{
		// Do nothing
	}
	
	/**
	 * @see net.sf.hajdbc.Balancer#afterOperation(net.sf.hajdbc.Database)
	 */
	public void afterOperation(Database database)
	{
		// Do nothing
	}
	
	/**
	 * @see net.sf.hajdbc.Balancer#remove(net.sf.hajdbc.Database)
	 */
	public boolean remove(Database database)
	{
		return this.getDatabases().remove(database);
	}
	
	/**
	 * @see net.sf.hajdbc.Balancer#add(net.sf.hajdbc.Database)
	 */
	public boolean add(Database database)
	{
		return (this.contains(database)) ? this.getDatabases().add(database) : false;
	}
	
	/**
	 * @see net.sf.hajdbc.Balancer#contains(net.sf.hajdbc.Database)
	 */
	public boolean contains(Database database)
	{
		return this.getDatabases().contains(database);
	}
	
	/**
	 * @see net.sf.hajdbc.Balancer#first()
	 */
	public Database first()
	{
		return this.all().iterator().next();
	}
	
	/**
	 * @see net.sf.hajdbc.Balancer#all()
	 */
	public final Collection<Database> all()
	{
		return Collections.unmodifiableCollection(this.getDatabases());
	}
	
	protected abstract Collection<Database> getDatabases();
}