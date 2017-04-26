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

import com.oracle.truffle.api.interop.ForeignAccess;
import com.oracle.truffle.api.interop.TruffleObject;

public final class JolieNullProcess implements TruffleObject {

    //singleton pattern
    public static final JolieNullProcess SINGLETON = new JolieNullProcess();

    /**
     * Disallow instantiation from outside to ensure that the {@link #SINGLETON} is the only
     * instance.
     */
    private JolieNullProcess() {
    }

    @Override
    public String toString() {
        return "NullProcess";
    }

    @Override
    public ForeignAccess getForeignAccess() {
        return JolieNullProcessMessageResolutionForeign.createAccess();
    }
}
