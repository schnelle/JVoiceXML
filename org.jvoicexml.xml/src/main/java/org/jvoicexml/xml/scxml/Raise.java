/*
 * JVoiceXML - A free VoiceXML implementation.
 *
 * Copyright (C) 2012-2018 JVoiceXML group - http://jvoicexml.sourceforge.net
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
 *
 */

package org.jvoicexml.xml.scxml;

import java.util.ArrayList;
import java.util.Collection;

import org.jvoicexml.xml.XmlNode;
import org.jvoicexml.xml.XmlNodeFactory;
import org.w3c.dom.Node;

/**
 * The <code>&lt;raise&gt;</code> element raises an event in the current SCXML
 * session. Note that the event will not be processed until the current block of
 * executable content has completed and all events that are already in the
 * internal event queue have been processed. For example, suppose the
 * <code>&lt;raise&gt;</code> element occurs first in the
 * <code>&lt;onentry&gt;</code> handler of state <code>S</code> followed by
 * executable content elements <code>ec1</code> and <code>ec2</code>. If event
 * <code>e1</code> is already in the internal event queue when <code>S</code> is
 * entered, the event generated by {@code <raise>} will not be processed until
 * <code>ec1</code> and <code>ec2</code> have finished execution and
 * <code>e1</code> has been processed.
 *
 * @author Dirk Schnelle-Walka
 * @version $Revision$
 * @since 0.7.6
 */
public final class Raise
        extends AbstractScxmlNode {

    /** Name of the tag. */
    public static final String TAG_NAME = "raise";

    /**
     * Specifies the name of the event. This will be matched against the 'event'
     * attribute of transitions.
     */
    private static final String ATTRIBUTE_EVENT = "event";

    /**
     * Supported attribute names for this node.
     */
    protected static final ArrayList<String> ATTRIBUTE_NAMES;

    /**
     * Set the valid attributes for this node.
     */
    static {
        ATTRIBUTE_NAMES = new java.util.ArrayList<String>();

        ATTRIBUTE_NAMES.add(ATTRIBUTE_EVENT);
    }

    /**
     * Construct a new final object without a node.
     * <p>
     * This is necessary for the node factory.
     * </p>
     *
     * @see org.jvoicexml.xml.scxml.ScxmlNodeFactory
     */
    public Raise() {
        super(null);
    }

    /**
     * Construct a new final object.
     * @param node The encapsulated node.
     */
    Raise(final Node node) {
        super(node);
    }

    /**
     * Constructs a new node.
     *
     * @param n
     *            The encapsulated node.
     * @param factory
     *            The node factory to use.
     */
    private Raise(final Node n,
            final XmlNodeFactory<? extends XmlNode> factory) {
        super(n, factory);
    }

    /**
     * Get the name of the tag for the derived node.
     *
     * @return name of the tag.
     */
    public String getTagName() {
        return TAG_NAME;
    }

    /**
     * {@inheritDoc}
     */
    public XmlNode newInstance(final Node n,
            final XmlNodeFactory<? extends XmlNode> factory) {
        return new Raise(n, factory);
    }

    /**
     * Retrieves the event attribute.
     *
     * @return value of the event attribute.
     * @see #ATTRIBUTE_EVENT
     */
    public String getEvent() {
        return getAttribute(ATTRIBUTE_EVENT);
    }

    /**
     * Sets the event attribute.
     *
     * @param event Value of the event attribute.
     * @see #ATTRIBUTE_EVENT
     */
    public void setEvent(final String event) {
        setAttribute(ATTRIBUTE_EVENT, event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canContainChild(final String tagName) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<String> getAttributeNames() {
        return ATTRIBUTE_NAMES;
    }
}
