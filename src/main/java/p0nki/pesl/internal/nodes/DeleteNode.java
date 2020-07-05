package p0nki.pesl.internal.nodes;

import p0nki.pesl.api.PESLContext;
import p0nki.pesl.api.PESLEvalException;
import p0nki.pesl.api.object.ArrayLikeObject;
import p0nki.pesl.api.object.PESLObject;
import p0nki.pesl.api.object.UndefinedObject;
import p0nki.pesl.api.parse.ASTNode;
import p0nki.pesl.api.parse.PESLIndentedLogger;

import javax.annotation.Nonnull;

public class DeleteNode implements ASTNode {

    private final ASTNode deleteNode;

    public DeleteNode(ASTNode deleteNode) {
        this.deleteNode = deleteNode;
    }

    @Nonnull
    @Override
    public PESLObject evaluate(@Nonnull PESLContext context) throws PESLEvalException {
        if (deleteNode instanceof AccessPropertyNode) {
            PESLObject value = ((AccessPropertyNode) deleteNode).getValue().evaluate(context);
            PESLObject key = ((AccessPropertyNode) deleteNode).getKey().evaluate(context);
            if (value instanceof ArrayLikeObject) {
                return ((ArrayLikeObject) value).removeElement((int) key.asNumber().getValue());
            } else {
//                System.out.println("maplike delete key " + key.stringify());
//                System.out.println(value.asMapLike().getKey(key.stringify()));
//                System.out.println(value.asMapLike().keys());
//                System.out.println("maplike " + key + " " + value);
                return value.asMapLike().setKey(key.castToString(), UndefinedObject.INSTANCE);
            }
        } else {
            throw new PESLEvalException("Cannot delete this value");
        }
    }

    @Override
    public void print(@Nonnull PESLIndentedLogger logger) {
        logger.println("DELETE");
        logger.pushPrint(deleteNode);
    }
}
