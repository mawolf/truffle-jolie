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

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import jolie.process.RootProcess;

import java.util.*;

/**
 * Created by mawo on 4/1/17.
 */
public class JolieDefinitionRegistry {

    private final Map<String, JolieDefinition> functions = new HashMap<>();

    /**
     * Returns the canonical {@link JolieDefinition} object for the given name. If it does not exist yet,
     * it is created.
     */
    public JolieDefinition lookup(String name, boolean createIfNotPresent) {
        JolieDefinition result = functions.get(name);
        if (result == null && createIfNotPresent) {
            result = new JolieDefinition(name);
            functions.put(name, result);
        }
        return result;
    }

    /**
     * Associates the {@link JolieDefinition} with the given name with the given implementation root
     * node. If the function did not exist before, it defines the function. If the function existed
     * before, it redefines the function and the old implementation is discarded.
     */
    public JolieDefinition register(String name, RootProcess rootNode) {
        JolieDefinition function = lookup(name, true);
        RootCallTarget callTarget = Truffle.getRuntime().createCallTarget(rootNode);
        function.setCallTarget(callTarget);
        return function;
    }

    public void register(Map<String, RootProcess> newFunctions) {
        for (Map.Entry<String, RootProcess> entry : newFunctions.entrySet()) {
            register(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Returns the sorted list of all functions, for printing purposes only.
     */
    public List<JolieDefinition> getDefinitions() {
        List<JolieDefinition> result = new ArrayList<>(functions.values());
        Collections.sort(result, Comparator.comparing(JolieDefinition::toString));
        return result;
    }
}
