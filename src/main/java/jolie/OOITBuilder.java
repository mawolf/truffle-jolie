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

package jolie;

import com.oracle.truffle.api.frame.FrameDescriptor;
import jolie.lang.parse.CorrelationFunctionInfo;
import jolie.lang.parse.OLParser;
import jolie.lang.parse.OLVisitor;
import jolie.lang.parse.ast.*;
import jolie.lang.parse.ast.courier.CourierChoiceStatement;
import jolie.lang.parse.ast.courier.CourierDefinitionNode;
import jolie.lang.parse.ast.courier.NotificationForwardStatement;
import jolie.lang.parse.ast.courier.SolicitResponseForwardStatement;
import jolie.lang.parse.ast.expression.*;
import jolie.lang.parse.ast.types.TypeChoiceDefinition;
import jolie.lang.parse.ast.types.TypeDefinition;
import jolie.lang.parse.ast.types.TypeDefinitionLink;
import jolie.lang.parse.ast.types.TypeInlineDefinition;
import jolie.process.Expression;
import jolie.process.RootProcess;
import jolie.runtime.JolieDefinition;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mawo on 4/1/17.
 */
public class OOITBuilder implements OLVisitor {

    private final Map<String,JolieDefinition> definitionMap = new HashMap<>();

    @Override
    public void visit(Program program) {
        for( OLSyntaxNode node : program.children() )
            node.accept( this );
    }

    @Override
    public void visit(OneWayOperationDeclaration oneWayOperationDeclaration) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(RequestResponseOperationDeclaration requestResponseOperationDeclaration) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(DefinitionNode definitionNode) {
        FrameDescriptor frameDescriptor = new FrameDescriptor();

        final Expression functionBodyNode = buildProcess(definitionNode.body());
        final RootProcess rootNode = new RootProcess(frameDescriptor, functionBodyNode, null, definitionNode.id());

        this.definitionMap.put(definitionNode.id(),new JolieDefinition());
    }

    private Expression currProcess;

    private Expression buildProcess( OLSyntaxNode n )
    {
        if ( n == null ) {
            return null;
        }
        n.accept( this );
        return currProcess;
    }

    @Override
    public void visit(ParallelStatement parallelStatement) {
        throw new NotImplementedException();

    }

    @Override
    public void visit(SequenceStatement sequenceStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(NDChoiceStatement ndChoiceStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(OneWayOperationStatement oneWayOperationStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(RequestResponseOperationStatement requestResponseOperationStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(NotificationOperationStatement notificationOperationStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(SolicitResponseOperationStatement solicitResponseOperationStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(LinkInStatement linkInStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(LinkOutStatement linkOutStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(AssignStatement assignStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(AddAssignStatement addAssignStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(SubtractAssignStatement subtractAssignStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(MultiplyAssignStatement multiplyAssignStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(DivideAssignStatement divideAssignStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(IfStatement ifStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(DefinitionCallStatement definitionCallStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(WhileStatement whileStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(OrConditionNode orConditionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(AndConditionNode andConditionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(NotExpressionNode notExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(CompareConditionNode compareConditionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ConstantIntegerExpression constantIntegerExpression) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ConstantDoubleExpression constantDoubleExpression) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ConstantBoolExpression constantBoolExpression) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ConstantLongExpression constantLongExpression) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ConstantStringExpression constantStringExpression) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ProductExpressionNode productExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(SumExpressionNode sumExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(VariableExpressionNode variableExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(NullProcessStatement nullProcessStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(Scope scope) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(InstallStatement installStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(CompensateStatement compensateStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ThrowStatement throwStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ExitStatement exitStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ExecutionInfo executionInfo) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(CorrelationSetInfo correlationSetInfo) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(InputPortInfo inputPortInfo) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(OutputPortInfo outputPortInfo) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(PointerStatement pointerStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(DeepCopyStatement deepCopyStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(RunStatement runStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(UndefStatement undefStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ValueVectorSizeExpressionNode valueVectorSizeExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(PreIncrementStatement preIncrementStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(PostIncrementStatement postIncrementStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(PreDecrementStatement preDecrementStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(PostDecrementStatement postDecrementStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ForStatement forStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ForEachSubNodeStatement forEachSubNodeStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ForEachArrayItemStatement forEachArrayItemStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(SpawnStatement spawnStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(IsTypeExpressionNode isTypeExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(InstanceOfExpressionNode instanceOfExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(TypeCastExpressionNode typeCastExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(SynchronizedStatement synchronizedStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(CurrentHandlerStatement currentHandlerStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(EmbeddedServiceNode embeddedServiceNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(InstallFixedVariableExpressionNode installFixedVariableExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(VariablePathNode variablePathNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(TypeInlineDefinition typeInlineDefinition) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(TypeDefinitionLink typeDefinitionLink) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(InterfaceDefinition interfaceDefinition) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(DocumentationComment documentationComment) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(FreshValueExpressionNode freshValueExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(CourierDefinitionNode courierDefinitionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(CourierChoiceStatement courierChoiceStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(NotificationForwardStatement notificationForwardStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(SolicitResponseForwardStatement solicitResponseForwardStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(InterfaceExtenderDefinition interfaceExtenderDefinition) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(InlineTreeExpressionNode inlineTreeExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(VoidExpressionNode voidExpressionNode) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(ProvideUntilStatement provideUntilStatement) {
        throw new NotImplementedException();
    }

    @Override
    public void visit(TypeChoiceDefinition typeChoiceDefinition) {
        throw new NotImplementedException();
    }
}
