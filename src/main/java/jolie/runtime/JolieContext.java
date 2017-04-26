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

package jolie.runtime;

import com.oracle.truffle.api.ExecutionContext;
import com.oracle.truffle.api.TruffleLanguage;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Created by mawo on 3/31/17.
 */
public class JolieContext extends ExecutionContext {

    private final JolieDefinitionRegistry definitionRegistry;

    public JolieContext(TruffleLanguage.Env env, BufferedReader input, PrintWriter output) {

        definitionRegistry = null;
    }

    public JolieDefinitionRegistry getDefinitionRegistry() {
        return definitionRegistry;
    }

    public static Object fromForeignValue(Object argument) {
        return null;
    }
}
