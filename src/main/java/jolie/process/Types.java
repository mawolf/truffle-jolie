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

import com.oracle.truffle.api.dsl.TypeCast;
import com.oracle.truffle.api.dsl.TypeCheck;
import com.oracle.truffle.api.dsl.TypeSystem;
import com.oracle.truffle.api.dsl.internal.DSLOptions;
import jolie.runtime.JolieDefinition;
import jolie.runtime.JolieNullProcess;

import java.math.BigInteger;

@TypeSystem({long.class, BigInteger.class, boolean.class, String.class, JolieDefinition.class, JolieNullProcess.class})
@DSLOptions(defaultGenerator = DSLOptions.DSLGenerator.FLAT)
public class Types {

    @TypeCheck(JolieNullProcess.class)
    public static boolean isJolieNullProcess(Object value) {
        return value == JolieNullProcess.SINGLETON;
    }

    @TypeCast(JolieNullProcess.class)
    public static JolieNullProcess asJolieNullProcess(Object value) {
        assert isJolieNullProcess(value);
        return JolieNullProcess.SINGLETON;
    }
}
