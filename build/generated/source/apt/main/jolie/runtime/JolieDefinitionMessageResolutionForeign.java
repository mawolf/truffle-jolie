package jolie.runtime;
import com.oracle.truffle.api.interop.ForeignAccess.Factory18;
import com.oracle.truffle.api.interop.ForeignAccess.Factory;
import com.oracle.truffle.api.interop.Message;
import com.oracle.truffle.api.interop.ForeignAccess;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.nodes.RootNode;

/**
 * This foreign access factory is generated by {@link jolie.runtime.JolieDefinitionMessageResolution}.
 * You are supposed to use it for the receiver object {@link jolie.runtime.JolieDefinition}.
 */
public final class JolieDefinitionMessageResolutionForeign implements Factory18, Factory {
  public static final ForeignAccess ACCESS = ForeignAccess.create(new JolieDefinitionMessageResolutionForeign(), jolie.runtime.CheckFunctionSub.createRoot(jolie.JolieLanguage.class));
  public static ForeignAccess createAccess() { return ForeignAccess.create(new JolieDefinitionMessageResolutionForeign(), jolie.runtime.CheckFunctionSub.createRoot(jolie.JolieLanguage.class)); }

  private JolieDefinitionMessageResolutionForeign() { }

  @Override
  public boolean canHandle(TruffleObject obj) {
    return (boolean) Truffle.getRuntime().createCallTarget(jolie.runtime.CheckFunctionSub.createRoot(jolie.JolieLanguage.class)).call(obj);
  }

    @Override
    public CallTarget accessIsNull() {
      return Truffle.getRuntime().createCallTarget(RootNode.createConstantNode(false));
    }
    @Override
    public CallTarget accessIsExecutable() {
      return Truffle.getRuntime().createCallTarget(jolie.runtime.SLForeignIsExecutableNodeSub.createRoot(jolie.JolieLanguage.class));
    }
    @Override
    public CallTarget accessIsBoxed() {
      return Truffle.getRuntime().createCallTarget(RootNode.createConstantNode(false));
    }
    @Override
    public CallTarget accessHasSize() {
      return Truffle.getRuntime().createCallTarget(RootNode.createConstantNode(false));
    }
    @Override
    public CallTarget accessGetSize() {
      return null;
    }
    @Override
    public CallTarget accessUnbox() {
      return null;
    }
    @Override
    public CallTarget accessRead() {
      return null;
    }
    @Override
    public CallTarget accessWrite() {
      return null;
    }
    @Override
    public CallTarget accessExecute(int argumentsLength) {
      return null;
    }
    @Override
    public CallTarget accessInvoke(int argumentsLength) {
      return null;
    }
    @Override
    public CallTarget accessNew(int argumentsLength) {
      return null;
    }
    @Override
    public CallTarget accessKeys() {
      return null;
    }
    @Override
    public CallTarget accessMessage(Message unknown) {
      return null;
    }
}
