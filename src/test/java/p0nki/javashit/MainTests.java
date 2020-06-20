package p0nki.javashit;

import org.junit.Test;
import p0nki.javashit.ast.JSASTCreator;
import p0nki.javashit.ast.JSParseException;
import p0nki.javashit.ast.nodes.JSASTNode;
import p0nki.javashit.builtins.Builtins;
import p0nki.javashit.object.JSObject;
import p0nki.javashit.run.JSContext;
import p0nki.javashit.run.JSEvalException;
import p0nki.javashit.token.JSTokenList;
import p0nki.javashit.token.JSTokenizeException;
import p0nki.javashit.token.JSTokenizer;

import java.util.HashMap;

public class MainTests {

    private void tokenize(String str) throws JSTokenizeException {
        System.out.println(str);
        JSTokenizer tokenizer = new JSTokenizer();
        JSTokenList tokens = tokenizer.tokenize(str);
        for (int i = 0; i < tokens.getSize(); i++) {
            System.out.println(tokens.get(i));
        }
    }

    private void run(JSContext ctx, String str) {
        JSTokenizer tokenizer = new JSTokenizer();
        System.out.println();
        System.out.println("<- " + str);
        JSTokenList tokens;
        try {
            tokens = tokenizer.tokenize(str);
        } catch (JSTokenizeException e) {
            int index = e.getIndex();
            System.out.print("   ");
            for (int i = 0; i < index; i++) System.out.print(" ");
            System.out.println("^");
            System.out.println("Tokenize error\n" + e.getMessage());
            return;
        }
        JSASTCreator astCreator = new JSASTCreator();
        try {
            while (tokens.hasAny()) {
                JSASTNode node = astCreator.parseExpression(tokens);
                JSObject result = node.evaluate(ctx);
                System.out.println("-> " + result.castToString());
            }
        } catch (JSParseException e) {
            System.out.print("  ");
            for (int i = 0; i < e.getToken().getStart(); i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < e.getToken().getEnd() - e.getToken().getStart(); i++) {
                System.out.print("^");
            }
            System.out.println();
            System.out.println(" " + e.getToken().toString());
            System.out.println("Parse error\n" + e.getMessage());
        } catch (JSEvalException e) {
            System.out.println("Eval error");
            System.out.println(e.getObject().toString());
        }
    }

    @Test
    public void test() {
        JSContext ctx = new JSContext(null, new HashMap<>());
        ctx.set("println", Builtins.PRINTLN);
        ctx.set("dir", Builtins.DIR);
        ctx.set("Math", Builtins.MATH);

        run(ctx, "dir(");

    }

}
