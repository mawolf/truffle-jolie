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
import com.oracle.truffle.api.source.SourceSection;
import jolie.JolieLanguage;
import jolie.runtime.JolieContext;

import java.util.Map;

/**
 * Created by mawo on 4/1/17.
 */
public class EvalRootProcess extends RootProcess {

    private final Map<String, RootProcess> functions;
    @CompilerDirectives.CompilationFinal
    private JolieContext context;

    public EvalRootProcess(FrameDescriptor frameDescriptor, Expression bodyNode, SourceSection sourceSection, String name, Map<String, RootProcess> functions) {
        super(frameDescriptor, bodyNode, sourceSection, name);
        this.functions = functions;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        /* Lazy registrations of functions on first execution. */
        if (context == null) {
            /* Function registration is a slow-path operation that must not be compiled. */
            CompilerDirectives.transferToInterpreterAndInvalidate();

            context = JolieLanguage.INSTANCE.findContext();
            context.getDefinitionRegistry().register(functions);
        }

        if (getBodyNode() == null) {
            /* The source code did not have a "main" function, so nothing to execute. */
            return null;
        }

        /* Conversion of arguments to types understood by Jolie. */
        Object[] arguments = frame.getArguments();
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = JolieContext.fromForeignValue(arguments[i]);
        }

        /* Now we can execute the body of the "main" function. */
        return super.execute(frame);
    }
}
