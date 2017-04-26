// CheckStyle: start generated
package jolie.runtime;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeCost;
import java.util.concurrent.locks.Lock;

@GeneratedBy(CheckFunctionSub.class)
public final class CheckFunctionSubNodeGen extends CheckFunctionSub {

    @CompilationFinal private int state_ = 1;

    private CheckFunctionSubNodeGen() {
    }

    @Override
    public Object executeWithTarget(VirtualFrame frameValue, Object arg0Value) {
        int state = state_;
        if ((state & 0b10) != 0 /* is-active testWithTarget(TruffleObject) */ && arg0Value instanceof TruffleObject) {
            TruffleObject arg0Value_ = (TruffleObject) arg0Value;
            return testWithTarget(arg0Value_);
        }
        CompilerDirectives.transferToInterpreterAndInvalidate();
        return executeAndSpecialize(arg0Value);
    }

    private Object executeAndSpecialize(Object arg0Value) {
        Lock lock = getLock();
        boolean hasLock = true;
        lock.lock();
        try {
            int state = state_ & 0xfffffffe/* mask-active uninitialized*/;
            if (arg0Value instanceof TruffleObject) {
                TruffleObject arg0Value_ = (TruffleObject) arg0Value;
                this.state_ = state | 0b10 /* add-active testWithTarget(TruffleObject) */;
                lock.unlock();
                hasLock = false;
                return testWithTarget(arg0Value_);
            }
            CompilerDirectives.transferToInterpreterAndInvalidate();
            throw new UnsupportedSpecializationException(this, new Node[] {});
        } finally {
            if (hasLock) {
                lock.unlock();
            }
        }
    }

    @Override
    public NodeCost getCost() {
        int state = state_ & 0xfffffffe/* mask-active uninitialized*/;
        if (state == 0b0) {
            return NodeCost.UNINITIALIZED;
        } else {
            return NodeCost.MONOMORPHIC;
        }
    }

    public static CheckFunctionSub create() {
        return new CheckFunctionSubNodeGen();
    }

}
