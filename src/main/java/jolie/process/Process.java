package jolie.process;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.Instrumentable;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.source.SourceSection;

/**
 * Created by mawo on 3/6/17.
 */
@NodeInfo(language = "Jolie", description = "The abstract base node for all Jolie processes")
@Instrumentable(factory = ProcessWrapper.class)
public abstract class Process extends Node {
    private SourceSection sourceSection;

    @Override
    public final SourceSection getSourceSection() {
        return sourceSection;
    }

    public abstract void executeVoid(VirtualFrame frame);

}
