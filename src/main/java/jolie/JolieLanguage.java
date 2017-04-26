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

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.debug.DebuggerTags;
import com.oracle.truffle.api.instrumentation.ProvidedTags;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.source.Source;
import jolie.lang.parse.OLParseTreeOptimizer;
import jolie.lang.parse.OLParser;
import jolie.lang.parse.Scanner;
import jolie.lang.parse.ast.Program;
import jolie.runtime.JolieContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@TruffleLanguage.Registration(name = "Jolie", version = "0.1", mimeType = JolieLanguage.MIME_TYPE)
@ProvidedTags({StandardTags.CallTag.class, StandardTags.StatementTag.class, StandardTags.RootTag.class, DebuggerTags.AlwaysHalt.class})
public class JolieLanguage extends TruffleLanguage<JolieContext> {

    public static final String MIME_TYPE = "application/x-jolie";

    //singleton pattern
    public static final JolieLanguage INSTANCE = new JolieLanguage();

    /**
     * No instances allowed apart from the {@link #INSTANCE singleton instance}.
     */
    private JolieLanguage() {
    }

    public JolieContext findContext() {
        CompilerAsserts.neverPartOfCompilation();
        return super.findContext(super.createFindContextNode());
    }

    @Override
    protected JolieContext createContext(Env env) {
        BufferedReader in = new BufferedReader(new InputStreamReader(env.in()));
        PrintWriter out = new PrintWriter(env.out(), true);
        return new JolieContext(env, in, out);
    }

    @Override
    protected CallTarget parse(ParsingRequest request) throws Exception {
        Source source = request.getSource();

        final Scanner scanner = new Scanner(source.getInputStream(), null, null);
        final OLParser olParser = new OLParser(scanner, new String[]{}, Jolie.class.getClassLoader());

        Program program = olParser.parse();
        program = OLParseTreeOptimizer.optimize( program );

        OOITBuilder ooitBuilder = new OOITBuilder();
        ooitBuilder.visit(program);
        ooitBuilder

        return Truffle.getRuntime().createCallTarget(evalMain);

    }

    @Override
    protected Object findExportedSymbol(JolieContext context, String globalName, boolean onlyExplicit) {
        return null;
    }

    @Override
    protected Object getLanguageGlobal(JolieContext context) {
        return null;
    }

    @Override
    protected boolean isObjectOfLanguage(Object object) {
        return false;
    }
}
