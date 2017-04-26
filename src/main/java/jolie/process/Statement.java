/*
 * Copyright (C) 2017 by Martin Wolf <mawol@martinwolf.eu>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jolie.process;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.Instrumentable;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.source.SourceSection;

@NodeInfo(language = "Jolie", description = "The abstract base node for all Jolie processes")
@Instrumentable(factory = StatementWrapper.class)
public abstract class Statement extends Node {

    private SourceSection sourceSection;

    private boolean hasStatementTag;
    private boolean hasRootTag;

    @Override
    public final SourceSection getSourceSection() {
        return sourceSection;
    }

    public void setSourceSection(SourceSection section) {
        assert this.sourceSection == null : "overwriting existing SourceSection";
        this.sourceSection = section;
    }

    /**
     * Execute this node as as statement, where no return value is necessary.
     */
    public abstract void executeVoid(VirtualFrame frame);

    /**
     * Marks this node as being a {@link StandardTags.StatementTag} for instrumentation purposes.
     */
    public final void addStatementTag() {
        hasStatementTag = true;
    }

    /**
     * Marks this node as being a {@link StandardTags.RootTag} for instrumentation purposes.
     */
    public final void addRootTag() {
        hasRootTag = true;
    }

    @Override
    protected boolean isTaggedWith(Class<?> tag) {
        if (tag == StandardTags.StatementTag.class) {
            return hasStatementTag;
        } else if (tag == StandardTags.RootTag.class) {
            return hasRootTag;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Node";
    }



}
