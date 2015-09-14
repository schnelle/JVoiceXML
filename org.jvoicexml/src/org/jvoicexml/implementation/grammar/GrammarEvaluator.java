/*
 * JVoiceXML - A free VoiceXML implementation.
 *
 * Copyright (C) 2015 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package org.jvoicexml.implementation.grammar;

import java.net.URI;



/**
 * A grammar evaluator processes a given utterance into a recognition result.
 * @author Dirk Schnelle-Walka
 * @version $Revision: $
 * @since 0.7.8
 */
public interface GrammarEvaluator {
    /**
     * Retrieves the base URI of this grammar.
     * @return the URI
     */
    URI getUri();
    
    /**
     * Processes the given utterance and retrieves the corresponding semantic
     * interpretation.
     * @param utterance the current utterance
     * @return the determined semantic interpretation, maybe {@code null}
     */
    Object process(final String utterance);
}