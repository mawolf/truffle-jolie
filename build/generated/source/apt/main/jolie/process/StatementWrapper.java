// CheckStyle: start generated
package jolie.process;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.InstrumentableFactory;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.nodes.NodeCost;

@GeneratedBy(Statement.class)
public final class StatementWrapper implements InstrumentableFactory<Statement> {

    @Override
    public WrapperNode createWrapper(Statement delegateNode, ProbeNode probeNode) {
        return new StatementWrapper0(delegateNode, probeNode);
    }

    @GeneratedBy(Statement.class)
    private static final class StatementWrapper0 extends Statement implements WrapperNode {

        @Child private Statement delegateNode;
        @Child private ProbeNode probeNode;

        private StatementWrapper0(Statement delegateNode, ProbeNode probeNode) {
            this.delegateNode = delegateNode;
            this.probeNode = probeNode;
        }

        @Override
        public Statement getDelegateNode() {
            return delegateNode;
        }

        @Override
        public ProbeNode getProbeNode() {
            return probeNode;
        }

        @Override
        public NodeCost getCost() {
            return NodeCost.NONE;
        }

        @Override
        public void executeVoid(VirtualFrame frame) {
            try {
                probeNode.onEnter(frame);
                delegateNode.executeVoid(frame);
                probeNode.onReturnValue(frame, null);
            } catch (Throwable t) {
                probeNode.onReturnExceptional(frame, t);
                throw t;
            }
        }

    }
}
