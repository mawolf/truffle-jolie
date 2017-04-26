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

package jolie;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.source.Source;
import jolie.process.EvalRootProcess;
import jolie.process.RootProcess;
import jolie.runtime.JolieDefinitionRegistry;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class for execution of Jolie programs
 */
public class Jolie {
    public static void main(String[] args) throws IOException {
        Source source;
        if (args.length == 0) {
            // @formatter:off
            source = Source.newBuilder(new InputStreamReader(System.in)).
                    name("<stdin>").
                    mimeType(JolieLanguage.MIME_TYPE).
                    build();
            // @formatter:on
        } else {
            source = Source.newBuilder(new File(args[0])).build();
        }

        FrameDescriptor frameDescriptor = new FrameDescriptor();

        JolieDefinitionRegistry jolieDefinitionRegistry = new JolieDefinitionRegistry();
        RootProcess rootProcess = new RootProcess(frameDescriptor, null, null, "main");

        RootProcess main = rootProcess;
        RootProcess evalMain;
        if (main != null) {
            /*
             * We have a main function, so "evaluating" the parsed source means invoking that main
             * function. However, we need to lazily register functions into the SLContext first, so
             * we cannot use the original SLRootNode for the main function. Instead, we create a new
             * SLEvalRootNode that does everything we need.
             */
            evalMain = new EvalRootProcess(main.getFrameDescriptor(), main.getBodyNode(), main.getSourceSection(), main.getName(), null);
        } else {
            /*
             * Even without a main function, "evaluating" the parsed source needs to register the
             * functions into the SLContext.
             */
            evalMain = new EvalRootProcess(null, null, null, "[no_main]", null);
        }
        Truffle.getRuntime().createCallTarget(evalMain);


        //      Program program = new Program(frameDescriptor, new Program(frameDescriptor, ), null, "none");
        //Statement statement = new JolieNullProcess();

    }
}