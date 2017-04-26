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

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.SourceSection;
import jolie.JolieLanguage;

/**
 * Created by mawo on 4/1/17.
 */
public class RootProcess extends RootNode {

    /** The function body that is executed, and specialized during execution. */
    @Child private Expression bodyNode;

    /** The name of the function, for printing purposes only. */
    private final String name;

    @CompilerDirectives.CompilationFinal
    private boolean isCloningAllowed;

    public RootProcess(FrameDescriptor frameDescriptor, Expression bodyNode, SourceSection sourceSection, String name) {
        super(JolieLanguage.class, sourceSection, frameDescriptor);
        this.bodyNode = bodyNode;
        this.name = name;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        assert JolieLanguage.INSTANCE.findContext() != null;
        return bodyNode.executeGeneric(frame);
    }

    public Expression getBodyNode() {
        return bodyNode;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setCloningAllowed(boolean isCloningAllowed) {
        this.isCloningAllowed = isCloningAllowed;
    }

    @Override
    public boolean isCloningAllowed() {
        return isCloningAllowed;
    }

    @Override
    public String toString() {
        return "Program " + name;
    }
}
