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

import java.util.HashMap;
import java.util.Map;

import net.sf.hajdbc.Balancer;
import net.sf.hajdbc.Messages;

/**
 * @author  Paul Ferraro
 * @since   1.1
 */
public final class BalancerFactory
{
	/**
	 * Creates a new instance of the Balancer implementation indentified by the specified identifier
	 * @param id an enumerated balancer identifier
	 * @return a new Balancer instance
	 */
	public static Balancer createBalancer(String id)
	{
		Map map = new HashMap();
		
		map.put("simple", SimpleBalancer.class);
		map.put("random", RandomBalancer.class);
		map.put("round-robin", RoundRobinBalancer.class);
		map.put("load", LoadBalancer.class);

		Class balancerClass = (Class) map.get(id);
		
		if (balancerClass == null)
		{
			throw new IllegalArgumentException(Messages.getMessage(Messages.INVALID_BALANCER, id));
		}
		
		try
		{
			return (Balancer) balancerClass.newInstance();
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	private BalancerFactory()
	{
		// Hide constructor
	}
}
