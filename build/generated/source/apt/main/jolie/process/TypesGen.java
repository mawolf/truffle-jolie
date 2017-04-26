// CheckStyle: start generated
package jolie.process;

import com.oracle.truffle.api.dsl.GeneratedBy;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import java.math.BigInteger;
import jolie.runtime.JolieDefinition;
import jolie.runtime.JolieNullProcess;

@GeneratedBy(Types.class)
public final class TypesGen extends Types {

    @Deprecated public static final TypesGen TYPES = new TypesGen();

    protected TypesGen() {
    }

    public static boolean isLong(Object value) {
        return value instanceof Long;
    }

    public static long asLong(Object value) {
        assert value instanceof Long : "TypesGen.asLong: long expected";
        return (long) value;
    }

    public static long expectLong(Object value) throws UnexpectedResultException {
        if (value instanceof Long) {
            return (long) value;
        }
        throw new UnexpectedResultException(value);
    }

    public static boolean isBigInteger(Object value) {
        return value instanceof BigInteger;
    }

    public static BigInteger asBigInteger(Object value) {
        assert value instanceof BigInteger : "TypesGen.asBigInteger: BigInteger expected";
        return (BigInteger) value;
    }

    public static BigInteger expectBigInteger(Object value) throws UnexpectedResultException {
        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }
        throw new UnexpectedResultException(value);
    }

    public static boolean isBoolean(Object value) {
        return value instanceof Boolean;
    }

    public static boolean asBoolean(Object value) {
        assert value instanceof Boolean : "TypesGen.asBoolean: boolean expected";
        return (boolean) value;
    }

    public static boolean expectBoolean(Object value) throws UnexpectedResultException {
        if (value instanceof Boolean) {
            return (boolean) value;
        }
        throw new UnexpectedResultException(value);
    }

    public static boolean isString(Object value) {
        return value instanceof String;
    }

    public static String asString(Object value) {
        assert value instanceof String : "TypesGen.asString: String expected";
        return (String) value;
    }

    public static String expectString(Object value) throws UnexpectedResultException {
        if (value instanceof String) {
            return (String) value;
        }
        throw new UnexpectedResultException(value);
    }

    public static boolean isJolieDefinition(Object value) {
        return value instanceof JolieDefinition;
    }

    public static JolieDefinition asJolieDefinition(Object value) {
        assert value instanceof JolieDefinition : "TypesGen.asJolieDefinition: JolieDefinition expected";
        return (JolieDefinition) value;
    }

    public static JolieDefinition expectJolieDefinition(Object value) throws UnexpectedResultException {
        if (value instanceof JolieDefinition) {
            return (JolieDefinition) value;
        }
        throw new UnexpectedResultException(value);
    }

    public static JolieNullProcess expectJolieNullProcess(Object value) throws UnexpectedResultException {
        if (Types.isJolieNullProcess(value)) {
            return Types.asJolieNullProcess(value);
        }
        throw new UnexpectedResultException(value);
    }

}
