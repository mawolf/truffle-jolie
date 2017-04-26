// CheckStyle: start generated
package jolie.runtime;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeCost;
import java.util.concurrent.locks.Lock;

@GeneratedBy(SLForeignIsExecutableNodeSub.class)
public final class SLForeignIsExecutableNodeSubNodeGen extends SLForeignIsExecutableNodeSub {

    @CompilationFinal private int state_ = 1;

    private SLForeignIsExecutableNodeSubNodeGen() {
    }

    @Override
    public Object executeWithTarget(VirtualFrame frameValue, Object arg0Value) {
        int state = state_;
        if ((state & 0b10) != 0 /* is-active accessWithTarget(Object) */) {
            return accessWithTarget(arg0Value);
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
            this.state_ = state | 0b10 /* add-active accessWithTarget(Object) */;
            lock.unlock();
            hasLock = false;
            return accessWithTarget(arg0Value);
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

    public static SLForeignIsExecutableNodeSub create() {
        return new SLForeignIsExecutableNodeSubNodeGen();
    }

}
