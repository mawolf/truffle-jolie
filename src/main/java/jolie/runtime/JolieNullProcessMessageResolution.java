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

import com.oracle.truffle.api.interop.CanResolve;
import com.oracle.truffle.api.interop.MessageResolution;
import com.oracle.truffle.api.interop.Resolve;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.nodes.Node;
import jolie.JolieLanguage;

@MessageResolution(receiverType = JolieNullProcess.class, language = JolieLanguage.class)
public class JolieNullProcessMessageResolution {
    /*
     * An Jolie function resolves the IS_NULL message.
     */
    @Resolve(message = "IS_NULL")
    public abstract static class JolieForeignIsNullNode extends Node {
        public Object access(Object receiver) {
            return JolieNullProcess.SINGLETON == receiver;
        }
    }

    @CanResolve
    public abstract static class CheckNull extends Node {
        protected static boolean test(TruffleObject receiver) {
            return receiver instanceof JolieNullProcess;
        }
    }
}
