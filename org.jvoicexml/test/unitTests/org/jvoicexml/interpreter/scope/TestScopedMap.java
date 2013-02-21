/*
 * File:    $HeadURL$
 * Version: $LastChangedRevision$
 * Date:    $Date $
 * Author:  $LastChangedBy$
 *
 * JVoiceXML - A free VoiceXML implementation.
 *
 * Copyright (C) 2005-2009 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Library General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Library General Public License for more details.
 *
 *  You should have received a copy of the GNU Library General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.jvoicexml.interpreter.scope;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link org.jvoicexml.interpreter.scope.ScopedMap}.
 *
 * @see org.jvoicexml.interpreter.scope.ScopedMap
 *
 * @author Dirk Schnelle-Walka
 * @version $Revision$
 */
public final class TestScopedMap {

    /** The VoiceXML interpreter context to use. */
    private ScopeObserver observer;

    /**
     * Set up the test environment.
     * @exception Exception
     *            set up failed
     */
    @Before
    public void setUp()
            throws Exception {
        observer = new ScopeObserver();
    }

    /**
     * Tear down the test environment.
     * @exception Exception
     *            tear down failed
     */
    @After
    public void tearDown()
            throws Exception {
        observer = null;
    }

    /**
     * Test method for
     * 'ScopedMap.get(java.lang.Object).
     *
     * @see ScopedMap#get(java.lang.Object)
     */
    @Test
    public void testGet() {
        final ScopedMap<String, String> map =
                new ScopedMap<String, String>(observer);
        Assert.assertNull(map.get("nokey"));

        map.put("key1", "value1");
        Assert.assertEquals("value1", map.get("key1"));

        map.put("key2", "value2");
        Assert.assertEquals("value1", map.get("key1"));
        Assert.assertEquals("value2", map.get("key2"));

        map.clear();
        Assert.assertNull(map.get("key1"));
        Assert.assertNull(map.get("key2"));
    }

    /**
     * Test method for
     * 'ScopedMap.put(K,V).
     *
     * @see ScopedMap#put(K,V)
     */
    @Test
    public void testPut() {
        final ScopedMap<String, String> map =
                new ScopedMap<String, String>(observer);

        Assert.assertNull(map.put("key1", "value1"));
        Assert.assertEquals("value1", map.get("key1"));

        Assert.assertNull(map.put("key2", "value2"));
        Assert.assertEquals("value1", map.get("key1"));
        Assert.assertEquals("value2", map.get("key2"));

        map.enterScope(Scope.SESSION, Scope.DOCUMENT);
        Assert.assertEquals("value1", map.get("key1"));
        Assert.assertEquals("value2", map.get("key2"));

        Assert.assertEquals("value2", map.put("key2", "value3"));
        Assert.assertEquals("value1", map.get("key1"));
        Assert.assertEquals("value3", map.get("key2"));

        Assert.assertNull(map.put("key3", "value3"));
        Assert.assertEquals("value3", map.get("key3"));

        map.exitScope(Scope.DOCUMENT, Scope.SESSION);
        Assert.assertEquals("value1", map.get("key1"));
        Assert.assertTrue(map.containsKey("key1"));
        Assert.assertEquals("value2", map.get("key2"));
        Assert.assertNull(map.get("key3"));
        Assert.assertFalse(map.containsKey("key3"));
    }

    /**
     * Test method for 'ScopedMap.values().
     *
     * @see ScopedMap#values()
     */
    @Test
    public void testValues() {
        final ScopedMap<String, String> map =
                new ScopedMap<String, String>(observer);

        Assert.assertNull(map.put("key1", "value1"));
        Assert.assertEquals("value1", map.get("key1"));

        Assert.assertNull(map.put("key2", "value2"));
        Assert.assertEquals("value1", map.get("key1"));
        Assert.assertEquals("value2", map.get("key2"));

        final Collection<String> values1 = map.values();
        Assert.assertEquals(2, values1.size());
        Assert.assertTrue(values1.contains("value1"));
        Assert.assertTrue(values1.contains("value2"));

        map.enterScope(Scope.SESSION, Scope.DOCUMENT);
        Assert.assertEquals("value1", map.get("key1"));
        Assert.assertEquals("value2", map.get("key2"));

        final Collection<String> values2 = map.values();
        Assert.assertEquals(2, values2.size());
        Assert.assertTrue(values2.contains("value1"));
        Assert.assertTrue(values2.contains("value2"));

        Assert.assertEquals("value2", map.put("key2", "value3"));
        Assert.assertEquals("value1", map.get("key1"));
        Assert.assertEquals("value3", map.get("key2"));

        final Collection<String> values3 = map.values();
        Assert.assertEquals(2, values3.size());
        Assert.assertTrue(values3.contains("value1"));
        Assert.assertTrue(values3.contains("value3"));

        map.exitScope(Scope.DOCUMENT, Scope.SESSION);
        Assert.assertEquals("value1", map.get("key1"));
        Assert.assertEquals("value2", map.get("key2"));

        final Collection<String> values4 = map.values();
        Assert.assertEquals(2, values4.size());
        Assert.assertTrue(values4.contains("value1"));
        Assert.assertTrue(values4.contains("value2"));
    }
}
